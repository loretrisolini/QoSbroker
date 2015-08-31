/*
 * XML Type:  NegotiationOfferStateType
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferStateType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * An XML NegotiationOfferStateType(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation).
 *
 * This is a complex type.
 */
public class NegotiationOfferStateTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferStateType
{
    private static final long serialVersionUID = 1L;
    
    public NegotiationOfferStateTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ADVISORY$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "Advisory");
    private static final javax.xml.namespace.QName SOLICITED$2 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "Solicited");
    private static final javax.xml.namespace.QName ACCEPTABLE$4 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "Acceptable");
    private static final javax.xml.namespace.QName REJECTED$6 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "Rejected");
    
    
    /**
     * Gets the "Advisory" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType getAdvisory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType)get_store().find_element_user(ADVISORY$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Advisory" element
     */
    public boolean isSetAdvisory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ADVISORY$0) != 0;
        }
    }
    
    /**
     * Sets the "Advisory" element
     */
    public void setAdvisory(org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType advisory)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType)get_store().find_element_user(ADVISORY$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType)get_store().add_element_user(ADVISORY$0);
            }
            target.set(advisory);
        }
    }
    
    /**
     * Appends and returns a new empty "Advisory" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType addNewAdvisory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType)get_store().add_element_user(ADVISORY$0);
            return target;
        }
    }
    
    /**
     * Unsets the "Advisory" element
     */
    public void unsetAdvisory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ADVISORY$0, 0);
        }
    }
    
    /**
     * Gets the "Solicited" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType getSolicited()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType)get_store().find_element_user(SOLICITED$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Solicited" element
     */
    public boolean isSetSolicited()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SOLICITED$2) != 0;
        }
    }
    
    /**
     * Sets the "Solicited" element
     */
    public void setSolicited(org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType solicited)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType)get_store().find_element_user(SOLICITED$2, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType)get_store().add_element_user(SOLICITED$2);
            }
            target.set(solicited);
        }
    }
    
    /**
     * Appends and returns a new empty "Solicited" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType addNewSolicited()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType)get_store().add_element_user(SOLICITED$2);
            return target;
        }
    }
    
    /**
     * Unsets the "Solicited" element
     */
    public void unsetSolicited()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SOLICITED$2, 0);
        }
    }
    
    /**
     * Gets the "Acceptable" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType getAcceptable()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType)get_store().find_element_user(ACCEPTABLE$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Acceptable" element
     */
    public boolean isSetAcceptable()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ACCEPTABLE$4) != 0;
        }
    }
    
    /**
     * Sets the "Acceptable" element
     */
    public void setAcceptable(org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType acceptable)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType)get_store().find_element_user(ACCEPTABLE$4, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType)get_store().add_element_user(ACCEPTABLE$4);
            }
            target.set(acceptable);
        }
    }
    
    /**
     * Appends and returns a new empty "Acceptable" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType addNewAcceptable()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType)get_store().add_element_user(ACCEPTABLE$4);
            return target;
        }
    }
    
    /**
     * Unsets the "Acceptable" element
     */
    public void unsetAcceptable()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ACCEPTABLE$4, 0);
        }
    }
    
    /**
     * Gets the "Rejected" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType getRejected()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType)get_store().find_element_user(REJECTED$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Rejected" element
     */
    public boolean isSetRejected()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(REJECTED$6) != 0;
        }
    }
    
    /**
     * Sets the "Rejected" element
     */
    public void setRejected(org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType rejected)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType)get_store().find_element_user(REJECTED$6, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType)get_store().add_element_user(REJECTED$6);
            }
            target.set(rejected);
        }
    }
    
    /**
     * Appends and returns a new empty "Rejected" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType addNewRejected()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.InnerNegotiationStateType)get_store().add_element_user(REJECTED$6);
            return target;
        }
    }
    
    /**
     * Unsets the "Rejected" element
     */
    public void unsetRejected()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(REJECTED$6, 0);
        }
    }
}
