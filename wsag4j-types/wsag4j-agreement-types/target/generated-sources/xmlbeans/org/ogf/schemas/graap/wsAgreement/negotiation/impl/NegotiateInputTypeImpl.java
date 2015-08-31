/*
 * XML Type:  NegotiateInputType
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateInputType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * An XML NegotiateInputType(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation).
 *
 * This is a complex type.
 */
public class NegotiateInputTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiateInputType
{
    private static final long serialVersionUID = 1L;
    
    public NegotiateInputTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEGOTIATIONOFFER$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationOffer");
    
    
    /**
     * Gets array of all "NegotiationOffer" elements
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType[] getNegotiationOfferArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(NEGOTIATIONOFFER$0, targetList);
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType[] result = new org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "NegotiationOffer" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType getNegotiationOfferArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().find_element_user(NEGOTIATIONOFFER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "NegotiationOffer" element
     */
    public int sizeOfNegotiationOfferArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NEGOTIATIONOFFER$0);
        }
    }
    
    /**
     * Sets array of all "NegotiationOffer" element
     */
    public void setNegotiationOfferArray(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType[] negotiationOfferArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(negotiationOfferArray, NEGOTIATIONOFFER$0);
        }
    }
    
    /**
     * Sets ith "NegotiationOffer" element
     */
    public void setNegotiationOfferArray(int i, org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType negotiationOffer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().find_element_user(NEGOTIATIONOFFER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(negotiationOffer);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "NegotiationOffer" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType insertNewNegotiationOffer(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().insert_element_user(NEGOTIATIONOFFER$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "NegotiationOffer" element
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
    
    /**
     * Removes the ith "NegotiationOffer" element
     */
    public void removeNegotiationOffer(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NEGOTIATIONOFFER$0, i);
        }
    }
}
