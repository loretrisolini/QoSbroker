package it.unipi.iotplatform.qosbroker.api.datamodel;

public class ThingAssignmentParams {

	private double totalUtilization;
	
	private double residualBattery;

	public double getTotalUtilization() {
		return totalUtilization;
	}

	public void setTotalUtilization(double totalUtilization) {
		this.totalUtilization = totalUtilization;
	}

	public double getResidualBattery() {
		return residualBattery;
	}

	public void setResidualBattery(double residualBattery) {
		this.residualBattery = residualBattery;
	}
}
