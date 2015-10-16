package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;
import eu.neclab.iotplatform.ngsi.api.datamodel.Polygon;
import eu.neclab.iotplatform.ngsi.api.datamodel.Vertex;

public class Statistics {

	public static void printThingsMappings(Request request, HashMap<String, Thing> thingsInfo,
			HashMap<String, ThingsIdList> serviceEquivalentThings) {
		
		FileWriter fileWriter = null;
		
		try{
			fileWriter = new FileWriter("/home/lorenzo/Downloads/FIWARE-WORK/git/QoSbroker/Tests/Things.csv");
			
			fileWriter.append("DevId,BatteryLevel,Coords");
			fileWriter.append("\n");
			
			Map<String, HashMap<String, ServiceFeatures>> mapThingServices = new HashMap<>();
			
			for(Map.Entry<String, Thing> entryThing: thingsInfo.entrySet()){
				fileWriter.append(entryThing.getKey());
				fileWriter.append(",");

				fileWriter.append(entryThing.getValue().getBatteryLevel()==null ? "null" 
													: entryThing.getValue().getBatteryLevel().toString());
				fileWriter.append(",");
				fileWriter.append(entryThing.getValue().getCoords()==null ? "null" 
													: entryThing.getValue().getCoords().getLatitude()+" "+
													entryThing.getValue().getCoords().getLongitude());
				fileWriter.append("\n");
				mapThingServices.put(entryThing.getKey(), entryThing.getValue().getServicesList());
				
			}
			
			fileWriter.append("\n");
			
			if(!mapThingServices.isEmpty()){
			
				fileWriter.append("DevId,ServiceName,Latency,EnergyCost,Accuracy");
				fileWriter.append("\n");
				
				for(Map.Entry<String, HashMap<String, ServiceFeatures>> entry: mapThingServices.entrySet()){
					
					String devId = entry.getKey();
					HashMap<String, ServiceFeatures> services = entry.getValue();
					
					for(Map.Entry<String, ServiceFeatures> service: services.entrySet()){
						
						fileWriter.append(devId);
						fileWriter.append(",");
						fileWriter.append(service.getKey());
						fileWriter.append(",");
						fileWriter.append(service.getValue().getLatency()==null ? "null"
														: service.getValue().getLatency().toString());
						fileWriter.append(",");
						fileWriter.append(service.getValue().getEnergyCost()==null ? "null"
														: service.getValue().getEnergyCost().toString());
						fileWriter.append(",");
						fileWriter.append(service.getValue().getAccuracy()==null ? "null"
														: service.getValue().getAccuracy().toString());
						fileWriter.append("\n");
					}
				}
			}
			
			fileWriter.append("\n");
			
			fileWriter.append("requiredServiceName,devIdList");
			fileWriter.append("\n");
			
			for(Map.Entry<String, ThingsIdList> entryEqThings: serviceEquivalentThings.entrySet()){
				
				fileWriter.append(entryEqThings.getKey());
				fileWriter.append(",");

				List<String> eqThings = entryEqThings.getValue().getEqThings();
				for(String thingId: eqThings){
					fileWriter.append(thingId);
					fileWriter.append(",");
				}
				fileWriter.append("\n");
			}
			
		}
		catch(Exception e){
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		}
		finally{
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
		}
	}
	
	
	/* function to print the input values of the GAP algorithm */
	public static void printInputGap(int k, List<Pair<String, Request>> requests,
			HashMap<String, ServicePeriodParams> servPeriodsMap,
			HashMap<String, Thing> eqThingInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList,
			HashMap<String, List<String>> matrixM, double teta, String prio,
			String policy) {
		
		FileWriter fileWriter = null;
		
		try{
			fileWriter = new FileWriter("/home/lorenzo/Downloads/FIWARE-WORK/git/QoSbroker/Tests/InputGap.csv");
			
			fileWriter.append("k,Teta,Priority,Policy");
			fileWriter.append("\n");
			
			fileWriter.append(String.valueOf(k));
			fileWriter.append(",");
			fileWriter.append(String.valueOf(teta));
			fileWriter.append(",");
			fileWriter.append(prio);
			fileWriter.append(",");
			fileWriter.append(policy);
			fileWriter.append("\n");
			fileWriter.append("\n");
			
			fileWriter.append("TransactionID, operationType, maxRespTime, maxRateRequest, LocationRequirement, ServiceList");
			fileWriter.append("\n");
			for(Pair<String, Request> reqPair: requests){
				String transId = reqPair.getLeft();
				Request req = reqPair.getRight();
				
				fileWriter.append(transId);
				fileWriter.append(",");
				fileWriter.append(req.getOpType());
				fileWriter.append(",");
				fileWriter.append(String.valueOf(req.getQosRequirements().getMaxResponseTime()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(req.getQosRequirements().getMaxRateRequest()));
				fileWriter.append(",");
				
				Point point = null;
				Circle circle = null;
				Polygon polygon = null;
				
				if(req.getLocationRequirement() != null){
					//take the location requirement from the LocationRequirement object in the request object
					Class<?> locRequirementsType = req.getLocationRequirement().getLocationRequirement().getClass();
					
					if(locRequirementsType == Point.class){
						point = (Point)req.getLocationRequirement().getLocationRequirement();
						fileWriter.append("Point: "+String.valueOf(point.getLatitude())+" "+String.valueOf(point.getLongitude()));
					}
					else{
						if(locRequirementsType == Circle.class){
							circle = (Circle)req.getLocationRequirement().getLocationRequirement();
							fileWriter.append("Circle: cLat: "+String.valueOf(circle.getCenterLatitude())+" | cLon: "+String.valueOf(circle.getCenterLongitude())
												+" | rad: "+String.valueOf(circle.getRadius()));
						}
						else{
							polygon = (Polygon)req.getLocationRequirement().getLocationRequirement();
							
							List<Vertex> vertexList = polygon.getVertexList();
							fileWriter.append("Polygon: ");
							for(Vertex v: vertexList){
								fileWriter.append("vLat: "+String.valueOf(v.getLatitude())+" vLon: "+String.valueOf(v.getLongitude())+" | ");
							}
						}
					}
				}
				fileWriter.append(",");
				
				for(String serv: req.getRequiredServicesNameList()){
					fileWriter.append(serv);
					fileWriter.append(",");
				}
				fileWriter.append("\n");
			}
			
			fileWriter.append("\n");
			fileWriter.append("TransactionID, h/p_j,p_j");
			fileWriter.append("\n");
			for(Map.Entry<String, ServicePeriodParams> entryPeriod: servPeriodsMap.entrySet()){
				fileWriter.append(entryPeriod.getKey());
				fileWriter.append(",");
				fileWriter.append(String.valueOf(entryPeriod.getValue().getNj()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(entryPeriod.getValue().getPeriod()));
				fileWriter.append("\n");
			}
			
			fileWriter.append("\n");
			
			fileWriter.append("DevId,BatteryLevel,Coords");
			fileWriter.append("\n");
			
			Map<String, HashMap<String, ServiceFeatures>> mapThingServices = new HashMap<>();
			
			for(Map.Entry<String, Thing> entryThing: eqThingInfo.entrySet()){
				fileWriter.append(entryThing.getKey());
				fileWriter.append(",");

				fileWriter.append(entryThing.getValue().getBatteryLevel()==null ? "null" 
													: entryThing.getValue().getBatteryLevel().toString());
				fileWriter.append(",");
				fileWriter.append(entryThing.getValue().getCoords()==null ? "null" 
													: entryThing.getValue().getCoords().getLatitude()+" "+
													entryThing.getValue().getCoords().getLongitude());
				fileWriter.append("\n");
				mapThingServices.put(entryThing.getKey(), entryThing.getValue().getServicesList());
				
			}
			
			fileWriter.append("\n");
			
			if(!mapThingServices.isEmpty()){
			
				fileWriter.append("DevId,ServiceName,Latency,EnergyCost,Accuracy");
				fileWriter.append("\n");
				
				for(Map.Entry<String, HashMap<String, ServiceFeatures>> entry: mapThingServices.entrySet()){
					
					String devId = entry.getKey();
					HashMap<String, ServiceFeatures> services = entry.getValue();
					
					for(Map.Entry<String, ServiceFeatures> service: services.entrySet()){
						
						fileWriter.append(devId);
						fileWriter.append(",");
						fileWriter.append(service.getKey());
						fileWriter.append(",");
						fileWriter.append(service.getValue().getLatency()==null ? "null"
															: service.getValue().getLatency().toString());
						fileWriter.append(",");
						fileWriter.append(service.getValue().getEnergyCost()==null ? "null"
															: service.getValue().getEnergyCost().toString());
						fileWriter.append(",");
						fileWriter.append(service.getValue().getAccuracy()==null ? "null"
														: service.getValue().getAccuracy().toString());
						fileWriter.append("\n");
					}
				}
			}
			
			fileWriter.append("\n");
			
			fileWriter.append("requiredServiceName,devIdList");
			fileWriter.append("\n");
			
			for(Map.Entry<String, ThingsIdList> entryEqThings: servNameThingsIdList.entrySet()){
				
				fileWriter.append(entryEqThings.getKey());
				fileWriter.append(",");

				List<String> eqThings = entryEqThings.getValue().getEqThings();
				for(String devId: eqThings){
					fileWriter.append(devId);
					fileWriter.append(",");
				}
				fileWriter.append("\n");
			}
			
			fileWriter.append("\n");
			
			fileWriter.append("transactionId::serviceName,devIdList");
			fileWriter.append("\n");
			
			for(Map.Entry<String, List<String>> entry: matrixM.entrySet()){
				
				fileWriter.append(entry.getKey());
				fileWriter.append(",");

				List<String> things = entry.getValue();
				for(String devId: things){
					fileWriter.append(devId);
					fileWriter.append(",");
				}
				fileWriter.append("\n");
			}
			
		}
		catch(Exception e){
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		}
		finally{
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
		}
		
	}
	
	
	public static void printAllocationSchema(
			HashMap<String, HashMap<String, AllocationObj>> allocationSchema) {
		
		try{
			PrintWriter writer = new PrintWriter("/home/lorenzo/Downloads/FIWARE-WORK/git/QoSbroker/Tests/ResultGap.txt", "UTF-8");

			writer.println("####################################");
			writer.println("####################################");
			
			for(Map.Entry<String, HashMap<String, AllocationObj>> entry: allocationSchema.entrySet()){
				writer.println("transId: "+entry.getKey());
				writer.println("<---------------------------------------->");
				
				HashMap<String, AllocationObj> services = entry.getValue();
				
				writer.println("services allocated:");
				for(Map.Entry<String, AllocationObj> entryAllocation: services.entrySet()){
					writer.println("service Name: "+entryAllocation.getKey());
					
					writer.println("split: "+entryAllocation.getValue().getSplit());
					writer.println("f_ij: "+entryAllocation.getValue().getF_ij());
					writer.println("u_ij: "+entryAllocation.getValue().getU_ij());
					
					List<String> devIdList = entryAllocation.getValue().getDevIdList();
					
					writer.println("Things allocated: ");
					for(String devId: devIdList){
						writer.println("thing: "+devId);
						writer.println("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>");
					}
					writer.println("<ooooooooooooooooooooooooooooooooooo>");
				}
				writer.println("<---------------------------------------->");
				writer.println("<---------------------------------------->");
			}
			
			writer.println("########################################");
			writer.println("########################################");
			writer.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
