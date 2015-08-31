/*
 * An XML document type.
 * Localname: InitiateNegotiationInput
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationInputDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * A document containing one InitiateNegotiationInput(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation) element.
 *
 * This is a complex type.
 */
public class InitiateNegotiationInputDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationInputDocument
{
    private static final long serialVersionUID = 1L;
    
    public InitiateNegotiationInputDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName INITIATENEGOTIATIONINPUT$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "InitiateNegotiationInput");
    
    
    /**
     * Gets the "InitiateNegotiationInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationInputType getInitiateNegotiationInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationInputType)get_store().find_element_user(INITIATENEGOTIATIONINPUT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "InitiateNegotiationInput" element
     */
    public void setInitiateNegotiationInput(org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationInputType initiateNegotiationInput)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationInputType)get_store().find_element_user(INITIATENEGOTIATIONINPUT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationInputType)get_store().add_element_user(INITIATENEGOTIATIONINPUT$0);
            }
            target.set(initiateNegotiationInput);
        }
    }
    
    /**
     * Appends and returns a new empty "InitiateNegotiationInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationInputType addNewInitiateNegotiationInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationInputType)get_store().add_element_user(INITIATENEGOTIATIONINPUT$0);
            return target;
        }
    }
}
