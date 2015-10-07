package it.unipi.iotplatform.qosbroker.qosmanager.impl;



import it.unipi.iotplatform.qosbroker.api.datamodel.RequestResult;
import it.unipi.iotplatform.qosbroker.api.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceAssignments;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceExecutionFeature;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingIdThingServiceIdPair;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingService;
import it.unipi.iotplatform.qosbroker.qoscalculator.api.QoSCalculatorIF;
import it.unipi.iotplatform.qosbroker.qosmanager.api.QoSManagerIF;

import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import eu.neclab.iotplatform.iotbroker.commons.interfaces.BigDataRepository;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistration;

public class QoSManager implements QoSManagerIF {

//	/**  Reference to the Negotiator engine */
//	private NegotiationInterface negotiator; 
	
	private QoSCalculatorIF qosCalculator;
	
	private BigDataRepository bigDataRepository;
	
	
	@Override
	public String getTemplate() {

		try{
			FileInputStream is = new FileInputStream("/home/lorenzo/Downloads/FIWARE-WORK/git/QoSbroker/qosbrokerconfig/schemas/wsag4j/fiware-template.xml");

		    DocumentBuilderFactory dFactory= DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder= dFactory.newDocumentBuilder();
		    Document doc= dBuilder.parse(is);
		    try {
		        StringWriter sw = new StringWriter();
		        TransformerFactory tf = TransformerFactory.newInstance();
		        Transformer transformer = tf.newTransformer();
		        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

		        transformer.transform(new DOMSource(doc), new StreamResult(sw));
		        String s=sw.toString();
		     
		     	return s;
		     	
		    } catch (Exception ex) {
		        throw new RuntimeException("Error converting to String", ex);
		    }

		}
		catch(Exception e){
			throw new RuntimeException("Error converting to String", e);
		}
	}
	
	@Override
	public void createAgreement(String offer, RequestResult reqResult){
		
		//TODO Parse Offer
		
		//Map<transId, RequestResults>
		HashMap<String, RequestResult> requestResultsMap = new HashMap<>();
		
		//TODO Get old List of RequestResults
		
		requestResultsMap.put(reqResult.getRequest().getTransactionId(), reqResult);
		
		//Map<transId ,List<ServId, Map<tId ,<tsId,f_ij,u_ij>>>
		HashMap<String, List<ServiceAssignments>> mappingServEqThings = new HashMap<>();
		
		//Map<transId, h/p_j> to compute number of things
		//to which assign a service
		HashMap<String,Integer> coefficientMap = new HashMap<>();
		
		//Map<transId, p_j>
		HashMap<String, Double> periodsMap = new HashMap<>();
		
		//List<p_j>
		ArrayList<Double> periods = new ArrayList<>();
		
		//iterate over the list of RequestResults objects
		//to build the list of periods
		for(RequestResult requestResult: requestResultsMap.values()){
			
			Double period = requestResult.getRequest().getQosRequirements().getMaxRateRequest();
			
			periodsMap.put(requestResult.getRequest().getTransactionId(), period);
			periods.add(period);
		}
		
		//h hyperperiod computed as least common multiple
		//of the list of periods
		double hyperPeriod = lcms(periods);
		
		//Map that contains all the thingsMaps for all
		//service requests
		HashMap<Integer, Thing> totalThingsMap = new HashMap<>();
		
		//Map that contains all the service requested
		//in a Request identify by a transId
		//Map<transId, Map<servId,servName>>
		HashMap<String, HashMap<Integer, String>> totalRequestedServicesMap = new HashMap<>();
		
		//counter of services requests
		int k=0;
		
		//iterate over the list of RequestResult to build the
		//MappingServEqThings
		for(RequestResult requestResult: requestResultsMap.values()){
			
			String transactionId = requestResult.getRequest().getTransactionId();
			Double period = periodsMap.get(transactionId);
			
			//compute the coefficient h/p_j
			Double coeff = hyperPeriod/period;
			coefficientMap.put(transactionId, coeff.intValue());
			
			//get the map of service requested for that
			//RequestResult object
			HashMap<Integer, String> requestedServicesMap = requestResult.getRequest().getRequestedServicesMap();
			
			//container Map of all service request identified by a transId and a servId
			totalRequestedServicesMap.put(transactionId, requestedServicesMap);
			
			//List that contains all the equivalent things for the 
			//requested service with servId
			List<ServiceAssignments> servAssignmentsList = new ArrayList<>();
			
			//iterate on the list of service identify by the services ids
			//for that request identify by the transactionId
			for(Map.Entry<Integer, String> serviceEntry : requestedServicesMap.entrySet()){
				
				//increment service request counter
				k++;
				
				//Map<tId, ServiceExecFeat>
				HashMap<Integer, ServiceExecutionFeature> servExecFeatMap = new HashMap<>();
				
				//Map<thingId, Thing> for that request
				HashMap<Integer, Thing> thingsMap = 
						requestResult.getEquivalentThingsMappings().getThingsMap();

				//container Map of all equivalent things for all requests
				totalThingsMap.putAll(thingsMap);
				
				//list of pairs ThingId_ThingServiceId for that serviceId
				//in that list of services in that request with
				//id transactionId
				List<ThingIdThingServiceIdPair> equivalentThingsIdThingServicesId = 
						requestResult.getEquivalentThingsMappings().getEqThingsListPerService()
						.get(serviceEntry.getKey()).getEquivalentThingIdThingServiceIdList();
				
				//compute the serviceExecutionFeature for each service id for that transactionId
				for(ThingIdThingServiceIdPair thingsIdThingServiceId: equivalentThingsIdThingServicesId){
					
					Integer thingId = thingsIdThingServiceId.getThingId();
					Integer thingServiceId = thingsIdThingServiceId.getThingServiceId();
					
					Thing t = thingsMap.get(thingId);
					ThingService ts = t.getThingServices().get(thingServiceId);
					
					Double batteryLev = t.getBatteryLevel();
					Double energyCost = ts.getThingServFeatures().getEnergyCost();
					Double latency = ts.getThingServFeatures().getLatency();
					
					Double normalizedEnergyCost = hyperPeriod/period * energyCost/batteryLev / 100;
					Double utilization = latency/period;
					
					ServiceExecutionFeature servExecFeat = new ServiceExecutionFeature();
					
					servExecFeat.setThingServiceId(thingServiceId);
					servExecFeat.setNormalizedEnergyCost(normalizedEnergyCost);
					servExecFeat.setUtilization(utilization);
					
					ArrayList<Double> priority = new ArrayList<>();
					priority.add(normalizedEnergyCost);
					priority.add(utilization);
					priority.add(new Random().nextDouble());
					
					//Map<thingId, <thingServiceId, f_ij, u_ij, priority[]>>
					servExecFeatMap.put(thingId, servExecFeat);
				}
				
				//Set the object that represents the list of things that
				//can satisfy a requested service identified by servId
				ServiceAssignments servAssigments = new ServiceAssignments();
				servAssigments.setServId(serviceEntry.getKey());
				servAssigments.setThingServiceExecFeatureMap(servExecFeatMap);
				
				//List<<servId, Map<thingId, ServiceExecutionFeature>>>
				//ServiceExecutionFeature = <thingServiceId, f_ij, u_ij, priority[]>
				servAssignmentsList.add(servAssigments);
			}
			
			//Map<transId, List<ServiceAssignment>>
			//ServiceAssignments = <servId, Map<thingId, <thingServiceId, f_ij, u_ij, priority[]>>
			mappingServEqThings.put(transactionId, servAssignmentsList);
		}
		
		//compute reservation results, given the total number of service requests k, 
		//the Map<transId, Map<servId, serviceName>>, the Map<thingId, Thing>,
		//Map<transId, List<ServiceAssignments>>, Map<transId, h/p_j>, epsilon
		ReservationResults allocationResult = 
				qosCalculator.computeAllocation(k, totalRequestedServicesMap, totalThingsMap, mappingServEqThings, 
												coefficientMap, Double.valueOf(0.001));
		
		List<ContextRegistration> ngsiAllocationSchema = allocationResult.getAllocationSchema();
		
		//TODO heuristic algorithm
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
		
		//TODO store new request Result if allocation feasible
		
		//TODO store new allocation schemas if allocation is feasible
	}
	
//	public NegotiationInterface getNegotiator() {
//	return negotiator;
//}
//
//public void setNegotiator(NegotiationInterface negotiator) {
//	this.negotiator = negotiator;
//}
	
	private long lcms(ArrayList<Double> periods){
	    long result = (long) Math.ceil(periods.get(0));
	    for(int i = 1; i < periods.size(); i++) result = lcm(result, (long) Math.ceil(periods.get(i)));
	    return result;
	}
	
	private long gcd(long a, long b){
	    while (b > 0)
	    {
	        long temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	}

	private long lcm(long a, long b){
	    return a * (b / gcd(a, b));
	}

	public BigDataRepository getBigDataRepository() {
		return bigDataRepository;
	}

	public void setBigDataRepository(BigDataRepository bigDataRepository) {
		this.bigDataRepository = bigDataRepository;
	}

	public QoSCalculatorIF getQosCalculator() {
		return qosCalculator;
	}

	public void setQosCalculator(QoSCalculatorIF qosCalculator) {
		this.qosCalculator = qosCalculator;
	}

//	public QoSCalculatorIF getQosCalculator() {
//		return qosCalculator;
//	}
//
//	public void setQosCalculator(QoSCalculatorIF qosCalculator) {
//		this.qosCalculator = qosCalculator;
//	}
}
