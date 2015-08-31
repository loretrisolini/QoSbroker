/*
 * An XML document type.
 * Localname: NegotiateInput
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateInputDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * A document containing one NegotiateInput(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation) element.
 *
 * This is a complex type.
 */
public class NegotiateInputDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateInputDocument
{
    private static final long serialVersionUID = 1L;
    
    public NegotiateInputDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEGOTIATEINPUT$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiateInput");
    
    
    /**
     * Gets the "NegotiateInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateInputType getNegotiateInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateInputType)get_store().find_element_user(NEGOTIATEINPUT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NegotiateInput" element
     */
    public void setNegotiateInput(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateInputType negotiateInput)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateInputType)get_store().find_element_user(NEGOTIATEINPUT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateInputType)get_store().add_element_user(NEGOTIATEINPUT$0);
            }
            target.set(negotiateInput);
        }
    }
    
    /**
     * Appends and returns a new empty "NegotiateInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateInputType addNewNegotiateInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateInputType)get_store().add_element_user(NEGOTIATEINPUT$0);
            return target;
        }
    }
}
