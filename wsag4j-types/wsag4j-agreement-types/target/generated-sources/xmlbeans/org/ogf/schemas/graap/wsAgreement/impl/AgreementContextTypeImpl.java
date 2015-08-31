/*
 * XML Type:  AgreementContextType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AgreementContextType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML AgreementContextType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class AgreementContextTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.AgreementContextType
{
    private static final long serialVersionUID = 1L;
    
    public AgreementContextTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AGREEMENTINITIATOR$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "AgreementInitiator");
    private static final javax.xml.namespace.QName AGREEMENTRESPONDER$2 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "AgreementResponder");
    private static final javax.xml.namespace.QName SERVICEPROVIDER$4 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ServiceProvider");
    private static final javax.xml.namespace.QName EXPIRATIONTIME$6 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ExpirationTime");
    private static final javax.xml.namespace.QName TEMPLATEID$8 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "TemplateId");
    private static final javax.xml.namespace.QName TEMPLATENAME$10 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "TemplateName");
    
    
    /**
     * Gets the "AgreementInitiator" element
     */
    public org.apache.xmlbeans.XmlObject getAgreementInitiator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(AGREEMENTINITIATOR$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "AgreementInitiator" element
     */
    public boolean isSetAgreementInitiator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(AGREEMENTINITIATOR$0) != 0;
        }
    }
    
    /**
     * Sets the "AgreementInitiator" element
     */
    public void setAgreementInitiator(org.apache.xmlbeans.XmlObject agreementInitiator)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(AGREEMENTINITIATOR$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(AGREEMENTINITIATOR$0);
            }
            target.set(agreementInitiator);
        }
    }
    
    /**
     * Appends and returns a new empty "AgreementInitiator" element
     */
    public org.apache.xmlbeans.XmlObject addNewAgreementInitiator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(AGREEMENTINITIATOR$0);
            return target;
        }
    }
    
    /**
     * Unsets the "AgreementInitiator" element
     */
    public void unsetAgreementInitiator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(AGREEMENTINITIATOR$0, 0);
        }
    }
    
    /**
     * Gets the "AgreementResponder" element
     */
    public org.apache.xmlbeans.XmlObject getAgreementResponder()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(AGREEMENTRESPONDER$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "AgreementResponder" element
     */
    public boolean isSetAgreementResponder()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(AGREEMENTRESPONDER$2) != 0;
        }
    }
    
    /**
     * Sets the "AgreementResponder" element
     */
    public void setAgreementResponder(org.apache.xmlbeans.XmlObject agreementResponder)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(AGREEMENTRESPONDER$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(AGREEMENTRESPONDER$2);
            }
            target.set(agreementResponder);
        }
    }
    
    /**
     * Appends and returns a new empty "AgreementResponder" element
     */
    public org.apache.xmlbeans.XmlObject addNewAgreementResponder()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(AGREEMENTRESPONDER$2);
            return target;
        }
    }
    
    /**
     * Unsets the "AgreementResponder" element
     */
    public void unsetAgreementResponder()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(AGREEMENTRESPONDER$2, 0);
        }
    }
    
    /**
     * Gets the "ServiceProvider" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementRoleType.Enum getServiceProvider()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVICEPROVIDER$4, 0);
            if (target == null)
            {
                return null;
            }
            return (org.ogf.schemas.graap.wsAgreement.AgreementRoleType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "ServiceProvider" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementRoleType xgetServiceProvider()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementRoleType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementRoleType)get_store().find_element_user(SERVICEPROVIDER$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "ServiceProvider" element
     */
    public void setServiceProvider(org.ogf.schemas.graap.wsAgreement.AgreementRoleType.Enum serviceProvider)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVICEPROVIDER$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SERVICEPROVIDER$4);
            }
            target.setEnumValue(serviceProvider);
        }
    }
    
    /**
     * Sets (as xml) the "ServiceProvider" element
     */
    public void xsetServiceProvider(org.ogf.schemas.graap.wsAgreement.AgreementRoleType serviceProvider)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementRoleType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementRoleType)get_store().find_element_user(SERVICEPROVIDER$4, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementRoleType)get_store().add_element_user(SERVICEPROVIDER$4);
            }
            target.set(serviceProvider);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EXPIRATIONTIME$6, 0);
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
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(EXPIRATIONTIME$6, 0);
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
            return get_store().count_elements(EXPIRATIONTIME$6) != 0;
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EXPIRATIONTIME$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(EXPIRATIONTIME$6);
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
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(EXPIRATIONTIME$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(EXPIRATIONTIME$6);
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
            get_store().remove_element(EXPIRATIONTIME$6, 0);
        }
    }
    
    /**
     * Gets the "TemplateId" element
     */
    public java.lang.String getTemplateId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEMPLATEID$8, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "TemplateId" element
     */
    public org.apache.xmlbeans.XmlString xgetTemplateId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEMPLATEID$8, 0);
            return target;
        }
    }
    
    /**
     * True if has "TemplateId" element
     */
    public boolean isSetTemplateId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(TEMPLATEID$8) != 0;
        }
    }
    
    /**
     * Sets the "TemplateId" element
     */
    public void setTemplateId(java.lang.String templateId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEMPLATEID$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TEMPLATEID$8);
            }
            target.setStringValue(templateId);
        }
    }
    
    /**
     * Sets (as xml) the "TemplateId" element
     */
    public void xsetTemplateId(org.apache.xmlbeans.XmlString templateId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEMPLATEID$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TEMPLATEID$8);
            }
            target.set(templateId);
        }
    }
    
    /**
     * Unsets the "TemplateId" element
     */
    public void unsetTemplateId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(TEMPLATEID$8, 0);
        }
    }
    
    /**
     * Gets the "TemplateName" element
     */
    public java.lang.String getTemplateName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEMPLATENAME$10, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "TemplateName" element
     */
    public org.apache.xmlbeans.XmlString xgetTemplateName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEMPLATENAME$10, 0);
            return target;
        }
    }
    
    /**
     * True if has "TemplateName" element
     */
    public boolean isSetTemplateName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(TEMPLATENAME$10) != 0;
        }
    }
    
    /**
     * Sets the "TemplateName" element
     */
    public void setTemplateName(java.lang.String templateName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TEMPLATENAME$10, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TEMPLATENAME$10);
            }
            target.setStringValue(templateName);
        }
    }
    
    /**
     * Sets (as xml) the "TemplateName" element
     */
    public void xsetTemplateName(org.apache.xmlbeans.XmlString templateName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TEMPLATENAME$10, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TEMPLATENAME$10);
            }
            target.set(templateName);
        }
    }
    
    /**
     * Unsets the "TemplateName" element
     */
    public void unsetTemplateName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(TEMPLATENAME$10, 0);
        }
    }
}
