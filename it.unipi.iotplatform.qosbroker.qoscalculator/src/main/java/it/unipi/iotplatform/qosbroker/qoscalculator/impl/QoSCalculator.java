package it.unipi.iotplatform.qosbroker.qoscalculator.impl;

import it.unipi.iotplatform.qosbroker.api.datamodel.AllocationInfo;
import it.unipi.iotplatform.qosbroker.api.datamodel.AllocationPolicy;
import it.unipi.iotplatform.qosbroker.api.datamodel.Priority;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSCode;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSConsts;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSReasonPhrase;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.api.datamodel.Reserveobj;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServicePeriodParams;
import it.unipi.iotplatform.qosbroker.api.datamodel.Split;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingAssignmentParams;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.api.utils.Statistics;
import it.unipi.iotplatform.qosbroker.api.utils.Utils;
import it.unipi.iotplatform.qosbroker.couchdb.api.QoSBigDataRepository;
import it.unipi.iotplatform.qosbroker.qoscalculator.api.QoSCalculatorIF;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.google.gson.Gson;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;
import eu.neclab.iotplatform.ngsi.api.datamodel.StatusCode;

public class QoSCalculator implements QoSCalculatorIF {

	private static Reserveobj reservationResults;
	
	private QoSBigDataRepository bigDataRepository;
	
	private final WRRPolicy wrrPolicyManager = new WRRPolicy();
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(QoSCalculator.class);

	private String operationType;
	private String allocationTransId;
	private String allocationServiceName;
	
	//list of priority in decreasing order
	//associated to Fj_sp
	private List<Double> priorityList;
	private Integer c_ij_sp_Sum;
	
//	private Integer c_ij_sp_MaxPriority;
//	
//	private Double maxPriority = 0.0;
//	private Double maxResidualBattery = 0.0;
//	private Double secondMaxPriority = 0.0;
	private String operationStatus = new String("");
	
	//temporary allocation object
	//<transId, servName, List<DevId>>
	private AllocationInfo allocationTemp;
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
			double epsilon,
			Split split) {
		
		Reserveobj[] res = new Reserveobj[2];
		
		ReservationResults ret = new ReservationResults();

		
		//Map<transId::service, List<DevId>>
		//matrix to get list of things
		//that respect restrictions for that transaction and service
		HashMap<String, List<String>> matrixM = createMatrixM(requests,thingsInfo,servNameThingsIdList);
		if(matrixM == null){
			StatusCode statusCode= new StatusCode(QoSCode.SERVICEALLOCATIONFAILED_502.getCode(),
					QoSReasonPhrase.SERVICEALLOCATIONFAILED_502.name(), "QoSCalculator -- computeAllocation() " + operationStatus);
			ret.setStatusCode(statusCode);
			
			operationStatus="";
			return ret;
		}
		
		//Map<DevId, Map<transId::ServName ,f_ij>>>
		//matric of normalized energy cost
		//(matrix F) 
		HashMap<String,HashMap<String, Double>> matrixF = createF(thingsInfo, servPeriodsMap);
		if(matrixF == null){
			StatusCode statusCode= new StatusCode(QoSCode.SERVICEALLOCATIONFAILED_502.getCode(),
					QoSReasonPhrase.SERVICEALLOCATIONFAILED_502.name(), "QoSCalculator -- computeAllocation() f_ij is null");
			ret.setStatusCode(statusCode);

			return ret;
		}
		
		//Map<DevId, Map<transId::ServName ,u_ij>>>
		//matrix of utilization
		//(matrix U)
		HashMap<String,HashMap<String, Double>> matrixU = createU(thingsInfo, servPeriodsMap);
		if(matrixU == null){
			StatusCode statusCode= new StatusCode(QoSCode.SERVICEALLOCATIONFAILED_502.getCode(),
					QoSReasonPhrase.SERVICEALLOCATIONFAILED_502.name(), "QoSCalculator -- computeAllocation() u_ij is null");
			ret.setStatusCode(statusCode);

			return ret;
		}
		
		//Map<transId, h/p_j>
		//matrix of coefficients hyperperiod / periodj
		//the periodj is one for each transId to which are associated
		//multiple services, so all the services in the same requests
		//identified by transId will have the same periodj
		//(matrix PHI)
		HashMap<String, Integer> hyperperiodPeriodMap = new HashMap<>();
		for(Map.Entry<String, ServicePeriodParams> entryPeriod :servPeriodsMap.entrySet()){
			
			//transId
			String transId = entryPeriod.getKey();
			//h/p_j
			Integer nj = entryPeriod.getValue().getNj();
			
			hyperperiodPeriodMap.put(transId, nj);
		}
		
		try{

			AllocationPolicy allocPolicy = AllocationPolicy.WRoundRobin;
			Priority prio = Priority.BATTERY;

			
			//Map<DevId, Map<transId::ServName ,p_ij>>>
			HashMap<String,HashMap<String,Double>> matrixP = matrixF;
			//execution with p_ij=f_ij
			res[0] = ABGAP(k, requests, matrixP, matrixF, matrixU, hyperperiodPeriodMap, thingsInfo, servNameThingsIdList, matrixM, epsilon, prio, split);

			res[0].setAllocPolicy(allocPolicy);
			res[0].setPriority(prio);
			res[0].setOperationStatus(operationStatus);
			
			prio = Priority.UTILIZATION;
			
			//Map<DevId, Map<transId::ServName ,p_ij>>>
			matrixP = matrixF;
			//execution with p_ij=u_ij
			res[1] = ABGAP(k, requests, matrixP, matrixF, matrixU, hyperperiodPeriodMap, thingsInfo, servNameThingsIdList, matrixM, epsilon, prio, split);

			res[1].setAllocPolicy(allocPolicy);
			res[1].setPriority(prio);
			res[1].setOperationStatus(operationStatus);
			
			prio = Priority.RANDOM;
			//execution with p_ij=random_double
//			res[2] = ABGAP(k, requests, matrixF_ij, matrixU_ij, servPeriodsMap, thingsInfo, servNameThingsIdList, matrixM, epsilon, prio, policy);
			
//			res[2].operationStatus = operationStatus;
			
			int imax=0;
			// Gets the best heuristic
			for(int j=1;j<2;j++)
			{
				if(res[imax].getTheta()<res[j].getTheta() && res[j].isFeasible())
					imax = j;

			}
			if(res[imax].isFeasible()){
				ret.setFeas(true);
				
				ret.setWhich(imax);
				
				ret.setRes(res);
				
				StatusCode statusCode= new StatusCode(QoSCode.OK_200.getCode(),QoSReasonPhrase.OK_200.name(), "QoSCalculator -- computeAllocation()" + res[imax].getOperationStatus());
				ret.setStatusCode(statusCode);
				operationStatus = "";
				
				//store reservation results
				reservationResults = res[imax];
				
				//store reservation results
				storeReservationResults();
				
				return ret;
			}
			
		}
		catch(UnsupportedEncodingException e){
			e.printStackTrace();
			operationStatus = " exception UnsupportedEncodingException opening statistics file";
		}
		catch(FileNotFoundException fe){
			fe.printStackTrace();
			operationStatus = " exception FileNotFoundException opening statistics file";
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			operationStatus = " exception IOException opening statistics file";
		}
		
		StatusCode statusCode= new StatusCode(QoSCode.SERVICEALLOCATIONFAILED_502.getCode(),
												QoSReasonPhrase.SERVICEALLOCATIONFAILED_502.name(), "QoSCalculator -- computeAllocation() " + operationStatus);
		ret.setStatusCode(statusCode);
		operationStatus = "";
		
		return ret;
	}

	/* function to create the utilization matrix u_ij */
	private HashMap<String,HashMap<String, Double>> createU(
			HashMap<String, Thing> thingsInfo,
			HashMap<String, ServicePeriodParams> servPeriodsMap) {
		
		//Map<DevId, Map<transId::Service, u_ij>>
		HashMap<String,HashMap<String, Double>> U = new HashMap<>();
		
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
						
						//check if the latency is null
						if(entryServ.getValue().getLatency()!=null){
							Double t_ij = entryServ.getValue().getLatency();

							Double u_ij = t_ij/p_j;

							transIdServiceMap.put(transId+"::"+service, u_ij);
						}
					}
				}
				
			}
			
			if(!transIdServiceMap.isEmpty()){
				U.put(devId, transIdServiceMap);
			}
		}
		
		if(U.isEmpty()) return null;
		else
		return U;
	}

	/* function to create the utilization matrix f_ij */
	private HashMap<String,HashMap<String, Double>> createF(
			HashMap<String, Thing> thingsInfo,
			HashMap<String, ServicePeriodParams> servPeriodsMap) {
		
		//Map<DevId, Map<transId::Service,  f_ij>>
		HashMap<String,HashMap<String, Double>> F = new HashMap<>();
		
		for(Map.Entry<String, Thing> entryThing: thingsInfo.entrySet()){
			
			String devId = entryThing.getKey();
			
			Thing t = entryThing.getValue();
			
			Double b_i = 0.0;
			//check if the batt is null
			if(t.getBatteryLevel()!=null){
				b_i=t.getBatteryLevel()/100;
			}
			else{
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
						
						//check if the energy cost is null
						if(entryServ.getValue().getEnergyCost()!=null){
							Double c_ij = entryServ.getValue().getEnergyCost();
							
							Double f_ij = hp_j*c_ij/b_i;

							transIdServiceMap.put(transId+"::"+service, f_ij);
						}
					}
				}

			}
			
			if(!transIdServiceMap.isEmpty()){
				F.put(devId, transIdServiceMap);
			}
		}
		
		if(F.isEmpty()) return null;
		else
		return F;
	}
	
	/**
	 * Execute the heuristic specifically tailored for battery consumption.
	 *
	 * @param int k, 
	 * @param List<Pair<String, Request>> requests, 
	 * @param HashMap<String, Double> hyperperiodPeriodMap,
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
			HashMap<String,HashMap<String, Double>> matrixP,
			HashMap<String,HashMap<String, Double>> matrixF,
			HashMap<String,HashMap<String, Double>> matrixU,
			HashMap<String, Integer> hyperperiodPeriodMap,
			HashMap<String, Thing> thingsInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList,
			HashMap<String, List<String>> matrixM,
			double epsilon,
			Priority prio, Split split) throws IOException,UnsupportedEncodingException,FileNotFoundException{

		Reserveobj res = null;
		double upper = 1.0;
		double lower = 0.0;
		double teta = 0;
		double z = 0;
		System.out.println("teta = "+teta);
		
		Statistics.printInputsABGAP(k, requests, matrixF, matrixU, hyperperiodPeriodMap, thingsInfo, servNameThingsIdList, matrixM, prio.name());
		
		if(split == Split.MULTI_SPLIT){
			res = GAP(k, requests, matrixP, matrixF, matrixU, hyperperiodPeriodMap, thingsInfo, servNameThingsIdList, matrixM, teta, prio);
		}
		else{
			res = GAP_singleSplit(k, requests, matrixP, matrixF, matrixU, hyperperiodPeriodMap, thingsInfo, servNameThingsIdList, matrixM, teta, prio);
		}
			
		if(res.isFeasible() == true)
		{
			teta = (upper - lower) / 2;
			while((upper - lower) > epsilon)
			{
				System.out.println("teta = "+teta);
				
				if(split == Split.MULTI_SPLIT){
					res = GAP(k, requests, matrixP, matrixF, matrixU, hyperperiodPeriodMap, thingsInfo, servNameThingsIdList, matrixM, teta, prio);
				}
				else{
					res = GAP_singleSplit(k, requests, matrixP, matrixF, matrixU, hyperperiodPeriodMap, thingsInfo, servNameThingsIdList, matrixM, teta, prio);
				}
					
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
				
				if(split == Split.MULTI_SPLIT){
					res = GAP(k, requests, matrixP, matrixF, matrixU, hyperperiodPeriodMap, thingsInfo, servNameThingsIdList, matrixM, teta, prio);
				}
				else{
					res = GAP_singleSplit(k, requests, matrixP, matrixF, matrixU, hyperperiodPeriodMap, thingsInfo, servNameThingsIdList, matrixM, teta, prio);
				}
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
	 * @param HashMap<String, Double> hyperperiodPeriodMap,
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
			HashMap<String,HashMap<String, Double>> matrixP,
			HashMap<String,HashMap<String, Double>> matrixF,
			HashMap<String,HashMap<String, Double>> matrixU,
			HashMap<String, Integer> hyperperiodPeriodMap,
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
		//(for i<-1 to n ci<-0 and zi<-1)
		HashMap<String, ThingAssignmentParams> assignmentParamsMap = createAssignmentParamsMap(thingsInfo);
		
		Double pow = Math.pow(2, (double)1/k);
		//upper bound for utilization
		Double ni = k*(pow-1);
		
		System.out.println();
		System.out.println("##########################################################");
		logger.info("ni= "+ni);
		logger.info("TETA= "+teta);
		System.out.println("##########################################################");
		System.out.println();
		
		//(isFeas<-TRUE)
		res.setFeasible(true);
		
		double ds, d = Double.NEGATIVE_INFINITY;
		
		double INF = Double.POSITIVE_INFINITY;
		
		//List of devId that satisfy the constraints
		//as <i, C_ij, p_ij> where c_ij says
		//how many times can assign that job (or splitted job)
		//to that thing
		List<Pair<String, Integer>> Fj_sp;
		
		//file for debugging
		writer = new PrintWriter(Statistics.file.getAbsolutePath()+"/GapResult"+prio.name()+".txt", "UTF-8");
		
		System.out.println();
		System.out.println("##########################################################");
		logger.info("PRIORITY: "+prio.name());
		System.out.println("##########################################################");
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
				
				//take the pair <transId, Request>
				Pair<String, Request> servRequest = requestsBck.get(req);
				
				//get the transaId that identify a request with multiple
				//service requests
				String transId = servRequest.getLeft();
				
				//Request object contains
				//operationType, QoSRequirements, LocationRequirements,
				//required service list
				Request requestObj = servRequest.getRight();
				
				//store operation type
				//for example: queryContext
				String opType = requestObj.getOpType();
				
				System.out.println();
				System.out.println("##########################################################");
				logger.info("request with TransId: "+transId);
				logger.info("opType: "+opType);
				System.out.println("##########################################################");
				System.out.println();
				
				writer.println();
				writer.println("##########################################################");
				writer.println("request with TransId: "+transId);
				writer.println("opType: "+opType);
				writer.println("##########################################################");
				writer.println();
				
				//Get the list of required services for this request
				//identified by the transId
				List<String> reqServicesList = requestObj.getRequiredServicesNameList();
				
				//iterate over the List of required services in the request
				//identified by transId
				//(for each j in K)
				for(int s=0; s<reqServicesList.size() && res.isFeasible() ; s++){
					
					//get the required Service name
					//(service j taken from K)
					String reqServiceName = reqServicesList.get(s);
					
					//get the list<DevId> for that reqServName
					//this list represents the list of equivalent things
					List<String> eqThings = servNameThingsIdList.get(reqServiceName).getEqThings();
					
					
					System.out.println();
					System.out.println("##########################################################");
					logger.info("ServiceRequest Name: "+reqServiceName.toUpperCase() +
									" inside request with TransId: "+transId);
					System.out.println("##########################################################");
					System.out.println();
					writer.println();
					writer.println("##########################################################");
					writer.println("ServiceRequest Name: "+reqServiceName.toUpperCase()
									+ " inside request with TransId: "+transId);
					writer.println("##########################################################");
					writer.println();
					
					//coefficientMap has the transId as key and h/pj as value
					//factorization returns the list of factor in creasing order
					List<Integer> Sj = Utils.factorization(hyperperiodPeriodMap.get(transId));
					
					Integer split = 1;
					
					//iterate over the list of split factors
					//of a service, any way the first trial is with
					//a split factor equal to one
					//so one service to only one thing
					while(!Sj.isEmpty()){
						
						System.out.println();
						System.out.println("##########################################################");
						logger.info("SPLIT factor = "+String.valueOf(split)+" TETA: "+teta);
						System.out.println("##########################################################");
						System.out.println();
						
						writer.println();
						writer.println("##########################################################");
						writer.println("SPLIT factor = "+String.valueOf(split)+" TETA: "+teta);
						writer.println("##########################################################");
						writer.println();
						
						//List of devId that satisfy
						//the constraints about
						//utilization and residual battery
						//the list is returned as list of pairs <devId, c_ij_sp>
						//and in decreasing order respect to the priority
						//so the first element will be the one with max priority
						//where c_ij_sp represents, given a split factor, the maximum 
						//times that i can allocate the service to the thing
						//set also the sum(c_ij_sp) for all the things that
						//respect the constraints
						Fj_sp = checkConstraints(assignmentParamsMap, eqThings, matrixP, matrixF, matrixU,
								matrixM.get(transId+"::"+reqServiceName), transId, reqServiceName, 
								split, ni, teta, null);
								
						System.out.println();
						System.out.println("##########################################################");
						logger.info("Fj_sp: "+Fj_sp);
						logger.info("priorityList: "+priorityList);
						System.out.println("##########################################################");
						System.out.println();
						
						writer.println();
						writer.println("##########################################################");
						writer.println("Fj_sp: "+Fj_sp);
						writer.println("priorityList: "+priorityList);
						writer.println("##########################################################");
						writer.println();
						
						//there is no thing that satisfy the
						//requirements
						//if(Fj_sp = 0 || sum(c_ij_sp) < split)
						if(c_ij_sp_Sum < split){
							
							System.out.println();
							System.out.println("##########################################################");
							logger.info("SPLIT FACTOR: "+split);
							logger.info("ServiceRequest Name: "+reqServiceName.toUpperCase()+
									" inside request with TransId: "+transId);
							logger.info("NO THINGS FOUND, TETA: "+teta);
							System.out.println("##########################################################");
							System.out.println();
							
							writer.println();
							writer.println("##########################################################");
							writer.println("SPLIT FACTOR: "+split);
							writer.println("ServiceRequest Name: "+reqServiceName.toUpperCase()+
									" inside request with TransId: "+transId);
							writer.println("NO THINGS FOUND, TETA: "+teta);
							writer.println("##########################################################");
							writer.println();
							
							operationStatus = "QoSCalculator -- GAP() ServiceRequest Name: "+reqServiceName+
									" inside request with TransId: "+transId+" NO THINGS FOUND, "
									+ "SPLIT factor: "+String.valueOf(split)+", TETA: "+teta;
							
							Sj.remove(split);
							
							if(Sj.isEmpty()) res.setFeasible(false);
							else
								split = Sj.get(0);
							
							continue;
						}

						
						//get the devId of the Thing that have max priority
						//Fjsp is ordered on decreasing priority order
						//(i' = argMax{P_ij: i in Fj_sp})
						String devId_maxPriority = Fj_sp.get(0).getLeft();
						if(devId_maxPriority == null){
							res.setFeasible(false);
							
							operationStatus = "QoSCalculator -- GAP() devId_maxPriority taken as first elem of Fj_sp is null";
							
							writer.println(operationStatus);
							writer.close();
							return res;
						}
						
						System.out.println();
						System.out.println("##########################################################");
						logger.info("devId with max priority: " + devId_maxPriority);
						System.out.println("##########################################################");
						System.out.println();
						
						writer.println();
						writer.println("##########################################################");
						writer.println("devId with max priority: " + devId_maxPriority);
						writer.println("##########################################################");
						writer.println();
						
						//verify conditions for direct assignment
						int c_ij_sp_MaxPriority = Fj_sp.get(0).getRight();
						
						if((c_ij_sp_Sum - c_ij_sp_MaxPriority) < split){
							ds = INF;
							
							System.out.println();
							System.out.println("##########################################################");
							System.out.println("FINAL ALLOCATION in ONE SHOOT");
							System.out.println("##########################################################");
							System.out.println();
							writer.println();
							writer.println("##########################################################");
							writer.println("FINAL ALLOCATION in ONE SHOOT");
							writer.println("##########################################################");
							writer.println();
							
							AllocationInfo allocation = new AllocationInfo();
							
							//set the split
							allocation.setSplit(new Integer(split));
							
							//compute the list <i, w_ij_sp>
							List<Pair<String, Integer>> listThingWij_sp = computeAllocation(Fj_sp, split);
							
							//set the list of allocated things as <i, w_ij_sp>
							for(Pair<String, Integer> entryAllocation: listThingWij_sp){
								
								String devId = entryAllocation.getLeft();
								Integer wij_sp = entryAllocation.getRight();
								
								//set <i, w_ij_sp> in the allocated things list
								//(y_j <- I_star with <i, w_ij_sp> in decreasing priority order)
								allocation.addThing(devId, wij_sp);
								
								//get f_ij, u_ij for the given devId, transId+"::"+reqServiceName
								Double f_ij = matrixF.get(devId).get(transId+"::"+reqServiceName);
								Double u_ij = matrixU.get(devId).get(transId+"::"+reqServiceName);
								
								if(f_ij == null || u_ij == null){
									operationStatus = "QoSCalculator -- GAP() allocation in one shot"
															+" f_ij or u_ij null for service: "
															+reqServiceName+"and devId "+devId;
									res.setFeasible(false);
									return res;
								}
								
								//update capacity and residualBattery
								//c_i = c_i + (u_ij/split)*w_ij_sp
								//z_i = z_i - (f_ij/split)*w_ij_sp
								updateAssignmentsParams(assignmentParamsMap, f_ij, u_ij, split, wij_sp, devId, 1);
							}
							
							
							//set operationType for the specified transId
							res.addTransactionIdOperationType(transId, opType);
							
							//add allocation to the allocationSchema in the reserveObj
							res.addAllocation(transId, reqServiceName, allocation);
							
							//remove service in the list of the request object
							//request obj index is requestIndex, service index is serviceIndex
							requestsBck.get(req).getRight().getRequiredServicesNameList().remove(s);
							
							if(requestsBck.get(req).getRight().getRequiredServicesNameList().isEmpty()){
								requestsBck.remove(req);
							}
							
							allocationTransId = null;
							allocationServiceName = null;
							allocationTemp = null;
							
							
							System.out.println();
							System.out.println("##########################################################");
							System.out.println("##########################################################");
							logger.info("transId="+transId);
							logger.info("and servName="+reqServiceName.toUpperCase()+" TETA: "+teta);
							logger.info(allocation.toString());
							System.out.println("##########################################################");
							System.out.println("##########################################################");
							System.out.println();

							writer.println();
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println("##########################################################");		
							writer.println("transId="+transId+
											"\nand servName="+reqServiceName.toUpperCase()+" TETA: "+teta);
							writer.println(allocation.toString());
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println();

							break;
						}
						else{
							
							d = getDiffMaxSecondMax(Fj_sp, split);
							
							System.out.println();
							logger.info("DIFFERENCE d="+String.valueOf(d));
							System.out.println();
							writer.println();
							writer.println("DIFFERENCE d="+String.valueOf(d));
							writer.println();
						}
						
						if(d > ds){
							
							System.out.println();
							logger.info("d > ds: "+ (d > ds));
							System.out.println();
							
							ds = d;
							
							if(allocationTemp == null)
								allocationTemp = new AllocationInfo();
							else{
								
								if(allocationTemp.getAllocatedThings().size() > 0)
									//clear the list <i, c_ij_sp> because it is was set
									//in a previous iteration
									allocationTemp.getAllocatedThings().clear();
							}
							
							//set transId, service, operationType
							//relative to the chosen service
							allocationTransId = transId;
							allocationServiceName = reqServiceName;
							operationType = opType;
							
							allocationTemp.setSplit(split);

							//compute the list <i, w_ij_sp>
							List<Pair<String, Integer>> listThingWij_sp = computeAllocation(Fj_sp, split);
							
							//set the list of allocated things as <i, w_ij_sp>
							for(Pair<String, Integer> entryAllocation: listThingWij_sp){
								
								String devId = entryAllocation.getLeft();
								Integer wij_sp = entryAllocation.getRight();
								
								allocationTemp.addThing(devId, wij_sp);
							}
							
							System.out.println();
							System.out.println("##########################################################");
							System.out.println("TEMPORARY CHOSEN SERVICE");
							System.out.println("transId: "+allocationTransId);
							System.out.println("serviceName: "+allocationServiceName.toUpperCase());
							System.out.println(allocationTemp.toString()+" TETA: "+teta);
							System.out.println("##########################################################");
							System.out.println();
							writer.println();
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println("TEMPORARY CHOSEN SERVICE");
							writer.println("transId: "+allocationTransId);
							writer.println("serviceName: "+allocationServiceName.toUpperCase());
							writer.println(allocationTemp.toString()+" TETA: "+teta);
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println();
							
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
							//for the service j with split factor sp
							break;
					
					}
					
					if(ds == INF) break;
				}//iteration on service list in the Request with transId
				
				if(ds == INF) break;
			}//iteration on the transIds in the list <transId, Request>
				
			//check if res is feasible  
			if(res.isFeasible()){
				
				//check if ds != INF
				//because if ds == INF, it has been already done
				//the allocation in one shoot
				if(ds != INF){
				
					System.out.println();
					System.out.println("##########################################################");
					System.out.println("FINAL ALLOCATION");
					System.out.println("##########################################################");
					System.out.println();
					writer.println();
					writer.println("##########################################################");
					writer.println("FINAL ALLOCATION");
					writer.println("##########################################################");
					writer.println();
					
					AllocationInfo allocation = new AllocationInfo();
	
					Integer split = new Integer(allocationTemp.getSplit());
					allocation.setSplit(split);
					
					//get the list <i, w_ij_sp> from the allocationTemp object
					List<Pair<String, Integer>> listThingWij_sp = allocationTemp.getAllocatedThings();
					
					//set the list of allocated things as <i, w_ij_sp>
					for(Pair<String, Integer> entryAllocation: listThingWij_sp){
						
						String devId = entryAllocation.getLeft();
						Integer wij_sp = entryAllocation.getRight();
						
						//set <i, w_ij_sp> in the allocated things list
						//(y_j <- I_star with <i, w_ij_sp> in decreasing priority order)
						allocation.addThing(devId, wij_sp);
						
						//get f_ij, u_ij for the given devId, transId+"::"+reqServiceName
						Double f_ij = matrixF.get(devId).get(allocationTransId+"::"+allocationServiceName);
						Double u_ij = matrixU.get(devId).get(allocationTransId+"::"+allocationServiceName);
						
						if(f_ij == null || u_ij == null){
							operationStatus = "QoSCalculator -- GAP() final allocation"
													+" f_ij or u_ij null for service: "
													+allocationServiceName+" and devId "+devId;
							res.setFeasible(false);
							return res;
						}
						
						//update capacity and residualBattery
						//c_i = c_i + (u_ij/split)*w_ij_sp
						//z_i = z_i - (f_ij/split)*w_ij_sp
						updateAssignmentsParams(assignmentParamsMap, f_ij, u_ij, split, wij_sp, devId, 1);
					}
	
					//set operationType for the specified transId
					res.addTransactionIdOperationType(allocationTransId, operationType);
					
					//add allocation to the allocationSchema in the reserveObj
					res.addAllocation(allocationTransId, allocationServiceName, allocation);
					
					//remove service in the list of the request object
					//request obj index is requestIndex, service index is serviceIndex
					requestsBck.get(requestIndex).getRight().getRequiredServicesNameList().remove(serviceIndex);
					
					if(requestsBck.get(requestIndex).getRight().getRequiredServicesNameList().isEmpty()){
						requestsBck.remove(requestIndex);
					}

					
					System.out.println();
					System.out.println("##########################################################");
					System.out.println("##########################################################");
					logger.info("transId="+allocationTransId);
					logger.info("and servName="+allocationServiceName.toUpperCase()+" TETA: "+teta);
					logger.info(allocation.toString());
					System.out.println("##########################################################");
					System.out.println("##########################################################");
					System.out.println();
	
					writer.println();
					writer.println("##########################################################");
					writer.println("##########################################################");
					writer.println("##########################################################");
					writer.println("##########################################################");
					writer.println("transId="+allocationTransId+
									"\nand servName="+allocationServiceName.toUpperCase()+" TETA: "+teta);
					writer.println(allocation.toString());
					writer.println("##########################################################");
					writer.println("##########################################################");
					writer.println("##########################################################");
					writer.println("##########################################################");
					writer.println();
					
					
					allocationTransId = null;
					allocationServiceName = null;
					allocationTemp = null;
				}
				
			}
			else{
				System.out.println();
				System.out.println("##########################################################");
				logger.info("ALLOCATION FAILED, TETA: "+teta);
				System.out.println("##########################################################");
				System.out.println();
				
				writer.println();
				writer.println("##########################################################");
				writer.println("ALLOCATION FAILED, TETA: "+teta);
				writer.println("##########################################################");
				writer.println();
			
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
	
		//store the values of <c_i, z_i> for this execution
		//of the heuristic
		res.setAssignmentsParamsMap(assignmentParamsMap);
		
		writer.println();
		writer.println();
		writer.println("ALLOCATION COMPLETED, TETA: "+teta +"<-------------------------------------");
		writer.close();
		
		operationStatus = "QoSCalculator -- GAP() allocation operation OK, TETA: "+teta;
		
		return res;
	}
	
	/**
	 * Real_battery_ gap.
	 *
	 * @param k the number of request
	 * @param requests all the requests as Pair <transId, Request>
	 * @param HashMap<String, Double> hyperperiodPeriodMap,
	 * @param eqThingInfo Map<devId, Thing>
	 * @param servNameThingsIdList Map<reServName, List<DevId>>
	 * @param matrixM Map<transId::servName, List<devId>> list of devId that respect 
	 * 					restrictions for that transaction
	 * @param teta the teta for the constraint on z_i - f_ij > teta
	 * @param prio say which value using as priority p_ij
	 * @param policy say if must be used max or min split policy
	 * @return the reserveobj
	 */
	private Reserveobj GAP_singleSplit(
			int k, List<Pair<String, Request>> requests, 
			HashMap<String,HashMap<String, Double>> matrixP,
			HashMap<String,HashMap<String, Double>> matrixF,
			HashMap<String,HashMap<String, Double>> matrixU,
			HashMap<String, Integer> hyperperiodPeriodMap,
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
		//(for i<-1 to n ci<-0 and zi<-1)
		HashMap<String, ThingAssignmentParams> assignmentParamsMap = createAssignmentParamsMap(thingsInfo);
		
		Double pow = Math.pow(2, (double)1/k);
		//upper bound for utilization
		Double ni = k*(pow-1);
		
		System.out.println();
		System.out.println("##########################################################");
		logger.info("ni= "+ni);
		logger.info("TETA= "+teta);
		System.out.println("##########################################################");
		System.out.println();
		
		//(isFeas<-TRUE)
		res.setFeasible(true);
		
		double ds, d = Double.NEGATIVE_INFINITY;
		
		double INF = Double.POSITIVE_INFINITY;
		
		//List of devId that satisfy the constraints
		//as <i, C_ij, p_ij> where c_ij says
		//how many times can assign that job (or splitted job)
		//to that thing
		List<Pair<String, Integer>> Fj_sp;
		
		//file for debugging
		writer = new PrintWriter(Statistics.file.getAbsolutePath()+"/GapResult"+prio.name()+".txt", "UTF-8");
		
		System.out.println();
		System.out.println("##########################################################");
		logger.info("PRIORITY: "+prio.name());
		System.out.println("##########################################################");
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
				
				//take the pair <transId, Request>
				Pair<String, Request> servRequest = requestsBck.get(req);
				
				//get the transaId that identify a request with multiple
				//service requests
				String transId = servRequest.getLeft();
				
				//Request object contains
				//operationType, QoSRequirements, LocationRequirements,
				//required service list
				Request requestObj = servRequest.getRight();
				
				//store operation type
				//for example: queryContext
				String opType = requestObj.getOpType();
				
				System.out.println();
				System.out.println("##########################################################");
				logger.info("request with TransId: "+transId);
				logger.info("opType: "+opType);
				System.out.println("##########################################################");
				System.out.println();
				
				writer.println();
				writer.println("##########################################################");
				writer.println("request with TransId: "+transId);
				writer.println("opType: "+opType);
				writer.println("##########################################################");
				writer.println();
				
				//Get the list of required services for this request
				//identified by the transId
				List<String> reqServicesList = requestObj.getRequiredServicesNameList();
				
				//iterate over the List of required services in the request
				//identified by transId
				//(for each j in K)
				for(int s=0; s<reqServicesList.size() && res.isFeasible() ; s++){
					
					//get the required Service name
					//(service j taken from K)
					String reqServiceName = reqServicesList.get(s);
					
					//get the list<DevId> for that reqServName
					//this list represents the list of equivalent things
					List<String> eqThings = servNameThingsIdList.get(reqServiceName).getEqThings();
					
					
					System.out.println();
					System.out.println("##########################################################");
					logger.info("ServiceRequest Name: "+reqServiceName.toUpperCase() +
									" inside request with TransId: "+transId);
					System.out.println("##########################################################");
					System.out.println();
					writer.println();
					writer.println("##########################################################");
					writer.println("ServiceRequest Name: "+reqServiceName.toUpperCase()
									+ " inside request with TransId: "+transId);
					writer.println("##########################################################");
					writer.println();
					
					//coefficientMap has the transId as key and h/pj as value
					//factorization returns the list of factor in creasing order
					List<Integer> Sj = Utils.factorization(hyperperiodPeriodMap.get(transId));
					
					Integer split = 1;
					
					//iterate over the list of split factors
					//of a service, any way the first trial is with
					//a split factor equal to one
					//so one service to only one thing
					while(!Sj.isEmpty()){
						
						System.out.println();
						System.out.println("##########################################################");
						logger.info("SPLIT factor = "+String.valueOf(split)+" TETA: "+teta);
						System.out.println("##########################################################");
						System.out.println();
						
						writer.println();
						writer.println("##########################################################");
						writer.println("SPLIT factor = "+String.valueOf(split)+" TETA: "+teta);
						writer.println("##########################################################");
						writer.println();
						
						//List of devId that satisfy
						//the constraints about
						//utilization and residual battery
						//the list is returned as list of pairs <devId, c_ij_sp>
						//and in decreasing order respect to the priority
						//so the first element will be the one with max priority
						//where c_ij_sp represents, given a split factor, the maximum 
						//times that i can allocate the service to the thing
						//set also the sum(c_ij_sp) for all the things that
						//respect the constraints
						Fj_sp = checkConstraints(assignmentParamsMap, eqThings, matrixP, matrixF, matrixU,
								matrixM.get(transId+"::"+reqServiceName), transId, reqServiceName, 
								split, ni, teta, null);
								
						System.out.println();
						System.out.println("##########################################################");
						logger.info("Fj_sp: "+Fj_sp);
						logger.info("priorityList: "+priorityList);
						System.out.println("##########################################################");
						System.out.println();
						
						writer.println();
						writer.println("##########################################################");
						writer.println("Fj_sp: "+Fj_sp);
						writer.println("priorityList: "+priorityList);
						writer.println("##########################################################");
						writer.println();
						
						//there is no thing that satisfy the
						//requirements
						//if(Fj_sp = 0 || sum(c_ij_sp) < split)
						if(c_ij_sp_Sum < split){
							
							System.out.println();
							System.out.println("##########################################################");
							logger.info("SPLIT FACTOR: "+split);
							logger.info("ServiceRequest Name: "+reqServiceName.toUpperCase()+
									" inside request with TransId: "+transId);
							logger.info("NO THINGS FOUND, TETA: "+teta);
							System.out.println("##########################################################");
							System.out.println();
							
							writer.println();
							writer.println("##########################################################");
							writer.println("SPLIT FACTOR: "+split);
							writer.println("ServiceRequest Name: "+reqServiceName.toUpperCase()+
									" inside request with TransId: "+transId);
							writer.println("NO THINGS FOUND, TETA: "+teta);
							writer.println("##########################################################");
							writer.println();
							
							operationStatus = "QoSCalculator -- GAP() ServiceRequest Name: "+reqServiceName+
									" inside request with TransId: "+transId+" NO THINGS FOUND, "
									+ "SPLIT factor: "+String.valueOf(split)+", TETA: "+teta;
							
							//version in which i stop at the first split
							Sj.clear();
							
							if(Sj.isEmpty()) res.setFeasible(false);
							else
								split = Sj.get(0);
							
							continue;
						}

						
						//get the devId of the Thing that have max priority
						//Fjsp is ordered on decreasing priority order
						//(i' = argMax{P_ij: i in Fj_sp})
						String devId_maxPriority = Fj_sp.get(0).getLeft();
						if(devId_maxPriority == null){
							res.setFeasible(false);
							
							operationStatus = "QoSCalculator -- GAP() devId_maxPriority taken as first elem of Fj_sp is null";
							
							writer.println(operationStatus);
							writer.close();
							return res;
						}
						
						System.out.println();
						System.out.println("##########################################################");
						logger.info("devId with max priority: " + devId_maxPriority);
						System.out.println("##########################################################");
						System.out.println();
						
						writer.println();
						writer.println("##########################################################");
						writer.println("devId with max priority: " + devId_maxPriority);
						writer.println("##########################################################");
						writer.println();
						
						//verify conditions for direct assignment
						int c_ij_sp_MaxPriority = Fj_sp.get(0).getRight();
						
						if((c_ij_sp_Sum - c_ij_sp_MaxPriority) < split){
							ds = INF;
							
							System.out.println();
							System.out.println("##########################################################");
							System.out.println("FINAL ALLOCATION in ONE SHOOT");
							System.out.println("##########################################################");
							System.out.println();
							writer.println();
							writer.println("##########################################################");
							writer.println("FINAL ALLOCATION in ONE SHOOT");
							writer.println("##########################################################");
							writer.println();
							
							AllocationInfo allocation = new AllocationInfo();
							
							//set the split
							allocation.setSplit(new Integer(split));
							
							//compute the list <i, w_ij_sp>
							List<Pair<String, Integer>> listThingWij_sp = computeAllocation(Fj_sp, split);
							
							//set the list of allocated things as <i, w_ij_sp>
							for(Pair<String, Integer> entryAllocation: listThingWij_sp){
								
								String devId = entryAllocation.getLeft();
								Integer wij_sp = entryAllocation.getRight();
								
								//set <i, w_ij_sp> in the allocated things list
								//(y_j <- I_star with <i, w_ij_sp> in decreasing priority order)
								allocation.addThing(devId, wij_sp);
								
								//get f_ij, u_ij for the given devId, transId+"::"+reqServiceName
								Double f_ij = matrixF.get(devId).get(transId+"::"+reqServiceName);
								Double u_ij = matrixU.get(devId).get(transId+"::"+reqServiceName);
								
								if(f_ij == null || u_ij == null){
									operationStatus = "QoSCalculator -- GAP() allocation in one shot"
															+" f_ij or u_ij null for service: "
															+reqServiceName+"and devId "+devId;
									res.setFeasible(false);
									return res;
								}
								
								//update capacity and residualBattery
								//c_i = c_i + (u_ij/split)*w_ij_sp
								//z_i = z_i - (f_ij/split)*w_ij_sp
								updateAssignmentsParams(assignmentParamsMap, f_ij, u_ij, split, wij_sp, devId, 1);
							}
							
							
							//set operationType for the specified transId
							res.addTransactionIdOperationType(transId, opType);
							
							//add allocation to the allocationSchema in the reserveObj
							res.addAllocation(transId, reqServiceName, allocation);
							
							//remove service in the list of the request object
							//request obj index is requestIndex, service index is serviceIndex
							requestsBck.get(req).getRight().getRequiredServicesNameList().remove(s);
							
							if(requestsBck.get(req).getRight().getRequiredServicesNameList().isEmpty()){
								requestsBck.remove(req);
							}
							
							allocationTransId = null;
							allocationServiceName = null;
							allocationTemp = null;
							
							
							System.out.println();
							System.out.println("##########################################################");
							System.out.println("##########################################################");
							logger.info("transId="+transId);
							logger.info("and servName="+reqServiceName.toUpperCase()+" TETA: "+teta);
							logger.info(allocation.toString());
							System.out.println("##########################################################");
							System.out.println("##########################################################");
							System.out.println();

							writer.println();
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println("##########################################################");		
							writer.println("transId="+transId+
											"\nand servName="+reqServiceName.toUpperCase()+" TETA: "+teta);
							writer.println(allocation.toString());
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println();

							break;
						}
						else{
							
							d = getDiffMaxSecondMax(Fj_sp, split);
							
							System.out.println();
							logger.info("DIFFERENCE d="+String.valueOf(d));
							System.out.println();
							writer.println();
							writer.println("DIFFERENCE d="+String.valueOf(d));
							writer.println();
						}
						
						if(d > ds){
							
							System.out.println();
							logger.info("d > ds: "+ (d > ds));
							System.out.println();
							
							ds = d;
							
							if(allocationTemp == null)
								allocationTemp = new AllocationInfo();
							else{
								
								if(allocationTemp.getAllocatedThings().size() > 0)
									//clear the list <i, c_ij_sp> because it is was set
									//in a previous iteration
									allocationTemp.getAllocatedThings().clear();
							}
							
							//set transId, service, operationType
							//relative to the chosen service
							allocationTransId = transId;
							allocationServiceName = reqServiceName;
							operationType = opType;
							
							allocationTemp.setSplit(split);

							//compute the list <i, w_ij_sp>
							List<Pair<String, Integer>> listThingWij_sp = computeAllocation(Fj_sp, split);
							
							//set the list of allocated things as <i, w_ij_sp>
							for(Pair<String, Integer> entryAllocation: listThingWij_sp){
								
								String devId = entryAllocation.getLeft();
								Integer wij_sp = entryAllocation.getRight();
								
								allocationTemp.addThing(devId, wij_sp);
							}
							
							System.out.println();
							System.out.println("##########################################################");
							System.out.println("TEMPORARY CHOSEN SERVICE");
							System.out.println("transId: "+allocationTransId);
							System.out.println("serviceName: "+allocationServiceName.toUpperCase());
							System.out.println(allocationTemp.toString()+" TETA: "+teta);
							System.out.println("##########################################################");
							System.out.println();
							writer.println();
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println("TEMPORARY CHOSEN SERVICE");
							writer.println("transId: "+allocationTransId);
							writer.println("serviceName: "+allocationServiceName.toUpperCase());
							writer.println(allocationTemp.toString()+" TETA: "+teta);
							writer.println("##########################################################");
							writer.println("##########################################################");
							writer.println();
							
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
							//for the service j with split factor sp
							break;
					
					}
					
					if(ds == INF) break;
				}//iteration on service list in the Request with transId
				
				if(ds == INF) break;
			}//iteration on the transIds in the list <transId, Request>
				
			//check if res is feasible  
			if(res.isFeasible()){
				
				//check if ds != INF
				//because if ds == INF, it has been already done
				//the allocation in one shoot
				if(ds != INF){
				
					System.out.println();
					System.out.println("##########################################################");
					System.out.println("FINAL ALLOCATION");
					System.out.println("##########################################################");
					System.out.println();
					writer.println();
					writer.println("##########################################################");
					writer.println("FINAL ALLOCATION");
					writer.println("##########################################################");
					writer.println();
					
					AllocationInfo allocation = new AllocationInfo();
	
					Integer split = new Integer(allocationTemp.getSplit());
					allocation.setSplit(split);
					
					//get the list <i, w_ij_sp> from the allocationTemp object
					List<Pair<String, Integer>> listThingWij_sp = allocationTemp.getAllocatedThings();
					
					//set the list of allocated things as <i, w_ij_sp>
					for(Pair<String, Integer> entryAllocation: listThingWij_sp){
						
						String devId = entryAllocation.getLeft();
						Integer wij_sp = entryAllocation.getRight();
						
						//set <i, w_ij_sp> in the allocated things list
						//(y_j <- I_star with <i, w_ij_sp> in decreasing priority order)
						allocation.addThing(devId, wij_sp);
						
						//get f_ij, u_ij for the given devId, transId+"::"+reqServiceName
						Double f_ij = matrixF.get(devId).get(allocationTransId+"::"+allocationServiceName);
						Double u_ij = matrixU.get(devId).get(allocationTransId+"::"+allocationServiceName);
						
						if(f_ij == null || u_ij == null){
							operationStatus = "QoSCalculator -- GAP() final allocation"
													+" f_ij or u_ij null for service: "
													+allocationServiceName+" and devId "+devId;
							res.setFeasible(false);
							return res;
						}
						
						//update capacity and residualBattery
						//c_i = c_i + (u_ij/split)*w_ij_sp
						//z_i = z_i - (f_ij/split)*w_ij_sp
						updateAssignmentsParams(assignmentParamsMap, f_ij, u_ij, split, wij_sp, devId, 1);
					}
	
					//set operationType for the specified transId
					res.addTransactionIdOperationType(allocationTransId, operationType);
					
					//add allocation to the allocationSchema in the reserveObj
					res.addAllocation(allocationTransId, allocationServiceName, allocation);
					
					//remove service in the list of the request object
					//request obj index is requestIndex, service index is serviceIndex
					requestsBck.get(requestIndex).getRight().getRequiredServicesNameList().remove(serviceIndex);
					
					if(requestsBck.get(requestIndex).getRight().getRequiredServicesNameList().isEmpty()){
						requestsBck.remove(requestIndex);
					}

					
					System.out.println();
					System.out.println("##########################################################");
					System.out.println("##########################################################");
					logger.info("transId="+allocationTransId);
					logger.info("and servName="+allocationServiceName.toUpperCase()+" TETA: "+teta);
					logger.info(allocation.toString());
					System.out.println("##########################################################");
					System.out.println("##########################################################");
					System.out.println();
	
					writer.println();
					writer.println("##########################################################");
					writer.println("##########################################################");
					writer.println("##########################################################");
					writer.println("##########################################################");
					writer.println("transId="+allocationTransId+
									"\nand servName="+allocationServiceName.toUpperCase()+" TETA: "+teta);
					writer.println(allocation.toString());
					writer.println("##########################################################");
					writer.println("##########################################################");
					writer.println("##########################################################");
					writer.println("##########################################################");
					writer.println();
					
					
					allocationTransId = null;
					allocationServiceName = null;
					allocationTemp = null;
				}
				
			}
			else{
				System.out.println();
				System.out.println("##########################################################");
				logger.info("ALLOCATION FAILED, TETA: "+teta);
				System.out.println("##########################################################");
				System.out.println();
				
				writer.println();
				writer.println("##########################################################");
				writer.println("ALLOCATION FAILED, TETA: "+teta);
				writer.println("##########################################################");
				writer.println();
			
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
	
		//store the values of <c_i, z_i> for this execution
		//of the heuristic
		res.setAssignmentsParamsMap(assignmentParamsMap);
		
		writer.println();
		writer.println();
		writer.println("ALLOCATION COMPLETED, TETA: "+teta +"<-------------------------------------");
		writer.close();
		
		operationStatus = "QoSCalculator -- GAP() allocation operation OK, TETA: "+teta;
		
		return res;
	}

	/* function to compute the difference
	 * from max priority and second max priority */
	private double getDiffMaxSecondMax(List<Pair<String, Integer>> Fj_sp, Integer split) {
		
		//list of pairs <i, c_ij> ordered by decreasing priority
		List<Pair<String, Integer>> Fj_spBck = cloneFj_sp(Fj_sp);
		
		Double[] max_max2 = new Double[2];
		
		//list of <i, w_ij_sp>
		List<Pair<String, Integer>> listThingWij_sp;
		
		//sum w_ij
		int wij_counter = 0;
		
		//p_ij*w_ij
		double pij_wijMul = 0.0;
		
		int max_max2Index = 0;
		
		//two iterations, one starting from the max priority
		//and one starting from the second max priority
		while(max_max2Index < 2){
			
			listThingWij_sp = computeAllocation(Fj_spBck, split);

			//pair <i, c_ij> ordered by decreasing priority
			//so i start from the first element with max priority
			//when it was created Fj_sp as list <i,c_ij_sp>, 
			//at same time it was created the list of associated priorities
			//to each thing
			//Rember that in the second iteration
			//it is get the second max priority
			int indexPriority = Fj_sp.size() - Fj_spBck.size();
			
			for(Pair<String, Integer> pair: listThingWij_sp){
				int wij_sp = pair.getRight();
				
				pij_wijMul = pij_wijMul + priorityList.get(indexPriority)*(double)wij_sp;
				
				indexPriority++;
				wij_counter += wij_sp;
			}
			
			//compute the weighted priority
			Double weightedPriority = (double)(pij_wijMul/wij_counter);
			
			max_max2[max_max2Index] = weightedPriority;
			
			pij_wijMul = 0.0;
			wij_counter = 0;
			
			//remove element with max priority
			//so the first element
			Fj_spBck.remove(0);
			
			max_max2Index++;
		}
		
		return max_max2[0] - max_max2[1];
	}

	private List<Pair<String, Integer>> cloneFj_sp(
			List<Pair<String, Integer>> fj_sp) {
		
		List<Pair<String, Integer>> fj_spBck = new ArrayList<>();
		
		for(Pair<String, Integer> pair: fj_sp){
			
			fj_spBck.add(new Pair<String, Integer>(pair.getLeft(), pair.getRight()));
		}
		
		return fj_spBck;
	}

	/* function to compute the size of allocation to each thing 
	 * in decreasing priority order */
	private List<Pair<String, Integer>> computeAllocation(
			List<Pair<String, Integer>> listThingCij_sp,
			int split) {
		
		List<Pair<String, Integer>> listThingWij_sp = new ArrayList<>();
		
		//compute for each device how many (sub) jobs it takes
		//commpute the w_ij_sp factor for each thing
		//set sum wij to split that is the
		//size that it must be allocated
		int wij_counter = split;
		
		int i = 0;
		
		while(wij_counter > 0){
			
			//the list is ordered in decreasing
			//priority so the iteration
			//start from the first element
			//pair <i, c_ij_sp> with max priority
			Pair<String, Integer> pairThingCij_sp = listThingCij_sp.get(i);
			
			String devId = pairThingCij_sp.getLeft();
			int cij_sp = pairThingCij_sp.getRight();
			
			int w_ij_sp = 0;
			
			if(cij_sp < wij_counter){
				w_ij_sp = cij_sp;
			}
			else{
				w_ij_sp = wij_counter;
			}
			
			//add a pair <i, wij_sp>
			listThingWij_sp.add(new Pair<String, Integer>(devId, w_ij_sp));
			
			//decrement of the quantity
			//of allocation
			wij_counter -= w_ij_sp;
			
			//next thing with
			//next max priority
			i++;
		}
		
		return listThingWij_sp;
	}

	/* function to update c_i and z_i */
	private void updateAssignmentsParams(
			HashMap<String, ThingAssignmentParams> assignmentParamsMap,
			Double f_ij, Double u_ij, int split, int w_ij_sp, 	
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
		assignmentParamsMap.get(devId).setTotalUtilization(c_i + i*(u_ij/split)*w_ij_sp);
		
		Double z_i = assignmentParamsMap.get(devId).getResidualBattery();
		assignmentParamsMap.get(devId).setResidualBattery(z_i - i*(f_ij/split)*w_ij_sp);
		
		logger.info("c_i: "+(c_i));
		logger.info("z_i: "+(z_i));
		logger.info("update c_i: "+(c_i + i*(u_ij/split)*w_ij_sp));
		logger.info("update z_i: "+(z_i - i*(f_ij/split)*w_ij_sp));
		logger.info("SPLIT: "+split);
		logger.info("wij_sp: "+w_ij_sp);
		System.out.println();
		writer.println("update c_i: "+(c_i + i*(u_ij/split)*w_ij_sp));
		writer.println("update z_i: "+(z_i - i*(f_ij/split)*w_ij_sp));
		writer.println("SPLIT: "+split);
		writer.println("wij_sp: "+w_ij_sp);

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
			
			logger.info("maxRespTime: "+maxRespTime);
			
			Point point = null;
			Circle circle = null;
			
			if(request.getLocationRequirementPoint()!=null){
				point = request.getLocationRequirementPoint().getLocationRequirement();
				
			}
			else{
				if(request.getLocationRequirementCircle()!=null){
					circle = request.getLocationRequirementCircle().getLocationRequirement();
					
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
					//coords of the thing is not null
					if(coords != null){
						//if some location requirements
						//is set must be respected
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
						}
					}
					else{
						//coords info of the thing is null
						//if the location requirements is not
						//null the thing is removed
						if(point != null || circle != null){
							eqThings.remove(i-1);
							i--;
							continue;
						}
					}
					
					//check QoSrequirements on services on a thing
					HashMap<String, ServiceFeatures> services = t.getServicesList();
					
					for(Map.Entry<String, ServiceFeatures> servEntry: services.entrySet()){
						
						//check the constraints only on the required reqServName
						//not on all services of the thing
						if(servEntry.getKey().contentEquals(reqServName)){
							Double latency = servEntry.getValue().getLatency();
							
							//check latency and accuracy constraints
							if(maxRespTime!=null && latency != null && latency > maxRespTime || maxRespTime!=null && latency == null){
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
	 * @param eqThings List<devId> equivalent things for that service
	 * @param eqThingsTransaction List<devId> things associated to the transaction
	 * 			and respect the restrictions of that transaction
	 * @param P priority matrix
	 * @param F normalized energy cost matrix
	 * @param U utilization matrix
	 * @param transactionId id of the transaction
	 * @param reqServiceName required service name
	 * @param split number to things to which assign the service
	 * @param ni utilization upper bound
	 * @param teta the teta for the constraint on z_i - f_ij > teta
	 * @param devIdExcluded devId to exclude
	 * @return the List<devid,C_ij_sp>> that respect the constraints
	 */
	private List<Pair<String, Integer>> checkConstraints(
			HashMap<String, ThingAssignmentParams> assignmentParamsMap,
			List<String> eqThings,
			HashMap<String,HashMap<String, Double>> matrixP,
			HashMap<String,HashMap<String, Double>> matrixF,
			HashMap<String,HashMap<String, Double>> matrixU,
			List<String> eqThingsTransaction, 
			String transactionId,
			String reqServiceName,
			int split, Double ni,
			double teta, String devIdExcluded) {
		
		//Map<devid, <C_ij_sp, p_ij>>
		List<Pair<String, Integer>> Fj_sp = new ArrayList<>();
		
		//HashMap to sort things based on priority
		//as Map<devId, p_ij>
		HashMap<String, Double> priorityMap = new HashMap<>();
		
		//Temporary Map<devId, c_ij_sp>
		HashMap<String, Integer> Fj_spTemp = new HashMap<>();
		
		//reset sum of c_ij
		c_ij_sp_Sum = 0;
		
		//reset priority list
		//this object is used to set
		//the list of priorities in decreasing order associated
		//to the things that respect the constraints
		if(priorityList == null){
			priorityList = new ArrayList<>();
		}
		if(priorityList.size() != 0){
			priorityList.clear();
		}
		
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
			
			//condition for the local optimization
			//where a devId is excluded
			if(devIdExcluded == null || !devId.contentEquals(devIdExcluded)){
					
				//check if the devId respect the constraints for that
				//transaction identified by the transactionId
				if(eqThingsTransaction.contains(devId)){
					
					writer.println("devId "+devId+" respects the constraints of the transId "+transactionId);
					logger.info("devId "+devId+" respects the constraints of the transId "+transactionId);
					
					//get f_ij,u_ij, p_ij
					Double f_ij = matrixF.get(devId).get(transactionId+"::"+reqServiceName);
					Double u_ij = matrixU.get(devId).get(transactionId+"::"+reqServiceName);
					Double p_ij = matrixP.get(devId).get(transactionId+"::"+reqServiceName);
					
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
					//to the thing i respecting the constraints of utilization and battery
					int c_ij_split = Math.min(capacityLimit, residualBatteryLimit);
					
					logger.info("c_ij_split: "+c_ij_split);
					writer.println("c_ij_split: "+c_ij_split);
					
					//check the constraints about ni and teta
					if(c_ij_split > 0){
						
						c_ij_sp_Sum += c_ij_split;
						
						priorityMap.put(devId, p_ij);
						Fj_spTemp.put(devId, c_ij_split);
						
						System.out.println();
						logger.info("##########################################################");
						logger.info("ADD DEVID: "+devId+" to Fjsp");
						logger.info("c_ij_split: "+c_ij_split);
						logger.info("p_ij: "+p_ij);
						logger.info("##########################################################");
						System.out.println();
						writer.println();
						writer.println("##########################################################");
						writer.println("add devId: "+devId+" to Fjsp");
						writer.println("c_ij_split: "+c_ij_split);
						writer.println("p_ij: "+p_ij);
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

				priorityList.add(entry.getValue());
				
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
			req.setQosRequirements(reqEntry.getRight().getQosRequirements());
			
			List<String> servList = new ArrayList<>(); 
			servList.addAll(reqEntry.getRight().getRequiredServicesNameList());
			
			req.setRequiredServicesNameList(servList);
			
			reqListBck.add(new Pair<String, Request>(transId, req));
		}
		
		return reqListBck;
	}
	


	@Override
	public Reserveobj readReservation() {
		
		return reservationResults;
	}

	@Override
	public String getNextDevId(String transId, String service, AllocationPolicy allocPolicy) {
		
		if(reservationResults != null && reservationResults.isFeasible()){
				
			List<Pair<String, Integer>> devIdList = 
					reservationResults.getAllocationSchema().get(transId).get(service).getAllocatedThings();
				
			String nextDevId = null;
			
			if(allocPolicy == AllocationPolicy.WRoundRobin)
				nextDevId = wrrPolicyManager.getDevId(transId, service, devIdList);
			
			return nextDevId;

		}

		return null;
	}

	@PostConstruct 
	public void loadReservationResults(){
		
		List<Pair<String, JSONObject>> reservationData = bigDataRepository.readData(null, QoSConsts.RESERVATION_RESULTS_DB);
		
		if(reservationData.size() > 0){
			Gson gson = new Gson();
	
			String jsonReservationResults = null;
			if(reservationData.get(0).getRight() != null)
				jsonReservationResults = reservationData.get(0).getRight().toString();
			
			if(jsonReservationResults != null){
				System.out.println("ReservationResults read from RESERVATION_RESULTS_DB: ");
				System.out.println(jsonReservationResults);

				reservationResults = gson.fromJson(jsonReservationResults, Reserveobj.class);
				
				System.out.println("ReservationResults loaded");
			}
			else{
				System.out.println("ERROR reading ReservationResults from RESERVATION_RESULTS_DB: ");
			}
		}
	}
	
	@PreDestroy
	public boolean storeReservationResults(){
		
		if(reservationResults != null){
			
			Gson gson = new Gson();
			
			JSONObject reservationResultsJson = new JSONObject(gson.toJson(reservationResults));

			System.out.println("store reservation Results: ");
			System.out.println(reservationResultsJson);
			
			List<Pair<String, JSONObject>> reservationData = new ArrayList<>();
			
			reservationData.add(new Pair<String, JSONObject>(QoSCalculator.class.getCanonicalName(),reservationResultsJson));
			
			return bigDataRepository.storeData(reservationData, QoSConsts.RESERVATION_RESULTS_DB);

		}
		
		System.out.println("WARNING reservationResults is NULL");
		return true;
	}

	public QoSBigDataRepository getBigDataRepository() {
		return bigDataRepository;
	}

	public void setBigDataRepository(QoSBigDataRepository bigDataRepository) {
		this.bigDataRepository = bigDataRepository;
	}
}
