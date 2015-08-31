/*
 * An XML document type.
 * Localname: NegotiationOffer
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * A document containing one NegotiationOffer(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation) element.
 *
 * This is a complex type.
 */
public class NegotiationOfferDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferDocument
{
    private static final long serialVersionUID = 1L;
    
    public NegotiationOfferDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEGOTIATIONOFFER$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationOffer");
    
    
    /**
     * Gets the "NegotiationOffer" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType getNegotiationOffer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().find_element_user(NEGOTIATIONOFFER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NegotiationOffer" element
     */
    public void setNegotiationOffer(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType negotiationOffer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().find_element_user(NEGOTIATIONOFFER$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().add_element_user(NEGOTIATIONOFFER$0);
            }
            target.set(negotiationOffer);
        }
    }
    
    /**
     * Appends and returns a new empty "NegotiationOffer" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType addNewNegotiationOffer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().add_element_user(NEGOTIATIONOFFER$0);
            return target;
        }
    }
}
