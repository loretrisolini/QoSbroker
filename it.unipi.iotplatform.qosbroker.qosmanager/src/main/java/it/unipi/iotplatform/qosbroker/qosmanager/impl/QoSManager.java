package it.unipi.iotplatform.qosbroker.qosmanager.impl;

import java.util.HashMap;
import java.util.List;

import it.unipi.iotplatform.qosbroker.qosmanager.api.QoSManagerIF;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceExecutionFeature;

public class QoSManager implements QoSManagerIF {

//	/**  Reference to the Negotiator engine */
//	private NegotiationInterface negotiator; 
	
//	private QoSCalculatorIF qosCalculator;
	
	@Override
	public String getTemplate() {

		return null;
	}
	
	@Override
	public void createAgreement(String offer){
		
		//TODO Parse Offer
		
		//TODO Get  Pair<TransactionID, <EquivalentThings, SeviceRequest>>
		
		//TODO Get old List of Pair<TransactionID, <EquivalentThings, SeviceRequest>>
		
		//TODO compute H hyperperiod
		
		//TODO create servExecFeature
		//TODO create mappingServThing
		
//		HashMap<Integer, List<ServiceExecutionFeature>> mappingServiceThing =
//				createMappingServiceThing();
		
//		heuristicScheduler = Executors.newSingleThreadExecutor();
//		Future<QoSRankList> future = 
//				heuristicScheduler.submit(new Heuristic(m_equivalents, m_assignments, 
//						m_requests, m_assuredrequests, m_thingservices, m_things, this.MapGw, 
//						serviceId, rank, requirement, caller.getGatewayId(), all, this.context));
//		
//			allocation_schema = future.get();
//		update_data(allocation_schema);
		
//		reservationResults = qosCalculator.
	}
	
//	public NegotiationInterface getNegotiator() {
//	return negotiator;
//}
//
//public void setNegotiator(NegotiationInterface negotiator) {
//	this.negotiator = negotiator;
//}
	
}
