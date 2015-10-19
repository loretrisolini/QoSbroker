package it.unipi.iotplatform.qosbroker.api.datamodel;

public enum AllocationPolicy {

	WRoundRobin(1);

	private final int value;

	private AllocationPolicy(int value) {
		this.value = value;
	}

	public int getCode() {
		return value;
	}
}
