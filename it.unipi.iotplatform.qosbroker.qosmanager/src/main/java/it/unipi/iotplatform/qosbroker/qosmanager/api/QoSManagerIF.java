package it.unipi.iotplatform.qosbroker.qosmanager.api;

import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.RequestResult;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceAgreementRequest;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceAgreementResponse;

import javax.annotation.Resource;

@Resource
public interface QoSManagerIF {

	/**
	 * It is called by RestController to get the standard template for the Fiware platform
	 * @return The template as a string
	 */
	public String getTemplate();
	
	/**
	 * It is called by RestController to send the Offer. The function returns a valid AgreemntEPR
	 * in case the negotiation is successful.
	 * The return is performed asynchronously through the call notifyAgreementEPR
	 * The agreementEPR is a valid AgreementEPR if the offer is accepted, otherwise an invalid value is returned 
	 * if the offer is rejected (empty string). 
	 * @param offer is the offer
	 * 
	 */
	public void createAgreement(String offer, RequestResult reqResult);
	
}
