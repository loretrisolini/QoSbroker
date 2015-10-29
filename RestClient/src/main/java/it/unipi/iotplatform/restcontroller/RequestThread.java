package it.unipi.iotplatform.restcontroller;

import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServicePeriodParams;
import it.unipi.iotplatform.qosbroker.api.datamodel.Split;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.qoscalculator.impl.QoSCalculator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import eu.neclab.iotplatform.iotbroker.commons.Pair;

public class RequestThread implements Runnable{

	private List<Pair<Long, Boolean>> arrivalTimeList = new ArrayList<>();
	private Long startTime;
	
	private final int[] eqThingsxServList = {2, 4, 6, 10};
	
	private final QoSCalculator qosCalculator = new QoSCalculator();;
	private List<Pair<String, Request>> requestList;
	private HashMap<String, Thing> thingsInfo;
	private HashMap<String, ThingsIdList> servNameThingsIdList;
	private double epsilon; 
	private Split split;
	
	private int eqThingsxServ;
	private int testCounter = 1;
	private int totalNumberOfServices;
	
	private boolean stopTest = false;
	
	private static AtomicInteger feasibleAllocations = new AtomicInteger(0);
	private ScheduledExecutorService scheduledExecutorService;
	
	private static int requestCounter = 0;
	
	public RequestThread(
			List<Pair<String, Request>> requestList,
			HashMap<String, Thing> thingsInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList, double epsilon,
			ScheduledExecutorService scheduledExecutorService, Split split) {

		this.requestList = requestList;
		this.thingsInfo = thingsInfo;
		this.servNameThingsIdList = servNameThingsIdList;
		this.epsilon = epsilon;
		this.scheduledExecutorService = scheduledExecutorService;
		this.split = split;
		
		eqThingsxServ = eqThingsxServList[eqThingsxServList.length-testCounter];
		totalNumberOfServices = thingsInfo.size()/eqThingsxServ;

		startTime = new Date().getTime();
	}


	@Override
    public void run(){
		
		allocationTest(this.split);
		
		if(stopTest && (eqThingsxServList.length-testCounter)==0){
			this.scheduledExecutorService.shutdown();
			return;
		}
		
		if(stopTest){
			requestCounter = 0;
			stopTest = false;
			testCounter++;
			
			eqThingsxServ = eqThingsxServList[eqThingsxServList.length-testCounter];
			startTime = new Date().getTime();
			arrivalTimeList.clear();
			feasibleAllocations.set(0);
			
			int newNumberOfThings = totalNumberOfServices*eqThingsxServ;
			HashMap<String, Thing> newThingsInfo = new HashMap<String, Thing>();
			int howManyThingsTaken = 0;
			for(Map.Entry<String, Thing> entryThing : thingsInfo.entrySet()){
				
				newThingsInfo.put(entryThing.getKey(), entryThing.getValue());
				howManyThingsTaken++;
				if(howManyThingsTaken == newNumberOfThings){
					break;
				}
			}
			this.thingsInfo = newThingsInfo;
			
			HashMap<String, ThingsIdList>  newServNameThingsIdList = new HashMap<String, ThingsIdList>();
			for(Map.Entry<String, ThingsIdList> entryServ : servNameThingsIdList.entrySet()){
				
				ThingsIdList newThingsIdList = new ThingsIdList();
				for(int i=0; i<eqThingsxServ; i++){
					
					newThingsIdList.addEqThing(entryServ.getValue().getEqThings().get(i));
				}
				
				newServNameThingsIdList.put(entryServ.getKey(), newThingsIdList);
			}
			this.servNameThingsIdList = newServNameThingsIdList;
			
		}
		
    }
	

	public void allocationTest(Split split){
		
		requestCounter++;
		
		if(requestCounter == requestList.size()){
			System.out.println("Requests terminated");
			
			printResults();
			
			stopTest = true;
		}
		
		System.out.println("Start Request number: "+requestCounter);
		
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
		
		synchronized(qosCalculator){
			result = qosCalculator.computeAllocation(k, requests, servPeriodsMap, thingsInfo, servNameThingsIdList, epsilon, split);

		}
			
		
		if(result.isFeas()){
			
			feasibleAllocations.getAndIncrement();
			
			synchronized(arrivalTimeList){
				
				arrivalTimeList.add(new Pair<Long,Boolean>(ArrivalTime, true));
			}

		}
		else{
			
			synchronized(arrivalTimeList){
				
				arrivalTimeList.add(new Pair<Long,Boolean>(ArrivalTime, false));
			}
			
			printResults();

			stopTest = true;
		}

	}
	
	public void printResults(){
		PrintWriter writer=null;
		FileWriter output = null;
		
		try{
			output = new FileWriter("/home/lorenzo/Desktop/RestClient/testResults"+split.name()+"_EqThings_"+eqThingsxServ+".txt", true);
			writer = new PrintWriter(output);
			writer.println("####################################");
			writer.println("####################################");
			
			writer.println("Final Results "+split.name()+" :");
			System.out.println("Final Results "+split.name()+" :");
			
			synchronized(this){
				
				writer.println("feasible allocations: "+ feasibleAllocations);
				System.out.println("feasible allocations: "+ feasibleAllocations);
				writer.println("<Arrival Times, result>: "+ arrivalTimeList);
				System.out.println("<Arrival Times, result>: "+ arrivalTimeList);
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
	

}
