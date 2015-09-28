package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

public class Request {

	private Integer servId;
	private String service;
	private QoSreq qosRequirements;
	public Integer getServId() {
		return servId;
	}
	public void setServId(Integer servId) {
		this.servId = servId;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public QoSreq getQosRequirements() {
		return qosRequirements;
	}
	public void setQosRequirements(QoSreq qosRequirements) {
		this.qosRequirements = qosRequirements;
	}
}
