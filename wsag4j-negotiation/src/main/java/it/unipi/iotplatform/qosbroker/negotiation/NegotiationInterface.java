package it.unipi.iotplatform.qosbroker.negotiation;

public interface NegotiationInterface {
	
	// Retrieve the given template
	public String getTemplate(String name);

	// Parse the given offer and send back the agreement 
	public String sendOffer(String offer);
	
}
