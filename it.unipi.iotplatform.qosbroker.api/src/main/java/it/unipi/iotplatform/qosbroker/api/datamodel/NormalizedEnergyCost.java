package it.unipi.iotplatform.qosbroker.api.datamodel;

public class NormalizedEnergyCost {
	
	private String service;
	
	private Double f_ij;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Double getF_ij() {
		return f_ij;
	}

	public void setF_ij(Double f_ij) {
		this.f_ij = f_ij;
	}
}
