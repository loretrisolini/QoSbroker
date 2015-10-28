package it.unipi.iotplatform.restcontroller;

import it.unipi.iotplatform.qosbroker.api.datamodel.LocationScopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSscopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.Split;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;

public class RestClient {

	private static final Double[] latency = {0.10, 0.20, 0.40, 0.60, 0.80, 1.0};
	private static final Double[] energyCost = {0.10, 0.20, 0.40, 0.45};
	private static final int[] periods = {10, 20, 40, 60, 80, 100, 120};
	private static final Double[] battery = {98.0, 99.0, 100.0};
	private static final float[] coords = {0, 2, 4, 6, 8, 10};
	
	private static final AtomicInteger thingIdCounter = new AtomicInteger(0);
	private static final AtomicInteger transIdCounter = new AtomicInteger(0);
	
	private static File file;
	
	//#EqThings x Serv,
	//#reqs and #requiresServ <-- assumptions 1 required service per request
	//#TotalNumber of services[temperature, humidity, CO2, presence]
	public static void main(String[] args) {

		if(args.length < 4){
			System.out.println("Error num of params not correct");
			return;
		}
		
		setTestDir();
		
		PrintWriter writer=null;
		FileWriter output = null;
		
		try{
		
			output = new FileWriter(file.getPath()+"/testInfo.txt", true);
			writer = new PrintWriter(output);
			writer.println("####################################");
			writer.println("####################################");
			
			//read parameters of the test
			int eqThingsPerService = Integer.parseInt(args[0]);
			
			System.out.println("eqThingsPerService: "+eqThingsPerService);
			writer.println("eqThingsPerService: "+eqThingsPerService);
			
			int requests = Integer.parseInt(args[1]);
			
			System.out.println("requests: "+requests);
			writer.println("requests: "+requests);
			
			//initial assumption is 1
			int requiredServicesPerRequest = Integer.parseInt(args[2]);
			
			System.out.println("requiredServicesPerRequest: "+requiredServicesPerRequest);
			writer.println("requiredServicesPerRequest: "+requiredServicesPerRequest);
			
			int totalServices = Integer.parseInt(args[3]);
			
			System.out.println("totalServices: "+totalServices);
			writer.println("totalServices: "+totalServices);
			
			double rate = (double)eqThingsPerService/totalServices;
			
			System.out.println("Parameters of the test set");
			writer.println("Parameters of the test set");
			writer.println("####################################");
			writer.println("####################################");

		
			int k = requests*requiredServicesPerRequest;
			System.out.println("number of services requested: "+k);
			writer.println("number of services requested: "+k);
			
			//generate things <-- assumption one service per thing
			int thingsCounter = eqThingsPerService*totalServices;
			
			System.out.println("how many things to generate: "+thingsCounter);
			writer.println("how many things to generate: "+thingsCounter);
			
			//ThingID, Thing
			HashMap<String, Thing> thingsInfo = new HashMap<>(thingsCounter);
			
			HashMap<String, ThingsIdList> servNameThingsIdList = new HashMap<>();
			
			//create a number of things = to eqThingsPerService
			//for each service
			for(int i=0; i < totalServices; i++){
				
				List<String> eqThings = new ArrayList<>();
				
				for(int s=0; s < eqThingsPerService; s++){
					int index = 0;
					Thing t = new Thing();
					
					index = getRandomIndex(battery.length);
					t.setBatteryLevel(battery[index]);
					
					Point point = new Point();
					index = getRandomIndex(coords.length);
					float lat = coords[index];
					index = getRandomIndex(coords.length);
					float lon = coords[index];
					point.setLatitude(lat);
					point.setLongitude(lon);
					t.setCoords(point);
					
					//assumption one service per thing
					HashMap<String, ServiceFeatures> services = new HashMap<>(1);
					
					ServiceFeatures servFeat = new ServiceFeatures();
					
					index = getRandomIndex(latency.length);
					servFeat.setLatency(latency[index]);
					index = getRandomIndex(energyCost.length);
					servFeat.setEnergyCost(energyCost[index]);
					
					String service = String.valueOf(i);
					
					//one service per thing
					services.put(service, servFeat);
					t.setServicesList(services);
					
					String thingId = String.valueOf(thingIdCounter.getAndIncrement());
					thingsInfo.put(thingId, t);
					
					eqThings.add(thingId);
				}
				
				ThingsIdList thingsId = new ThingsIdList();
				
				thingsId.setEqThings(eqThings);
				
				String service = String.valueOf(i);
				servNameThingsIdList.put(service, thingsId);
			}
			
			System.out.println("####################################");
			System.out.println("thingsInfo: "+thingsInfo);
			System.out.println("####################################");
			System.out.println("servNameThingsIdList: "+servNameThingsIdList);
			System.out.println("####################################");
			
			writer.println("####################################");
			writer.println("thingsInfo: "+thingsInfo);
			writer.println("####################################");
			writer.println("servNameThingsIdList: "+servNameThingsIdList);
			writer.println("####################################");
			
			List<Pair<String, Request>> requestList = new ArrayList<>();
			
			for(int r=0; r<requests; r++){
				
				int index = 0;

				String transId = String.valueOf(transIdCounter.getAndIncrement());

				
				String opType = "queryContext";
				
				index = getRandomIndex(periods.length);
				double maxRateRequest  = periods[index];
				
				index = getRandomIndex(latency.length);
				double maxRespTime = 2; //latency[index];
				
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

			System.out.println("requestList: "+requestList);
			System.out.println("####################################");
			writer.println("requestList: "+requestList);
			writer.println("####################################");
			
			ScheduledExecutorService scheduledExecutorService =
			        Executors.newScheduledThreadPool(1);
			
			System.out.println("Test single Split");
			
			Long startTime = new Date().getTime();
			
			Runnable reqSingle = new RequestThread(
					startTime,
					requestList,
					thingsInfo,
					servNameThingsIdList, 0.001,
					scheduledExecutorService);
			
			scheduledExecutorService.scheduleWithFixedDelay(reqSingle, 0, 3, TimeUnit.SECONDS);
			
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
	
	private static int getRandomIndex(int max){

		SecureRandom generator = new SecureRandom();
		int i = generator.nextInt(max);
		
		return i;
	}
}
