/*
 * An XML document type.
 * Localname: TerminateResponse
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.TerminateResponseDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * A document containing one TerminateResponse(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation) element.
 *
 * This is a complex type.
 */
public class TerminateResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.TerminateResponseDocument
{
    private static final long serialVersionUID = 1L;
    
    public TerminateResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TERMINATERESPONSE$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "TerminateResponse");
    
    
    /**
     * Gets the "TerminateResponse" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.TerminateOutputType getTerminateResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.TerminateOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.TerminateOutputType)get_store().find_element_user(TERMINATERESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TerminateResponse" element
     */
    public void setTerminateResponse(org.ogf.schemas.graap.wsAgreement.negotiation.TerminateOutputType terminateResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.TerminateOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.TerminateOutputType)get_store().find_element_user(TERMINATERESPONSE$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.TerminateOutputType)get_store().add_element_user(TERMINATERESPONSE$0);
            }
            target.set(terminateResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "TerminateResponse" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.TerminateOutputType addNewTerminateResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.TerminateOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.TerminateOutputType)get_store().add_element_user(TERMINATERESPONSE$0);
            return target;
        }
    }
}
