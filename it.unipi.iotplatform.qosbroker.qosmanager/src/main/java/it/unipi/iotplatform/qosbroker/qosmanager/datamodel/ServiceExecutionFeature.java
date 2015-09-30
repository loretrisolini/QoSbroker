package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

/* class that represents the info about the execution of a service
 * on a thing */
public class ServiceExecutionFeature {

	//Id created as ThingId_ThingSrviceId
	//it identifies a thingService on a Thing
	private String featureId;
	
	//f_ij
	private double normalizedEnergyCost;
	
	//u_ij
	private double utilization;

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

	public String getFeatureId() {
		return featureId;
	}

	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}
	
	
}
