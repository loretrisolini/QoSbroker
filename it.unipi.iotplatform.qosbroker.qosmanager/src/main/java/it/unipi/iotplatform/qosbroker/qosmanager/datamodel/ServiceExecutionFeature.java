package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

public class ServiceExecutionFeature {

	//id inside the structure EntityId of the ContRegResp
	//and ContElem
	private String id;
	
	//f_ij
	private double normalizedEnergyCost;
	
	//u_ij
	private double utilization;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getNormalizedEnergyCost() {
		return normalizedEnergyCost;
	}

	public void setNormalizedEnergyCost(double normalizedEnergyCost) {
		this.normalizedEnergyCost = normalizedEnergyCost;
	}

	public double getUtilization() {
		return utilization;
	}

	public void setUtilization(double utilization) {
		this.utilization = utilization;
	}
	
	
}
