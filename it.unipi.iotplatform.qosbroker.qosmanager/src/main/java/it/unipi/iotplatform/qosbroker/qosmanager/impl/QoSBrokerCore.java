package it.unipi.iotplatform.qosbroker.qosmanager.impl;

import it.unipi.iotplatform.qosbroker.api.datamodel.EquivalentThingsInfoContainer;
import it.unipi.iotplatform.qosbroker.api.datamodel.EquivalentThingsList;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSreq;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSscopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.RequestResult;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceAgreementRequest;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceAgreementResponse;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceDefinition;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingIdThingServiceIdPair;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingService;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingServiceFeatures;
import it.unipi.iotplatform.qosbroker.qosmanager.api.QoSBrokerIF;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.Constants;
import it.unipi.iotplatform.qosbroker.qosmanager.utils.ThingInfoContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import eu.neclab.iotplatform.iotbroker.commons.EntityIDMatcher;
import eu.neclab.iotplatform.ngsi.api.datamodel.Code;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextAttribute;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElementResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextMetadata;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistrationAttribute;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistrationResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.DiscoverContextAvailabilityRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.DiscoverContextAvailabilityResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;
import eu.neclab.iotplatform.ngsi.api.datamodel.NotifyContextAvailabilityRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.NotifyContextAvailabilityResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.NotifyContextRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.NotifyContextResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.OperationScope;
import eu.neclab.iotplatform.ngsi.api.datamodel.QueryContextRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.QueryContextResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.ReasonPhrase;
import eu.neclab.iotplatform.ngsi.api.datamodel.RegisterContextRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.RegisterContextResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.Restriction;
import eu.neclab.iotplatform.ngsi.api.datamodel.StatusCode;
import eu.neclab.iotplatform.ngsi.api.datamodel.SubscribeContextAvailabilityRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.SubscribeContextAvailabilityResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.SubscribeContextRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.SubscribeContextResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.UnsubscribeContextAvailabilityRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.UnsubscribeContextAvailabilityResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.UnsubscribeContextRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.UnsubscribeContextResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.UpdateContextAvailabilitySubscriptionRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.UpdateContextAvailabilitySubscriptionResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.UpdateContextRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.UpdateContextResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.UpdateContextSubscriptionRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.UpdateContextSubscriptionResponse;
import eu.neclab.iotplatform.ngsi.api.ngsi10.Ngsi10Interface;
import eu.neclab.iotplatform.ngsi.api.ngsi10.Ngsi10Requester;
import eu.neclab.iotplatform.ngsi.api.ngsi9.Ngsi9Interface;
//import it.unipi.iotplatform.qosbroker.qosmonitor.api.QoSMonitorIF;

public class QoSBrokerCore implements Ngsi10Interface, Ngsi9Interface, QoSBrokerIF {
	
	private final String CONFMAN_REG_URL = System.getProperty("confman.ip");
	
	private static final AtomicInteger serviceIdCounter = new AtomicInteger();
	private static final AtomicInteger thingIdCounter = new AtomicInteger();
	private static final AtomicInteger thingServiceIdCounter = new AtomicInteger();
	
	/** Executor for asynchronous tasks */
	private final ExecutorService taskExecutor = Executors
			.newCachedThreadPool();
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(QoSBrokerCore.class);
	
	private final QoSManager qosManager = new QoSManager();
	
	private Ngsi10Interface qosMonitor;
	
	/** The implementation of the NGSI 9 interface */
	@Autowired
	private Ngsi9Interface ngsi9Impl;

	/** Used to make NGSI 10 requests. */
	private Ngsi10Requester ngsi10Requester;

//	/**
//	 * Returns the implementation of the NGSI 9 interface. This interface is
//	 * used by the core for making NGSI-9 discovery operations.
//	 *
//	 * @return The NGSI 9 interface.
//	 */
	public Ngsi9Interface getNgsi9Impl() {
		return ngsi9Impl;
	}

//	/**
//	 * Sets the implementation of the NGSI 9 interface. This interface is used
//	 * by the core for making NGSI-9 discovery operations.
//	 *
//	 * @param ngsi9
//	 *            The NGSI 9 interface.
//	 */
	public void setNgsi9Impl(Ngsi9Interface ngsi9) {
		ngsi9Impl = ngsi9;
	}

//	/**
//	 * Returns the ngsi10 requester. This object is used for making NGSI-10
//	 * requests to arbitrary URLs.
//	 *
//	 * @return the ngsi10 requester.
//	 */
	public Ngsi10Requester getNgsi10Requester() {

		return ngsi10Requester;
	}
//
//	/**
//	 * Sets the ngsi10 requester. This object is used for making NGSI-10
//	 * requests to arbitrary URLs.
//	 *
//	 * @param ngsi10Requester
//	 *            The new ngsi10 requester.
//	 */
	public void setNgsi10Requestor(Ngsi10Requester ngsi10Requester) {

		this.ngsi10Requester = ngsi10Requester;
	}
	
	/**
	 *
	 * This operation realizes synchronous retrieval of Context Information via
	 * the QueryContext method defined by NGSI 10. When this method is called,
	 * the IoT Broker Core tries to retrieve the requested information and
	 * returns whatever could be retrieved.
	 *
	 * @param request
	 *            The QueryContextRequest.
	 * @return The QueryContextResponse.
	 */
	@Override
	public QueryContextResponse queryContext(QueryContextRequest request) {

		return null;
//		/*
//		 * create associations operation scope for discovery
//		 */
//		OperationScope operationScope = new OperationScope(
//				"IncludeAssociations", "SOURCES");
//
//		/*
//		 * Create a new restriction with the same attribute expression and
//		 * operation scope as in the request.
//		 */
//		Restriction restriction = new Restriction();
//
//		if (request.getRestriction() != null) {
//			if (request.getRestriction().getAttributeExpression() != null) {
//				restriction.setAttributeExpression(request.getRestriction()
//						.getAttributeExpression());
//			}
//			if (request.getRestriction().getOperationScope() != null) {
//				restriction.setOperationScope(new ArrayList<OperationScope>(
//						request.getRestriction().getOperationScope()));
//			}
//
//		} else {
//
//			restriction.setAttributeExpression("");
//
//		}
//
//		/*
//		 * Add the associations operation scope to the the restriction.
//		 */
//
//		ArrayList<OperationScope> lstOperationScopes = null;
//
//		if (restriction.getOperationScope() == null) {
//			lstOperationScopes = new ArrayList<OperationScope>();
//			lstOperationScopes.add(operationScope);
//			restriction.setOperationScope(lstOperationScopes);
//		} else {
//			restriction.getOperationScope().add(operationScope);
//		}
//
//		DiscoverContextAvailabilityRequest discoveryRequest = new DiscoverContextAvailabilityRequest(
//				request.getEntityIdList(), request.getAttributeList(),
//				restriction);
//		logger.debug("DiscoverContextAvailabilityRequest:"
//				+ discoveryRequest.toString());
//
//		/* Get the NGSI 9 DiscoverContextAvailabilityResponse */
//		DiscoverContextAvailabilityResponse discoveryResponse = ngsi9Impl
//				.discoverContextAvailability(discoveryRequest);
//
//		/*
//		 * The following code lines are used to filter out certain metadata in
//		 * case the NGSI hierarchy extension is used.
//		 */
//		if (ngsiHierarchyExtension != null) {
//			ngsiHierarchyExtension
//			.filterOutSelfSubordination(discoveryResponse);
//		}
//		/* till here */
//
//		if ((discoveryResponse.getErrorCode() == null || discoveryResponse
//				.getErrorCode().getCode() == 200)
//				&& discoveryResponse.getContextRegistrationResponse() != null) {
//
//			logger.debug("Receive discoveryResponse from Config Man:"
//					+ discoveryResponse);
//			List<AssociationDS> assocList = associationUtil
//					.retrieveAssociation(discoveryResponse);
//
//			logger.debug("Association List Size: " + assocList.size());
//
//			List<AssociationDS> additionalRequestList = associationUtil
//					.initialLstOfmatchedAssociation(request, assocList);
//
//			logger.debug("(Step 1) Initial List Of matchedAssociation:"
//					+ additionalRequestList);
//
//			List<AssociationDS> transitiveList = associationUtil
//					.transitiveAssociationAnalysisFrQuery(assocList,
//							additionalRequestList);
//
//			logger.debug("(Step 2 ) Transitive List Of matchedAssociation:"
//					+ transitiveList);
//
//			logger.debug("(Step 2 a) Final additionalRequestList List Of matchedAssociation:"
//					+ additionalRequestList);
//
//			List<Pair<QueryContextRequest, URI>> queryList = createQueryRequestList(
//					discoveryResponse, request);
//
//			logger.debug("Query List Size: " + queryList.size());
//
//			QueryResponseMerger merger = new QueryResponseMerger(request);
//
//			 List of Task
//			List<Callable<Object>> tasks = new ArrayList<Callable<Object>>();
//
//			 Countdown of Task
//			CountDownLatch count = new CountDownLatch(queryList.size());
//
//			for (int i = 0; i < queryList.size(); i++) {
//				logger.debug("Starting Thread number: " + i);
//
//				logger.debug("info1:"
//						+ queryList.get(i).getLeft().getEntityIdList().get(0)
//						.getId());
//				logger.debug("info2:"
//						+ discoveryResponse.getContextRegistrationResponse()
//						.get(i).getContextRegistration()
//						.getProvidingApplication());
//
//				tasks.add(Executors.callable(new RequestThread(resultFilter,
//						ngsi10Requester, queryList.get(i).getLeft(), queryList
//						.get(i).getRight(), merger, count,
//						transitiveList)));
//
//			}
//
//			try {
//
//				long t0 = System.currentTimeMillis();
//				taskExecutor.invokeAll(tasks);
//				long t1 = System.currentTimeMillis();
//				logger.debug("Finished all tasks in " + (t1 - t0) + " ms");
//
//			} catch (InterruptedException e) {
//				logger.debug("Thread Error", e);
//			}
//
//			 Call the Merge Method
//			QueryContextResponse threadResponse = merger.get();
//
//			logger.debug("Response after merging: " + threadResponse);
//
//			if (resultFilter != null) {
//				logger.info("-----------++++++++++++++++++++++Begin Filter");
//				List<QueryContextRequest> lqcReq = new ArrayList<QueryContextRequest>();
//				lqcReq.add(request);
//				List<ContextElementResponse> lceRes = threadResponse
//						.getListContextElementResponse();
//				logger.info("-----------++++++++++++++++++++++ QueryContextRequest:"
//						+ lqcReq.toString()
//						+ " ContextElementResponse:"
//						+ lceRes.toString());
//
//				logger.info(lqcReq.size());
//				logger.info(lceRes.size());
//
//				List<QueryContextResponse> lqcRes = resultFilter.filterResult(
//						lceRes, lqcReq);
//
//				if (lqcRes.size() == 1) {
//					threadResponse = lqcRes.get(0);
//				}
//
//				logger.info("-----------++++++++++++++++++++++ After Filter ListContextElementResponse:"
//						+ lqcRes.toString()
//						+ " ContextElementResponse:"
//						+ lqcRes.toString());
//				logger.info("-----------++++++++++++++++++++++End Filter");
//			} else {
//
//				logger.info("Result filter not found!!");
//
//			}
//
//			final QueryContextResponse queryContextRespLIstAfterMerge = threadResponse;
//
//			logger.debug("QueryContextResponse after merger:" + threadResponse);
//
//			/**
//			 * The code snippet below is for dumping the data in a Big Data
//			 * repository in addition. This feature is currently disabled.
//			 */
//			if (bigDataRepository != null) {
//
//				new Thread() {
//					@Override
//					public void run() {
//
//						List<ContextElement> contextElementList = new ArrayList<ContextElement>();
//
//						Iterator<ContextElementResponse> iter = queryContextRespLIstAfterMerge
//								.getListContextElementResponse().iterator();
//
//						while (iter.hasNext()) {
//
//							ContextElementResponse contextElementResp = iter
//									.next();
//
//							contextElementList.add(contextElementResp
//									.getContextElement());
//
//						}
//
//						bigDataRepository.storeData(contextElementList);
//
//					}
//				}.start();
//			}
//
//			if (request.getRestriction() != null) {
//
//				String xpathExpression = request.getRestriction()
//						.getAttributeExpression();
//				applyRestriction(xpathExpression, threadResponse);
//
//			}
//
//			if (threadResponse.getListContextElementResponse() == null
//					|| threadResponse.getListContextElementResponse().isEmpty()) {
//
//				threadResponse.setErrorCode(new StatusCode(
//						Code.CONTEXTELEMENTNOTFOUND_404.getCode(),
//						ReasonPhrase.CONTEXTELEMENTNOTFOUND_404.toString(),
//						null));
//
//			}
//
//			return threadResponse;
//
//		} else {
//
//			QueryContextResponse response = new QueryContextResponse(null,
//					discoveryResponse.getErrorCode());
//
//			return response;
//
//		}

	}
	
	/**
	 *
	 * This operation triggers asynchronous retrieval of Context Information by
	 * the SubscribeContext method defined by NGSI 10. When this method is
	 * called, the IoT Broker Core will send notifications about the requested
	 * context data in regularQoSManagerCore intervals, where the exact conditions of sending
	 * notifications have to be specified in the request.
	 *
	 * @param request
	 *            The subscription request.
	 * @return The response returned by the IoT Broker in reaction to the
	 *         request. Note that the response does not contain the actually
	 *         requested data, but only management information about the
	 *         subscription.
	 */
	@Override
	public SubscribeContextResponse subscribeContext(
			final SubscribeContextRequest request) {
//		SubscribeContextResponse response;
//		logger.debug("Recieve Request: " + request);
//		response = northBoundWrapper.receiveReqFrmNorth(request);
//
//		return response;
		
		return null;

	}

	/**
	 * This operation can be used for canceling existing subscriptions. The
	 * method is defined by NGSI 10.
	 *
	 * @param request
	 *            The NGSI 10 SubscribeContextRequest.
	 * @return The NGSI 10 SubscribeContextResponse.
	 */
	@Override
	public UnsubscribeContextResponse unsubscribeContext(
			UnsubscribeContextRequest request) {

//		UnsubscribeContextResponse response = northBoundWrapper
//				.receiveReqFrmNorth(request);
//
//		return response;
		return null;

	}
	
	/**
	 * This operation implements the UpdateContext operation defined by NGSI 10.
	 * Upon retrieval of an UpdateContextRequest, the IoT Broker will forward
	 * the update to a fixed NGSI-10 data consumer, possibly including results
	 * of applying associations to the updated data.
	 *
	 * @param request
	 *            The NGSI 10 UpdateContextRequest.
	 * @return The NGSI 10 UpdateContextResponse.
	 */
	@Override
	public UpdateContextResponse updateContext(
			final UpdateContextRequest request) {

		return null;
		
//		UpdateContextResponse response = null;
//		List<AssociationDS> listAssociationDS = new LinkedList<AssociationDS>();
//		final List<ContextElement> lContextElements = request
//				.getContextElement();
//		final List<ContextElement> listContextElement = new LinkedList<ContextElement>();
//		UpdateContextRequest updateContextRequest = null;
//		 Going through individual ContextElement
//		Iterator<ContextElement> it = lContextElements.iterator();
//		while (it.hasNext()) {
//			ContextElement ce = it.next();
//
//			/*
//			 * Retrieving EntityID and Entity Attributes for
//			 * DiscoverContextAvailabilityRequest
//			 */
//			List<EntityId> eidList = new LinkedList<EntityId>();
//			eidList.add(ce.getEntityId());
//
//			List<ContextAttribute> lContextAttributes = ce
//					.getContextAttributeList();
//			List<String> attributeList = new LinkedList<String>();
//
//			if (lContextAttributes != null && !lContextAttributes.isEmpty()) {
//
//				Iterator<ContextAttribute> itAttributeList = lContextAttributes
//						.iterator();
//				while (itAttributeList.hasNext()) {
//					ContextAttribute ca = itAttributeList.next();
//					attributeList.add(ca.getName());
//				}
//			}
//			 Creating Restriction OperationScopes for
//			 DiscoverContextAvailabilityRequest
//			OperationScope os = new OperationScope("IncludeAssociations",
//					"TARGETS");
//			List<OperationScope> loperOperationScopes = new LinkedList<OperationScope>();
//			loperOperationScopes.add(os);
//			Restriction restriction = new Restriction("", loperOperationScopes);
//
//			 Create the NGSI 9 DiscoverContextAvailabilityRequest
//			DiscoverContextAvailabilityRequest discoveryRequest = new DiscoverContextAvailabilityRequest(
//					eidList, attributeList, restriction);
//			 Get the NGSI 9 DiscoverContextAvailabilityResponse
//			DiscoverContextAvailabilityResponse discoveryResponse = ngsi9Impl
//					.discoverContextAvailability(discoveryRequest);
//
//			/*
//			 * Getting Associations information from
//			 * DiscoverContextAvailabilityResponse
//			 */
//
//			List<ContextRegistrationResponse> lcrr = discoveryResponse
//					.getContextRegistrationResponse();
//			Iterator<ContextRegistrationResponse> itContextRegistrationResponse = lcrr
//					.iterator();
//			while (itContextRegistrationResponse.hasNext()) {
//				ContextRegistrationResponse crr = itContextRegistrationResponse
//						.next();
//
//				List<ContextMetadata> lcmd = crr.getContextRegistration()
//						.getListContextMetadata();
//
//				Iterator<ContextMetadata> it1 = lcmd.iterator();
//				while (it1.hasNext()) {
//					ContextMetadata cmd = it1.next();
//					if ("Association".equals(cmd.getType().toString())) {
//
//						logger.debug("++++++++++++++++++++++++++++++++++++++++++++++++++befor value");
//						String s = "<value>" + cmd.getValue() + "</value>";
//						logger.debug("++++++++++++++++++++++++++++++++++++++++++++++++++befor value");
//						ContextMetadataAssociation cma = (ContextMetadataAssociation) xmlFactory
//								.convertStringToXml(cmd.toString(),
//										ContextMetadataAssociation.class);
//						XmlFactory xmlFac = new XmlFactory();
//						ValueAssociation va = (ValueAssociation) xmlFac
//								.convertStringToXml(s, ValueAssociation.class);
//						cma.setValue(va);
//
//						if (va.getAttributeAssociation().size() == 0) {
//
//							AssociationDS ads = new AssociationDS(
//									new EntityAttribute(va.getSourceEntity(),
//											""), new EntityAttribute(
//													va.getSourceEntity(), ""));
//							listAssociationDS.add(ads);
//						} else {
//							List<AttributeAssociation> lAttributeAsociations = va
//									.getAttributeAssociation();
//							for (AttributeAssociation aa : lAttributeAsociations) {
//								AssociationDS ads = new AssociationDS(
//										new EntityAttribute(
//												va.getSourceEntity(),
//												aa.getSourceAttribute()),
//												new EntityAttribute(va
//														.getTargetEntity(), aa
//														.getTargetAttribute()));
//								listAssociationDS.add(ads);
//							}
//						}
//					}
//
//				}
//			}
//			logger.debug("List of Assocaions from ConfigManager:"
//					+ listAssociationDS.toString());
//			if (!listAssociationDS.isEmpty()) {
//				for (ContextAttribute ca : ce.getContextAttributeList()) {
//
//					List<EntityAttribute> loutput = associationUtil
//							.findA(listAssociationDS,
//									new EntityAttribute(ce.getEntityId(), ca
//											.getName()));
//					logger.debug("List of effective Associations:"
//							+ loutput.toString());
//					EntityId currentEntityID = null;
//
//					for (EntityAttribute ea1 : loutput) {
//						List<ContextAttribute> lcaRes = new LinkedList<ContextAttribute>();
//						if (currentEntityID != null) {
//							if (!currentEntityID.getId().equals(
//									ea1.getEntity().getId())) {
//								ContextElement ceRes = new ContextElement(
//										ea1.getEntity(),
//										ce.getAttributeDomainName(), lcaRes,
//										ce.getDomainMetadata());
//								listContextElement.add(ceRes);
//								currentEntityID = ea1.getEntity();
//							}
//						} else {
//							ContextElement ceRes = new ContextElement(
//									ea1.getEntity(),
//									ce.getAttributeDomainName(), lcaRes,
//									ce.getDomainMetadata());
//							listContextElement.add(ceRes);
//							currentEntityID = ea1.getEntity();
//						}
//						ContextAttribute ca1 = new ContextAttribute(
//								"".equals(ea1.getEntityAttribute()) ? ca.getName()
//										: ea1.getEntityAttribute(),
//										ca.getType(), ca.getcontextValue().toString(),
//										ca.getMetadata());
//						lcaRes.add(ca1);
//
//					}
//				}
//
//			} else {
//
//				listContextElement.add(ce);
//
//			}
//
//		}
//
//		updateContextRequest = new UpdateContextRequest(listContextElement,
//				request.getUpdateAction());
//
//		logger.info("Started Contact pub/sub broker..");
//
//		/**
//		 * Dump data in Big Data Repository if present.
//		 */
//		 if (bigDataRepository != null) {
//		
//		 new Thread() {
//		
//		 @Override
//		 public void run() {
//		
//		 bigDataRepository.storeData(lContextElements);
//		
//		 }
//		 }.start();
//		
//		 }
//		
//		try {
//
//			response = ngsi10Requester.updateContext(updateContextRequest,
//					new URI(pubSubUrl));
//		} catch (URISyntaxException e) {
//			logger.debug("URI Syntax Error", e);
//		}
//
//		return response;

	}
	
	/**
	 * This operation implements the NotifyContext operation of NGSI 10. Upon
	 * reception of a notifyContext operation the IoT Broker will find out the
	 * relevant original subscriptions from applications and triggers
	 * notification of these applications are notified accordingly.
	 *
	 *
	 * @param request
	 *            the NotifyContextRequest
	 * @return the NotifyContextResponse.
	 */
	@Override
	public NotifyContextResponse notifyContext(
			final NotifyContextRequest request) {

		return null;

	}
	
	/**
	 * This operation implements the NotifyContextAvailability operation of NGSI
	 * 9. Upon reception of an availability operations, the IoT Broker will
	 * interact with the new NGSI data sources that have become available in
	 * order to satisfy the application subscriptions it maintains.
	 *
	 *
	 * @param request
	 *            the NotifyContextAvailabilityRequest
	 * @return the NotifyContextAvailabilityResponse.
	 */
	@Override
	public NotifyContextAvailabilityResponse notifyContextAvailability(
			final NotifyContextAvailabilityRequest request) {

		return null;

	}
	
	/**
	 * This method is not implemented by the IoT Broker Core and returns null.
	 */
	@Override
	public RegisterContextResponse registerContext(
			RegisterContextRequest request) {
		return null;
	}

	/**
	 * This method is not implemented by the IoT Broker Core and returns null.
	 */
	@Override
	public DiscoverContextAvailabilityResponse discoverContextAvailability(
			DiscoverContextAvailabilityRequest request) {
		return null;
	}

	/**
	 * This method is not implemented by the IoT Broker Core and returns null.
	 */
	@Override
	public SubscribeContextAvailabilityResponse subscribeContextAvailability(
			SubscribeContextAvailabilityRequest request) {
		return null;
	}

	/**
	 * This method is not implemented by the IoT Broker Core and returns null.
	 */
	@Override
	public UpdateContextAvailabilitySubscriptionResponse updateContextAvailabilitySubscription(
			UpdateContextAvailabilitySubscriptionRequest request) {
		return null;
	}

	/**
	 * This method is not implemented by the IoT Broker Core and returns null.
	 */
	@Override
	public UnsubscribeContextAvailabilityResponse unsubscribeContextAvailability(
			UnsubscribeContextAvailabilityRequest request) {
		return null;
	}
	
	/**
	 * This method is not implemented by the IoT Broker Core and returns null.
	 */
	@Override
	public UpdateContextSubscriptionResponse updateContextSubscription(
			UpdateContextSubscriptionRequest request) {

		return null;

	}

	@Override
	public ServiceAgreementResponse createAgreement(ServiceAgreementRequest offer) throws Exception{

		//transactionID to identify the service request
		String transactionID = UUID.randomUUID().toString();
		
//		parse the request to create discovery request
		
		//always only one serviceRequest in our implementation
		//TODO manage serviceAgreementRequest as List of serviceDefinition
		ServiceDefinition serviceRequest = offer.getServiceDefinitionList().get(0);
		
		String opType = serviceRequest.getOperationType();
		
		//Create a new restriction with the same attribute expression and
		//operation scope as in the request.
		Restriction restriction = getRestriction(serviceRequest);
		
		if(restriction == null){
			throw new Exception("No restrictions in ServiceAgreementRequest");
		}
		
		QoSreq qosRequirements = new QoSreq();
		
		//remove the QoS scopeValues from the operationScopes list, to store them
		//in the QoSreq object
		qosRequirements = getQoSrequirements(restriction.getOperationScope());
		
		if(qosRequirements == null){
			throw new Exception("No QoS requirements in ServiceAgreementRequest");
		}

//		CREATE THE REQUEST FROM THE INFO TAKEN FROM SERVICE_AGREEMENT_REQUEST
		
		//create Map<ServId, ServiceName> of services
		HashMap<Integer, String> servicesMap = createServiceList(serviceRequest.getAttributeList());
		
		//object to store the details of the service Request
		Request request = new Request();
		request.setTransactionId(transactionID);
		request.setOpType(opType);
		request.setQosRequirements(qosRequirements);
		request.setRestriction(restriction);
		request.setEntityIdList(serviceRequest.getEntityIdList());
		request.setRequestedServiceMap(servicesMap);
		
		StatusCode statusCode = new StatusCode();
		
//		discovery phase
		
		//get list of equivalentThings
		RequestResult reqResult = discoverThings(request, statusCode);
		
		if(reqResult == null){
			
			ServiceAgreementResponse response = new ServiceAgreementResponse();
			
			response.setServiceID(null);
			
			response.setErrorCode(statusCode);
			
			return response;
		}		
		
		logger.debug(reqResult.toString());
		
		String negotiationOffer = qosManager.getTemplate();
		//TODO set values in the template
		
		qosManager.createAgreement(negotiationOffer, reqResult);
		
		
		logger.info("############## createAgreement ###############");
		
		
		ServiceAgreementResponse response = new ServiceAgreementResponse();
		
		response.setServiceID("home_service");
		
		StatusCode statusCodeEx = new StatusCode(
				Code.OK_200.getCode(),
				ReasonPhrase.OK_200.toString(), "Succes Operation");
		
		response.setErrorCode(statusCode);
		
		return response;
	}

	/* discover a list of equivalent things given the request object.
	 * the flag value indicates if the requestResult structures must be updated by the monitoring task */
	private RequestResult discoverThings(Request request, StatusCode statusCode){
		
		//list of requested services names
		List<String> requestedServicesNameList = new ArrayList<>();
		
		//list of objects that represents the requested Services <servId, requestedServiceName>
		Collection<String> servicesRequestsList = request.getRequestedServicesMap().values();

		//get the list of services names (attributes names) for the request to IoTDiscovery
		for(String servName: servicesRequestsList){
			
			requestedServicesNameList.add(servName);
		}
		
		//create a discovery request object to get ContextRegResp List (list of things)
		DiscoverContextAvailabilityRequest discoveryRequest = new DiscoverContextAvailabilityRequest(
				request.getEntityIdList(), requestedServicesNameList, request.getRestriction());
		
		logger.debug("DiscoverContextAvailabilityRequest:"
				+ discoveryRequest.toString());
		
		//Get the NGSI 9 DiscoverContextAvailabilityResponse 
		DiscoverContextAvailabilityResponse discoveryResponse = ngsi9Impl
				.discoverContextAvailability(discoveryRequest);
		
		if ((discoveryResponse.getErrorCode() == null || discoveryResponse
				.getErrorCode().getCode() == 200)
				&& discoveryResponse.getContextRegistrationResponse() != null) {
			
			List<ContextRegistrationResponse> ContextRegistrationResponseList = 
					discoveryResponse.getContextRegistrationResponse();
			
			//build request to QoSmonitor from List<ContextRegistrationResponse>
			//look for the battery level in the ContextElements
			//associated to the list of ContextRegistrationResponse elements
			QueryContextRequest queryRequest = setRequestToQoSMonitor(ContextRegistrationResponseList);
			
			//request to the QoSmonitor
			QueryContextResponse qosMonitorResponse = qosMonitor.queryContext(queryRequest);
			
			if(qosMonitorResponse == null){
				
				statusCode =  new StatusCode(
						Code.BADREQUEST_400.getCode(),
						ReasonPhrase.BADREQUEST_400.toString(), "No response from QoSMonitor");
				
				return null;
			}
			
			//create, from the List<ContextRegistrationResponse>, List<ContextElement>, 
			//a map of things objects (with a map of thing services inside) 
			//and check if there is at least one ContextRegistrationResponse for
			//each service (checking if the ContextRegistrationResponse has an ContextAttribute = requested service)
			//and if each ContextRegistrationResponse element has an associated ContextElement object
			
			double maxRespTime = request.getQosRequirements().getMaxResponseTime();
			
			EquivalentThingsInfoContainer equivalentThingsMappings = 
					createThingsMap(request.getRequestedServicesMap(), ContextRegistrationResponseList, qosMonitorResponse.getListContextElementResponse(),maxRespTime);
			
			if(equivalentThingsMappings == null){
				
				statusCode =  new StatusCode(
						Code.BADREQUEST_400.getCode(),
						ReasonPhrase.BADREQUEST_400.toString(), "Service Agreement can't be achieved");
				
				return null;
			}
			
			if(discoveryResponse.getErrorCode() != null){
				statusCode = discoveryResponse.getErrorCode();
			}
			else{
				statusCode =  new StatusCode(
						Code.OK_200.getCode(),
						ReasonPhrase.OK_200.toString(), "Result");
			}

			//object RequestResult that contains the Request object and
			//the map of equivalent things
			RequestResult reqResult = new RequestResult();
			
			reqResult.setRequest(request);
			reqResult.setEquivalentThingsMappings(equivalentThingsMappings);
			
			return reqResult;
		}
		else{
			
			//no elements found in the iotDiscovery
			statusCode = discoveryResponse.getErrorCode();
			return null;
		}
	}

	/* function to create a map of things that can satisfy the requested services */
	private EquivalentThingsInfoContainer createThingsMap(HashMap<Integer, String> servicesRequestsMap,
			List<ContextRegistrationResponse> contextRegistrationResponseList,
			List<ContextElementResponse> qosMonitorResponse, double maxResponseTime) {
		
		//no ContextElement so no batteryLevel
		if(qosMonitorResponse.isEmpty()) return null;
		
		HashMap<Integer, Thing> thingsMap = new HashMap<>();
		
		//Map<ServId, List<ThingId, ThingServiceId>>
		HashMap<Integer, EquivalentThingsList> eqThingsListPerService = new HashMap<>();
		
		//first check if for each
		//contextRegResp (Thing) object there is 
		//an associated ContextElement (Thing battery level) object
		
		//creation of a new list that represents the pair ContextRegistration
		//ContextElement (services on a thing and batteryLevel of a thing)
		//the pair is enclosed in an object ThingInfoContainer
		//and it is represented through a series of map objects
		List<ThingInfoContainer> thingInfoContainersList = new ArrayList<>();
		
		for(ContextRegistrationResponse crr: contextRegistrationResponseList){
			
			for(ContextElementResponse contElemResp: qosMonitorResponse){
				
				//check if in the list of entityId of the crr
				//there is an entityId that match with
				//the entityId of contElemResp
				if(matchEntityId(contElemResp, crr)){
					
					//id of the entityId structure on which it is matched the 
					//ContextRegistration with the ContextElement
					String id = contElemResp.getContextElement().getEntityId().getId();
					List<ContextRegistrationAttribute> contRegAttrsList = crr.getContextRegistration().getContextRegistrationAttribute();
					List<ContextAttribute> contAttrsList = contElemResp.getContextElement().getContextAttributeList();
					
					//All the list passed in the getThingInfoContainer function
					//are converted in maps
					//The assumption is that all attributes names in the lists are unique
					//that is every ContextRegistrationAttribute_Name is unique
					//the same assumption is true for ContextAttribute_Name
					ThingInfoContainer thingInfoContainer = 
							ThingInfoContainer.getThingInfoContainer(id, contRegAttrsList, contAttrsList);
					
					if(thingInfoContainer == null){
						
						logger.error("thingInfoContainer object null");
						return null;
					}
					
					thingInfoContainersList.add(thingInfoContainer);
				}
			}
		}

		//none contRegAttrListContElemPair is possible
		if(thingInfoContainersList.isEmpty()) return null;
		
		//check if for each requested service name there is at least one
		//contRegAttrListContElemPair and create for each contRegAttrListContElemPair
		//that satisfies the request service an object thing (with a list of services
		//that satisfies, that is a list of ThingServices)
		
		//map to check if it is available a service at least on one thing.
		//the key is the service name taken from the attributeList.
		//the value is set to true when it is found the thing that has that service
		//given by the key
		HashMap<String, Boolean> serviceAvailableAtLeastOnOneThing = new HashMap<>();
		
		for(Map.Entry<Integer, String> serviceEntry : servicesRequestsMap.entrySet()){
		    
			serviceAvailableAtLeastOnOneThing.put(serviceEntry.getValue(), false);
			
			//for each service there is a list of equivalent things with a a thing service
			//that satisfy that service requested
			eqThingsListPerService.put(serviceEntry.getKey(), new EquivalentThingsList());
		}
		
		//iterate on the List<ThingInfoContainer> to create the 
		//Map<thingId, Thing> with the info in the previous List
		for(ThingInfoContainer thingInfoContainer 
				:thingInfoContainersList){
			
			//get the Maps that are used to look what ContextRegistration has
			//a ContextAttribute that has a name equal to requestedServiceName
			Map<String, ContextRegistrationAttribute> contRegAttrsMap = thingInfoContainer.getContRegAttrsMap();
			Map<String, Map<String, ContextMetadata>> contRegAttrsMetadataMap = thingInfoContainer.getContRegAttrsMetadataMapForEachContRegAttr();
			Map<String, ContextAttribute> contAttrsMap = thingInfoContainer.getContAttrsMap();
			
			Thing t = new Thing();				
			HashMap<Integer, ThingService> thingServicesMap = new HashMap<>();	
			
			t.setContextEntityId(thingInfoContainer.getContextEntityId());
			
			Double batteryLevel = Double.valueOf(String.valueOf(contAttrsMap.get("battery").getcontextValue()));
			
			t.setBatteryLevel(batteryLevel);
			
			Integer thingId = thingIdCounter.getAndIncrement();
			
			//look for what services are exposed by the ContextRegistration(Thing)
			for(Map.Entry<Integer, String> serviceEntry : servicesRequestsMap.entrySet()){
				
				//the Thing exposes the service requested serviceRequest
				if(contRegAttrsMap.get(serviceEntry.getValue()) != null){

					Integer serviceId = serviceEntry.getKey();
					String requestedServiceName = serviceEntry.getValue();
					
					ContextRegistrationAttribute contRegAttr  = contRegAttrsMap.get(requestedServiceName);
					
					if(contRegAttr == null){
						
						logger.error("ContextRegistrationAttribute object null");
						return null;
					}
					
					Map<String, ContextMetadata> contMetadata = contRegAttrsMetadataMap.get(contRegAttr.getName());
					
					if(contMetadata == null){
						
						logger.error("Map<ContextRegistrationAttributeName, ContextMetadata> object null");
						return null;
					}
					
					ThingServiceFeatures thingServFeat = new ThingServiceFeatures();
					
					Double latency = Double.valueOf(String.valueOf(contMetadata.get("latency").getValue()));
					
					if(latency == null){
						
						logger.error("latency object null");
						return null;
					}
					
					//The ThingService has a latency greater than the maxResponseTime
					//so it is not taken
					if(latency > maxResponseTime) continue;
					
					//set the bool associated to requestedServiceName, to
					//indicate that the requestedServiceName has at
					//least an associated Thing
					serviceAvailableAtLeastOnOneThing.put(requestedServiceName, true);
					
					Double energyCost = Double.valueOf(String.valueOf(contMetadata.get("energy_cost").getValue()));
					
					if(energyCost == null){
						
						logger.error("energyCost object null");
						return null;
					}

					thingServFeat.setEnergyCost(energyCost);
					thingServFeat.setLatency(latency);
					
					ThingService thingService = new ThingService();
					
					thingService.setServiceName(contRegAttr.getName());
					
					thingService.setThingServFeatures(thingServFeat);
					
					Integer thingServiceId = thingServiceIdCounter.getAndIncrement();
					thingServicesMap.put(thingServiceId, thingService);
					
					//add a thingId in the list of equivalent things for that service					
					//object that represents the pair thing id -thing service id
					ThingIdThingServiceIdPair tId_tsId = new ThingIdThingServiceIdPair();
					
					tId_tsId.setThingId(thingId);
					tId_tsId.setThingServiceId(thingServiceId);
					
					//add the pair tId_tsId associated to the service with id serviceId
					eqThingsListPerService.get(serviceId).getEquivalentThingIdThingServiceIdList().add(tId_tsId);
				}
			}
			
			t.setThingServices(thingServicesMap);
			
			thingsMap.put(thingId, t);
		}
		
		//check if every requestedService has an associated Thing
		for(Map.Entry<Integer, String> serviceEntry : servicesRequestsMap.entrySet()){
			
			if(!serviceAvailableAtLeastOnOneThing.get(serviceEntry.getValue())){
				
				return null;
			}
		}
		
		EquivalentThingsInfoContainer equivalentThingsMappings = new EquivalentThingsInfoContainer();
		
		equivalentThingsMappings.setThingsMap(thingsMap);
		
		equivalentThingsMappings.setEqThingsListPerService(eqThingsListPerService);
		
		return equivalentThingsMappings;
	}

	
	/* function to create a list of requested services from the
	   attribute list taken from serviceRequestAgreement */
	private HashMap<Integer, String> createServiceList(List<String> attributeList) {

		//get a list of unique service names
		List<String> requestedServiceNames = new ArrayList<String>(new LinkedHashSet<String>(attributeList));
		
		HashMap<Integer, String> serviceMap = new HashMap<>();
		
		for(String serviceName: requestedServiceNames){
			
			int servId = serviceIdCounter.getAndIncrement();
			
			serviceMap.put(servId, serviceName);
		}
		
		return serviceMap;
	}

	/* get Restriction from serviceRequest */
	private Restriction getRestriction(
			ServiceDefinition serviceRequest) {
		
		if (serviceRequest.getRestriction() != null) {
			
			Restriction restriction = new Restriction();
			
			if (serviceRequest.getRestriction().getOperationScope() != null) {
				
				//get the list of opeartionScopes in the restriction object
				ArrayList<OperationScope> operationScopeList = new ArrayList<OperationScope>(
						serviceRequest.getRestriction().getOperationScope());
				
				restriction.setOperationScope(operationScopeList);
				
			}
			else {
	
				restriction.setAttributeExpression("");
			}
			
			return restriction;
		}
		else{
			return null;
		}

	}
	
	
	/* function to look for an entityId, in the ContextRegistrationResponse entityId List,
	   that match with the EntityId in the ContextElementResponse */
	private boolean matchEntityId(ContextElementResponse contElemResp,
			ContextRegistrationResponse crr) {
		
		EntityId contElemEntId = contElemResp.getContextElement().getEntityId();
		
		List<EntityId> contRegEntIdList = crr.getContextRegistration().getListEntityId();
		
		for(EntityId contRegEntId: contRegEntIdList){
			
			if(EntityIDMatcher.matcher(contElemEntId, contRegEntId)){
				return true;
			}
		}
		return false;
	}

	/* function to create a request to send to the QoSmonitor */
	private QueryContextRequest setRequestToQoSMonitor(
			List<ContextRegistrationResponse> contextRegistrationResponseList) {

		QueryContextRequest request = new QueryContextRequest();
		
		//list of entityId taken from the response of the iotDiscovery
		List<EntityId> responseEntIdList = new ArrayList<>();
		
		//list of entityId stored in the ContReg 
		//inside the contextRegistrationResponseList
		List<EntityId> contRegEntityIdList = new ArrayList<>();
		
		for(ContextRegistrationResponse crr: contextRegistrationResponseList){
			
			contRegEntityIdList = crr.getContextRegistration().getListEntityId();
			
			for(EntityId eId: contRegEntityIdList){
				responseEntIdList.add(eId);
			}
		}
		
		request.setEntityIdList(responseEntIdList);
		
		return request;
	}

	/* function to get QoS requirements from the restriction */
	private QoSreq getQoSrequirements(List<OperationScope> operationScopeList){
		
		for(OperationScope opScope : operationScopeList){
			
			if(opScope.getScopeType().contentEquals(Constants.QOS)){
				
				QoSreq qosReq = new QoSreq();
				
				QoSscopeValue qosScopeValue = new QoSscopeValue();
				
				qosScopeValue = QoSscopeValue.convertObjectToJaxbObject(opScope.getScopeValue(), qosScopeValue);
				
				qosReq.setMaxResponseTime(qosScopeValue.getMaxResponseTime());
				
				qosReq.setMaxRateRequest(qosScopeValue.getMaxRateRequest());
				
				//remove qosOperationScope from the operationScopeList
				operationScopeList.remove(opScope);
				
				return qosReq;
			}
		}
		
		return null;
	}
	
	public void setNgsi10Requester(Ngsi10Requester ngsi10Requester) {
		this.ngsi10Requester = ngsi10Requester;
	}

	public Ngsi10Interface getQosMonitor() {
		return qosMonitor;
	}

	public void setQosMonitor(Ngsi10Interface qosMonitor) {
		this.qosMonitor = qosMonitor;
	}

}
