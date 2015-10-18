package it.unipi.iotplatform.qosbroker.qosmonitor.impl;



import it.unipi.iotplatform.qosbroker.api.datamodel.DataStructure;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSCode;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSConsts;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSReasonPhrase;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.couchdb.api.QoSBigDataRepository;
import it.unipi.iotplatform.qosbroker.qosmonitor.api.QoSMonitorIF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Code;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElement;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElementResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistration;
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

//	private static Logger logger = Logger.getLogger(QoSMonitor.class);
	
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
					ReasonPhrase.RECEIVERINTERNALERROR_500.toString(), "QoSMonitor -- queryContext() "
							+ "error in reading ContextElements from DB "+QoSConsts.SENS_ACT_ATTR_DB));
			
			return queryResponse;
		}
		
		//check if for all keys, the response
		//was element not found
		if(dataList.isEmpty()){

			queryResponse.setErrorCode(new StatusCode(
					Code.CONTEXTELEMENTNOTFOUND_404.getCode(),
					ReasonPhrase.CONTEXTELEMENTNOTFOUND_404.toString(), "QoSMonitor -- queryContext() No Context Element not found"));
			
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
				ReasonPhrase.OK_200.toString(), "QoSMonitor -- queryContext() OK"));
		
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

					List<Pair<String, JSONObject>> sensActAttrData = bigDataRepository.readData(null, QoSConsts.SENS_ACT_ATTR_DB);
					
					if(sensActAttrData == null){
						//error
						System.out.println("ERROR reading sensor active attributes data");
						return;
					}
					
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
						
//						String devIdFromDB = null;
						JSONObject jsonContElemFromDB = null;
						JSONArray attributesFromDB = null;
						Boolean attrFromDB = false;
						//merge data read from DB with data in updateCont body
						//to avoid to delete data in DB
						for(Pair<String, JSONObject> pair: sensActAttrData){
							
							if(pair.getLeft().contentEquals(key)){
//								devIdFromDB = pair.getLeft();
								jsonContElemFromDB = pair.getRight();
								
								if(!jsonContElemFromDB.isNull("attributes")){
									attributesFromDB = jsonContElemFromDB.getJSONArray("attributes");
									attrFromDB = true && attributesFromDB!=null;
								}
								break;
							}
						}
						
						Double battFromDB = null;
						Point coordsFromDB = null;
						if(attrFromDB){
							for(int i = 0; i < attributesFromDB.length(); i++){
								
								JSONObject attr = attributesFromDB.getJSONObject(i);
								
								if(!attr.isNull("name")){
									if(attr.getString("name").contentEquals(QoSConsts.BATTERY)){
										battFromDB = Double.valueOf(attr.getString("contextValue"));
									}
													
									if(attr.getString("name").contentEquals(QoSConsts.COORDS)){
										
										coordsFromDB = new Point();
										String[] coords = attr.getString("contextValue").split(",");
										
										coordsFromDB.setLatitude(Float.valueOf(coords[0]));
										coordsFromDB.setLongitude(Float.valueOf(coords[1]));
									}
									
								}
							}
						}
						
						//create the pair <DevId, Thing> to put 
						//in Map<DevId, Thing>
						Thing t = new Thing();
						Boolean battFound = false;
						Boolean coordsFound = false;
						if(!jsonContElem.isNull("attributes")){
							
							JSONArray attributes = jsonContElem.getJSONArray("attributes");
							
							for(int i = 0; i < attributes.length(); i++){
								
								JSONObject attr = attributes.getJSONObject(i);
								
								if(!attr.isNull("name")){
									if(attr.getString("name").contentEquals(QoSConsts.BATTERY)){
										t.setBatteryLevel(Double.valueOf(attr.getString("contextValue")));
										battFound = true;
									}
													
									if(attr.getString("name").contentEquals(QoSConsts.COORDS)){
										
										Point p = new Point();
										String[] coords = attr.getString("contextValue").split(",");
										
										p.setLatitude(Float.valueOf(coords[0]));
										p.setLongitude(Float.valueOf(coords[1]));
										
										t.setCoords(p);
										coordsFound = true;
									}
									
								}

							}
							
							
							//nothing to store in the DB sensorActAttr & thingInfo
							if(battFound || coordsFound){

								if(!battFound && battFromDB!=null){
									t.setBatteryLevel(battFromDB);
									
									JSONObject jsonContAttribute = new JSONObject();
									
									jsonContAttribute.put("name", "battery");
									jsonContAttribute.put("contextValue", String.valueOf(battFromDB));
									jsonContAttribute.put("type", "double");
									
									attributes.put(jsonContAttribute);
								}
								
								if(t.getCoords()==null && coordsFromDB!=null){
									t.setCoords(coordsFromDB);
									
									JSONObject jsonContAttribute = new JSONObject();
									
									jsonContAttribute.put("name", "coords");
									jsonContAttribute.put("contextValue", String.valueOf(coordsFromDB.getLatitude()
																+" "+coordsFromDB.getLongitude()));
									jsonContAttribute.put("type", "coords");
									
									attributes.put(jsonContAttribute);
								}
								
								t.setServicesList(new HashMap<String, ServiceFeatures>());
								
								thingsInfo.put(key, t);
								
								Pair<String,JSONObject> dataEntry = new Pair<String, JSONObject>(key, jsonContElem);

								dataList.add(dataEntry);
							}
							
						}

					}
					
					if(!dataList.isEmpty())
						bigDataRepository.storeData(dataList, QoSConsts.SENS_ACT_ATTR_DB);
					
					if(!thingsInfo.isEmpty())
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
	public StatusCode updateThingsServicesInfo(HashMap<String, Thing> thingsInfo,
			HashMap<String, ThingsIdList> serviceEquivalentThings) {
		
		System.out.println("QoSMonitor -- updateThingsServicesInfo()");
		
		StatusCode statusCode;
		
		List<Pair<String, JSONObject>> thingsData = bigDataRepository.readData(null, QoSConsts.THINGS_INFO_DB);
		
		if(thingsData == null){
			//error
			
			statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), "QoSMonitor -- ERROR in updateThingsServicesInfo() "
										+ "reading old thingsData from DB "+QoSConsts.THINGS_INFO_DB);
			return statusCode;
		}
		
		
		//list of devId to check if the list of devId in serviceEquivalentThings
		//point to things that are still alive, otherwise you remove the devId
		List<String> devIdCheckList = new ArrayList<>();
		
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

					//add the devId of the thing stored in the DB
					devIdCheckList.add(entryOldThing.getLeft());
					
					//convert json to Thing (Old)
					Thing t = Thing.fromJsonToJaxb(entryOldThing.getRight(), new Thing(), Thing.class);
					
					//check the case in which update is called
					//to update only battery and coords
					//after the updateContext
					//in this case must be avoided the deleting
					//of old data things so we read the thingsInfoDB
					//and we update only battery and coords
					if(serviceEquivalentThings == null){
						
						System.out.println("QoSMonitor -- updateThingsServicesInfo() monitoring after updateContext");
						
						//update the old thing with battery and coords
						//MONITORING OPERATION
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
		newThingsData = Thing.fromJavaFormatToDbFormat(thingsInfo, Thing.class);
		
		if(newThingsData == null){
			statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), "QoSMonitor -- ERROR in updateThingsServicesInfo() "
					+ "error conversion fromJavaFormatToDbFormat newThingsData for storing in DB "+QoSConsts.THINGS_INFO_DB);
			return statusCode;
		}
//		for(Map.Entry<String, Thing> entry : thingsInfo.entrySet()){
//			
//			//conversion of Thing in json format
////			JSONObject jsonThing = XML.toJSONObject(entry.getValue().toString());
//			JSONObject jsonThing = Thing.fromJaxbToJson(entry.getValue(), Thing.class);
//			
//			Pair<String, JSONObject> thingPair = new Pair<String, JSONObject>(entry.getKey(), jsonThing);
//			
//			newThingsData.add(thingPair);
//		}
		
		System.out.println("QoSMonitor -- updateThingsServicesInfo() update THINGS_INFO_DB");
		
		//store data
		if(!bigDataRepository.storeData(newThingsData, QoSConsts.THINGS_INFO_DB)){
			statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), "QoSMonitor -- ERROR in updateThingsServicesInfo() "
					+ "error writing newThingsInfo in DB "+QoSConsts.THINGS_INFO_DB);
			return statusCode;
		}
		
		//check the case in which update is called
		//to update only battery and coords
		//after the updateContext otherwise
		//update list of equivalent things for each reqServ
		if(serviceEquivalentThings != null){
			
			List<Pair<String, JSONObject>> servEqThingsData = bigDataRepository.readData(null, QoSConsts.SERV_EQ_THINGS_DB);
			
			if(servEqThingsData == null){
				statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), "QoSMonitor -- ERROR in updateThingsServicesInfo() "
						+ "error reading old servEqThingsData in DB "+QoSConsts.SERV_EQ_THINGS_DB);
				return statusCode;
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
						ThingsIdList oldEqThings = 
								ThingsIdList.fromJsonToJaxb(entryOldEqThings.getRight(), new ThingsIdList(), 
																	ThingsIdList.class);
						
						List<String> oldEqThingsList = oldEqThings.getEqThings();
						List<String> newEqThingsList = serviceEquivalentThings.get(entryOldEqThings.getLeft()).getEqThings();

						//merge operation of the old eqThingsList and the new One
						//avoiding duplicates
						for(int i=0; i<oldEqThingsList.size(); i++){
							for(String newDevId : newEqThingsList){
								//check if the devId point to a thing that is 
								//no more alive
								//MONITORING OPERATION
								if(oldEqThingsList.get(i).contentEquals(newDevId) || 
										!devIdCheckList.contains(oldEqThingsList.get(i))){
									oldEqThingsList.remove(i);
								}
								if(oldEqThingsList.isEmpty()){ break; }
							}
						}
						
						if(!oldEqThingsList.isEmpty())
							serviceEquivalentThings.get(entryOldEqThings.getLeft()).getEqThings().addAll(oldEqThingsList);
					}
				}
			}
			
			//store new Map<reServName, List<DevId>> in ServiceEquivalentThingsDB
			List<Pair<String, JSONObject>> newServEqThingsData = new ArrayList<>();
			newServEqThingsData = ThingsIdList.fromJavaFormatToDbFormat(serviceEquivalentThings, ThingsIdList.class);
			
			if(newServEqThingsData == null){
				statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), "QoSMonitor -- ERROR in updateThingsServicesInfo() "
						+ "error conversion fromJavaFormatToDbFormat newServEqThingsData for storing in DB "+QoSConsts.SERV_EQ_THINGS_DB);
				return statusCode;
			}
//			for(Map.Entry<String, EquivalentThings> entry : serviceEquivalentThings.entrySet()){
//				
//				//conversion of EquivalentThings in json format
////				JSONObject jsonThing = XML.toJSONObject(entry.getValue().toString());
//				JSONObject jsonThing = EquivalentThings.fromJaxbToJson(entry.getValue(), EquivalentThings.class);
//				
//				Pair<String, JSONObject> servEqThingsPair = new Pair<String, JSONObject>(entry.getKey(), jsonThing);
//				
//				newServEqThingsData.add(servEqThingsPair);
//			}
			
			System.out.println("QoSMonitor -- updateThingsServicesInfo() update SERV_EQ_THINGS_DB");
			
			//store data
			if(!bigDataRepository.storeData(newServEqThingsData, QoSConsts.SERV_EQ_THINGS_DB)){
				statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), "QoSMonitor -- ERROR in updateThingsServicesInfo() "
						+ "error writing newServEqThingsData in DB "+QoSConsts.SERV_EQ_THINGS_DB);
				return statusCode;
			}
		}
		
		statusCode = new StatusCode(QoSCode.OK_200.getCode(), QoSReasonPhrase.OK_200.name(), "QoSMonitor -- updateThingsServicesInfo() OK");
		return statusCode;
		
	}

//	/* function to update requirements_db with Map<devId, List<transId>>
//	 * that represents the devId with an associated List of transId
//	 * for which the thing with devId respect the constraints of 
//	 * the request identified by the transId */
//	@Override
//	public boolean updateThingTransactions(
//			HashMap<String, String> thingTransactionMap) {
//		
//		List<Pair<String, JSONObject>> thingTransactionsData = bigDataRepository.readData(null, QoSConsts.REQUIREMENTS_DB);
//		
//		if(thingTransactionsData == null){
//			//error maybe there is no things stored
//			return false;
//		}
//		
//		//convert data from DB format to java format
//		HashMap<String, TransIdList> thingTransactionsStored = Thing.fromDbFormatToJavaFormat(thingTransactionsData, TransIdList.class);
//		if(thingTransactionsStored == null){
//			return false;
//		}
//		
//		//iterate over the new thingTransactionsMap, if devId match
//		//in the thingTransactionsStored, add the new transId
//		for(Map.Entry<String, String> entry: thingTransactionMap.entrySet()){
//			
//			String devId = entry.getKey();
//			if(thingTransactionsStored.get(devId) != null){
//				//only add transId
//				thingTransactionsStored.get(devId).getTransIdList().add(entry.getValue());
//			}
//			else{
//				//add the entry <DevId, List<transId>>
//				TransIdList transactions = new TransIdList();
//				List<String> transIdList = new ArrayList<String>();
//				transIdList.add(entry.getValue());
//				transactions.setTransIdList(transIdList);
//				thingTransactionsStored.put(devId, transactions);
//			}
//		}
//		
//		thingTransactionsData = TransIdList.fromJavaFormatToDbFormat(thingTransactionsStored, TransIdList.class);
//		
//		//store data
//		if(!bigDataRepository.storeData(thingTransactionsData, QoSConsts.REQUIREMENTS_DB)){
//			return false;
//		}
//		
//		return true;
//	}

}
