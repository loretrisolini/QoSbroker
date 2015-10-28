package it.unipi.iotplatform.qosbroker.api.datamodel;

public enum Split {

	SINGLE_SPLIT(1), MULTI_SPLIT(2);
	
	private final int value;

	private Split(int value) {
		this.value = value;
	}

	public int getCode() {
		return value;
	}
}
