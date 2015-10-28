package it.unipi.iotplatform.restcontroller;

import it.unipi.iotplatform.qosbroker.api.datamodel.LocationScopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSscopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;

public class RestClient {

	private final Double[] latency = {0.10, 0.20, 0.40, 0.60, 0.80, 1.0};
	private final Double[] energyCost = {0.10, 0.20, 0.40, 0.60, 0.80, 1.0};
	private final int[] periods = {10, 20, 40, 60, 80, 100};
	private final Double[] battery = {10.0, 20.0, 40.0, 60.0, 80.0, 100.0};
	private final float[] coords = {0, 2, 4, 6, 8, 10};
	
	private final QoSCalculator qosCalculator = new QoSCalculator();
	
	/*int k, List<Pair<String, Request>> requests, 
	HashMap<String, ServicePeriodParams> servPeriodsMap,
	HashMap<String, Thing> thingsInfo,
	HashMap<String, ThingsIdList> servNameThingsIdList,
	double epsilon,
	Split split*/
	
	private static int r = 0;
	private static File file;
	
	//split <-- single or multiple
	//#EqThings x Serv,
	//#reqs and #requiresServ <-- assumptions 1 required service per request
	//#TotalNumber of services[temperature, humidity, CO2, presence]
	public void main(String[] args) {

		if(args.length < 10){
			System.out.println("Error num of params not correct");
			return;
		}
		
		Split split = Split.valueOf(args[0]);
		if(split == null || split != Split.SINGLE_SPLIT || split != Split.MULTI_SPLIT){
			System.out.println("Error split param");
			return;
		}
		
		//read parameters of the test
		int eqThingsPerService = Integer.parseInt(args[1]);
		
		int requests = Integer.parseInt(args[2]);
		
		//initial assumption is 1
		int requiredServicesPerRequest = Integer.parseInt(args[3]);
		
		ArrayList<String> totalServices = new ArrayList<>();
		
		for(int j = 4; j < args.length; j++){
			totalServices.add(args[4]);
		}
		
		double rate = (double)eqThingsPerService/(totalServices.size());
		
		System.out.println("Parameters of the test set");
		
		setTestDir();
		
		PrintWriter writer=null;
		FileWriter output = null;
		
		try{
			
			output = new FileWriter(file.getPath()+"/testInfo.txt", true);
			writer = new PrintWriter(output);
			writer.println("####################################");
			writer.println("####################################");
		
			int k = requests*requiredServicesPerRequest;
			writer.println("number of services requested: "+k);
			
			//generate things <-- assumption one service per thing
			int thingsCounter = eqThingsPerService*totalServices.size();
			//ThingID, Thing
			HashMap<String, Thing> thingsInfo = new HashMap<>(thingsCounter);
			
			HashMap<String, ThingsIdList> servNameThingsIdList = new HashMap<>();
			
			//create a number of things = to eqThingsPerService
			//for each service
			for(int i=0; i < totalServices.size(); i++){
				
				List<String> eqThings = new ArrayList<>();
				
				for(int s=0; s < eqThingsPerService; s++){
					int index = 0;
					Thing t = new Thing();
					
					index = getRandomIndex(0, battery.length);
					t.setBatteryLevel(battery[index]);
					
					Point point = new Point();
					index = getRandomIndex(0, coords.length);
					float lat = coords[index];
					index = getRandomIndex(0, coords.length);
					float lon = coords[index];
					point.setLatitude(lat);
					point.setLongitude(lon);
					t.setCoords(point);
					
					//assumption one service per thing
					HashMap<String, ServiceFeatures> services = new HashMap<>(1);
					
					ServiceFeatures servFeat = new ServiceFeatures();
					
					index = getRandomIndex(0, latency.length);
					servFeat.setLatency(latency[index]);
					index = getRandomIndex(0, energyCost.length);
					servFeat.setEnergyCost(energyCost[index]);
					
					services.put(totalServices.get(i), servFeat);
					
					String thingId = UUID.randomUUID().toString();
					thingsInfo.put(thingId, t);
					
					eqThings.add(thingId);
				}
				
				ThingsIdList thingsId = new ThingsIdList();
				
				thingsId.setEqThings(eqThings);
				
				servNameThingsIdList.put(totalServices.get(i), thingsId);
			}
			
			writer.println("####################################");
			writer.println("thingsInfo: "+thingsInfo);
			writer.println("####################################");
			writer.println("servNameThingsIdList: "+servNameThingsIdList);
			writer.println("####################################");
			
			List<Pair<String, Request>> requestList = new ArrayList<>();
			
			for(int r=0; r<requests; r++){
				
				int index = 0;

				String transId = UUID.randomUUID().toString();

				
				String opType = "queryContext";
				
				index = getRandomIndex(0, periods.length);
				double maxRateRequest  = periods[index];
				
				index = getRandomIndex(0, latency.length);
				double maxRespTime = latency[index];
				
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
					
					index = getRandomIndex(0, totalServices.size());
					String requiredService = totalServices.get(index);
					
					requiredServiceList.add(requiredService);
				}
				
				Request req = new Request();
				req.setOpType(opType);
				req.setLocationRequirementCircle(locReqCircle);
				req.setQosRequirements(qosReq);
				req.setRequiredServicesNameList(requiredServiceList);
				
				requestList.add(new Pair<String, Request>(transId, req));
			}

			writer.println("requestList: "+requestList);
			writer.println("####################################");
			
			ResultMerger allocationResult = new ResultMerger();
			
			ScheduledExecutorService scheduledExecutorService =
			        Executors.newScheduledThreadPool(1);

				
			Callable<Boolean> req = new RequestThread(qosCalculator,
					allocationResult,
					requestList,
					thingsInfo,
					servNameThingsIdList, 0.001,
					split);
			
			ScheduledFuture<Boolean> scheduledFuture =
				    scheduledExecutorService.schedule(req,
				    5,
				    TimeUnit.SECONDS);
			
			Runnable controlThread = new StopThread(scheduledFuture, scheduledExecutorService, allocationResult);
			scheduledExecutorService.schedule(controlThread, 6, TimeUnit.SECONDS);
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
	
	private static void setTestDir() {
		
		file = new File("/home/lorenzo/Desktop/RestClient/");
		
	}
	
	private int getRandomIndex(int min, int max){

		Random rand = new Random(System.currentTimeMillis());
		
		  // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
