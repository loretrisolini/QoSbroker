package it.unipi.iotplatform.qosbroker.qosmanager.impl;

import it.unipi.iotplatform.qosbroker.api.datamodel.EquivalentThings;
import it.unipi.iotplatform.qosbroker.api.datamodel.LocationScopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSConsts;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSscopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceAgreementRequest;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceAgreementResponse;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceDefinition;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceEquivalentThingsMapping;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsInfo;
import it.unipi.iotplatform.qosbroker.qosmanager.api.QoSBrokerIF;
import it.unipi.iotplatform.qosbroker.qosmonitor.api.QoSMonitorIF;

import java.util.ArrayList;
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
import org.w3c.dom.Node;

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
	
	private QoSMonitorIF qosMonitor;
	private Ngsi10Interface qosMonitorNgsi;
	
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
	public ServiceAgreementResponse createAgreement(ServiceAgreementRequest offer) throws Exception{

		//transactionID to identify the service request
		String transactionId = UUID.randomUUID().toString();
		
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

//		CREATE THE REQUEST FROM THE INFO TAKEN FROM SERVICE_AGREEMENT_REQUEST
		
		//create List<requiredServicesName> of services required in service Agreement request
		//LinkedHashSer is used to avoid the equal string in requiredServicesName
		//so thre are no equal serviceName Temp, Temp is equal to Temp
		List<String> requiredServicesName = new ArrayList<>(new LinkedHashSet<String>(serviceRequest.getAttributeList()));
		
		
		//object to store the details of the service Request
		Request request = setRequest(opType, requiredServicesName, restriction.getOperationScope());
		if(request == null){
			throw new Exception("error in creation of the request object");
		}
		
//		DISCOVERY PHASE
		
		//get list of equivalentThings
		StatusCode statusCode = discoverThings(serviceRequest.getEntityIdList(), request, restriction);
		
		if(statusCode.getCode() == Code.CONTEXTELEMENTNOTFOUND_404.getCode() ||
				statusCode.getCode() == Code.BADREQUEST_400.getCode()){
			
			ServiceAgreementResponse response = new ServiceAgreementResponse();
			
			response.setServiceID(null);
			
			response.setErrorCode(statusCode);
			
			return response;
		}		
		
		logger.debug(request.toString());
		
		String negotiationOffer = qosManager.getTemplate();
		//TODO set values in the template
		
		qosManager.createAgreement(negotiationOffer, transactionId, request);
		
		
		logger.info("############## createAgreement ###############");
		
		
		ServiceAgreementResponse response = new ServiceAgreementResponse();
		
		response.setServiceID("home_service");
		
		StatusCode statusCodeEx = new StatusCode(
				Code.OK_200.getCode(),
				ReasonPhrase.OK_200.toString(), "Succes Operation");
		
		response.setErrorCode(statusCode);
		
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
		LocationScopeValue locationScopeValue = new LocationScopeValue();
		
		for(OperationScope opScope : operationScope){
			
			if(opScope.getScopeType().contentEquals(QoSConsts.QOS)){

				qosScopeValue = QoSscopeValue.convertObjectToJaxbObject((Node)opScope.getScopeValue(), qosScopeValue, QoSscopeValue.class);

				qosReqFound = true;
				
			}
			if(opScope.getScopeType().contentEquals(QoSConsts.LOCATION)){
				
				locationScopeValue = LocationScopeValue.convertObjectToJaxbObject((Node)opScope.getScopeValue(), locationScopeValue, LocationScopeValue.class);
				
			}
		}

		if(!qosReqFound || qosScopeValue == null){
			return null;
		}
		
		request.setOpType(opType);
		request.setRequiredServicesNameList(requiredServicesName);
		request.setQosRequirements(qosScopeValue);
		request.setLocationRequirement(locationScopeValue);
		return request;
		
	}

	/* discover a list of equivalent things given the list of entityId objects
	 * the list of required services and the restrictions */
	private StatusCode discoverThings(List<EntityId> entityIdList,
			Request request, Restriction restriction) {
		
		StatusCode statusCode;
		
		//create a discovery request object to get ContextRegResp List (list of things)
		DiscoverContextAvailabilityRequest discoveryRequest = new DiscoverContextAvailabilityRequest(
				entityIdList, request.getRequiredServicesNameList(), restriction);
		
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
			QueryContextResponse qosMonitorResponse = qosMonitorNgsi.queryContext(queryRequest);
			
			if(qosMonitorResponse == null){
				
				statusCode =  new StatusCode(
						Code.BADREQUEST_400.getCode(),
						ReasonPhrase.BADREQUEST_400.toString(), "No response from QoSMonitor");
				
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
			Boolean serviceOK = 
					createThingsMap(ContextRegistrationResponseList, 
									qosMonitorResponse.getListContextElementResponse(), request);
			
			//the conditions for execution of the service
			//allocation algorithm are not achieved
			if(!serviceOK){
				
				statusCode =  new StatusCode(
						Code.BADREQUEST_400.getCode(),
						ReasonPhrase.BADREQUEST_400.toString(), "Service Agreement can't be achieved");
				
				return statusCode;
			}
			
			if(discoveryResponse.getErrorCode() != null){
				statusCode = discoveryResponse.getErrorCode();
			}
			else{
				statusCode =  new StatusCode(
						Code.OK_200.getCode(),
						ReasonPhrase.OK_200.toString(), "Result");
			}
			
			return statusCode;
		}
		else{
			
			//no elements found in the iotDiscovery
			statusCode = discoveryResponse.getErrorCode();
			return null;
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
	private Boolean createThingsMap(
			List<ContextRegistrationResponse> contextRegistrationResponseList,
			List<ContextElementResponse> qosMonitorResponse, Request request) {
		
		//Map<reqServName, List<DevID>>
		HashMap<String, EquivalentThings> serviceEquivalentThings = new HashMap<>();
		
		//set the key serviceEquivalentThings Map 
		//taken from the requiredServicesNameList
		//in Request object
		for(String reqServName: request.getRequiredServicesNameList()){
			serviceEquivalentThings.put(reqServName, new EquivalentThings());
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
					
					if(t.getServicesList().get(reqServName) != null){
						serviceEquivalentThings.get(reqServName).addEqThing(devId);
					}
				}
			}
			else{
				logger.debug("ERROR: devId is not set, Thing can be stored");
			}
			
		}
		
		//TODO use threads for writing in DBs
		//update ThingsInfoDB and ServiceEquivalentThingsDB
		//with the new Map<DevId, Thing> and Map<reqServName, List<DevId>>
		ThingsInfo thInfo = new ThingsInfo();
		thInfo.setThingInfoList(thingsInfo);
		ServiceEquivalentThingsMapping servEqThings = new ServiceEquivalentThingsMapping();
		servEqThings.setServiceEquivalentThings(serviceEquivalentThings);
		
		qosMonitor.updateThingsServicesInfo(thingsInfo, serviceEquivalentThings);
		
		Boolean checkServiceAgreementRequestConditions =
					checkServiceAllocationConditions(serviceEquivalentThings, thingsInfo, request);
		
		if(!checkServiceAgreementRequestConditions){
			return false;
		}
		
		return true;
	}

	/* check the condition for the serviceAgreementRequest
	at least one thing for required service
	if only one thing for a service the Thing
	battery must be != null */
	private Boolean checkServiceAllocationConditions(
			HashMap<String, EquivalentThings> serviceEquivalentThings,
			HashMap<String, Thing> thingsInfo, Request request) {

		for(Map.Entry<String, EquivalentThings> entry: serviceEquivalentThings.entrySet()){
			
			List<String> eqThings = entry.getValue().getEqThings();
			if(eqThings.isEmpty()){
				return false;
			}
			
			if(eqThings.size() == 1){
				
				String eqThingDevId = eqThings.get(0);
				
				if(thingsInfo.get(eqThingDevId).getBatteryLevel()==null){
					return false;
				}
			}
			
			Double maxRespTime = request.getQosRequirements().getMaxResponseTime();
			Double accuracy = request.getQosRequirements().getAccuracy();
			String reqServName = entry.getKey();
			
			//var to check if filtering the eqThings
			//based on latency and accuracy there is at least one
			//thing for that requiredService
			Boolean constraints = false;
			
			for(String eqThingDevId: eqThings){
				
				Double latency = thingsInfo.get(eqThingDevId).getServicesList().get(reqServName).getLatency();
				Double servAccuracy = thingsInfo.get(eqThingDevId).getServicesList().get(reqServName).getAccuracy();
				
				//if latency or servAccuracy are not null
				//they must respect the constraints
				if((latency == null || latency < maxRespTime) && (servAccuracy == null || servAccuracy >= accuracy)){
					constraints = true;
					break;
				}
			}
			
			//filtering a list of eqThing
			//this service has no things
			//so it is useless continue in the allocation
			//process
			if(!constraints){
				return false;
			}

		}
		
		return true;
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



}
