package it.unipi.iotplatform.qosbroker.qoscalculator.impl;

import it.unipi.iotplatform.qosbroker.api.datamodel.AllocationObj;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSCode;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSReasonPhrase;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServicePeriodParams;
import it.unipi.iotplatform.qosbroker.api.datamodel.Statistics;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.qoscalculator.api.QoSCalculatorIF;
import it.unipi.iotplatform.qosbroker.qoscalculator.datamodel.ThingAssignmentParams;

import java.io.FileNotFoundException;
import java.io.IOException;
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
	
	//indicates the value of p_ij
	//to use f_ij, u_ij or random value
	private enum Priority{
		BATTERY, UTILIZATION, RANDOM
	};
	
	//indicates the policy to follow
	//to split service to multiple things
	//max split or min split
	public enum Policy{
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

	private String assignmentTransId;
	private String assignmentServiceName;
	
	private Double maxPriority = 0.0;
	private Double maxResidualBattery = 0.0;
	private Double secondMaxPriority = 0.0;
	private String operationStatus = new String("");
	
	//temporary allocation object
	//<transId, servName, List<DevId>>
	private AllocationObj allocationTemp;
	private PrintWriter writer;
	
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
			Policy policy,
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
	
				List<ContextRegistration> ngsiAllocationSchema = createNgsiAllocationSchema(res[imax], requests);
				
				ret.setAllocationSchema(ngsiAllocationSchema);
				
				ret.setWhich(imax);
				
				Statistics.printAllocationSchema(res[imax].allocationSchema);
				
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
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		
		StatusCode statusCode= new StatusCode(QoSCode.SERVICEALLOCATIONFAILED_502.getCode(),
												QoSReasonPhrase.SERVICEALLOCATIONFAILED_502.name(), "QoSCalculator -- computeAllocation() " + operationStatus);
		ret.setStatusCode(statusCode);
		operationStatus = "";
		
		return ret;
	}



	/**
	 * Execute the heuristic specifically tailored for battery consumption.
	 *
	 * @param int k, 
	 * @param List<Pair<String, Request>> requests, 
	 * @param HashMap<String, ServicePeriodParams> servPeriodsMap,
	 * @param HashMap<String, Thing> eqThingInfo,
	 * @param HashMap<String, ThingsIdList> servNameThingsIdList,
	 * @param HashMap<String, List<String>> matrixM,
	 * @param double epsilon,
	 * @param Priority prio,
	 * @param Policy policy
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
			Policy policy) throws IOException,UnsupportedEncodingException,FileNotFoundException{

		Reserveobj res = null;
		double upper = 1.0;
		double lower = 0.0;
		double teta = 0;
		double z = 0;
		System.out.println("teta = "+teta);
		
		Statistics.printInputGap(k, requests, servPeriodsMap, eqThingInfo, servNameThingsIdList, matrixM, teta, prio.name(), policy.name());
		
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
			Policy policy) throws IOException, UnsupportedEncodingException,FileNotFoundException{

		
		Reserveobj res = new Reserveobj();
		allocationTemp = new AllocationObj(1);
		
		//Backup of the requests list List<transId, Request>
		List<Pair<String, Request>> requestsBck = cloneRequests(requests);
		
		List<Pair<String, Request>> requestsBckLoc = new ArrayList<>();
		if(prio != Priority.BATTERY){
			//backup of the requests list List<transId, Request>
			//for the local optimization
			requestsBckLoc = cloneRequests(requests);
		}
		
		//create Map<devId, <c_i, z_i>> from the eqThingInfo Map<DevId, Thing>
		HashMap<String, ThingAssignmentParams> assignmentParamsMap = createAssignmentParamsMap(eqThingInfo);
		
		Double pow = Math.pow(2, (double)1/k);
		//upper bound for utilization
		Double ni = k*(pow-1);
		logger.info("ni= "+ni);
		
		res.feasible = true;
		
		double ds, d, dsMulti = Double.NEGATIVE_INFINITY;
		
		double INF = Double.POSITIVE_INFINITY;
		
		//List of devId that satisfy the constraints
		List<String> Fjr;
		
		writer = new PrintWriter("/home/lorenzo/Downloads/FIWARE-WORK/git/QoSbroker/Tests/GapResult"+prio.name()+".txt", "UTF-8");
		
		logger.info("PRIORITY: "+prio.name());
		logger.info("POLICY "+policy.name());
		System.out.println();
		
		//index to identify the position of the ServiceObject 
		//on which an assignment is executed
		int requestIndex = 0;
		int serviceIndex = 0;
//		int j = 0;
//		int i = 0;
		
		//iterate over the service requests as List<<transId, Request>>
		while(res.feasible && !requestsBck.isEmpty()){
			
			ds = -1 * INF;

			//iterate over the list of request identify by transId
			for(int req=0; req<requestsBck.size() && res.feasible; req++){
				
				Pair<String, Request> servRequest = requestsBck.get(req);
				
//				j=0;
				
				//get the transaId that identify a request with multiple
				//service requests
				String transId = servRequest.getLeft();
				
				//Request object contains
				//operationType, QoSRequirements, LocationRequirements,
				//required service list
				Request requestObj = servRequest.getRight();
				
				String opType = requestObj.getOpType();
				
				System.out.println();
				System.out.println();
				writer.println();
				writer.println("request with TransId: "+transId);
				logger.info("request with TransId: "+transId);
				writer.println();
				writer.println("opType: "+opType);
				logger.info("opType: "+opType);
				
				//Get the list of required services for this request
				List<String> reqServicesList = requestObj.getRequiredServicesNameList();
				
				//iterate over the List of required services in the request
				//identified by transId
				for(int s=0; s<reqServicesList.size() && res.feasible ; s++){
					
					String reqServiceName = reqServicesList.get(s);
					
					//get the list<DevId> for that reqServName
					//this list represents the list of equivalent things
					List<String> eqThings = servNameThingsIdList.get(reqServiceName).getEqThings();
					
					writer.println();
					writer.println();
					
					System.out.println();
					logger.info("ServiceRequest Name: "+reqServiceName.toUpperCase() +
									" inside request with TransId: "+transId+"<----------------------------");
					writer.println("ServiceRequest Name: "+reqServiceName.toUpperCase() +"<----------------------------");
					
					//coefficientMap ha as key the transId
					//so i get the first elem of array transId_servId
					List<Integer> Sj = factorization(servPeriodsMap.get(transId).getNj());
					
					//var that a trial to assign one service
					//to only one thing has been done
//					Boolean oneServiceToOneThingTrial = true;
//					int splitIndex = 0;
					
					//iterate over the list of split factors
					//of a service on multiple things
					while(Sj.contains(1) || (!res.feasible && !Sj.isEmpty())){

						Integer split = null;
						Integer splitBck = null;
						//split says to how many things assign the service
						if(Sj.contains(1)){ 
							split = Sj.get(0);
						}
						else{
							splitBck = split;
							split = chooseFactor(Sj, policy);
							
							//avoid iteration on the same spli value
							if(splitBck == split){
								Sj.remove(split);
								continue;
							}
						}
						
						System.out.println();
						logger.info("split factor = "+String.valueOf(split)+"<----------------------------");
						writer.println();
						writer.println("split factor = "+String.valueOf(split)+"<----------------------------");
						
						//R is used to iterate in case of 
						//assignment to multiple things
						int R = split;
						
						
						res.feasible = true;
						//iterate over the R that indicates the number
						//of suballocation for the service servId
						while(res.feasible && R!=0){
							
							if(split > 1){ 
								//if there is a multi-assignment
								//i use another d star
								dsMulti = -1 * INF;
							}
							
							for(int r = 0; r<R; r++){
	
								//List of devId that satisfy
								//the constraints about
								//utilization and residual battery
								Fjr = checkConstraints(assignmentParamsMap, eqThingInfo, eqThings, servPeriodsMap, 
														matrixM.get(transId+"::"+reqServiceName), transId, reqServiceName, 
														split, ni, teta, null);
								
								writer.println("Fjr: "+Fjr);
								logger.info("Fjr: "+Fjr);
								
								//there is no thing that satisfy the
								//requirements
								if(Fjr.size() < split){
									
									System.out.println();
									logger.info("Fjr size: "+Fjr.size()+" < of the split: "+split+"<----------------------------");
									logger.info("ServiceRequest Name: "+reqServiceName.toUpperCase()+
											" inside request with TransId: "+transId
											+" NO THING FOUND OR THINGS NUMBER < SPLIT<---------------------------");
									System.out.println();
									
									writer.println("Fjr size: "+Fjr.size()+" < of the split: "+split+"<----------------------------");
									writer.println("ServiceRequest Name: "+reqServiceName.toUpperCase()+
											" inside request with TransId: "+transId
											+" NO THING FOUND OR THINGS NUMBER < SPLIT<---------------------------");
									
									operationStatus = "QoSCalculator -- GAP() ServiceRequest Name: "+reqServiceName+
											" inside request with TransId: "+transId+" NO THING FOUND OR THINGS NUMBER < SPLIT, split factor: "+String.valueOf(split);
									
									res.feasible = false;
									break;
								}
								
								if(split >1 && Fjr.size() == R){
									
									//TODO direct allocation of service reqServName
									
								}
								
								//get the devId of the Thing that have max priority
								//at the same time set maxPriority and secondMaxPriority
								String devId_maxPriority = getArgMax(Fjr, eqThingInfo, prio, servPeriodsMap, 
																reqServiceName, transId, split, null, true);
								if(devId_maxPriority == null){
									res.feasible = false;
									
									writer.println(operationStatus);
									writer.close();
									return res;
								}
								
								System.out.println();
								logger.info("devId with max priority: " + devId_maxPriority+"<----------------------------");
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
									logger.info("difference d="+String.valueOf(d));
									writer.println("difference d="+String.valueOf(d));
									writer.println();
								}
								
								if(split == 1 && d > ds || split > 1 && d > dsMulti){
									
									logger.info("split == 1 && d > ds: "+ (split == 1 && d > ds));
									logger.info("split > 1 && d > dsMulti: "+ (split > 1 && d > dsMulti));
									
									dsMulti = d;
									ds = d;
									
									//clear allocation object devIdList, F_ij, U_ij if the transId or the service is changed
									if(assignmentTransId!=null && !assignmentTransId.contentEquals(transId) || 
											assignmentServiceName!=null && !assignmentServiceName.contentEquals(reqServiceName)){
										allocationTemp = new AllocationObj(split);

									}

									//allocate a new AllocationObj if change the split factor
									if(split > 1 && allocationTemp.getSplit() != split){

										allocationTemp = new AllocationObj(split);
									}
									
									//allocation of the service given by transId, reqServName 
									//to a thing with a thingService thingId, thingServiceId
									assignmentTransId = transId;
									
									assignmentServiceName = reqServiceName;
									
									//compute pair f_ij, u_ij
									Pair<Double, Double> f_u_ij = computeF_U(devId_maxPriority, servPeriodsMap, transId, eqThingInfo, reqServiceName);
									if(f_u_ij == null){
										res.feasible = false;
										operationStatus = "QoSCalculator -- GAP() f_u_ij null for service: "+reqServiceName;
										
										writer.println(operationStatus);
										writer.close();
										return res;
									}
									
									allocationTemp.getF_ij()[split-R] = f_u_ij.getLeft();
									allocationTemp.getU_ij()[split-R] = f_u_ij.getRight();
									allocationTemp.setSplit(split);
									
									//add the devId
									allocationTemp.getDevIdList()[split-R] = devId_maxPriority;
									
									System.out.println();
									System.out.println();
									logger.info("allocationObj<------------------------------");
									logger.info("transId: "+assignmentTransId);
									logger.info("serviceName: "+assignmentServiceName.toUpperCase());
									logger.info(allocationTemp.toString());
									logger.info("new Thing allocated: "+devId_maxPriority);
									System.out.println();
									System.out.println();
									writer.println();
									writer.println("##########################################################");
									writer.println("##########################################################");
									writer.println("allocationObj<----------------------------");
									writer.println("transId: "+assignmentTransId);
									writer.println("serviceName: "+assignmentServiceName.toUpperCase());
									writer.println(allocationTemp.toString());
									writer.println("new Thing allocated: "+devId_maxPriority);
									writer.println("##########################################################");
									writer.println("##########################################################");
									
									//store the index of the service for which
									//a list of things has been assigned
									requestIndex = req;
									serviceIndex = s;
								}
								
								Fjr.clear();
							}
							
							if(res.feasible)
								R--;
	
						}
						//if a feasible allocation is found
						//stop the cicle here
//						if(res.feasible) break;
						
						//remove a split factor from the list
						Sj.remove(split);
					}
					
//					if(!res.feasible) break;
					
					//index of the Service taken in consideration
//					j++;
				}
				
//				if(!res.feasible) break;
				
				//index of the Request with multiple services
//				i++;
			}
				
			if(res.feasible){
				
				AllocationObj allocation = new AllocationObj(allocationTemp.getSplit());
				
				//update the allocationSchema with the new allocation for the service servId
				//Map<transId, Map<servId, List<tId_tsId>>
				if(res.allocationSchema.get(assignmentTransId) == null){
					res.allocationSchema.put(assignmentTransId, new HashMap<String, AllocationObj>());
				}
				
				allocation.setSplit(new Integer(allocationTemp.getSplit()));
				
				String[] devIdList = allocationTemp.getDevIdList();
				for(int i=0; i<devIdList.length; i++){
					allocation.getDevIdList()[i] = new String(devIdList[i]);
					allocation.getF_ij()[i] = new Double(allocationTemp.getF_ij()[i]);
					allocation.getU_ij()[i] = new Double(allocationTemp.getU_ij()[i]);
				}
				
				res.allocationSchema.get(assignmentTransId).put(assignmentServiceName, allocation);
				
				System.out.println();
				logger.info("alloction of service request with transId="+assignmentTransId);
				logger.info("and servName="+assignmentServiceName.toUpperCase()+"<----------------------------");
				logger.info(allocation.toString());
				System.out.println();
				writer.println();
				writer.println("##########################################################");
				writer.println("##########################################################");
				writer.println("##########################################################");
				writer.println("##########################################################");

				writer.println("alloction of service request with transId="+assignmentTransId+
								"\nand servName="+assignmentServiceName.toUpperCase()+"<----------------------------");
				writer.println(allocation.toString());

				writer.println("##########################################################");
				writer.println("##########################################################");
				writer.println("##########################################################");
				writer.println("##########################################################");

				writer.println();
				
				//update assignments params c_i+(u_ij/s_p), z_i-(f_ij/s_p)
				int split = allocation.getSplit();
				for(int u=0; u<devIdList.length; u++){
					updateAssignmentsParams(assignmentParamsMap, allocation.getF_ij()[u], allocation.getU_ij()[u], 
							split, devIdList[u], 1);
				}
				writer.println("<--------------------------->");
				writer.println("<--------------------------->");
				writer.println();
				
				//remove service in the list of the request object
				//request obj index is requestIndex, service index is serviceIndex
				requestsBck.get(requestIndex).getRight().getRequiredServicesNameList().remove(serviceIndex);
				
				if(requestsBck.get(requestIndex).getRight().getRequiredServicesNameList().isEmpty()){
					requestsBck.remove(requestIndex);
				}
				
				allocationTemp = new AllocationObj(1);
//				i=0;
//				j=0;
			}
			else{
				writer.println();
				writer.println();
				writer.println("ALLOCATION FAILED<------------------------------------");
				writer.close();
				return res;
			}
		}
		
		//Local optimization
		if(prio != Priority.BATTERY && res.feasible){
			
			writer.println("##########################################################");
			writer.println("##########################################################");
			writer.println("##########################################################");
			System.out.println();
			System.out.println();
			logger.info("Local Optimization");
			writer.println("Local Optimization");
			
			//iterate over the list of request identify by transId
			for(Pair<String, Request> servRequest : requestsBckLoc){
				
				//get the transaId that identify a request with multiple
				//service requests
				String transId = servRequest.getLeft();
				
				//Request object contains
				//operationType, QoSRequirements, LocationRequirements,
				//required service list
				Request requestObj = servRequest.getRight();
				
				System.out.println();
				logger.info("request with TransId: "+transId);
				writer.println("request with TransId: "+transId);
				
				//Get the list of required services for this request
				List<String> reqServicesList = requestObj.getRequiredServicesNameList();
				
				//iterate over the List of required services in the request
				//identified by transId
				for(String reqServiceName: reqServicesList){
					
					System.out.println();
					logger.info("ServiceRequest Name: "+ reqServiceName.toUpperCase() +
							" inside request with TransId: "+transId);
					writer.println("ServiceRequest Name: "+ reqServiceName.toUpperCase());
					
					//get the list<DevId> for that reqServName
					//this list represents the list of equivalent things
					List<String> eqThings = servNameThingsIdList.get(reqServiceName).getEqThings();
					
					//get the Allocation associated to the pair transId, servId
					allocationTemp = res.allocationSchema.get(transId).get(reqServiceName);
					
					//get the split factor for that allocation
					int split = allocationTemp.getSplit();
					
					System.out.println();
					logger.info("split factor="+String.valueOf(split));
					writer.println("split factor="+String.valueOf(split));
					
					for(int s = 0; s<split; s++){
					
						String devId_substitution = allocationTemp.getDevIdList()[s];
						
						System.out.println();
						logger.info("try to substitute the thing with id= "+devId_substitution);
						writer.println("try to substitute the thing with id= "+devId_substitution);
						
						//check the constraints excluding the thing with id thingId_sub
						Fjr = checkConstraints(assignmentParamsMap, eqThingInfo, eqThings, servPeriodsMap, 
								matrixM.get(transId+"::"+reqServiceName), transId, reqServiceName, 
								split, ni, teta, devId_substitution);
						
						logger.info("Fjr: "+Fjr);
						
						if(Fjr.isEmpty()) continue;
						
						//get the id (from Fjr) of the thing with max residualBattery
						
						String devId_star = getArgMax(Fjr, eqThingInfo, prio, servPeriodsMap, 
												reqServiceName, transId, split, assignmentParamsMap, false);
						if(devId_star == null){
							res.feasible = false;
							
							writer.println(operationStatus);
							writer.close();
							return res;
						}
						
						logger.info("thingId* with max residual battery="+devId_star);
						writer.println("thingId* with max residual battery="+devId_star);
						
//						//get the max residual battery value
//						Double maxResidualBattery = assignmentParamsMap.get(devId_star).getResidualBattery();
						logger.info("max residual battery value="+String.valueOf(maxResidualBattery));
						writer.println("max residual battery value="+String.valueOf(maxResidualBattery));
						
						//z_i'-f_i'j residual battery value of the thing to substitute
						double residualBatt_substitution = assignmentParamsMap.get(devId_substitution).getResidualBattery();
						logger.info("residual battery value of thing to substitute="+String.valueOf(residualBatt_substitution));
						writer.println("residual battery value of thing to substitute="+String.valueOf(residualBatt_substitution));
						
						//if the max value of residual battery is greater than
						//the value of residual battery of the thing to substitute
						//there is a change in the allocation
						if(maxResidualBattery > residualBatt_substitution){
							
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println("##########################################################");
							System.out.println();
							logger.info("remove allocation of service "+reqServiceName);
							logger.info("to devId "+devId_substitution);
							logger.info("and assigned to "+devId_star);
							writer.println("remove allocation of service "+reqServiceName);
							writer.println("to devId "+devId_substitution);
							writer.println("and assigned to "+devId_star);
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println("##########################################################");

							
							//update the value of the thing substituted
							//c_i-(u_ij/split) and z_i+(f_ij/split)
							//using -split
							logger.info("c_i-(u_ij/split) and z_i+(f_ij/split) devId_substitution "+devId_substitution);
							writer.println("c_i-(u_ij/split) and z_i+(f_ij/split) devId_substitution "+devId_substitution);
							Double oldF_ij = allocationTemp.getF_ij()[s];
							Double oldU_ij = allocationTemp.getU_ij()[s];
							split = allocationTemp.getSplit();
							updateAssignmentsParams(assignmentParamsMap, oldF_ij, oldU_ij, split, devId_substitution, -1);
							
							//compute pair f_ij, u_ij
							Pair<Double, Double> f_u_ij = computeF_U(devId_star, servPeriodsMap, transId, eqThingInfo, reqServiceName);
							if(f_u_ij == null){
								res.feasible = false;
								operationStatus = "QoSCalculator -- GAP() LocalOptimization f_u_ij null for service: "+reqServiceName;
								
								writer.println(operationStatus);
								writer.close();
								return res;
							}
							
							Double newF_ij = f_u_ij.getLeft();
							Double newU_ij = f_u_ij.getRight();
							//add the new thing, with new f_ij, new u_ij
							allocationTemp.getDevIdList()[s] = devId_star;
							allocationTemp.getF_ij()[s] = newF_ij;
							allocationTemp.getU_ij()[s] = newU_ij;
							
							logger.info("c_i+(u_ij/split) and z_i-(f_ij/split) devId_star "+devId_star);
							writer.println("c_i+(u_ij/split) and z_i-(f_ij/split) devId_star "+devId_star);
							//update the value of the new thing allocated
							//c_i+(u_ij/split) and z_i-(f_ij/split)
							updateAssignmentsParams(assignmentParamsMap, f_u_ij.getLeft(), f_u_ij.getRight(), split, devId_star, 1);
							
							//update the allocation in reservation object
							//TODO List<AllocationObj> -> List<tId_tsId>
							res.allocationSchema.get(transId).put(reqServiceName, allocationTemp);
							
							
							
							Fjr.clear();
						}
						
						maxResidualBattery = Double.NEGATIVE_INFINITY;
						
					}
				}
			}
			writer.println("##########################################################");
			writer.println("##########################################################");
			writer.println("##########################################################");
		}
	
		res.assignmentsParamsMap = assignmentParamsMap;
		
		writer.println();
		writer.println();
		writer.println("ALLOCATION COMPLETED<-------------------------------------");
		writer.close();
		
		operationStatus += "QoSCalculator -- GAP() allocation operation OK";
		
		return res;
	}

	/* function to update c_i and z_i */
	private void updateAssignmentsParams(
			HashMap<String, ThingAssignmentParams> assignmentParamsMap,
			Double f_ij, Double u_ij, int split, 	
			String devId, int i) {
		
		writer.println();
		writer.println("<--------------------------->");
		writer.println("<--------------------------->");
		writer.println("update <c_i, z_i>");
		System.out.println();
		logger.info("update <c_i, z_i>");
		
//		if(_devId == null){
//		for(String devId: devIdList){
			
		writer.println();
		writer.println("devId: "+devId);
		logger.info("devId: "+devId);
		
		Double c_i = assignmentParamsMap.get(devId).getTotalUtilization();
		assignmentParamsMap.get(devId).setTotalUtilization(c_i + i*(u_ij/split));
		
		Double z_i = assignmentParamsMap.get(devId).getResidualBattery();
		assignmentParamsMap.get(devId).setResidualBattery(z_i - i*(f_ij/split));
		
		logger.info("c_i: "+(c_i));
		logger.info("z_i: "+(z_i));
		logger.info("update c_i: "+(c_i + i*(u_ij/split)));
		logger.info("update z_i: "+(z_i - i*(f_ij/split)));
		logger.info("split: "+split);
		System.out.println();
		writer.println("update c_i: "+(c_i + i*(u_ij/split)));
		writer.println("update z_i: "+(z_i - i*(f_ij/split)));
		writer.println("split: "+split);
//		}
//		}
//		else{
//			writer.println();
//			writer.println("single devId: "+_devId);
//			System.out.println();
//			logger.info("single devId: "+_devId);
//			
//			Double c_i = assignmentParamsMap.get(_devId).getTotalUtilization();
//			assignmentParamsMap.get(_devId).setTotalUtilization(c_i + i*(u_ij/split));
//			
//			Double z_i = assignmentParamsMap.get(_devId).getResidualBattery();
//			assignmentParamsMap.get(_devId).setResidualBattery(z_i - i*(f_ij/split));
//			
//			logger.info("update c_i: "+(c_i + i*(u_ij/split)));
//			logger.info("update z_i: "+(z_i - i*(f_ij/split)));
//			logger.info("split: "+split);
//			writer.println("update c_i: "+(c_i + i*(u_ij/split)));
//			writer.println("update z_i: "+(z_i - i*(f_ij/split)));
//			writer.println("split: "+split);
//		}

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
			
			logger.info("maxRespTime: "+maxRespTime+" accuracy: "+ accuracy==null ? "null": accuracy);
			
			Point point = null;
			Circle circle = null;
			Polygon polygon = null;
			
			if(request.getLocationRequirement() != null){
				//take the location requirement from the LocationRequirement object in the request object
				Class<?> locRequirementsType = request.getLocationRequirement().getLocationRequirement().getClass();
				
				logger.info("locRequirementsType is "+locRequirementsType.getCanonicalName());
				
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
	private String getArgMax(List<String> Fjr,
			HashMap<String, Thing> eqThingInfo, Priority prio,
			HashMap<String, ServicePeriodParams> servPeriodsMap,
			String reqServiceName, String transactionId, int split,
			HashMap<String, ThingAssignmentParams> assignmentParamsMap,
			boolean getArgMaxPrio) {
		
		maxPriority = -1 * Double.POSITIVE_INFINITY;
		
		String devId_argMax = null; 
		
		for(String devId: Fjr){
			
			//compute pair f_ij, u_ij
			Pair<Double, Double> f_u_ij = computeF_U(devId, servPeriodsMap, transactionId, eqThingInfo, reqServiceName);
			
			if(f_u_ij == null){
				operationStatus = "QoSCalculator -- getArgMax() f_u_ij null for service: "+reqServiceName;
				return null;
			}
			
			Double f_ij = f_u_ij.getLeft();
			Double u_ij = f_u_ij.getRight();
			
			if(getArgMaxPrio){
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
					
					devId_argMax = devId;
				}
				else{
					secondMaxPriority = p_ij;
				}
			}
			else{
				
				Double residualBattery = assignmentParamsMap.get(devId).getResidualBattery() - f_ij;
				if(residualBattery > maxResidualBattery){
					maxResidualBattery = residualBattery;
					
					devId_argMax = devId;
				}
			}
		}

		return devId_argMax;
	}



	

	/* function to create the ngsi allocation schema from the Reserveobj object */
	private List<ContextRegistration> createNgsiAllocationSchema(Reserveobj reserveobj, List<Pair<String, Request>> requests) {
		
		HashMap<String, HashMap<String, AllocationObj>> allocationSchema = reserveobj.allocationSchema;
		
		List<ContextRegistration> contRegList = new ArrayList<>();
		
		//for each transId
		for(Map.Entry<String, HashMap<String, AllocationObj>> entry : allocationSchema.entrySet()){
			
			String transId = entry.getKey();
			
			String opType = "";
			//Pair<transId, Request>
			for(Pair<String, Request> reqPair: requests){
				
				if(reqPair.getLeft().contentEquals(transId)){
					opType = reqPair.getRight().getOpType();
				}
			}
			
			ContextRegistration contReg = new ContextRegistration();
			
			List<EntityId> entityIdList = new ArrayList<>();
			EntityId entId = new EntityId();
			entId.setId(transId);
			entId.setIsPattern(false);
			entId.setType(URI.create("Allocation"));
			entityIdList.add(entId);
			
			contReg.setListEntityId(entityIdList);
			
			List<ContextMetadata> contMetadataList = new ArrayList<>();
			ContextMetadata contMetadata = new ContextMetadata();
			contMetadata.setName("operationType");
			contMetadata.setType(URI.create("operation"));
			contMetadata.setValue(opType);
			contMetadataList.add(contMetadata);
			contReg.setListContextMetadata(contMetadataList);
			
			HashMap<String, AllocationObj> servicesAllocation = entry.getValue();
			List<ContextRegistrationAttribute> contRegAttrList = new ArrayList<>();
			
			ContextRegistrationAttribute contRegAttr = new ContextRegistrationAttribute();
			
			//for each servName in a request identify by transId
			for(Map.Entry<String, AllocationObj> entryAllocation : servicesAllocation.entrySet()){

				String serviceName = entryAllocation.getKey();
				
				contRegAttr.setName(serviceName);
				contRegAttr.setType(URI.create("service"));
				
				List<ContextMetadata> contMetadataAttrList = new ArrayList<>();
				
				String[] allocationDevIdList = entryAllocation.getValue().getDevIdList();
				
				for(String devId: allocationDevIdList){
					
					ContextMetadata contMetadataAttr = new ContextMetadata();
					
					String contextEntityId = devId;
					String attrName = serviceName;
					
					contMetadataAttr.setName(serviceName);
					contMetadataAttr.setType(URI.create("string"));
					contMetadataAttr.setValue(contextEntityId+"::"+attrName);
					
					contMetadataAttrList.add(contMetadataAttr);
				}
				
				contRegAttr.setMetaData(contMetadataAttrList);
			}
			
			contRegAttrList.add(contRegAttr);
			contReg.setListContextRegistrationAttribute(contRegAttrList);
			contRegList.add(contReg);
		}
		
		return contRegList;
	}


	
//	/* function to get the thingId to which is associated the max residualBatteryLevel */
//	private String getArgMaxResidualBattery(
//			HashMap<String, ThingAssignmentParams> assignmentParamsMap,
//			List<String> Fjr) {
//		
//		String devId_maxResidualBattery = null;
//		Double maxResidualBattery = Double.NEGATIVE_INFINITY;
//		
//		for(String devId: Fjr){
//
//			Double residualBattery = assignmentParamsMap.get(devId).getResidualBattery();
//			if(residualBattery > maxResidualBattery){
//				maxResidualBattery = residualBattery;
//				
//				devId_maxResidualBattery = devId;
//			}
//		}
//		
//		return devId_maxResidualBattery;
//	}

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
//			logger.info("update <c_i,z_i> of the thing with id "+tId_tsId.getThingId().toString());
//			
//			ThingAssignmentParams assParams = assignmentsParamsMap.get(tId_tsId.getThingId());
//			
//			double u_ij = thingServiceExecFeatureMap.get(tId_tsId.getThingId()).getUtilization();
//			double f_ij = thingServiceExecFeatureMap.get(tId_tsId.getThingId()).getNormalizedEnergyCost();
//			
//			logger.info("u_ij/s_p="+String.valueOf(u_ij/s_p));
//			logger.info("f_ij/s_p="+String.valueOf(f_ij/s_p));
//			
//			assParams.setTotalUtilization(assParams.getTotalUtilization() + (u_ij/s_p));
//			assParams.setResidualBattery(assParams.getResidualBattery() - (f_ij/s_p));
//			
//			logger.info("new c_i="+String.valueOf(assParams.getTotalUtilization()));
//			logger.info("new c_i="+String.valueOf(assParams.getResidualBattery()));
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
//		logger.info("mappingServEqThings Map<transId, List<ServId, Map<thingId, <ThingServiceId, f_ij, u_ij, p[]>>>>");
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
//		logger.info("checkConstraints to obtain the list of thingId in Fjr array");
//		logger.info("split="+String.valueOf(s_p));
//		logger.info("ni="+String.valueOf(ni));
//		logger.info("teta="+String.valueOf(teta));
//		
//		List<Integer> Fjr = new ArrayList<>();
//		
//		for (Map.Entry<Integer, ServiceExecutionFeature> entry : equivalentThingsParamsMap.entrySet()) {
//			Integer thingId = entry.getKey();
//			
//			logger.info("thingId: "+thingId.toString());
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
//			logger.info("c_i= "+String.valueOf(c_i));
//			logger.info("c_i= "+String.valueOf(z_i));
//			logger.info("c_i= "+String.valueOf(u_ij));
//			logger.info("c_i= "+String.valueOf(f_ij));
//			
//			logger.info("c_i+u_ij= "+String.valueOf(c_i+u_ij));
//			logger.info("ni= "+String.valueOf(ni));
//			
//			logger.info("z_i-f_ij= "+String.valueOf(z_i-f_ij));
//			logger.info("teta= "+String.valueOf(teta));
//			
//			if((c_i+u_ij<ni) && (z_i-f_ij>teta)){
//				
//				logger.info("thingId="+thingId.toString()+"respect the constraints");
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
		
		writer.println();
		writer.println("##########################################################");
		writer.println("##########################################################");
		writer.println("##########################################################");

		writer.println("CHECK CONSTRAINTS FOR SERVICE "+ reqServiceName+"<---------------------------");
		System.out.println();
		logger.info("CHECK CONSTRAINTS FOR SERVICE "+ reqServiceName.toUpperCase() +"<---------------------------");
		
		//iterate over the list of equivalent things
		//to check constraints
		for(String devId: eqThings){
			
			writer.println("devId: "+devId);
			logger.info("devId: "+devId);
			
			if(devIdExcluded == null || !devId.contentEquals(devIdExcluded)){
					
				//check if the devId respect the constraints for that
				//transaction identified by the transactionId
				if(eqThingsTransaction.contains(devId)){
					
					writer.println("devId "+devId+" respects the constraints of the transId "+transactionId);
					logger.info("devId "+devId+" respects the constraints of the transId "+transactionId);
					
					Pair<Double, Double> f_u_ij = computeF_U(devId, servPeriodsMap, transactionId, eqThingInfo, reqServiceName);
					
					if(f_u_ij == null){
						operationStatus = "QoSCalculator -- checkConstraints() f_u_ij null for service: "+reqServiceName;
						
						continue;
					}
					
					Double f_ij = f_u_ij.getLeft();
					Double u_ij = f_u_ij.getRight();
					
					//this operation is not done in case
					//of LOCAL OPTIMIZATION
					if(devIdExcluded==null){
						//case in which assign the service
						//multiple times to the same thing
						//so i multiple f_ij,u_ij for the iteration
						//of the assignment procedure
						int count = 1;
						for(int d =0; d<allocationTemp.getDevIdList().length; d++){
							if(allocationTemp.getDevIdList()[d]!=null && 
									allocationTemp.getDevIdList()[d].contentEquals(devId)){
								count++;
							}
						}
						logger.info("service: "+reqServiceName+" assigned to the thing: "+devId+" "+(count-1)+" times");
						f_ij*=count;
						u_ij*=count;
					}
						
					writer.println("f_ij: "+f_ij);
					writer.println("u_ij: "+u_ij);
					logger.info("f_ij: "+f_ij);
					logger.info("u_ij: "+u_ij);
					
					//get c_i and z_i
					Double c_i = assignmentParamsMap.get(devId).getTotalUtilization();
					Double z_i = assignmentParamsMap.get(devId).getResidualBattery();
					
					logger.info("c_i: "+c_i);
					logger.info("(u_ij/split): "+(u_ij/split));
					logger.info("z_i: "+z_i);
					logger.info("(f_ij/split): "+(f_ij/split));
					logger.info("split: "+split);
					writer.println("c_i: "+c_i);
					writer.println("(u_ij/split): "+(u_ij/split));
					writer.println("z_i: "+z_i);
					writer.println("(f_ij/split): "+(f_ij/split));
					writer.println("split: "+split);
					logger.info("c_i + (u_ij/split): "+(c_i + (u_ij/split)));
					logger.info("ni: "+ni);
					logger.info("z_i - (f_ij/split): "+(z_i - (f_ij/split)));
					logger.info("teta: "+teta);
					writer.println("c_i + (u_ij/split): "+(c_i + (u_ij/split)));
					writer.println("ni: "+ni);
					writer.println("z_i - (f_ij/split): "+(z_i - (f_ij/split)));
					writer.println("teta: "+teta);
					
					//check the constraints about ni and teta
					if(c_i + (u_ij/split) < ni && z_i - (f_ij/split) > teta){
						
						logger.info("ADD DEVID: "+devId+" to Fjr<----------------------------");
						writer.println("add devId: "+devId+" to Fjr<----------------------------");
						writer.println();
						Fjr.add(devId);
					}
					System.out.println();
					writer.println("");
					writer.println("");
				}
			}
		}
		
		if(Fjr.isEmpty()) writer.println("NO THING found for service: "+ reqServiceName.toUpperCase() + "<----------------------------------");
		
		System.out.println();
		System.out.println();
		writer.println("##########################################################");
		writer.println("##########################################################");
		writer.println("##########################################################");


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
		
//		logger.info("c_ij/b_i: "+(enCost/battery));
//		logger.info("h/p_j: "+coeff);
		
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
			Request req = new Request();
			
			req.setOpType(reqEntry.getRight().getOpType());
			req.setLocationRequirement(reqEntry.getRight().getLocationRequirement());
			req.setQosRequirements(reqEntry.getRight().getQosRequirements());
			
			List<String> servList = new ArrayList<>(); 
			servList.addAll(reqEntry.getRight().getRequiredServicesNameList());
			
			req.setRequiredServicesNameList(servList);
			
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

}
