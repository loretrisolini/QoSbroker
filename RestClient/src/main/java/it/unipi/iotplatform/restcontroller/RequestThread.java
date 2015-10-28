package it.unipi.iotplatform.restcontroller;

import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServicePeriodParams;
import it.unipi.iotplatform.qosbroker.api.datamodel.Split;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.qoscalculator.impl.QoSCalculator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import eu.neclab.iotplatform.iotbroker.commons.Pair;

public class RequestThread implements Callable<Boolean>{

	private final QoSCalculator qosCalculator;
	private ResultMerger merger;
	
	private List<Pair<String, Request>> requestList;
	private HashMap<String, Thing> thingsInfo;
	private HashMap<String, ThingsIdList> servNameThingsIdList;
	private double epsilon;
	private Split split; 
	
	private static int requestCounter = 0;
	
	public RequestThread(QoSCalculator qosCalculator,
			ResultMerger merger,
			List<Pair<String, Request>> requestList,
			HashMap<String, Thing> thingsInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList, double epsilon,
			Split split) {

		this.qosCalculator = qosCalculator;
		this.merger = merger;
		this.requestList = requestList;
		this.thingsInfo = thingsInfo;
		this.servNameThingsIdList = servNameThingsIdList;
		this.epsilon = epsilon;
		this.split = split;
	}


	
	@Override
    public Boolean call(){
		
		
		System.out.println("Start Request number: "+requestCounter);
		synchronized(merger){
			merger.setArrivalTime(new Date());
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
		
		requestCounter++;
		
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
		
		ReservationResults result = qosCalculator.computeAllocation(k, requests, servPeriodsMap, thingsInfo, servNameThingsIdList, epsilon, split);

		synchronized(merger){
			if(result.isFeas()){
				merger.setResult();
				return true;
			}
			else{
				return false;

			}
		}

    }
	

}
