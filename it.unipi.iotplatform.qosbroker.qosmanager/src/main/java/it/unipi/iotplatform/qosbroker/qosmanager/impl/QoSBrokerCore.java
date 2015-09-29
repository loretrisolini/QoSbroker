package it.unipi.iotplatform.qosbroker.qosmanager.impl;

import it.unipi.iotplatform.qosbroker.qosmanager.api.QoSBrokerIF;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.QoSreq;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.QoSscopeValue;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.Request;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.Constants;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.Service;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceAgreementRequest;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceAgreementResponse;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceDefinition;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.Thing;
//import it.unipi.iotplatform.qosbroker.qosmonitor.api.QoSMonitorIF;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

public class QoSBrokerCore implements Ngsi10Interface, Ngsi9Interface, QoSBrokerIF {
	
	private final String CONFMAN_REG_URL = System.getProperty("confman.ip");
	
	private static final AtomicInteger serviceIdCounter = new AtomicInteger();
	private static final AtomicInteger thingIdCounter = new AtomicInteger();
	
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
		
		//TODO parse serviceRequest, check two equal attributes
		
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
		
		//create list of services
		List<Service> serviceList = createServiceList(serviceRequest.getAttributeList());
		
		//object to store the details of the service Request
		Request request = new Request(transactionID, opType, qosRequirements, restriction, serviceRequest.getEntityIdList(), serviceList);
		
		
		StatusCode statusCode = new StatusCode();
		
//		discovery phase
		
		//get list of equivalentThings
		HashMap<Integer, Thing> thingsMap = discoverThings(request, statusCode);
		
		if(thingsMap == null){
			
			ServiceAgreementResponse response = new ServiceAgreementResponse();
			
			response.setServiceID(null);
			
			response.setErrorCode(statusCode);
			
			return response;
		}
		
		//TODO filtering based on latency, MaxRespTime, MaxRateReq
		
		//TODO String offer = qosManager.getTemplate();
		//TODO prepare offer
		//TODO store Pair<TransactionID, <EquivalentThings, SeviceRequest>>
		
//		qosManager.createAgreement(offer);
		
		
		logger.info("############## createAgreement ###############");
		
		
		ServiceAgreementResponse response = new ServiceAgreementResponse();
		
		response.setServiceID("home_service");
		
		StatusCode statusCodeEx = new StatusCode(
				Code.OK_200.getCode(),
				ReasonPhrase.OK_200.toString(), "Succes Operation");
		
		response.setErrorCode(statusCode);
		
		return response;
	}

	/* function to create a list of requested services from the
	   attribute list taken from serviceRequestAgreement */
	private List<Service> createServiceList(List<String> attributeList) {

		List<Service> serviceList = new ArrayList<>();
		
		for(String attr: attributeList){
			
			Service service = new Service();
			
			service.setServId(serviceIdCounter.getAndIncrement());
			
			service.setService(attr);
			
			serviceList.add(service);
		}
		
		return serviceList;
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

	/* discover <ContextElement, ConteRegResp> given entityIdList, attributeList, restriction */
	private HashMap<Integer, Thing> discoverThings(Request request, StatusCode statusCode){
		
		List<String> attributeList = new ArrayList<>();
		List<Service> requestedServiceList = request.getRequestedServiceList();
		
		//get the list of attributes for the request to IoTDiscovery
		for(Service serv: requestedServiceList){
			
			attributeList.add(serv.getService());
		}
		
		//create a discovery request object to get ContextRegResp List
		DiscoverContextAvailabilityRequest discoveryRequest = new DiscoverContextAvailabilityRequest(
				request.getEntityIdList(), attributeList, request.getRestriction());
		
		logger.debug("DiscoverContextAvailabilityRequest:"
				+ discoveryRequest.toString());
		
		// Get the NGSI 9 DiscoverContextAvailabilityResponse 
		DiscoverContextAvailabilityResponse discoveryResponse = ngsi9Impl
				.discoverContextAvailability(discoveryRequest);
		
		if ((discoveryResponse.getErrorCode() == null || discoveryResponse
				.getErrorCode().getCode() == 200)
				&& discoveryResponse.getContextRegistrationResponse() != null) {
			
			List<ContextRegistrationResponse> ContextRegistrationResponseList = 
					discoveryResponse.getContextRegistrationResponse();
			
			//build request to QoSmonitor from List<ContextRegistrationResponse>
			QueryContextRequest queryRequest = setRequestToQoSMonitor(ContextRegistrationResponseList);
			
			//request to the QoSmonitor
			QueryContextResponse qosMonitorResponse = qosMonitor.queryContext(queryRequest);
			
			if(qosMonitorResponse == null){
				
				statusCode =  new StatusCode(
						Code.BADREQUEST_400.getCode(),
						ReasonPhrase.BADREQUEST_400.toString(), "No response from QoSMonitor");
				
				return null;
			}
			
			//create equivalentThing List and check if there is at least one resource for
			//each service requested
			HashMap<Integer, Thing> thingsMap = createThingsMap(attributeList, ContextRegistrationResponseList, qosMonitorResponse.getListContextElementResponse());
			
			if(thingsMap == null){
				
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

			return thingsMap;
		}
		else{
			
			//no elements found in the iotDiscovery
			statusCode = discoveryResponse.getErrorCode();
			return null;
		}
	}

	//filter discovery result so that every ContextRegistration
	//had only the attribute requested
//	private List<ContextRegistrationResponse> filterResults(
//			List<String> attributeList,
//			List<ContextRegistrationResponse> contextRegistrationResponse) {
//			
//		for(ContextRegistrationResponse crr: contextRegistrationResponse){
//			
//			List<ContextRegistrationAttribute> crAttrList = 
//					crr.getContextRegistration().getContextRegistrationAttribute();
//			
//			List<ContextRegistrationAttribute> crAttrListFiltered = new ArrayList<>();
//			
//			for(String attr: attributeList){
//				
//				for(ContextRegistrationAttribute crAttr: crAttrList){
//					
//					//Matching attr is stored in the new 
//					//ContextAttributeList
//					if(crAttr.getName().contentEquals(attr)){
//						crAttrListFiltered.add(crAttr);
//					}
//				}
//			}
//			
//			//ContextRegistrationResponse that doesn't have at least
//			//one attribute(taken from attributeList)
//			if(crAttrListFiltered.isEmpty()) return null;
//			
//			crr.getContextRegistration().setListContextRegistrationAttribute(crAttrListFiltered);
//		}
//		
//		return contextRegistrationResponse;
//	}

	/* function to create a map of things that can satisfy the requested services */
	private HashMap<Integer, Thing> createThingsMap(List<String> attributeList,
			List<ContextRegistrationResponse> contextRegistrationResponseList,
			List<ContextElementResponse> qosMonitorResponse) {
		
		HashMap<Integer, Thing> thingsMap = new HashMap<>();
		
		//map to check if it is available a service at least on one thing.
		//the key is the service name taken from the attributeList.
		//the value is set to true when it is found the thing that has that service
		//given by the key
		HashMap<String, Boolean> serviceAvailableAtLeastOnOneThing = new HashMap<>();
		
		for(String attr: attributeList){
			
			serviceAvailableAtLeastOnOneThing.put(attr, false);
		}
		
		//look for ContextRegistrationResponse that has attribute attr
		//ContRegResp is the Thing
		for(ContextRegistrationResponse crr: contextRegistrationResponseList){
			
			//look for ContextElementResponse associated to the ContextRegistrationResponse
			//ContElemResp is the associated value of the battery of the thing
			for(ContextElementResponse contElemResp: qosMonitorResponse){
				
				//check if the ContextElementResponse is
				//associated with the ContextRegistrationResponse crr
				if(matchEntityId(contElemResp, crr)){
					
					//TODO create thing-> discover list of services in the thing
					
					List<ContextRegistrationAttribute> crAttrList = 
							crr.getContextRegistration().getContextRegistrationAttribute();
					
					//given ContextRegistrationResponse crr look if it has
					//the attribute attr
					//the ContRegAttr is the thing service
					for(ContextRegistrationAttribute crAttr: crAttrList){
						
						//checking of the services offered by the thing
						//for each service offered create a thingService
						//the attr is the service requested
						for(String attr: attributeList){
							
							//the contextRegAttr give me the info about the latency
							//and the energyCost of the thingService
							if(crAttr.getName().contentEquals(attr)){
								
								//TODO create thingService for each available service in the thing
								
//								Thing t = new Thing();
//								
//								t.setId(contElemResp.getContextElement().getEntityId().getId());
//								
//								t.setServiceSpec(crAttr);
//								
//								List<ContextAttribute> contElemRespAttrList = contElemResp.getContextElement().getContextAttributeList();
//										
//								for(ContextAttribute cAttr: contElemRespAttrList){
//									
//									if(cAttr.getName().contentEquals("battery")){
//										t.setBatteryLevel(cAttr);
//									}
//								}
//								
//								//problem battery can't be null if It has been found
//								//a ContextElement associated with the ContextRegistrationResponse
//								if(t.getBatteryLevel() == null) return null;
//								
//								equivalentThingsList.add(t);
	
							}
						}
					}
				}
			}
			

		}
		
		return null;
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

	/* function to get mapping attr->EquivalentContextRegistrationRespList */
	private HashMap<String, ArrayList<ContextRegistrationResponse>> getMappingAttrEquivalentContRegResponseList(
			List<String> attributeList, List<ContextRegistrationResponse> contextRegistrationResponse) {

		HashMap<String, ArrayList<ContextRegistrationResponse>> mappingAttrEquivalentContRegResponseList = new HashMap<>();
		
		//itearte over the list of AttributeList
		for(String attr : attributeList){
			
			ArrayList<ContextRegistrationResponse> crRespList = new ArrayList<>();
			
			//iterate over the list of Context Registration Resp
			for(ContextRegistrationResponse crr : contextRegistrationResponse){
				
				//get the List of ContextRegistrationAttribute 
				//in the ContextRegistration element
				List<ContextRegistrationAttribute> crAttrList = crr.getContextRegistration().getContextRegistrationAttribute();
				
				//iterate over the list of Context Registration attr
				for(ContextRegistrationAttribute crAttr: crAttrList){
					
					//if the ContextRegistrationResponse crr has the attribute attr,
					//add crr to the contextRegistrationResponseList crRespList
					if(crAttr.getName().contentEquals(attr)){
						
						crRespList.add(crr);
						break;
					}
				}
			}
			
			//if an attribute attr has a contextRegistrationResponseList crRespList
			//empty, the service cannot be establish because for one
			//attribute attr, There aren't resourses (ContextRegistrationResp Entities)
			if(crRespList.isEmpty()) return null;
			else
				mappingAttrEquivalentContRegResponseList.put(attr, crRespList);
		}
			
		return mappingAttrEquivalentContRegResponseList;
	}

	public void setNgsi10Requester(Ngsi10Requester ngsi10Requester) {
		this.ngsi10Requester = ngsi10Requester;
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

	public Ngsi10Interface getQosMonitor() {
		return qosMonitor;
	}

	public void setQosMonitor(Ngsi10Interface qosMonitor) {
		this.qosMonitor = qosMonitor;
	}



}
