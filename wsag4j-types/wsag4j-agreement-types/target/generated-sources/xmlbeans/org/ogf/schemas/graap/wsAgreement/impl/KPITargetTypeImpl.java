/*
 * XML Type:  KPITargetType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.KPITargetType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML KPITargetType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class KPITargetTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.KPITargetType
{
    private static final long serialVersionUID = 1L;
    
    public KPITargetTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName KPINAME$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "KPIName");
    private static final javax.xml.namespace.QName CUSTOMSERVICELEVEL$2 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "CustomServiceLevel");
    
    
    /**
     * Gets the "KPIName" element
     */
    public java.lang.String getKPIName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(KPINAME$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "KPIName" element
     */
    public org.apache.xmlbeans.XmlString xgetKPIName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(KPINAME$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "KPIName" element
     */
    public void setKPIName(java.lang.String kpiName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(KPINAME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(KPINAME$0);
            }
            target.setStringValue(kpiName);
        }
    }
    
    /**
     * Sets (as xml) the "KPIName" element
     */
    public void xsetKPIName(org.apache.xmlbeans.XmlString kpiName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(KPINAME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(KPINAME$0);
            }
            target.set(kpiName);
        }
    }
    
    /**
     * Gets the "CustomServiceLevel" element
     */
    public org.apache.xmlbeans.XmlObject getCustomServiceLevel()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(CUSTOMSERVICELEVEL$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CustomServiceLevel" element
     */
    public void setCustomServiceLevel(org.apache.xmlbeans.XmlObject customServiceLevel)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(CUSTOMSERVICELEVEL$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(CUSTOMSERVICELEVEL$2);
            }
            target.set(customServiceLevel);
        }
    }
    
    /**
     * Appends and returns a new empty "CustomServiceLevel" element
     */
    public org.apache.xmlbeans.XmlObject addNewCustomServiceLevel()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(CUSTOMSERVICELEVEL$2);
            return target;
        }
    }
}
