package it.unipi.iotplatform.qosbroker.qosmonitor.api;

import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;

import java.util.HashMap;
import java.util.List;

public interface QoSMonitorIF {

	public boolean updateThingsServicesInfo(HashMap<String, Thing> thingsInfo, HashMap<String, List<String>> serviceEquivalentThings);
}
