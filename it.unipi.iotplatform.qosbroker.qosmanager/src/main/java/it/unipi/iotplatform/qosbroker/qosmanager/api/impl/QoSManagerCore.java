package it.unipi.iotplatform.qosbroker.qosmanager.api.impl;

import it.unipi.iotplatform.qosbroker.qosmanager.api.QoSManagerIF;
import it.unipi.iotplatform.qosbroker.qosmanager.api.datamodel.ServiceAgreementRequest;
import it.unipi.iotplatform.qosbroker.qosmanager.api.datamodel.ServiceAgreementResponse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import eu.neclab.iotplatform.ngsi.api.datamodel.DiscoverContextAvailabilityRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.DiscoverContextAvailabilityResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.NotifyContextAvailabilityRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.NotifyContextAvailabilityResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.NotifyContextRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.NotifyContextResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.QueryContextRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.QueryContextResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.RegisterContextRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.RegisterContextResponse;
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

public class QoSManagerCore implements Ngsi10Interface, Ngsi9Interface, QoSManagerIF {
	
	private final String CONFMAN_REG_URL = System.getProperty("confman.ip");

	/** Executor for asynchronous tasks */
	private final ExecutorService taskExecutor = Executors
			.newCachedThreadPool();
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(QoSManagerCore.class);

	/** The implementation of the NGSI 9 interface */
	private Ngsi9Interface ngsi9Impl;

	/** Used to make NGSI 10 requests. */
	private Ngsi10Requester ngsi10Requester;

	/**
	 * Returns the implementation of the NGSI 9 interface. This interface is
	 * used by the core for making NGSI-9 discovery operations.
	 *
	 * @return The NGSI 9 interface.
	 */
	public Ngsi9Interface getNgsi9Impl() {
		return ngsi9Impl;
	}

	/**
	 * Sets the implementation of the NGSI 9 interface. This interface is used
	 * by the core for making NGSI-9 discovery operations.
	 *
	 * @param ngsi9
	 *            The NGSI 9 interface.
	 */
	public void setNgsi9Impl(Ngsi9Interface ngsi9) {
		ngsi9Impl = ngsi9;
	}

	/**
	 * Returns the ngsi10 requester. This object is used for making NGSI-10
	 * requests to arbitrary URLs.
	 *
	 * @return the ngsi10 requester.
	 */
	public Ngsi10Requester getNgsi10Requester() {

		return ngsi10Requester;
	}

	/**
	 * Sets the ngsi10 requester. This object is used for making NGSI-10
	 * requests to arbitrary URLs.
	 *
	 * @param ngsi10Requester
	 *            The new ngsi10 requester.
	 */
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
//			// List of Task
//			List<Callable<Object>> tasks = new ArrayList<Callable<Object>>();
//
//			// Countdown of Task
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
//			// Call the Merge Method
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
//		// Going through individual ContextElement
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
//			// Creating Restriction OperationScopes for
//			// DiscoverContextAvailabilityRequest
//			OperationScope os = new OperationScope("IncludeAssociations",
//					"TARGETS");
//			List<OperationScope> loperOperationScopes = new LinkedList<OperationScope>();
//			loperOperationScopes.add(os);
//			Restriction restriction = new Restriction("", loperOperationScopes);
//
//			// Create the NGSI 9 DiscoverContextAvailabilityRequest
//			DiscoverContextAvailabilityRequest discoveryRequest = new DiscoverContextAvailabilityRequest(
//					eidList, attributeList, restriction);
//			// Get the NGSI 9 DiscoverContextAvailabilityResponse
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
//		// if (bigDataRepository != null) {
//		//
//		// new Thread() {
//		//
//		// @Override
//		// public void run() {
//		//
//		// bigDataRepository.storeData(lContextElements);
//		//
//		// }
//		// }.start();
//		//
//		// }
//		//
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
	public String getTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceAgreementResponse createAgreement(ServiceAgreementRequest offer) {
		// TODO Auto-generated method stub
		return null;
	}
}
