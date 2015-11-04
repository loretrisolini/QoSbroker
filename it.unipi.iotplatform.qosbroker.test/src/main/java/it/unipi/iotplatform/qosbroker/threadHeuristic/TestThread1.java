package it.unipi.iotplatform.qosbroker.threadHeuristic;

import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ReservationResults;
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
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import eu.neclab.iotplatform.iotbroker.commons.Pair;

public class TestThread1 implements Runnable{

	private List<Pair<Long, Boolean>> arrivalTimeList = new ArrayList<>();
	private Long startTime;
	
	private final int[] eqThingsxServList = {2, 4, 6, 10};
	
	private final QoSCalculator qosCalculator = new QoSCalculator();
	private List<Pair<String, Request>> requestList;
	
	private HashMap<String, Thing> thingsInfo;
	private HashMap<String, Thing> thingsInfoBck;
	private HashMap<String, ThingsIdList> servNameThingsIdList;
	private HashMap<String, ThingsIdList> servNameThingsIdListBck;
	private double epsilon; 
	private Split split;
	
	private int eqThingsxServ;
	private int testCounter = 1;
	private int totalNumberOfServices;
	
	private boolean stopTest = false;
	
	private static AtomicInteger feasibleAllocations = new AtomicInteger(0);
	private ScheduledExecutorService scheduledExecutorService;
	
	private static int requestCounter = 0;
	private final Lock lock = new ReentrantLock();
	
	public TestThread1(
			List<Pair<String, Request>> requestList,
			HashMap<String, Thing> thingsInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList, double epsilon,
			ScheduledExecutorService scheduledExecutorService) {

		this.requestList = requestList;
		
		this.thingsInfo = thingsInfo;
		this.thingsInfoBck = thingsInfo;
		this.servNameThingsIdList = servNameThingsIdList;
		this.servNameThingsIdListBck = servNameThingsIdList;
		this.epsilon = epsilon;
		this.scheduledExecutorService = scheduledExecutorService;
		this.split = Split.SINGLE_SPLIT;
		
		eqThingsxServ = eqThingsxServList[eqThingsxServList.length-testCounter];
		totalNumberOfServices = thingsInfo.size()/eqThingsxServ;

		startTime = new Date().getTime();
	}


	@Override
    public void run(){
		
		if(this.scheduledExecutorService.isShutdown()) return;

		lock.lock();
		
		allocationTest(this.split);
		
		//stop
		if(stopTest && (eqThingsxServList.length-testCounter)==0 && this.split==Split.MULTI_SPLIT){
			
			System.out.println("STOP");
			this.scheduledExecutorService.shutdownNow();
			return;
		}
		
		//pass to multi-split
		if(stopTest && (eqThingsxServList.length-testCounter)==0 && this.split==Split.SINGLE_SPLIT){
			
			System.out.println();
			System.out.println("NOW MULTI-SPLIT");
			
			requestCounter = 0;
			stopTest = false;
			testCounter=1;
			
			eqThingsxServ = eqThingsxServList[eqThingsxServList.length-testCounter];
			startTime = new Date().getTime();
			arrivalTimeList.clear();
			feasibleAllocations.set(0);
			
			this.thingsInfo = this.thingsInfoBck;
			this.servNameThingsIdList = this.servNameThingsIdListBck;
			this.split = Split.MULTI_SPLIT;
		}
		
		//pass to another eqThingsxServ factor
		if(stopTest){
			requestCounter = 0;
			stopTest = false;
			testCounter++;
			
			eqThingsxServ = eqThingsxServList[eqThingsxServList.length-testCounter];
			startTime = new Date().getTime();
			arrivalTimeList.clear();
			feasibleAllocations.set(0);
			
			HashMap<String, Thing> newThingsInfo = new HashMap<String, Thing>();
			HashMap<String, ThingsIdList>  newServNameThingsIdList = new HashMap<String, ThingsIdList>();
			int newNumberOfThings = 0;
			for(Map.Entry<String, ThingsIdList> entry: servNameThingsIdList.entrySet()){
				
				newServNameThingsIdList.put(entry.getKey(), new ThingsIdList());
				
				//take the first "eqThingsxServ" things for each service
				for(int e = 0; e < eqThingsxServ; e++){
					
					newServNameThingsIdList.get(entry.getKey()).addEqThing(entry.getValue().getEqThings().get(e));
					
					Thing t = this.thingsInfo.get(entry.getValue().getEqThings().get(e));
					
					newThingsInfo.put(entry.getValue().getEqThings().get(e), t);
					newNumberOfThings++;
				}
			}
			
			if(newNumberOfThings == totalNumberOfServices*eqThingsxServ)
				System.out.println("# things OK");
			else{
				System.out.println("ERROR CREATION THINGS");
				this.scheduledExecutorService.shutdownNow();
				return;
			}

			this.thingsInfo = newThingsInfo;

			this.servNameThingsIdList = newServNameThingsIdList;
			
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
		System.out.println("EqThingsPerService: "+eqThingsxServ+" split: "+split);
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
			this.scheduledExecutorService.shutdownNow();
		}
		
		
		if(result.isFeas()){
			
			feasibleAllocations.getAndIncrement();
			
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
		
		File fileTest = new File("./TestClient");
		if(!fileTest.exists()){
			fileTest.mkdir();
		}
		
		PrintWriter writer=null;
		FileWriter output = null;
		
		try{
			output = new FileWriter(fileTest.getAbsoluteFile()+"/testResults"+split.name()+"_EqThings_"+eqThingsxServ+".txt", true);
			writer = new PrintWriter(output);
			writer.println("####################################");
			writer.println("####################################");
			
			writer.println("Final Results "+split.name()+" :");
			System.out.println("Final Results "+split.name()+" :");
			
			writer.println("feasible allocations: "+ feasibleAllocations);
			System.out.println("feasible allocations: "+ feasibleAllocations);
			writer.println("<Arrival Times, result>: "+ arrivalTimeList);
			System.out.println("<Arrival Times, result>: "+ arrivalTimeList);
			
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
