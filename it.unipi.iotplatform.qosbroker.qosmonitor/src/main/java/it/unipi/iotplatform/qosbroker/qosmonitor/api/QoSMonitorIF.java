package it.unipi.iotplatform.qosbroker.qosmonitor.api;

import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.api.datamodel.TransIdList;

import java.util.HashMap;

public interface QoSMonitorIF {

	public boolean updateThingsServicesInfo(HashMap<String, Thing> thingsInfo,
			HashMap<String, ThingsIdList> serviceEquivalentThings);
	
	public boolean updateThingTransactions(HashMap<String, String> thingTransactionMap);
}
