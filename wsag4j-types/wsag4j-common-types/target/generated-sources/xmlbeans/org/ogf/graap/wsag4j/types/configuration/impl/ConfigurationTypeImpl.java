/*
 * XML Type:  ConfigurationType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.ConfigurationType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * An XML ConfigurationType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public class ConfigurationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.ConfigurationType
{
    private static final long serialVersionUID = 1L;
    
    public ConfigurationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName WSRFENGINECONFIGURATION$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "WSRFEngineConfiguration");
    private static final javax.xml.namespace.QName WSRFCLIENTCONFIGURATION$2 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "WSRFClientConfiguration");
    
    
    /**
     * Gets the "WSRFEngineConfiguration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType getWSRFEngineConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType)get_store().find_element_user(WSRFENGINECONFIGURATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "WSRFEngineConfiguration" element
     */
    public boolean isSetWSRFEngineConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(WSRFENGINECONFIGURATION$0) != 0;
        }
    }
    
    /**
     * Sets the "WSRFEngineConfiguration" element
     */
    public void setWSRFEngineConfiguration(org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType wsrfEngineConfiguration)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType)get_store().find_element_user(WSRFENGINECONFIGURATION$0, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType)get_store().add_element_user(WSRFENGINECONFIGURATION$0);
            }
            target.set(wsrfEngineConfiguration);
        }
    }
    
    /**
     * Appends and returns a new empty "WSRFEngineConfiguration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType addNewWSRFEngineConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType)get_store().add_element_user(WSRFENGINECONFIGURATION$0);
            return target;
        }
    }
    
    /**
     * Unsets the "WSRFEngineConfiguration" element
     */
    public void unsetWSRFEngineConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(WSRFENGINECONFIGURATION$0, 0);
        }
    }
    
    /**
     * Gets the "WSRFClientConfiguration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.WSRFClientConfigurationType getWSRFClientConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.WSRFClientConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.WSRFClientConfigurationType)get_store().find_element_user(WSRFCLIENTCONFIGURATION$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "WSRFClientConfiguration" element
     */
    public boolean isSetWSRFClientConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(WSRFCLIENTCONFIGURATION$2) != 0;
        }
    }
    
    /**
     * Sets the "WSRFClientConfiguration" element
     */
    public void setWSRFClientConfiguration(org.ogf.graap.wsag4j.types.configuration.WSRFClientConfigurationType wsrfClientConfiguration)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.WSRFClientConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.WSRFClientConfigurationType)get_store().find_element_user(WSRFCLIENTCONFIGURATION$2, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.WSRFClientConfigurationType)get_store().add_element_user(WSRFCLIENTCONFIGURATION$2);
            }
            target.set(wsrfClientConfiguration);
        }
    }
    
    /**
     * Appends and returns a new empty "WSRFClientConfiguration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.WSRFClientConfigurationType addNewWSRFClientConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.WSRFClientConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.WSRFClientConfigurationType)get_store().add_element_user(WSRFCLIENTCONFIGURATION$2);
            return target;
        }
    }
    
    /**
     * Unsets the "WSRFClientConfiguration" element
     */
    public void unsetWSRFClientConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(WSRFCLIENTCONFIGURATION$2, 0);
        }
    }
}
