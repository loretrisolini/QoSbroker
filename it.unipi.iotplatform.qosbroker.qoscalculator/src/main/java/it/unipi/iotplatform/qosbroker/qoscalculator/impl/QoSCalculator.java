package it.unipi.iotplatform.qosbroker.qoscalculator.impl;

import it.unipi.iotplatform.qosbroker.api.datamodel.QoSCode;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSReasonPhrase;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServicePeriodParams;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.api.datamodel.TransIdList;
import it.unipi.iotplatform.qosbroker.qoscalculator.api.QoSCalculatorIF;
import it.unipi.iotplatform.qosbroker.qoscalculator.datamodel.ThingAssignmentParams;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextMetadata;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistration;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistrationAttribute;
import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;
import eu.neclab.iotplatform.ngsi.api.datamodel.Polygon;
import eu.neclab.iotplatform.ngsi.api.datamodel.StatusCode;
import eu.neclab.iotplatform.ngsi.api.datamodel.Vertex;

public class QoSCalculator implements QoSCalculatorIF {

	/** The logger. */
	private static Logger logger = Logger.getLogger(QoSCalculator.class);
	
	private PrintWriter writer;
	
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
		
		//Map<devId, <c_i, z_i>>
		HashMap<String, ThingAssignmentParams> assignmentsParamsMap;
		
		//Map<transId, Map<reqServName, List<devId>>>
		HashMap<String, HashMap<String, AllocationObj>> allocationSchema;

		Reserveobj() {
			allocationSchema = new HashMap<String, HashMap<String, AllocationObj>>();

		}
		
	}
	
	/* allocation class to store a single service
	 * allocation transId,ServId -> tId, tsId */
	public class AllocationObj{
		
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
	
	private Double maxPriority = 0.0;
	private Double secondMaxPriority = 0.0;
	private String operationStatus = new String("");
	
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
			//HashMap<String, TransIdList> matrixM,
			double epsilon) {
		
		Reserveobj[] res = new Reserveobj[3];
		
		ReservationResults ret = new ReservationResults();
		
		HashMap<String, List<String>> matrixM = createMatrixM(requests,eqThingInfo,servNameThingsIdList);
		if(matrixM == null){
			StatusCode statusCode= new StatusCode(QoSCode.SERVICEALLOCATIONFAILED_502.getCode(),
					QoSReasonPhrase.SERVICEALLOCATIONFAILED_502.name(), "QoSCalculator -- computeAllocation() " + operationStatus);
			ret.setStatusCode(statusCode);
			
			operationStatus="";
			return ret;
		}
		
		try{
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
	
			int imax=0;
			// Gets the best heuristic
			for(int j=1;j<3;j++)
			{
				if(res[imax].z<res[j].z && res[j].feasible)
					imax = j;
			}
			if(res[imax].feasible){
				ret.setFeas(true);
	
				List<ContextRegistration> ngsiAllocationSchema = createNgsiAllocationSchema(res[imax]);
				
				ret.setAllocationSchema(ngsiAllocationSchema);
				
				ret.setWhich(imax);
				
				printAllocationSchema(res[imax].allocationSchema);
				
				StatusCode statusCode= new StatusCode(QoSCode.OK_200.getCode(),QoSReasonPhrase.OK_200.name(), "QoSCalculator -- computeAllocation()" + operationStatus);
				ret.setStatusCode(statusCode);
				operationStatus = "";
				
				return ret;
			}

		}
		catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		catch(FileNotFoundException fe){
			fe.printStackTrace();
		}
		
		StatusCode statusCode= new StatusCode(QoSCode.SERVICEALLOCATIONFAILED_502.getCode(),
												QoSReasonPhrase.SERVICEALLOCATIONFAILED_502.name(), "QoSCalculator -- computeAllocation()" + operationStatus);
		ret.setStatusCode(statusCode);
		operationStatus = "";
		
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
			Policy policy) throws UnsupportedEncodingException,FileNotFoundException{

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
	 * @param matrixM Map<transId::servName, List<devId>> list of devId that respect 
	 * 					restrictions for that transaction
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
			Policy policy) throws UnsupportedEncodingException,FileNotFoundException{
		
		Reserveobj res = new Reserveobj();
		
		//Backup of the requests list List<transId, Request>
		 List<Pair<String, Request>> requestsBck = cloneRequests(requests);
		
		//create Map<devId, <c_i, z_i>> from the eqThingInfo Map<DevId, Thing>
		HashMap<String, ThingAssignmentParams> assignmentParamsMap = createAssignmentParamsMap(eqThingInfo);
		
		Double pow = Math.pow(2, (double)1/k);
		//upper bound for utilization
		Double ni = k*(pow-1);
		logger.debug("ni= "+ni);
		
		res.feasible = true;
		
		double ds, d;
		
		double INF = Double.POSITIVE_INFINITY;
		
		//List of devId that satisfy the constraints
		List<String> Fjr;

		//temporary allocation object
		//<transId, servName, List<DevId>>
		AllocationObj allocationTemp = new AllocationObj();
		
		writer = new PrintWriter("/home/lorenzo/Desktop/GapResult"+prio.name()+".txt", "UTF-8");
		
		//index to identify the position of the ServiceObject 
		//on which an assignment is executed
		int requestIndex = 0;
		int serviceIndex = 0;
		int j = 0;
		int i = 0;
		
		//iterate over the service requests as List<<transId, Request>>
		while(res.feasible && !requests.isEmpty()){
			
			ds = -1 * INF;

			//iterate over the list of request identify by transId
			for(Pair<String, Request> servRequest: requests){
				
				//get the transaId that identify a request with multiple
				//service requests
				String transId = servRequest.getLeft();
				
				//Request object contains
				//operationType, QoSRequirements, LocationRequirements,
				//required service list
				Request requestObj = servRequest.getRight();
				
				writer.println();
				writer.println("request with TransId: "+transId);
				logger.debug("request with TransId: "+transId);
				
				//Get the list of required services for this request
				List<String> reqServicesList = requestObj.getRequiredServicesNameList();
				
				//iterate over the List of required services in the request
				//identified by transId
				for(String reqServiceName: reqServicesList){
					
					//get the list<DevId> for that reqServName
					//this list represents the list of equivalent things
					List<String> eqThings = servNameThingsIdList.get(reqServiceName).getEqThings();
					
					writer.println();
					logger.debug("ServiceRequest Name: "+reqServiceName+
									" inside request with TransId: "+transId+"<----------------------------");
					writer.println("ServiceRequest Name: "+reqServiceName+"<----------------------------");
					
					//coefficientMap ha as key the transId
					//so i get the first elem of array transId_servId
					List<Integer> Sj = factorization(servPeriodsMap.get(transId).getNj());
					
					//var that a trial to assign one service
					//to only one thing has been done
					Boolean oneServiceToOneThingTrial = true;
					int splitIndex = 0;
					
					//iterate over the list of split factors
					//of a service on multiple things
					while(/*!res.feasible ||*/ !Sj.isEmpty()){
						
						int split;
						
						//split says to how many things assign the service
						if(oneServiceToOneThingTrial){ 
							split = Sj.get(0);
							oneServiceToOneThingTrial = false;
							splitIndex = 0;
						}
						else{
							split = chooseFactor(Sj, policy);
							splitIndex+=1;
						}
						logger.debug("split factor= "+String.valueOf(split)+"<----------------------------");
						writer.println();
						writer.println("split factor= "+String.valueOf(split)+"<----------------------------");
						
						//R is used to iterate in case of 
						//assignment to multiple things
						int R = split;
						
						//iterate over the R that indicates the number
						//of suballocation for the service servId
						while(res.feasible && R!=0){
							
							ds = -1 * INF;
							
							for(int r = 0; r<R; r++){
	
								//List of devId that satisfy
								//the constraints about
								//utilization and residual battery
								Fjr = checkConstraints(assignmentParamsMap, eqThingInfo, eqThings, servPeriodsMap, 
														matrixM.get(transId+"::"+reqServiceName), transId, reqServiceName, split, ni, teta, null);
								
								//there is no thing that satisfy the
								//requirements
								if(Fjr.isEmpty()){
									
									logger.debug("Fjr is empty<----------------------------");
									logger.debug("ServiceRequest Name: "+reqServiceName+
											" inside request with TransId: "+transId+" no thing found<-------------------");
									
									writer.println("Fjr is empty<----------------------------");
									writer.println("ServiceRequest Name: "+reqServiceName+
											" inside request with TransId: "+transId+" no thing found<-------------------");
									
									operationStatus = "QoSCalculator -- GAP() ServiceRequest Name: "+reqServiceName+
											" inside request with TransId: "+transId+" no thing found";
									
									if(policy == Policy.MAX_SPLIT)
										res.feasible = false;
									break;
								}
								
								if(Fjr.size() == R){
									
									//TODO direct allocation of service reqServName
									
								}
								
								//get the devId of the Thing that have max priority
								//at the same time set maxPriority and secondMaxPriority
								String devId_maxPriority = getArgMaxPriority(Fjr, eqThingInfo, prio, servPeriodsMap, 
																reqServiceName, transId, split);
								
								logger.debug("devId with max priority: " + devId_maxPriority+"<----------------------------");
								writer.println("devId with max priority: " + devId_maxPriority+"<----------------------------");
								
								//only one thing satisfy the constraints
								//about utilization and residual battery
								if(Fjr.size() == 1){
									d = INF;
								}
								else{
									
									//difference between the max and the second max priority
									d = maxPriority - secondMaxPriority;
									
									maxPriority = 0.0;
									secondMaxPriority = 0.0;
									logger.debug("difference d="+String.valueOf(d));
									writer.println("difference d="+String.valueOf(d));
								}
								
								if(d > ds){
									ds = d;
									
									//allocation of the service given by transId, reqServName 
									//to a thing with a thingService thingId, thingServiceId
									allocationTemp.transId = transId;
									
									allocationTemp.serviceName = reqServiceName;
									
									//compute pair f_ij, u_ij
									Pair<Double, Double> f_u_ij = computeF_U(devId_maxPriority, servPeriodsMap, transId, eqThingInfo, reqServiceName);
									if(f_u_ij == null){
										res.feasible = false;
										return res;
									}
									
									allocationTemp.f_ij = f_u_ij.getLeft();
									allocationTemp.u_ij = f_u_ij.getRight();
									allocationTemp.split = split;
									
									//add the devId
									allocationTemp.devIdList.add(devId_maxPriority);
									
									logger.debug("allocationObj<------------------------------");
									logger.debug("transId: "+allocationTemp.transId);
									logger.debug("serviceName: "+allocationTemp.serviceName);
									logger.debug("f_ij: "+allocationTemp.f_ij);
									logger.debug("u_ij: "+allocationTemp.u_ij);
									logger.debug("split: "+allocationTemp.split);
									logger.debug("devId with max priority allocated: "+devId_maxPriority);
									writer.println("allocationObj<----------------------------");
									writer.println("transId: "+allocationTemp.transId);
									writer.println("serviceName: "+allocationTemp.serviceName);
									writer.println("f_ij: "+allocationTemp.f_ij);
									writer.println("u_ij: "+allocationTemp.u_ij);
									writer.println("split: "+allocationTemp.split);
									writer.println("devId with max priority allocated: "+devId_maxPriority);
									writer.println("##########################################################");
									writer.println("##########################################################");
									writer.println("##########################################################");
									
									//store the index of the service for which
									//a list of things has been assigned
									requestIndex = i;
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
						
						allocationTemp.devIdList.clear();
						
						//remove a split factor from the list
						Sj.remove(splitIndex);
					}
					
					if(!res.feasible) break;
					
					//index of the Service taken in consideration
					j++;
				}
				
				if(!res.feasible) break;
				
				//index of the Request with multiple services
				i++;
			}
				
			if(res.feasible){
				
				//update the allocationSchema with the new allocation for the service servId
				//Map<transId, Map<servId, List<tId_tsId>>
				res.allocationSchema.get(allocationTemp.transId).put(allocationTemp.serviceName, allocationTemp);
				
				logger.debug("alloction of service request with transId="+allocationTemp.transId+
								"and servId="+allocationTemp.serviceName+"<----------------------------");
				logger.debug("allocated with a split factor "+String.valueOf(allocationTemp.split));
				logger.debug("allocated with a f_ij "+allocationTemp.f_ij);
				logger.debug("allocated with a u_ij "+allocationTemp.u_ij);
				writer.println("<<---------------------------------------------------->>");
				writer.println("<<---------------------------------------------------->>");
				writer.println("<<---------------------------------------------------->>");
				writer.println("alloction of service request with transId="+allocationTemp.transId+
								"and servId="+allocationTemp.serviceName+"<----------------------------");
				writer.println("allocated with a split factor "+String.valueOf(allocationTemp.split));
				writer.println("allocated with a f_ij "+allocationTemp.f_ij);
				writer.println("allocated with a f_ij "+allocationTemp.u_ij);
				writer.println("devId allocated to the service "+allocationTemp.serviceName+": ");
				for(String devId: allocationTemp.devIdList){ writer.println(devId); }
				writer.println("<<---------------------------------------------------->>");
				writer.println("<<---------------------------------------------------->>");
				writer.println("<<---------------------------------------------------->>");
				
				//update assignments params c_i+(u_ij/s_p), z_i-(f_ij/s_p)
				updateAssignmentsParams(assignmentParamsMap, allocationTemp, 1, null);

				//remove service in the list of the request object
				//request obj index is requestIndex, service index is serviceIndex
				requests.get(requestIndex).getRight().getRequiredServicesNameList().remove(serviceIndex);
				
				allocationTemp.devIdList.clear();
				i=0;
				j=0;
			}
			else{
				writer.println("allocation Failed<------------------------------------");
				writer.close();
				return res;
			}
		}
		
		//Local optimization
		if(prio != Priority.BATTERY && res.feasible){
			
			writer.println("##########################################################");
			writer.println("##########################################################");
			writer.println("##########################################################");
			logger.debug("Local Optimization");
			writer.println("Local Optimization");
			
			//iterate over the list of request identify by transId
			for(Pair<String, Request> servRequest : requestsBck){
				
				//get the transaId that identify a request with multiple
				//service requests
				String transId = servRequest.getLeft();
				
				//Request object contains
				//operationType, QoSRequirements, LocationRequirements,
				//required service list
				Request requestObj = servRequest.getRight();
				
				logger.debug("request with TransId: "+transId);
				writer.println("request with TransId: "+transId);
				
				//Get the list of required services for this request
				List<String> reqServicesList = requestObj.getRequiredServicesNameList();
				
				//iterate over the List of required services in the request
				//identified by transId
				for(String reqServiceName: reqServicesList){
					
					logger.debug("ServiceRequest Name: "+reqServiceName+
							"inside request with TransId: "+transId);
					writer.println("ServiceRequest Name: "+reqServiceName);
					
					//get the list<DevId> for that reqServName
					//this list represents the list of equivalent things
					List<String> eqThings = servNameThingsIdList.get(reqServiceName).getEqThings();
					
					//get the Allocation associated to the pair transId, servId
					AllocationObj allocation = res.allocationSchema.get(transId).get(reqServiceName);
					
					//get the split factor for that allocation
					int split = allocation.split;
					
					logger.debug("split factor="+String.valueOf(split));
					writer.println("split factor="+String.valueOf(split));
					
					for(int s = 0; s<split; s++){
					
						String devId_substitution = allocation.devIdList.get(s);
						
						logger.debug("try to substitute the thing with id= "+devId_substitution);
						writer.println("try to substitute the thing with id= "+devId_substitution);
						
						//check the constraints excluding the thing with id thingId_sub
						Fjr = checkConstraints(assignmentParamsMap, eqThingInfo, eqThings, servPeriodsMap, 
								matrixM.get(transId+"::"+reqServiceName), transId, reqServiceName, split, ni, teta, devId_substitution);
						
						//get the id (from Fjr) of the thing with max residualBattery
						String devId_star = getArgMaxResidualBattery(assignmentParamsMap, Fjr);
						
						logger.debug("thingId* with max residual battery="+devId_star);
						writer.println("thingId* with max residual battery="+devId_star);
						
						//get the max residual battery value
						Double maxResidualBattery = assignmentParamsMap.get(devId_star).getResidualBattery();
						logger.debug("max residual battery value="+String.valueOf(maxResidualBattery));
						writer.println("max residual battery value="+String.valueOf(maxResidualBattery));
						
						//z_i'-f_i'j residual battery value of the thing to substitute
						double residualBatt_substitution = assignmentParamsMap.get(devId_substitution).getResidualBattery();
						logger.debug("residual battery value of thing to substitute="+String.valueOf(residualBatt_substitution));
						writer.println("residual battery value of thing to substitute="+String.valueOf(residualBatt_substitution));
						
						//if the max value of residual battery is greater than
						//the value of residual battery of the thing to substitute
						//there is a change in the allocation
						if(maxResidualBattery > residualBatt_substitution){
							
							writer.println("<------------------------------------------->");
							writer.println("<------------------------------------------->");
							logger.debug("remove allocation of service "+reqServiceName);
							logger.debug("to devId "+devId_substitution);
							logger.debug("and assigned to "+devId_star);
							writer.println("remove allocation of service "+reqServiceName);
							writer.println("to devId "+devId_substitution);
							writer.println("and assigned to "+devId_star);
							
							//update the value of the thing substituted
							//c_i-(u_ij/split) and z_i+(f_ij/split)
							//using -split
							logger.debug("c_i-(u_ij/split) and z_i+(f_ij/split) devId_substitution "+devId_substitution);
							writer.println("c_i-(u_ij/split) and z_i+(f_ij/split) devId_substitution "+devId_substitution);
							updateAssignmentsParams(assignmentParamsMap, allocationTemp, -1, devId_substitution);

							//add the new thing
							allocation.devIdList.add(devId_star);
							
							//remove the old thing substituted
							allocation.devIdList.remove(devId_substitution);

							logger.debug("c_i+(u_ij/split) and z_i-(f_ij/split) devId_star "+devId_star);
							writer.println("c_i+(u_ij/split) and z_i-(f_ij/split) devId_star "+devId_star);
							//update the value of the new thing allocated
							//c_i+(u_ij/split) and z_i-(f_ij/split)
							updateAssignmentsParams(assignmentParamsMap, allocationTemp, 1, devId_star);
							
							//update the allocation in reservation object
							//TODO List<AllocationObj> -> List<tId_tsId>
							res.allocationSchema.get(transId).put(reqServiceName, allocation);
							writer.println("<------------------------------------------->");
							writer.println("<------------------------------------------->");
						}
						writer.println("<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					}
				}
				writer.println("<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
			writer.println("##########################################################");
			writer.println("##########################################################");
			writer.println("##########################################################");
		}
	
		res.assignmentsParamsMap = assignmentParamsMap;
		writer.close();
		
		operationStatus += "QoSCalculator -- GAP() allocation operation OK";
		
		return res;
	}

	/* function to update c_i and z_i */
	private void updateAssignmentsParams(
			HashMap<String, ThingAssignmentParams> assignmentParamsMap,
			AllocationObj allocationTemp, int i, String _devId) {
		
		List<String> devIdList = allocationTemp.devIdList;
		int split = allocationTemp.split;
		Double u_ij = allocationTemp.u_ij;
		Double f_ij = allocationTemp.f_ij;
		
		writer.println("<--------------------------->");
		writer.println("<--------------------------->");
		writer.println("update <c_i, z_i>");
		logger.debug("update <c_i, z_i>");
		
		if(_devId == null){
			for(String devId: devIdList){
				
				writer.println("devId: "+devId);
				logger.debug("devId: "+devId);
				
				Double c_i = assignmentParamsMap.get(devId).getTotalUtilization();
				assignmentParamsMap.get(devId).setTotalUtilization(c_i + i*(u_ij/split));
				
				Double z_i = assignmentParamsMap.get(devId).getResidualBattery();
				assignmentParamsMap.get(devId).setResidualBattery(z_i - i*(f_ij/split));
				
				logger.debug("update c_i: "+(c_i + i*(u_ij/split)));
				logger.debug("update z_i: "+(z_i - i*(f_ij/split)));
				logger.debug("split: "+split);
				writer.println("update c_i: "+(c_i + i*(u_ij/split)));
				writer.println("update z_i: "+(z_i - i*(f_ij/split)));
				writer.println("split: "+split);
			}
		}
		else{
			writer.println("single devId: "+_devId);
			logger.debug("single devId: "+_devId);
			
			Double c_i = assignmentParamsMap.get(_devId).getTotalUtilization();
			assignmentParamsMap.get(_devId).setTotalUtilization(c_i + i*(u_ij/split));
			
			Double z_i = assignmentParamsMap.get(_devId).getResidualBattery();
			assignmentParamsMap.get(_devId).setResidualBattery(z_i - i*(f_ij/split));
			
			logger.debug("update c_i: "+(c_i + i*(u_ij/split)));
			logger.debug("update z_i: "+(z_i - i*(f_ij/split)));
			logger.debug("split: "+split);
			writer.println("update c_i: "+(c_i + i*(u_ij/split)));
			writer.println("update z_i: "+(z_i - i*(f_ij/split)));
			writer.println("split: "+split);
		}
		
		writer.println("<--------------------------->");
		writer.println("<--------------------------->");
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
			for(String reqServName: reqServNameList){
				
				//clone the list of DevId of all equivalent things for that 
				//required service name
				List<String> eqThings = new ArrayList<>();
				eqThings.addAll(servNameThingsIdList.get(reqServName).getEqThings());
				
				//iterate over the list of equivalent things
				//for that serviceName
				for(int i=0; i<eqThings.size(); i++){
					
					String devId = eqThings.get(i);
					
					//get the thing
					Thing t = eqThingInfo.get(devId);
					
					//get the coords of the thing
					Point coords = t.getCoords();
					
					//check location requirement
					if(coords != null){
						if(point != null){
							if(coords.getLatitude() != point.getLatitude() ||
									coords.getLongitude() != point.getLongitude()){
								eqThings.remove(i);
								continue;
							}
						}
						else{
							if(circle != null){
								if(!in_circle(circle, coords)){
									eqThings.remove(i);
									continue;
								}
							}
							else{
								if(!in_polygon(polygon, coords)){
									eqThings.remove(i);
									continue;
								}
							}
						}
					}
					
					//check QoSrequirements on services on a thing
					HashMap<String, ServiceFeatures> services = t.getServicesList();
					
					for(Map.Entry<String, ServiceFeatures> servEntry: services.entrySet()){
						
						//check the constraints only on the required reqServName
						//not on all services of the thing
						if(servEntry.getKey().contentEquals(reqServName)){
							Double latency = servEntry.getValue().getLatency();
							
							Double servAccuracy = servEntry.getValue().getAccuracy()==null ? null 
																: servEntry.getValue().getAccuracy();
							
							//check latency and accuracy constraints
							if(latency != null && latency > maxRespTime || servAccuracy != null && servAccuracy != accuracy){
								eqThings.remove(i);
								break;
							}
							break;
						}
					}
					
				}
				
				//if the list of devId of the equivalentThings for
				//that is empty, the allocation can take place
				if(eqThings.isEmpty()){
					operationStatus = "no things respect restrictions of the transaction "+transId+", service: "+reqServName;
					return null;
				}
				else{

					matrixM.put(transId+"::"+reqServName, eqThings);
				}
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
	
	/* function to get the max priority */
	private String getArgMaxPriority(List<String> Fjr,
			HashMap<String, Thing> eqThingInfo, Priority prio,
			HashMap<String, ServicePeriodParams> servPeriodsMap,
			String reqServiceName, String transactionId, int split) {
		
		maxPriority = -1 * Double.POSITIVE_INFINITY;
		
		String devId_MaxPriority = null; 
		
		for(String devId: Fjr){
			
			//compute pair f_ij, u_ij
			Pair<Double, Double> f_u_ij = computeF_U(devId, servPeriodsMap, transactionId, eqThingInfo, reqServiceName);
			
			if(f_u_ij == null){
				continue;
			}
			
			Double f_ij = f_u_ij.getLeft();
			Double u_ij = f_u_ij.getRight();
			
			Double p_ij = null;
			
			if(prio == Priority.BATTERY){
				p_ij = f_ij;
			}
			else{
				if(prio == Priority.UTILIZATION){
					p_ij = u_ij;
				}
				else{
					p_ij = Math.random();
				}
			}
			
			if(p_ij > maxPriority){

				secondMaxPriority = maxPriority;
				
				maxPriority = p_ij;
				
				devId_MaxPriority = devId;
			}
			else{
				secondMaxPriority = p_ij;
			}
		}

		return devId_MaxPriority;
	}



	/* function to print the input values of the GAP algorithm */
	private void printInputGap(int k, List<Pair<String, Request>> requests,
			HashMap<String, ServicePeriodParams> servPeriodsMap,
			HashMap<String, Thing> eqThingInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList,
			HashMap<String, List<String>> matrixM, double teta, Priority prio,
			Policy policy) {
		

		try{
			PrintWriter writer = new PrintWriter("/home/lorenzo/Desktop/InputGap.txt", "UTF-8");

			writer.println("####################################");
			writer.println("####################################");
			writer.println("number of Service Requests: "+k);
			writer.println("<------------------------------>");
			
			writer.println("ServicePeriodParams <p_j, h/p_j>");
			for(Map.Entry<String, ServicePeriodParams> entryPeriod: servPeriodsMap.entrySet()){
				writer.println("transactionId: "+entryPeriod.getKey());
				writer.println("p_j: "+entryPeriod.getValue().getPeriod());
				writer.println("h/p_j: "+entryPeriod.getValue().getNj());
				writer.println("<------------------------------>");
			}
			
			writer.println("Things available <batt, coords, <servName, <t_ij, c_ij>>>");
			for(Map.Entry<String, Thing> entryThing: eqThingInfo.entrySet()){
				writer.println("devId: "+entryThing.getKey());
				writer.println("batt: "+entryThing.getValue().getBatteryLevel());
				if(entryThing.getValue().getCoords()!=null)
					writer.println("coords: "+entryThing.getValue().getCoords().getLatitude()+","+entryThing.getValue().getCoords().getLongitude());
				
				HashMap<String, ServiceFeatures> services = entryThing.getValue().getServicesList();
				for(Map.Entry<String, ServiceFeatures> entryServ: services.entrySet()){
					writer.println("Service Name: "+entryServ.getKey());
					writer.println("latency: "+entryServ.getValue().getLatency());
					writer.println("energy_cost: "+entryServ.getValue().getEnergyCost());
					writer.println("accuracy: "+entryServ.getValue().getAccuracy());
					writer.println("<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>");
				}
				writer.println("<------------------------------>");
			}
			
			writer.println("equivalent Things per required Service Name");
			for(Map.Entry<String, ThingsIdList> entryThingId: servNameThingsIdList.entrySet()){
				writer.println("requiredServiceName: "+entryThingId.getKey());
				List<String> thingsIdList = entryThingId.getValue().getEqThings();
				writer.println("list of devId");		
				for(String devId: thingsIdList){
					writer.println("devId: "+ devId);
					writer.println("<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>");
				}
				writer.println("<------------------------------>");
			}
			
			writer.println("matrixM");
			for(Map.Entry<String, List<String>> entryTransId: matrixM.entrySet()){
				writer.println("transId: "+entryTransId.getKey());
				List<String> devIdList = entryTransId.getValue();
				writer.println("list of devId");		
				for(String devId: devIdList){
					writer.println("devId: "+ devId);
					writer.println("<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>");
				}
				writer.println("<------------------------------>");
			}
			
			writer.println("teta: "+teta);
			writer.println("<------------------------------>");
			writer.println("priority: "+prio.name());
			writer.println("<------------------------------>");
			writer.println("policy: "+policy.name());
			writer.println("########################################");
			writer.println("########################################");
			writer.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/* function to create the ngsi allocation schema from the Reserveobj object */
	private List<ContextRegistration> createNgsiAllocationSchema(Reserveobj reserveobj) {
		
		HashMap<String, HashMap<String, AllocationObj>> allocationSchema = reserveobj.allocationSchema;
		
		List<ContextRegistration> contRegList = new ArrayList<>();
		
		//for each transId
		for(Map.Entry<String, HashMap<String, AllocationObj>> entry : allocationSchema.entrySet()){
			
			String transId = entry.getKey();
			ContextRegistration contReg = new ContextRegistration();
			
			List<EntityId> entityIdList = new ArrayList<>();
			EntityId entId = new EntityId();
			entId.setId(transId);
			entId.setIsPattern(false);
			entId.setType(URI.create("Allocation"));
			entityIdList.add(entId);
			
			contReg.setListEntityId(entityIdList);
			
			HashMap<String, AllocationObj> servicesAllocation = entry.getValue();
			List<ContextRegistrationAttribute> contRegAttrList = new ArrayList<>();
			
			ContextRegistrationAttribute contRegAttr = new ContextRegistrationAttribute();
			
			//for each servName in a request identify by transId
			for(Map.Entry<String, AllocationObj> entryAllocation : servicesAllocation.entrySet()){

				String serviceName = entryAllocation.getKey();
				
				contRegAttr.setName(serviceName);
				contRegAttr.setType(URI.create("service"));
				
				List<ContextMetadata> contMetadataList = new ArrayList<>();
				
				List<String> allocationDevIdList = entryAllocation.getValue().devIdList;
				
				for(String devId: allocationDevIdList){
					
					ContextMetadata contMetadata = new ContextMetadata();
					
					String contextEntityId = devId;
					String attrName = serviceName;
					
					contMetadata.setName(serviceName);
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


	
	/* function to get the thingId to which is associated the max residualBatteryLevel */
	private String getArgMaxResidualBattery(
			HashMap<String, ThingAssignmentParams> assignmentParamsMap,
			List<String> Fjr) {
		
		String devId_maxResidualBattery = null;
		Double maxResidualBattery = Double.NEGATIVE_INFINITY;
		
		for(String devId: Fjr){
			
			Double residualBattery = assignmentParamsMap.get(devId).getResidualBattery();
			if(residualBattery > maxResidualBattery){
				maxResidualBattery = residualBattery;
				
				devId_maxResidualBattery = devId;
			}
		}
		
		return devId_maxResidualBattery;
	}

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




	/**
	 *
	 * @param assignmentParamsMap Map<DevId, <c_i, z_i>>
	 * @param eqThingInfo Map<devId, Thing>
	 * @param eqThings List<devId> equivalent things for that service
	 * @param servPeriodsMap Map<transId, <p_j, h/p_j>>
	 * @param eqThingsTransaction List<devId> things associated to the transaction
	 * 			and respect the restrictions of that transaction
	 * @param transactionId id of the transaction
	 * @param reqServiceName required service name
	 * @param split number to things to which assign the service
	 * @param ni utilization upper bound
	 * @param teta the teta for the constraint on z_i - f_ij > teta
 	 * @param devIdExcluded devId to exclude
	 * @return the List<devId> that respect the constraints
	 */
	private List<String> checkConstraints(
			HashMap<String, ThingAssignmentParams> assignmentParamsMap,
			HashMap<String, Thing> eqThingInfo,
			List<String> eqThings,
			HashMap<String, ServicePeriodParams> servPeriodsMap,
			List<String> eqThingsTransaction, 
			String transactionId,
			String reqServiceName,
			int split, Double ni,
			double teta, String devIdExcluded) {
		
		List<String> Fjr = new ArrayList<>();
		
		writer.println("<--------------------------->");
		writer.println("<--------------------------->");
		writer.println();
		writer.println("check constraints for service "+reqServiceName+"<---------------------------");
		logger.debug("check constraints for service "+reqServiceName+"<---------------------------");
		
		//iterate over the list of equivalent things
		//to check constraints
		for(String devId: eqThings){
			
			writer.println("devId: "+devId);
			logger.debug("devId: "+devId);
			
			if(devIdExcluded == null || !devId.contentEquals(devIdExcluded)){
					
				//check if the devId respect the constraints for that
				//transaction identified by the transactionId
				if(eqThingsTransaction.contains(devId)){
					
					writer.println("devId "+devId+" respects the constraints of the transId "+transactionId);
					logger.debug("devId "+devId+" respects the constraints of the transId "+transactionId);
					
					Pair<Double, Double> f_u_ij = computeF_U(devId, servPeriodsMap, transactionId, eqThingInfo, reqServiceName);
					
					if(f_u_ij == null){
						operationStatus = "QoSCalculator -- checkConstraints() f_u_ij null in checkConstarints";
						
						continue;
					}
					
					Double f_ij = f_u_ij.getLeft();
					Double u_ij = f_u_ij.getRight();
					
					writer.println("f_ij: "+f_ij);
					writer.println("u_ij: "+u_ij);
					logger.debug("f_ij: "+f_ij);
					logger.debug("u_ij: "+u_ij);
					
					//get c_i and z_i
					Double c_i = assignmentParamsMap.get(devId).getTotalUtilization();
					Double z_i = assignmentParamsMap.get(devId).getResidualBattery();
					
					logger.debug("c_i: "+c_i);
					logger.debug("(u_ij/split): "+(u_ij/split));
					logger.debug("z_i: "+z_i);
					logger.debug("(f_ij/split): "+(f_ij/split));
					logger.debug("split: "+split);
					writer.println("c_i: "+c_i);
					writer.println("(u_ij/split): "+(u_ij/split));
					writer.println("z_i: "+z_i);
					writer.println("(f_ij/split): "+(f_ij/split));
					writer.println("split: "+split);
					logger.debug("c_i + (u_ij/split): "+(c_i + (u_ij/split)));
					logger.debug("ni: "+ni);
					logger.debug("z_i - (f_ij/split): "+(z_i - (f_ij/split)));
					logger.debug("teta: "+teta);
					writer.println("c_i + (u_ij/split): "+(c_i + (u_ij/split)));
					writer.println("ni: "+ni);
					writer.println("z_i - (f_ij/split): "+(z_i - (f_ij/split)));
					writer.println("teta: "+teta);
					writer.println();
					
					//check the constraints about ni and teta
					if(c_i + (u_ij/split) < ni && z_i - (f_ij/split) > teta){
						
						logger.debug("add devId: "+devId+" to Fjr<----------------------------");
						writer.println("add devId: "+devId+" to Fjr<----------------------------");
						writer.println();
						Fjr.add(devId);
					}
					
				}
			}
		}
		
		if(Fjr.isEmpty()) writer.println("no thing found for service: "+reqServiceName);
		
		writer.println("<--------------------------->");
		writer.println("<--------------------------->");
		writer.println();
		return Fjr;
	}



	private Pair<Double, Double> computeF_U(String devId,
		HashMap<String, ServicePeriodParams> servPeriodsMap,
		String transactionId, HashMap<String, Thing> eqThingInfo,
		String reqServiceName) {
	
		//get all the variables to compute
		//f_ij and u_ij, checking that
		//they were not null
		Double battery = null;
		Double enCost = null;
		Double latency = null;
		Boolean varsOK = false;
		//coeff is h/p_j
		Integer coeff = servPeriodsMap.get(transactionId).getNj();
		Double p_j = servPeriodsMap.get(transactionId).getPeriod();
		
		//it is checked that all vars are not null
		if(eqThingInfo.get(devId)!=null){
			if(eqThingInfo.get(devId).getBatteryLevel() != null){
				battery = eqThingInfo.get(devId).getBatteryLevel()/100;
			}
			
			if(eqThingInfo.get(devId).getServicesList().get(reqServiceName).getLatency() != null){
				latency = eqThingInfo.get(devId).getServicesList().get(reqServiceName).getLatency();
			}
			
			if(eqThingInfo.get(devId).getServicesList().get(reqServiceName).getEnergyCost() != null){
				enCost = eqThingInfo.get(devId).getServicesList().get(reqServiceName).getEnergyCost();
				varsOK = true;
			}
			
			if(!varsOK){
				return null;
			}
		}
		else{
			return null;
		}
		
		//compute f_ij and u_ij
		Double f_ij = coeff * enCost/battery;
		Double u_ij = latency/p_j;
		
		return new Pair<Double, Double>(f_ij, u_ij);
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
	    factors.add(1);
	    for (int i = 2; i <= n; i++) {
	      while (n % i == 0) {
	        factors.add(i);
	        n /= i;
	      }
	    }
	    return factors;
	}

	private void printAllocationSchema(
			HashMap<String, HashMap<String, AllocationObj>> allocationSchema) {
		
		try{
			PrintWriter writer = new PrintWriter("/home/lorenzo/Desktop/ResultGap.txt", "UTF-8");

			writer.println("####################################");
			writer.println("####################################");
			
			for(Map.Entry<String, HashMap<String, AllocationObj>> entry: allocationSchema.entrySet()){
				writer.println("transId: "+entry.getKey());
				writer.println("<---------------------------------------->");
				
				HashMap<String, AllocationObj> services = entry.getValue();
				
				writer.println("services allocated:");
				for(Map.Entry<String, AllocationObj> entryAllocation: services.entrySet()){
					writer.println("service Name: "+entryAllocation.getKey());
					
					writer.println("split: "+entryAllocation.getValue().split);
					writer.println("f_ij: "+entryAllocation.getValue().f_ij);
					writer.println("u_ij: "+entryAllocation.getValue().u_ij);
					
					List<String> devIdList = entryAllocation.getValue().devIdList;
					
					writer.println("Things allocated: ");
					for(String devId: devIdList){
						writer.println("thing: "+devId);
						writer.println("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>");
					}
					writer.println("<ooooooooooooooooooooooooooooooooooo>");
				}
				writer.println("<---------------------------------------->");
				writer.println("<---------------------------------------->");
			}
			
			writer.println("########################################");
			writer.println("########################################");
			writer.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
