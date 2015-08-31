/*
 * XML Type:  NegotiationContextType
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * An XML NegotiationContextType(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation).
 *
 * This is a complex type.
 */
public class NegotiationContextTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType
{
    private static final long serialVersionUID = 1L;
    
    public NegotiationContextTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEGOTIATIONTYPE$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationType");
    private static final javax.xml.namespace.QName EXPIRATIONTIME$2 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "ExpirationTime");
    private static final javax.xml.namespace.QName NEGOTIATIONINITIATOR$4 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationInitiator");
    private static final javax.xml.namespace.QName NEGOTIATIONRESPONDER$6 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationResponder");
    private static final javax.xml.namespace.QName AGREEMENTRESPONDER$8 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "AgreementResponder");
    private static final javax.xml.namespace.QName AGREEMENTFACTORYEPR$10 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "AgreementFactoryEPR");
    
    
    /**
     * Gets the "NegotiationType" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType getNegotiationType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType)get_store().find_element_user(NEGOTIATIONTYPE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NegotiationType" element
     */
    public void setNegotiationType(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType negotiationType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType)get_store().find_element_user(NEGOTIATIONTYPE$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType)get_store().add_element_user(NEGOTIATIONTYPE$0);
            }
            target.set(negotiationType);
        }
    }
    
    /**
     * Appends and returns a new empty "NegotiationType" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType addNewNegotiationType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType)get_store().add_element_user(NEGOTIATIONTYPE$0);
            return target;
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
     * Gets the "NegotiationInitiator" element
     */
    public org.apache.xmlbeans.XmlObject getNegotiationInitiator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(NEGOTIATIONINITIATOR$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "NegotiationInitiator" element
     */
    public boolean isSetNegotiationInitiator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NEGOTIATIONINITIATOR$4) != 0;
        }
    }
    
    /**
     * Sets the "NegotiationInitiator" element
     */
    public void setNegotiationInitiator(org.apache.xmlbeans.XmlObject negotiationInitiator)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(NEGOTIATIONINITIATOR$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(NEGOTIATIONINITIATOR$4);
            }
            target.set(negotiationInitiator);
        }
    }
    
    /**
     * Appends and returns a new empty "NegotiationInitiator" element
     */
    public org.apache.xmlbeans.XmlObject addNewNegotiationInitiator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(NEGOTIATIONINITIATOR$4);
            return target;
        }
    }
    
    /**
     * Unsets the "NegotiationInitiator" element
     */
    public void unsetNegotiationInitiator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NEGOTIATIONINITIATOR$4, 0);
        }
    }
    
    /**
     * Gets the "NegotiationResponder" element
     */
    public org.apache.xmlbeans.XmlObject getNegotiationResponder()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(NEGOTIATIONRESPONDER$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "NegotiationResponder" element
     */
    public boolean isSetNegotiationResponder()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NEGOTIATIONRESPONDER$6) != 0;
        }
    }
    
    /**
     * Sets the "NegotiationResponder" element
     */
    public void setNegotiationResponder(org.apache.xmlbeans.XmlObject negotiationResponder)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(NEGOTIATIONRESPONDER$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(NEGOTIATIONRESPONDER$6);
            }
            target.set(negotiationResponder);
        }
    }
    
    /**
     * Appends and returns a new empty "NegotiationResponder" element
     */
    public org.apache.xmlbeans.XmlObject addNewNegotiationResponder()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(NEGOTIATIONRESPONDER$6);
            return target;
        }
    }
    
    /**
     * Unsets the "NegotiationResponder" element
     */
    public void unsetNegotiationResponder()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NEGOTIATIONRESPONDER$6, 0);
        }
    }
    
    /**
     * Gets the "AgreementResponder" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType.Enum getAgreementResponder()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AGREEMENTRESPONDER$8, 0);
            if (target == null)
            {
                return null;
            }
            return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "AgreementResponder" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType xgetAgreementResponder()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType)get_store().find_element_user(AGREEMENTRESPONDER$8, 0);
            return target;
        }
    }
    
    /**
     * Sets the "AgreementResponder" element
     */
    public void setAgreementResponder(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType.Enum agreementResponder)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AGREEMENTRESPONDER$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(AGREEMENTRESPONDER$8);
            }
            target.setEnumValue(agreementResponder);
        }
    }
    
    /**
     * Sets (as xml) the "AgreementResponder" element
     */
    public void xsetAgreementResponder(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType agreementResponder)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType)get_store().find_element_user(AGREEMENTRESPONDER$8, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType)get_store().add_element_user(AGREEMENTRESPONDER$8);
            }
            target.set(agreementResponder);
        }
    }
    
    /**
     * Gets the "AgreementFactoryEPR" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType getAgreementFactoryEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(AGREEMENTFACTORYEPR$10, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "AgreementFactoryEPR" element
     */
    public boolean isSetAgreementFactoryEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(AGREEMENTFACTORYEPR$10) != 0;
        }
    }
    
    /**
     * Sets the "AgreementFactoryEPR" element
     */
    public void setAgreementFactoryEPR(org.w3.x2005.x08.addressing.EndpointReferenceType agreementFactoryEPR)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(AGREEMENTFACTORYEPR$10, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(AGREEMENTFACTORYEPR$10);
            }
            target.set(agreementFactoryEPR);
        }
    }
    
    /**
     * Appends and returns a new empty "AgreementFactoryEPR" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType addNewAgreementFactoryEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(AGREEMENTFACTORYEPR$10);
            return target;
        }
    }
    
    /**
     * Unsets the "AgreementFactoryEPR" element
     */
    public void unsetAgreementFactoryEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(AGREEMENTFACTORYEPR$10, 0);
        }
    }
}
