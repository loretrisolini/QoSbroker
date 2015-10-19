package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.HashMap;

/* class that represents the inputs and the result of a single iteration of ABGAP algorithm */
public class Reserveobj {
	
	//Map<transId, Map<servName, AllocationObj>>>
	private HashMap<String, HashMap<String, AllocationObj>> allocationSchema;
	
	//Map<transId, operationType>
	private HashMap<String, String> transId_opType;
	
	//feasible
	private boolean feasible = false;
	//theta value
	private Double theta = 0.0;
	
	//status of the execution of the heuristic
	private String operationStatus;
	
	private Priority priority;
	private SplitPolicy splitPolicy;
	private AllocationPolicy allocPolicy; 

	//Map<devId, <c_i, z_i>>
	//result of heuristic for totalUtilization and totalResidualBattery
	private HashMap<String, ThingAssignmentParams> assignmentsParamsMap;
	
	public Reserveobj(){
		allocationSchema = new HashMap<String, HashMap<String, AllocationObj>>();
		
		transId_opType = new HashMap<String, String>();
	}
	
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public HashMap<String, ThingAssignmentParams> getAssignmentsParamsMap() {
		return assignmentsParamsMap;
	}
	public void setAssignmentsParamsMap(
			HashMap<String, ThingAssignmentParams> assignmentsParamsMap) {
		this.assignmentsParamsMap = assignmentsParamsMap;
	}

	public boolean isFeasible() {
		return feasible;
	}

	public void setFeasible(boolean feasible) {
		this.feasible = feasible;
	}

	public Double getTheta() {
		return theta;
	}

	public void setTheta(Double theta) {
		this.theta = theta;
	}

	public String getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}


	public SplitPolicy getSplitPolicy() {
		return splitPolicy;
	}

	public void setSplitPolicy(SplitPolicy splitPolicy) {
		this.splitPolicy = splitPolicy;
	}

	public AllocationPolicy getAllocPolicy() {
		return allocPolicy;
	}

	public void setAllocPolicy(AllocationPolicy allocPolicy) {
		this.allocPolicy = allocPolicy;
	}

	public HashMap<String, HashMap<String, AllocationObj>> getAllocationSchema() {
		return allocationSchema;
	}

	public void setAllocationSchema(
			HashMap<String, HashMap<String, AllocationObj>> allocationSchema) {
		this.allocationSchema = allocationSchema;
	}

	public HashMap<String, String> getTransId_opType() {
		return transId_opType;
	}

	public void setTransId_opType(HashMap<String, String> transId_opType) {
		this.transId_opType = transId_opType;
	}
	

	
}