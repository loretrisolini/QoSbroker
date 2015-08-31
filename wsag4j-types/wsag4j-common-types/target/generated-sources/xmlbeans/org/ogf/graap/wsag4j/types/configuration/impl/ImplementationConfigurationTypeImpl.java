/*
 * XML Type:  ImplementationConfigurationType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * An XML ImplementationConfigurationType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public class ImplementationConfigurationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType
{
    private static final long serialVersionUID = 1L;
    
    public ImplementationConfigurationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName IMPLEMENTATIONCLASS$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "ImplementationClass");
    
    
    /**
     * Gets the "ImplementationClass" element
     */
    public java.lang.String getImplementationClass()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(IMPLEMENTATIONCLASS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "ImplementationClass" element
     */
    public org.apache.xmlbeans.XmlString xgetImplementationClass()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(IMPLEMENTATIONCLASS$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "ImplementationClass" element
     */
    public void setImplementationClass(java.lang.String implementationClass)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(IMPLEMENTATIONCLASS$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(IMPLEMENTATIONCLASS$0);
            }
            target.setStringValue(implementationClass);
        }
    }
    
    /**
     * Sets (as xml) the "ImplementationClass" element
     */
    public void xsetImplementationClass(org.apache.xmlbeans.XmlString implementationClass)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(IMPLEMENTATIONCLASS$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(IMPLEMENTATIONCLASS$0);
            }
            target.set(implementationClass);
        }
    }
}
