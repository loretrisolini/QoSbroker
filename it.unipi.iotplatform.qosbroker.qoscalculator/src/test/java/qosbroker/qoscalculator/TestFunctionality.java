package qosbroker.qoscalculator;

import static org.junit.Assert.assertEquals;
import it.unipi.iotplatform.qosbroker.api.datamodel.LocationScopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSscopeValue;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServicePeriodParams;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.qoscalculator.impl.QoSCalculator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;
import eu.neclab.iotplatform.ngsi.api.datamodel.Polygon;
import eu.neclab.iotplatform.ngsi.api.datamodel.Vertex;


public class TestFunctionality {

		//The components that are tested:
		QoSCalculator qosCalculator;
		FileWriter fileWriter;
		
		int k = 0;
		Double epsilon = null;
		List<Pair<String, Request>> requests = new ArrayList<Pair<String, Request>>();
		HashMap<String, ServicePeriodParams> servPeriodsMap = new HashMap<>();
		HashMap<String, Thing> eqThingInfo = new HashMap<>();
		HashMap<String, ThingsIdList> servNameThingsIdList = new HashMap<>();
		
		ReservationResults resultNotNull = new ReservationResults();
		
		//initialize logger
		private static Logger logger = Logger.getLogger("Unit Tests");
		
		private final static int SINGLE_PARAMS = 1;
		private final static int REQUESTS = 2;
		private final static int PERIODS_PARAMS = 3;
		private final static int THINGS = 4;
		private final static int SERVICES = 5;
		private final static int SERVICE_EQTHINGS = 6;
		
		@Before
		public void before(){
			
			qosCalculator = new QoSCalculator();
			
			String csvFile = "/home/lorenzo/Downloads/FIWARE-WORK/git/QoSbroker/"
					+ "it.unipi.iotplatform.qosbroker.qoscalculator"
					+ "/src/test/resources/inputQoSCalculatorTest.csv";
			
			readParams(csvFile);
		}

		@Test
		public void gapTest(){

			logger.info("Now testing it.unipi.iotplatform.qosbroker.qoscalculator");
			
			//execute the test
			ReservationResults result = qosCalculator.computeAllocation(k, requests, servPeriodsMap, eqThingInfo, servNameThingsIdList, epsilon);
			
			assertEquals(result,resultNotNull);
			
			logger.info("Successfully tested it.unipi.iotplatform.qosbroker.qoscalculator");
		}
	
		
		private void readParams(String fileName){
			
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
			
			try {
				String[] header;
				String[] values;
				
				br = new BufferedReader(new FileReader(fileName));
				while ((line = br.readLine()) != null) {

					header = line.split(cvsSplitBy);
					
					String[] lineKey = null;
					if(line.trim().isEmpty()) continue;
					else
						lineKey = header[0].split(":");
					
					switch(Integer.valueOf(lineKey[0])){
					
						case SINGLE_PARAMS:{
							line = br.readLine();
							
							values = line.split(cvsSplitBy);
							
							k = Integer.valueOf(values[0]);
							
//							policy = Policy.valueOf(values[1]);
							
							epsilon = Double.valueOf(values[1]);
							
							break;
						}
					
						case REQUESTS:{
							
							while (!(line = br.readLine()).trim().isEmpty()){
								values = line.split(cvsSplitBy);
								
								String transId = values[0];
								Request req = new Request();
								req.setOpType(values[1]);
								
								QoSscopeValue qosReq = new QoSscopeValue();
								qosReq.setMaxResponseTime(Double.valueOf(values[2]));
								qosReq.setMaxRateRequest(Double.valueOf(values[3]));
								qosReq.setAccuracy(values[4].contentEquals("null") ? null : Double.valueOf(values[4]));
								req.setQosRequirements(qosReq);
								
								LocationScopeValue<Point> locReqPoint = null;
								LocationScopeValue<Circle> locReqCircle = null;
								LocationScopeValue<Polygon> locReqPolygon = null;

								if(values[5].contains("Point")){
									locReqPoint = new LocationScopeValue<Point>();
									
									Point p = new Point();
									values[5] = values[5].replace("Point:", "");
									String[] coords = values[5].split(";");
									
									p.setLatitude(Float.valueOf(coords[0]));
									p.setLongitude(Float.valueOf(coords[1]));
									
									locReqPoint.setLocationRequirement(p);
									
									req.setLocationRequirementPoint(locReqPoint);
								}
								else{
									if(values[5].contains("Circle")){
										locReqCircle = new LocationScopeValue<Circle>();
										
										Circle c = new Circle();
										values[5] = values[5].replace("Circle:", "");
										String[] circleParams = values[5].split(";");
										
										c.setCenterLatitude(Float.valueOf(circleParams[0]));
										c.setCenterLongitude(Float.valueOf(circleParams[1]));
										c.setRadius(Float.valueOf(circleParams[2]));
										
										locReqCircle.setLocationRequirement(c);
										
										req.setLocationRequirementCircle(locReqCircle);
									}
									else{
										locReqPolygon = new LocationScopeValue<Polygon>();
										
										Polygon polygon = new Polygon();
										values[5] = values[5].replace("Polygon:", "");
										
										List<Vertex> vertexList = new ArrayList<>();
										String[] vertexListString = values[5].split(";");
										
										for(int j = 0; j<vertexListString.length; j++){
											String[] vertex = vertexListString[j].split(" ");
											
											Vertex v = new Vertex();
											v.setLatitude(Float.valueOf(vertex[0]));
											v.setLongitude(Float.valueOf(vertex[1]));
											vertexList.add(v);
										}
										
										polygon.setVertexList(vertexList);
										
										locReqPolygon.setLocationRequirement(polygon);
										
										req.setLocationRequirementPolygon(locReqPolygon);
									}
								}
								
								List<String> serviceList = new ArrayList<>();
								for(int p=6; p<values.length; p++){
									
									serviceList.add(values[p]);
								}
								req.setRequiredServicesNameList(serviceList);
								
								requests.add(new Pair<String, Request>(transId, req));
							}
							
							break;
						}
						
						case PERIODS_PARAMS:{
							while (!(line = br.readLine()).trim().isEmpty()){
								values = line.split(cvsSplitBy);
								
								
								String transId = values[0];
								
								ServicePeriodParams servPeriod = new ServicePeriodParams();
								servPeriod.setNj(Integer.valueOf(values[1]));
								servPeriod.setPeriod(Double.valueOf(values[2]));
								
								servPeriodsMap.put(transId, servPeriod);
							}
							
							break;
						}
						
						case THINGS:{
							while (!(line = br.readLine()).trim().isEmpty()){
								values = line.split(cvsSplitBy);
								
								String devId = values[0];
								Thing t = new Thing();
								
								t.setBatteryLevel(Double.valueOf(values[1]));
								
								String[] coords = values[2].split(" ");
								Point p = new Point();
								p.setLatitude(Float.valueOf(coords[0]));
								p.setLongitude(Float.valueOf(coords[1]));
								t.setCoords(p);
								
								t.setServicesList(new HashMap<String,ServiceFeatures>());
								
								eqThingInfo.put(devId, t);
							}
							
							break;
						}
						
						case SERVICES:{
							
							String devId=null;
							HashMap<String, ServiceFeatures> services = new HashMap<>();
							
							while (!(line = br.readLine()).trim().isEmpty()){
								values = line.split(cvsSplitBy);
								
								if(devId!=null && !devId.contentEquals(values[0])){
									eqThingInfo.get(devId).setServicesList(services);
									services = new HashMap<>();
								}
								
								devId = values[0];
								
								ServiceFeatures servFeat = new ServiceFeatures();
								servFeat.setLatency(values[2].contentEquals("null") ? null : Double.valueOf(values[2]));
								servFeat.setEnergyCost(values[3].contentEquals("null") ? null : Double.valueOf(values[3]));
								servFeat.setAccuracy(values[4].contentEquals("null") ? null : Double.valueOf(values[4]));
								services.put(values[1], servFeat);
							}
							eqThingInfo.get(devId).setServicesList(services);
							
							break;
						}
						
						case SERVICE_EQTHINGS:{
							
							while ((line = br.readLine()) != null){
								values = line.split(cvsSplitBy);
								
								ThingsIdList tIdList = new ThingsIdList();
								List<String> servList = new ArrayList<>();
							
								for(int j=1; j<values.length; j++){
									servList.add(values[j]);
								}
								tIdList.setEqThings(servList);
								
								servNameThingsIdList.put(values[0], tIdList);
							}
							
							break;
						}
						
						default:{
							
							System.out.println("ERROR");
							
							break;
						}
					}
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
}

/*
1:k,epsilon
4,0.001

2:TransactionID, operationType, maxRespTime, maxRateRequest, accuracy, LocationRequirement, ServiceList
ad7fc40d-e23e-4557-9fbb-d89f63b3c6f3,queryContext,15,3,null,Circle:43.656998;10.437418;1000.9,degrees,humidity,CO2
ad7fc40d-e23e-4557-9fbb-eeeeefffffffffdd,queryContext,7,5,null,Circle:43.656998;10.437418;100.9,humidity

3:TransactionID,h/p_j, p_j
ad7fc40d-e23e-4557-9fbb-d89f63b3c6f3,5,3.0
ad7fc40d-e23e-4557-9fbb-eeeeefffffffffdd,3,5.0

4:DevId,BatteryLevel,Coords
sensor_3:environment,99,37.0 45.9
sensor_1:environment,99,57.0 45.9
sensor_2:temperature,90,47.0 45.9
sensor_4:temperature,77,57.0 44.9

5:DevId,ServiceName,Latency,EnergyCost,Accuracy
sensor_3:environment,CO2,0.3,0.2,null
sensor_3:environment,battery,null,null,null
sensor_3:environment,degrees,0.4,0.4,null
sensor_1:environment,humidity,0.54,0.3,null
sensor_1:environment,battery,null,null,null
sensor_1:environment,degrees,0.54,0.4,null
sensor_2:temperature,battery,null,null,null
sensor_2:temperature,degrees,0.6,0.3,null
sensor_2:temperature,humidity,0.3,0.11,null
sensor_4:temperature,battery,null,null,null
sensor_4:temperature,degrees,0.2,0.4,null

6:requiredServiceName,devIdList
CO2,sensor_3:environment
humidity,sensor_1:environment,sensor_2:temperature
degrees,sensor_1:environment,sensor_3:environment,sensor_2:temperature,sensor_4:temperature*/
