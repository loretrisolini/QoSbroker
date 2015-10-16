package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.ArrayList;

/* allocation class to store a single service
 * allocation transId,ServId -> tId, tsId */
public class AllocationObj{
	//List<DevId> assigned to a service request
	private ArrayList<String> devIdList;
	
	private Double u_ij;
	private Double f_ij;
	private int split;
	
	public AllocationObj(){
		devIdList = new ArrayList<>();
		
		u_ij = 0.0;
		f_ij = 0.0;
		split = 0;
	}

	public ArrayList<String> getDevIdList() {
		return devIdList;
	}

	public void setDevIdList(ArrayList<String> devIdList) {
		this.devIdList = devIdList;
	}

	public Double getU_ij() {
		return u_ij;
	}

	public void setU_ij(Double u_ij) {
		this.u_ij = u_ij;
	}

	public Double getF_ij() {
		return f_ij;
	}

	public void setF_ij(Double f_ij) {
		this.f_ij = f_ij;
	}

	public int getSplit() {
		return split;
	}

	public void setSplit(int split) {
		this.split = split;
	}
}
