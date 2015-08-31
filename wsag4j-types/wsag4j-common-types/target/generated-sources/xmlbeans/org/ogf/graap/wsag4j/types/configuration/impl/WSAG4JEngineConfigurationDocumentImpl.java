/*
 * An XML document type.
 * Localname: WSAG4JEngineConfiguration
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineConfigurationDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * A document containing one WSAG4JEngineConfiguration(@http://schemas.scai.fraunhofer.de/config/wsag4j) element.
 *
 * This is a complex type.
 */
public class WSAG4JEngineConfigurationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineConfigurationDocument
{
    private static final long serialVersionUID = 1L;
    
    public WSAG4JEngineConfigurationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName WSAG4JENGINECONFIGURATION$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "WSAG4JEngineConfiguration");
    
    
    /**
     * Gets the "WSAG4JEngineConfiguration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineConfigurationType getWSAG4JEngineConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineConfigurationType)get_store().find_element_user(WSAG4JENGINECONFIGURATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "WSAG4JEngineConfiguration" element
     */
    public void setWSAG4JEngineConfiguration(org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineConfigurationType wsag4JEngineConfiguration)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineConfigurationType)get_store().find_element_user(WSAG4JENGINECONFIGURATION$0, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineConfigurationType)get_store().add_element_user(WSAG4JENGINECONFIGURATION$0);
            }
            target.set(wsag4JEngineConfiguration);
        }
    }
    
    /**
     * Appends and returns a new empty "WSAG4JEngineConfiguration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineConfigurationType addNewWSAG4JEngineConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineConfigurationType)get_store().add_element_user(WSAG4JENGINECONFIGURATION$0);
            return target;
        }
    }
}
