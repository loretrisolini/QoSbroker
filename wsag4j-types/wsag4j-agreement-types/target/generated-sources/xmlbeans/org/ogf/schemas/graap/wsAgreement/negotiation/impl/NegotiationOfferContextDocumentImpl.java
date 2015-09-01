/*
 * An XML document type.
 * Localname: NegotiationOfferContext
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * A document containing one NegotiationOfferContext(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation) element.
 *
 * This is a complex type.
 */
public class NegotiationOfferContextDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextDocument
{
    private static final long serialVersionUID = 1L;
    
    public NegotiationOfferContextDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEGOTIATIONOFFERCONTEXT$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationOfferContext");
    
    
    /**
     * Gets the "NegotiationOfferContext" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType getNegotiationOfferContext()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType)get_store().find_element_user(NEGOTIATIONOFFERCONTEXT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NegotiationOfferContext" element
     */
    public void setNegotiationOfferContext(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType negotiationOfferContext)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType)get_store().find_element_user(NEGOTIATIONOFFERCONTEXT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType)get_store().add_element_user(NEGOTIATIONOFFERCONTEXT$0);
            }
            target.set(negotiationOfferContext);
        }
    }
    
    /**
     * Appends and returns a new empty "NegotiationOfferContext" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType addNewNegotiationOfferContext()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType)get_store().add_element_user(NEGOTIATIONOFFERCONTEXT$0);
            return target;
        }
    }
}