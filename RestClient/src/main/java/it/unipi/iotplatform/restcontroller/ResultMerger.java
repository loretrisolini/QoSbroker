package it.unipi.iotplatform.restcontroller;

import java.util.concurrent.atomic.AtomicInteger;

public class ResultMerger {

	private AtomicInteger howManyFeasible = new AtomicInteger(0);
	
	public void setResult(){
		howManyFeasible.getAndIncrement();
		
	}

	public Integer getHowManyFeasible() {
		return howManyFeasible.get();
	}

}
