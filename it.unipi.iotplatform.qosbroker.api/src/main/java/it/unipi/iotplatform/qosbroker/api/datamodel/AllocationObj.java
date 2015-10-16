package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.Arrays;


/* allocation class to store a single service
 * allocation transId,ServId -> tId, tsId */
public class AllocationObj{
	//List<DevId> assigned to a service request
	private String[] devIdList;
	
	private Double[] u_ij;
	private Double[] f_ij;
	private int split;
	
	public AllocationObj(int dim){
		devIdList = new String[dim];
		
		u_ij = new Double[dim];
		f_ij = new Double[dim];
		split = dim;
	}

	public String[] getDevIdList() {
		return devIdList;
	}

	public void setDevIdList(String[] devIdList) {
		this.devIdList = devIdList;
	}

	public Double[] getU_ij() {
		return u_ij;
	}

	public void setU_ij(Double[] u_ij) {
		this.u_ij = u_ij;
	}

	public Double[] getF_ij() {
		return f_ij;
	}

	public void setF_ij(Double[] f_ij) {
		this.f_ij = f_ij;
	}

	public int getSplit() {
		return split;
	}

	public void setSplit(int split) {
		this.split = split;
	}

	@Override
	public String toString(){
		
		String print = new String();
		print += "\nf_ij: "+ Arrays.deepToString(this.getF_ij()) + "\n";
		print += "u_ij: "+ Arrays.deepToString(this.getU_ij()) + "\n";
		print += "split: "+ this.getSplit() + "\n";
		print += "Things allocated: "+ Arrays.deepToString(this.getDevIdList()) + "\n";
		
		return print;
	}
}
