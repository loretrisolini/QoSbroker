/*
 * XML Type:  ServiceTermType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.ServiceTermType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML ServiceTermType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class ServiceTermTypeImpl extends org.ogf.schemas.graap.wsAgreement.impl.TermTypeImpl implements org.ogf.schemas.graap.wsAgreement.ServiceTermType
{
    private static final long serialVersionUID = 1L;
    
    public ServiceTermTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICENAME$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ServiceName");
    
    
    /**
     * Gets the "ServiceName" attribute
     */
    public java.lang.String getServiceName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SERVICENAME$0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "ServiceName" attribute
     */
    public org.apache.xmlbeans.XmlString xgetServiceName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(SERVICENAME$0);
            return target;
        }
    }
    
    /**
     * Sets the "ServiceName" attribute
     */
    public void setServiceName(java.lang.String serviceName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SERVICENAME$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(SERVICENAME$0);
            }
            target.setStringValue(serviceName);
        }
    }
    
    /**
     * Sets (as xml) the "ServiceName" attribute
     */
    public void xsetServiceName(org.apache.xmlbeans.XmlString serviceName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(SERVICENAME$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(SERVICENAME$0);
            }
            target.set(serviceName);
        }
    }
}
