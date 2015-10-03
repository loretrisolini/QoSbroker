package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

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
public class QoSscopeValue extends DataStructure {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(DataStructure.class);
	
	@XmlElement(name = "maxResponseTime", required = true)
	private Double maxResponseTime;
	
	@XmlElement(name = "maxRateRequest", required = true)
	private Double maxRateRequest;

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		QoSscopeValue.logger = logger;
	}

	public Double getMaxResponseTime() {
		return maxResponseTime;
	}

	public void setMaxResponseTime(Double maxResponseTime) {
		this.maxResponseTime = maxResponseTime;
	}

	public Double getMaxRateRequest() {
		return maxRateRequest;
	}

	public void setMaxRateRequest(Double maxRateRequest) {
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
