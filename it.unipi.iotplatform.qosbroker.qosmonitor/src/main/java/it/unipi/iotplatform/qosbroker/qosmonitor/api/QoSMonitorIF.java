package it.unipi.iotplatform.qosbroker.qosmonitor.api;

import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;

import java.util.HashMap;

import eu.neclab.iotplatform.ngsi.api.datamodel.StatusCode;

public interface QoSMonitorIF {

	public StatusCode updateThingsServicesInfo(HashMap<String, Thing> thingsInfo,
			HashMap<String, ThingsIdList> serviceEquivalentThings);

//	public boolean updateThingTransactions(HashMap<String, String> thingTransactionMap);
}
