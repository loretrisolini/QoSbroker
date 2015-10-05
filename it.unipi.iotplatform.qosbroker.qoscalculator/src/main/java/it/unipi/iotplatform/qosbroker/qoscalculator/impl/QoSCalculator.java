package it.unipi.iotplatform.qosbroker.qoscalculator.impl;

import it.unipi.iotplatform.qosbroker.qoscalculator.api.QoSCalculatorIF;
import it.unipi.iotplatform.qosbroker.qoscalculator.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.qoscalculator.datamodel.ThingAssignmentParams;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.RequestResult;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.Service;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceAssignments;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceExecutionFeature;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ThingIdThingServiceIdPair;
import it.unipi.iotplatform.qosbroker.qosmanager.impl.QoSBrokerCore;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eu.neclab.iotplatform.ngsi.api.datamodel.ContextMetadata;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistration;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistrationAttribute;
import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;

public class QoSCalculator implements QoSCalculatorIF {

	/** The logger. */
	private static Logger logger = Logger.getLogger(QoSCalculator.class);
	
	/**
	 * The Class Reserveobj.
	 */
	private class Reserveobj {
		
		/** The feasible. */
		boolean feasible = false;
		
		/** The z. */
		Double z = 0.0;
		
		HashMap<Integer, ThingAssignmentParams> assignmentsParamsMap;
		
		//Map<transId, Map<ServId, List<tId_tsId>>>
		HashMap<String, HashMap<Integer, AllocationObj>> allocationSchema;

		Reserveobj() {
			allocationSchema = new HashMap<>();
		}
		
	}
	
	private class AllocationObj{
		
		String transId;
		
		Integer servId;
		
		ArrayList<ThingIdThingServiceIdPair> thingIdThingServiceIdAssignments;
		
		int split;
		
		AllocationObj(){
			thingIdThingServiceIdAssignments = new ArrayList<>();
		}
	}
	
	/**
	 * @param k the number of requests
	 * @param totalRequestedServicesMap all the requestService Names for each servId given a transId
	 * @param totalThingsMap the total number of equivalentThings for each service
	 * @param mappingServEqThings Map<transId, List<servId, Map<thingId, <thingServiceId, f_ij, u_ij, prioriy[]>>>>
	 * @param coefficientMap Map<transId, h/p_j>
	 * @param epsilon the tolerance used to stop iterations
	 * @return the ReservationResults object
	 */
	@Override
	public ReservationResults computeAllocation(
			int k,
			HashMap<String, HashMap<Integer, String>> totalRequestedServicesMap,
			HashMap<Integer, Thing> totalThingsMap,
			HashMap<String, List<ServiceAssignments>> mappingServEqThings,
			HashMap<String, Integer> coefficientMap,
			double epsilon) {
		
		Reserveobj[] res = new Reserveobj[3];
		
		//execution with p_ij=f_ij
		res[0] = ABGAP(k, mappingServEqThings, coefficientMap, totalThingsMap, epsilon, 0,true);
		
		//execution with p_ij=u_ij
		res[1]= ABGAP(k, mappingServEqThings, coefficientMap, totalThingsMap, epsilon, 1, false);

		//execution with p_ij=random_double
		res[2] = ABGAP(k, mappingServEqThings, coefficientMap, totalThingsMap, epsilon, 2, false);

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

			List<ContextRegistration> ngsiAllocationSchema = createNgsiAllocationSchema(res[imax], totalThingsMap, totalRequestedServicesMap);
			
			ret.setAllocationSchema(ngsiAllocationSchema);
			
			ret.setWhich(imax);
		}
		
		return ret;
	}

	

	/**
	 * Execute the heuristic specifically tailored for battery consumption.
	 *
	 * @param k the number of request
	 * @param mappingServEqThings contains the P, F, U matrix
	 * @param coefficientMap Map<transId, h/p_j>
	 * @param totalThingsMap the vector of starting battery
	 * @param epsilon the tolerance used to stop iterations
	 * @param priorityIndex say which value using as priority p_ij
	 * @param indicates if it has to be done the local optimization
	 * @return the Reserveobj object
	 */
	private Reserveobj ABGAP(
			int k,
			HashMap<String, List<ServiceAssignments>> mappingServEqThings,
			HashMap<String, Integer> coefficientMap,
			HashMap<Integer, Thing> totalThingsMap,
			double epsilon, int priorityIndex, boolean battery){

		Reserveobj res = null;
		double upper = 1.0;
		double lower = 0.0;
		double teta = 0;
		double z = 0;
		System.out.println("teta = "+teta);
		
		//indicates the policy to follow
		//the first element says that the allocation must start
		//with one service per thing
		//the second indicates if the policy is max split or min split
		boolean[] policy = {true, false};
		
		res = GAP(k, mappingServEqThings, coefficientMap, totalThingsMap, priorityIndex, teta, true, policy.clone());
	
		if(res.feasible == true)
		{
			teta = (upper - lower) / 2;
			while((upper - lower) > epsilon)
			{
				System.out.println("teta = "+teta);
//				policy[0] = true;
//				policy[1] = false;
				
				res = GAP(k, mappingServEqThings, coefficientMap, totalThingsMap, priorityIndex, teta, true, policy.clone());
	
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
				
				res = GAP(k, mappingServEqThings, coefficientMap, totalThingsMap, priorityIndex, teta, true, policy.clone());
			}
		}
		
		res.z = z;
		return res;
	}

	/**
	 * Real_battery_ gap.
	 *
	 * @param k the number of requests
	 * @param mappingServEqThings represents F,U,P matrix 
	 * @param coefficientMap Map<thingId, h/p_j>
	 * @param totalThingsMap Map<thingId, Thing>
	 * @param priorityIndex indicates wht priority to use
	 * @param teta the teta
	 * @param battery says if it must be done the local optimization
	 * @param policy says how to split
	 * @return the reserveobj
	 */
	private Reserveobj GAP(
			int k,
			HashMap<String, List<ServiceAssignments>> mappingServEqThings,
			HashMap<String, Integer> coefficientMap,
			HashMap<Integer, Thing> totalThingsMap,
			int priorityIndex, double teta, boolean battery, boolean[] policy) {
	
		Reserveobj res = new Reserveobj();
		
		//Backup of the mapping of services and equivalent things
		HashMap<String, List<ServiceAssignments>> mappingServEqThingsBck = cloneMappingServEqThings(mappingServEqThings);
		
		//create Map<thingId, <c_i, z_i>> from the Map<thingId, Thing>
		HashMap<Integer, ThingAssignmentParams> assignmentsParamsMap = createAssignmentsParamsMap(totalThingsMap);
		
		//upper bound for utilization
		Double ni = k*(Math.pow(2, 1/k)-1);
		
		res.feasible = true;
		
		double ds, d;
		
		double INF = Double.POSITIVE_INFINITY;

		//List of thingIds that satisfy the constraints
		List<Integer> Fjr;
		
		//iterate over the service requests
		while(res.feasible && !mappingServEqThings.isEmpty()){
			
			ds = -1 * INF;
			
			//temporary allocation object
			//<transId, servId, thingId, thingServiceId>
			AllocationObj allocationTemp = new AllocationObj();
			
			//index to identify the position of the ServiceObject 
			//on which an assignment is executed
			int serviceIndex = 0;
			int j = 0;
			
			//iterate over the list of request identify by transId
			for(Map.Entry<String, List<ServiceAssignments>> entry : mappingServEqThings.entrySet()){
				
				//get the transaId that identify a request with multiple
				//service requests
				String transId = entry.getKey();
				
				logger.debug("request with TransId: "+transId);
				
				//Get the list of <servId, Map<thingId, <thingServiceId, f_ij, u_ij, p[]>>>
				List<ServiceAssignments> servAssignmentsList = entry.getValue();
				
				//iterate over the equivalent things-thingService params <f_ij, u_ij, p[]> 
				//for that service request
				for(ServiceAssignments servAssignment: servAssignmentsList){
					
					//map<thingId, ServiceExecutionFeature> of equivalent thing services associated to the thing
					//with the params <f_ij, u_ij, p_ij>
					HashMap<Integer, ServiceExecutionFeature> equivalentThingsParamsMap = servAssignment.getThingServiceExecFeatureMap();
					
					//id that identify the single service request
					//inside a request composed by a list
					//of service requests
					Integer servId = servAssignment.getServId();
					
					logger.debug("ServiceRequest with servId: "+servId.toString()+
									"inside request with TransId: "+transId);
					
					//coefficientMap ha as key the transId
					//so i get the first elem of array transId_servId
					List<Integer> Sj = factorization(coefficientMap.get(transId));
					
					//iterate over the list of split factors
					//of a service on multiple things
					while(!res.feasible || !Sj.isEmpty()){
						
						//s_p says to how many things assign the service
						int s_p = chooseFactor(Sj, policy);
						logger.debug("split= "+String.valueOf(s_p));
						
						//R is used iterate in case of 
						//assignment to multiple things
						int R = s_p;
						
						//iterate over the R that indicates the number
						//of suballocation for the service servId
						while(res.feasible && R!=0){
							
							ds = -1 * INF;
							
							for(int r = 0; r<R; r++){
	
								//List of thingId that satisfy
								//the constraints about
								//utilization and residual battery
								Fjr = checkConstraints(equivalentThingsParamsMap, assignmentsParamsMap, 
																		s_p, ni, teta, null);
								
								//there is no thing that satisfy the
								//requirements
								if(Fjr.isEmpty()){
									
									logger.debug("Fjr is empty");
									
									res.feasible = false;
									break;
								}
								
								//get the id of the Thing that have max priority
								Integer tID_maxPriority = getArgMaxPriority(equivalentThingsParamsMap, Fjr, priorityIndex);
								
								logger.debug("thingId with max priority: "+tID_maxPriority.toString());
								
								//only one thing satisfy the constraints
								//about utilization and residual battery
								if(Fjr.size() == 1){
									d = INF;
								}
								else{
									//difference between the max and the second max priority
									d = getDiffMaxAndSecondMax(tID_maxPriority, equivalentThingsParamsMap, Fjr, priorityIndex);
									
									logger.debug("difference d="+String.valueOf(d));
								}
								
								if(d > ds){
									ds = d;
									
									//allocation of the service given by transId, servId 
									//to a thing with a thingService thingId, thingServiceId
									allocationTemp.transId = transId;
									allocationTemp.servId = servId;
									
									ThingIdThingServiceIdPair tId_tsId = new ThingIdThingServiceIdPair();
									tId_tsId.setThingId(tID_maxPriority);
									Integer thingServiceId = equivalentThingsParamsMap.get(tID_maxPriority).getThingServiceId();
									tId_tsId.setThingServiceId(thingServiceId);
									
									//take the pair thingId, thingServiceId to which allocate the service
									//with servId in the request with transId
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
			}
				
			if(res.feasible){
				
				if(res.allocationSchema.get(allocationTemp.transId) == null){
					res.allocationSchema.put(allocationTemp.transId, new HashMap<Integer, AllocationObj>());
				}
				
				//update the allocationSchema with the new allocation for the service servId
				//Map<transId, Map<servId, List<tId_tsId>>
				res.allocationSchema.get(allocationTemp.transId).put(allocationTemp.servId, allocationTemp);
				
				logger.debug("alloction of service request with transId="+allocationTemp.transId+
								"and servId="+allocationTemp.servId.toString());
				logger.debug("allocated with a spli factor "+String.valueOf(allocationTemp.split));
				
				//update assignments params c_i+(u_ij/s_p), z_i-(f_ij/s_p)
				updateAssignmentsParams(assignmentsParamsMap, 
						mappingServEqThings.get(allocationTemp.transId).get(serviceIndex).getThingServiceExecFeatureMap(), 
						allocationTemp.thingIdThingServiceIdAssignments,
						allocationTemp.split);
				
				
				//remove ServiceAssignments object from the mapping
				mappingServEqThings.get(allocationTemp.transId).remove(serviceIndex);
			}
			else return res;
			
			//Local optimization
			if(!battery && res.feasible){
				
				logger.debug("Local Optimization");
				
				//itearate over the list <transId, List<servId, Map<thingId, <tsId, f_ij, u_ij, p[]>>>>
				for(Map.Entry<String, List<ServiceAssignments>> entry : mappingServEqThingsBck.entrySet()){
					
					//get the transId with which the request is identified 
					String transId = entry.getKey();
					List<ServiceAssignments> servAssignmentList = entry.getValue();
					
					//iterate over the list <servId, Map<thingId, <tsId, f_ij, u_ij, p[]>>
					for(ServiceAssignments servAssignment: servAssignmentList){
						
						//map of equivalent thing services associated to the thing
						//with the params <f_ij, u_ij, p_ij>
						HashMap<Integer, ServiceExecutionFeature> equivalentThingsParamsMap = servAssignment.getThingServiceExecFeatureMap();
						
						//get the servId of the requested service
						Integer servId = servAssignment.getServId();
						
						//get the Allocation associated to the pair transId, servId
						AllocationObj allocation = res.allocationSchema.get(transId).get(servId);
						
						//get the split factor for that allocation
						int split = allocation.split;
						
						logger.debug("split factor="+String.valueOf(split));
						
						for(int s = 0; s<split; s++){
						
							int thingId_sub = allocation.thingIdThingServiceIdAssignments.get(s).getThingId();
							
							logger.debug("try to substitute the thing with id="+String.valueOf(thingId_sub));
							
							//check the constraints excluding the thing with id thingId_sub
							Fjr = checkConstraints(equivalentThingsParamsMap, 
									assignmentsParamsMap, split, ni, teta, 
									allocation.thingIdThingServiceIdAssignments.get(s).getThingId());
							
							//get the id (from Fjr) of the thing with max residualBattery
							int thingId_star = getArgMaxResidualBattery(assignmentsParamsMap, Fjr);
							
							logger.debug("thingId* with max residual battery="+String.valueOf(thingId_star));
							
							//get the max residual battery value
							Double maxResidualBattery = assignmentsParamsMap.get(thingId_star).getResidualBattery();
							logger.debug("max residual battery value="+String.valueOf(maxResidualBattery));
							
							//z_i'-f_i'j residual battery value of the thing to substitute
							double residualBatt_sub = assignmentsParamsMap.get(thingId_sub).getResidualBattery();
							logger.debug("residual battery value of thing to substitute="+String.valueOf(residualBatt_sub));
							
							//if the max value of residual battery is greater than
							//the value of residual battery of the thing to substitute
							//there is a change in the allocation
							if(maxResidualBattery > residualBatt_sub){
								
								//create a new pair tId_tsId for the thingId*
								//with the max residual battery value
								ThingIdThingServiceIdPair tId_tsId_star = new ThingIdThingServiceIdPair();
								tId_tsId_star.setThingId(thingId_star);
								Integer thingServiceId = equivalentThingsParamsMap.get(thingId_star).getThingServiceId();
								tId_tsId_star.setThingServiceId(thingServiceId);
								
								allocation.thingIdThingServiceIdAssignments.add(tId_tsId_star);
								
								//remove the old thing substituted
								allocation.thingIdThingServiceIdAssignments
											.remove(allocation.thingIdThingServiceIdAssignments.get(s));
								
								//update the allocation in reservation object
								res.allocationSchema.get(transId).put(servId, allocation);
								
								//update the value of the thing substituted
								//c_i-(u_ij/split) and z_i+(f_ij/split)
								//using -split
								ArrayList<ThingIdThingServiceIdPair> tId_tsIdList = new ArrayList<>();
								ThingIdThingServiceIdPair tId_tsId_sub = new ThingIdThingServiceIdPair();
								tId_tsId_sub.setThingId(thingId_sub);
								tId_tsId_sub.setThingServiceId(thingServiceId);
								tId_tsIdList.add(tId_tsId_sub);
								updateAssignmentsParams(assignmentsParamsMap, equivalentThingsParamsMap, tId_tsIdList, -split);
								
								//update the value of the new thing allocated
								//c_i+(u_ij/split) and z_i-(f_ij/split)
								tId_tsIdList.clear();
								tId_tsIdList.add(tId_tsId_star);
								updateAssignmentsParams(assignmentsParamsMap, equivalentThingsParamsMap, tId_tsIdList, split);
							}
						}
					}
					
				}
				
			}
		}
		
		return res;
	}

	/* function to create the ngsi allocation schema from the Reserveobj object */
	private List<ContextRegistration> createNgsiAllocationSchema(
			Reserveobj reserveobj, HashMap<Integer, Thing> totalThingsMap, 			
			HashMap<String, HashMap<Integer, String>> totalRequestedServicesMap) {
		
		HashMap<String, HashMap<Integer, AllocationObj>> allocationSchema = reserveobj.allocationSchema;
		
		List<ContextRegistration> contRegList = new ArrayList<>();
		
		//for each transId
		for(Map.Entry<String, HashMap<Integer, AllocationObj>> entry : allocationSchema.entrySet()){
			
			String transId = entry.getKey();
			ContextRegistration contReg = new ContextRegistration();
			
			List<EntityId> entityIdList = new ArrayList<>();
			EntityId entId = new EntityId();
			entId.setId(transId);
			entId.setIsPattern(false);
			entId.setType(URI.create("Allocation"));
			entityIdList.add(entId);
			
			contReg.setListEntityId(entityIdList);
			
			HashMap<Integer, AllocationObj> servicesAllocation = entry.getValue();
			List<ContextRegistrationAttribute> contRegAttrList = new ArrayList<>();
			
			ContextRegistrationAttribute contRegAttr = new ContextRegistrationAttribute();
			
			//for each servId in a request identify by transId
			for(Map.Entry<Integer, AllocationObj> entryAllocation : servicesAllocation.entrySet()){
				
				Integer servId = entryAllocation.getKey();
				String serviceName = totalRequestedServicesMap.get(transId)
										.get(servId);
				
				contRegAttr.setName(serviceName);
				contRegAttr.setType(URI.create("service"));
				
				List<ContextMetadata> contMetadataList = new ArrayList<>();
				
				List<ThingIdThingServiceIdPair> allocationList = 
						entryAllocation.getValue().thingIdThingServiceIdAssignments;
				for(ThingIdThingServiceIdPair tId_tsId: allocationList){
					
					ContextMetadata contMetadata = new ContextMetadata();
					
					String contextEntityId = totalThingsMap.get(tId_tsId.getThingId()).getContextEntityId();
					String attrName = totalThingsMap.get(tId_tsId.getThingId()).getThingServices()
										.get(tId_tsId.getThingServiceId()).getServiceName();
					
					contMetadata.setName(String.valueOf(tId_tsId.getThingId()));
					contMetadata.setType(URI.create("string"));
					contMetadata.setValue(contextEntityId+"::"+attrName);
					
					contMetadataList.add(contMetadata);
				}
				
				contRegAttr.setMetaData(contMetadataList);
			}
			
			contRegAttrList.add(contRegAttr);
			contReg.setListContextRegistrationAttribute(contRegAttrList);
			contRegList.add(contReg);
		}
		
		return contRegList;
	}

	/* function to create the Map<thingId, <c_i, z_i>> given the Map<thingId, Thing> */
	private HashMap<Integer, ThingAssignmentParams> createAssignmentsParamsMap(
			HashMap<Integer, Thing> thingsMap) {
		
		HashMap<Integer, ThingAssignmentParams> assignmentsParamsMap = new HashMap<>();
		
		for(Map.Entry<Integer, Thing> entry : thingsMap.entrySet()){
			
			ThingAssignmentParams thingAssParams = new ThingAssignmentParams();
			
			//set z_i using the residual battery of the thing
			thingAssParams.setResidualBattery(entry.getValue().getBatteryLevel()/100);
			
			//set c_i
			thingAssParams.setTotalUtilization(0.0);
			
			assignmentsParamsMap.put(entry.getKey(), thingAssParams);
		}
		
		return assignmentsParamsMap;
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

	/* function to update c_i and z_i, given Map<thingId, <c_i, z_i>>,
	 * Map<thingId, <thingServiceId, f_ij, u_ij, p[]>>,
	 * List<thingId, thingServiceId>, the split */
	private void updateAssignmentsParams(
			HashMap<Integer, ThingAssignmentParams> assignmentsParamsMap,
			HashMap<Integer, ServiceExecutionFeature> thingServiceExecFeatureMap,
			ArrayList<ThingIdThingServiceIdPair> thingIdThingServiceIdAssignments,
			Integer s_p) {
		
		for(ThingIdThingServiceIdPair tId_tsId: thingIdThingServiceIdAssignments){
			
			logger.debug("update <c_i,z_i> of the thing with id "+tId_tsId.getThingId().toString());
			
			ThingAssignmentParams assParams = assignmentsParamsMap.get(tId_tsId.getThingId());
			
			double u_ij = thingServiceExecFeatureMap.get(tId_tsId.getThingId()).getUtilization();
			double f_ij = thingServiceExecFeatureMap.get(tId_tsId.getThingId()).getNormalizedEnergyCost();
			
			logger.debug("u_ij/s_p="+String.valueOf(u_ij/s_p));
			logger.debug("f_ij/s_p="+String.valueOf(f_ij/s_p));
			
			assParams.setTotalUtilization(assParams.getTotalUtilization() + (u_ij/s_p));
			assParams.setResidualBattery(assParams.getResidualBattery() - (f_ij/s_p));
			
			logger.debug("new c_i="+String.valueOf(assParams.getTotalUtilization()));
			logger.debug("new c_i="+String.valueOf(assParams.getResidualBattery()));
		}
	}

	/* function to get the thingId (in Fjr) for which there is max priority given
	 * Map<thingId, <thingServiceId, f_ij, u_ij, p[]>>, Fjr, priorityIndex that
	 * says what priority to take in consideration */
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
	private HashMap<String, List<ServiceAssignments>> cloneMappingServEqThings(
			HashMap<String, List<ServiceAssignments>> mappingServEqThings) {

		HashMap<String, List<ServiceAssignments>> mappingServEqThingsBck = new HashMap<>();
		
		for(Map.Entry<String, List<ServiceAssignments>> entry : mappingServEqThings.entrySet()){
			
			String transId = entry.getKey();
			List<ServiceAssignments> servAssigmentsList = new ArrayList<>();
			
			for(ServiceAssignments serviceAssignment: entry.getValue()){
				
				ServiceAssignments servAssBck = new ServiceAssignments();
				
				servAssBck.setServId(serviceAssignment.getServId());
				servAssBck.setThingServiceExecFeatureMap(serviceAssignment.getThingServiceExecFeatureMap());
				servAssigmentsList.add(servAssBck);
			}
			
			mappingServEqThingsBck.put(transId, servAssigmentsList);
		}
		
		return mappingServEqThingsBck;
	}

	/* function to get the difference between the max priority and the second max priority, given
	 * thingId with maxPriority, Map<thingId, <thingServiceId, f_ij, u_ij, p[]>>, Fjr,
	 * priorityIndex that says what priority value to take */
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

	/* function that return the list of things that respect the constraints
	 * given Map<thingId, <f_ij, u_ij, p[]>>, Map<thingId, <c_i,z_i>>, split factor s_p,
	 * the upper bound for utilization, and theta for residual battery,
	 * thingIdExcluded indicates the thingId to exclude in the check */
	private List<Integer> checkConstraints(
			HashMap<Integer, ServiceExecutionFeature> equivalentThingsParamsMap,
			HashMap<Integer, ThingAssignmentParams> assignmentsParamsMap,
			int s_p, Double ni, double teta,
			Integer thingIdExcluded) {
		
		logger.debug("checkConstraints to obtain the list of thingId in Fjr array");
		logger.debug("split="+String.valueOf(s_p));
		logger.debug("ni="+String.valueOf(ni));
		logger.debug("teta="+String.valueOf(teta));
		
		List<Integer> Fjr = new ArrayList<>();
		
		for (Map.Entry<Integer, ServiceExecutionFeature> entry : equivalentThingsParamsMap.entrySet()) {
			Integer thingId = entry.getKey();
			
			logger.debug("thingId: "+thingId.toString());
			
			if(thingIdExcluded != null && thingId.equals(thingIdExcluded)){
				continue;
			}
			
			ServiceExecutionFeature servExecFeat = entry.getValue();
			
			double c_i = assignmentsParamsMap.get(thingId).getTotalUtilization();
			double z_i = assignmentsParamsMap.get(thingId).getResidualBattery();
			double u_ij = servExecFeat.getUtilization()/s_p;
			double f_ij = servExecFeat.getNormalizedEnergyCost()/s_p;
			
			logger.debug("c_i= "+String.valueOf(c_i));
			logger.debug("c_i= "+String.valueOf(z_i));
			logger.debug("c_i= "+String.valueOf(u_ij));
			logger.debug("c_i= "+String.valueOf(f_ij));
			
			logger.debug("c_i+u_ij= "+String.valueOf(c_i+u_ij));
			logger.debug("ni= "+String.valueOf(ni));
			
			logger.debug("z_i-f_ij= "+String.valueOf(z_i-f_ij));
			logger.debug("teta= "+String.valueOf(teta));
			
			if((c_i+u_ij<ni) && (z_i-f_ij>teta)){
				
				logger.debug("thingId="+thingId.toString()+"respect the constraints");
				
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
