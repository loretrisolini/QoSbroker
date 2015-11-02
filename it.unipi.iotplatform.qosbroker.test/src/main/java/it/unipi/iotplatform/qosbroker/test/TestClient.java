package it.unipi.iotplatform.qosbroker.test;

import it.unipi.iotplatform.qosbroker.api.datamodel.LocationScopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSscopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.api.utils.Statistics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;

public class TestClient {

	private static final float[] coords = {0, 2, 4, 6, 8, 10};
	
	private static final double ttransf[]={3.0 / 1000, 3.5 / 1000, 4.0 / 1000, 4.5 / 1000, 5.0 / 1000, 5.5 / 1000, 6.0 / 1000, 6.5 / 1000, 7.0 / 1000, 7.5 / 1000, 8.0 / 1000, 8.5 / 1000, 9.0 / 1000, 9.5 / 1000, 10.0 / 1000}; //s
	private static final double pmcu = 3.6 * (10) / 3600; //mW/s
	private static final double prx = 3.6 * (18.8) / 3600; //mW/s
	private static final double ptx = 3.6 * (17.4) / 3600; //mW/s
	private static final double texe[]={1.0 / 1000, 1.5 / 1000, 1.8 / 1000, 2.0 / 1000, 2.5 / 1000}; //s
	
	private static final double per[]={10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0}; //s
	private static final double bat[]={50.710, 45.639, 40.568, 35.497, 30.426, 25.355}; //mJ/100000
	
	private static Long seed;
	private static Random generator;
	private static final AtomicInteger thingIdCounter = new AtomicInteger(0);
	private static final AtomicInteger transIdCounter = new AtomicInteger(0);
	
	//seed
	//#reqs 
	//#requiresServ <-- assumptions 1 required service per request
	//#TotalNumber of services all different
	public static void main(String[] args) {

		if(args.length < 4){
			System.out.println("Error num of params not correct");
			return;
		}
		
		File fileTest = new File("./TestClient");
		if(!fileTest.exists()){
			fileTest.mkdir();
		}
		
		PrintWriter writer=null;
		FileWriter output = null;
		
		try{
		
			output = new FileWriter(fileTest.getAbsolutePath()+"/testInfo.txt", true);
			writer = new PrintWriter(output);
			writer.println("####################################");
			writer.println("####################################");
			
			if(args[0].contentEquals("null")){
				System.out.println("no seed read, creation of the seed");
				seed = System.currentTimeMillis();
			}
			else{
				seed = Long.valueOf(args[1]);
			}
			
			System.out.println("Seed: "+seed);
			writer.println("Seed: "+seed);
			generator = new Random(seed);
			
			int eqThingsPerService = 10;
			
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
			
			
			System.out.println("Parameters of the test set");
			writer.println("Parameters of the test set");
			writer.println("####################################");
			writer.println("####################################");
			System.out.println("####################################");
			System.out.println("####################################");
		
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
					
					//assumption one service per thing
					HashMap<String, ServiceFeatures> services = new HashMap<>(1);
					
					ServiceFeatures servFeat = new ServiceFeatures();
					
					//latency = ttransf_1 + texe + ttransf_2
					index = getRandomIndex(ttransf.length);
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
			
//			System.out.println("####################################");
//			System.out.println("thingsInfo: "+thingsInfo);
//			System.out.println("####################################");
//			System.out.println("servNameThingsIdList: "+servNameThingsIdList);
//			System.out.println("####################################");
		
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
			writer.println("requestList: "+requestList);
			writer.println("####################################");
			
			ScheduledExecutorService scheduledExecutorService =
			        Executors.newScheduledThreadPool(1);
			
			Runnable reqSingle = new TestThread(
					requestList,
					thingsInfo,
					servNameThingsIdList, 0.001,
					scheduledExecutorService);
			
			scheduledExecutorService.scheduleWithFixedDelay(reqSingle, 0, 5, TimeUnit.SECONDS);
			
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
	
	private static int getRandomIndex(int max){

		int i = generator.nextInt(max);
		
		return i;
	}
}
