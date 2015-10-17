package it.unipi.iotplatform.qosbroker.api.datamodel;

public enum Policy {

	MAX_SPLIT(1), MIN_SPLIT(2);

	private final int value;

	private Policy(int value) {
		this.value = value;
	}

	public int getCode() {
		return value;
	}

}