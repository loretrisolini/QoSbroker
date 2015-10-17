package it.unipi.iotplatform.qosbroker.api.datamodel;

public class Utilization {

	private String service;
	
	private Double u_ij;

	private Double t_ij;
	private Double p_j;
	
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

	public Double getT_ij() {
		return t_ij;
	}

	public void setT_ij(Double t_ij) {
		this.t_ij = t_ij;
	}

	public Double getP_j() {
		return p_j;
	}

	public void setP_j(Double p_j) {
		this.p_j = p_j;
	}
}
