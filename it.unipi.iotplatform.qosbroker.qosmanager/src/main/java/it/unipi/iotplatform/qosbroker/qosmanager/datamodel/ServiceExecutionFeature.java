package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

/* class that represents the info about the execution of a service
 * on a thing */
public class ServiceExecutionFeature {

	//it identifies a thingService on a Thing
	private Integer thingServiceId;
	
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

	public Integer getThingServiceId() {
		return thingServiceId;
	}

	public void setThingServiceId(Integer thingServiceId) {
		this.thingServiceId = thingServiceId;
	}	
	
}
