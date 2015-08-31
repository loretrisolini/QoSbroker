/*
 * An XML document type.
 * Localname: NegotiationCounterOffer
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationCounterOfferDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * A document containing one NegotiationCounterOffer(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation) element.
 *
 * This is a complex type.
 */
public class NegotiationCounterOfferDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationCounterOfferDocument
{
    private static final long serialVersionUID = 1L;
    
    public NegotiationCounterOfferDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEGOTIATIONCOUNTEROFFER$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationCounterOffer");
    
    
    /**
     * Gets the "NegotiationCounterOffer" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType getNegotiationCounterOffer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().find_element_user(NEGOTIATIONCOUNTEROFFER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NegotiationCounterOffer" element
     */
    public void setNegotiationCounterOffer(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType negotiationCounterOffer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().find_element_user(NEGOTIATIONCOUNTEROFFER$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().add_element_user(NEGOTIATIONCOUNTEROFFER$0);
            }
            target.set(negotiationCounterOffer);
        }
    }
    
    /**
     * Appends and returns a new empty "NegotiationCounterOffer" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType addNewNegotiationCounterOffer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().add_element_user(NEGOTIATIONCOUNTEROFFER$0);
            return target;
        }
    }
}
