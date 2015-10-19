package it.unipi.iotplatform.qosbroker.api.datamodel;

public enum Priority {

	//indicates the value of p_ij
	//to use f_ij, u_ij or random value
	BATTERY(1), UTILIZATION(2), RANDOM(3);
	
	private final int value;

	private Priority(int value) {
		this.value = value;
	}

	public int getCode() {
		return value;
	}
}
