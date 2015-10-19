package it.unipi.iotplatform.qosbroker.api.datamodel;

import eu.neclab.iotplatform.ngsi.api.datamodel.StatusCode;

/* class that represents the input and the result of the execution of the QoSCalculator
 * heuristic */
public class ReservationResults {
	
	private Reserveobj[] res;
	
	private boolean isFeas;
	
	//say which Reserveobj
	//is a feasible allocation
	private int which;
	
	private StatusCode statusCode;

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

	public Reserveobj[] getRes() {
		return res;
	}

	public void setRes(Reserveobj[] res) {
		this.res = res;
	}

}
