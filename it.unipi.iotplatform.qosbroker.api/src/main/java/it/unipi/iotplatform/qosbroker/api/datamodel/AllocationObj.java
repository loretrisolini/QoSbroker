package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.ArrayList;
import java.util.List;


/* allocation class to store a single service
 * allocation transId,ServId -> tId, tsId */
public class AllocationObj{

	public class AllocationInfo{
		public String devId;
		
		//how many times calls
		//this thing to
		//satisfies this service
		public Integer c_ij_split;
		
		Double u_ij;
		Double f_ij;
		
		public AllocationInfo(String devId, Integer c_ij_split, Double u_ij, Double f_ij){
			this.devId = devId;
			this.c_ij_split = c_ij_split;
			this.u_ij = u_ij;
			this.f_ij = f_ij;
		}
	}
	
	//pair thingId and how many times this thing is taken
	private List<AllocationInfo> deviceList;
	private int split;

	public void addDevice(String devId, Integer c_ij_split, Double u_ij, Double f_ij){
		deviceList.add(new AllocationInfo(devId, c_ij_split, u_ij, f_ij));
	}

	public List<String> getDeviceIdList(){
		List<String> devices = new ArrayList<>();
		
		for(AllocationInfo assObj: deviceList){
			devices.add(assObj.devId);
		}
		return devices;
	}
	
	public List<AllocationInfo> getDeviceAllocaInfoList(){
		return deviceList;
	}
	
	public int getSplit() {
		return split;
	}

	public void setSplit(int split) {
		this.split = split;
	}

	public List<AllocationInfo> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<AllocationInfo> deviceList) {
		this.deviceList = deviceList;
	}

	@Override
	public String toString(){
		
		String print = "Split Factor: "+String.valueOf(this.split)+"\n";
		
		for(AllocationInfo allocInfo: deviceList){
			print+="devId: "+allocInfo.devId+"\n";
			print+="c_ij_split: "+allocInfo.c_ij_split+"\n";
			print+="u_ij: "+allocInfo.u_ij+"\n";
			print+="f_ij: "+allocInfo.f_ij+"\n";
			print+="\n";
		}
		
		print+="\n";
		
		return print;
	}
}
