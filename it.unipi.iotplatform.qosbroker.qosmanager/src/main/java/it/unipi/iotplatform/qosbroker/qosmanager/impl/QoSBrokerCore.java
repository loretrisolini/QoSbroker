package it.unipi.iotplatform.qosbroker.qosmanager.impl;

import it.unipi.iotplatform.qosbroker.api.datamodel.AllocationInfo;
import it.unipi.iotplatform.qosbroker.api.datamodel.AllocationPolicy;
import it.unipi.iotplatform.qosbroker.api.datamodel.LocationScopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSCode;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSConsts;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSReasonPhrase;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSscopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.Reserveobj;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceAgreementRequest;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceAgreementResponse;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceDefinition;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.api.utils.Statistics;
import it.unipi.iotplatform.qosbroker.qosmanager.api.QoSBrokerIF;
import it.unipi.iotplatform.qosbroker.qosmanager.api.QoSManagerIF;
import it.unipi.iotplatform.qosbroker.qosmonitor.api.QoSMonitorIF;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Node;

import eu.neclab.iotplatform.iotbroker.commons.EntityIDMatcher;
import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.iotbroker.core.QueryResponseMerger;
import eu.neclab.iotplatform.iotbroker.core.RequestThread;
import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Code;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextAttribute;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElementResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistration;
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
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;
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
import eu.neclab.iotplatform.ngsi.association.datamodel.AssociationDS;

public class QoSBrokerCore implements Ngsi10Interface, Ngsi9Interface, QoSBrokerIF {
	
	private final String CONFMAN_REG_URL = System.getProperty("confman.ip");
	
	private final Statistics stat = new Statistics();

	/** String representing xml content type. */
	private final String CONTENT_TYPE_XML = "application/xml";
	
	/** Executor for asynchronous tasks */
	private final ExecutorService taskExecutor = Executors
			.newCachedThreadPool();
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(QoSBrokerCore.class);
	
	private QoSManagerIF qosManager;
	private QoSMonitorIF qosMonitor;
	private Ngsi10Interface qosMonitorNgsi;

	
	/** The implementation of the NGSI 9 interface */
	@Autowired
	private Ngsi9Interface ngsi9Impl;

	/** Used to make NGSI 10 requests. */
	private Ngsi10Requester ngsi10Requester;

//	/** Used to make NGSI 10 requests. */
//	private Ngsi10Requester ngsi10IoTBrokerCore;
	
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

		logger.info("QoSBrokerCore -- queryContext() dispatching phase for SLA established");
		
		//read entityId with type "QoSservice"
		//so get transactionsId
		List<EntityId> entityIdList = request.getEntityIdList();
		StatusCode statusCode;
		
		QueryContextResponse queryResponse = new QueryContextResponse();
		
		logger.info("QoSBrokerCore -- queryContext() lookup for transaction Id in the request");
		String transId = entityIdList.get(0)==null ? null : entityIdList.get(0).getId();

		//no transactionId
		if(transId == null){

			statusCode = new StatusCode(
					Code.BADREQUEST_400.getCode(),
					ReasonPhrase.BADREQUEST_400.toString(), "No transactionId found in the request!");

			queryResponse.setErrorCode(statusCode);

			return queryResponse;
		}
		
		logger.info("QoSBrokerCore -- queryContext() read Allocation Schemas");
		Reserveobj reservationResults = qosManager.getReservationResults();
		
		if(reservationResults == null){
			
			statusCode = new StatusCode(
					Code.INTERNALERROR_500.getCode(),
					ReasonPhrase.RECEIVERINTERNALERROR_500.toString(), "QoSBrokerCore -- queryContext() Error reading allocation schemas or there is no"
							+ "	allocation schema set");

			queryResponse.setErrorCode(statusCode);

			return queryResponse;
		}

		//Allocation Policy
		AllocationPolicy allocPolicy = reservationResults.getAllocPolicy();
		
		if(allocPolicy == null){
			statusCode = new StatusCode(
					Code.INTERNALERROR_500.getCode(),
					ReasonPhrase.RECEIVERINTERNALERROR_500.toString(), "QoSBrokerCore -- queryContext() no allocation Policy founded");

			queryResponse.setErrorCode(statusCode);

			return queryResponse;
		}
		
		//Map<reqServName, List<w_ij, devId>>
		HashMap<String, AllocationInfo> allocationResult = 
				reservationResults.getAllocationSchema().get(transId);
		
		//Map<transId, operationType>
		String opType = reservationResults.getTransId_opType().get(transId);
		
		//allocationSchema not found
		if(allocationResult == null){
			
			statusCode = new StatusCode(
					QoSCode.SERVICEALLOCATIONNOTFOUND_405.getCode(),
					QoSReasonPhrase.SERVICEALLOCATIONNOTFOUND_405.toString(), "QoSBrokerCore -- queryContext() allocation schema not found");

			queryResponse.setErrorCode(statusCode);

			return queryResponse;
		}
		
		//check on operationType of the SLA
		if(opType == null || !opType.contentEquals("queryContext")){
			statusCode = new StatusCode(
					Code.BADREQUEST_400.getCode(),
					ReasonPhrase.BADREQUEST_400.toString(), "QoSBrokerCore -- queryContext() Error Allocation opType doesn't match");

			queryResponse.setErrorCode(statusCode);

			return queryResponse;
		}
		
		//list of pairs <thing(id), serviceAllocated>
		HashMap<String, List<String>> allocationPairs = new HashMap<>();
		

		List<EntityId> entityIdAllocList = new ArrayList<>();
		List<String> attributeAllocList = new ArrayList<>();
		
		//build the list of entityId and attribute to query the IoTDiscovery
		for(Map.Entry<String, AllocationInfo> entryAlloc: allocationResult.entrySet()){

			
			//get the serviceName
			String service = entryAlloc.getKey();
			attributeAllocList.add(service);
			
			//given the list of id of the things allocated to a service
			//take one thingId based on the allocationPolicy
			List<String> devIdList = entryAlloc.getValue().getDeviceIdList();
			String devId = qosManager.computeNextDevId(transId, service, allocPolicy);
			
			if(devId == null || !devIdList.contains(devId)){
				statusCode = new StatusCode(
						Code.INTERNALERROR_500.getCode(),
						ReasonPhrase.RECEIVERINTERNALERROR_500.toString(), "QoSBrokerCore -- queryContext() Error Policy compute next DevId");

				queryResponse.setErrorCode(statusCode);

				return queryResponse;
			}
			
			if(allocationPairs.get(devId)==null){
				allocationPairs.put(devId, new ArrayList<String>());
				allocationPairs.get(devId).add(service);
			}
			else{
				allocationPairs.get(devId).add(service);
			}
			
			EntityId entId = new EntityId();
			entId.setIsPattern(false);
			entId.setType(URI.create(""));
			entId.setId(devId);
			
			if(!entityIdAllocList.contains(entId))
				entityIdAllocList.add(entId);
		}
		
		//create the QueryContextRequest
		//with the entityIds of the Allocation
		//and the attributeList of the Allocation
		QueryContextRequest queryRequestAllocation = new QueryContextRequest();
		queryRequestAllocation.setEntityIdList(entityIdAllocList);
		queryRequestAllocation.setAttributeList(attributeAllocList);
		
		Restriction restriction = new Restriction();
		restriction.setAttributeExpression("");
		
		//discovery Request to find contReg 
		DiscoverContextAvailabilityRequest discoveryRequest = new DiscoverContextAvailabilityRequest(
				entityIdAllocList, attributeAllocList, restriction);
		
		logger.info("DiscoverContextAvailabilityRequest: "
				+ discoveryRequest.toString());

		/* Get the NGSI 9 DiscoverContextAvailabilityResponse */
		DiscoverContextAvailabilityResponse discoveryResponse = ngsi9Impl
				.discoverContextAvailability(discoveryRequest);

		if ((discoveryResponse.getErrorCode() == null || discoveryResponse
				.getErrorCode().getCode() == 200)
				&& discoveryResponse.getContextRegistrationResponse() != null) {

			logger.info("Receive discoveryResponse from Config Man:"
					+ discoveryResponse);
			
			//get create list of QueryContextRequest, URI
			List<Pair<QueryContextRequest, URI>> queryList = createQueryRequestList(
					discoveryResponse, allocationPairs);
			
			if(queryList == null){
				return null;
			}
			
			logger.info("Query List Size: " + queryList.size());


			QueryResponseMerger merger = new QueryResponseMerger(queryRequestAllocation);

			// List of Task
			List<Callable<Object>> tasks = new ArrayList<Callable<Object>>();

			// Countdown of Task
			CountDownLatch count = new CountDownLatch(queryList.size());

			for (int i = 0; i < queryList.size(); i++) {
				logger.info("Starting Thread number: " + i);

				logger.info("info1:"
						+ queryList.get(i).getLeft().getEntityIdList().get(0)
						.getId());
				logger.info("info2:"
						+ queryList.get(i).getRight());

				tasks.add(Executors.callable(new RequestThread(null,
						ngsi10Requester, queryList.get(i).getLeft(), queryList
						.get(i).getRight(), merger, count,
						new ArrayList<AssociationDS>())));

			}

			try {

				long t0 = System.currentTimeMillis();
				taskExecutor.invokeAll(tasks);
				long t1 = System.currentTimeMillis();
				logger.info("Finished all tasks in " + (t1 - t0) + " ms");

			} catch (InterruptedException e) {
				logger.info("Thread Error", e);
			}

			// Call the Merge Method
			QueryContextResponse threadResponse = merger.get();

			logger.info("Response after merging: " + threadResponse);
		
		
			System.out.println();
			System.out.println("####################################");
			System.out.println("Response From sensors: ");
			System.out.println(threadResponse.getListContextElementResponse());
			System.out.println("####################################");
			System.out.println();
			
			return threadResponse;
		}
		else{
			QueryContextResponse response = new QueryContextResponse(null,
					discoveryResponse.getErrorCode());

			return response;
		}

		
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

		return qosMonitorNgsi.updateContext(request);

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
	public synchronized ServiceAgreementResponse createAgreement(ServiceAgreementRequest offer){
		
		StatusCode statusCode;
		ServiceAgreementResponse response;
		
		//transactionID to identify the service request
		String transactionId = "QoS_"+UUID.randomUUID().toString();
		
//		parse the request to create discovery request
		
		//always only one serviceRequest in our implementation
		//TODO manage serviceAgreementRequest as List of serviceDefinition
		ServiceDefinition serviceRequest = offer.getServiceDefinitionList().get(0);
		
		stat.printServiceAgreementReq(serviceRequest);
		
		String opType = serviceRequest.getOperationType();
		
		logger.info("QoSBrokerCore -- createAgreement() getRestriction");
		
		//Create a new restriction with the same attribute expression and
		//operation scope as in the request.
		Restriction restriction = getRestriction(serviceRequest);
		
		if(restriction == null){
			statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), 
					"QoSBrokerCore -- createAgreement() No restrictions in ServiceAgreementRequest");
			
			response = new ServiceAgreementResponse();
			
			response.setServiceID(null);
			
			response.setErrorCode(statusCode);
			
			return response;
		}

//		CREATE THE REQUEST FROM THE INFO TAKEN FROM SERVICE_AGREEMENT_REQUEST
		
		//create List<requiredServicesName> of services required in service Agreement request
		//LinkedHashSer is used to avoid the equal string in requiredServicesName
		//so thre are no equal serviceName Temp, Temp is equal to Temp
		List<String> requiredServicesName = new ArrayList<>(new LinkedHashSet<String>(serviceRequest.getAttributeList()));
		
		logger.info("QoSBrokerCore -- createAgreement() setRequest");
		//object to store the details of the service Request
		Request request = setRequest(opType, requiredServicesName, restriction.getOperationScope());
		if(request == null){
			
			statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), 
					"QoSBrokerCore -- createAgreement() error in creation of the request object -- no QoSRequirements founded");
			
			response = new ServiceAgreementResponse();
			
			response.setServiceID(null);
			
			response.setErrorCode(statusCode);
			
			return response;
		}
		
//		DISCOVERY PHASE
		
		logger.info("QoSBrokerCore -- createAgreement() discoveryThings");
		//get list of equivalentThings
		statusCode = discoverThings(serviceRequest.getEntityIdList(), request, restriction, transactionId);
		
		if(statusCode.getCode() != QoSCode.OK_200.getCode()){
			
			response = new ServiceAgreementResponse();
			
			response.setServiceID(null);
			
			response.setErrorCode(statusCode);
			
			return response;
		}		
		
		logger.info("QoSBrokerCore -- createAgreement() " + request.toString());
		
		String negotiationOffer = qosManager.getTemplate();
		//TODO set values in the template


		statusCode = qosManager.createAgreement(negotiationOffer, transactionId, request/*, thingTransactionsMap*/);

			
		if(statusCode.getCode() != QoSCode.OK_200.getCode()){
			response = new ServiceAgreementResponse();
			
			response.setServiceID("NO SERVICE ID");
			
			response.setErrorCode(statusCode);
		}
		else{
			response = new ServiceAgreementResponse();
			
			response.setServiceID(transactionId);
			
			response.setErrorCode(statusCode);
		}
		
		return response;
	}

	/* function that create a Request object given opType, list of required services
	 * and the list of operations scope in Restriction object */
	private Request setRequest(String opType,
			List<String> requiredServicesName,
			List<OperationScope> operationScope) {
		
		Request request = new Request();
		
		//if there is no qosRequirements on the request
		//there is an error
		boolean qosReqFound = false;
		
		QoSscopeValue qosScopeValue = new QoSscopeValue();
		LocationScopeValue<Point> locationScopeValuePoint = new LocationScopeValue<Point>();
		LocationScopeValue<Circle> locationScopeValueCircle = new LocationScopeValue<Circle>();
		
		for(OperationScope opScope : operationScope){
			
			if(opScope.getScopeType().contentEquals(QoSConsts.QOS)){

				qosScopeValue = QoSscopeValue.convertObjectToJaxbObject((Node)opScope.getScopeValue(), qosScopeValue, QoSscopeValue.class);
				
				if(qosScopeValue.getMaxResponseTime()!=null && qosScopeValue.getMaxRateRequest()!=null){
					//check correct conversion of the object
					qosReqFound = true;
				}

				
			}
			if(opScope.getScopeType().contentEquals(QoSConsts.LOCATION_POINT)){
				
				locationScopeValuePoint = LocationScopeValue.convertObjectToJaxbObject((Node)opScope.getScopeValue(), new LocationScopeValue<Point>(), LocationScopeValue.class);
				request.setLocationRequirementPoint(locationScopeValuePoint);
			}
			if(opScope.getScopeType().contentEquals(QoSConsts.LOCATION_CIRCLE)){
				locationScopeValueCircle = LocationScopeValue.convertObjectToJaxbObject((Node)opScope.getScopeValue(), locationScopeValueCircle, LocationScopeValue.class);
				request.setLocationRequirementCircle(locationScopeValueCircle);
			}
		}

		if(!qosReqFound || qosScopeValue == null){
			return null;
		}
		
		request.setOpType(opType);
		request.setRequiredServicesNameList(requiredServicesName);
		request.setQosRequirements(qosScopeValue);

		return request;
		
	}

	/* discover a list of equivalent things given the list of entityId objects
	 * the list of required services and the restrictions */
	private StatusCode discoverThings(List<EntityId> entityIdList,
			Request request, Restriction restriction, String transId) {
		
		StatusCode statusCode;
		
		//create a discovery request object to get ContextRegResp List (list of things)
		DiscoverContextAvailabilityRequest discoveryRequest = new DiscoverContextAvailabilityRequest(
				entityIdList, request.getRequiredServicesNameList(), restriction);
		
		logger.info("QoSBrokerCore -- discoverThings() DiscoverContextAvailabilityRequest:"
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
			QueryContextResponse qosMonitorResponse = qosMonitorNgsi.queryContext(queryRequest);
			
			if(qosMonitorResponse.getErrorCode() != null &&
					qosMonitorResponse.getErrorCode().getCode() != QoSCode.OK_200.getCode()){
				
				statusCode = qosMonitorResponse.getErrorCode();
				
				statusCode.setDetails("QoSBrokerCore -- discoverThings() "+statusCode.getDetails());
				return statusCode;
			}
			
			//from the pairs List<ContexReg> and List<ContElem> (stored in ThingInfoContainer as <ContReg,ContElem>)
			//create Map<DevId, Thing> and Map<reqServName, List<DevId>> and update ThingsInfoDB and ServiceThingsDB
			//using QoSMonitor
			//and check if for every reqService there is at least a Thing available.
			//if for a contReg there isn't an associated contElem create an entry <DevId, Thing> 
			//but the Thing has batt=0.
			//if for a required serviceName there is only one thing but that thing has battery
			//equal to zero the service cannot be considered satisfied.
			//filter thing based on MaxRespTime in request object
			statusCode = 
					createThingsMap(ContextRegistrationResponseList, 
									qosMonitorResponse.getListContextElementResponse(), request, transId);
			
			//the conditions for execution of the service
			//allocation algorithm are not achieved
			if(statusCode.getCode() != QoSCode.OK_200.getCode()){
				
				return statusCode;
			}
			
			if(discoveryResponse.getErrorCode() != null){
				statusCode = discoveryResponse.getErrorCode();
				statusCode.setDetails("QoSBrokerCore -- discoverThings() "+statusCode.getDetails());
			}
			else{
				statusCode =  new StatusCode(
						Code.OK_200.getCode(),
						ReasonPhrase.OK_200.toString(), "QoSBrokerCore -- discoverThings() OK");
			}
			
			statusCode.setDetails("QoSBrokerCore -- discoverThings() "+statusCode.getDetails());
			return statusCode;
		}
		else{

			//no elements found in the iotDiscovery
			statusCode = discoveryResponse.getErrorCode();
			statusCode.setDetails("QoSBrokerCore -- discoverThings() "+statusCode.getDetails());
			return statusCode;
		}
	}

	/* function that taken the pairs List<ContexReg> and List<ContElem> (stored in ThingInfoContainer as <ContReg,ContElem>)
	create Map<DevId, Thing> and Map<reqServName, List<DevId>> and update ThingsInfoDB and ServiceThingsDB
	using QoSManager and check if for every reqService there is at least a Thing available.
	if for a contReg there isn't an associated contElem create an entry <DevId, Thing> 
	but the Thing has batt=0.
	if for a required serviceName there is only one thing but that thing has battery
	equal to zero the service cannot be considered satisfied.
	filter thing based on MaxRespTime in request object */
	private StatusCode createThingsMap(
			List<ContextRegistrationResponse> contextRegistrationResponseList,
			List<ContextElementResponse> qosMonitorResponse, Request request, String transId) {
		
		stat.printNgsiResults(contextRegistrationResponseList, qosMonitorResponse);
		
		StatusCode statusCode;
		
		//Map<reqServName, List<DevID>>
		HashMap<String, ThingsIdList> serviceEquivalentThings = new HashMap<>();
		
		//set the key serviceEquivalentThings Map 
		//taken from the requiredServicesNameList
		//in Request object
		for(String reqServName: request.getRequiredServicesNameList()){
			serviceEquivalentThings.put(reqServName, new ThingsIdList());
		}
		
		//creation of the map<DevId, Thing> from the List<ContextRegistration>
		//and the List<ContextElement> used to fill the info about a Thing
		//the ContextRegistration represents the Thing with c_ij & t_ij
		//and the ContextElement represents the batteryValue of a Thing
		//If c_ij or t_ij are not found, they are set to null.
		//The same is done for the battery
		HashMap<String, Thing> thingsInfo = new HashMap<>();
		
		for(ContextRegistrationResponse crr: contextRegistrationResponseList){

			List<ContextRegistrationAttribute> contRegAttrsList = crr.getContextRegistration().getContextRegistrationAttribute();
			
			String devId = null;
			List<ContextAttribute> contAttrsList = null;
			
			boolean matchFound = false;
			
			//check if the qosMonitorResponse is empty so List<ContextElement>
			//is empty
			if(!qosMonitorResponse.isEmpty()){
				for(ContextElementResponse contElemResp: qosMonitorResponse){
					
					//check if in the list of entityId of the crr
					//there is an entityId that match with
					//the entityId of contElemResp
					if(matchEntityId(contElemResp, crr)){
						
						matchFound = true;
						
						//id of the entityId structure of ContElem that match
						//with the one of ConteReg
						devId = contElemResp.getContextElement().getEntityId().getId();
	
						contAttrsList = contElemResp.getContextElement().getContextAttributeList();
						break;
					}
				}
			}
	
			//check if no match has found, in that case give
			//as devId the first id in the first entityIdList
			//in the contextRegistration and null as contextAttrsList
			//the second case is an empty qosMonitorResponse
			if(qosMonitorResponse.isEmpty() || !matchFound){
				
				//usually the case of multiple entityId for contextRegistration
				//doesn't happen because there is one registrationContext
				//for each sensor using only one entityId to identify
				//a single sensor
				devId = crr.getContextRegistration().getListEntityId().get(0).getId();
				
				//there is no value for the battery
				contAttrsList = null;
			}
				
			//All the list passed in the getThing function
			//are used to create a Thing object
			//The assumption is that all attributes names in the lists are unique
			//that is every ContextRegistrationAttribute_Name is unique
			//the same assumption is true for ContextAttribute_Name
			Thing t = 
					Thing.getThing(contRegAttrsList, contAttrsList);
			
			if(devId != null){
				thingsInfo.put(devId, t);
				
				//update serviceEquivalentThings Map
				//using the serviceName on the created thing
				for(String reqServName: serviceEquivalentThings.keySet()){
					
					//check about required service on the thing
					if(t.getServicesList().get(reqServName) != null){
						serviceEquivalentThings.get(reqServName).addEqThing(devId);
					}
				}
			}
			else{
				logger.info("createThingsMap() ERROR: devId is not set, Thing can be stored");
			}
			
		}
		
		//update ThingsInfoDB and ServiceEquivalentThingsDB
		//with the new Map<DevId, Thing> and Map<reqServName, List<DevId>>
		
		//if the update operation of ThingsInfoDB and ServEqThingsDB
		//is not correct, it is useless continue with
		//allocation procedure
		logger.info("QoSBrokerCore -- createThingsMap() updateThingsServicesInfo");
		statusCode = qosMonitor.updateThingsServicesInfo(thingsInfo, serviceEquivalentThings); 
		
		if(statusCode.getCode() != QoSCode.OK_200.getCode()){
			statusCode.setDetails("createThingsMap() "+statusCode.getDetails());
			return statusCode;
		}
		
		stat.printThingsMappings(request, thingsInfo, serviceEquivalentThings);
		
		logger.info("QoSBrokerCore -- createThingsMap() checkServiceAllocationConditions");
		//check the condition for the serviceAgreementRequest
		//at least one thing for required service
		//if only one thing for a service the Thing
		//battery must be != null
		statusCode  =
					checkServiceAllocationConditions(serviceEquivalentThings, thingsInfo, request, transId);
		
		if(statusCode.getCode() != QoSCode.OK_200.getCode()){
			statusCode.setDetails("createThingsMap() "+statusCode.getDetails());
			return statusCode;
		}
		
		statusCode = new StatusCode(QoSCode.OK_200.getCode(), QoSReasonPhrase.OK_200.name(), "createThingsMap() OK");
		
		return statusCode;
	}

	

	/* check the condition for the serviceAgreementRequest
	at least one thing for required service
	if only one thing for a service the Thing
	battery must be != null */
	private StatusCode checkServiceAllocationConditions(
			HashMap<String, ThingsIdList> serviceEquivalentThings,
			HashMap<String, Thing> thingsInfo, Request request, String transId) {

		StatusCode statusCode;
		
		//Map<devId, transId> represents the devId that respect
		//the requirements of the transaction with transId
//		thingTransactionsMap = new HashMap<>();
		
		for(Map.Entry<String, ThingsIdList> entry: serviceEquivalentThings.entrySet()){
			
			List<String> eqThings = entry.getValue().getEqThings();
			
			logger.info("QoSBrokerCore -- checkServiceAllocationConditions() check at least one thing for the required service");
			if(eqThings.isEmpty()){
				
				statusCode = new StatusCode(QoSCode.SERVICEALLOCATIONFAILED_502.getCode(), QoSReasonPhrase.SERVICEALLOCATIONFAILED_502.name(), 
												"checkServiceAllocationConditions() service "+entry.getKey()+" has no things associated");
				
				return statusCode;
			}
			
			logger.info("QoSBrokerCore -- checkServiceAllocationConditions() if there is one thing for the required service, the battery mist be set");
			if(eqThings.size() == 1){
				
				String eqThingDevId = eqThings.get(0);
				
				if(thingsInfo.get(eqThingDevId).getBatteryLevel()==null){
					statusCode = new StatusCode(QoSCode.SERVICEALLOCATIONFAILED_502.getCode(), QoSReasonPhrase.SERVICEALLOCATIONFAILED_502.name(), 
							"checkServiceAllocationConditions() service "+entry.getKey()+" equivalent thing "+eqThingDevId+" has no battery value");
					
					return statusCode;
				}
			}
			
			logger.info("QoSBrokerCore -- checkServiceAllocationConditions() check QoS requirements");
			Double maxRespTime = request.getQosRequirements().getMaxResponseTime();
			String reqServName = entry.getKey();
			
			//var to check if filtering the eqThings
			//based on latency and accuracy there is at least one
			//thing for that requiredService
			Boolean constraints = false;
			
			for(String eqThingDevId: eqThings){
				
				Double latency = thingsInfo.get(eqThingDevId).getServicesList().get(reqServName).getLatency();
				
				//if latency or servAccuracy are not null
				//they must respect the constraints
				if(maxRespTime!=null && latency != null && latency <= maxRespTime || maxRespTime==null){
					constraints = true;
					
					break;
				}

			}
			
			//filtering a list of eqThing
			//this service has no things
			//so it is useless continue in the allocation
			//process
			if(!constraints){
				statusCode = new StatusCode(QoSCode.SERVICEALLOCATIONFAILED_502.getCode(), QoSReasonPhrase.SERVICEALLOCATIONFAILED_502.name(), 
						"checkServiceAllocationConditions() transId "+transId+" service "+entry.getKey()+
						" has no equivalent things that respect requirements maxRespTime and/or accuracy");
				
				return statusCode;
			}

		}
		
		statusCode = new StatusCode(QoSCode.OK_200.getCode(), QoSReasonPhrase.OK_200.name(), "checkServiceAllocationConditions() OK");
		
		return statusCode;
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
	
	public void setNgsi10Requester(Ngsi10Requester ngsi10Requester) {
		this.ngsi10Requester = ngsi10Requester;
	}

	public QoSMonitorIF getQosMonitor() {
		return qosMonitor;
	}

	public void setQosMonitor(QoSMonitorIF qosMonitor) {
		this.qosMonitor = qosMonitor;
	}

	public Ngsi10Interface getQosMonitorNgsi() {
		return qosMonitorNgsi;
	}

	public void setQosMonitorNgsi(Ngsi10Interface qosMonitorNgsi) {
		this.qosMonitorNgsi = qosMonitorNgsi;
	}

	public QoSManagerIF getQosManager() {
		return qosManager;
	}

	public void setQosManager(QoSManagerIF qosManager) {
		this.qosManager = qosManager;
	}

	/**
	 * This function creates a list of which query context request should be
	 * sent to which address, based on a discovery response (containing the
	 * available data sources) and a query context request (defining which data
	 * should be retrieved).
	 *
	 * @param discoveryResponse
	 *            The discovery response specifying the available data sources
	 * @param request
	 *            The query context request defining which data is to be
	 *            obtained.
	 * @return A list of (QueryContextRequest,URL) pairs specifying where to
	 *         make which query.
	 */
	private List<Pair<QueryContextRequest, URI>> createQueryRequestList(
			DiscoverContextAvailabilityResponse discoveryResponse,
			HashMap<String, List<String>> allocationPairs) {

		List<Pair<QueryContextRequest, URI>> output = new ArrayList<Pair<QueryContextRequest, URI>>();

		List<ContextRegistrationResponse> contRegRespList = discoveryResponse.getContextRegistrationResponse();
		
		for (int i = 0; i < contRegRespList.size(); i++) {

			//take the contReg 
			ContextRegistration contReg = contRegRespList.get(i).getContextRegistration();
			
			// (1) get the access URI
			URI uri = contRegRespList.get(i).getContextRegistration().getProvidingApplication();

			if(uri == null){
				return null;
			}
			
			QueryContextRequest query = new QueryContextRequest();
			
			// check if EntityId is != null
			if (contReg.getListEntityId() != null) {

				List<EntityId> entityIdList = contReg.getListEntityId(); 
				
				String id = null;
				
				for(EntityId entId: entityIdList){
					
					//entityId unique
					if(allocationPairs.get(entId.getId())!=null){
						
						id = entId.getId();
					
						break;
					}
				}
				
				if(id == null){
					return null;
				}

				
				boolean attributeFound = false;
				
				// check if different to null
				if (contReg.getContextRegistrationAttribute() != null) {

					// run over all attributes
					for (int j = 0; j < contReg.getContextRegistrationAttribute().size(); j++) {

						String attributeName = contReg.getContextRegistrationAttribute().get(j)
								.getName();
						
						if(allocationPairs.get(id).contains(attributeName)){
							attributeFound = true;

						}
					}
					
				}
				else{
					return null;
				}
				
				if(attributeFound){
					List<EntityId> entityIdListRequest = new ArrayList<>();
					List<String> attributeListRequest = new ArrayList<>();
					
					EntityId entId = new EntityId();
					entId.setId(id);
					entId.setIsPattern(false);
					entId.setType(URI.create(""));
					
					entityIdListRequest.add(entId);
					
					attributeListRequest = allocationPairs.get(id);
					
					query.setEntityIdList(entityIdListRequest);
					query.setAttributeList(attributeListRequest);
					query.setRestriction(null);
					
					output.add(new Pair<QueryContextRequest, URI>(query,
							uri));
				}

			} else {

				return null;
			}

		}
		return output;
	}

	
}
