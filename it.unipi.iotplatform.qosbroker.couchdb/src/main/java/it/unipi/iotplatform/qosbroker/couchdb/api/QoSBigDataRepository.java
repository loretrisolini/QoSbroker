package it.unipi.iotplatform.qosbroker.couchdb.api;

import java.util.List;

import org.json.JSONObject;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;

public interface QoSBigDataRepository {
	
	Boolean storeData(List<Pair<String, JSONObject>> dataList, String DBName);
	
	List<Pair<String, JSONObject>> readData(List<String> keyList, String DBName);
}
