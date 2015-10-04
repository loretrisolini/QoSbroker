package it.unipi.iotplatform.qosbroker.qoscalculator.api;

import it.unipi.iotplatform.qosbroker.qoscalculator.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.RequestResult;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceAssignments;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.Thing;

import java.util.HashMap;
import java.util.List;

public interface QoSCalculatorIF {

	public ReservationResults computeAllocation(
			HashMap<String, List<ServiceAssignments>> mappingServEqThings,
			HashMap<String, Integer> coefficientMap,
			HashMap<Integer, Thing> thingsMap,
			HashMap<String, RequestResult> requestResultsMap,
			double epsilon);
}
