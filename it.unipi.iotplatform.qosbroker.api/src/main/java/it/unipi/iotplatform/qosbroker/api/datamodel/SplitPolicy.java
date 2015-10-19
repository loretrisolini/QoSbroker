package it.unipi.iotplatform.qosbroker.api.datamodel;

public enum SplitPolicy {

	MAX_SPLIT(1), MIN_SPLIT(2);

	private final int value;

	private SplitPolicy(int value) {
		this.value = value;
	}

	public int getCode() {
		return value;
	}

}