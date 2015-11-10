package it.unipi.iotplatform.qosbroker.api.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;
import eu.neclab.iotplatform.ngsi.api.datamodel.Polygon;
import eu.neclab.iotplatform.ngsi.api.datamodel.Vertex;

public class Utils {

	public static HashMap<String, Double> sortByValue(HashMap<String, Double> unsortMap) {

		// Convert Map to List
		List<Map.Entry<String, Double>> list = 
			new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, Collections.reverseOrder(new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
                                           Map.Entry<String, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		}));

		// Convert sorted map back to a Map
		HashMap<String, Double> sortedMap = new LinkedHashMap<String, Double>();
		for (Iterator<Map.Entry<String, Double>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Double> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	/* function to check if a point is inside a polygon */
	public static boolean in_polygon(Polygon polygon, Point coords) {
	
		int i;
		int j;
		
		List<Vertex> vertexList = polygon.getVertexList();
		
//	      for (i = 0, j = points.length - 1; i < points.length; j = i++) {
//	          if ((points[i].y > test.y) != (points[j].y > test.y) &&
//	              (test.x < (points[j].x - points[i].x) * (test.y - points[i].y) / (points[j].y-points[i].y) + points[i].x)) {
//	            result = !result;
//	           }
//	        }
		
		boolean result = false;
		for (i = 0, j = vertexList.size() - 1; i < vertexList.size(); j = i++) {
			
			if ((vertexList.get(i).getLongitude() > coords.getLongitude()) != 
					(vertexList.get(j).getLongitude() > coords.getLongitude())
					
					&& (coords.getLatitude() < (vertexList.get(j).getLatitude() - vertexList.get(i).getLatitude())
							* (coords.getLongitude() - vertexList.get(i).getLongitude())
							/ (vertexList.get(j).getLongitude() - vertexList.get(i).getLongitude()) 
							+ vertexList.get(i).getLatitude())) {
				
				result = !result;
			}
		}
		
		return result;
	}

	/* function to check if a point is inside a circle */
	public static boolean in_circle(Circle circle, Point coords) {
		
		Double x2 = Math.pow((circle.getCenterLatitude() - coords.getLatitude()), 2);
		Double y2 = Math.pow((circle.getCenterLongitude() - coords.getLongitude()), 2);
		
		Double dist = Math.sqrt(x2 + y2);
	    return dist <= circle.getRadius();
	}
	
	/* function to compute the factorization of a number */
	public static List<Integer> factorization(Integer number) {
		
		int n = number;
	    List<Integer> factors = new ArrayList<Integer>();
	    factors.add(1);
	    for (int i = 2; i <= n; i++) {
	      while (n % i == 0) {
	    	if(!factors.contains(i))
	    		factors.add(i);
	        n /= i;
	      }
	    }
	    return factors;
	}
	
	public static long getHyperperiod(ArrayList<Double> periods){
	    long result = (long) Math.ceil(periods.get(0));
	    for(int i = 1; i < periods.size(); i++) result = lcm(result, (long) Math.ceil(periods.get(i)));
	    return result;
	}
	
	private static long gcd(long a, long b){
	    while (b > 0)
	    {
	        long temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	}

	private static long lcm(long a, long b){
	    return a * (b / gcd(a, b));
	}
}
