package it.unipi.iotplatform.qosbroker.qosmanager.api;

import javax.annotation.Resource;

import it.unipi.iotplatform.qosbroker.qosmanager.api.datamodel.ServiceAgreementRequest;
import it.unipi.iotplatform.qosbroker.qosmanager.api.datamodel.ServiceAgreementResponse;

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
	public ServiceAgreementResponse createAgreement(ServiceAgreementRequest offer);
	
}
