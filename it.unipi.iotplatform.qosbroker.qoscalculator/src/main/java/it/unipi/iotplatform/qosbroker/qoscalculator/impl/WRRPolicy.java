package it.unipi.iotplatform.qosbroker.qoscalculator.impl;


import it.unipi.iotplatform.qosbroker.qoscalculator.api.Policy;

import java.util.HashMap;
import java.util.List;

import eu.neclab.iotplatform.iotbroker.commons.Pair;

public class WRRPolicy extends Policy{

	private final HashMap<String, HashMap<String, String>> policyMap;
	private final HashMap<String, HashMap<String, Integer>> howMany;
	
	public WRRPolicy(){
		policyMap = new HashMap<String, HashMap<String, String>>();
		
		howMany = new HashMap<String, HashMap<String, Integer>>();
	}
	
	public String getDevId(String transId, String service, List<Pair<String, Integer>> devIdList){
		
		if(policyMap.get(transId) == null){
			
			policyMap.put(transId, new HashMap<String, String>());
			
			howMany.put(transId, new HashMap<String, Integer>());
			
			policyMap.get(transId).put(service, devIdList.get(0).getLeft());
			
			howMany.get(transId).put(service, 1);
			
			return devIdList.get(0).getLeft();
		}
		
		if(policyMap.get(transId).get(service)==null){
			
			policyMap.get(transId).put(service, devIdList.get(0).getLeft());
			
			howMany.get(transId).put(service, 1);
			
			return devIdList.get(0).getLeft();
		}
		
		
		String lastDevId = policyMap.get(transId).get(service);
		
		for(int i=0; i < devIdList.size(); i++){
			
			int counter = howMany.get(transId).get(service);
			
			if(devIdList.get(i).getLeft().contentEquals(lastDevId) && counter < devIdList.get(i).getRight()){
				
				howMany.get(transId).put(service, new Integer(counter+1));
				
				break;
			}
			else{
				
				policyMap.get(transId).put(service, devIdList.get((i+1%devIdList.size())).getLeft());
				
				howMany.get(transId).put(service, 1);
				
				lastDevId = devIdList.get((i+1%devIdList.size())).getLeft();
				
				break;
			}
		}
		
		return lastDevId;
	}
}
