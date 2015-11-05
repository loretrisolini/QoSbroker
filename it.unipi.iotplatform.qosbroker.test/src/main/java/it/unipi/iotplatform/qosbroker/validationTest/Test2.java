package it.unipi.iotplatform.qosbroker.validationTest;

import it.unipi.iotplatform.qosbroker.api.datamodel.LocationScopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSscopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.Split;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.threadHeuristic.TestThread2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;

public class Test2 {

	private final int REQ_PERIOD = 4;
	
	//seed
	//#reqs 
	//#requiresServ <-- assumptions 1 required service per request
	//#TotalNumber of services all different
	public void test(String[] args) {

		if(args.length < 5){
			System.out.println("Error num of params not correct");
			return;
		}
		
		File fileTest = new File("./TestClient");
		if(!fileTest.exists()){
			fileTest.mkdir();
		}
		
		PrintWriter writer=null;
		FileWriter output = null;
		
		Long seed;
		int requests;
		int requiredServicesPerRequest;
		int totalServices;
		int thingsNumber;
		
		try{
		
			output = new FileWriter(fileTest.getAbsolutePath()+"/testInfo.txt", true);
			writer = new PrintWriter(output);
			writer.println("####################################");
			writer.println("####################################");
			
			//read the seed
			if(args[0].contentEquals("null")){
				System.out.println("no seed read, creation of the seed");
				seed = System.currentTimeMillis();
			}
			else{
				seed = Long.valueOf(args[0]);
			}
			
			System.out.println("Seed: "+seed);
			writer.println("Seed: "+seed);
			
			requests = Integer.parseInt(args[1]);
			
			System.out.println("requests: "+requests);
			writer.println("requests: "+requests);
			
			//initial assumption is 1
			requiredServicesPerRequest = Integer.parseInt(args[2]);
			
			System.out.println("requiredServicesPerRequest: "+requiredServicesPerRequest);
			writer.println("requiredServicesPerRequest: "+requiredServicesPerRequest);
			
			totalServices = Integer.parseInt(args[3]);
			
			System.out.println("totalServices: "+totalServices);
			writer.println("totalServices: "+totalServices);
			
			thingsNumber = Integer.parseInt(args[4]);
			System.out.println("thingsNumber: "+thingsNumber);
			writer.println("thingsNumber: "+thingsNumber);
			
			System.out.println("Parameters of the test set");
			writer.println("Parameters of the test set");
			writer.println("####################################");
			writer.println("####################################");
			System.out.println("####################################");
			System.out.println("####################################");
		
			int k = requests*requiredServicesPerRequest;
			System.out.println("number of services requested: "+k);
			writer.println("number of services requested: "+k);
			

				
			ScheduledExecutorService scheduledExecutorService =
			        Executors.newScheduledThreadPool(1);
			
			Runnable run = new TestThread2(
					seed,
					requests,
					requiredServicesPerRequest, totalServices, thingsNumber,
					scheduledExecutorService);
	
			scheduledExecutorService.scheduleWithFixedDelay(run, 0, REQ_PERIOD, TimeUnit.SECONDS);
					

			
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
