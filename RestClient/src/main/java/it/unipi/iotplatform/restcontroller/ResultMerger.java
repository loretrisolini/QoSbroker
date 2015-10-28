package it.unipi.iotplatform.restcontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ResultMerger {

	private AtomicInteger howManyFeasible = new AtomicInteger(0);
	
	private Long startTime;
	
	private List<Long> arrivalTime = new ArrayList<>();
	
	public void setResult(){
		howManyFeasible.getAndIncrement();
		
	}

	public void setStartTime(){
		startTime = System.currentTimeMillis();
	}
	
	public Integer getHowManyFeasible() {
		return howManyFeasible.get();
	}

	public List<Long> getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime.add(arrivalTime.getTime() - startTime);
	}

}
