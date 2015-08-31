/*
 * An XML document type.
 * Localname: NegotiationContext
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * A document containing one NegotiationContext(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation) element.
 *
 * This is a complex type.
 */
public class NegotiationContextDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextDocument
{
    private static final long serialVersionUID = 1L;
    
    public NegotiationContextDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEGOTIATIONCONTEXT$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationContext");
    
    
    /**
     * Gets the "NegotiationContext" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType getNegotiationContext()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType)get_store().find_element_user(NEGOTIATIONCONTEXT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NegotiationContext" element
     */
    public void setNegotiationContext(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType negotiationContext)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType)get_store().find_element_user(NEGOTIATIONCONTEXT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType)get_store().add_element_user(NEGOTIATIONCONTEXT$0);
            }
            target.set(negotiationContext);
        }
    }
    
    /**
     * Appends and returns a new empty "NegotiationContext" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType addNewNegotiationContext()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType)get_store().add_element_user(NEGOTIATIONCONTEXT$0);
            return target;
        }
    }
}
