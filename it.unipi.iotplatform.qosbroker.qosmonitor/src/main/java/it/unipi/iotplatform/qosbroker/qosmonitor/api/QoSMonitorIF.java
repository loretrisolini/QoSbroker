package it.unipi.iotplatform.qosbroker.qosmonitor.api;

import it.unipi.iotplatform.qosbroker.api.datamodel.EquivalentThings;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceEquivalentThingsMapping;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsInfo;

import java.util.HashMap;
import java.util.List;

public interface QoSMonitorIF {

	public boolean updateThingsServicesInfo(HashMap<String, Thing> thingsInfo,
			HashMap<String, EquivalentThings> serviceEquivalentThings);
}
