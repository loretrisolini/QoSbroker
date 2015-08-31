/*
 * XML Type:  NegotiateOutputType
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateOutputType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * An XML NegotiateOutputType(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation).
 *
 * This is a complex type.
 */
public class NegotiateOutputTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateOutputType
{
    private static final long serialVersionUID = 1L;
    
    public NegotiateOutputTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEGOTIATIONCOUNTEROFFER$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationCounterOffer");
    
    
    /**
     * Gets array of all "NegotiationCounterOffer" elements
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType[] getNegotiationCounterOfferArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(NEGOTIATIONCOUNTEROFFER$0, targetList);
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType[] result = new org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "NegotiationCounterOffer" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType getNegotiationCounterOfferArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().find_element_user(NEGOTIATIONCOUNTEROFFER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "NegotiationCounterOffer" element
     */
    public int sizeOfNegotiationCounterOfferArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NEGOTIATIONCOUNTEROFFER$0);
        }
    }
    
    /**
     * Sets array of all "NegotiationCounterOffer" element
     */
    public void setNegotiationCounterOfferArray(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType[] negotiationCounterOfferArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(negotiationCounterOfferArray, NEGOTIATIONCOUNTEROFFER$0);
        }
    }
    
    /**
     * Sets ith "NegotiationCounterOffer" element
     */
    public void setNegotiationCounterOfferArray(int i, org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType negotiationCounterOffer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().find_element_user(NEGOTIATIONCOUNTEROFFER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(negotiationCounterOffer);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "NegotiationCounterOffer" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType insertNewNegotiationCounterOffer(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().insert_element_user(NEGOTIATIONCOUNTEROFFER$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "NegotiationCounterOffer" element
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
    
    /**
     * Removes the ith "NegotiationCounterOffer" element
     */
    public void removeNegotiationCounterOffer(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NEGOTIATIONCOUNTEROFFER$0, i);
        }
    }
}
