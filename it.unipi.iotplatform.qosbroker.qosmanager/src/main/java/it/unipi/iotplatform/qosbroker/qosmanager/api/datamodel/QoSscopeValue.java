package it.unipi.iotplatform.qosbroker.qosmanager.api.datamodel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

/*
<scopeType>QoS</scopeType>
<scopeValue>
	 <maxResponseTime>15</maxResponseTime>
	 <maxRateRequest>15</maxRateRequest>
</scopeValue>
*/

@XmlRootElement(name = "scopeValue")
@XmlAccessorType(XmlAccessType.FIELD)
public class QoSscopeValue extends ServiceAgreementStructure {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(ServiceAgreementStructure.class);
	
	@XmlElement(name = "maxResponseTime", required = true)
	private double maxResponseTime;
	
	@XmlElement(name = "maxRateRequest", required = true)
	private double maxRateRequest;

	public double getMaxResponseTime() {
		return maxResponseTime;
	}

	public void setMaxResponseTime(double maxResponseTime) {
		this.maxResponseTime = maxResponseTime;
	}

	public double getMaxRateRequest() {
		return maxRateRequest;
	}

	public void setMaxRateRequest(double maxRateRequest) {
		this.maxRateRequest = maxRateRequest;
	}
	
//	public static QoSscopeValue convertObjectToQoSscopeValue(Object object) {
//
//		QoSscopeValue response = null;
//
//		try {
//			JAXBContext jaxbContext = JAXBContext.newInstance(QoSscopeValue.class);
//			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//
//			response = (QoSscopeValue)unmarshaller.unmarshal((Node)object);
//
//		} catch (JAXBException e) {
//			logger.info("JAXBException", e);
//		}
//
//		return response;
//
//	}
}
