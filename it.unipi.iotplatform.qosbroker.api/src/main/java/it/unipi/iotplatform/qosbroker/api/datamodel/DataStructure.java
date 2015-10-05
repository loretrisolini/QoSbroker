package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

/**
 *  Common super-type for NGSI data structure implementations.
 */
public abstract class DataStructure {

	/** The logger. */
	private static Logger logger = Logger.getLogger(DataStructure.class);

	@Override
	public String toString() {

		String result;
		StringWriter sw = new StringWriter();
		try {
			JAXBContext carContext = JAXBContext.newInstance(this.getClass());
			Marshaller carMarshaller = carContext.createMarshaller();
			carMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			carMarshaller.marshal(this, sw);
			result = sw.toString();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}

		return result;

	}

	public static Object convertStringToXml(String xml, Class<?> type) {

		if (type.getSuperclass() != DataStructure.class) {
			throw new RuntimeException("Cannot convert String to "
					+ type.getName());
		}

		Object response = null;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(type);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xml);
			response = unmarshaller.unmarshal(reader);

		} catch (JAXBException e) {
			logger.info("JAXBException", e);
		}

		return response;

	}
	
	public static <T> T convertObjectToJaxbObject(Object object, T JaxbObject) {

		if (JaxbObject.getClass().getSuperclass() != DataStructure.class) {
			throw new RuntimeException("Cannot convert Object to "
					+ JaxbObject.getClass().getName());
		}
		


		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(QoSscopeValue.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			JaxbObject = (T)unmarshaller.unmarshal((Node)object);

		} catch (JAXBException e) {
			logger.info("JAXBException", e);
		}

		return JaxbObject;

	}

	
}