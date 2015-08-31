/*
 * XML Type:  ActionType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.ActionType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * An XML ActionType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public class ActionTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.ActionType
{
    private static final long serialVersionUID = 1L;
    
    public ActionTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CREATEAGREEMENTCONFIGURATION$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "CreateAgreementConfiguration");
    private static final javax.xml.namespace.QName GETTEMPLATECONFIGURATION$2 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "GetTemplateConfiguration");
    private static final javax.xml.namespace.QName NEGOTIATIONCONFIGURATION$4 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "NegotiationConfiguration");
    private static final javax.xml.namespace.QName NAME$6 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "name");
    private static final javax.xml.namespace.QName USESESSION$8 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "useSession");
    
    
    /**
     * Gets the "CreateAgreementConfiguration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType getCreateAgreementConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().find_element_user(CREATEAGREEMENTCONFIGURATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CreateAgreementConfiguration" element
     */
    public void setCreateAgreementConfiguration(org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType createAgreementConfiguration)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().find_element_user(CREATEAGREEMENTCONFIGURATION$0, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().add_element_user(CREATEAGREEMENTCONFIGURATION$0);
            }
            target.set(createAgreementConfiguration);
        }
    }
    
    /**
     * Appends and returns a new empty "CreateAgreementConfiguration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType addNewCreateAgreementConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().add_element_user(CREATEAGREEMENTCONFIGURATION$0);
            return target;
        }
    }
    
    /**
     * Gets the "GetTemplateConfiguration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType getGetTemplateConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().find_element_user(GETTEMPLATECONFIGURATION$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "GetTemplateConfiguration" element
     */
    public void setGetTemplateConfiguration(org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType getTemplateConfiguration)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().find_element_user(GETTEMPLATECONFIGURATION$2, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().add_element_user(GETTEMPLATECONFIGURATION$2);
            }
            target.set(getTemplateConfiguration);
        }
    }
    
    /**
     * Appends and returns a new empty "GetTemplateConfiguration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType addNewGetTemplateConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().add_element_user(GETTEMPLATECONFIGURATION$2);
            return target;
        }
    }
    
    /**
     * Gets the "NegotiationConfiguration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType getNegotiationConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().find_element_user(NEGOTIATIONCONFIGURATION$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NegotiationConfiguration" element
     */
    public void setNegotiationConfiguration(org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType negotiationConfiguration)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().find_element_user(NEGOTIATIONCONFIGURATION$4, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().add_element_user(NEGOTIATIONCONFIGURATION$4);
            }
            target.set(negotiationConfiguration);
        }
    }
    
    /**
     * Appends and returns a new empty "NegotiationConfiguration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType addNewNegotiationConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().add_element_user(NEGOTIATIONCONFIGURATION$4);
            return target;
        }
    }
    
    /**
     * Gets the "name" attribute
     */
    public java.lang.String getName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$6);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "name" attribute
     */
    public org.apache.xmlbeans.XmlString xgetName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$6);
            return target;
        }
    }
    
    /**
     * Sets the "name" attribute
     */
    public void setName(java.lang.String name)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(NAME$6);
            }
            target.setStringValue(name);
        }
    }
    
    /**
     * Sets (as xml) the "name" attribute
     */
    public void xsetName(org.apache.xmlbeans.XmlString name)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(NAME$6);
            }
            target.set(name);
        }
    }
    
    /**
     * Gets the "useSession" attribute
     */
    public boolean getUseSession()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(USESESSION$8);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_default_attribute_value(USESESSION$8);
            }
            if (target == null)
            {
                return false;
            }
            return target.getBooleanValue();
        }
    }
    
    /**
     * Gets (as xml) the "useSession" attribute
     */
    public org.apache.xmlbeans.XmlBoolean xgetUseSession()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBoolean target = null;
            target = (org.apache.xmlbeans.XmlBoolean)get_store().find_attribute_user(USESESSION$8);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlBoolean)get_default_attribute_value(USESESSION$8);
            }
            return target;
        }
    }
    
    /**
     * True if has "useSession" attribute
     */
    public boolean isSetUseSession()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(USESESSION$8) != null;
        }
    }
    
    /**
     * Sets the "useSession" attribute
     */
    public void setUseSession(boolean useSession)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(USESESSION$8);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(USESESSION$8);
            }
            target.setBooleanValue(useSession);
        }
    }
    
    /**
     * Sets (as xml) the "useSession" attribute
     */
    public void xsetUseSession(org.apache.xmlbeans.XmlBoolean useSession)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBoolean target = null;
            target = (org.apache.xmlbeans.XmlBoolean)get_store().find_attribute_user(USESESSION$8);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlBoolean)get_store().add_attribute_user(USESESSION$8);
            }
            target.set(useSession);
        }
    }
    
    /**
     * Unsets the "useSession" attribute
     */
    public void unsetUseSession()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(USESESSION$8);
        }
    }
}
