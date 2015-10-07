package it.unipi.iotplatform.qosbroker.couchdb.api;

import java.util.List;

import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElement;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElementResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;

public interface QoSBigDataRepository {
	
	void storeData(List<ContextElement> contextElementList);
	
	List<ContextElementResponse> getEntityLatestValues(EntityId entityId);
}
