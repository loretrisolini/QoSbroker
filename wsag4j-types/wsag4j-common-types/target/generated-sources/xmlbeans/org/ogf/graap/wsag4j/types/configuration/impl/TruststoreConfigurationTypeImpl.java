/*
 * XML Type:  TruststoreConfigurationType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * An XML TruststoreConfigurationType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public class TruststoreConfigurationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType
{
    private static final long serialVersionUID = 1L;
    
    public TruststoreConfigurationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TRUSTSTORETYPE$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "TruststoreType");
    private static final javax.xml.namespace.QName TRUSTSTOREFILE$2 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "TruststoreFile");
    private static final javax.xml.namespace.QName TRUSTSTOREPASSWORD$4 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "TruststorePassword");
    
    
    /**
     * Gets the "TruststoreType" element
     */
    public java.lang.String getTruststoreType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TRUSTSTORETYPE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "TruststoreType" element
     */
    public org.apache.xmlbeans.XmlString xgetTruststoreType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TRUSTSTORETYPE$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "TruststoreType" element
     */
    public void setTruststoreType(java.lang.String truststoreType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TRUSTSTORETYPE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TRUSTSTORETYPE$0);
            }
            target.setStringValue(truststoreType);
        }
    }
    
    /**
     * Sets (as xml) the "TruststoreType" element
     */
    public void xsetTruststoreType(org.apache.xmlbeans.XmlString truststoreType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TRUSTSTORETYPE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TRUSTSTORETYPE$0);
            }
            target.set(truststoreType);
        }
    }
    
    /**
     * Gets the "TruststoreFile" element
     */
    public java.lang.String getTruststoreFile()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TRUSTSTOREFILE$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "TruststoreFile" element
     */
    public org.apache.xmlbeans.XmlString xgetTruststoreFile()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TRUSTSTOREFILE$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "TruststoreFile" element
     */
    public void setTruststoreFile(java.lang.String truststoreFile)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TRUSTSTOREFILE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TRUSTSTOREFILE$2);
            }
            target.setStringValue(truststoreFile);
        }
    }
    
    /**
     * Sets (as xml) the "TruststoreFile" element
     */
    public void xsetTruststoreFile(org.apache.xmlbeans.XmlString truststoreFile)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TRUSTSTOREFILE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TRUSTSTOREFILE$2);
            }
            target.set(truststoreFile);
        }
    }
    
    /**
     * Gets the "TruststorePassword" element
     */
    public java.lang.String getTruststorePassword()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TRUSTSTOREPASSWORD$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "TruststorePassword" element
     */
    public org.apache.xmlbeans.XmlString xgetTruststorePassword()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TRUSTSTOREPASSWORD$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "TruststorePassword" element
     */
    public void setTruststorePassword(java.lang.String truststorePassword)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TRUSTSTOREPASSWORD$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TRUSTSTOREPASSWORD$4);
            }
            target.setStringValue(truststorePassword);
        }
    }
    
    /**
     * Sets (as xml) the "TruststorePassword" element
     */
    public void xsetTruststorePassword(org.apache.xmlbeans.XmlString truststorePassword)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TRUSTSTOREPASSWORD$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TRUSTSTOREPASSWORD$4);
            }
            target.set(truststorePassword);
        }
    }
}
