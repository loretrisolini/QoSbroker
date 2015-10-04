package it.unipi.iotplatform.qosbroker.qoscalculator.impl;

import it.unipi.iotplatform.qosbroker.qoscalculator.api.QoSCalculatorIF;
import it.unipi.iotplatform.qosbroker.qoscalculator.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.qoscalculator.datamodel.ThingAssignmentParams;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.RequestResult;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceAssignments;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceExecutionFeature;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ThingIdThingServiceIdPair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QoSCalculator implements QoSCalculatorIF {

	/**
	 * The Class Reserveobj.
	 */
	private class Reserveobj {
		
		/** The feasible. */
		boolean feasible = false;
		
		/** The z. */
		Double z = 0.0;
		
		HashMap<Integer, ThingAssignmentParams> assignmentsParamsMap;
		
		HashMap<String, AllocationObj> allocationSchema;

		Reserveobj() {
			allocationSchema = new HashMap<>();
		}
		
	}
	
	private class AllocationObj{
		
		String transId_servId;
		
		ArrayList<ThingIdThingServiceIdPair> thingIdThingServiceIdAssignments;
		
		int split;
		
		AllocationObj(){
			thingIdThingServiceIdAssignments = new ArrayList<>();
		}
	}
	
	@Override
	public ReservationResults computeAllocation(
			List<ServiceAssignments> mappingServEqThings,
			HashMap<String, Integer> coefficientMap,
			HashMap<Integer, Thing> thingsMap,
			HashMap<String, RequestResult> requestResultsMap,
			double epsilon) {
		
		Reserveobj[] res = new Reserveobj[3];

		
		res[0] = ABGAP(mappingServEqThings, coefficientMap, thingsMap, epsilon, 0,true);
		
		res[1]= ABGAP(mappingServEqThings, coefficientMap, thingsMap, epsilon, 1, false);

		res[2] = ABGAP(mappingServEqThings, coefficientMap, thingsMap, epsilon, 2, false);

		ReservationResults ret = new ReservationResults();
		int imax=0;
		// Gets the best heuristic
		for(int j=1;j<3;j++)
		{
			if(res[imax].z<res[j].z && res[j].feasible)
				imax = j;
		}
		if(res[imax].feasible){
			ret.setFeas(true);

			//TODO create list<ContextRegistration>
			
			ret.setWhich(imax);
		}
		
		return ret;
	}

	private HashMap<Integer, ThingAssignmentParams> createAssignmentsParamsMap(
			HashMap<Integer, Thing> thingsMap) {
		
		HashMap<Integer, ThingAssignmentParams> assignmentsParamsMap = new HashMap<>();
		
		for(Map.Entry<Integer, Thing> entry : thingsMap.entrySet()){
			
			ThingAssignmentParams thingAssParams = new ThingAssignmentParams();
			
			//set z_i
			thingAssParams.setResidualBattery(entry.getValue().getBatteryLevel()/100);
			
			//set c_i
			thingAssParams.setTotalUtilization(0.0);
			
			assignmentsParamsMap.put(entry.getKey(), thingAssParams);
		}
		
		return assignmentsParamsMap;
	}

	/**
	 * Execute the heuristic specifically tailored for battery consumption.
	 *
	 * @param n the number of things
	 * @param k the number of request
	 * @param p the matrix of battery costs 
	 * @param W the matrix of computational costs
	 * @param b2 the vector of starting battery
	 * @param ts 
	 * @param C 
	 * @param numass 
	 * @param epsilon the tolerance used to stop iterations
	 * @return the reserve object
	 */
	private Reserveobj ABGAP(List<ServiceAssignments> mappingServEqThings,
			HashMap<String, Integer> coefficientMap,
			HashMap<Integer, Thing> thingsMap,
			double epsilon, int priorityIndex, boolean battery){

		Reserveobj res = null;
		double upper = 1.0;
		double lower = 0.0;
		double teta = 0;
		double z = 0;
		System.out.println("teta = "+teta);
		
		boolean[] policy = {true, false};
		
		res = GAP(mappingServEqThings, coefficientMap, thingsMap, priorityIndex, teta, true, policy.clone());
	
		if(res.feasible == true)
		{
			teta = (upper - lower) / 2;
			while((upper - lower) > epsilon)
			{
				System.out.println("teta = "+teta);
//				policy[0] = true;
//				policy[1] = false;
				
				res = GAP(mappingServEqThings, coefficientMap, thingsMap, priorityIndex, teta, true, policy.clone());
	
				if(res.feasible)
				{
					z = teta;
					lower = teta;
					teta = teta + ((upper-lower) / 2 );
				}
				else
				{
					upper = teta;
					teta = teta - ((upper-lower) / 2 );
				}
	
			}
			if(!res.feasible){
				teta = z;
				System.out.println("teta = "+teta);
				
//				policy[0] = true;
//				policy[1] = false;
				
				res = GAP(mappingServEqThings, coefficientMap, thingsMap, priorityIndex, teta, true, policy.clone());
			}
		}
		
		res.z = z;
		return res;
	}

	/**
	 * Real_battery_ gap.
	 *
	 * @param n the n
	 * @param k2 the k
	 * @param ts 
	 * @param F the f
	 * @param W the w
	 * @param B the b
	 * @param C 
	 * @param teta the teta
	 * @return the reserveobj
	 */
	private Reserveobj GAP(List<ServiceAssignments> mappingServEqThings,
			HashMap<String, Integer> coefficientMap,
			HashMap<Integer, Thing> thingsMap,
			int priorityIndex, double teta, boolean battery, boolean[] policy) {
	
		Reserveobj res = new Reserveobj();
		
		List<ServiceAssignments> mappingServEqThingsBck = cloneMappingServEqThings(mappingServEqThings);
		
		HashMap<Integer, ThingAssignmentParams> assignmentsParamsMap = createAssignmentsParamsMap(thingsMap);
		
		//number of services requests
		int k = mappingServEqThings.size();
		//upper bound for utilization
		Double ni = k*(Math.pow(2, 1/k)-1);
		
		res.feasible = true;
		
		double ds, d;
		
		double INF = Double.POSITIVE_INFINITY;

		List<Integer> Fjr;
		
		//iterate over the service requests
		while(res.feasible && !mappingServEqThings.isEmpty()){
			
			ds = -1 * INF;
			
			//temporary allocation object
			//<transId_servId, thingId, thingServiceId>
			AllocationObj allocationTemp = new AllocationObj();
			
			//index to identify the position of the ServiceObject 
			//on which an assignment is executed
			int serviceIndex = 0;
			int j = 0;
			
			//iterate over the equivalent things params for that service request
			for(ServiceAssignments servAssignment: mappingServEqThings){
				
				//map of equivalent thing services associated to the thing
				//with the params <f_ij, u_ij, p_ij>
				HashMap<Integer, ServiceExecutionFeature> equivalentThingsParamsMap = servAssignment.getThingServiceExecFeatureMap();
				
				//id that identify the single service request
				//inside a request composed by a list
				//of service requests
				String[] transId_servId = servAssignment.getTransId_servId().split("_");
				
				//coefficientMap ha as key the transId
				//so i get the first elem of array transId_servId
				List<Integer> Sj = factorization(coefficientMap.get(transId_servId[0]));
				
				while(!res.feasible || !Sj.isEmpty()){
					
					//s_p says to how many things assign the service
					int s_p = chooseFactor(Sj, policy);
					
					//R is used iterate in case of 
					//assignment to multiple things
					int R = s_p;
					
					while(res.feasible && R!=0){
						
						ds = -1 * INF;
						
						for(int r = 0; r<R; r++){

							//List of thingId that satisfy
							//the constraints about
							//utilization and residual battery
							Fjr = checkConstraints(equivalentThingsParamsMap, assignmentsParamsMap, 
																	s_p, ni, teta, null);
							
							if(Fjr.isEmpty()){
								res.feasible = false;
								break;
							}
							
							//get the id of the Thing that have max priority
							Integer tID_maxPriority = getArgMaxPriority(equivalentThingsParamsMap, Fjr, priorityIndex);
							
							//only one thing satisfy the constraints
							//about utilization and residual battery
							if(Fjr.size() == 1){
								d = INF;
							}
							else{
								//difference between the max and the second max priority
								d = getDiffMaxAndSecondMax(tID_maxPriority, equivalentThingsParamsMap, Fjr, priorityIndex);
							}
							
							if(d > ds){
								ds = d;
								
								//allocation of the service given by transId_servId 
								//to a thing with a thingService thingId, thingServiceId
								allocationTemp.transId_servId = servAssignment.getTransId_servId();
								
								ThingIdThingServiceIdPair tId_tsId = new ThingIdThingServiceIdPair();
								tId_tsId.setThingId(tID_maxPriority);
								Integer thingServiceId = equivalentThingsParamsMap.get(tID_maxPriority).getThingServiceId();
								tId_tsId.setThingServiceId(thingServiceId);
								
								allocationTemp.thingIdThingServiceIdAssignments.add(tId_tsId);
								
								//value of the split of the service
								allocationTemp.split = s_p;
								
								//store the index of the service for which
								//a list of things has been assigned
								serviceIndex = j;
							}
							
							Fjr.clear();
						}
						
						if(res.feasible)
							R--;

					}
					//if a feasible allocation is found
					//stop the cicle here
					if(res.feasible) break;
					
					//remove a split factor from the list
					Sj.remove(s_p);
				}
				
				//index of the Service taken in consideration
				j++;
			}
			if(res.feasible){
				
				//update the allocationSchema with the new allocation for the service transId_servId
				res.allocationSchema.put(allocationTemp.transId_servId, allocationTemp);
				
				//update assignments params c_i+(u_ij/s_p), z_i-(f_ij/s_p)
				updateAssignmentsParams(assignmentsParamsMap, 
						mappingServEqThings.get(serviceIndex).getThingServiceExecFeatureMap(), 
						allocationTemp.thingIdThingServiceIdAssignments,
						allocationTemp.split);
				
				
				//remove ServiceAssignments object from the mapping
				mappingServEqThings.remove(serviceIndex);
			}
			else return null;
			
			//Local optimization
			if(!battery && res.feasible){
				
				for(int i = 0; i< mappingServEqThingsBck.size(); i++){
					
					//map of equivalent thing services associated to the thing
					//with the params <f_ij, u_ij, p_ij>
					HashMap<Integer, ServiceExecutionFeature> equivalentThingsParamsMap = mappingServEqThingsBck.get(i).getThingServiceExecFeatureMap();
					
					String transId_servId = mappingServEqThingsBck.get(i).getTransId_servId();
					
					AllocationObj allocation = res.allocationSchema.get(transId_servId);
					
					int split = allocation.split;
					
					for(int s = 0; s<split; s++){
					
						int thingId_sub = allocation.thingIdThingServiceIdAssignments.get(s).getThingId();
						
						Fjr = checkConstraints(equivalentThingsParamsMap, 
								assignmentsParamsMap, split, ni, teta, 
								allocation.thingIdThingServiceIdAssignments.get(s).getThingId());
						
						int thingId_star = getArgMaxResidualBattery(assignmentsParamsMap, Fjr);
						Double maxResidualBattery = assignmentsParamsMap.get(thingId_star).getResidualBattery();
						
						//z_i'-f_i'j
						double residualBatt_sub = assignmentsParamsMap.get(thingId_sub).getResidualBattery();
						
						if(maxResidualBattery > residualBatt_sub){
							
							ThingIdThingServiceIdPair tId_tsId_star = new ThingIdThingServiceIdPair();
							tId_tsId_star.setThingId(thingId_star);
							Integer thingServiceId = equivalentThingsParamsMap.get(thingId_star).getThingServiceId();
							tId_tsId_star.setThingServiceId(thingServiceId);
							
							allocation.thingIdThingServiceIdAssignments.add(tId_tsId_star);
							
							allocation.thingIdThingServiceIdAssignments
										.remove(allocation.thingIdThingServiceIdAssignments.get(s));
							
							res.allocationSchema.put(transId_servId, allocation);
							
							ArrayList<ThingIdThingServiceIdPair> tId_tsIdList = new ArrayList<>();
							ThingIdThingServiceIdPair tId_tsId_sub = new ThingIdThingServiceIdPair();
							tId_tsId_sub.setThingId(thingId_sub);
							tId_tsId_sub.setThingServiceId(thingServiceId);
							tId_tsIdList.add(tId_tsId_sub);
							
							updateAssignmentsParams(assignmentsParamsMap, equivalentThingsParamsMap, tId_tsIdList, -split);
							
							tId_tsIdList.clear();
							tId_tsIdList.add(tId_tsId_star);
							updateAssignmentsParams(assignmentsParamsMap, equivalentThingsParamsMap, tId_tsIdList, split);
						}
					}
					
				}
				
			}
		}
		
		return res;
	}

	/* function to get the thingId to which is associated the max residualBatteryLevel */
	private int getArgMaxResidualBattery(
			HashMap<Integer, ThingAssignmentParams> assignmentsParamsMap,
			List<Integer> Fjr) {
		
		int thingId_maxResidualBattery = 0;
		Double maxResidualBattery = Double.NEGATIVE_INFINITY;
		
		for(Integer thingId: Fjr){
			
			Double residualBattery = assignmentsParamsMap.get(thingId).getResidualBattery();
			if(residualBattery > maxResidualBattery){
				maxResidualBattery = residualBattery;
				
				thingId_maxResidualBattery = thingId;
			}
		}
		
		return thingId_maxResidualBattery;
	}

	/* function to update c_i and z_i */
	private void updateAssignmentsParams(
			HashMap<Integer, ThingAssignmentParams> assignmentsParamsMap,
			HashMap<Integer, ServiceExecutionFeature> thingServiceExecFeatureMap,
			ArrayList<ThingIdThingServiceIdPair> thingIdThingServiceIdAssignments,
			Integer s_p) {
		
		for(ThingIdThingServiceIdPair tId_tsId: thingIdThingServiceIdAssignments){
			
			ThingAssignmentParams assParams = assignmentsParamsMap.get(tId_tsId.getThingId());
			
			double u_ij = thingServiceExecFeatureMap.get(tId_tsId.getThingId()).getUtilization();
			double f_ij = thingServiceExecFeatureMap.get(tId_tsId.getThingId()).getNormalizedEnergyCost();
			
			assParams.setTotalUtilization(assParams.getTotalUtilization() + (u_ij/s_p));
			assParams.setResidualBattery(assParams.getResidualBattery() - (f_ij/s_p));
		}
		
	}

	/* function to get the thingId (in Fjr) for which there is max priority */
	private Integer getArgMaxPriority(
			HashMap<Integer, ServiceExecutionFeature> equivalentThingsParamsMap,
			List<Integer> Fjr, int priorityIndex) {
		
		Double maxPriority = -1 * Double.POSITIVE_INFINITY;
		
		Integer thingId_MaxPriority = 0; 
		
		for(Integer thingId: Fjr){
			
			Double priority = equivalentThingsParamsMap.get(thingId).getPriority().get(priorityIndex);
			
			if(priority > maxPriority){
				maxPriority = priority;
				
				thingId_MaxPriority = thingId;
			}
		}
		
		return thingId_MaxPriority;
	}

	/* function to clone mappingServiceEquivalentThings */
	private List<ServiceAssignments> cloneMappingServEqThings(
			List<ServiceAssignments> mappingServEqThings) {

		List<ServiceAssignments> mappingServEqThingsBck = new ArrayList<>();
		
		for(ServiceAssignments serviceAssignment: mappingServEqThings){
			
			ServiceAssignments servAssBck = new ServiceAssignments();
			
			servAssBck.setTransId_servId(serviceAssignment.getTransId_servId());
			servAssBck.setThingServiceExecFeatureMap(serviceAssignment.getThingServiceExecFeatureMap());
			mappingServEqThingsBck.add(servAssBck);
		}
		
		return mappingServEqThingsBck;
	}

	/* function to get the difference between the max priority and the second max priority */
	private double getDiffMaxAndSecondMax(
			Integer tID_maxPriority,
			HashMap<Integer, ServiceExecutionFeature> equivalentThingsParamsMap,
			List<Integer> Fjr, int priorityIndex) {
		
		Double maxPriority = equivalentThingsParamsMap.get(tID_maxPriority).getPriority().get(priorityIndex);
		
		Fjr.remove(tID_maxPriority);
		
		Double secondMaxPriority = Double.NEGATIVE_INFINITY;
		
		for(Integer thingId: Fjr){
			
			Double priority = equivalentThingsParamsMap.get(thingId).getPriority().get(priorityIndex);
			
			if(priority > secondMaxPriority){
				secondMaxPriority = priority;
			}
		}
		
		return (maxPriority-secondMaxPriority);
	}

	/* function that return the list of things that respect the constraints */
	private List<Integer> checkConstraints(
			HashMap<Integer, ServiceExecutionFeature> equivalentThingsParamsMap,
			HashMap<Integer, ThingAssignmentParams> assignmentsParamsMap,
			int s_p, Double ni, double teta,
			Integer thingIdExcluded) {
		
		List<Integer> Fjr = new ArrayList<>();
		
		for (Map.Entry<Integer, ServiceExecutionFeature> entry : equivalentThingsParamsMap.entrySet()) {
			Integer thingId = entry.getKey();
			
			if(thingIdExcluded != null && thingId.equals(thingIdExcluded)){
				continue;
			}
			
			ServiceExecutionFeature servExecFeat = entry.getValue();
			
			double c_i = assignmentsParamsMap.get(thingId).getTotalUtilization();
			double z_i = assignmentsParamsMap.get(thingId).getResidualBattery();
			double u_ij = servExecFeat.getUtilization()/s_p;
			double f_ij = servExecFeat.getNormalizedEnergyCost()/s_p;
			
			if((c_i+u_ij<ni) && (z_i-f_ij>teta)){
				Fjr.add(thingId);
			}
		}
		
		return Fjr;
	}

	/* function to choose a term from 
	 * the list of factors given a policy */
	private int chooseFactor(List<Integer> sj, boolean[] policy) {
		
		//first time try to assign a service
		//to one sensor
		if(policy[0]){
			policy[0] = false;
			
			return sj.get(0);
		}
		else{
			//if the second term of policy is false
			//start from the max spit
			//otherwise from the min split
			if(policy[1]){
				return sj.get(1);
			}
			else{
				return sj.get((sj.size()-1));
			}
		}
	}

	/* function to compute the factorization of a number */
	private List<Integer> factorization(Integer number) {
		
		int n = number;
	    List<Integer> factors = new ArrayList<Integer>();
	    factors.add(1);
	    for (int i = 2; i <= n; i++) {
	      while (n % i == 0) {
	        factors.add(i);
	        n /= i;
	      }
	    }
	    return factors;
	}

	
}
