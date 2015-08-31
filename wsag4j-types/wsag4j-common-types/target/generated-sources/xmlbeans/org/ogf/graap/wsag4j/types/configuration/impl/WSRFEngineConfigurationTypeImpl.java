/*
 * XML Type:  WSRFEngineConfigurationType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * An XML WSRFEngineConfigurationType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public class WSRFEngineConfigurationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType
{
    private static final long serialVersionUID = 1L;
    
    public WSRFEngineConfigurationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GATEWAYADDRESS$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "GatewayAddress");
    private static final javax.xml.namespace.QName KEYSTORE$2 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "Keystore");
    private static final javax.xml.namespace.QName TRUSTSTORE$4 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "Truststore");
    private static final javax.xml.namespace.QName SECURITYHANDLERCHAIN$6 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "SecurityHandlerChain");
    private static final javax.xml.namespace.QName WSAG4JENGINEINSTANCES$8 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "WSAG4JEngineInstances");
    
    
    /**
     * Gets the "GatewayAddress" element
     */
    public java.lang.String getGatewayAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(GATEWAYADDRESS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "GatewayAddress" element
     */
    public org.apache.xmlbeans.XmlString xgetGatewayAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(GATEWAYADDRESS$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "GatewayAddress" element
     */
    public void setGatewayAddress(java.lang.String gatewayAddress)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(GATEWAYADDRESS$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(GATEWAYADDRESS$0);
            }
            target.setStringValue(gatewayAddress);
        }
    }
    
    /**
     * Sets (as xml) the "GatewayAddress" element
     */
    public void xsetGatewayAddress(org.apache.xmlbeans.XmlString gatewayAddress)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(GATEWAYADDRESS$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(GATEWAYADDRESS$0);
            }
            target.set(gatewayAddress);
        }
    }
    
    /**
     * Gets the "Keystore" element
     */
    public org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType getKeystore()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType)get_store().find_element_user(KEYSTORE$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Keystore" element
     */
    public void setKeystore(org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType keystore)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType)get_store().find_element_user(KEYSTORE$2, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType)get_store().add_element_user(KEYSTORE$2);
            }
            target.set(keystore);
        }
    }
    
    /**
     * Appends and returns a new empty "Keystore" element
     */
    public org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType addNewKeystore()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType)get_store().add_element_user(KEYSTORE$2);
            return target;
        }
    }
    
    /**
     * Gets the "Truststore" element
     */
    public org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType getTruststore()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType)get_store().find_element_user(TRUSTSTORE$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Truststore" element
     */
    public void setTruststore(org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType truststore)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType)get_store().find_element_user(TRUSTSTORE$4, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType)get_store().add_element_user(TRUSTSTORE$4);
            }
            target.set(truststore);
        }
    }
    
    /**
     * Appends and returns a new empty "Truststore" element
     */
    public org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType addNewTruststore()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType)get_store().add_element_user(TRUSTSTORE$4);
            return target;
        }
    }
    
    /**
     * Gets the "SecurityHandlerChain" element
     */
    public org.ogf.graap.wsag4j.types.configuration.HandlerChainType getSecurityHandlerChain()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.HandlerChainType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.HandlerChainType)get_store().find_element_user(SECURITYHANDLERCHAIN$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "SecurityHandlerChain" element
     */
    public boolean isSetSecurityHandlerChain()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SECURITYHANDLERCHAIN$6) != 0;
        }
    }
    
    /**
     * Sets the "SecurityHandlerChain" element
     */
    public void setSecurityHandlerChain(org.ogf.graap.wsag4j.types.configuration.HandlerChainType securityHandlerChain)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.HandlerChainType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.HandlerChainType)get_store().find_element_user(SECURITYHANDLERCHAIN$6, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.HandlerChainType)get_store().add_element_user(SECURITYHANDLERCHAIN$6);
            }
            target.set(securityHandlerChain);
        }
    }
    
    /**
     * Appends and returns a new empty "SecurityHandlerChain" element
     */
    public org.ogf.graap.wsag4j.types.configuration.HandlerChainType addNewSecurityHandlerChain()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.HandlerChainType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.HandlerChainType)get_store().add_element_user(SECURITYHANDLERCHAIN$6);
            return target;
        }
    }
    
    /**
     * Unsets the "SecurityHandlerChain" element
     */
    public void unsetSecurityHandlerChain()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SECURITYHANDLERCHAIN$6, 0);
        }
    }
    
    /**
     * Gets the "WSAG4JEngineInstances" element
     */
    public org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceListType getWSAG4JEngineInstances()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceListType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceListType)get_store().find_element_user(WSAG4JENGINEINSTANCES$8, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "WSAG4JEngineInstances" element
     */
    public boolean isSetWSAG4JEngineInstances()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(WSAG4JENGINEINSTANCES$8) != 0;
        }
    }
    
    /**
     * Sets the "WSAG4JEngineInstances" element
     */
    public void setWSAG4JEngineInstances(org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceListType wsag4JEngineInstances)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceListType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceListType)get_store().find_element_user(WSAG4JENGINEINSTANCES$8, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceListType)get_store().add_element_user(WSAG4JENGINEINSTANCES$8);
            }
            target.set(wsag4JEngineInstances);
        }
    }
    
    /**
     * Appends and returns a new empty "WSAG4JEngineInstances" element
     */
    public org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceListType addNewWSAG4JEngineInstances()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceListType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceListType)get_store().add_element_user(WSAG4JENGINEINSTANCES$8);
            return target;
        }
    }
    
    /**
     * Unsets the "WSAG4JEngineInstances" element
     */
    public void unsetWSAG4JEngineInstances()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(WSAG4JENGINEINSTANCES$8, 0);
        }
    }
}
