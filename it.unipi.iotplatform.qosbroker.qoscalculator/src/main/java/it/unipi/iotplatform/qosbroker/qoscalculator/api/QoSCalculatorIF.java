package it.unipi.iotplatform.qosbroker.qoscalculator.api;


import it.unipi.iotplatform.qosbroker.api.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceAssignments;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;

import java.util.HashMap;
import java.util.List;

public interface QoSCalculatorIF {

	public ReservationResults computeAllocation(
			int k,
			HashMap<String, HashMap<Integer, String>> totalRequestedServicesMap,
			HashMap<Integer, Thing> thingsMap,
			HashMap<String, List<ServiceAssignments>> mappingServEqThings,
			HashMap<String, Integer> coefficientMap,
			double epsilon);
}
