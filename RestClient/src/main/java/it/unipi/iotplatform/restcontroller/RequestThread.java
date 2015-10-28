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

	private AtomicInteger howManyFeasible = new AtomicInteger(0);
	private List<Long> arrivalTimeList = new ArrayList<>();
	private Long startTime;
	
	private final QoSCalculator qosCalculator = new QoSCalculator();;
	private List<Pair<String, Request>> requestList;
	private HashMap<String, Thing> thingsInfo;
	private HashMap<String, ThingsIdList> servNameThingsIdList;
	private double epsilon; 
	private Split split;
	
	private ScheduledExecutorService scheduledExecutorService;
	
	private static boolean allocationFeasible = true;
	private static int requestCounter = 0;
	
	public RequestThread(
			Long startTime,
			List<Pair<String, Request>> requestList,
			HashMap<String, Thing> thingsInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList, double epsilon,
			ScheduledExecutorService scheduledExecutorService) {

		this.startTime = startTime;
		this.requestList = requestList;
		this.thingsInfo = thingsInfo;
		this.servNameThingsIdList = servNameThingsIdList;
		this.epsilon = epsilon;
		this.scheduledExecutorService = scheduledExecutorService;
		this.split = Split.SINGLE_SPLIT;

	}


	@Override
    public void run(){
		
		allocationTest(this.split);
		
		if(!allocationFeasible && this.split == Split.SINGLE_SPLIT){
			
			
			requestCounter = 0;
			
			synchronized(this){
				arrivalTimeList.clear();
				howManyFeasible = new AtomicInteger(0);
			}
				
			startTime = new Date().getTime();
			
			this.split = Split.MULTI_SPLIT;
			
			allocationFeasible = true;
		}

		if(!allocationFeasible && this.split == Split.MULTI_SPLIT)
			this.scheduledExecutorService.shutdown();
		
    }
	

	public void allocationTest(Split split){
		
		requestCounter++;
		
		if(requestCounter == requestList.size()){
			System.out.println("Requests terminated");
			
			printResults();
			
			allocationFeasible = false;
		}
		
		System.out.println("Start Request number: "+requestCounter);
		
		synchronized(arrivalTimeList){
			
			Long arrivalTime = (new Date().getTime()) - startTime;
			
			arrivalTimeList.add(arrivalTime);
		}
		
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
			howManyFeasible.getAndIncrement();

		}
		else{
			
			printResults();

			allocationFeasible = false;
		}

	}
	
	public void printResults(){
		PrintWriter writer=null;
		FileWriter output = null;
		
		try{
			output = new FileWriter("/home/lorenzo/Desktop/RestClient/testResults"+split.name()+".txt", true);
			writer = new PrintWriter(output);
			writer.println("####################################");
			writer.println("####################################");
			
			writer.println("Final Results "+split.name()+" :");
			System.out.println("Final Results "+split.name()+" :");
			
			synchronized(this){
				writer.println("Feasible allocations: "+ howManyFeasible);
				System.out.println("Feasible allocations: "+ howManyFeasible);
				
				writer.println("Arrival Times: "+ arrivalTimeList);
				System.out.println("Arrival Times: "+ arrivalTimeList);
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
