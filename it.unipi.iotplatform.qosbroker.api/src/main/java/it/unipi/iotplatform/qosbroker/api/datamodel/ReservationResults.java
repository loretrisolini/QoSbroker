package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.List;

import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistration;
import eu.neclab.iotplatform.ngsi.api.datamodel.StatusCode;

public class ReservationResults {

	private List<ContextRegistration> allocationSchema;
	
	private boolean isFeas;
	
	private int which;
	
	private StatusCode statusCode;
	
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

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}
}
