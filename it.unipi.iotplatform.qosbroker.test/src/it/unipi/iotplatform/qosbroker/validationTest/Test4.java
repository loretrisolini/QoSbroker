package it.unipi.iotplatform.qosbroker.validationTest;

import it.unipi.iotplatform.qosbroker.api.datamodel.LocationScopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.Priority;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSscopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.Reserveobj;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.Split;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
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
import java.util.concurrent.atomic.AtomicInteger;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;

public class Test4 {

private static final float[] coords = {0, 2, 4, 6, 8, 10};
	
	private static final double ttransf[]={3.0 / 1000, 3.5 / 1000, 4.0 / 1000, 4.5 / 1000, 5.0 / 1000, 5.5 / 1000, 6.0 / 1000, 6.5 / 1000, 7.0 / 1000, 7.5 / 1000, 8.0 / 1000, 8.5 / 1000, 9.0 / 1000, 9.5 / 1000, 10.0 / 1000}; //s
	private static final double pmcu = 3.6 * (10) / 3600; //mW/s
	private static final double prx = 3.6 * (18.8) / 3600; //mW/s
	private static final double ptx = 3.6 * (17.4) / 3600; //mW/s
	private static final double texe[]={1.0 / 1000, 1.5 / 1000, 1.8 / 1000, 2.0 / 1000, 2.5 / 1000}; //s
	
	private static final double per[]={10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0}; //s
										//50		40		30		20		10      5
	private static final double bat[]={25.355, 20.284, 15.213, 10.142, 5.071, 2.535};//{25.355, 20.284, 15.213, 10.142, 5.071, 2.535}; //mJ/100000
									//{50.710, 45.639, 40.568, 35.497, 30.426, 25.355}
	private int k;
	private int n;
	private final int[] mediumList = {15, 25, 50, 75};
	private int mediumIndex;
	
	private final int EQTHINGS_LIST_SIZE = 4;
	private int[] M;
	
	private final Random generator;
	private final Long seed;
	
	
	private final AtomicInteger thingIdCounter = new AtomicInteger(0);
	private final AtomicInteger transIdCounter = new AtomicInteger(0);
	
	private HashMap<String, Thing> thingsInfo;
	private HashMap<String, ThingsIdList> servNameThingsIdList;
	private List<Pair<String, Request>> requestList;
	
	//HashMap to store p_j
	private HashMap<String, Double> periods;
	//HashMap to store h/p_j 
	private HashMap<String, Integer> coefficients;
	//List of requests that modify in the execution of the tests
	private List<Pair<String, Request>> requestsBck;
	private HashMap<String,HashMap<String, Double>> matrixF;

	//Map<split::medium, <#allocazioniFeas, SimulationTime>>
	private HashMap<String, Pair<Integer, Long>> testResults;

	private Long startTime;
	private Long finishTime;
	
	private final QoSCalculator qosCalculator = new QoSCalculator();
	private int medium;
	
	private double epsilon; 
	private Split split;
	

	private static Statistics stat;
	private int feasibleAllocations = 0;
	
	
	//transId richieste fallite
	private ArrayList<String> failedRequestList;
	
	public Test4(
			long seed,
			int k,
			int n) {

		failedRequestList = new ArrayList<>();
		stat = new Statistics();
		
		this.seed = seed;
		generator = new Random(this.seed);
		
		this.k = k;
		this.n = n;

		this.split = Split.SINGLE_SPLIT;
		this.epsilon = 0.001;
		
		M = new int[n*k];
		
		System.out.println("generate requests");
		generateRequests();
		
		System.out.println("set services");
		setServices();
		
		System.out.println("generate things");
		generateThings();
		
		mediumIndex = 1;
		medium = mediumList[mediumIndex];
		
		System.out.println("medium: "+medium);
		generateThingsServicesData(medium);
		
		stat.medium = medium;
		
		System.out.println("####################################");
		System.out.println("thingsInfo: "+thingsInfo);
		System.out.println("####################################");
		System.out.println("servNameThingsIdList: "+servNameThingsIdList);
		System.out.println("####################################");

		testResults = new HashMap<String, Pair<Integer, Long>>();
		
		startTime = new Date().getTime();
	}


	public void allocationTest(){
		
		//set result object
		Reserveobj result = new Reserveobj();;
		result.setFeasible(false);

		//store number of services
		int k=this.k;
		
		//clone request list, create periodList
		System.out.println("clone requests");
		cloneRequests();
		
		//set periods list
		setPeriodsList();
		System.out.println("set periods: "+periods);
		
		//create matrixM to filter equivalent Things accordingly to the restrictions in each request
		//Map<transId::service, List<devId>>
		System.out.println("set matrix M");
		HashMap<String, List<String>> matrixM = qosCalculator.createM(requestsBck,thingsInfo,servNameThingsIdList);
		if(matrixM == null){

			System.out.println("ERROR matrixM");
			
			return;
		}
		
		//matrix U of the utilizations
		//Map<DevId, Map<transId::Service, u_ij>>
		System.out.println("set matrix U");
		HashMap<String,HashMap<String, Double>> matrixU = qosCalculator.createU(thingsInfo, periods);
		if(matrixU == null){
			
			System.out.println("ERROR matrixU");
			
			return;
		}
		
		//compute h and set coefficients h/p_j
		hyperperiodComputations();
		
		//matrix F of the normalized energy costs
		System.out.println("set matrix F");
		matrixF = qosCalculator.createF(thingsInfo, coefficients);
		if(matrixF == null){
			
			System.out.println("ERROR matrixF");
			
			return;
		}
		
		Priority prio = Priority.BATTERY;
		stat.printInputsABGAP(k, requestsBck, matrixF, matrixU, coefficients, thingsInfo, servNameThingsIdList, matrixM, prio.name(), split.name());
		
		boolean stopTest = false;
		
		while(!stopTest){
			
			System.out.println("Test with medium "+medium+" split "+split.name());
			System.out.println("############################################");
			
			//stop at the first feasible allocation or when all the
			//requests are finished
			while(!result.isFeasible() && !requestsBck.isEmpty()){
				
				//Map<DevId, Map<transId::ServName ,p_ij>>>
				HashMap<String,HashMap<String,Double>> matrixP = matrixF;
				
				try{
					result = qosCalculator.ABGAP(k, requestsBck, matrixP, matrixF, matrixU, coefficients, thingsInfo, servNameThingsIdList, matrixM, epsilon, prio, split);
				}
				catch(Exception e){
					e.printStackTrace();
				}
					
				if(!result.isFeasible()){
					
					String transIdNotFeas = result.getTransIdNotFeas();
					
					System.out.println("transId failed request: "+transIdNotFeas);
					
					failedRequestList.add(transIdNotFeas);
					
					System.out.println("remove period and coefficient of failed transId");
					coefficients.remove(transIdNotFeas);
					periods.remove(transIdNotFeas);
					
					for(int i = 0; i < requestsBck.size(); i++){
						
						String transId = requestsBck.get(i).getLeft();
						
						//remove request not feasible
						if(transId.contentEquals(transIdNotFeas)){
							
							System.out.println("remove request number: "+i);
							
							requestsBck.remove(i);
							
							break;
						}
						
					}
					
					System.out.println("###################");
					System.out.println("new requests list: \n");
					System.out.println(requestsBck);
					System.out.println("###################");
					
					//decrement total reqs counter
					k--;
					
					//compute h and set coefficients h/p_j
					hyperperiodComputations();

					//matrix F of the normalized energy costs
					matrixF = qosCalculator.createF(thingsInfo, coefficients);
					if(matrixF == null){
						
						System.out.println("ERROR matrixF");
						
						return;
					}
				}
			}
			
			if(!result.isFeasible()) stat.printAllocationSchema(result.getAllocationSchema(), split.name());
			
			//store simulation results
			finishTime = (new Date().getTime() - startTime)/1000;
			
			feasibleAllocations = this.k - failedRequestList.size();
			
			System.out.println("Feas allocations: "+feasibleAllocations+" time: "+finishTime);
			
			testResults.put(split.name()+"::"+medium, new Pair<Integer, Long>(feasibleAllocations, finishTime));
			
			//print intermediate results
			printResults(false);

			if(split == Split.MULTI_SPLIT && mediumIndex == EQTHINGS_LIST_SIZE-1){
				
				System.out.println("STOP SIMULATION");
				break;
			}
			
			//reset start time
			startTime = new Date().getTime();
			
			//clear failed request list
			failedRequestList.clear();
			
			result = new Reserveobj();
			result.setFeasible(false);

			//store number of services
			k=this.k;
			
			//clone request list, create periodList
			System.out.println("clone requests");
			cloneRequests();
			
			//set periods list
			setPeriodsList();
			System.out.println("set periods: "+periods);
			
			//compute h and set coefficients h/p_j
			hyperperiodComputations();

			//matrix F of the normalized energy costs
			matrixF = qosCalculator.createF(thingsInfo, coefficients);
			if(matrixF == null){
				
				System.out.println("ERROR matrixF");
				
				return;
			}

			if(split == Split.SINGLE_SPLIT){
				
				split = Split.MULTI_SPLIT;
				
			}
			else{
				//next factor services on a thing
				mediumIndex++;
				
				System.out.println("set services");
				setServices();
				
				medium = mediumList[mediumIndex];
				System.out.println("medium: "+medium);
				generateThingsServicesData(medium);
				
				printM(M);
				
				stat.medium = medium;
				
				split = Split.SINGLE_SPLIT;
				
				//create matrixM to filter equivalent Things accordingly to the restrictions in each request
				//Map<transId::service, List<devId>>
				
				//eqThings for a service are changed
				System.out.println("reset Matrix M because eqThings Per service is changed");
				matrixM = qosCalculator.createM(requestsBck,thingsInfo,servNameThingsIdList);
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
		
		//final results
		printResults(true);
	}
	
	public void printResults(boolean totalResults){
		
		File fileTest = new File("./TestResults");
		if(!fileTest.exists()){
			fileTest.mkdir();
		}
		
		PrintWriter writer=null;
		FileWriter output = null;
		
		try{
			
			if(!totalResults){
				
				//print intermediate results
				output = new FileWriter(fileTest.getAbsoluteFile()+"/Result_"+split.name()+"_medium_"+medium+".txt", true);
				writer = new PrintWriter(output);
				
				Pair<Integer, Long> res = testResults.get(split.name()+"::"+medium);
				
				int feasibleAlloc = res.getLeft();
				Long time = res.getRight();
				
				writer.println("Split: "+split+" medium: "+medium+" feasible: "+feasibleAlloc+" Simulation Time: "+time);
				
			}
			else{
				output = new FileWriter(fileTest.getAbsoluteFile()+"/testResultsSummary.txt", true);
				writer = new PrintWriter(output);
				
				//print final results
				for(Map.Entry<String, Pair<Integer, Long>> res : testResults.entrySet()){
					
					String[] resultType = res.getKey().split("::");
					
					writer.println("Split: "+resultType[0]+" medium "+resultType[1]);
					writer.println("Feasible Allocations: "+res.getValue().getLeft()+" Simulation Time: "+res.getValue().getRight());
					writer.println();
				}
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
	
	private void setPeriodsList(){
		
		periods = new HashMap<>();
		
		//get the first requestCounter requests
		for(int i=0; i < requestsBck.size(); i++){
			
			String transId = requestsBck.get(i).getLeft();
			
			periods.put(transId, requestsBck.get(i).getRight().getQosRequirements().getMaxRateRequest());

		}
	}
	
	private void cloneRequests(){
		
		requestsBck = new ArrayList<>();
		
		//get the first requestCounter requests
		for(int i=0; i < requestList.size(); i++){
			requestsBck.add(requestList.get(i));
			
		}
	}
	
	private void hyperperiodComputations(){
		
		coefficients = new HashMap<>();
		
		//compute hyperiod h
		Long h = Utils.getHyperperiod(new ArrayList<>(periods.values()));
		
		System.out.println("hyperperiod: "+h);

		Double coeff;
		//complete to fill Map<transactionId, <p_j, h/p_j>> with h/p_j
		for(Map.Entry<String, Double> entry: periods.entrySet()){
			Double p_j = entry.getValue();
			coeff = h/p_j;
			coefficients.put(entry.getKey(), coeff.intValue());
		}
		
		System.out.println();
		System.out.println("h/pj for each transId: "+coefficients);
		System.out.println();
	}
	
	private int getRandomIndex(int max){

		int i = generator.nextInt(max);
		
		return i;
	}
	
	private void generateRequests(){
		
		requestList = new ArrayList<>();
		
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
			circle.setRadius(50);
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
	}
	
	private void setServices(){
		
		servNameThingsIdList = new HashMap<>(k);
		
		for(int j=0; j<k; j++){
			
			servNameThingsIdList.put(String.valueOf(j), new ThingsIdList());
		}
		
	}
	
	private void generateThings(){
		
		thingsInfo = new HashMap<>(n);
		
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
			thingsInfo.put(thingId, t);
			
		}
	}
	
	private void generateThingsServicesData(int medium){
		
		double m = (double)medium/100 * (double)k;
		
		System.out.println("percentace of services on a thing: "+m);
		
		double p = (double)m / (double)k;
		double q = 1 - p;
		
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
		while(min(n, rows)==0 || !checkM());
		
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
		      this.servNameThingsIdList.put(String.valueOf(j), eqThings);
		}
		System.out.println("<eq things per service>:");
		System.out.println(this.servNameThingsIdList);
		
		System.out.println("");
		System.out.println("");
		System.out.println("set things");
		for(Map.Entry<String, Thing> entryThing : thingsInfo.entrySet()){
			
			String thingId = entryThing.getKey();
			Thing t = entryThing.getValue();
			
			List<String> serviceList = new ArrayList<>();
			
			for(Map.Entry<String, ThingsIdList> entryEqThings : servNameThingsIdList.entrySet()){
				
				String service = entryEqThings.getKey();
				List<String> eqThings = entryEqThings.getValue().getEqThings();
				
				if(eqThings.contains(thingId)){
					
					serviceList.add(service);
				}
			}
			
			//assumption one service per thing
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
				
				//one service per thing
				services.put(service, servFeat);
				
			}
				
			t.setServicesList(services);
			thingsInfo.put(thingId, t);
		}
		
		System.out.println("###############################");
		System.out.println("<Things>: ");
		
		for(Map.Entry<String, Thing> entry: thingsInfo.entrySet()){
			System.out.println("thingId "+entry.getKey());
			System.out.println("thingBatt "+entry.getValue().getBatteryLevel());
			
			HashMap<String, ServiceFeatures> services = entry.getValue().getServicesList();
			for(Map.Entry<String, ServiceFeatures> entryService : services.entrySet()){
				System.out.println("service "+entryService.getKey());
				System.out.println("latency "+entryService.getValue().getLatency());
				System.out.println("energy cost "+entryService.getValue().getEnergyCost());
			}
			System.out.println("###############################");
		}
	}


	private boolean checkM() {
		
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
			output = new FileWriter(fileTest.getAbsoluteFile()+"/M_"+split.name()+"_medium_"+medium+".txt", true);
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
