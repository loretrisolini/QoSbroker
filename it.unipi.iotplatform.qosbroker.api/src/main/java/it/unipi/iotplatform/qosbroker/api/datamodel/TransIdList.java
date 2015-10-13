package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name = "transactions")
@XmlAccessorType(XmlAccessType.FIELD)
/* class that contain the List<DevId> */
public class TransIdList extends DataStructure{


	@XmlElementWrapper(name = "transIdList")
	@XmlElement(name = "transId")
	@JsonProperty("transactions")
	private List<String> transIdList;

	public TransIdList() {

		this.transIdList = new ArrayList<>();
	}
	
	public List<String> getTransIdList() {
		return transIdList;
	}

	public void setTransIdList(List<String> transIdList) {
		this.transIdList = transIdList;
	}


}