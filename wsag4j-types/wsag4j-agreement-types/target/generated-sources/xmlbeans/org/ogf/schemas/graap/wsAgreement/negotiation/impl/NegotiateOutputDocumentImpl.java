/*
 * An XML document type.
 * Localname: NegotiateOutput
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateOutputDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * A document containing one NegotiateOutput(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation) element.
 *
 * This is a complex type.
 */
public class NegotiateOutputDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateOutputDocument
{
    private static final long serialVersionUID = 1L;
    
    public NegotiateOutputDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEGOTIATEOUTPUT$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiateOutput");
    
    
    /**
     * Gets the "NegotiateOutput" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateOutputType getNegotiateOutput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateOutputType)get_store().find_element_user(NEGOTIATEOUTPUT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NegotiateOutput" element
     */
    public void setNegotiateOutput(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateOutputType negotiateOutput)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateOutputType)get_store().find_element_user(NEGOTIATEOUTPUT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateOutputType)get_store().add_element_user(NEGOTIATEOUTPUT$0);
            }
            target.set(negotiateOutput);
        }
    }
    
    /**
     * Appends and returns a new empty "NegotiateOutput" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateOutputType addNewNegotiateOutput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateOutputType)get_store().add_element_user(NEGOTIATEOUTPUT$0);
            return target;
        }
    }
}
