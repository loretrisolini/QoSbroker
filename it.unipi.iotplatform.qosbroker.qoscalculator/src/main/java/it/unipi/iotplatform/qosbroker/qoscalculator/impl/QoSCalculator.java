package it.unipi.iotplatform.qosbroker.qoscalculator.impl;

import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServicePeriodParams;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.qoscalculator.api.QoSCalculatorIF;
import it.unipi.iotplatform.qosbroker.qoscalculator.datamodel.ThingAssignmentParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.NgsiStructure;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;
import eu.neclab.iotplatform.ngsi.api.datamodel.Polygon;
import eu.neclab.iotplatform.ngsi.api.datamodel.Vertex;

public class QoSCalculator implements QoSCalculatorIF {

	/** The logger. */
	private static Logger logger = Logger.getLogger(QoSCalculator.class);
	
	//indicates the value of p_ij
	//to use f_ij, u_ij or random value
	private enum Priority{
		BATTERY, UTILIZATION, RANDOM
	};
	
	//indicates the policy to follow
	//to split service to multiple things
	//max split or min split
	private enum Policy{
		MAX_SPLIT, MIN_SPLIT
	}
	
	/**
	 * The Class Reserveobj.
	 */
	private class Reserveobj {
		
		//feasible
		boolean feasible = false;
		
		//theta value
		Double z = 0.0;
		
		//Map<thingId, <c_i, z_i>>
		HashMap<Integer, ThingAssignmentParams> assignmentsParamsMap;
		
		//Map<transId, Map<reqServName, List<devId>>>
		HashMap<String, HashMap<Integer, ThingsIdList>> allocationSchema;

		Reserveobj() {
			allocationSchema = new HashMap<>();

		}
		
	}
	
	/* allocation class to store a single service
	 * allocation transId,ServId -> tId, tsId */
	private class AllocationObj{
		
		String transId;
		
		String serviceName;
		
		//List<DevId> assigned to a service request
		ArrayList<String> devIdList;
		
		Double u_ij;
		Double f_ij;
		int split;
		
		AllocationObj(){
			devIdList = new ArrayList<>();
		}
	}
	
	/**
	 * @param k the number of requests
	 * @param requests all the requests as Pair <transId, Request>
	 * @param servPeriodsMap Map<transId, <p_j, h/p_j>>
	 * @param eqThingInfo Map<devId, Thing>
	 * @param servNameThingsIdList Map<reServName, List<DevId>>
	 * @param epsilon the tolerance used to stop iterations
	 * @return the ReservationResults object
	 */
	@Override
	public ReservationResults computeAllocation(
			int k, List<Pair<String, Request>> requests, 
			HashMap<String, ServicePeriodParams> servPeriodsMap,
			HashMap<String, Thing> eqThingInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList,
			double epsilon) {
		
		Reserveobj[] res = new Reserveobj[3];
		
		
		HashMap<String, List<String>> matrixM = createMatrixM(requests, eqThingInfo, servNameThingsIdList);
		if(matrixM == null){
			
			logger.debug("ERROR: problem with matrix M creation");
			return null;
		}
		
		Priority prio = Priority.BATTERY;
		Policy policy = Policy.MAX_SPLIT;
		//execution with p_ij=f_ij
		res[0] = ABGAP(k, requests, servPeriodsMap, eqThingInfo, servNameThingsIdList, matrixM, epsilon, prio, policy);
		
		prio = Priority.UTILIZATION;
		//execution with p_ij=u_ij
		res[1]= ABGAP(k, requests, servPeriodsMap, eqThingInfo, servNameThingsIdList, matrixM, epsilon, prio, policy);

		prio = Priority.RANDOM;
		//execution with p_ij=random_double
		res[2] = ABGAP(k, requests, servPeriodsMap, eqThingInfo, servNameThingsIdList, matrixM, epsilon, prio, policy);

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

////			List<ContextRegistration> ngsiAllocationSchema = createNgsiAllocationSchema(res[imax], totalThingsMap, totalRequestedServicesMap);
//			
////			ret.setAllocationSchema(ngsiAllocationSchema);
////			
////			ret.setWhich(imax);
		}
		
		return ret;
	}

	

	/**
	 * Execute the heuristic specifically tailored for battery consumption.
	 *
	 * @param k the number of request
	 * @param requests all the requests as Pair <transId, Request>
	 * @param servPeriodsMap Map<transId, <p_j, h/p_j>>
	 * @param eqThingInfo Map<devId, Thing>
	 * @param servNameThingsIdList Map<reServName, List<DevId>>
	 * @param epsilon the tolerance used to stop iterations
	 * @param prio say which value using as priority p_ij
	 * @param policy say if must be used max or min split policy
	 * @return the Reserveobj object
	 */
	private Reserveobj ABGAP(
			int k, List<Pair<String, Request>> requests, 
			HashMap<String, ServicePeriodParams> servPeriodsMap,
			HashMap<String, Thing> eqThingInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList,
			HashMap<String, List<String>> matrixM,
			double epsilon,
			Priority prio,
			Policy policy){

		Reserveobj res = null;
		double upper = 1.0;
		double lower = 0.0;
		double teta = 0;
		double z = 0;
		System.out.println("teta = "+teta);
		
		printInputGap(k, requests, servPeriodsMap, eqThingInfo, servNameThingsIdList, matrixM, teta, prio, policy);
		
		res = GAP(k, requests, servPeriodsMap, eqThingInfo, servNameThingsIdList, matrixM, teta, prio, policy);
	
		if(res.feasible == true)
		{
			teta = (upper - lower) / 2;
			while((upper - lower) > epsilon)
			{
				System.out.println("teta = "+teta);
				
				res = GAP(k, requests, servPeriodsMap, eqThingInfo, servNameThingsIdList, matrixM, teta, prio, policy);
	
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
				
				res = GAP(k, requests, servPeriodsMap, eqThingInfo, servNameThingsIdList, matrixM, teta, prio, policy);
			}
		}
		
		res.z = z;
		return res;
	}

	/**
	 * Real_battery_ gap.
	 *
	 * @param k the number of request
	 * @param requests all the requests as Pair <transId, Request>
	 * @param servPeriodsMap Map<transId, <p_j, h/p_j>>
	 * @param eqThingInfo Map<devId, Thing>
	 * @param servNameThingsIdList Map<reServName, List<DevId>>
	 * @param teta the teta for the constraint on z_i - f_ij > teta
	 * @param prio say which value using as priority p_ij
	 * @param policy say if must be used max or min split policy
	 * @return the reserveobj
	 */
	private Reserveobj GAP(
			int k, List<Pair<String, Request>> requests, 
			HashMap<String, ServicePeriodParams> servPeriodsMap,
			HashMap<String, Thing> eqThingInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList,
			HashMap<String, List<String>> matrixM,
			double teta,
			Priority prio,
			Policy policy) {
		
//		Reserveobj res = new Reserveobj();
//		
//		//Backup of the requests list List<transId, Request>
//		 List<Pair<String, Request>> requestsBck = cloneRequests(requests);
//		
//		//create Map<thingId, <c_i, z_i>> from the eqThingInfo Map<DevId, Thing>
//		HashMap<String, ThingAssignmentParams> assignmentParamsMap = createAssignmentParamsMap(eqThingInfo);
//		
//		//upper bound for utilization
//		Double ni = k*(Math.pow(2, 1/k)-1);
//		
//		res.feasible = true;
//		
//		double ds, d;
//		
//		double INF = Double.POSITIVE_INFINITY;
//
//		//List of thingIds that satisfy the constraints
//		List<Integer> Fjr;
//		
//		//iterate over the service requests
//		while(res.feasible && !requests.isEmpty()){
//			
//			ds = -1 * INF;
//			
//			//temporary allocation object
//			//<transId, servName, List<DevId>>
//			AllocationObj allocationTemp = new AllocationObj();
//			
//			//index to identify the position of the ServiceObject 
//			//on which an assignment is executed
//			int serviceIndex = 0;
//			int j = 0;
//			
//			//iterate over the list of request identify by transId
//			for(Pair<String, Request> servRequest: requests){
//				
//				//get the transaId that identify a request with multiple
//				//service requests
//				String transId = servRequest.getLeft();
//				
//				//Request object contains
//				//operationType, QoSRequirements, LocationRequirements,
//				//required service list
//				Request requestObj = servRequest.getRight();
//				
//				logger.debug("request with TransId: "+transId);
//				
//				//Get the list of required services for this request
//				List<String> reqServicesList = requestObj.getRequiredServicesNameList();
//				
//				//iterate over the List of required services in the request
//				//identified by transId
//				for(String reqServiceName: reqServicesList){
//					
//					logger.debug("ServiceRequest Name: "+reqServiceName+
//									"inside request with TransId: "+transId);
//					
//					//coefficientMap ha as key the transId
//					//so i get the first elem of array transId_servId
//					List<Integer> Sj = factorization(servPeriodsMap.get(transId).getNj());
//					
//					//var that a trial to assign one service
//					//to only one thing has been done
//					Boolean oneServiceToOneThingTrial = true;
//					
//					//iterate over the list of split factors
//					//of a service on multiple things
//					while(!res.feasible || !Sj.isEmpty()){
//						
//						int split;
//						
//						//split says to how many things assign the service
//						if(oneServiceToOneThingTrial){ 
//							split = 1;
//							oneServiceToOneThingTrial = false;
//						}
//						else{
//							split = chooseFactor(Sj, policy);
//						}
//						logger.debug("split factor= "+String.valueOf(split));
//						
//						//R is used iterate in case of 
//						//assignment to multiple things
//						int R = split;
//						
//						//iterate over the R that indicates the number
//						//of suballocation for the service servId
//						while(res.feasible && R!=0){
//							
//							ds = -1 * INF;
//							
//							for(int r = 0; r<R; r++){
//	
//								//List of devId that satisfy
//								//the constraints about
//								//utilization and residual battery
//								Fjr = checkConstraints(assignmentParamsMap, eqThingInfo, matrixM, 
//																		split, ni, teta, null);
//								
//								//there is no thing that satisfy the
//								//requirements
//								if(Fjr.isEmpty()){
//									
//									logger.debug("Fjr is empty");
//									
//									res.feasible = false;
//									break;
//								}
//								
//								//get the id of the Thing that have max priority
//								Integer tID_maxPriority = getArgMaxPriority(equivalentThingsParamsMap, Fjr, priorityIndex);
//								
//								logger.debug("thingId with max priority: "+tID_maxPriority.toString());
//								
//								//only one thing satisfy the constraints
//								//about utilization and residual battery
//								if(Fjr.size() == 1){
//									d = INF;
//								}
//								else{
//									//difference between the max and the second max priority
//									d = getDiffMaxAndSecondMax(tID_maxPriority, equivalentThingsParamsMap, Fjr, priorityIndex);
//									
//									logger.debug("difference d="+String.valueOf(d));
//								}
//								
//								if(d > ds){
//									ds = d;
//									
//									//allocation of the service given by transId, servId 
//									//to a thing with a thingService thingId, thingServiceId
//									allocationTemp.transId = transId;
//									allocationTemp.servId = servId;
//									
//									ThingIdThingServiceIdPair tId_tsId = new ThingIdThingServiceIdPair();
//									tId_tsId.setThingId(tID_maxPriority);
//									Integer thingServiceId = equivalentThingsParamsMap.get(tID_maxPriority).getThingServiceId();
//									tId_tsId.setThingServiceId(thingServiceId);
//									
//									//take the pair thingId, thingServiceId to which allocate the service
//									//with servId in the request with transId
//									allocationTemp.thingIdThingServiceIdAssignments.add(tId_tsId);
//									
//									//value of the split of the service
//									allocationTemp.split = s_p;
//									
//									//store the index of the service for which
//									//a list of things has been assigned
//									serviceIndex = j;
//								}
//								
//								Fjr.clear();
//							}
//							
//							if(res.feasible)
//								R--;
//	
//						}
//						//if a feasible allocation is found
//						//stop the cicle here
//						if(res.feasible) break;
//						
//						//remove a split factor from the list
//						Sj.remove(s_p);
//					}
//					
//					//index of the Service taken in consideration
//					j++;
//				}
//			}
//				
//			if(res.feasible){
//				
//				if(res.allocationSchema.get(allocationTemp.transId) == null){
//					res.allocationSchema.put(allocationTemp.transId, new HashMap<Integer, AllocationObj>());
//				}
//				
//				//update the allocationSchema with the new allocation for the service servId
//				//Map<transId, Map<servId, List<tId_tsId>>
//				res.allocationSchema.get(allocationTemp.transId).put(allocationTemp.servId, allocationTemp);
//				
//				logger.debug("alloction of service request with transId="+allocationTemp.transId+
//								"and servId="+allocationTemp.servId.toString());
//				logger.debug("allocated with a spli factor "+String.valueOf(allocationTemp.split));
//				
//				//update assignments params c_i+(u_ij/s_p), z_i-(f_ij/s_p)
//				updateAssignmentsParams(assignmentsParamsMap, 
//						mappingServEqThings.get(allocationTemp.transId).get(serviceIndex).getThingServiceExecFeatureMap(), 
//						allocationTemp.thingIdThingServiceIdAssignments,
//						allocationTemp.split);
//				
//				
//				//remove ServiceAssignments object from the mapping
//				mappingServEqThings.get(allocationTemp.transId).remove(serviceIndex);
//			}
//			else return res;
//			
//			//Local optimization
//			if(!battery && res.feasible){
//				
//				logger.debug("Local Optimization");
//				
//				//itearate over the list <transId, List<servId, Map<thingId, <tsId, f_ij, u_ij, p[]>>>>
//				for(Map.Entry<String, List<ServiceAssignments>> entry : mappingServEqThingsBck.entrySet()){
//					
//					//get the transId with which the request is identified 
//					String transId = entry.getKey();
//					List<ServiceAssignments> servAssignmentList = entry.getValue();
//					
//					//iterate over the list <servId, Map<thingId, <tsId, f_ij, u_ij, p[]>>
//					for(ServiceAssignments servAssignment: servAssignmentList){
//						
//						//map of equivalent thing services associated to the thing
//						//with the params <f_ij, u_ij, p_ij>
//						HashMap<Integer, ServiceExecutionFeature> equivalentThingsParamsMap = servAssignment.getThingServiceExecFeatureMap();
//						
//						//get the servId of the requested service
//						Integer servId = servAssignment.getServId();
//						
//						//get the Allocation associated to the pair transId, servId
//						AllocationObj allocation = res.allocationSchema.get(transId).get(servId);
//						
//						//get the split factor for that allocation
//						int split = allocation.split;
//						
//						logger.debug("split factor="+String.valueOf(split));
//						
//						for(int s = 0; s<split; s++){
//						
//							int thingId_sub = allocation.thingIdThingServiceIdAssignments.get(s).getThingId();
//							
//							logger.debug("try to substitute the thing with id="+String.valueOf(thingId_sub));
//							
//							//check the constraints excluding the thing with id thingId_sub
//							Fjr = checkConstraints(equivalentThingsParamsMap, 
//									assignmentsParamsMap, split, ni, teta, 
//									allocation.thingIdThingServiceIdAssignments.get(s).getThingId());
//							
//							//get the id (from Fjr) of the thing with max residualBattery
//							int thingId_star = getArgMaxResidualBattery(assignmentsParamsMap, Fjr);
//							
//							logger.debug("thingId* with max residual battery="+String.valueOf(thingId_star));
//							
//							//get the max residual battery value
//							Double maxResidualBattery = assignmentsParamsMap.get(thingId_star).getResidualBattery();
//							logger.debug("max residual battery value="+String.valueOf(maxResidualBattery));
//							
//							//z_i'-f_i'j residual battery value of the thing to substitute
//							double residualBatt_sub = assignmentsParamsMap.get(thingId_sub).getResidualBattery();
//							logger.debug("residual battery value of thing to substitute="+String.valueOf(residualBatt_sub));
//							
//							//if the max value of residual battery is greater than
//							//the value of residual battery of the thing to substitute
//							//there is a change in the allocation
//							if(maxResidualBattery > residualBatt_sub){
//								
//								//create a new pair tId_tsId for the thingId*
//								//with the max residual battery value
//								ThingIdThingServiceIdPair tId_tsId_star = new ThingIdThingServiceIdPair();
//								tId_tsId_star.setThingId(thingId_star);
//								Integer thingServiceId = equivalentThingsParamsMap.get(thingId_star).getThingServiceId();
//								tId_tsId_star.setThingServiceId(thingServiceId);
//								
//								allocation.thingIdThingServiceIdAssignments.add(tId_tsId_star);
//								
//								//remove the old thing substituted
//								allocation.thingIdThingServiceIdAssignments
//											.remove(allocation.thingIdThingServiceIdAssignments.get(s));
//								
//								//update the allocation in reservation object
//								//TODO List<AllocationObj> -> List<tId_tsId>
//								res.allocationSchema.get(transId).put(servId, allocation);
//								
//								//update the value of the thing substituted
//								//c_i-(u_ij/split) and z_i+(f_ij/split)
//								//using -split
//								ArrayList<ThingIdThingServiceIdPair> tId_tsIdList = new ArrayList<>();
//								ThingIdThingServiceIdPair tId_tsId_sub = new ThingIdThingServiceIdPair();
//								tId_tsId_sub.setThingId(thingId_sub);
//								tId_tsId_sub.setThingServiceId(thingServiceId);
//								tId_tsIdList.add(tId_tsId_sub);
//								updateAssignmentsParams(assignmentsParamsMap, equivalentThingsParamsMap, tId_tsIdList, -split);
//								
//								//update the value of the new thing allocated
//								//c_i+(u_ij/split) and z_i-(f_ij/split)
//								tId_tsIdList.clear();
//								tId_tsIdList.add(tId_tsId_star);
//								updateAssignmentsParams(assignmentsParamsMap, equivalentThingsParamsMap, tId_tsIdList, split);
//							}
//						}
//					}
//					
//				}
//				
//			}
//		}
//		
//		res.assignmentsParamsMap = assignmentsParamsMap;
		
		return null;
	}


	/* function to create a table that say the list of DevId of the
	 * things that respect the restriction of a request identified
	 * by the transactionId (Map<transId, List<DevId>>) */
	private HashMap<String, List<String>> createMatrixM(
			List<Pair<String, Request>> requests,
			HashMap<String, Thing> eqThingInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList) {
		
		HashMap<String, List<String>> matrixM = new HashMap<>();
		
		//iterate over the list of requests
		for(Pair<String, Request> reqPair: requests){
			
			//get the transId, request object and list of reqServName
			String transId = reqPair.getLeft();
			Request request = reqPair.getRight();
			List<String> reqServNameList = request.getRequiredServicesNameList();
			
			//take the maxRespTime and accuracy from QoSrequirements of the request object
			Double maxRespTime = request.getQosRequirements().getMaxResponseTime();
			Double accuracy = request.getQosRequirements().getAccuracy();
			
			logger.debug("maxRespTime: "+maxRespTime+" accuracy: "+ accuracy==null ? "null": accuracy);
			
			Point point = null;
			Circle circle = null;
			Polygon polygon = null;
			
			if(request.getLocationRequirement() != null){
				//take the location requirement from the LocationRequirement object in the request object
				Class<?> locRequirementsType = request.getLocationRequirement().getLocationRequirement().getClass();
				
				logger.debug("locRequirementsType is "+locRequirementsType.getCanonicalName());
				
				if(locRequirementsType == Point.class){
					point = (Point)request.getLocationRequirement().getLocationRequirement();

				}
				else{
					if(locRequirementsType == Circle.class){
						circle = (Circle)request.getLocationRequirement().getLocationRequirement();
					}
					else{
						polygon = (Polygon)request.getLocationRequirement().getLocationRequirement();
					}
				}
			}
			
			//iterate over the list of required servName
			for(String reServName: reqServNameList){
				
				//clone the list of DevId of all equivalent things for that 
				//required service name
				List<String> eqThings = new ArrayList<>();
				eqThings.addAll(servNameThingsIdList.get(reServName).getEqThings());
				
				//iterate over the list of equivalent things
				//for that serviceName
				for(String devId: eqThings){
					
					//get the thing
					Thing t = eqThingInfo.get(devId);
					
					//get the coords of the thing
					Point coords = t.getCoords();
					
					//check location requirement
					if(coords != null){
						if(point != null){
							if(coords.getLatitude() != point.getLatitude() ||
									coords.getLongitude() != point.getLongitude()){
								eqThings.remove(devId);
							}
						}
						else{
							if(circle != null){
								if(!in_circle(circle, coords)){
									eqThings.remove(devId);
								}
							}
							else{
								if(!in_polygon(polygon, coords)){
									eqThings.remove(devId);
								}
							}
						}
					}
					
					//check QoSrequirements on services on a thing
					HashMap<String, ServiceFeatures> services = t.getServicesList();
					
					for(Map.Entry<String, ServiceFeatures> servEntry: services.entrySet()){
						
						Double latency = servEntry.getValue().getLatency();
						
						Double servAccuracy = servEntry.getValue().getAccuracy()==null ? null 
															: servEntry.getValue().getAccuracy();
						
						//check latency and accuracy constraints
						if(latency != null && latency > maxRespTime || servAccuracy != null && servAccuracy != accuracy){
							eqThings.remove(devId);
						}
					}
					
				}
				
				//if the list of devId of the equivalentThings for
				//that is empty, the allocation can take place
				if(eqThings.isEmpty()) return null;
				else
					matrixM.put(transId, eqThings);
				
				eqThings.clear();
			}
			
			
		}
		
		return matrixM;
	}


	/* function to check if a point is inside a polygon */
	private boolean in_polygon(Polygon polygon, Point coords) {
	
		int i;
		int j;
		
		List<Vertex> vertexList = polygon.getVertexList();
		
		boolean result = false;
		for (i = 0, j = vertexList.size() - 1; i < vertexList.size(); j = i++) {
			if ((vertexList.get(i).getLongitude() > coords.getLongitude()) != 
					(vertexList.get(j).getLatitude() > coords.getLatitude())
					&& (coords.getLatitude() < (vertexList.get(j).getLatitude() - vertexList.get(i).getLatitude())
							* (coords.getLongitude() - vertexList.get(i).getLongitude())
							/ (vertexList.get(j).getLongitude() - vertexList.get(i).getLongitude()) 
							+ vertexList.get(i).getLatitude())) {
				result = !result;
			}
		}
		
		return result;
	}

	/* function to check if a point is inside a circle */
	private boolean in_circle(Circle circle, Point coords) {
		
		Double x2 = Math.pow((circle.getCenterLatitude() - coords.getLatitude()), 2);
		Double y2 = Math.pow((circle.getCenterLongitude() - coords.getLongitude()), 2);
		
		Double dist = Math.sqrt(x2 + y2);
	    return dist <= circle.getRadius();
	}
	
	/* function to print the input values of the GAP algorithm */
	private void printInputGap(int k, List<Pair<String, Request>> requests,
			HashMap<String, ServicePeriodParams> servPeriodsMap,
			HashMap<String, Thing> eqThingInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList,
			HashMap<String, List<String>> matrixM, double teta, Priority prio,
			Policy policy) {
		
		
		
	}



//	/* function to create the ngsi allocation schema from the Reserveobj object */
//	private List<ContextRegistration> createNgsiAllocationSchema(
//			Reserveobj reserveobj, HashMap<Integer, Thing> totalThingsMap, 			
//			HashMap<String, HashMap<Integer, String>> totalRequestedServicesMap) {
//		
//		HashMap<String, HashMap<Integer, AllocationObj>> allocationSchema = reserveobj.allocationSchema;
//		
//		List<ContextRegistration> contRegList = new ArrayList<>();
//		
//		//for each transId
//		for(Map.Entry<String, HashMap<Integer, AllocationObj>> entry : allocationSchema.entrySet()){
//			
//			String transId = entry.getKey();
//			ContextRegistration contReg = new ContextRegistration();
//			
//			List<EntityId> entityIdList = new ArrayList<>();
//			EntityId entId = new EntityId();
//			entId.setId(transId);
//			entId.setIsPattern(false);
//			entId.setType(URI.create("Allocation"));
//			entityIdList.add(entId);
//			
//			contReg.setListEntityId(entityIdList);
//			
//			HashMap<Integer, AllocationObj> servicesAllocation = entry.getValue();
//			List<ContextRegistrationAttribute> contRegAttrList = new ArrayList<>();
//			
//			ContextRegistrationAttribute contRegAttr = new ContextRegistrationAttribute();
//			
//			//for each servId in a request identify by transId
//			for(Map.Entry<Integer, AllocationObj> entryAllocation : servicesAllocation.entrySet()){
//				
//				Integer servId = entryAllocation.getKey();
//				String serviceName = totalRequestedServicesMap.get(transId)
//										.get(servId);
//				
//				contRegAttr.setName(serviceName);
//				contRegAttr.setType(URI.create("service"));
//				
//				List<ContextMetadata> contMetadataList = new ArrayList<>();
//				
//				List<ThingIdThingServiceIdPair> allocationList = 
//						entryAllocation.getValue().thingIdThingServiceIdAssignments;
//				for(ThingIdThingServiceIdPair tId_tsId: allocationList){
//					
//					ContextMetadata contMetadata = new ContextMetadata();
//					
////					String contextEntityId = totalThingsMap.get(tId_tsId.getThingId()).getContextEntityId();
////					String attrName = totalThingsMap.get(tId_tsId.getThingId()).getThingServices()
////										.get(tId_tsId.getThingServiceId()).getServiceName();
//					
//					contMetadata.setName(String.valueOf(tId_tsId.getThingId()));
//					contMetadata.setType(URI.create("string"));
//					contMetadata.setValue(contextEntityId+"::"+attrName);
//					
//					contMetadataList.add(contMetadata);
//				}
//				
//				contRegAttr.setMetaData(contMetadataList);
//			}
//			
//			contRegAttrList.add(contRegAttr);
//			contReg.setListContextRegistrationAttribute(contRegAttrList);
//			contRegList.add(contReg);
//		}
//		
//		return contRegList;
//	}


//	
//	/* function to get the thingId to which is associated the max residualBatteryLevel */
//	private int getArgMaxResidualBattery(
//			HashMap<Integer, ThingAssignmentParams> assignmentsParamsMap,
//			List<Integer> Fjr) {
//		
//		int thingId_maxResidualBattery = 0;
//		Double maxResidualBattery = Double.NEGATIVE_INFINITY;
//		
//		for(Integer thingId: Fjr){
//			
//			Double residualBattery = assignmentsParamsMap.get(thingId).getResidualBattery();
//			if(residualBattery > maxResidualBattery){
//				maxResidualBattery = residualBattery;
//				
//				thingId_maxResidualBattery = thingId;
//			}
//		}
//		
//		return thingId_maxResidualBattery;
//	}
//
//	/* function to update c_i and z_i, given Map<thingId, <c_i, z_i>>,
//	 * Map<thingId, <thingServiceId, f_ij, u_ij, p[]>>,
//	 * List<thingId, thingServiceId>, the split */
//	private void updateAssignmentsParams(
//			HashMap<Integer, ThingAssignmentParams> assignmentsParamsMap,
//			HashMap<Integer, ServiceExecutionFeature> thingServiceExecFeatureMap,
//			ArrayList<ThingIdThingServiceIdPair> thingIdThingServiceIdAssignments,
//			Integer s_p) {
//		
//		for(ThingIdThingServiceIdPair tId_tsId: thingIdThingServiceIdAssignments){
//			
//			logger.debug("update <c_i,z_i> of the thing with id "+tId_tsId.getThingId().toString());
//			
//			ThingAssignmentParams assParams = assignmentsParamsMap.get(tId_tsId.getThingId());
//			
//			double u_ij = thingServiceExecFeatureMap.get(tId_tsId.getThingId()).getUtilization();
//			double f_ij = thingServiceExecFeatureMap.get(tId_tsId.getThingId()).getNormalizedEnergyCost();
//			
//			logger.debug("u_ij/s_p="+String.valueOf(u_ij/s_p));
//			logger.debug("f_ij/s_p="+String.valueOf(f_ij/s_p));
//			
//			assParams.setTotalUtilization(assParams.getTotalUtilization() + (u_ij/s_p));
//			assParams.setResidualBattery(assParams.getResidualBattery() - (f_ij/s_p));
//			
//			logger.debug("new c_i="+String.valueOf(assParams.getTotalUtilization()));
//			logger.debug("new c_i="+String.valueOf(assParams.getResidualBattery()));
//		}
//	}
//
//	/* function to get the thingId (in Fjr) for which there is max priority given
//	 * Map<thingId, <thingServiceId, f_ij, u_ij, p[]>>, Fjr, priorityIndex that
//	 * says what priority to take in consideration */
//	private Integer getArgMaxPriority(
//			HashMap<Integer, ServiceExecutionFeature> equivalentThingsParamsMap,
//			List<Integer> Fjr, int priorityIndex) {
//		
//		Double maxPriority = -1 * Double.POSITIVE_INFINITY;
//		
//		Integer thingId_MaxPriority = 0; 
//		
//		for(Integer thingId: Fjr){
//			
//			Double priority = equivalentThingsParamsMap.get(thingId).getPriority().get(priorityIndex);
//			
//			if(priority > maxPriority){
//				maxPriority = priority;
//				
//				thingId_MaxPriority = thingId;
//			}
//		}
//		
//		return thingId_MaxPriority;
//	}
//
//	/* function to clone mappingServiceEquivalentThings */
//	private HashMap<String, List<ServiceAssignments>> cloneMappingServEqThings(
//			HashMap<String, List<ServiceAssignments>> mappingServEqThings) {
//
//		HashMap<String, List<ServiceAssignments>> mappingServEqThingsBck = new HashMap<>();
//		
//		logger.debug("mappingServEqThings Map<transId, List<ServId, Map<thingId, <ThingServiceId, f_ij, u_ij, p[]>>>>");
//		
//		for(Map.Entry<String, List<ServiceAssignments>> entry : mappingServEqThings.entrySet()){
//			
//			String transId = entry.getKey();
//			List<ServiceAssignments> servAssigmentsList = new ArrayList<>();
//			
//			for(ServiceAssignments serviceAssignment: entry.getValue()){
//				
//				ServiceAssignments servAssBck = new ServiceAssignments();
//				
//				servAssBck.setServId(serviceAssignment.getServId());
//				
//				servAssBck.setThingServiceExecFeatureMap(serviceAssignment.getThingServiceExecFeatureMap());
//				servAssigmentsList.add(servAssBck);
//			}
//			
//			mappingServEqThingsBck.put(transId, servAssigmentsList);
//		}
//		
//		return mappingServEqThingsBck;
//	}
//
//	/* function to get the difference between the max priority and the second max priority, given
//	 * thingId with maxPriority, Map<thingId, <thingServiceId, f_ij, u_ij, p[]>>, Fjr,
//	 * priorityIndex that says what priority value to take */
//	private double getDiffMaxAndSecondMax(
//			Integer tID_maxPriority,
//			HashMap<Integer, ServiceExecutionFeature> equivalentThingsParamsMap,
//			List<Integer> Fjr, int priorityIndex) {
//		
//		Double maxPriority = equivalentThingsParamsMap.get(tID_maxPriority).getPriority().get(priorityIndex);
//		
//		Fjr.remove(tID_maxPriority);
//		
//		Double secondMaxPriority = Double.NEGATIVE_INFINITY;
//		
//		for(Integer thingId: Fjr){
//			
//			Double priority = equivalentThingsParamsMap.get(thingId).getPriority().get(priorityIndex);
//			
//			if(priority > secondMaxPriority){
//				secondMaxPriority = priority;
//			}
//		}
//		
//		return (maxPriority-secondMaxPriority);
//	}
//
//	/* function that return the list of things that respect the constraints
//	 * given Map<thingId, <f_ij, u_ij, p[]>>, Map<thingId, <c_i,z_i>>, split factor s_p,
//	 * the upper bound for utilization, and theta for residual battery,
//	 * thingIdExcluded indicates the thingId to exclude in the check */
//	private List<Integer> checkConstraints(
//			HashMap<Integer, ServiceExecutionFeature> equivalentThingsParamsMap,
//			HashMap<Integer, ThingAssignmentParams> assignmentsParamsMap,
//			int s_p, Double ni, double teta,
//			Integer thingIdExcluded) {
//		
//		logger.debug("checkConstraints to obtain the list of thingId in Fjr array");
//		logger.debug("split="+String.valueOf(s_p));
//		logger.debug("ni="+String.valueOf(ni));
//		logger.debug("teta="+String.valueOf(teta));
//		
//		List<Integer> Fjr = new ArrayList<>();
//		
//		for (Map.Entry<Integer, ServiceExecutionFeature> entry : equivalentThingsParamsMap.entrySet()) {
//			Integer thingId = entry.getKey();
//			
//			logger.debug("thingId: "+thingId.toString());
//			
//			if(thingIdExcluded != null && thingId.equals(thingIdExcluded)){
//				continue;
//			}
//			
//			ServiceExecutionFeature servExecFeat = entry.getValue();
//			
//			double c_i = assignmentsParamsMap.get(thingId).getTotalUtilization();
//			double z_i = assignmentsParamsMap.get(thingId).getResidualBattery();
//			double u_ij = servExecFeat.getUtilization()/s_p;
//			double f_ij = servExecFeat.getNormalizedEnergyCost()/s_p;
//			
//			logger.debug("c_i= "+String.valueOf(c_i));
//			logger.debug("c_i= "+String.valueOf(z_i));
//			logger.debug("c_i= "+String.valueOf(u_ij));
//			logger.debug("c_i= "+String.valueOf(f_ij));
//			
//			logger.debug("c_i+u_ij= "+String.valueOf(c_i+u_ij));
//			logger.debug("ni= "+String.valueOf(ni));
//			
//			logger.debug("z_i-f_ij= "+String.valueOf(z_i-f_ij));
//			logger.debug("teta= "+String.valueOf(teta));
//			
//			if((c_i+u_ij<ni) && (z_i-f_ij>teta)){
//				
//				logger.debug("thingId="+thingId.toString()+"respect the constraints");
//				
//				Fjr.add(thingId);
//			}
//		}
//		
//		return Fjr;
//	}





	private List<Integer> checkConstraints(
			HashMap<String, ThingAssignmentParams> assignmentParamsMap,
			HashMap<String, Thing> eqThingInfo,
			HashMap<String, List<String>> matrixM, int split, Double ni,
			double teta, Object object) {
		
		
		return null;
	}



	/* function to compute Map<DevId, <c_i, z_i>> */
	private HashMap<String, ThingAssignmentParams> createAssignmentParamsMap(
			HashMap<String, Thing> eqThingInfo) {
		
		HashMap<String, ThingAssignmentParams> assignmentParamsMap = new HashMap<>();
		
		for(String devId: eqThingInfo.keySet()){
			
			Double batt = eqThingInfo.get(devId).getBatteryLevel();
			
			if(batt == null){
				batt = 0.0;
			}
			
			ThingAssignmentParams assParams = new ThingAssignmentParams();
			
			//set the vaue of c_i
			assParams.setTotalUtilization(0.0);
			
			//z_i set to one or to the value of the battery/100
			assParams.setResidualBattery(batt/100);
			
			assignmentParamsMap.put(devId, assParams);
		}
		
		return assignmentParamsMap;
	}


	/* function to create a copy od List<transId, Request> */
	private List<Pair<String, Request>> cloneRequests(
			List<Pair<String, Request>> requests) {
		
		List<Pair<String, Request>> reqListBck = new ArrayList<Pair<String, Request>>();
		
		for(Pair<String, Request> reqEntry: requests){
			
			String transId = reqEntry.getLeft();
			Request req = reqEntry.getRight();
			
			reqListBck.add(new Pair<String, Request>(transId, req));
		}
		
		return reqListBck;
	}



	/* function to choose a term from 
	 * the list of factors given a policy */
	private int chooseFactor(List<Integer> sj, Policy policy) {

		if(policy == Policy.MIN_SPLIT){
			
			return sj.get(0);
		}
		else{
			
			return sj.get((sj.size()-1));
		}
	}

	/* function to compute the factorization of a number */
	private List<Integer> factorization(Integer number) {
		
		int n = number;
	    List<Integer> factors = new ArrayList<Integer>();

	    for (int i = 2; i <= n; i++) {
	      while (n % i == 0) {
	        factors.add(i);
	        n /= i;
	      }
	    }
	    return factors;
	}

	
}
