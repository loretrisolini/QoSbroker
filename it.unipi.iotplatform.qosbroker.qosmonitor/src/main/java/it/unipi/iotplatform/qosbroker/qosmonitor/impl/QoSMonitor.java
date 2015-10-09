package it.unipi.iotplatform.qosbroker.qosmonitor.impl;



import it.unipi.iotplatform.qosbroker.api.datamodel.DataStructure;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSConsts;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.couchdb.api.QoSBigDataRepository;
import it.unipi.iotplatform.qosbroker.qosmonitor.api.QoSMonitorIF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Code;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElement;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElementResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;
import eu.neclab.iotplatform.ngsi.api.datamodel.NgsiStructure;
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
		
		List<Pair<String, JSONObject>> dataList = bigDataRepository.readData(idList, QoSConsts.SENS_ACT_ATTR_DB);
		
		QueryContextResponse queryResponse = new QueryContextResponse();
		
		if(dataList == null){

			queryResponse.setErrorCode(new StatusCode(
					Code.INTERNALERROR_500.getCode(),
					ReasonPhrase.RECEIVERINTERNALERROR_500.toString(), "Internal QoSMonitor Error"));
			
			return queryResponse;
		}
		
		//check if for all keys, the response
		//was element not found
		if(dataList.isEmpty()){

			queryResponse.setErrorCode(new StatusCode(
					Code.CONTEXTELEMENTNOTFOUND_404.getCode(),
					ReasonPhrase.CONTEXTELEMENTNOTFOUND_404.toString(), "Context Element not found"));
			
			return queryResponse;
		}
		
		for(Pair<String, JSONObject> data: dataList){
			
			//problem with ContextElem in json format
			//that is different from the jaxb format
			ContextElementResponse contElemResp = 
					DataStructure.fromJsonToContextElementResponse(data.getRight());
			
			contextElemRespList.add(contElemResp);
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

	/* update battery and coords of a thing */
	@Override
	public UpdateContextResponse updateContext(UpdateContextRequest arg0) {
		
		final List<ContextElement> contextElementList = arg0.getContextElement();
		
		if (bigDataRepository != null) {

			new Thread() {
				@Override
				public void run() {

					Iterator<ContextElement> iter = contextElementList.iterator();
					
					List<Pair<String, JSONObject>> dataList = new ArrayList<Pair<String, JSONObject>>();
					
					while (iter.hasNext()) {

						ContextElement contElem = iter.next();
						
						//get id of the entityId of the ContElem as DevId
						String key = contElem.getEntityId().getId();
						
						//TODO READ Map<DevId, Thing> and update battery and coords
						
						//problem of conversion of ContElem from
						//jaxb xml to json
						JSONObject jsonContElem = DataStructure.fromContextElementToJson(contElem);
						
						Pair<String,JSONObject> dataEntry = new Pair<String, JSONObject>(key, jsonContElem);

						dataList.add(dataEntry);

					}

					bigDataRepository.storeData(dataList, QoSConsts.SENS_ACT_ATTR_DB);
				}
			}.start();
		}
		
		UpdateContextResponse response = new UpdateContextResponse(
				new StatusCode(Code.OK_200.getCode(),
						ReasonPhrase.OK_200.toString(),
						"store ContElements in QoSMonitor"), null);

		return response;
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
