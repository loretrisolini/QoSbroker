/*
 * XML Type:  ServiceNameSet
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.ServiceNameSet
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML ServiceNameSet(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class ServiceNameSetImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.ServiceNameSet
{
    private static final long serialVersionUID = 1L;
    
    public ServiceNameSetImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICENAME$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ServiceName");
    
    
    /**
     * Gets array of all "ServiceName" elements
     */
    public java.lang.String[] getServiceNameArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SERVICENAME$0, targetList);
            java.lang.String[] result = new java.lang.String[targetList.size()];
            for (int i = 0, len = targetList.size() ; i < len ; i++)
                result[i] = ((org.apache.xmlbeans.SimpleValue)targetList.get(i)).getStringValue();
            return result;
        }
    }
    
    /**
     * Gets ith "ServiceName" element
     */
    public java.lang.String getServiceNameArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVICENAME$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) array of all "ServiceName" elements
     */
    public org.apache.xmlbeans.XmlString[] xgetServiceNameArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SERVICENAME$0, targetList);
            org.apache.xmlbeans.XmlString[] result = new org.apache.xmlbeans.XmlString[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets (as xml) ith "ServiceName" element
     */
    public org.apache.xmlbeans.XmlString xgetServiceNameArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SERVICENAME$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return (org.apache.xmlbeans.XmlString)target;
        }
    }
    
    /**
     * Returns number of "ServiceName" element
     */
    public int sizeOfServiceNameArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SERVICENAME$0);
        }
    }
    
    /**
     * Sets array of all "ServiceName" element
     */
    public void setServiceNameArray(java.lang.String[] serviceNameArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(serviceNameArray, SERVICENAME$0);
        }
    }
    
    /**
     * Sets ith "ServiceName" element
     */
    public void setServiceNameArray(int i, java.lang.String serviceName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVICENAME$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(serviceName);
        }
    }
    
    /**
     * Sets (as xml) array of all "ServiceName" element
     */
    public void xsetServiceNameArray(org.apache.xmlbeans.XmlString[]serviceNameArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(serviceNameArray, SERVICENAME$0);
        }
    }
    
    /**
     * Sets (as xml) ith "ServiceName" element
     */
    public void xsetServiceNameArray(int i, org.apache.xmlbeans.XmlString serviceName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SERVICENAME$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(serviceName);
        }
    }
    
    /**
     * Inserts the value as the ith "ServiceName" element
     */
    public void insertServiceName(int i, java.lang.String serviceName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = 
                (org.apache.xmlbeans.SimpleValue)get_store().insert_element_user(SERVICENAME$0, i);
            target.setStringValue(serviceName);
        }
    }
    
    /**
     * Appends the value as the last "ServiceName" element
     */
    public void addServiceName(java.lang.String serviceName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SERVICENAME$0);
            target.setStringValue(serviceName);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ServiceName" element
     */
    public org.apache.xmlbeans.XmlString insertNewServiceName(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().insert_element_user(SERVICENAME$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ServiceName" element
     */
    public org.apache.xmlbeans.XmlString addNewServiceName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(SERVICENAME$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "ServiceName" element
     */
    public void removeServiceName(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SERVICENAME$0, i);
        }
    }
}
