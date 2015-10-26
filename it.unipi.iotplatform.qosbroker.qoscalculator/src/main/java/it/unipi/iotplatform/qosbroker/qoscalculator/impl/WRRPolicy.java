package it.unipi.iotplatform.qosbroker.qoscalculator.impl;


import it.unipi.iotplatform.qosbroker.qoscalculator.api.Policy;

import java.util.HashMap;
import java.util.List;

import eu.neclab.iotplatform.iotbroker.commons.Pair;

public class WRRPolicy extends Policy{

	//Map<transId, Map<service, lastDevId>>
	private final HashMap<String, HashMap<String, String>> policyMap;
	
	//Map<transId, Map<service, counterLastDevIdGetted>>
	private final HashMap<String, HashMap<String, Integer>> howMany;
	
	public WRRPolicy(){
		policyMap = new HashMap<String, HashMap<String, String>>();
		
		howMany = new HashMap<String, HashMap<String, Integer>>();
	}
	
	//taken transId, service and List<<devId, w_ij>> listThingW_ij
	//return the devId to which send the request
	public String getDevId(String transId, String service, List<Pair<String, Integer>> devIdList){
		
		//request never arrived for that transId, service
		if(policyMap.get(transId) == null){
			
			policyMap.put(transId, new HashMap<String, String>());
			
			howMany.put(transId, new HashMap<String, Integer>());
			
			//put the service with the associated devId
			policyMap.get(transId).put(service, devIdList.get(0).getLeft());
			
			//put the counter to one for that service
			howMany.get(transId).put(service, 1);
			
			return devIdList.get(0).getLeft();
		}
		
		//request never arrived for that service
		if(policyMap.get(transId).get(service)==null){
			
			//put the devId for that service
			policyMap.get(transId).put(service, devIdList.get(0).getLeft());
			
			//put the counter to one for that service
			howMany.get(transId).put(service, 1);
			
			return devIdList.get(0).getLeft();
		}
		
		//get the lastDevId for that transId, service
		String lastDevId = policyMap.get(transId).get(service);
		
		for(int i=0; i < devIdList.size(); i++){
			
			//get how many times I have taken
			//that devId for satisfied the request
			int counter = howMany.get(transId).get(service);
			
			//search for the devId in the list that match with lastDevId
			//so i take the pair <devId, w_ij>
			if(devIdList.get(i).getLeft().contentEquals(lastDevId) && counter < devIdList.get(i).getRight()){
				
				howMany.get(transId).put(service, new Integer(counter+1));
				
				break;
			}
			else{
				
				policyMap.get(transId).put(service, devIdList.get(((i+1)%devIdList.size())).getLeft());
				
				howMany.get(transId).put(service, 1);
				
				lastDevId = devIdList.get(((i+1)%devIdList.size())).getLeft();
				
				break;
			}
		}
		
		//return the devId to which
		//send the request
		return lastDevId;
	}
}
