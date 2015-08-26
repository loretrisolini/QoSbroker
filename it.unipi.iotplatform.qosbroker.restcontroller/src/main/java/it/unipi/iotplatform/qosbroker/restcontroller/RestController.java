package it.unipi.iotplatform.qosbroker.restcontroller;

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

import eu.neclab.iotplatform.ngsi.api.datamodel.QueryContextRequest;
import eu.neclab.iotplatform.ngsi.api.ngsi10.Ngsi10Interface;
import eu.neclab.iotplatform.ngsi.api.ngsi9.Ngsi9Interface;
import it.unipi.iotplatform.qosbroker.restcontroller.sanitycheck.SanityCheck;
/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/qos")
public class RestController {
	
	private static final Logger logger =Logger.getLogger(RestController.class);
	
	/** String representing json content type. */
	private final String CONTENT_TYPE_JSON = "application/json";

	/** String representing xml content type. */
	private final String CONTENT_TYPE_XML = "application/xml";
	
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
	
	/**
	 * Executes the Sanity Check Procedure of the IoT Broker.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/QoSbroker/sanityCheck", method = RequestMethod.GET, consumes = { "*/*" }, produces = {
			CONTENT_TYPE_XML, CONTENT_TYPE_JSON })
	public ResponseEntity<SanityCheck> sanityCheck() {

		
		
		BundleContext bc = FrameworkUtil
				.getBundle(RestController.class).getBundleContext();

		SanityCheck response = new SanityCheck("IoT Broker GE", "Sanity Check",
				"Version: " + bc.getBundle().getVersion());

		logger.info("/QoSbroker/sanityCheck"+response.toString());
		
		return new ResponseEntity<SanityCheck>(response, HttpStatus.OK);

	}
	
	/**
	 * Executes the test of the IoT Broker, which simply returns the request
	 * message.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/QoSbroker/test", method = RequestMethod.POST, consumes = { "*/*" }, produces = {
			CONTENT_TYPE_XML, CONTENT_TYPE_JSON })
	public ResponseEntity<QueryContextRequest> test(
			HttpServletRequest requester,
			@RequestBody QueryContextRequest request) {

		System.out.println(request);

		logger.info("/QoSbroker/sanityCheck"+request);
		
		return new ResponseEntity<QueryContextRequest>(request, HttpStatus.OK);

	}
	
}

