package it.unipi.iotplatform.qosbroker.api.datamodel;

public class Utilization {

	private String service;
	
	private Double u_ij;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Double getU_ij() {
		return u_ij;
	}

	public void setU_ij(Double u_ij) {
		this.u_ij = u_ij;
	}
}
