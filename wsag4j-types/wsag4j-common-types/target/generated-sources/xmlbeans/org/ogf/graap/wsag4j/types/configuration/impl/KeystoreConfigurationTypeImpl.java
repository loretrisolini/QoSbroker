/*
 * XML Type:  KeystoreConfigurationType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * An XML KeystoreConfigurationType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public class KeystoreConfigurationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType
{
    private static final long serialVersionUID = 1L;
    
    public KeystoreConfigurationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName KEYSTORETYPE$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "KeystoreType");
    private static final javax.xml.namespace.QName KEYSTOREFILE$2 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "KeystoreFile");
    private static final javax.xml.namespace.QName KEYSTOREPASSWORD$4 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "KeystorePassword");
    private static final javax.xml.namespace.QName ALIAS$6 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "Alias");
    private static final javax.xml.namespace.QName ALIASPASSWORD$8 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "AliasPassword");
    
    
    /**
     * Gets the "KeystoreType" element
     */
    public java.lang.String getKeystoreType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(KEYSTORETYPE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "KeystoreType" element
     */
    public org.apache.xmlbeans.XmlString xgetKeystoreType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(KEYSTORETYPE$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "KeystoreType" element
     */
    public void setKeystoreType(java.lang.String keystoreType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(KEYSTORETYPE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(KEYSTORETYPE$0);
            }
            target.setStringValue(keystoreType);
        }
    }
    
    /**
     * Sets (as xml) the "KeystoreType" element
     */
    public void xsetKeystoreType(org.apache.xmlbeans.XmlString keystoreType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(KEYSTORETYPE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(KEYSTORETYPE$0);
            }
            target.set(keystoreType);
        }
    }
    
    /**
     * Gets the "KeystoreFile" element
     */
    public java.lang.String getKeystoreFile()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(KEYSTOREFILE$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "KeystoreFile" element
     */
    public org.apache.xmlbeans.XmlString xgetKeystoreFile()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(KEYSTOREFILE$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "KeystoreFile" element
     */
    public void setKeystoreFile(java.lang.String keystoreFile)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(KEYSTOREFILE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(KEYSTOREFILE$2);
            }
            target.setStringValue(keystoreFile);
        }
    }
    
    /**
     * Sets (as xml) the "KeystoreFile" element
     */
    public void xsetKeystoreFile(org.apache.xmlbeans.XmlString keystoreFile)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(KEYSTOREFILE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(KEYSTOREFILE$2);
            }
            target.set(keystoreFile);
        }
    }
    
    /**
     * Gets the "KeystorePassword" element
     */
    public java.lang.String getKeystorePassword()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(KEYSTOREPASSWORD$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "KeystorePassword" element
     */
    public org.apache.xmlbeans.XmlString xgetKeystorePassword()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(KEYSTOREPASSWORD$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "KeystorePassword" element
     */
    public void setKeystorePassword(java.lang.String keystorePassword)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(KEYSTOREPASSWORD$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(KEYSTOREPASSWORD$4);
            }
            target.setStringValue(keystorePassword);
        }
    }
    
    /**
     * Sets (as xml) the "KeystorePassword" element
     */
    public void xsetKeystorePassword(org.apache.xmlbeans.XmlString keystorePassword)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(KEYSTOREPASSWORD$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(KEYSTOREPASSWORD$4);
            }
            target.set(keystorePassword);
        }
    }
    
    /**
     * Gets the "Alias" element
     */
    public java.lang.String getAlias()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ALIAS$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Alias" element
     */
    public org.apache.xmlbeans.XmlString xgetAlias()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ALIAS$6, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Alias" element
     */
    public void setAlias(java.lang.String alias)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ALIAS$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ALIAS$6);
            }
            target.setStringValue(alias);
        }
    }
    
    /**
     * Sets (as xml) the "Alias" element
     */
    public void xsetAlias(org.apache.xmlbeans.XmlString alias)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ALIAS$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ALIAS$6);
            }
            target.set(alias);
        }
    }
    
    /**
     * Gets the "AliasPassword" element
     */
    public java.lang.String getAliasPassword()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ALIASPASSWORD$8, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "AliasPassword" element
     */
    public org.apache.xmlbeans.XmlString xgetAliasPassword()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ALIASPASSWORD$8, 0);
            return target;
        }
    }
    
    /**
     * Sets the "AliasPassword" element
     */
    public void setAliasPassword(java.lang.String aliasPassword)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ALIASPASSWORD$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ALIASPASSWORD$8);
            }
            target.setStringValue(aliasPassword);
        }
    }
    
    /**
     * Sets (as xml) the "AliasPassword" element
     */
    public void xsetAliasPassword(org.apache.xmlbeans.XmlString aliasPassword)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ALIASPASSWORD$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ALIASPASSWORD$8);
            }
            target.set(aliasPassword);
        }
    }
}
