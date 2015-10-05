package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.HashMap;

/* class that represents the association between
 * a requested service and the list of equivalent
 * things with a thing service that can satisfy
 * that service  */
public class ServiceAssignments {

	private Integer servId;
	
	//Map<thingId, <thingServiceId, f_ij, u_ij, priority[]>>
	private HashMap<Integer, ServiceExecutionFeature> thingServiceExecFeatureMap;


	public HashMap<Integer, ServiceExecutionFeature> getThingServiceExecFeatureMap() {
		return thingServiceExecFeatureMap;
	}

	public void setThingServiceExecFeatureMap(
			HashMap<Integer, ServiceExecutionFeature> thingServiceExecFeatureMap) {
		this.thingServiceExecFeatureMap = thingServiceExecFeatureMap;
	}

	public Integer getServId() {
		return servId;
	}

	public void setServId(Integer servId) {
		this.servId = servId;
	}
}
