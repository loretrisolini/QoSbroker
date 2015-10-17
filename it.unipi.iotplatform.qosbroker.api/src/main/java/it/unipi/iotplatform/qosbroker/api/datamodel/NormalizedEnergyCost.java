package it.unipi.iotplatform.qosbroker.api.datamodel;

public class NormalizedEnergyCost {
	
	private String service;
	
	private Double f_ij;

	private Integer h_p_j;
	
	private Double cij_b_i;
	
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

	public Integer getH_p_j() {
		return h_p_j;
	}

	public void setH_p_j(Integer h_p_j) {
		this.h_p_j = h_p_j;
	}

	public Double getCij_b_i() {
		return cij_b_i;
	}

	public void setCij_b_i(Double cij_b_i) {
		this.cij_b_i = cij_b_i;
	}
}
