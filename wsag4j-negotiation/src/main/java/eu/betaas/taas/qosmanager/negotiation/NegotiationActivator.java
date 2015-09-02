// BETaaS - Building the Environment for the Things as a Service
//
// Component: TaaS QoS Manager
// Responsible: Carlo Vallati & Giacomo Tanganelli

package eu.betaas.taas.qosmanager.negotiation;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;
import org.ogf.graap.wsag.api.exceptions.AgreementFactoryException;
import org.ogf.graap.wsag.api.exceptions.NegotiationFactoryException;
import org.ogf.graap.wsag.samples.Sample1CreateAgreementAction;
import org.ogf.graap.wsag.samples.SampleAgreement;
//import org.ogf.graap.wsag.client.api.local.LocalAgreementFactoryClient;
import org.ogf.graap.wsag.server.actions.INegotiationAction;
//import org.ogf.graap.wsag.server.actions.Sample1NegotiateAgreementAction;
//import org.ogf.graap.wsag.server.actions.Sample2NegotiateAgreementAction;
import org.ogf.graap.wsag.server.actions.impl.AgreementFactoryAction;
import org.ogf.graap.wsag.server.actions.impl.NegotiationUnsupportedAction;
import org.ogf.schemas.graap.wsAgreement.AgreementTemplateType;
import org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType;
import org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintSectionType;
import org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType;
import org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType;
import org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferStateType;
import org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType;
import org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType;
import org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineConfigurationDocument;
import org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineConfigurationType;
import org.ogf.graap.wsag.api.Agreement;
//import org.ggf.schemas.jsdl.x2005.x11.jsdl.JobDefinitionDocument;
//import org.ggf.schemas.jsdl.x2005.x11.jsdl.JobDefinitionType;
import org.ogf.graap.wsag.api.AgreementFactory;
import org.ogf.graap.wsag.api.Negotiation;
import org.ogf.graap.wsag.server.engine.EngineInstantiationException;
import org.ogf.graap.wsag.server.engine.WsagEngine;
import org.ogf.graap.wsag.server.persistence.impl.PersistentAgreementContainer;
import org.ogf.graap.wsag.api.AgreementOffer;
import org.ogf.graap.wsag.api.types.AgreementOfferType;
import org.ogf.schemas.graap.wsAgreement.ServiceTermStateType;

//import eu.betaas.taas.bigdatamanager.database.service.IBigDataDatabaseService;


/**
* Activator class for the QoSManager into the TaaS layer
**/

public class NegotiationActivator {
	private static Logger LOG = Logger.getLogger("betaas");;
	
    private static final Map<String, Object> DEFAULT_ENVIRONMENT = new HashMap<String, Object>();

    private static final XmlObject[] DEFAULT_CRITICAL_EXT = new XmlObject[0];

    private static final XmlObject[] DEFAULT_NON_CRITICAL_EXT = new XmlObject[0];	
//    public static IBigDataDatabaseService service;
    public static BundleContext context;
    private WsagEngine engine;
	
    private WSAG4JEngineConfigurationType loadEngineConfiguration(BundleContext context) throws Exception
    {
        WSAG4JEngineConfigurationType wsag4JEngineConfigurationType = null;
        final String configFile = "wsag4j-engine.config";

            InputStream in = getClass().getResourceAsStream( configFile );
            
            /*BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String inputLine;
            while ((inputLine = br.readLine()) != null)
                System.out.println(inputLine);*/
            
        	/*URL res = context.getBundle().getResource(configFile);
        	InputStream in = res.openConnection().getInputStream();*/
            
            //System.out.println(in);
            
            //WSAG4JEngineConfigurationDocument engineConfigurationDocumentn = 
            //        (WSAG4JEngineConfigurationDocument) XmlObject.Factory.parse( in );
              
            //WSAG4JEngineConfigurationDocument engineConfigurationDocumentn = WSAG4JEngineConfigurationDocument.Factory.newInstance();
            //System.out.println("Created");
            
            WSAG4JEngineConfigurationDocument engineConfigurationDocumentn = WSAG4JEngineConfigurationDocument.Factory.parse(in);
            
            wsag4JEngineConfigurationType = engineConfigurationDocumentn.getWSAG4JEngineConfiguration();
            
        return wsag4JEngineConfigurationType;
    }
    
    private AgreementFactory getEngine( BundleContext arg0 ) throws Exception
    {
        // initialize engine
        WSAG4JEngineConfigurationType wsag4JConfiguration = loadEngineConfiguration(arg0);
        LOG.debug( "Engine configuration loaded." );

        
        try
        {
            engine = WsagEngine.getInstance( wsag4JConfiguration );
            if(engine==null)
            	System.out.println( "failed to instantiate engine" );
            //assertNotNull( engine );
        }
        catch ( EngineInstantiationException e )
        {
        	System.out.println( "failed to instantiate engine" );

            // call above raises an error, so we will never reach this point
            return null;
        }

        try
        {
        	System.out.println( engine.list()[0] );
            //assertEquals( 1, engine.list().length );
            return engine.list()[0];
        }
        catch ( Exception e )
        {
        	System.out.println( "failed to retrieve agreement factory" );

            // call above raises an error, so we will never reach this point
            return null;
        }
    }
    
    
    /*private Negotiation getNegotiationInstance( AgreementFactory factory, XmlObject[] critical,
            XmlObject[] nonCritical, Map<String, Object> environment )
            		throws Exception
    {
		NegotiationContextType negotiationContextType = NegotiationContextType.Factory.newInstance();
		negotiationContextType.addNewNegotiationType().addNewNegotiation();
		
		Negotiation negotiation =
		factory.initiateNegotiation( negotiationContextType, critical, nonCritical, environment );
		//assertNotNull( negotiation );
		
		if ( LOG.isDebugEnabled() )
		{
		LOG.debug( "Negotiation process initiated." );
		}
		
		return negotiation;
	}*/
    
    private AgreementTemplateType getTemplateWithRequiredTerm( AgreementFactory factory, String templateName )
    {
        //LocalAgreementFactoryClient factoryClient = new LocalAgreementFactoryClient( factory );
    	
    	AgreementTemplateType[] templates = factory.getTemplates();
    	
        AgreementTemplateType agreementTemplateType = null;
        //System.out.println(templates.length);
        for( int i = 0; i < templates.length; i++ ){
        	//System.out.println("TEMPLATE " + templates[i].getName());
        	if( templates[i].getName().equals(templateName)){
        		agreementTemplateType = templates[i];
        		break;
        	}
        }

        return agreementTemplateType;
    }
    
   /* private NegotiationOfferType buildNegotiationOfferType( AgreementTemplateType agreementTemplate )
    {
        NegotiationOfferType negotiationOfferType = NegotiationOfferType.Factory.newInstance();
        negotiationOfferType.setName( agreementTemplate.getContext().getTemplateName() );
        negotiationOfferType.setAgreementId( agreementTemplate.getContext().getTemplateId() );
        negotiationOfferType.addNewContext().set( agreementTemplate.getContext() );
        negotiationOfferType.addNewTerms().set( agreementTemplate.getTerms() );

        NegotiationOfferContextType negOfferContext = NegotiationOfferContextType.Factory.newInstance();
        negOfferContext.setExpirationTime( Calendar.getInstance() );
        negOfferContext.setCreator( NegotiationRoleType.NEGOTIATION_INITIATOR );
        NegotiationOfferStateType negOfferState = NegotiationOfferStateType.Factory.newInstance();
        negOfferState.addNewAdvisory();
        negOfferContext.setState( negOfferState );

        String templateId = agreementTemplate.getTemplateId() + "-" + agreementTemplate.getName();
        negOfferContext.setCounterOfferTo( templateId );

        NegotiationConstraintSectionType constraints = NegotiationConstraintSectionType.Factory.newInstance();
        constraints.addNewConstraint();

        negotiationOfferType.setNegotiationOfferContext( negOfferContext );
        negotiationOfferType.setNegotiationConstraints( constraints );
        negotiationOfferType.setOfferId( "Original_offer_ID" );

        return negotiationOfferType;
    }*/

   
    
	public void start() throws Exception {
//		System.out.println("TaaS QoS Manager started");
//		LOG.debug("TaaS QoS Manager started");
		
		/*ServiceReference reference = arg0.getServiceReference(IBigDataDatabaseService.class.getName());
        IBigDataDatabaseService service = (IBigDataDatabaseService) arg0.getService(reference);*/
//        LOG.error("Activator: service "+ service);
//        EntityManager em = service.getEntityManager(); 
        
        
    	/*LOG.error("before query");
        Query query = em.createQuery( "SELECT p FROM PersistentAgreementContainer p "
                     + "WHERE p.agreementFactoryId = :agreementFactoryId" );
        LOG.error("after query");
        query.setParameter( "agreementFactoryId", "pippo" );
        LOG.error("after setParameter");*/
       
        //
        // retrieve a template and build offer document
        //

        AgreementFactory genericAgreementFactory = getEngine(context);
        
        
        
        //
        // get negotiation instance
        //

        /*Map<String, Object> environment = new HashMap<String, Object>();
        XmlObject[] critical = new XmlObject[0];
        XmlObject[] nonCritical = new XmlObject[0];
        Negotiation negotiation = getNegotiationInstance( genericAgreementFactory, critical, nonCritical, environment );*/

        //
        // retrieve a template
        //

        
        AgreementTemplateType agreementTemplate = getTemplateWithRequiredTerm( genericAgreementFactory, "BETaaS-Template" );
        String template= agreementTemplate.toString();
        String tmp=template.replace("$TRANSACTIONID","new_transactionID");
        template=tmp.replace("$MAXRESPONSETIME","20");
        tmp=template.replace("$MINAVAILABILITY","80");
        template=tmp.replace("$MAXINTERREQUESTTIME","50");
        
        
        agreementTemplate = AgreementTemplateType.Factory.parse(template);
        //assertEquals( "WSAG4J-NEGOTIATION-SAMPLE-1", agreementTemplate.getName() );
        System.out.println(agreementTemplate);
        
        //
        // invoke negotiation (generate negotiation offer type, receive counter offers, etc.)
        //
        
        /*NegotiationOfferType negotiationOfferType = buildNegotiationOfferType( agreementTemplate );
        NegotiationOfferType[] negotiationOfferTypes = { negotiationOfferType };*/
        AgreementOffer offer = new AgreementOfferType(agreementTemplate);
        
        Agreement agreement = genericAgreementFactory.createAgreement(offer, DEFAULT_ENVIRONMENT);
        
        /*Agreement agreement = sample.createAgreement(offer);
        Agreement agreement2 = sample.createAgreement(offer);*/
 
        //Sample1CreateAgreementAction sample = new Sample1CreateAgreementAction();
        
        System.out.println("AGREEMENT CREATED " + agreement.getAgreementId() + " " + agreement.getState());
        LOG.info("AGREEMENT CREATED " + agreement.getAgreementId() + " " + agreement.getState());
        
        //System.out.println(agreement2.getAgreementId());
       /* ServiceTermStateType[] states = agreement.getServiceTermStates();
        for( int i = 0; i < states.length; i++ ){
        	System.out.println(states[i]);
        }*/
        
        //System.out.println(agreement.getServiceTermStates());
       // LOG.info(agreement);
                
        //engine.shutdown();
        
        /*agreement.terminate(TerminateInputType.Factory.newInstance());
        LOG.error(agreement.getState());
        System.out.println("ALL OK2");*/
       /* NegotiationOfferType[] counterOffers = negotiation.negotiate( negotiationOfferTypes, nonCritical );
        NegotiationOfferType counterOffer = counterOffers[0];
*/

        //assertEquals( "1", counterOffer.getContext().getTemplateId() );
        //assertEquals( "WSAG4J-NEGOTIATION-SAMPLE-1", counterOffer.getContext().getTemplateName() );

        // default state for Sample1NegotiationException is "advisory"
        //assertTrue( counterOffer.getNegotiationOfferContext().getState().isSetAdvisory() );
        //assertFalse( counterOffer.getNegotiationOfferContext().getState().isSetAcceptable() );
        //assertFalse( counterOffer.getNegotiationOfferContext().getState().isSetRejected() );
        //assertFalse( counterOffer.getNegotiationOfferContext().getState().isSetSolicited() );*/
	}

	public void stop(BundleContext arg0) throws Exception {
		engine.shutdown();
		System.out.println("TaaS QoS Manager stopped CARLO");
		LOG.debug("TaaS QoS Manager stopped");
		
	}
//	public void setService(IBigDataDatabaseService service) throws SQLException {
//		// TODO Auto-generated method stub
//		this.service = service;
//		//this.setConnection(service.getConnection());
//	}
	public void setContext(BundleContext context) {
		// TODO Auto-generated method stub
		this.context = context;
		//this.setConnection(service.getConnection());
	}
	 public Agreement createAgreement( AgreementOffer offer ) throws AgreementFactoryException
    {
        //
        // First, check the validity of the offer
        //
        /*if ( ( offer.getTerms() == null ) || ( offer.getTerms().getAll() == null ) )
        {

            throw new AgreementFactoryException( "Offer does not contain any terms." );

        }

        //
        // Then, evaluate the service description terms.
        //
        ServiceDescriptionTermType[] sdts = offer.getTerms().getAll().getServiceDescriptionTermArray();
        if ( ( sdts != null ) && ( sdts.length == 1 ) && ( sdts[0].getName().equals( "TEST_EXCEPTION" ) ) )
        {

            // e.g. if a SDT named TEST_EXCEPTION was found, fire the Exception
            throw new AgreementFactoryException( "No ServiceDescriptionTerms defined." );
        }*/

        //
        // Implement the SLA specific logic, e.g. reserve resources and start a service
        //
        // myResources.reserve();
        // myServer.start();
        //

        //
        // At last, create an agreement instance and return it
        //
        SampleAgreement agreement = new SampleAgreement(offer);

        /*agreement.setContext( offer.getContext() );
        agreement.setTerms( offer.getTerms() );*/

        return agreement;
    }

}
