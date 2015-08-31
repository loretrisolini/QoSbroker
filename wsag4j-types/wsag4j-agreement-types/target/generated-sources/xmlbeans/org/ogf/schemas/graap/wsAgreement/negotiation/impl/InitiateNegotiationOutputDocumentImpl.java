/*
 * An XML document type.
 * Localname: InitiateNegotiationOutput
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationOutputDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * A document containing one InitiateNegotiationOutput(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation) element.
 *
 * This is a complex type.
 */
public class InitiateNegotiationOutputDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationOutputDocument
{
    private static final long serialVersionUID = 1L;
    
    public InitiateNegotiationOutputDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName INITIATENEGOTIATIONOUTPUT$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "InitiateNegotiationOutput");
    
    
    /**
     * Gets the "InitiateNegotiationOutput" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationOutputType getInitiateNegotiationOutput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationOutputType)get_store().find_element_user(INITIATENEGOTIATIONOUTPUT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "InitiateNegotiationOutput" element
     */
    public void setInitiateNegotiationOutput(org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationOutputType initiateNegotiationOutput)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationOutputType)get_store().find_element_user(INITIATENEGOTIATIONOUTPUT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationOutputType)get_store().add_element_user(INITIATENEGOTIATIONOUTPUT$0);
            }
            target.set(initiateNegotiationOutput);
        }
    }
    
    /**
     * Appends and returns a new empty "InitiateNegotiationOutput" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationOutputType addNewInitiateNegotiationOutput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationOutputType)get_store().add_element_user(INITIATENEGOTIATIONOUTPUT$0);
            return target;
        }
    }
}
