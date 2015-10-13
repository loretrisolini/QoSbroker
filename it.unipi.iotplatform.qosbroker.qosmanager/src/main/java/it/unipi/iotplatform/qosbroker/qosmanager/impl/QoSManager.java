package it.unipi.iotplatform.qosbroker.qosmanager.impl;

import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSConsts;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServicePeriodParams;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.TransIdList;
import it.unipi.iotplatform.qosbroker.couchdb.api.QoSBigDataRepository;
import it.unipi.iotplatform.qosbroker.qoscalculator.api.QoSCalculatorIF;
import it.unipi.iotplatform.qosbroker.qosmanager.api.QoSManagerIF;

import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONObject;
import org.w3c.dom.Document;

import eu.neclab.iotplatform.iotbroker.commons.Pair;

public class QoSManager implements QoSManagerIF {

//	/**  Reference to the Negotiator engine */
//	private NegotiationInterface negotiator; 
	
	private QoSCalculatorIF qosCalculator;
	
	private QoSBigDataRepository bigDataRepository;
	
	
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
	public Boolean createAgreement(String offer, String transactionId, Request request, HashMap<String, TransIdList> thingTransactionsMap){
		
		//TODO Parse Offer
		
		//counter for the number of service requests
		int k=0;
		
		//read all old requests from requests DB
		List<Pair<String, JSONObject>> requestsJsonList = bigDataRepository.readData(null, QoSConsts.REQUESTS_DB);
		if(requestsJsonList == null){
			return false;
		}
		
		//convert JsonObj requests in request object
		//and put it in List<transId, Request>
		//it will be the list of service requests
		//given in input to the heuristic
		List<Pair<String, Request>> requestsList = new ArrayList<>();
		
		//it is used to compute hyperperiod h
		ArrayList<Double> periodsList = new ArrayList<>();
		
		//Map<transactionId, <p_j, h/p_j>>
		HashMap<String, ServicePeriodParams> servPeriodParamsMap = new HashMap<>();
		
		Double p_j;
		
		//check if there are old requests already allocated
		if(!requestsJsonList.isEmpty()){
			for(Pair<String, JSONObject> reqPair: requestsJsonList){
				String transId = reqPair.getLeft();
				Request req = Request.fromJsonToJaxb(reqPair.getRight(), new Request(), Request.class);
				
				//take the number of services requested for
				//the request in this transaction
				k+=req.getRequiredServicesNameList().size();
				
				//take the period p_j equal to maxRateRequest
				//in QoSrequirements of the request identified by transId
				p_j = request.getQosRequirements().getMaxRateRequest();
				periodsList.add(p_j);
				
				//set p_j in servPeriodParamsMap
				ServicePeriodParams servPeriodParams = new ServicePeriodParams();
				servPeriodParams.setPeriod(p_j);
				servPeriodParamsMap.put(transId, servPeriodParams);
				
				requestsList.add(new Pair<String, Request>(transId, req));
			}
		}
		
		//add the new request object
		requestsList.add(new Pair<String, Request>(transactionId, request));
		
		k+=request.getRequiredServicesNameList().size();
		p_j = request.getQosRequirements().getMaxRateRequest();
		periodsList.add(p_j);
		
		ServicePeriodParams servPeriodParams = new ServicePeriodParams();
		servPeriodParams.setPeriod(p_j);
		servPeriodParamsMap.put(transactionId, servPeriodParams);
		
		//compute hyperiod h
		Long h = ServicePeriodParams.getHyperperiod(periodsList);
		
		Double coeff;
		//complete to fill Map<transactionId, <p_j, h/p_j>> with h/p_j
		for(Map.Entry<String, ServicePeriodParams> entry: servPeriodParamsMap.entrySet()){
			p_j = entry.getValue().getPeriod();
			coeff = h/p_j;
			entry.getValue().setNj(coeff.intValue());
		}
		
		//read from DBs the Map<DevId, Thing> and the Map<reqServName, List<DevId>>
		//Map<DevId, List<transId>>
		List<Pair<String, JSONObject>> eqThingInfoJson = bigDataRepository.readData(null, QoSConsts.THINGS_INFO_DB);
		List<Pair<String, JSONObject>> servNameThingsIdListJson = bigDataRepository.readData(null, QoSConsts.SERV_EQ_THINGS_DB);
		List<Pair<String, JSONObject>> thingTransactionsJson = bigDataRepository.readData(null, QoSConsts.REQUIREMENTS_DB);
		if(eqThingInfoJson == null || eqThingInfoJson.isEmpty() || 
				servNameThingsIdListJson == null || servNameThingsIdListJson.isEmpty()){
			return false;
		}
		
		HashMap<String, Thing> eqThingInfo = Thing.fromDbFormatToJavaFormat(eqThingInfoJson, Thing.class);
		if(eqThingInfo == null){
			return false;
		}
		
		HashMap<String, ThingsIdList> servNameThingsIdList = 
				ThingsIdList.fromDbFormatToJavaFormat(servNameThingsIdListJson, ThingsIdList.class);
		if(servNameThingsIdList == null){
			return false;
		}

		HashMap<String, TransIdList> matrixM = 
				TransIdList.fromDbFormatToJavaFormat(thingTransactionsJson, TransIdList.class);
		if(matrixM == null){
			return false;
		}
		
		//fuse the matrixM read from the DB
		//and new one computed with the ServiceAgreement operation
		if(!matrixM.isEmpty()){
			for(Map.Entry<String, TransIdList> entry: thingTransactionsMap.entrySet()){
				String devId = entry.getKey();
				
				if(matrixM.get(devId) != null){
					matrixM.get(devId).getTransIdList().addAll(entry.getValue().getTransIdList());
				}
				else{
					matrixM.put(devId, entry.getValue());
				}
			}
		}
		else{
			//case in which matrixM is empty
			//so add directly all the elements
			matrixM.putAll(thingTransactionsMap);
		}
		
		//execute allocation algorithm
		ReservationResults result = qosCalculator.computeAllocation(k, requestsList, servPeriodParamsMap, 
																		eqThingInfo, servNameThingsIdList, matrixM, 0.001);
		
		
		//TODO store new request Result if allocation feasible
		
		//TODO store new allocation schemas if allocation is feasible
		
		return true;
	}

	public QoSCalculatorIF getQosCalculator() {
		return qosCalculator;
	}

	public void setQosCalculator(QoSCalculatorIF qosCalculator) {
		this.qosCalculator = qosCalculator;
	}

	public QoSBigDataRepository getBigDataRepository() {
		return bigDataRepository;
	}

	public void setBigDataRepository(QoSBigDataRepository bigDataRepository) {
		this.bigDataRepository = bigDataRepository;
	}
	
//	public NegotiationInterface getNegotiator() {
//	return negotiator;
//}
//
//public void setNegotiator(NegotiationInterface negotiator) {
//	this.negotiator = negotiator;
//}

}
