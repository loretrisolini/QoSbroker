package it.unipi.iotplatform.qosbroker.threadHeuristic;

import it.unipi.iotplatform.qosbroker.api.datamodel.AllocationPolicy;
import it.unipi.iotplatform.qosbroker.api.datamodel.LocationScopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.Priority;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSscopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.Reserveobj;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServicePeriodParams;
import it.unipi.iotplatform.qosbroker.api.datamodel.Split;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.api.utils.Statistics;
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
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;

public class TestThread2 implements Runnable{

		private static final float[] coords = {0, 2, 4, 6, 8, 10};
		
		private static final double ttransf[]={3.0 / 1000, 3.5 / 1000, 4.0 / 1000, 4.5 / 1000, 5.0 / 1000, 5.5 / 1000, 6.0 / 1000, 6.5 / 1000, 7.0 / 1000, 7.5 / 1000, 8.0 / 1000, 8.5 / 1000, 9.0 / 1000, 9.5 / 1000, 10.0 / 1000}; //s
		private static final double pmcu = 3.6 * (10) / 3600; //mW/s
		private static final double prx = 3.6 * (18.8) / 3600; //mW/s
		private static final double ptx = 3.6 * (17.4) / 3600; //mW/s
		private static final double texe[]={1.0 / 1000, 1.5 / 1000, 1.8 / 1000, 2.0 / 1000, 2.5 / 1000}; //s
		
		private static final double per[]={10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0}; //s
											//50		40		30		20		10      5
		private static final double bat[]={25.355, 20.284, 15.213, 10.142, 5.071, 2.535}; //mJ/100000
										//{50.710, 45.639, 40.568, 35.497, 30.426, 25.355}
		private int requests;
		private int requiredServicesPerRequest;
		private int totalServices;
		private int thingsNumber;
		private final int[] mediumList = {15, 25, 50, 75};
		private int mediumIndex;
		
		private final int EQTHINGS_LIST_SIZE = 4;
		private final int[] M;
		
		private final Random generator;
		private final Long seed;
		
		private ScheduledExecutorService scheduledExecutorService;
		private final AtomicInteger thingIdCounter = new AtomicInteger(0);
		private final AtomicInteger transIdCounter = new AtomicInteger(0);
		private HashMap<String, Thing> thingsInfo;
		private HashMap<String, ThingsIdList> servNameThingsIdList;
		
		private List<Pair<String, Request>> requestList;
	
		private List<Pair<Long, Boolean>> arrivalTimeList = new ArrayList<>();
		private Long startTime;
		
		private final QoSCalculator qosCalculator = new QoSCalculator();
		private int medium;
		
		private double epsilon; 
		private Split split;
		
		private boolean stopTest;
		private static Statistics stat;
		private final AtomicInteger feasibleAllocations = new AtomicInteger(0);
		
		private static int requestCounter = 0;
		
		//indice richieste fallite
		private static ArrayList<Integer> failedRequestList;
		private final Lock lock = new ReentrantLock();
		
		public TestThread2(
				long seed,
				int requests,
				int requiredServicesPerRequest,
				int totalServices,
				int thingsNumber,
				ScheduledExecutorService scheduledExecutorService) {

			stopTest = false;
			failedRequestList = new ArrayList<>();
			stat = new Statistics();
			
			this.seed = seed;
			generator = new Random(this.seed);
			
			this.requests = requests;
			this.requiredServicesPerRequest = requiredServicesPerRequest;
			this.totalServices = totalServices;
			this.thingsNumber = thingsNumber;
			this.scheduledExecutorService = scheduledExecutorService;
			this.split = Split.SINGLE_SPLIT;
			this.epsilon = 0.001;
			
			M = new int[thingsNumber*totalServices];
			
			generateRequests();
			generateServices();
			generateThings();
			
			mediumIndex = 0;
			medium = mediumList[mediumIndex];
			generateThingsServicesData(medium);
			
			printM(M);
			
			stat.medium = medium;
			
			System.out.println("####################################");
			System.out.println("thingsInfo: "+thingsInfo);
			System.out.println("####################################");
			System.out.println("servNameThingsIdList: "+servNameThingsIdList);
			System.out.println("####################################");
			
			startTime = new Date().getTime();
		}


		@Override
	    public void run(){
			
			lock.lock();
			
			if(this.scheduledExecutorService.isShutdown()){
				lock.unlock();
				return;
			}
			
			allocationTest(this.split);
			
			//stop
			if(stopTest && mediumIndex == EQTHINGS_LIST_SIZE-1 && split == Split.MULTI_SPLIT){
				
				System.out.println("STOP");
				
				lock.unlock();
				this.scheduledExecutorService.shutdownNow();
				return;
			}
			
			if(stopTest){

				requestCounter = 0;
				stopTest = false;
				
				startTime = new Date().getTime();
				arrivalTimeList.clear();
				failedRequestList.clear();
				feasibleAllocations.set(0);
				
				if(split == Split.SINGLE_SPLIT){
					
					System.out.println("NOW MULTI SPLIT");
					this.split = Split.MULTI_SPLIT;
					
					printM(M);
				}
				else{
					
					System.out.println("NOW SINGLE SPLIT");
					this.split = Split.SINGLE_SPLIT;
					
					System.out.println("Next factor of services on a thing");
					//the next number of eqThings Per Service
					mediumIndex++;
					medium = mediumList[mediumIndex];
					stat.medium = medium;				
					generateServices();
					generateThingsServicesData(mediumList[mediumIndex]);
					
					printM(M);
				}

			}
			
			lock.unlock();
	    }


		public void allocationTest(Split split){
			
			//increment request Counter
			requestCounter++;
			
			Reserveobj result = null;
			
			System.out.println("Start Request number: "+requestCounter+" medium "+medium);
			System.out.println("############################################");
			
			//set arrival time of the request
			Long ArrivalTime = new Date().getTime() - startTime;
				
			List<Pair<String, Request>> requests = new ArrayList<>();
			ArrayList<Double> periodsList = new ArrayList<Double>();
			HashMap<String, ServicePeriodParams> servPeriodsMap = new HashMap<>();
			int k=0;
			
			//get the first requestCounter requests
			for(int i=0; i < requestCounter; i++){
				requests.add(requestList.get(i));
				
				k+=requestList.get(i).getRight().getRequiredServicesNameList().size();
				
				String transId = requestList.get(i).getLeft();
				
				servPeriodsMap.put(transId, new ServicePeriodParams());
				servPeriodsMap.get(transId).setPeriod(requestList.get(i).getRight().getQosRequirements().getMaxRateRequest());
				periodsList.add(requestList.get(i).getRight().getQosRequirements().getMaxRateRequest());
			}

			//compute hyperiod h
			Long h = ServicePeriodParams.getHyperperiod(periodsList);
			
			System.out.println("hyperperiod: "+h);

			Double coeff;
			//complete to fill Map<transactionId, <p_j, h/p_j>> with h/p_j
			for(Map.Entry<String, ServicePeriodParams> entry: servPeriodsMap.entrySet()){
				Double p_j = entry.getValue().getPeriod();
				coeff = h/p_j;
				entry.getValue().setNj(coeff.intValue());
			}
			
			//create matrixM to filter equivalent Things accordingly to the restrictions in each request
			HashMap<String, List<String>> matrixM = qosCalculator.createM(requests,thingsInfo,servNameThingsIdList);
			if(matrixM == null){

				System.out.println("ERROR matrixM");
				
				stopTest = true;
				mediumIndex = EQTHINGS_LIST_SIZE-1;
				return;
			}
			
			//matrix F of the normalized energy costs
			HashMap<String,HashMap<String, Double>> matrixF = qosCalculator.createF(thingsInfo, servPeriodsMap);
			if(matrixF == null){
				
				System.out.println("ERROR matrixF");
				
				stopTest = true;
				mediumIndex = EQTHINGS_LIST_SIZE-1;
				return;
			}
			
			//matrix U of the utilizations
			HashMap<String,HashMap<String, Double>> matrixU = qosCalculator.createU(thingsInfo, servPeriodsMap);
			if(matrixU == null){
				
				System.out.println("ERROR matrixU");
				
				stopTest = true;
				mediumIndex = EQTHINGS_LIST_SIZE-1;
				return;
			}
			
			//coeff h/p_j for each request 
			HashMap<String, Integer> hyperperiodPeriodMap = new HashMap<>();
			for(Map.Entry<String, ServicePeriodParams> entryPeriod :servPeriodsMap.entrySet()){
				
				//transId
				String transId = entryPeriod.getKey();
				//h/p_j
				Integer nj = entryPeriod.getValue().getNj();
				
				hyperperiodPeriodMap.put(transId, nj);
			}
			
			System.out.println();
			System.out.println("h/pj for each transId: "+hyperperiodPeriodMap);
			System.out.println();

			AllocationPolicy allocPolicy = AllocationPolicy.WRoundRobin;
			Priority prio = Priority.BATTERY;

			
			//Map<DevId, Map<transId::ServName ,p_ij>>>
			HashMap<String,HashMap<String,Double>> matrixP = matrixF;
			//execution with p_ij=f_ij
			
			try{

				result = qosCalculator.ABGAP(k, requests, matrixP, matrixF, matrixU, hyperperiodPeriodMap, thingsInfo, servNameThingsIdList, matrixM, epsilon, prio, split);

				result.setAllocPolicy(allocPolicy);
				result.setPriority(prio);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
				
			if(result == null){
				System.out.println("QoSCalculator ERROR");
				
				stopTest = true;
				mediumIndex = EQTHINGS_LIST_SIZE-1;
				return;
			}
			
			
			if(result.isFeasible()){
				
				feasibleAllocations.addAndGet(1);
				
				arrivalTimeList.add(new Pair<Long,Boolean>(ArrivalTime, true));

				stat.printAllocationSchema(result.getAllocationSchema(), split.name());
				
			}
			else{
				
				arrivalTimeList.add(new Pair<Long,Boolean>(ArrivalTime, false));
				
				failedRequestList.add(requestCounter);
			}

			if((requestCounter+1) > requestList.size()){
				System.out.println("Requests terminated");
				
				stat.printInputsABGAP(k, requests, matrixF, matrixU, hyperperiodPeriodMap, thingsInfo, servNameThingsIdList, matrixM, prio.name(), split.name());
				
				printResults();
				
				stopTest = true;
				
				return;
			}
		}
		
		public void printResults(){
			
			File fileTest = new File("./Test_"+split.name());
			if(!fileTest.exists()){
				fileTest.mkdir();
			}
			
			PrintWriter writer=null;
			FileWriter output = null;
			
			try{
				output = new FileWriter(fileTest.getAbsoluteFile()+"/testResults_"+split.name()+"_medium_"+medium+".txt", true);
				writer = new PrintWriter(output);
				writer.println("####################################");
				writer.println("####################################");
				
				writer.println("Final Results "+split.name()+"_medium_"+medium+" :");
				System.out.println("Final Results "+split.name()+"_medium_"+medium+" :");
				
				writer.println("feasible allocations: "+ feasibleAllocations);
				System.out.println("feasible allocations: "+ feasibleAllocations);
				writer.println("<Arrival Times, result>: "+ arrivalTimeList);
				System.out.println("<Arrival Times, result>: "+ arrivalTimeList);
				
				writer.println("failedRequests indexes: " + failedRequestList);
				System.out.println("failedRequests indexes: " + failedRequestList);
				
				writer.println("####################################");   	  
				writer.println("####################################");
				writer.println("<Serv Eq Things>: ");
				writer.println(servNameThingsIdList);
				
				writer.println("####################################");
				writer.println("####################################");
				
				writer.println("<Things Info>: ");
				
				for(Map.Entry<String, Thing> entry: thingsInfo.entrySet()){
					writer.println("thingId "+entry.getKey());
					writer.println("thingBatt "+entry.getValue().getBatteryLevel());
					
					HashMap<String, ServiceFeatures> services = entry.getValue().getServicesList();
					for(Map.Entry<String, ServiceFeatures> entryService : services.entrySet()){
						writer.println("service "+entryService.getKey());
						writer.println("latency "+entryService.getValue().getLatency());
						writer.println("latency "+entryService.getValue().getEnergyCost());
					}
					writer.println();
				}
					
				writer.println("####################################");
				writer.println("####################################");
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
		
		private int getRandomIndex(int max){

			int i = generator.nextInt(max);
			
			return i;
		}
		
		private void generateRequests(){
			
			requestList = new ArrayList<>();
			
			for(int r=0; r<requests; r++){
				
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
				for(int s=0; s < requiredServicesPerRequest; s++){
					
					index = getRandomIndex(totalServices);
					String requiredService = String.valueOf(index);
					
					requiredServiceList.add(requiredService);
				}
				
				Request req = new Request();
				req.setOpType(opType);
				req.setLocationRequirementCircle(locReqCircle);
				req.setQosRequirements(qosReq);
				req.setRequiredServicesNameList(requiredServiceList);
				
				requestList.add(new Pair<String, Request>(transId, req));
			}

//			System.out.println("requestList: "+requestList);
//			System.out.println("####################################");
//			writer.println("requestList: "+requestList);
//			writer.println("####################################");
			
		}
		
		private void generateServices(){
			
			servNameThingsIdList = new HashMap<>(totalServices);
			
			for(int j=0; j<totalServices; j++){
				
				servNameThingsIdList.put(String.valueOf(j), new ThingsIdList());
			}
			
		}
		
		private void generateThings(){
			
			thingsInfo = new HashMap<>(thingsNumber);
			
			for(int i = 0; i < thingsNumber; i++){
				
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
			
			double p = (double)medium / (double)totalServices;
			double q = 1 - p;
			
			int[] rows = new int[thingsNumber];
			double tmp;
			
			do{
				
				for(int i=0; i<thingsNumber; i++){  
			      for(int j=0; j<totalServices; j++){
				
			    	  tmp = (double)generator.nextDouble();
			    	  
	    			  if(tmp > q)
	    				  M[i * totalServices + j] = 1;
	    				  
	    			  else
	    				  M[i * totalServices + j] = 0;
			      }
			    }
				
				rows = sum(thingsNumber, totalServices, M);
				
			}
			while(min(thingsNumber, rows)==0 || !checkM(M));
			
			for(int j=0; j<totalServices; j++){  
			      for(int i=0; i<thingsNumber; i++){
			    	  
			    	  if(M[i * totalServices + j] == 1)
			    		  servNameThingsIdList.get(String.valueOf(j)).getEqThings().add(String.valueOf(i));
			      }
			}
			
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
			}
		}


		private boolean checkM(int[] m2) {
			
			int sum = 0;
			
			for(int j=0; j < totalServices; j++){
				for(int i=0; i<thingsNumber; i++){
					
					if(m2[i*totalServices+j]==1){
						sum+=m2[i*totalServices+j];
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
		  
		  int[] rows = new int[thingsNumber];
		  
		  for(i=0; i<n; i++){  
		    rows[i] = 0;
		    for(j=0; j<k; j++){
		      rows[i] = rows[i] + M[i * k + j];
		    }
		  }
		  return rows;
		}

		private int min(int n, int[] rows)
		{
		  int i;
		  int min = rows[0];

		  for(i=1; i<n; i++){
		    if(min> rows[i])
		      min = rows[i];
		  }
		  return min;
		}

		
		private void printM(int[] M){
			
			File fileTest = new File("./Test_"+split.name());
			if(!fileTest.exists()){
				fileTest.mkdir();
			}
			
			PrintWriter writer=null;
			FileWriter output = null;
			
			try{
				output = new FileWriter(fileTest.getAbsoluteFile()+"/M_"+split.name()+"_medium_"+medium+".txt", true);
				writer = new PrintWriter(output);
			
				writer.println("<Matrix M>: ");
				
				for(int i=0; i<thingsNumber; i++){  
				      for(int j=0; j<totalServices; j++){
				    	  
				    	  writer.print(M[i*totalServices + j] + " ");
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
