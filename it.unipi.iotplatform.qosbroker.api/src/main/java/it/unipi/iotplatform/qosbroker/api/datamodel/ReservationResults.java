package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.List;

import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistration;

public class ReservationResults {

	private List<ContextRegistration> allocationSchema;
	
	private boolean isFeas;
	
	private int which;

	public List<ContextRegistration> getAllocationSchema() {
		return allocationSchema;
	}

	public void setAllocationSchema(List<ContextRegistration> allocationSchema) {
		this.allocationSchema = allocationSchema;
	}

	public boolean isFeas() {
		return isFeas;
	}

	public void setFeas(boolean isFeas) {
		this.isFeas = isFeas;
	}

	public int getWhich() {
		return which;
	}

	public void setWhich(int which) {
		this.which = which;
	}
}
