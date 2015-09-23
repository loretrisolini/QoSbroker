package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElement;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistrationResponse;

public class Thing {

	//id inside the structure EntityId of the ContRegResp
	//and ContElem
	private String id;
	
	private ContextElement contElem;
	
	private ContextRegistrationResponse contRegResp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ContextElement getContElem() {
		return contElem;
	}

	public void setContElem(ContextElement contElem) {
		this.contElem = contElem;
	}

	public ContextRegistrationResponse getContRegResp() {
		return contRegResp;
	}

	public void setContRegResp(ContextRegistrationResponse contRegResp) {
		this.contRegResp = contRegResp;
	}
	
}
