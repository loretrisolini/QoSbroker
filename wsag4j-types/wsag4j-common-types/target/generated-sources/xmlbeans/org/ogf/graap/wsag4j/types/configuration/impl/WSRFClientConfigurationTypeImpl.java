/*
 * XML Type:  WSRFClientConfigurationType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.WSRFClientConfigurationType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * An XML WSRFClientConfigurationType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public class WSRFClientConfigurationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.WSRFClientConfigurationType
{
    private static final long serialVersionUID = 1L;
    
    public WSRFClientConfigurationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SECURITYHANDLERCHAIN$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "SecurityHandlerChain");
    
    
    /**
     * Gets the "SecurityHandlerChain" element
     */
    public org.ogf.graap.wsag4j.types.configuration.HandlerChainType getSecurityHandlerChain()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.HandlerChainType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.HandlerChainType)get_store().find_element_user(SECURITYHANDLERCHAIN$0, 0);
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
            return get_store().count_elements(SECURITYHANDLERCHAIN$0) != 0;
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
            target = (org.ogf.graap.wsag4j.types.configuration.HandlerChainType)get_store().find_element_user(SECURITYHANDLERCHAIN$0, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.HandlerChainType)get_store().add_element_user(SECURITYHANDLERCHAIN$0);
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
            target = (org.ogf.graap.wsag4j.types.configuration.HandlerChainType)get_store().add_element_user(SECURITYHANDLERCHAIN$0);
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
            get_store().remove_element(SECURITYHANDLERCHAIN$0, 0);
        }
    }
}
