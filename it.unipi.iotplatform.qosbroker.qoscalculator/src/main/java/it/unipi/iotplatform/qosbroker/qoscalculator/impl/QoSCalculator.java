package it.unipi.iotplatform.qosbroker.qoscalculator.impl;

import it.unipi.iotplatform.qosbroker.qoscalculator.api.QoSCalculatorIF;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.RequestResult;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceAssignments;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceExecutionFeature;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ThingIdThingServiceIdPair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistration;

public class QoSCalculator implements QoSCalculatorIF {

	/**
	 * The Class Reserveobj.
	 */
	private class Reserveobj {
		
		/** The feasible. */
		boolean feasible = false;
		
		/** The z. */
		Double z = 0.0;
		
		HashMap<String, ArrayList<ThingIdThingServiceIdPair>> allocationSchema;

		Reserveobj() {
			allocationSchema = new HashMap<>();
		}
		
	}
	
	@Override
	public List<ContextRegistration> computeAllocation(
			List<ServiceAssignments> mappingServEqThings,
			HashMap<String, Integer> coefficientMap,
			HashMap<Integer, Thing> thingsMap,
			HashMap<String, RequestResult> requestResultsMap) {
		
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Execute the heuristic specifically tailored for battery consumption.
	 *
	 * @param n the number of things
	 * @param k the number of request
	 * @param p the matrix of battery costs 
	 * @param W the matrix of computational costs
	 * @param b2 the vector of starting battery
	 * @param ts 
	 * @param C 
	 * @param numass 
	 * @param epsilon the tolerance used to stop iterations
	 * @return the reserve object
	 */
	private Reserveobj ABGAP(List<ServiceAssignments> mappingServEqThings,
			HashMap<String, Integer> coefficientMap,
			double epsilon, boolean battery){

		Reserveobj res = null;
		double upper = 1.0;
		double lower = 0.0;
		double teta = 0;
		double z = 0;
		//System.out.println("teta = "+teta);
		
		
//		res = GAP(k, f, p, b2, ts, teta, battery);
//	
//		if(res.feasible == true)
//		{
//			teta = (upper - lower) / 2;
//			while((upper - lower) > epsilon)
//			{
//				//System.out.println("teta = "+teta);
//				res = GAP(k, f, p, b2, ts, teta, battery);
//	
//				if(res.feasible)
//				{
//					z = teta;
//					lower = teta;
//					teta = teta + ((upper-lower) / 2 );
//				}
//				else
//				{
//					upper = teta;
//					teta = teta - ((upper-lower) / 2 );
//				}
//	
//			}
//			if(!res.feasible){
//				teta = z;
//				//System.out.println("teta = "+teta);
//				res = GAP(k, f, p, b2, ts, teta, battery);
//			}
//		}
//		
//		res.z = z;
//		return res;
		return null;
	}

	/**
	 * Real_battery_ gap.
	 *
	 * @param n the n
	 * @param k2 the k
	 * @param ts 
	 * @param F the f
	 * @param W the w
	 * @param B the b
	 * @param C 
	 * @param teta the teta
	 * @return the reserveobj
	 */
	private Reserveobj GAP(List<ServiceAssignments> mappingServEqThings,
			HashMap<String, Integer> coefficientMap,
			int priorityIndex, double teta, boolean battery) {
	
		Reserveobj res = new Reserveobj();
		
//		ArrayList<AssignmentPlus> Fj = new ArrayList<AssignmentPlus>();
//		QoSMAssignmentStar max;
//		QoSMAssignmentStar[] ma;
//		QoSMAssignmentStar chosen = null;
//		
//		double ds, d;
//		
//		double INF = Double.POSITIVE_INFINITY;
//		
//		ArrayList<QoSMRequestStar> K = cloneRequests(k2);
//		ArrayList<QoSMAssignmentStar> F = cloneAssignments(f2);
//		ArrayList<QoSMAssignmentStar> P = cloneAssignments(p2);
//		ArrayList<QoSMThingStar> B = cloneThings(b2);
//		
//		Map<String, QoSMThingServiceStar> S = cloneThingServices(s);
//		
//		res.feasible = true;
//		res.b.clear();
//		res.b = B;
//
//		ArrayList<QoSMRequestStar> bckK = cloneRequests(K);
//
//		
//		while(res.feasible && !K.isEmpty()){
//			ds = -1 * INF;
//			for(int j=0;j<K.size();j++){
//				QoSMRequestStar req = K.get(j);
//				Fj.clear();
//				for(QoSMAssignmentStar a : F)
//				{
//					if(!(a.getId().getServiceId().equals(req.getId().getServiceId()) && 
//							a.getId().getRequestId() == req.getId().getRequestId()))
//						continue;
//					
//					QoSMThingServiceStar ts = S.get(a.getId().getThingServiceId());
//					QoSMThingStar t = getThing(ts.getDeviceId(), res.b);
//					double exp = 1.0 /(t.getNumass() + 1.0);
//					double cal_res =  Math.pow(2, exp);
//					double upper = (t.getNumass() + 1.0 ) * (cal_res - 1.0);
//					if((a.getTotalComputationalCost() + t.getCapacityUsed()) < upper 
//							&& (t.getBatteryLevel() - a.getTotalBatteryCost()) >= teta){
//						
//						QoSMAssignmentStar ass = new QoSMAssignmentStar(req.getId().getServiceId(),
//								req.getId().getRequestId(), ts.getThingServiceId(), a.getTotalBatteryCost(), 
//								a.getTotalComputationalCost());
//
//						AssignmentPlus assp = null;
//						if(battery == true){
//							assp = new AssignmentPlus(ass, t.getBatteryLevel() - ass.getTotalBatteryCost());
//						}
//						else{
//							assp = new AssignmentPlus(ass,ass.getTotalBatteryCost());
//						}
//						Fj.add(assp);
//					}
//				}
//				if(Fj.isEmpty()){
//					res.feasible = false;
//				}
//				else{
//					
//					if(Fj.size() <= 1){
//						d = INF;
//						max = Fj.get(0).ass;
//					}
//					else{
//						ma = maximum(Fj);
//						max=ma[0];
//						d = max.getTotalBatteryCost() - ma[1].getTotalBatteryCost();
//					}
//					if(d>ds){
//						ds = d;
//						chosen=max;
//					}
//				}
//			}// end for
//			if(res.feasible){
//				QoSMThingServiceStar ts = S.get(chosen.getId().getThingServiceId());
//				QoSMThingStar t = getThing(ts.getDeviceId(), res.b);
//				res.y.add(new QoSMAssignmentStar(chosen));
//				t.setBatteryLevel(t.getBatteryLevel() - chosen.getTotalBatteryCost());
//				t.setCapacityUsed((t.getCapacityUsed() + chosen.getTotalComputationalCost()));
//				int num = t.getNumass();
//				num++;
//				t.setNumass(num);
//				QoSMRequestStar del=null;
//				for(QoSMRequestStar r : K)
//				{
//					if(r.getId().getServiceId().equals(chosen.getId().getServiceId()) && 
//							r.getId().getRequestId() == chosen.getId().getRequestId())
//						del = r;
//				}
//				K.remove(del);
//			}
//
//		}
//		if(!battery && res.feasible)
//		{
//			QoSMAssignmentStar ip, min;
//			
//			for(int j=0;j<bckK.size();j++){
//				ip = res.y.get(j);
//				
//				Fj.clear();
//				QoSMRequestStar req = getRequest(ip.getId().getServiceId(), ip.getId().getRequestId(), bckK);
//				for(QoSMAssignmentStar a : P)
//				{
//					if(!(a.getId().getServiceId().equals(req.getId().getServiceId()) && 
//							a.getId().getRequestId() == req.getId().getRequestId()))
//						continue;
//					QoSMThingServiceStar ts = S.get(a.getId().getThingServiceId());
//					QoSMThingStar t = getThing(ts.getDeviceId(), res.b);
//					double upper = (t.getNumass() + 1) * Math.pow(2, ((1/(t.getNumass() + 1))-1));
//					
//					if((a.getTotalComputationalCost() + t.getCapacityUsed()) < upper 
//							&& (t.getBatteryLevel() - a.getTotalBatteryCost()) >= teta){
//						if(!ip.equals(a)){
//							QoSMAssignmentStar ass = new QoSMAssignmentStar(req.getId().getServiceId(),
//									req.getId().getRequestId(), ts.getThingServiceId(), a.getTotalBatteryCost(), 
//									a.getTotalComputationalCost());
//							AssignmentPlus assp = null;
//							assp = new AssignmentPlus(ass, t.getBatteryLevel() - ass.getTotalBatteryCost());
//							Fj.add(assp);
//						}
//					}
//				}
//				
//				
//				if(!Fj.isEmpty()){
//					min = minimum(Fj);
//					if(min.getTotalBatteryCost() < ip.getTotalBatteryCost()){
//						QoSMAssignmentStar del = null;
//						for(QoSMAssignmentStar a : res.y)
//						{
//							if(a.equals(ip))
//								del = a;
//						}
//						res.y.remove(del);
//						res.y.add(new QoSMAssignmentStar(min));
//						
//						QoSMThingServiceStar ts = S.get(ip.getId().getThingServiceId());
//						QoSMThingStar t = getThing(ts.getDeviceId(), res.b);
//						
//						t.setBatteryLevel(t.getBatteryLevel() + ip.getTotalBatteryCost());
//						t.setCapacityUsed(t.getCapacityUsed() - ip.getTotalComputationalCost());
//						
//						ts = S.get(min.getId().getThingServiceId());
//						QoSMThingStar t2 = getThing(ts.getDeviceId(), res.b);
//						t2.setBatteryLevel(t2.getBatteryLevel() - min.getTotalBatteryCost());
//						t2.setCapacityUsed(t2.getCapacityUsed() + min.getTotalComputationalCost());
//						
//						
//						int num = t.getNumass();
//						t.setNumass(num--);
//						num = t2.getNumass();
//						t2.setNumass(num++);
//		
//					}
//				}
//		
//			}
//	
//		}
		
		
		
		/*if(res.feasible)
		{
			for(QoSMThingStar t : res.b)
			{
				QoSMThingStar origin = getThing(t.getDeviceId(), B);
				t.setBatteryLevel(t.getBatteryLevel() * origin.getBatteryLevel());
			}
		}*/
		return res;
	}

	
}
