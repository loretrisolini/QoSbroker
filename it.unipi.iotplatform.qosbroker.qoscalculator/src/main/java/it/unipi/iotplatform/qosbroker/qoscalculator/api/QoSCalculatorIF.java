package it.unipi.iotplatform.qosbroker.qoscalculator.api;



import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServicePeriodParams;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;

import java.util.HashMap;
import java.util.List;

import eu.neclab.iotplatform.iotbroker.commons.Pair;

public interface QoSCalculatorIF {

	public ReservationResults computeAllocation(int k, List<Pair<String, Request>> requests, 
												HashMap<String, ServicePeriodParams> servPeriodsMap,
												HashMap<String, Thing> thingsInfo,
												HashMap<String, ThingsIdList> servNameThingsIdList,
												//HashMap<String, TransIdList> matrixM,
												//Policy policy,
												double epsilon);
	
	public ReservationResults readReservation();
	
	public String getNextDevId(String transId, String service);
}
