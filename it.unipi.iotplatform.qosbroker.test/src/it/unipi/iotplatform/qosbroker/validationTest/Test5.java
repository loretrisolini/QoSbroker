package it.unipi.iotplatform.qosbroker.validationTest;

import it.unipi.iotplatform.qosbroker.api.datamodel.AllocationInfo;
import it.unipi.iotplatform.qosbroker.api.datamodel.LocationScopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.Priority;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSscopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.Reserveobj;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.Split;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingAssignmentParams;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.api.utils.Statistics;
import it.unipi.iotplatform.qosbroker.api.utils.Utils;
import it.unipi.iotplatform.qosbroker.qoscalculator.impl.QoSCalculator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;

public class Test5 {

	private static final float[] coords = {0, 2, 4, 6, 8, 10};
	
	private static final double ttransf[]={3.0 / 1000, 3.5 / 1000, 4.0 / 1000, 4.5 / 1000, 5.0 / 1000, 5.5 / 1000, 6.0 / 1000, 6.5 / 1000, 7.0 / 1000, 7.5 / 1000, 8.0 / 1000, 8.5 / 1000, 9.0 / 1000, 9.5 / 1000, 10.0 / 1000}; //s
	private static final double pmcu = 3.6 * (10) / 3600; //mW/s
	private static final double prx = 3.6 * (18.8) / 3600; //mW/s
	private static final double ptx = 3.6 * (17.4) / 3600; //mW/s
	private static final double texe[]={1.0 / 1000, 1.5 / 1000, 1.8 / 1000, 2.0 / 1000, 2.5 / 1000}; //s
	
	private static final double per[]={10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0}; //s
										
	private static final double bat[]={50.710, 45.639, 40.568, 35.497, 30.426, 25.355};//{25.355, 20.284, 15.213, 10.142, 5.071, 2.535}; //mJ/100000
									
	private int k;
	private int n;
	private final int[] mediumList = {15, 25, 50, 75};
	private int mediumIndex;
	
	private final int EQTHINGS_LIST_SIZE = 4;
	
	private final Random generator;
	private final Long seed;
	
	
	private final AtomicInteger thingIdCounter = new AtomicInteger(0);
	private final AtomicInteger transIdCounter = new AtomicInteger(0);
	
	private HashMap<String, Thing> thingsInfo;
	private HashMap<String, ThingsIdList> servNameThingsIdList;
	private List<Pair<String, Request>> requestList;
	
	//Map<iteration::split::medium, <Feasible, reservationObj>>
	private List<Pair<String, Reserveobj>> testResults;
	
	//Map<iteration::split::medium, <Feasible>>
	private List<Pair<String, Boolean>> testCompare;
	
	//Map<medium, SimulationTime>
	private Long simulationsTime;
	private int howManyFeasSingle;
	private int howManyFeasMulti;
	
	private final int ITERATIONS = 100;
	
	private Long startTime;
	private Long finishTime;
	
	private final QoSCalculator qosCalculator = new QoSCalculator();
	private int medium;
	
	private double epsilon; 
	private Split split;
	

	private static Statistics stat;
	
	public Test5(
			long seed,
			int k,
			int n) {

		stat = new Statistics();
		
		this.seed = seed;
		generator = new Random(this.seed);
		
		this.k = k;
		this.n = n;

		this.split = Split.SINGLE_SPLIT;
		this.epsilon = 0.001;
		
		mediumIndex = 1;
		medium = mediumList[mediumIndex];

		testResults = new ArrayList<Pair<String, Reserveobj>>();
		testCompare = new ArrayList<Pair<String, Boolean>>();
		
	}


	public void allocationTest(){
		
		//set result object
		Reserveobj result = new Reserveobj();
		result.setFeasible(false);
		
		System.out.println("generate requests");
		requestList = generateRequests(this.k);
		
		System.out.println("generate things");
		thingsInfo = generateThings(this.n);
		
		servNameThingsIdList = new HashMap<>();
		
		//set periods list
		HashMap<String, Double> periods = setPeriodsList(requestList);
		
		//compute h and set coefficients h/p_j
		HashMap<String, Integer> coefficients = hyperperiodComputations(periods);
		
		
		//create matrixM to filter equivalent Things accordingly to the restrictions in each request
		//Map<transId::service, List<devId>>
		HashMap<String, List<String>> matrixM = new HashMap<>();
		
		//matrix U of the utilizations
		//Map<DevId, Map<transId::Service, u_ij>>
		HashMap<String,HashMap<String, Double>> matrixU = new HashMap<>();
		
		//matrix F of the normalized energy costs
		HashMap<String,HashMap<String, Double>> matrixF = new HashMap<>();

		Priority prio = Priority.BATTERY;
		
		boolean stopTest = false;

		HashMap<String,HashMap<String,Double>> matrixP;
		
		while(!stopTest){
			
			System.out.println("");
			System.out.println("Test with medium "+medium+" split "+split.name());
			System.out.println("############################################");

			int indexTest = 1;
			howManyFeasSingle = 0;
			howManyFeasMulti = 0;
			
			startTime = new Date().getTime();
			//stop at the first feasible allocation or when all the
			//requests are finished
			while(indexTest <= ITERATIONS){

				System.out.println("medium: "+medium);
				servNameThingsIdList.clear();
				servNameThingsIdList = generateThingsServicesData(medium, this.n, this.k, servNameThingsIdList);
				
				thingsInfo = setServicesOnThings(thingsInfo, servNameThingsIdList);
				
				//create matrixM to filter equivalent Things accordingly to the restrictions in each request
				//Map<transId::service, List<devId>>
				System.out.println("set matrix M");
				matrixM = qosCalculator.createM(requestList,thingsInfo,servNameThingsIdList);
				if(matrixM == null){

					System.out.println("ERROR matrixM");
					
					return;
				}
				
				//matrix U of the utilizations
				//Map<DevId, Map<transId::Service, u_ij>>
				System.out.println("set matrix U");
				matrixU = qosCalculator.createU(thingsInfo, periods);
				if(matrixU == null){
					
					System.out.println("ERROR matrixU");
					
					return;
				}
				
				//matrix F of the normalized energy costs
				System.out.println("set matrix F");
				matrixF = qosCalculator.createF(thingsInfo, coefficients);
				if(matrixF == null){
					
					System.out.println("ERROR matrixF");
					
					return;
				}
				
				System.out.println("iteration number: "+indexTest);
				
				//Map<DevId, Map<transId::ServName ,p_ij>>>
				matrixP = matrixF;
				
//				stat.printInputsABGAP(k, requestList, matrixF, matrixU, coefficients, thingsInfo, servNameThingsIdList, matrixM, prio.name(), split.name());
				
				try{
					result = qosCalculator.ABGAP(k, requestList, matrixP, matrixF, matrixU, coefficients, thingsInfo, servNameThingsIdList, matrixM, epsilon, prio, split);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
				//store reservation result for that iteration
				//Map<iteration::split::medium, Reservation>
				this.testResults.add(new Pair<String,Reserveobj>(indexTest+"::"+split+"::"+medium, result));
				
				this.testCompare.add(new Pair<String,Boolean>(indexTest+"::"+split+"::"+medium, result.isFeasible()));
				
				//increment feasibility counter
				if(result.isFeasible() && split == Split.SINGLE_SPLIT){
					
					howManyFeasSingle++;
				}
				else{
					if(result.isFeasible() && split == Split.MULTI_SPLIT){
						howManyFeasMulti++;
					}
				}
				
				//execute multisplit 
				if(split == Split.SINGLE_SPLIT) split = Split.MULTI_SPLIT;
				else{
					//another heuristic execution starting with single split
					split = Split.SINGLE_SPLIT;
					indexTest++;
				}
				
				
			}

			finishTime = (new Date().getTime() - startTime)/1000;
			
			System.out.println("iterations: "+ITERATIONS);
			System.out.println("split: single; medium "+medium+" how many feasible: "+ howManyFeasSingle);
			System.out.println("split: multi; medium "+medium+" how many feasible: "+ howManyFeasMulti);
			System.out.println("simulationTime: "+finishTime);
			
			simulationsTime = finishTime;
			
			//final results
			printResults();
			
			testResults.clear();
			testCompare.clear();
			
			if(mediumIndex == EQTHINGS_LIST_SIZE-1){
				
				System.out.println("STOP SIMULATION");
				break;
			}
			
			//next factor services on a thing
			mediumIndex++;
			
			medium = mediumList[mediumIndex];
			System.out.println("medium: "+medium);
			servNameThingsIdList.clear();
			servNameThingsIdList = generateThingsServicesData(medium, this.n, this.k, servNameThingsIdList);
			
			thingsInfo = setServicesOnThings(thingsInfo, servNameThingsIdList);
			
			//matrix F of the normalized energy costs
			System.out.println("reset Matrix F because service of each thing and coefficients are changed");
			matrixF = qosCalculator.createF(thingsInfo, coefficients);
			if(matrixF == null){
				
				System.out.println("ERROR matrixF");
				
				return;
			}
			
			stat.medium = medium;
			
			split = Split.SINGLE_SPLIT;
			
			//create matrixM to filter equivalent Things accordingly to the restrictions in each request
			//Map<transId::service, List<devId>>
			
			//eqThings for a service are changed
			System.out.println("reset Matrix M because eqThings Per service and service of each thing are changed");
			matrixM = qosCalculator.createM(requestList,thingsInfo,servNameThingsIdList);
			if(matrixM == null){

				System.out.println("ERROR matrixM");
				
				stopTest = true;
			}
			
			//matrix U of the utilizations
			//Map<DevId, Map<transId::Service, u_ij>>
			
			//services on a thing are changed
			System.out.println("reset Matrix U because service of each thing are changed");
			matrixU = qosCalculator.createU(thingsInfo, periods);
			if(matrixU == null){
				
				System.out.println("ERROR matrixU");
				
				stopTest = true;
			}
			
			
		}		

	}
	
	public void printResults(){
		
		File fileTest = new File("./TestResults");
		if(!fileTest.exists()){
			fileTest.mkdir();
		}
		
		PrintWriter writer1=null;
		FileWriter output1 = null;
		
		PrintWriter writer2=null;
		FileWriter output2 = null;
		
		PrintWriter writer3=null;
		FileWriter output3 = null;
		
		try{
			
			output1 = new FileWriter(fileTest.getAbsoluteFile()+"/testResultsSummary_"+medium+".txt", true);
			writer1 = new PrintWriter(output1);
			
			output2 = new FileWriter(fileTest.getAbsoluteFile()+"/testResultsComparison_"+medium+".txt", true);
			writer2 = new PrintWriter(output2);
			
			output3 = new FileWriter(fileTest.getAbsoluteFile()+"/reservationSummary_"+medium+".txt", true);
			writer3 = new PrintWriter(output3);
			
			//print final results how many feasible
			writer1.println("Iterations: " + ITERATIONS);
			writer1.println("Simulation Time: " + simulationsTime);
			writer1.println("Split: single; medium " + medium + "Feasible Allocations: " + howManyFeasSingle);
			writer1.println("Split: multi; medium " + medium + "Feasible Allocations: " + howManyFeasMulti);
			
			int lineCounter = 0;
			for(Pair<String, Boolean> res : testCompare){
				
				String[] resultType = res.getLeft().split("::");
				int iteration = Integer.valueOf(resultType[0]);

				Split split = Split.valueOf(resultType[1]);
				int medium = Integer.valueOf(resultType[2]);
				boolean feas = res.getRight();
				
				writer2.println("iteration: "+iteration+" split: "+split+" medium: "+medium+" feasible: "+feas);
				
				if(lineCounter < 2)
					lineCounter++;
				else{
					lineCounter=0;
					writer2.println("");
				}
			}
			
			//print final results
			for(Pair<String, Reserveobj> res : testResults){
				
				String[] resultType = res.getLeft().split("::");
				
				int iteration = Integer.valueOf(resultType[0]);
				Split split = Split.valueOf(resultType[1]);
				int medium = Integer.valueOf(resultType[2]);
				Reserveobj result = res.getRight();
				
				writer3.println("iteration: "+iteration+" split: "+split+" medium: "+medium);
				writer3.println("feasible: "+result.isFeasible());
				writer3.println("teta: "+result.getTheta());
				writer3.println();
				writer3.println("Reservation object:");
				
				if(result.isFeasible()){
					writer3.println("total UTILIZATION and total RESIDUAL BATTERY values:");
					for(Map.Entry<String, ThingAssignmentParams> entry: result.getAssignmentsParamsMap().entrySet()){
						writer3.println("devId: "+entry.getKey());
						writer3.println("ThingAssignmentParams:");
						writer3.println("total Capacity: "+entry.getValue().getTotalUtilization());
						writer3.println("total Residual Battery: "+entry.getValue().getResidualBattery());
						writer3.println("");
					}
					
					writer3.println("ALLOCATION SCHEMA:");
					if(!result.getAllocationSchema().isEmpty()){
						for(Map.Entry<String, HashMap<String, AllocationInfo>> entry: result.getAllocationSchema().entrySet()){
							writer3.println("transId: "+entry.getKey());
							
							HashMap<String, AllocationInfo> services = entry.getValue();
							
							writer3.println("services allocated:");
							for(Map.Entry<String, AllocationInfo> entryAllocation: services.entrySet()){
								writer3.println("service Name: "+entryAllocation.getKey());
								
								writer3.println(entryAllocation.toString());
								
							}
							writer3.println("");
						}
					}
					writer3.println("#############################################");
				}	
				else{
					writer3.println("NOT FEASIBLE");
					writer3.println("#############################################");
				}
				writer3.println();
				writer3.println();
				writer3.println();
				writer3.println();
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				output1.flush();
				output1.close();
				
				output2.flush();
				output2.close();
				
				output3.flush();
				output3.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
	            e.printStackTrace();
			}
		}
	}
	
	/* function to create the list of periods relative to the requests */
	private HashMap<String, Double> setPeriodsList(List<Pair<String, Request>> requests){
		
		HashMap<String, Double> periods = new HashMap<>();
		
		//get the first requestCounter requests
		for(int i=0; i < requests.size(); i++){
			
			String transId = new String(requests.get(i).getLeft());
			
			periods.put(transId, new Double(requests.get(i).getRight().getQosRequirements().getMaxRateRequest().doubleValue()));

		}
		
		return periods;
	}
	
	/* function to clone the list of requests */
	private List<Pair<String, Request>> cloneRequests() {
		
		List<Pair<String, Request>> requestsBck = new ArrayList<Pair<String, Request>>();
		
		for(Pair<String, Request> reqEntry: requestList){
			
			//copy the transactionId
			String transId = new String(reqEntry.getLeft());
			Request req = new Request();
			
			//copy the operation type for example queryContext
			req.setOpType(new String(reqEntry.getRight().getOpType()));
			
			//copy the geographical scope of the request, point or circle
			if(reqEntry.getRight().getLocationRequirementPoint() != null){
				Point p = new Point();
				p.setLatitude(reqEntry.getRight().getLocationRequirementPoint().getLocationRequirement().getLatitude());
				p.setLongitude(reqEntry.getRight().getLocationRequirementPoint().getLocationRequirement().getLongitude());
				
				LocationScopeValue<Point> locReq = new LocationScopeValue<Point>();
				locReq.setLocationRequirement(p);
				req.setLocationRequirementPoint(locReq);
			}
			
			if(reqEntry.getRight().getLocationRequirementCircle() != null){
				Circle c = new Circle();
				c.setCenterLatitude(reqEntry.getRight().getLocationRequirementCircle().getLocationRequirement().getCenterLatitude());
				c.setCenterLongitude(reqEntry.getRight().getLocationRequirementCircle().getLocationRequirement().getCenterLongitude());
				c.setRadius(reqEntry.getRight().getLocationRequirementCircle().getLocationRequirement().getRadius());
				
				LocationScopeValue<Circle> locReq = new LocationScopeValue<Circle>();
				locReq.setLocationRequirement(c);
				req.setLocationRequirementCircle(locReq);
			}
			
			//copy max request rate
			double maxRateRequest = reqEntry.getRight().getQosRequirements().getMaxRateRequest().doubleValue();
			
			//copy max response time
			double maxRespTime = reqEntry.getRight().getQosRequirements().getMaxResponseTime().doubleValue();
			
			QoSscopeValue qos = new QoSscopeValue();
			qos.setMaxRateRequest(maxRateRequest);
			qos.setMaxResponseTime(maxRespTime);
			req.setQosRequirements(qos);
			
			//copy the list of required services
			List<String> servListBck = new ArrayList<>(); 
			
			List<String> servList = reqEntry.getRight().getRequiredServicesNameList();
			
			for(String service : servList){
				servListBck.add(new String(service));
			}
			
			req.setRequiredServicesNameList(servListBck);
			
			requestsBck.add(new Pair<String, Request>(transId, req));
		}
		
		System.out.println("clone requests:");
		System.out.println(requestsBck);
		return requestsBck;
	}
	
	/* function that computes the hyperperiod of the requests and the list of coefficients h/p_j */
	private HashMap<String, Integer> hyperperiodComputations(HashMap<String, Double> periods){
		
		HashMap<String, Integer> coefficients = new HashMap<>();
		
		//compute hyperiod h
		Long h = Utils.getHyperperiod(new ArrayList<>(periods.values()));
		
		System.out.println("hyperperiod: "+h);

		Double coeff;

		for(Map.Entry<String, Double> entry: periods.entrySet()){
			Double p_j = entry.getValue();
			coeff = h/p_j;
			coefficients.put(entry.getKey(), coeff.intValue());
		}
		
		System.out.println();
		System.out.println("h/pj for each transId: "+coefficients);
		System.out.println();
		
		return coefficients;
	}
	
	private int getRandomIndex(int max){

		int i = generator.nextInt(max);
		
		return i;
	}
	
	/* functions to generate requests objects */
	private List<Pair<String, Request>> generateRequests(int k){
		
		List<Pair<String, Request>> requestList = new ArrayList<>();
		
		for(int r=0; r<k; r++){
			
			int index = 0;

			String transId = String.valueOf(transIdCounter.getAndIncrement());

			String opType = "queryContext";
			
			index = getRandomIndex(per.length);
			double maxRateRequest  = per[index];
			
			double maxRespTime = 20; //latency[index];
			
			QoSscopeValue qosReq = new QoSscopeValue();
			qosReq.setMaxResponseTime(maxRespTime);
			qosReq.setMaxRateRequest(maxRateRequest);
			
			Circle circle = new Circle();
			circle.setCenterLatitude(0);
			circle.setCenterLongitude(0);
			circle.setRadius(150);
			LocationScopeValue<Circle> locReqCircle = new LocationScopeValue<>();
			locReqCircle.setLocationRequirement(circle);
			
			List<String> requiredServiceList = new ArrayList<>();

			index = getRandomIndex(k);
			String requiredService = String.valueOf(index);
			requiredServiceList.add(requiredService);

			Request req = new Request();
			req.setOpType(opType);
			req.setLocationRequirementCircle(locReqCircle);
			req.setQosRequirements(qosReq);
			req.setRequiredServicesNameList(requiredServiceList);
			
			requestList.add(new Pair<String, Request>(transId, req));
		}
		
		System.out.println("###################");
		System.out.println("requests:");
		System.out.println(requestList);
		System.out.println("###################");
		
		return requestList;
	}
	
//	/* function to set the map of equivalent things */
//	private HashMap<String, ThingsIdList> setServices(int k){
//		
//		HashMap<String, ThingsIdList> servNameThingsIdList = new HashMap<>(k);
//		
//		for(int j=0; j<k; j++){
//			
//			servNameThingsIdList.put(String.valueOf(j), new ThingsIdList());
//		}
//		return servNameThingsIdList;
//	}
	
	/* function that generate things given the list of equivalent things for each service */
	private HashMap<String, Thing> generateThings(int n){
		
		HashMap<String, Thing> thingsInfo = new HashMap<>(n);
		
		for(int i = 0; i < n; i++){
			
			int index = 0;
			Thing t = new Thing();
			
			index = getRandomIndex(bat.length);
			t.setBatteryLevel(bat[index]);
			
			Point point = new Point();
			index = getRandomIndex(coords.length);
			float lat = coords[index];
			index = getRandomIndex(coords.length);
			float lon = coords[index];
			point.setLatitude(lat);
			point.setLongitude(lon);
			t.setCoords(point);
			
			String thingId = String.valueOf(thingIdCounter.getAndIncrement());
			
			t.setServicesList(null);
			thingsInfo.put(thingId, t);
		}
			
		
		//reset thing id counter
		thingIdCounter.set(0);
		
		return thingsInfo;
	}

	
	/* function that generate the list of equivalent things per service, given
	 * the medium of services that a thing should expose */
	private HashMap<String, ThingsIdList> generateThingsServicesData(int medium,  
												int n, 
												int k,
												HashMap<String, ThingsIdList> servNameThingsIdList){
		
		double m = (double)medium/100 * (double)k;
		
		System.out.println("percentace of services on a thing: "+m);
		
		double p = (double)m / (double)k;
		double q = 1 - p;
		
		int[] M = new int[n*k];
		int[] rows = new int[n];
		double tmp;
		
		do{
			
			for(int i=0; i<n; i++){  
		      for(int j=0; j<k; j++){
			
		    	  tmp = (double)generator.nextDouble();
		    	  
    			  if(tmp > q)
    				  M[i * k + j] = 1;
    				  
    			  else
    				  M[i * k + j] = 0;
		      }
		    }

			rows = sum(n, k, M);
		}
		while(min(n, rows)==0 || !checkM(M, n, k));
		
		printM(M);
		
		System.out.println("set eqThings list per service");
		for(int j=0; j<k; j++){
			  
			  //eqThings for a service j
			  List<String> thingsIdList = new ArrayList<>();
			  
		      for(int i=0; i<n; i++){
		    	  
		    	  if(M[i * k + j] == 1)
		    		  thingsIdList.add(String.valueOf(i));

		      }
		      
		      ThingsIdList eqThings = new ThingsIdList();
		      eqThings.setEqThings(thingsIdList);
		 
		      
		      servNameThingsIdList.put(new String(String.valueOf(j)), eqThings);
		}
//		System.out.println("<eq things per service>:");
//		System.out.println(servNameThingsIdList);
		
		return servNameThingsIdList;
	}

	private HashMap<String, Thing> setServicesOnThings(HashMap<String, Thing> thingsInfo, 
													HashMap<String, ThingsIdList> servNameThingsIdList){
		
		System.out.println("set things services");

		for(Map.Entry<String, Thing> entryThing : thingsInfo.entrySet()){
			
			String thingId = entryThing.getKey();
			Thing t = entryThing.getValue();
			
			if(t.getServicesList()!=null)
				t.getServicesList().clear();
			
			List<String> serviceList = new ArrayList<>();
			
			for(Map.Entry<String, ThingsIdList> entryEqThings : servNameThingsIdList.entrySet()){
				
				String serv = entryEqThings.getKey();
				List<String> eqThings = entryEqThings.getValue().getEqThings();
				
				if(eqThings.contains(thingId)){
					serviceList.add(serv);
				}
			}
			
			HashMap<String, ServiceFeatures> services = new HashMap<String, ServiceFeatures>(serviceList.size());
			
			for(String service : serviceList){
	
				ServiceFeatures servFeat = new ServiceFeatures();
				
				//latency = ttransf_1 + texe + ttransf_2
				int index = getRandomIndex(ttransf.length);
				double t_transf_1 = ttransf[index];
				
				index = getRandomIndex(texe.length);
				double t_exe = texe[index];
				
				index = getRandomIndex(ttransf.length);
				double t_transf_2 = ttransf[index];
				
				double latency = t_transf_1 + t_exe + t_transf_2;
				servFeat.setLatency(latency);
				
				//c_ij = prx*ttransf+pmcu*texe+ptx*ttransf
				double enCost = prx*t_transf_1+pmcu*t_exe+ptx*t_transf_2;
				servFeat.setEnergyCost(enCost);
				
				services.put(service, servFeat);
				
			}
				
			t.setServicesList(services);
			
			thingsInfo.put(thingId, t);
		}
		
//		System.out.println("###############################");
//		System.out.println("<Things>: ");
//		
//		for(Map.Entry<String, Thing> entry: thingsInfo.entrySet()){
//			System.out.println("thingId "+entry.getKey());
//			System.out.println("thingBatt "+entry.getValue().getBatteryLevel());
//			
//			HashMap<String, ServiceFeatures> servicesPrint = entry.getValue().getServicesList();
//			for(Map.Entry<String, ServiceFeatures> entryService : servicesPrint.entrySet()){
//				System.out.println("service "+entryService.getKey());
//				System.out.println("latency "+entryService.getValue().getLatency());
//				System.out.println("energy cost "+entryService.getValue().getEnergyCost());
//			}
//			System.out.println("###############################");
//		}
		
		return thingsInfo;
	}

	private boolean checkM(int[] M, int n, int k) {
		
		int sum = 0;
		int i,j;
		for(j=0; j < k; j++){
			for(i=0; i<n; i++){
				
				if(M[i*k+j]==1){
					sum+=M[i*k+j];
				}
			}
			
			if(sum < 1){
				
				return false;
			}
			sum = 0;
		}

		return true;
	}
	
	private int[] sum(int n, int k, int[] M){
	  int i,j;
	  
	  int[] rows = new int[n];
	  
	  for(i=0; i<n; i++){  
	    rows[i] = 0;
	    for(j=0; j<k; j++){
	      rows[i] = rows[i] + M[i * k + j];
	    }
	  }
	  return rows;
	}

	private int min(int n, int[] rows){
	  int i;
	  int min = rows[0];

	  for(i=1; i<n; i++){
	    if(min> rows[i])
	      min = rows[i];
	  }
	  return min;
	}

	
	private void printM(int[] M){
		
		File fileTest = new File("./TestResults");
		if(!fileTest.exists()){
			fileTest.mkdir();
		}
		
		PrintWriter writer=null;
		FileWriter output = null;
		
		try{
			output = new FileWriter(fileTest.getAbsoluteFile()+"/M_medium_"+medium+".txt", true);
			writer = new PrintWriter(output);
		
			writer.println("<Matrix M>: ");
			
			for(int i=0; i<n; i++){  
			      for(int j=0; j<k; j++){
			    	  
			    	  writer.print(M[i*k + j] + " ");
			      }
			      writer.println();
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				output.flush();
				output.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
	            e.printStackTrace();
			}
		}
	}
}
