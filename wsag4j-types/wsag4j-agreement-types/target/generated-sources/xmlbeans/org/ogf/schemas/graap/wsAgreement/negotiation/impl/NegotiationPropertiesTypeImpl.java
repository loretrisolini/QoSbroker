/*
 * XML Type:  NegotiationPropertiesType
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationPropertiesType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * An XML NegotiationPropertiesType(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation).
 *
 * This is a complex type.
 */
public class NegotiationPropertiesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationPropertiesType
{
    private static final long serialVersionUID = 1L;
    
    public NegotiationPropertiesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEGOTIATIONCONTEXT$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationContext");
    private static final javax.xml.namespace.QName NEGOTIABLETEMPLATE$2 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiableTemplate");
    private static final javax.xml.namespace.QName NEGOTIATIONOFFER$4 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationOffer");
    
    
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
    
    /**
     * Gets array of all "NegotiableTemplate" elements
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementTemplateType[] getNegotiableTemplateArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(NEGOTIABLETEMPLATE$2, targetList);
            org.ogf.schemas.graap.wsAgreement.AgreementTemplateType[] result = new org.ogf.schemas.graap.wsAgreement.AgreementTemplateType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "NegotiableTemplate" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementTemplateType getNegotiableTemplateArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementTemplateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementTemplateType)get_store().find_element_user(NEGOTIABLETEMPLATE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "NegotiableTemplate" element
     */
    public int sizeOfNegotiableTemplateArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NEGOTIABLETEMPLATE$2);
        }
    }
    
    /**
     * Sets array of all "NegotiableTemplate" element
     */
    public void setNegotiableTemplateArray(org.ogf.schemas.graap.wsAgreement.AgreementTemplateType[] negotiableTemplateArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(negotiableTemplateArray, NEGOTIABLETEMPLATE$2);
        }
    }
    
    /**
     * Sets ith "NegotiableTemplate" element
     */
    public void setNegotiableTemplateArray(int i, org.ogf.schemas.graap.wsAgreement.AgreementTemplateType negotiableTemplate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementTemplateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementTemplateType)get_store().find_element_user(NEGOTIABLETEMPLATE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(negotiableTemplate);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "NegotiableTemplate" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementTemplateType insertNewNegotiableTemplate(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementTemplateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementTemplateType)get_store().insert_element_user(NEGOTIABLETEMPLATE$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "NegotiableTemplate" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementTemplateType addNewNegotiableTemplate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementTemplateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementTemplateType)get_store().add_element_user(NEGOTIABLETEMPLATE$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "NegotiableTemplate" element
     */
    public void removeNegotiableTemplate(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NEGOTIABLETEMPLATE$2, i);
        }
    }
    
    /**
     * Gets array of all "NegotiationOffer" elements
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType[] getNegotiationOfferArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(NEGOTIATIONOFFER$4, targetList);
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
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().find_element_user(NEGOTIATIONOFFER$4, i);
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
            return get_store().count_elements(NEGOTIATIONOFFER$4);
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
            arraySetterHelper(negotiationOfferArray, NEGOTIATIONOFFER$4);
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
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().find_element_user(NEGOTIATIONOFFER$4, i);
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
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().insert_element_user(NEGOTIATIONOFFER$4, i);
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
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType)get_store().add_element_user(NEGOTIATIONOFFER$4);
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
            get_store().remove_element(NEGOTIATIONOFFER$4, i);
        }
    }
}
