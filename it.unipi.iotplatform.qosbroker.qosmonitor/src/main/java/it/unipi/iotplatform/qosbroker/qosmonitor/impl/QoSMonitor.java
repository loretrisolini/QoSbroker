package it.unipi.iotplatform.qosbroker.qosmonitor.impl;



import it.unipi.iotplatform.qosbroker.api.datamodel.DataStructure;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.couchdb.api.CouchDbConsts;
import it.unipi.iotplatform.qosbroker.couchdb.api.QoSBigDataRepository;
import it.unipi.iotplatform.qosbroker.qosmonitor.api.QoSMonitorIF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Code;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElement;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElementResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;
import eu.neclab.iotplatform.ngsi.api.datamodel.NotifyContextRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.NotifyContextResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.QueryContextRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.QueryContextResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.ReasonPhrase;
import eu.neclab.iotplatform.ngsi.api.datamodel.StatusCode;
import eu.neclab.iotplatform.ngsi.api.datamodel.SubscribeContextRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.SubscribeContextResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.UnsubscribeContextRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.UnsubscribeContextResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.UpdateContextRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.UpdateContextResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.UpdateContextSubscriptionRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.UpdateContextSubscriptionResponse;
import eu.neclab.iotplatform.ngsi.api.ngsi10.Ngsi10Interface;

public class QoSMonitor implements Ngsi10Interface, QoSMonitorIF{

	/**
	 * A pointer to a Big Data repository. (This functionality is currently
	 * disabled.)
	 */
	private QoSBigDataRepository bigDataRepository;
	
	@Override
	public NotifyContextResponse notifyContext(NotifyContextRequest arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryContextResponse queryContext(QueryContextRequest query) {
		
		List<EntityId> entityIdList = query.getEntityIdList();
		
		//this list is updated every i read a contextElementResponse 
		//from the database
		List<ContextElementResponse> contextElemRespList = new ArrayList<>();
		
		List<String> idList = new ArrayList<>();
		
		for(EntityId Id: entityIdList){
			
			idList.add(Id.getId());
		}
		
		List<Pair<String, JSONObject>> dataList = bigDataRepository.readData(idList, CouchDbConsts.SENS_ACT_ATTR_DB);
		
		QueryContextResponse queryResponse = new QueryContextResponse();
		
		if(dataList == null){

			queryResponse.setErrorCode(new StatusCode(
					Code.INTERNALERROR_500.getCode(),
					ReasonPhrase.RECEIVERINTERNALERROR_500.toString(), "Internal QoSMonitor Error"));
			
			return queryResponse;
		}
		
		for(Pair<String, JSONObject> data: dataList){
			
			if(!data.getRight().isNull("error")){
				continue;
			}
			
			//problem with ContextElem in json format
			//that is different from the jaxb format
			ContextElementResponse contElemResp = 
					DataStructure.fromJsonToContextElementResponse(data.getRight());
			
			contextElemRespList.add(contElemResp);
		}
		
		if(dataList.isEmpty()){

			queryResponse.setErrorCode(new StatusCode(
					Code.CONTEXTELEMENTNOTFOUND_404.getCode(),
					ReasonPhrase.CONTEXTELEMENTNOTFOUND_404.toString(), "Context Element not found"));
			
			return queryResponse;
		}
		
		queryResponse.setContextResponseList(contextElemRespList);
		queryResponse.setErrorCode(new StatusCode(
				Code.OK_200.getCode(),
				ReasonPhrase.OK_200.toString(), "Result"));
		
		return queryResponse;
	}

	@Override
	public SubscribeContextResponse subscribeContext(
			SubscribeContextRequest arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnsubscribeContextResponse unsubscribeContext(
			UnsubscribeContextRequest arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateContextResponse updateContext(UpdateContextRequest arg0) {
		// TODO Auto-generated method stub
		
//		/**
//		 * The code snippet below is for dumping the data in a Big Data
//		 * repository in addition. This feature is currently disabled.
//		 */
//		if (bigDataRepository != null) {
//
//			new Thread() {
//				@Override
//				public void run() {
//
//					List<ContextElement> contextElementList = new ArrayList<ContextElement>();
//
//					Iterator<ContextElementResponse> iter = queryContextRespLIstAfterMerge
//							.getListContextElementResponse().iterator();
//
//					while (iter.hasNext()) {
//
//						ContextElementResponse contextElementResp = iter
//								.next();
//
//						contextElementList.add(contextElementResp
//								.getContextElement());
//
//					}
//
//					bigDataRepository.storeData(contextElementList);
//
//				}
//			}.start();
//		}

		
		return null;
	}

	@Override
	public UpdateContextSubscriptionResponse updateContextSubscription(
			UpdateContextSubscriptionRequest arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public QoSBigDataRepository getBigDataRepository() {
		return bigDataRepository;
	}

	public void setBigDataRepository(QoSBigDataRepository bigDataRepository) {
		this.bigDataRepository = bigDataRepository;
	}

	@Override
	public boolean updateThingsServicesInfo(HashMap<String, Thing> thingsInfo,
			HashMap<String, List<String>> serviceEquivalentThings) {
		// TODO Auto-generated method stub
		return true;
		
	}

}
