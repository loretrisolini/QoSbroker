package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

import eu.neclab.iotplatform.ngsi.api.datamodel.ContextAttribute;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistrationAttribute;

public class Thing {

	//id inside the structure EntityId of the ContRegResp
	//and ContElem
	private String id;
	
	private ContextRegistrationAttribute serviceSpec;
	
	private ContextAttribute batteryLevel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ContextRegistrationAttribute getServiceSpec() {
		return serviceSpec;
	}

	public void setServiceSpec(ContextRegistrationAttribute serviceSpec) {
		this.serviceSpec = serviceSpec;
	}

	public ContextAttribute getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(ContextAttribute batteryLevel) {
		this.batteryLevel = batteryLevel;
	}
	
}
