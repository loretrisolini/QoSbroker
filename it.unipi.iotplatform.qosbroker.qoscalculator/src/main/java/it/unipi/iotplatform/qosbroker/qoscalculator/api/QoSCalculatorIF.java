package it.unipi.iotplatform.qosbroker.qoscalculator.api;

import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.RequestResult;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceAssignments;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceExecutionFeature;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.Thing;

import java.util.HashMap;
import java.util.List;

import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistration;

public interface QoSCalculatorIF {

	public List<ContextRegistration> computeAllocation(
			List<ServiceAssignments> mappingServEqThings,
			HashMap<String, Integer> coefficientMap,
			HashMap<Integer, Thing> thingsMap,
			HashMap<String, RequestResult> requestResultsMap);
}
