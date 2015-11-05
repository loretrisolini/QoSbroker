package it.unipi.iotplatform.qosbroker.threadHeuristic;

import it.unipi.iotplatform.qosbroker.api.datamodel.LocationScopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSscopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServicePeriodParams;
import it.unipi.iotplatform.qosbroker.api.datamodel.Split;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
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
		private static final double bat[]={50.710, 45.639, 40.568, 35.497, 30.426, 25.355}; //mJ/100000
		
		private int requests;
		private int requiredServicesPerRequest;
		private int totalServices;
		private int thingsNumber;
		private final int[] mediumList = {15, 25, 50, 75, 100};
		private int mediumIndex;
		
		private final int EQTHINGS_LIST_SIZE = 5;
		private final int[] M;
		
		private Random generator;
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
		
		private final AtomicInteger feasibleAllocations = new AtomicInteger(0);
		
		private static int requestCounter = 0;
		private final Lock lock = new ReentrantLock();
		
		public TestThread2(
				long seed,
				int requests,
				int requiredServicesPerRequest,
				int totalServices,
				int thingsNumber,
				Split split,
				ScheduledExecutorService scheduledExecutorService) {

			stopTest = false;
			generator = new Random(seed);
			
			this.requests = requests;
			this.requiredServicesPerRequest = requiredServicesPerRequest;
			this.totalServices = totalServices;
			this.thingsNumber = thingsNumber;
			this.scheduledExecutorService = scheduledExecutorService;
			this.split = split;
			this.epsilon = 0.001;
			
			M = new int[thingsNumber*totalServices];
			
			generateRequests();
			generateServices();
			generateThings();
			
			mediumIndex = 0;
			medium = mediumList[mediumIndex];
			generateThingsServicesData(medium);
			
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
			if(stopTest && mediumIndex == EQTHINGS_LIST_SIZE-1){
				
				System.out.println("STOP");
				
				lock.unlock();
				this.scheduledExecutorService.shutdownNow();
				return;
			}

			if(stopTest){
				requestCounter = 0;
				stopTest = false;
				//the next number of eqThings Per Service
				mediumIndex++;
				
				medium = mediumList[mediumIndex];
				
				startTime = new Date().getTime();
				arrivalTimeList.clear();
				feasibleAllocations.set(0);;
				
				generateServices();
				generateThingsServicesData(mediumList[mediumIndex]);
					
				System.out.println("Next EqThings Per Service Number");
				
			}
			
			lock.unlock();
	    }


		public void allocationTest(Split split){
			
			requestCounter++;
			
			if(requestCounter == requestList.size()){
				System.out.println("Requests terminated");
				
				printResults();
				
				stopTest = true;
				
				return;
			}
			
			System.out.println("Start Request number: "+requestCounter);
			System.out.println("############################################");
			
			Long ArrivalTime = new Date().getTime() - startTime;
				
			List<Pair<String, Request>> requests = new ArrayList<>();
			ArrayList<Double> periodsList = new ArrayList<Double>();
			HashMap<String, ServicePeriodParams> servPeriodsMap = new HashMap<>();
			int k=0;
			
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
			
			ReservationResults result = null;
			
			try{

				result = qosCalculator.computeAllocation(k, requests, servPeriodsMap, thingsInfo, servNameThingsIdList, epsilon, split);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
				
			if(result == null){
				System.out.println("QoSCalculator ERROR");
				return;
			}
			
			
			if(result.isFeas()){
				
				feasibleAllocations.addAndGet(1);
				
				arrivalTimeList.add(new Pair<Long,Boolean>(ArrivalTime, true));

			}
			else{
				
				arrivalTimeList.add(new Pair<Long,Boolean>(ArrivalTime, false));

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
				
			}
			while(!checkM(M));
			
			printM(M);
			
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
