/*
 * XML Type:  NegotiationOfferContextType
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * An XML NegotiationOfferContextType(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation).
 *
 * This is a complex type.
 */
public class NegotiationOfferContextTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType
{
    private static final long serialVersionUID = 1L;
    
    public NegotiationOfferContextTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COUNTEROFFERTO$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "CounterOfferTo");
    private static final javax.xml.namespace.QName EXPIRATIONTIME$2 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "ExpirationTime");
    private static final javax.xml.namespace.QName CREATOR$4 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "Creator");
    private static final javax.xml.namespace.QName STATE$6 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "State");
    
    
    /**
     * Gets the "CounterOfferTo" element
     */
    public java.lang.String getCounterOfferTo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COUNTEROFFERTO$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "CounterOfferTo" element
     */
    public org.apache.xmlbeans.XmlString xgetCounterOfferTo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COUNTEROFFERTO$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "CounterOfferTo" element
     */
    public void setCounterOfferTo(java.lang.String counterOfferTo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COUNTEROFFERTO$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COUNTEROFFERTO$0);
            }
            target.setStringValue(counterOfferTo);
        }
    }
    
    /**
     * Sets (as xml) the "CounterOfferTo" element
     */
    public void xsetCounterOfferTo(org.apache.xmlbeans.XmlString counterOfferTo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COUNTEROFFERTO$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(COUNTEROFFERTO$0);
            }
            target.set(counterOfferTo);
        }
    }
    
    /**
     * Gets the "ExpirationTime" element
     */
    public java.util.Calendar getExpirationTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EXPIRATIONTIME$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "ExpirationTime" element
     */
    public org.apache.xmlbeans.XmlDateTime xgetExpirationTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(EXPIRATIONTIME$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "ExpirationTime" element
     */
    public boolean isSetExpirationTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(EXPIRATIONTIME$2) != 0;
        }
    }
    
    /**
     * Sets the "ExpirationTime" element
     */
    public void setExpirationTime(java.util.Calendar expirationTime)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EXPIRATIONTIME$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(EXPIRATIONTIME$2);
            }
            target.setCalendarValue(expirationTime);
        }
    }
    
    /**
     * Sets (as xml) the "ExpirationTime" element
     */
    public void xsetExpirationTime(org.apache.xmlbeans.XmlDateTime expirationTime)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(EXPIRATIONTIME$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(EXPIRATIONTIME$2);
            }
            target.set(expirationTime);
        }
    }
    
    /**
     * Unsets the "ExpirationTime" element
     */
    public void unsetExpirationTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(EXPIRATIONTIME$2, 0);
        }
    }
    
    /**
     * Gets the "Creator" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType.Enum getCreator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CREATOR$4, 0);
            if (target == null)
            {
                return null;
            }
            return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "Creator" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType xgetCreator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType)get_store().find_element_user(CREATOR$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Creator" element
     */
    public void setCreator(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType.Enum creator)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CREATOR$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CREATOR$4);
            }
            target.setEnumValue(creator);
        }
    }
    
    /**
     * Sets (as xml) the "Creator" element
     */
    public void xsetCreator(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType creator)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType)get_store().find_element_user(CREATOR$4, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType)get_store().add_element_user(CREATOR$4);
            }
            target.set(creator);
        }
    }
    
    /**
     * Gets the "State" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferStateType getState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferStateType)get_store().find_element_user(STATE$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "State" element
     */
    public void setState(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferStateType state)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferStateType)get_store().find_element_user(STATE$6, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferStateType)get_store().add_element_user(STATE$6);
            }
            target.set(state);
        }
    }
    
    /**
     * Appends and returns a new empty "State" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferStateType addNewState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferStateType)get_store().add_element_user(STATE$6);
            return target;
        }
    }
}
