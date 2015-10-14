package it.unipi.iotplatform.qosbroker.qosmanager.api;


import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceAgreementRequest;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceAgreementResponse;

import javax.annotation.Resource;

@Resource
public interface QoSBrokerIF {
	
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