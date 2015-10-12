package it.unipi.iotplatform.qosbroker.qosmonitor.impl;



import it.unipi.iotplatform.qosbroker.api.datamodel.DataStructure;
import it.unipi.iotplatform.qosbroker.api.datamodel.EquivalentThings;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSConsts;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.couchdb.api.QoSBigDataRepository;
import it.unipi.iotplatform.qosbroker.qosmonitor.api.QoSMonitorIF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Code;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElement;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElementResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;
import eu.neclab.iotplatform.ngsi.api.datamodel.NotifyContextRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.NotifyContextResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;
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
			
			if(!data.getRight().isNull("attributes")){
				data.getRight().put("contextAttributeList", data.getRight().getJSONArray("attributes"));
				data.getRight().remove("attributes");
			}
			
			//problem with ContextElem in json format
			//that is different from the jaxb format
//			ContextElementResponse contElemResp = 
//					DataStructure.fromJsonToContextElementResponse(data.getRight());
			ContextElement contElem =
					DataStructure.fromJsonToJaxb(data.getRight(), new ContextElement(), ContextElement.class);
			
			ContextElementResponse contElemResp = new ContextElementResponse();
			contElemResp.setContextElement(contElem);
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
					
					//Map<DevId, Thing> used to update battery and coords
					//in the ThingsInfoDB
					HashMap<String, Thing> thingsInfo = new HashMap<>();
					
					while (iter.hasNext()) {

						ContextElement contElem = iter.next();
						
						//get id of the entityId of the ContElem as DevId
						String key = contElem.getEntityId().getId();
						
						//problem of conversion of ContElem from
						//jaxb xml to json
						//JSONObject jsonContElem = DataStructure.fromContextElementToJson(contElem);
						JSONObject jsonContElem = DataStructure.fromJaxbToJson(contElem, ContextElement.class);
						
						if(!jsonContElem.isNull("contextAttributeList")){
							jsonContElem.put("attributes", jsonContElem.getJSONArray("contextAttributeList"));
							jsonContElem.remove("contextAttributeList");
						}
							
						//create the pair <DevId, Thing> to put 
						//in Map<DevId, Thing>
						Thing t = new Thing();
						if(!jsonContElem.isNull("attributes")){
							
							JSONArray attributes = jsonContElem.getJSONArray("attributes");
							
							for(int i = 0; i < attributes.length(); i++){
								
								JSONObject attr = attributes.getJSONObject(i);
								
								if(!attr.isNull("name")){
									if(attr.getString("name").contentEquals(QoSConsts.BATTERY)){
										t.setBatteryLevel(Double.valueOf(attr.getString("contextValue")));
									}
													
									if(attr.getString("name").contentEquals(QoSConsts.COORDS)){
										
										Point p = new Point();
										String[] coords = attr.getString("contextValue").split(",");
										
										p.setLatitude(Float.valueOf(coords[0]));
										p.setLongitude(Float.valueOf(coords[1]));
										
										t.setCoords(p);
									}
									
								}
							}
							
							t.setServicesList(new HashMap<String, ServiceFeatures>());
							
							thingsInfo.put(key, t);
						}
						
						Pair<String,JSONObject> dataEntry = new Pair<String, JSONObject>(key, jsonContElem);

						dataList.add(dataEntry);

					}
					
					bigDataRepository.storeData(dataList, QoSConsts.SENS_ACT_ATTR_DB);
					
					//update battery and coords in Map<DevId, Thing>
					updateThingsServicesInfo(thingsInfo, null);
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

	/* function to update ThingsInfoDB and ServNameEqThingsDB */
	@Override
	public boolean updateThingsServicesInfo(HashMap<String, Thing> thingsInfo,
			HashMap<String, EquivalentThings> serviceEquivalentThings) {
		
		
		List<Pair<String, JSONObject>> thingsData = bigDataRepository.readData(null, QoSConsts.THINGS_INFO_DB);
		
		if(thingsData == null){
			//error
			return false;
		}


		//conversion of data read from the DB 
		if(!thingsData.isEmpty()){
			
			//conversion from json data to Map<DevId, Thing>
			for(Pair<String, JSONObject> entryOldThing: thingsData){
					
				//check if the key DevId taken from the data read from DB
				//is equal to some key in new Map<DevId, Thing> thingsInfo
				//two case: 
				//FIRST case, create Map<DevId, Thing> after an updateContext so the 
				//update operation is only for batt and coords
				//SECOND case, create Map<DevId, Thing> after ServAgreement and
				//if batt or coords are null try to read this from thingsInfoDB
				if(thingsInfo.get(entryOldThing.getLeft()) != null){

					//convert json to Thing (Old)
					Thing t = Thing.fromJsonToJaxb(entryOldThing.getRight(), new Thing(), Thing.class);
					
					//check the case in which update is called
					//to update only battery and coords
					//after the updateContext
					//in this case must be avoided the deleting
					//of old data things so we read the thingsInfoDB
					//and we update only battery and coords
					if(serviceEquivalentThings == null){
						//update the old thing with battery and coords
						t.setBatteryLevel(thingsInfo.get(entryOldThing.getLeft()).getBatteryLevel());
						t.setCoords(thingsInfo.get(entryOldThing.getLeft()).getCoords());

						//store the old thing with updated values of
						//battery and coords in the Map<DevId, Thing>
						//that will be stored in the DB
						//it is done to avoid that in case of updating
						//only batt and coords old thing data are deleted
						thingsInfo.put(entryOldThing.getLeft(), t);
					}
					else{
						//if a thing on new Map<DevId, Thing> (created in the ServAgreement operation) 
						//has battery or coords null
						//there is a trial to read this values from the things read in the DB
						//(check if values are not null in the thing read from DB)
						if(thingsInfo.get(entryOldThing.getLeft()).getBatteryLevel() == null ||
								thingsInfo.get(entryOldThing.getLeft()).getCoords() == null){
							if(t.getBatteryLevel() != null){
								thingsInfo.get(entryOldThing.getLeft()).setBatteryLevel(t.getBatteryLevel());
							}
							
							if(t.getCoords() != null){
								thingsInfo.get(entryOldThing.getLeft()).setCoords(t.getCoords());
							}
						}
					}


				}

			}
		}
			
		//store new Map<DevId, Thing> in ThingsInfoDB
		List<Pair<String, JSONObject>> newThingsData = new ArrayList<>();
		for(Map.Entry<String, Thing> entry : thingsInfo.entrySet()){
			
			//conversion of Thing in json format
//			JSONObject jsonThing = XML.toJSONObject(entry.getValue().toString());
			JSONObject jsonThing = Thing.fromJaxbToJson(entry.getValue(), Thing.class);
			
			Pair<String, JSONObject> thingPair = new Pair<String, JSONObject>(entry.getKey(), jsonThing);
			
			newThingsData.add(thingPair);
		}
		
		//store data
		if(!bigDataRepository.storeData(newThingsData, QoSConsts.THINGS_INFO_DB)){
			return false;
		}
		
		//check the case in which update is called
		//to update only battery and coords
		//after the updateContext otherwise
		//update list of equivalent things for each reqServ
		if(serviceEquivalentThings != null){
			
			List<Pair<String, JSONObject>> servEqThingsData = bigDataRepository.readData(null, QoSConsts.SERV_EQ_THINGS_DB);
			
			if(servEqThingsData == null){
				//error
				return false;
			}
			
			//conversion of data read from the DB 
			//if the result from db is not empty
			//serviceEquivalentThings must be updated
			if(!servEqThingsData.isEmpty()){
				//conversion from json data to Map<reqServName, List<DevId>>
				//and update the list of equivalent things for a reqServName
				for(Pair<String, JSONObject> entryOldEqThings: servEqThingsData){
					
					//if match reqServName, add the new <DevId> list to stored reqServ
					if(serviceEquivalentThings.get(entryOldEqThings.getLeft()) != null){
						//conversion of old eq things data
						EquivalentThings oldEqThings = 
								EquivalentThings.fromJsonToJaxb(entryOldEqThings.getRight(), new EquivalentThings(), 
																	EquivalentThings.class);
						
						List<String> oldEqThingsList = oldEqThings.getEqThings();
						List<String> newEqThingsList = serviceEquivalentThings.get(entryOldEqThings.getLeft()).getEqThings();

						//merge operation of the old eqThingsList and the new One
						//avoiding duplicates
						for(String oldDevId: oldEqThingsList){
							for(String newDevId : newEqThingsList){
								if(oldDevId.contentEquals(newDevId)){
									oldEqThingsList.remove(oldDevId);
								}
							}
						}
						serviceEquivalentThings.get(entryOldEqThings.getLeft()).getEqThings().addAll(oldEqThingsList);
					}
				}
			}
			
			//store new Map<reServName, List<DevId>> in ServiceEquivalentThingsDB
			List<Pair<String, JSONObject>> newServEqThingsData = new ArrayList<>();
			for(Map.Entry<String, EquivalentThings> entry : serviceEquivalentThings.entrySet()){
				
				//conversion of EquivalentThings in json format
//				JSONObject jsonThing = XML.toJSONObject(entry.getValue().toString());
				JSONObject jsonThing = EquivalentThings.fromJaxbToJson(entry.getValue(), EquivalentThings.class);
				
				Pair<String, JSONObject> servEqThingsPair = new Pair<String, JSONObject>(entry.getKey(), jsonThing);
				
				newServEqThingsData.add(servEqThingsPair);
			}
			
			//store data
			if(!bigDataRepository.storeData(newServEqThingsData, QoSConsts.SERV_EQ_THINGS_DB)){
				return false;
			}
		}
		
		return true;
		
	}

}
