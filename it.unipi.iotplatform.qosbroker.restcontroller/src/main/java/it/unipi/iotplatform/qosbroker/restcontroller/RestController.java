package it.unipi.iotplatform.qosbroker.restcontroller;

import it.unipi.iotplatform.qosbroker.qosmanager.api.QoSManagerIF;
import it.unipi.iotplatform.qosbroker.qosmanager.api.datamodel.ServiceAgreementRequest;
import it.unipi.iotplatform.qosbroker.qosmanager.api.datamodel.ServiceAgreementResponse;
import it.unipi.iotplatform.qosbroker.restcontroller.sanitycheck.SanityCheck;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.neclab.iotplatform.iotbroker.commons.JsonValidator;
import eu.neclab.iotplatform.iotbroker.commons.XmlValidator;
import eu.neclab.iotplatform.ngsi.api.datamodel.Code;
import eu.neclab.iotplatform.ngsi.api.datamodel.QueryContextRequest;
import eu.neclab.iotplatform.ngsi.api.datamodel.ReasonPhrase;
import eu.neclab.iotplatform.ngsi.api.datamodel.StatusCode;
import eu.neclab.iotplatform.ngsi.api.ngsi10.Ngsi10Interface;
import eu.neclab.iotplatform.ngsi.api.ngsi9.Ngsi9Interface;
/**
 * Handles requests for the application home page.
 */
@Controller
public class RestController {
	
	private static final Logger logger =Logger.getLogger(RestController.class);
	
	/** String representing json content type. */
	private final String CONTENT_TYPE_JSON = "application/json";

	/** String representing xml content type. */
	private final String CONTENT_TYPE_XML = "application/xml";
	
	/** The component for receiving WSAG4J requests. **/
	@Autowired
	private QoSManagerIF qosCore;
	
	/** The component for receiving NGSI9 requests. */
	@Autowired
	private Ngsi9Interface ngsi9Core;

	/** The component for receiving NGSI 10 requests. */
	private Ngsi10Interface ngsiCore;

	/**
	 * Returns a pointer to the component which receives NGSI 10 requests
	 * arriving at the controller.
	 *
	 * @return the ngsi core
	 */
	public Ngsi10Interface getNgsiCore() {
		return ngsiCore;
	}

	/**
	 * Assigns a pointer to the component which will receive NGSI 10 requests
	 * arriving at the controller.
	 *
	 * @param ngsiCore
	 *            the new ngsi core
	 */
	public void setNgsiCore(Ngsi10Interface ngsiCore) {
		this.ngsiCore = ngsiCore;
	}

	/**
	 * Returns a pointer to the component which receives NGSI 9 requests
	 * arriving at the controller.
	 *
	 * @return the ngsi9 core
	 */
	public Ngsi9Interface getNgsi9Core() {
		return ngsi9Core;
	}

	/**
	 * Assigns a pointer to the component which will receive NGSI 9 requests
	 * arriving at the controller.
	 *
	 * @param ngsi9Core
	 *            the new ngsi9 core
	 */
	public void setNgsi9Core(Ngsi9Interface ngsi9Core) {
		this.ngsi9Core = ngsi9Core;
	}

	/** String representing the xml schema for NGSI 10. */
	private @Value("${schema_ngsi10_operation}") String sNgsi10schema;

	/** String representing the xml schema for NGSI 9. */
	private @Value("${schema_ngsi9_operation}") String sNgsi9schema;
	
	/** String representing the xml schema for service request. */
	private @Value("${schema_serviceReq_operation}") String qosSchema;
	
	/**
	 * Executes the Sanity Check Procedure of the IoT Broker.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/sanityCheck", method = RequestMethod.GET, consumes = { "*/*" }, produces = {
			CONTENT_TYPE_XML, CONTENT_TYPE_JSON })
	public ResponseEntity<SanityCheck> sanityCheck() {

		
		
		BundleContext bc = FrameworkUtil
				.getBundle(RestController.class).getBundleContext();

		SanityCheck response = new SanityCheck("QoS Broker GE", "Sanity Check",
				"Version: " + bc.getBundle().getVersion());

		
		return new ResponseEntity<SanityCheck>(response, HttpStatus.OK);

	}
	
	/**
	 * Executes the test of the IoT Broker, which simply returns the request
	 * message.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/test", method = RequestMethod.POST, consumes = { "*/*" }, produces = {
			CONTENT_TYPE_XML, CONTENT_TYPE_JSON })
	public ResponseEntity<QueryContextRequest> test(
			HttpServletRequest requester,
			@RequestBody QueryContextRequest request) {

		System.out.println(request);

		return new ResponseEntity<QueryContextRequest>(request, HttpStatus.OK);

	}
	
	/**
	 * Executes the test of the IoT Broker, which simply returns the request
	 * message.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/createAgreement", method = RequestMethod.POST, consumes = { "CONTENT_TYPE_XML" }, produces = {
			CONTENT_TYPE_XML})
	public ResponseEntity<ServiceAgreementResponse> createAgreement(
			HttpServletRequest requester,
			@RequestBody ServiceAgreementRequest request) {

		logger.info(" <--- WSAG4J has received negotiation request ---> \n");
		
		if (validateMessageBody(requester, request, qosSchema)) {

			ServiceAgreementResponse response = qosCore.createAgreement(request);

			logger.debug("ServiceID: "+ response.getServiceID());

			return new ResponseEntity<ServiceAgreementResponse>(response,
					HttpStatus.OK);
		} else {

			ServiceAgreementResponse response = new ServiceAgreementResponse();
			StatusCode statusCode = new StatusCode(
					Code.BADREQUEST_400.getCode(),
					ReasonPhrase.BADREQUEST_400.toString(), "XML syntax Error!");

			response.setErrorCode(statusCode);

			return new ResponseEntity<ServiceAgreementResponse>(response,
					HttpStatus.BAD_REQUEST);

		}

	}
	
	/**
	 * Executes a syntax check of incoming messages. Currently supported formats
	 * are XML and JSON.
	 */
	private boolean validateMessageBody(HttpServletRequest request,
			Object objRequest, String schema) {

		boolean status = false;

		logger.info("ContentType: " + request.getContentType());

		if (request.getContentType().contains("application/xml")) {

			XmlValidator validator = new XmlValidator();

			status = validator.xmlValidation(objRequest, schema);

		} else if (request.getContentType().contains("application/json")) {

			JsonValidator validator = new JsonValidator();
			
			StringBuffer jb = new StringBuffer();
			String line = null;
			try {
				BufferedReader reader = request.getReader();	
				
				while ((line = reader.readLine()) != null) {
					jb.append(line);
				}
			} catch (Exception e) {
				logger.info("Impossible to get the Json Request! Please check the error using debug mode.");
				logger.debug("Impossible to get the Json Request", e);
			}
			
			status = validator.isValidJSON(jb.toString());

		}

		logger.info("Incoming request Valid:" + status);

		return status;

	}
	
}

