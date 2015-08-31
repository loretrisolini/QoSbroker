/*
 * XML Type:  WSAG4JEngineInstanceType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * An XML WSAG4JEngineInstanceType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public class WSAG4JEngineInstanceTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType
{
    private static final long serialVersionUID = 1L;
    
    public WSAG4JEngineInstanceTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ENGINECONFIGURATIONFILE$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "EngineConfigurationFile");
    
    
    /**
     * Gets the "EngineConfigurationFile" attribute
     */
    public java.lang.String getEngineConfigurationFile()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ENGINECONFIGURATIONFILE$0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "EngineConfigurationFile" attribute
     */
    public org.apache.xmlbeans.XmlString xgetEngineConfigurationFile()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(ENGINECONFIGURATIONFILE$0);
            return target;
        }
    }
    
    /**
     * Sets the "EngineConfigurationFile" attribute
     */
    public void setEngineConfigurationFile(java.lang.String engineConfigurationFile)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ENGINECONFIGURATIONFILE$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ENGINECONFIGURATIONFILE$0);
            }
            target.setStringValue(engineConfigurationFile);
        }
    }
    
    /**
     * Sets (as xml) the "EngineConfigurationFile" attribute
     */
    public void xsetEngineConfigurationFile(org.apache.xmlbeans.XmlString engineConfigurationFile)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(ENGINECONFIGURATIONFILE$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(ENGINECONFIGURATIONFILE$0);
            }
            target.set(engineConfigurationFile);
        }
    }
}
