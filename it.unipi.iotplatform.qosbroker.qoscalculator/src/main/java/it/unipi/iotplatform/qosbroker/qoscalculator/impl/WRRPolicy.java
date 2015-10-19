package it.unipi.iotplatform.qosbroker.qoscalculator.impl;

import it.unipi.iotplatform.qosbroker.api.datamodel.AllocationObj.AllocationInfo;
import it.unipi.iotplatform.qosbroker.qoscalculator.api.Policy;

import java.util.HashMap;
import java.util.List;

public class WRRPolicy extends Policy{

	private final HashMap<String, HashMap<String, String>> policyMap;
	private final HashMap<String, HashMap<String, Integer>> howMany;
	
	public WRRPolicy(){
		policyMap = new HashMap<String, HashMap<String, String>>();
		
		howMany = new HashMap<String, HashMap<String, Integer>>();
	}
	
	public String getDevId(String transId, String service, List<AllocationInfo> devIdList){
		
		if(policyMap.get(transId) == null){
			
			policyMap.put(transId, new HashMap<String, String>());
			
			howMany.put(transId, new HashMap<String, Integer>());
			
			policyMap.get(transId).put(service, devIdList.get(0).devId);
			
			howMany.get(transId).put(service, 1);
			
			return devIdList.get(0).devId;
		}
		
		if(policyMap.get(transId).get(service)==null){
			
			policyMap.get(transId).put(service, devIdList.get(0).devId);
			
			howMany.get(transId).put(service, 1);
			
			return devIdList.get(0).devId;
		}
		
		
		String lastDevId = policyMap.get(transId).get(service);
		
		for(int i=0; i < devIdList.size(); i++){
			
			int counter = howMany.get(transId).get(service);
			
			if(devIdList.get(i).devId.contentEquals(lastDevId) && counter < devIdList.get(i).c_ij_split){
				
				howMany.get(transId).put(service, new Integer(counter+1));
				
				break;
			}
			else{
				
				policyMap.get(transId).put(service, devIdList.get((i+1%devIdList.size())).devId);
				
				howMany.get(transId).put(service, 1);
				
				lastDevId = devIdList.get((i+1%devIdList.size())).devId;
				
				break;
			}
		}
		
		return lastDevId;
	}
}
