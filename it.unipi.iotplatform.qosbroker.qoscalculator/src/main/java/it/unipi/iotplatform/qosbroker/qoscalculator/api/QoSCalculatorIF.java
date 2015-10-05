package it.unipi.iotplatform.qosbroker.qoscalculator.api;

import it.unipi.iotplatform.qosbroker.qoscalculator.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.RequestResult;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.Service;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceAssignments;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.Thing;

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
