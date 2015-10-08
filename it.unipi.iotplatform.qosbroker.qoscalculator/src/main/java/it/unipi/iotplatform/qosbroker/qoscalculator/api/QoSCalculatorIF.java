package it.unipi.iotplatform.qosbroker.qoscalculator.api;


import it.unipi.iotplatform.qosbroker.api.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;

import java.util.HashMap;
import java.util.List;

public interface QoSCalculatorIF {

	public ReservationResults computeAllocation();
}
