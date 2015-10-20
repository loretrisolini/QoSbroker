package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.ArrayList;
import java.util.List;

import eu.neclab.iotplatform.iotbroker.commons.Pair;


/* allocation class to store a single service
 * allocation transId,ServId -> tId, tsId */
public class AllocationInfo{

//	public class AllocationInfo{
//		public String devId;
//		
//		//how many times calls
//		//this thing to
//		//satisfies this service
//		public Integer c_ij_split;
//		
//		Double u_ij;
//		Double f_ij;
//		
//		public AllocationInfo(String devId, Integer c_ij_split, Double u_ij, Double f_ij){
//			this.devId = devId;
//			this.c_ij_split = c_ij_split;
//			this.u_ij = u_ij;
//			this.f_ij = f_ij;
//		}
//	}
	
	//pair thingId and how many times this thing is taken
	private List<Pair<String, Integer>> allocatedThings;
	
	private int split;

	public List<String> getDeviceIdList(){
		List<String> devices = new ArrayList<>();
		
		for(Pair<String, Integer> pair: allocatedThings){
			devices.add(pair.getLeft());
		}
		return devices;
	}
	
	public int getSplit() {
		return split;
	}

	public void setSplit(int split) {
		this.split = split;
	}

	@Override
	public String toString(){
		
		String print = "Split Factor: "+String.valueOf(this.split)+"\n";
		
		for(Pair<String, Integer> allocInfo: allocatedThings){
			print+="devId: "+allocInfo.getLeft()+"\n";
			print+="c_ij_split: "+allocInfo.getRight()+"\n";
			print+="\n";
		}
		
		print+="\n";
		
		return print;
	}

	public List<Pair<String, Integer>> getAllocatedThings() {
		return allocatedThings;
	}

	public void setAllocatedThings(List<Pair<String, Integer>> allocatedThings) {
		this.allocatedThings = allocatedThings;
	}
}
