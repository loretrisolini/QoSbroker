package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

import java.util.HashMap;

/* class that represents the association between
 * a requested service and the list of equivalent
 * things with a thing service that can satisfy
 * that service  */
public class ServiceAssignments {

	private String transId_servId;
	
	//Map<thingId, <thingServiceId, f_ij, u_ij>>
	private HashMap<Integer, ServiceExecutionFeature> thingServiceExecFeatureMap;

	public String getTransId_servId() {
		return transId_servId;
	}

	public void setTransId_servId(String transId_servId) {
		this.transId_servId = transId_servId;
	}

	public HashMap<Integer, ServiceExecutionFeature> getThingServiceExecFeatureMap() {
		return thingServiceExecFeatureMap;
	}

	public void setThingServiceExecFeatureMap(
			HashMap<Integer, ServiceExecutionFeature> thingServiceExecFeatureMap) {
		this.thingServiceExecFeatureMap = thingServiceExecFeatureMap;
	}
}
