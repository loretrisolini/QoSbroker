package it.unipi.iotplatform.qosbroker.qoscalculator.impl;

import it.unipi.iotplatform.qosbroker.api.datamodel.AllocationObj;
import it.unipi.iotplatform.qosbroker.api.datamodel.AllocationObj.AllocationInfo;
import it.unipi.iotplatform.qosbroker.api.datamodel.AllocationPolicy;
import it.unipi.iotplatform.qosbroker.api.datamodel.NormalizedEnergyCost;
import it.unipi.iotplatform.qosbroker.api.datamodel.Priority;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSCode;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSReasonPhrase;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.api.datamodel.Reserveobj;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServicePeriodParams;
import it.unipi.iotplatform.qosbroker.api.datamodel.SplitPolicy;
import it.unipi.iotplatform.qosbroker.api.datamodel.Statistics;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingAssignmentParams;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.api.datamodel.Utilization;
import it.unipi.iotplatform.qosbroker.api.utils.Utils;
import it.unipi.iotplatform.qosbroker.qoscalculator.api.QoSCalculatorIF;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;
import eu.neclab.iotplatform.ngsi.api.datamodel.Polygon;
import eu.neclab.iotplatform.ngsi.api.datamodel.StatusCode;

public class QoSCalculator implements QoSCalculatorIF {

	private ReservationResults reservationResults;
	
	private final WRRPolicy policyManager = new WRRPolicy();
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(QoSCalculator.class);

	private String operationType;
	private String assignmentTransId;
	private String assignmentServiceName;
	private Integer c_ij_sp_Sum;
	private Integer c_ij_sp_MaxPriority;
	
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
			HashMap<String, Thing> thingsInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList,
			double epsilon) {
		
		Reserveobj[] res = new Reserveobj[2];
		
		ReservationResults ret = new ReservationResults();

		
		//Map<transId, List<DevId>>
		HashMap<String, List<String>> matrixM = createMatrixM(requests,thingsInfo,servNameThingsIdList);
		if(matrixM == null){
			StatusCode statusCode= new StatusCode(QoSCode.SERVICEALLOCATIONFAILED_502.getCode(),
					QoSReasonPhrase.SERVICEALLOCATIONFAILED_502.name(), "QoSCalculator -- computeAllocation() " + operationStatus);
			ret.setStatusCode(statusCode);
			
			operationStatus="";
			return ret;
		}
		
		//Map<DevId, Map<transId::ServName ,f_ij>>>
		HashMap<String,HashMap<String, Double>> matrixF_ij = createF_ij(thingsInfo, servPeriodsMap);
		if(matrixF_ij == null){
			StatusCode statusCode= new StatusCode(QoSCode.SERVICEALLOCATIONFAILED_502.getCode(),
					QoSReasonPhrase.SERVICEALLOCATIONFAILED_502.name(), "QoSCalculator -- computeAllocation() f_ij is null");
			ret.setStatusCode(statusCode);

			return ret;
		}
		
		//Map<DevId, Map<transId::ServName ,u_ij>>>
		HashMap<String,HashMap<String, Double>> matrixU_ij = createU_ij(thingsInfo, servPeriodsMap);
		if(matrixU_ij == null){
			StatusCode statusCode= new StatusCode(QoSCode.SERVICEALLOCATIONFAILED_502.getCode(),
					QoSReasonPhrase.SERVICEALLOCATIONFAILED_502.name(), "QoSCalculator -- computeAllocation() u_ij is null");
			ret.setStatusCode(statusCode);

			return ret;
		}
		
		try{

			AllocationPolicy allocPolicy = AllocationPolicy.WRoundRobin;
			Priority prio = Priority.BATTERY;
			res[0].setAllocPolicy(allocPolicy);
			res[0].setPriority(prio);
			
			//Map<DevId, Map<transId::ServName ,p_ij>>>
			HashMap<String,HashMap<String,Double>> matrixP_ij = matrixF_ij;

			//execution with p_ij=f_ij
			res[0] = ABGAP(k, requests, matrixP_ij, matrixF_ij, matrixU_ij, servPeriodsMap, thingsInfo, servNameThingsIdList, matrixM, epsilon, prio);

			
//			res[0].operationStatus = operationStatus;
			
			prio = Priority.UTILIZATION;
			
			//Map<DevId, Map<transId::ServName ,p_ij>>>
			matrixP_ij = matrixF_ij;
			
			//execution with p_ij=u_ij
			res[1] = ABGAP(k, requests, matrixP_ij, matrixF_ij, matrixU_ij, servPeriodsMap, thingsInfo, servNameThingsIdList, matrixM, epsilon, prio);

			
//			res[1].operationStatus = operationStatus;
			
			prio = Priority.RANDOM;
			//execution with p_ij=random_double
//			res[2] = ABGAP(k, requests, matrixF_ij, matrixU_ij, servPeriodsMap, thingsInfo, servNameThingsIdList, matrixM, epsilon, prio, policy);
			
//			res[2].operationStatus = operationStatus;
			
			int imax=0;
			// Gets the best heuristic
			for(int j=1;j<2;j++)
			{
//				if(res[imax].theta<res[j].theta && res[j].feasible)
//					imax = j;

			}
//			if(res[imax].feasible){
//				ret.setFeas(true);
//				
//				ret.setWhich(imax);
//				
//				ret.setRes(res);
//				
//				StatusCode statusCode= new StatusCode(QoSCode.OK_200.getCode(),QoSReasonPhrase.OK_200.name(), "QoSCalculator -- computeAllocation()" + res[imax].operationStatus);
//				ret.setStatusCode(statusCode);
//				operationStatus = "";
//				
//				return ret;
//			}
			
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
		
		reservationResults = ret;
		
		return ret;
	}

	/* function to create the utilization matrix u_ij */
	private HashMap<String,HashMap<String, Double>> createU_ij(
			HashMap<String, Thing> thingsInfo,
			HashMap<String, ServicePeriodParams> servPeriodsMap) {
		
		//Map<DevId, Map<transId::Service, u_ij>>
		HashMap<String,HashMap<String, Double>> U_ij = new HashMap<>();
		
		for(Map.Entry<String, Thing> entryThing: thingsInfo.entrySet()){
			
			String devId = entryThing.getKey();
			
			Thing t = entryThing.getValue();
			
			HashMap<String, ServiceFeatures> services = t.getServicesList();
			
			//for each transId::Service there is a u_ij
			HashMap<String, Double> transIdServiceMap = new HashMap<>();
			
			//Map<transId, <h/p_j, p_j>>
			for(Map.Entry<String, ServicePeriodParams> entryPeriod: servPeriodsMap.entrySet()){
				
				String transId = entryPeriod.getKey();
				
				Double p_j = entryPeriod.getValue().getPeriod();
				
				//Map<ServiceName, <c_ij, t_ij>>
				for(Map.Entry<String, ServiceFeatures> entryServ: services.entrySet()){
					
					String service = entryServ.getKey();
					
					if(entryServ.getValue() != null){
						
						if(entryServ.getValue().getLatency()!=null){
							Double t_ij = entryServ.getValue().getLatency();

							Double u_ij = t_ij/p_j;

							transIdServiceMap.put(transId+"::"+service, u_ij);
						}
					}
				}
				
			}
			
			if(!transIdServiceMap.isEmpty()){
				U_ij.put(devId, transIdServiceMap);
			}
		}
		
		return U_ij;
	}

	/* function to create the utilization matrix f_ij */
	private HashMap<String,HashMap<String, Double>> createF_ij(
			HashMap<String, Thing> thingsInfo,
			HashMap<String, ServicePeriodParams> servPeriodsMap) {
		
		//Map<DevId, Map<transId::Service,  f_ij>>
		HashMap<String,HashMap<String, Double>> F_ij = new HashMap<>();
		
		for(Map.Entry<String, Thing> entryThing: thingsInfo.entrySet()){
			
			String devId = entryThing.getKey();
			
			Thing t = entryThing.getValue();
			
			Double b_i = 0.0;
			if(t.getBatteryLevel()!=null){
				b_i=t.getBatteryLevel()/100;
			}
			else{
				//continue on the next thing
				continue;
			}
			
			HashMap<String, ServiceFeatures> services = t.getServicesList();
			
			//for each transId::Service there is a u_ij
			HashMap<String, Double> transIdServiceMap = new HashMap<>();
			
			//Map<transId, <h/p_j, p_j>>
			for(Map.Entry<String, ServicePeriodParams> entryPeriod: servPeriodsMap.entrySet()){
				
				String transId = entryPeriod.getKey();
				
				Integer hp_j = entryPeriod.getValue().getNj();
				
				//Map<ServiceName, <c_ij, t_ij>>
				for(Map.Entry<String, ServiceFeatures> entryServ: services.entrySet()){
					
					String service = entryServ.getKey();
					
					if(entryServ.getValue() != null){
						
						if(entryServ.getValue().getEnergyCost()!=null){
							Double c_ij = entryServ.getValue().getEnergyCost();
							
							Double f_ij = hp_j*c_ij/b_i;

							transIdServiceMap.put(transId+"::"+service, f_ij);
						}
					}
				}

			}
			
			if(!transIdServiceMap.isEmpty()){
				F_ij.put(devId, transIdServiceMap);
			}
		}
		
		return F_ij;
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
			HashMap<String,HashMap<String, Double>> matrixP_ij,
			HashMap<String,HashMap<String,List<NormalizedEnergyCost>>> matrixF_ij,
			HashMap<String,HashMap<String,List<Utilization>>> matrixU_ij,
			HashMap<String, ServicePeriodParams> servPeriodsMap,
			HashMap<String, Thing> thingsInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList,
			HashMap<String, List<String>> matrixM,
			double epsilon,
			Priority prio) throws IOException,UnsupportedEncodingException,FileNotFoundException{

		Reserveobj res = null;
		double upper = 1.0;
		double lower = 0.0;
		double teta = 0;
		double z = 0;
		System.out.println("teta = "+teta);
		
		Statistics.printInputsABGAP(k, requests, matrixF_ij, matrixU_ij, servPeriodsMap, thingsInfo, servNameThingsIdList, matrixM, prio.name());
		
		res = GAP(k, requests, matrixP_ij, matrixF_ij, matrixU_ij, servPeriodsMap, thingsInfo, servNameThingsIdList, matrixM, teta, prio);
	
		if(res.isFeasible() == true)
		{
			teta = (upper - lower) / 2;
			while((upper - lower) > epsilon)
			{
				System.out.println("teta = "+teta);
				
				res = GAP(k, requests, matrixP_ij, matrixF_ij, matrixU_ij, servPeriodsMap, thingsInfo, servNameThingsIdList, matrixM, teta, prio);
	
				if(res.isFeasible())
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
			if(!res.isFeasible()){
				teta = z;
				System.out.println("teta = "+teta);
				
				res = GAP(k, requests, matrixP_ij, matrixF_ij, matrixU_ij, servPeriodsMap, thingsInfo, servNameThingsIdList, matrixM, teta, prio);
			}
		}

		res.setPriority(prio);
		res.setTheta(z);
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
			HashMap<String,HashMap<String, Double>> matrixP_ij,
			HashMap<String,HashMap<String, Double>> matrixF_ij,
			HashMap<String,HashMap<String, Double>> matrixU_ij,
			HashMap<String, ServicePeriodParams> servPeriodsMap,
			HashMap<String, Thing> thingsInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList,
			HashMap<String, List<String>> matrixM,
			double teta,
			Priority prio) throws IOException, UnsupportedEncodingException,FileNotFoundException{

		//object to store reservation results
		Reserveobj res = new Reserveobj();
		
		//Backup of the requests list List<transId, Request>
		List<Pair<String, Request>> requestsBck = cloneRequests(requests);
		
		List<Pair<String, Request>> requestsBckLoc = new ArrayList<>();
		if(prio != Priority.BATTERY){
			//backup of the requests list List<transId, Request>
			//for the local optimization
			requestsBckLoc = cloneRequests(requests);
		}
		
		//create Map<devId, <c_i, z_i>> from the eqThingInfo Map<DevId, Thing>
		HashMap<String, ThingAssignmentParams> assignmentParamsMap = createAssignmentParamsMap(thingsInfo);
		
		Double pow = Math.pow(2, (double)1/k);
		//upper bound for utilization
		Double ni = k*(pow-1);
		logger.info("ni= "+ni);
		logger.info("TETA= "+teta);
		res.setFeasible(true);
		
		double ds, d = Double.NEGATIVE_INFINITY;
		
		double INF = Double.POSITIVE_INFINITY;
		
		//List of devId that satisfy the constraints
		//as <i, C_ij, p_ij> where c_ij says
		//how many times can assign that job (or splitted job)
		//to that thing
		List<Pair<String, Integer>> Fj_sp;
		
		//file for debugging
		File file = new File("/home/lorenzo/Downloads/FIWARE-WORK/git/QoSbroker/Tests/"+Statistics.testFolder+Statistics.r);
		if(!file.exists()) file.mkdir();
		writer = new PrintWriter(file.getAbsolutePath()+"/GapResult"+Statistics.count+""+prio.name()+".txt", "UTF-8");
		
		logger.info("PRIORITY: "+prio.name());
		System.out.println();
		
		//index to identify the position of the ServiceObject 
		//on which an assignment is executed
		int requestIndex = 0;
		int serviceIndex = 0;
		
		//iterate over the service requests as List<<transId, Request>>
		//(while isFeas & k!=0)
		while(res.isFeasible() && !requestsBck.isEmpty()){
			
			ds = -1 * INF;

			//iterate over the list of request identify by transId
			//(for each j in K)
			for(int req=0; req<requestsBck.size() && res.isFeasible(); req++){
				
				Pair<String, Request> servRequest = requestsBck.get(req);
				
				//get the transaId that identify a request with multiple
				//service requests
				String transId = servRequest.getLeft();
				
				//Request object contains
				//operationType, QoSRequirements, LocationRequirements,
				//required service list
				Request requestObj = servRequest.getRight();
				
				String opType = requestObj.getOpType();
				
				System.out.println();
				System.out.println("##########################################################");
				writer.println();
				writer.println("request with TransId: "+transId);
				logger.info("request with TransId: "+transId);
				writer.println();
				writer.println("opType: "+opType);
				logger.info("opType: "+opType);
				System.out.println("##########################################################");
				System.out.println();
				
				//Get the list of required services for this request
				List<String> reqServicesList = requestObj.getRequiredServicesNameList();
				
				//iterate over the List of required services in the request
				//identified by transId
				//(for each j in K)
				for(int s=0; s<reqServicesList.size() && res.isFeasible() ; s++){
					
					String reqServiceName = reqServicesList.get(s);
					
					//get the list<DevId> for that reqServName
					//this list represents the list of equivalent things
					List<String> eqThings = servNameThingsIdList.get(reqServiceName).getEqThings();
					
					writer.println();
					writer.println();
					
					System.out.println();
					System.out.println("##########################################################");
					logger.info("ServiceRequest Name: "+reqServiceName.toUpperCase() +
									" inside request with TransId: "+transId);
					writer.println("ServiceRequest Name: "+reqServiceName.toUpperCase());
					System.out.println("##########################################################");
					System.out.println();
					
					//coefficientMap ha as key the transId
					//so i get the first elem of array transId_servId
					List<Integer> Sj = factorization(servPeriodsMap.get(transId).getNj());
					
					Integer split = 1;
					
					//iterate over the list of split factors
					//of a service on multiple things
					while(!Sj.isEmpty()){
						
						System.out.println();
						System.out.println("##########################################################");
						logger.info("split factor = "+String.valueOf(split)+" TETA: "+teta);
						writer.println();
						writer.println("split factor = "+String.valueOf(split)+" TETA: "+teta);
						System.out.println("##########################################################");
						System.out.println();
	
						//List of devId that satisfy
						//the constraints about
						//utilization and residual battery
						
						Fj_sp = checkConstraints(assignmentParamsMap, eqThings, matrixP_ij, matrixF_ij, matrixU_ij,
								matrixM.get(transId+"::"+reqServiceName), transId, reqServiceName, 
								split, ni, teta, null);
								
						System.out.println();
						System.out.println("##########################################################");
						writer.println("Fj_sp: "+Fj_sp);
						logger.info("Fj_sp: "+Fj_sp);
						System.out.println("##########################################################");
						
						//there is no thing that satisfy the
						//requirements
						//if(Fj_sp = 0 || sum(c_ij_sp) < split)
						if(c_ij_sp_Sum < split){
							
							System.out.println("##########################################################");
							logger.info("SPLIT FACTOR: "+split);
							logger.info("ServiceRequest Name: "+reqServiceName.toUpperCase()+
									" inside request with TransId: "+transId);
							logger.info("NO THINGS FOUND, TETA: "+teta);
							System.out.println("##########################################################");
							
							writer.println("SPLIT FACTOR: "+split);
							writer.println("ServiceRequest Name: "+reqServiceName.toUpperCase()+
									" inside request with TransId: "+transId);
							writer.println("NO THINGS FOUND, TETA: "+teta);
							
							operationStatus = "QoSCalculator -- GAP() ServiceRequest Name: "+reqServiceName+
									" inside request with TransId: "+transId+" NO THINGS FOUND, "
									+ "split factor: "+String.valueOf(split)+", TETA: "+teta;
							
							Sj.remove(split);
							
							if(Sj.isEmpty()) res.setFeasible(false);
							else
								split = Sj.get(0);
							
							continue;
						}

						
						//get the devId of the Thing that have max priority
						//at the same time set maxPriority and secondMaxPriority
						//Fjsp is ordered on decreasing priority
						//i' = argMax{P_ij: i in Fj_sp}
						String devId_maxPriority = Fj_sp.get(0).getLeft();
								
						if(devId_maxPriority == null){
							res.setFeasible(false);
							
							writer.println(operationStatus);
							writer.close();
							return res;
						}
						
						System.out.println();
						System.out.println("##########################################################");
						logger.info("devId with max priority: " + devId_maxPriority);
						System.out.println("##########################################################");
						System.out.println();
						writer.println("devId with max priority: " + devId_maxPriority);
						
						//verify conditions for direct assignment
						if((c_ij_sp_Sum - c_ij_sp_MaxPriority) < split){
							ds = INF;
							
							//TODO Allocation set Wij, c_i z_i
							
							
							
							//remove service in the list of the request object
							//request obj index is requestIndex, service index is serviceIndex
							requestsBck.get(requestIndex).getRight().getRequiredServicesNameList().remove(serviceIndex);
							
							if(requestsBck.get(requestIndex).getRight().getRequiredServicesNameList().isEmpty()){
								requestsBck.remove(requestIndex);
							}
							
							break;
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
						
						if(d > ds){
							
							logger.info("d > ds: "+ (d > ds));

							ds = d;
							
							//set a new AllocationObj if the transId or the service is changed 
							//or if the AllocationObj is null
							if(assignmentTransId!=null && !assignmentTransId.contentEquals(transId) || 
									assignmentServiceName!=null && !assignmentServiceName.contentEquals(reqServiceName) ||
									allocationTemp == null){
								allocationTemp = new AllocationObj();
							}
							
							//TODO Allocation set Wij
							
							System.out.println();
							System.out.println("##########################################################");
							System.out.println("TEMP allocationObj");
							System.out.println("transId: "+assignmentTransId);
							System.out.println("serviceName: "+assignmentServiceName.toUpperCase());
							System.out.println(allocationTemp.toString()+" TETA: "+teta);
							System.out.println("##########################################################");
							System.out.println();
							writer.println();
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println("TEMP allocationObj<----------------------------");
							writer.println("transId: "+assignmentTransId);
							writer.println("serviceName: "+assignmentServiceName.toUpperCase());
							writer.println(allocationTemp.toString()+" TETA: "+teta);
							writer.println("##########################################################");
							writer.println("##########################################################");
							
							//store the index of the service for which
							//a list of things has been assigned
							requestIndex = req;
							serviceIndex = s;
							
							//exit from the cycle of Sj
							//beacuse a feasible allocation is found
							break;
						}
						else
							//exit from the cycle of Sj
							//beacuse a feasible allocation is found
							break;
					
					}
				
				}
				
				if(ds == INF) break;
			}
				
			if(res.isFeasible()){
				
				AllocationObj allocation = new AllocationObj();
				
				HashMap<String, HashMap<String, AllocationObj>> allocationSchema = res.getAllocationSchema();
				
				//update the allocationSchema with the new allocation for the service 
				//Map<transId, Map<service, List<devId>>
				if(allocationSchema.get(assignmentTransId) == null){
					allocationSchema.put(assignmentTransId, new HashMap<String, AllocationObj>());
				}

				allocation.setSplit(new Integer(allocationTemp.getSplit()));
				
				//TODO Allocation set Wij, c_i z_i from T_star

				System.out.println();
				System.out.println("##########################################################");
				System.out.println("##########################################################");
				logger.info("FINAL ALLOCATION transId="+assignmentTransId);
				logger.info("and servName="+assignmentServiceName.toUpperCase()+" TETA: "+teta);
				logger.info(allocation.toString());
				System.out.println("##########################################################");
				System.out.println("##########################################################");
				System.out.println();

				writer.println();
				writer.println("##########################################################");
				writer.println("##########################################################");
				writer.println("##########################################################");
				writer.println("##########################################################");

				writer.println("FINAL ALLOCATION transId="+assignmentTransId+
								"\nand servName="+assignmentServiceName.toUpperCase()+" TETA: "+teta+"<----------------------------");
				writer.println(allocation.toString());

				writer.println("##########################################################");
				writer.println("##########################################################");
				writer.println("##########################################################");
				writer.println("##########################################################");

				writer.println();
				

				writer.println("<--------------------------->");
				writer.println("<--------------------------->");
				writer.println();
				
				//remove service in the list of the request object
				//request obj index is requestIndex, service index is serviceIndex
				requestsBck.get(requestIndex).getRight().getRequiredServicesNameList().remove(serviceIndex);
				
				if(requestsBck.get(requestIndex).getRight().getRequiredServicesNameList().isEmpty()){
					requestsBck.remove(requestIndex);
				}
				
				allocationTemp = null;

			}
			else{
				System.out.println();
				System.out.println("##########################################################");
				logger.info("ALLOCATION FAILED, TETA: "+teta +"<--------------------------------------");
				System.out.println("##########################################################");
				System.out.println();
				
				writer.println();
				writer.println();
				writer.println("ALLOCATION FAILED, TETA: "+teta +"<------------------------------------");
				writer.close();
				return res;
			}
		}
		
//		//Local optimization
//		if(prio != Priority.BATTERY && res.feasible){
//			
//			writer.println("##########################################################");
//			writer.println("##########################################################");
//			writer.println("##########################################################");
//			System.out.println();
//			System.out.println("##########################################################");
//			logger.info("Local Optimization");
//			writer.println("Local Optimization");
//			
//			//iterate over the list of request identify by transId
//			for(Pair<String, Request> servRequest : requestsBckLoc){
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
//				System.out.println();
//				logger.info("request with TransId: "+transId);
//				writer.println("request with TransId: "+transId);
//				
//				//Get the list of required services for this request
//				List<String> reqServicesList = requestObj.getRequiredServicesNameList();
//				
//				//iterate over the List of required services in the request
//				//identified by transId
//				for(String reqServiceName: reqServicesList){
//					
//					System.out.println();
//					logger.info("ServiceRequest Name: "+ reqServiceName.toUpperCase() +
//							" inside request with TransId: "+transId);
//					writer.println("ServiceRequest Name: "+ reqServiceName.toUpperCase());
//					
//					//get the list<DevId> for that reqServName
//					//this list represents the list of equivalent things
//					List<String> eqThings = servNameThingsIdList.get(reqServiceName).getEqThings();
//					
//					//get the Allocation associated to the pair transId, servId
//					allocationTemp = res.allocationSchema.get(transId).get(reqServiceName);
//					
//					//get the split factor for that allocation
//					int split = allocationTemp.getSplit();
//					
//					System.out.println();
//					logger.info("split factor="+String.valueOf(split));
//					writer.println("split factor="+String.valueOf(split));
//					
//					for(int s = 0; s<split; s++){
//					
//						String devId_substitution = allocationTemp.getDevIdList()[s];
//						
//						System.out.println();
//						logger.info("try to substitute the thing with id= "+devId_substitution);
//						writer.println("try to substitute the thing with id= "+devId_substitution);
//						
//						//check the constraints excluding the thing with id thingId_sub
//						Fjr = checkConstraints(assignmentParamsMap, eqThings, matrixF_ij, matrixU_ij, 
//								matrixM.get(transId+"::"+reqServiceName), transId, reqServiceName, 
//								split, ni, teta, devId_substitution);
//						
//						logger.info("Fjr: "+Fjr);
//						
//						if(Fjr.isEmpty()) continue;
//						
//						//get the id (from Fjr) of the thing with max residualBattery
//						String devId_star = getArgMax(Fjr, matrixF_ij, matrixU_ij, prio, 
//								reqServiceName, transId, split, assignmentParamsMap, false);
//						
//						if(devId_star == null){
//							res.feasible = false;
//							
//							writer.println(operationStatus);
//							writer.close();
//							return res;
//						}
//						
//						logger.info("thingId* with max residual battery="+devId_star);
//						writer.println("thingId* with max residual battery="+devId_star);
//						logger.info("max residual battery value="+String.valueOf(maxResidualBattery));
//						writer.println("max residual battery value="+String.valueOf(maxResidualBattery));
//						
//						//z_i'-f_i'j residual battery value of the thing to substitute
//						double residualBatt_substitution = assignmentParamsMap.get(devId_substitution).getResidualBattery();
//						logger.info("residual battery value of thing to substitute="+String.valueOf(residualBatt_substitution));
//						writer.println("residual battery value of thing to substitute="+String.valueOf(residualBatt_substitution));
//						
//						//if the max value of residual battery is greater than
//						//the value of residual battery of the thing to substitute
//						//there is a change in the allocation
//						if(maxResidualBattery > residualBatt_substitution){
//							
//							writer.println("##########################################################");
//							writer.println("##########################################################");
//							writer.println("##########################################################");
//							System.out.println();
//							System.out.println("##########################################################");
//							logger.info("remove allocation of service "+reqServiceName);
//							logger.info("to devId "+devId_substitution);
//							logger.info("and assigned to "+devId_star);
//							writer.println("remove allocation of service "+reqServiceName);
//							writer.println("to devId "+devId_substitution);
//							writer.println("and assigned to "+devId_star);
//							writer.println("##########################################################");
//							writer.println("##########################################################");
//							writer.println("##########################################################");
//
//							
//							//update the value of the thing substituted
//							//c_i-(u_ij/split) and z_i+(f_ij/split)
//							//using -split
//							logger.info("c_i-(u_ij/split) and z_i+(f_ij/split) devId_substitution "+devId_substitution);
//							writer.println("c_i-(u_ij/split) and z_i+(f_ij/split) devId_substitution "+devId_substitution);
//							Double oldF_ij = allocationTemp.getF_ij()[s];
//							Double oldU_ij = allocationTemp.getU_ij()[s];
//							split = allocationTemp.getSplit();
//							updateAssignmentsParams(assignmentParamsMap, oldF_ij, oldU_ij, split, devId_substitution, -1);
//
//							
//							//compute pair f_ij, u_ij
//							Pair<Double, Double> f_u_ij = computeF_U(devId_star, matrixF_ij, matrixU_ij, transId, reqServiceName);
//							if(f_u_ij == null){
//								res.feasible = false;
//								operationStatus = "QoSCalculator -- GAP() LocalOptimization f_u_ij null for service: "+reqServiceName;
//								
//								writer.println(operationStatus);
//								writer.close();
//								return res;
//							}
//							
//							Double newF_ij = f_u_ij.getLeft();
//							Double newU_ij = f_u_ij.getRight();
//							//add the new thing, with new f_ij, new u_ij
//							allocationTemp.getDevIdList()[s] = devId_star;
//							allocationTemp.getF_ij()[s] = newF_ij;
//							allocationTemp.getU_ij()[s] = newU_ij;
//							
//							logger.info("c_i+(u_ij/split) and z_i-(f_ij/split) devId_star "+devId_star);
//							writer.println("c_i+(u_ij/split) and z_i-(f_ij/split) devId_star "+devId_star);
//							//update the value of the new thing allocated
//							//c_i+(u_ij/split) and z_i-(f_ij/split)
//							updateAssignmentsParams(assignmentParamsMap, newF_ij, newU_ij, split, devId_star, 1);
//							
//							System.out.println("##########################################################");
//							System.out.println();
//							
//							//update the allocation in reservation object
//							res.allocationSchema.get(transId).put(reqServiceName, allocationTemp);
//
//							Fjr.clear();
//						}
//						
//						maxResidualBattery = Double.NEGATIVE_INFINITY;
//						
//					}
//				}
//			}
//			writer.println("##########################################################");
//			writer.println("##########################################################");
//			writer.println("##########################################################");
//		}
	
		res.setAssignmentsParamsMap(assignmentParamsMap);
		
		writer.println();
		writer.println();
		writer.println("ALLOCATION COMPLETED, TETA: "+teta +"<-------------------------------------");
		writer.close();
		
		operationStatus = "QoSCalculator -- GAP() allocation operation OK, TETA: "+teta;
		
		return res;
	}

	/* function to set the allocation object */
	private Boolean setAllocationObj(
			String transId,
			String devId,
			String reqServiceName,
			HashMap<String, HashMap<String, List<NormalizedEnergyCost>>> matrixF_ij,
			HashMap<String, HashMap<String, List<Utilization>>> matrixU_ij,
			int pos, Integer split) {
		
		//allocation of the service given by transId, reqServName 
		//to a thing with a thingService thingId, thingServiceId
		assignmentTransId = transId;
		
		assignmentServiceName = reqServiceName;
		
		
		
		return true;
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

	}



	/* function to create a table that say the list of DevId of the
	 * things that respect the restriction of a request identified
	 * by the transactionId (Map<transId, List<DevId>>) */
	private HashMap<String, List<String>> createMatrixM(
			List<Pair<String, Request>> requests,
			HashMap<String, Thing> thingsInfo,
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
			
			if(request.getLocationRequirementPoint()!=null){
				point = request.getLocationRequirementPoint().getLocationRequirement();
				
			}
			else{
				if(request.getLocationRequirementCircle()!=null){
					circle = request.getLocationRequirementCircle().getLocationRequirement();
					
				}
				if(request.getLocationRequirementPolygon()!=null){
					polygon = request.getLocationRequirementPolygon().getLocationRequirement();
					
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
				for(int i=1; i<=eqThings.size(); i++){
					
					String devId = eqThings.get(i-1);
					
					//get the thing
					Thing t = thingsInfo.get(devId);
					
					//get the coords of the thing
					Point coords = t.getCoords();
					
					//check location requirement
					if(coords != null){
						if(point != null){
							if(coords.getLatitude() != point.getLatitude() ||
									coords.getLongitude() != point.getLongitude()){
								eqThings.remove(i-1);
								i--;
								continue;
							}
						}
						else{
							if(circle != null){
								if(!Utils.in_circle(circle, coords)){
									eqThings.remove(i-1);
									i--;
									continue;
								}
							}
							else{
								if(!Utils.in_polygon(polygon, coords)){
									eqThings.remove(i-1);
									i--;
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
							if(maxRespTime!=null && latency != null && latency > maxRespTime || maxRespTime!=null && latency == null
									|| accuracy!=null && servAccuracy != null && servAccuracy < accuracy || accuracy!=null && servAccuracy == null ){
								eqThings.remove(i-1);
								i--;
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



	
	/* function to get the max priority */
//	private String getArgPrioriry(List<Pair<String, Integer>> Fj_sp,
//			HashMap<String,HashMap<String,List<NormalizedEnergyCost>>> matrixF_ij,
//			HashMap<String,HashMap<String,List<Utilization>>> matrixU_ij, 
//			Priority prio,
//			String reqServiceName, String transactionId, int split) {
//		
//		maxPriority = -1 * Double.POSITIVE_INFINITY;
//		c_ij_sp_MaxPriority = 0;
//		
//		String devId_argMax = null; 
//		
//		for(Pair<String, Integer> pair: Fj_sp){
//			
//			String devId = pair.getLeft();
//			Integer c_ij_sp = pair.getRight(); 
//			
//			//compute pair f_ij, u_ij
//			Pair<Double, Double> f_u_ij = computeF_U(devId, matrixF_ij, matrixU_ij, transactionId, reqServiceName);
//			if(f_u_ij == null){
//				operationStatus = "QoSCalculator -- getArgMax() f_u_ij null for service: "+reqServiceName;
//				return null;
//			}
//			
//			Double f_ij = f_u_ij.getLeft();
//			Double u_ij = f_u_ij.getRight();
//			
//			Double p_ij = null;
//			
//
//			
//			if(p_ij > maxPriority){
//				
//				maxPriority = p_ij;
//				c_ij_sp_MaxPriority = c_ij_sp;
//				
//				devId_argMax = devId;
//			}
//
//		}
//
//		return devId_argMax;
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
	 * @return the Map<devid, <C_ij_sp, p_ij>> that respect the constraints
	 */
	private List<Pair<String, Integer>> checkConstraints(
			HashMap<String, ThingAssignmentParams> assignmentParamsMap,
			List<String> eqThings,
			HashMap<String,HashMap<String, Double>> matrixP_ij,
			HashMap<String,HashMap<String, Double>> matrixF_ij,
			HashMap<String,HashMap<String, Double>> matrixU_ij,
			List<String> eqThingsTransaction, 
			String transactionId,
			String reqServiceName,
			int split, Double ni,
			double teta, String devIdExcluded) {
		
		//Map<devid, <C_ij_sp, p_ij>>
		List<Pair<String, Integer>> Fj_sp = new ArrayList<>();
		
		//HashMap to sort things based on priority
		HashMap<String, Double> priorityMap = new HashMap<>();
		HashMap<String, Integer> Fj_spTemp = new HashMap<>();
		
		c_ij_sp_Sum = 0;
		
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
					
					Double f_ij = matrixF_ij.get(devId).get(transactionId+"::"+reqServiceName);
					Double u_ij = matrixU_ij.get(devId).get(transactionId+"::"+reqServiceName);
					Double p_ij = matrixU_ij.get(devId).get(transactionId+"::"+reqServiceName);
					
					if(f_ij == null || u_ij == null || p_ij == null){
						operationStatus = "QoSCalculator -- checkConstraints() p_ij or f_ij or u_ij null for service: "+reqServiceName;
						
						continue;
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
					
					//limit for the capacity to be less than ni
					int capacityLimit = (int)Math.floor((ni-c_i)/(u_ij/split));
					
					//limit for the capacity to be greater than teta
					int residualBatteryLimit = (int)Math.floor((z_i-teta)/(f_ij/split));
					
					//how many times can assign the job (or sub job) j
					//to the thing i respecting the constraints
					int c_ij_split = Math.min(capacityLimit, residualBatteryLimit);
					
					logger.info("c_ij_split: "+c_ij_split);
					writer.println("c_ij_split: "+c_ij_split);
					
					//check the constraints about ni and teta
					if(c_ij_split > 0){
						
						c_ij_sp_Sum += c_ij_split;
						
						priorityMap.put(devId, p_ij);
						Fj_spTemp.put(devId, c_ij_split);
						
						logger.info("##########################################################");
						logger.info("ADD DEVID: "+devId+" to Fjr");
						logger.info("c_ij_split: "+c_ij_split);
						logger.info("##########################################################");
						
						writer.println("##########################################################");
						writer.println("add devId: "+devId+" to Fjr");
						writer.println("c_ij_split: "+c_ij_split);
						writer.println("##########################################################");
						writer.println();
						
					}
					System.out.println();
					writer.println("");
					writer.println("");
				}
			}
		}
		
		if(Fj_spTemp.isEmpty()) writer.println("NO THING found for service: "+ reqServiceName.toUpperCase());
		else{
			priorityMap = Utils.sortByValue(priorityMap);
			
			for(Map.Entry<String, Double> entry: priorityMap.entrySet()){
				
				String devId = entry.getKey();
				Integer c_ij_sp = Fj_spTemp.get(devId);
				
				Fj_sp.add(new Pair<String, Integer>(devId, c_ij_sp));
			}
		}
		
		System.out.println();
		System.out.println();
		writer.println("##########################################################");
		writer.println("##########################################################");
		writer.println("##########################################################");


		return Fj_sp;
	}

//	private Pair<Double, Double> computeF_U(String devId,
//			HashMap<String,HashMap<String,List<NormalizedEnergyCost>>> matrixF_ij,
//			HashMap<String,HashMap<String,List<Utilization>>> matrixU_ij,
//			String transactionId,
//			String reqServiceName) {
//			
//		Double f_ij = null;
//		Double u_ij = null;
//		if(matrixF_ij.get(devId)!=null && matrixF_ij.get(devId).get(transactionId)!=null){
//			
//			List<NormalizedEnergyCost> normEnCostList = matrixF_ij.get(devId).get(transactionId);
//			
//			for(NormalizedEnergyCost f: normEnCostList){
//				if(f.getService().contentEquals(reqServiceName)){
//					f_ij = f.getF_ij();
//					break;
//				}
//			}
//		}
//		
//		if(matrixU_ij.get(devId)!=null && matrixU_ij.get(devId).get(transactionId)!=null){
//			
//			List<Utilization> utList = matrixU_ij.get(devId).get(transactionId);
//			
//			for(Utilization u: utList){
//				if(u.getService().contentEquals(reqServiceName)){
//					u_ij = u.getU_ij();
//					break;
//				}
//			}
//		}
//		if(f_ij==null || u_ij==null){
//			operationStatus = "QoSCalculator -- computeF_U() f_u_ij null for service: "+reqServiceName;
//			
//			return null;
//		}
//		
//		return new Pair<Double, Double>(f_ij, u_ij);
//	}


	/* function to compute Map<DevId, <c_i, z_i>> */
	private HashMap<String, ThingAssignmentParams> createAssignmentParamsMap(
			HashMap<String, Thing> thingsInfo) {
		
		HashMap<String, ThingAssignmentParams> assignmentParamsMap = new HashMap<>();
		
		for(String devId: thingsInfo.keySet()){
			
			Double batt = thingsInfo.get(devId).getBatteryLevel();
			
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
			req.setLocationRequirementPoint(reqEntry.getRight().getLocationRequirementPoint());
			req.setLocationRequirementCircle(reqEntry.getRight().getLocationRequirementCircle());
			req.setLocationRequirementPolygon(reqEntry.getRight().getLocationRequirementPolygon());
			req.setQosRequirements(reqEntry.getRight().getQosRequirements());
			
			List<String> servList = new ArrayList<>(); 
			servList.addAll(reqEntry.getRight().getRequiredServicesNameList());
			
			req.setRequiredServicesNameList(servList);
			
			reqListBck.add(new Pair<String, Request>(transId, req));
		}
		
		return reqListBck;
	}

	/* function to compute the factorization of a number */
	private List<Integer> factorization(Integer number) {
		
		int n = number;
	    List<Integer> factors = new ArrayList<Integer>();
	    factors.add(1);
	    for (int i = 2; i <= n; i++) {
	      while (n % i == 0) {
	    	if(!factors.contains(i))
	    		factors.add(i);
	        n /= i;
	      }
	    }
	    return factors;
	}
	


	@Override
	public ReservationResults readReservation() {
		
		return this.reservationResults;
	}

	@Override
	public String getNextDevId(String transId, String service) {
		
		if(reservationResults != null){
			
			if(reservationResults.isFeas()){
				
				List<AllocationInfo> devIdList = 
						reservationResults.getRes()[reservationResults.getWhich()]
								.getAllocationSchema().get(transId).get(service).getDeviceAllocaInfoList();
					
				String nextDevId = policyManager.getDevId(transId, service, devIdList);
				
				return nextDevId;
			}
		}

		return null;
	}

}
