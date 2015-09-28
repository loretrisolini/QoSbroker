package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

public class Service {

	private int servId;
	
	//Service = attr.name
	private String service;

	public int getServId() {
		return servId;
	}

	public void setServId(int servId) {
		this.servId = servId;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
}
