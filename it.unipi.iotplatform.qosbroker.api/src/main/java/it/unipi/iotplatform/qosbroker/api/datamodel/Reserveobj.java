package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.HashMap;

/* class that represents the inputs and the result of a single iteration of ABGAP algorithm */
public class Reserveobj {
	
	//Map<transId, Map<servName, AllocationInfo>>>
	private HashMap<String, HashMap<String, AllocationInfo>> allocationSchema;
	
	//Map<transId, operationType>
	private HashMap<String, String> transId_opType;
	
	//feasible
	private boolean feasible = false;
	//theta value
	private Double theta = 0.0;
	
	//status of the execution of the heuristic
	private String operationStatus;
	
	private Priority priority;
	private AllocationPolicy allocPolicy; 

	//Map<devId, <c_i, z_i>>
	//result of heuristic for totalUtilization and totalResidualBattery
	private HashMap<String, ThingAssignmentParams> assignmentsParamsMap;
	
	public Reserveobj(){
		allocationSchema = new HashMap<String, HashMap<String, AllocationInfo>>();
		
		transId_opType = new HashMap<String, String>();
	}
	
	public void addTransactionIdOperationType(String transId, String opType){
		this.transId_opType.put(transId, opType);
	}
	
	public void addAllocation(String transId, String serviceName, AllocationInfo allocation){
		
		if(transId!=null && this.allocationSchema.get(transId) == null){
			this.allocationSchema.put(transId, new HashMap<String, AllocationInfo>());
		}
		
		this.allocationSchema.get(transId).put(serviceName, allocation);
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

	public AllocationPolicy getAllocPolicy() {
		return allocPolicy;
	}

	public void setAllocPolicy(AllocationPolicy allocPolicy) {
		this.allocPolicy = allocPolicy;
	}

	public HashMap<String, HashMap<String, AllocationInfo>> getAllocationSchema() {
		return allocationSchema;
	}

	public void setAllocationSchema(
			HashMap<String, HashMap<String, AllocationInfo>> allocationSchema) {
		this.allocationSchema = allocationSchema;
	}

	public HashMap<String, String> getTransId_opType() {
		return transId_opType;
	}

	public void setTransId_opType(HashMap<String, String> transId_opType) {
		this.transId_opType = transId_opType;
	}
	

	
}