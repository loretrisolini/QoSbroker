/*
 * An XML document type.
 * Localname: Configuration
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.ConfigurationDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * A document containing one Configuration(@http://schemas.scai.fraunhofer.de/config/wsag4j) element.
 *
 * This is a complex type.
 */
public class ConfigurationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.ConfigurationDocument
{
    private static final long serialVersionUID = 1L;
    
    public ConfigurationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CONFIGURATION$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "Configuration");
    
    
    /**
     * Gets the "Configuration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ConfigurationType getConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ConfigurationType)get_store().find_element_user(CONFIGURATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Configuration" element
     */
    public void setConfiguration(org.ogf.graap.wsag4j.types.configuration.ConfigurationType configuration)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ConfigurationType)get_store().find_element_user(CONFIGURATION$0, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.ConfigurationType)get_store().add_element_user(CONFIGURATION$0);
            }
            target.set(configuration);
        }
    }
    
    /**
     * Appends and returns a new empty "Configuration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ConfigurationType addNewConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ConfigurationType)get_store().add_element_user(CONFIGURATION$0);
            return target;
        }
    }
}
