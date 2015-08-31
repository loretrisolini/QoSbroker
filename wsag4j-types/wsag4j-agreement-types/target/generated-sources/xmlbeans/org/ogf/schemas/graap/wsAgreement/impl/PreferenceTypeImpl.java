/*
 * XML Type:  PreferenceType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.PreferenceType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML PreferenceType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class PreferenceTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.PreferenceType
{
    private static final long serialVersionUID = 1L;
    
    public PreferenceTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICETERMREFERENCE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ServiceTermReference");
    private static final javax.xml.namespace.QName UTILITY$2 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Utility");
    
    
    /**
     * Gets array of all "ServiceTermReference" elements
     */
    public java.lang.String[] getServiceTermReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SERVICETERMREFERENCE$0, targetList);
            java.lang.String[] result = new java.lang.String[targetList.size()];
            for (int i = 0, len = targetList.size() ; i < len ; i++)
                result[i] = ((org.apache.xmlbeans.SimpleValue)targetList.get(i)).getStringValue();
            return result;
        }
    }
    
    /**
     * Gets ith "ServiceTermReference" element
     */
    public java.lang.String getServiceTermReferenceArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVICETERMREFERENCE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) array of all "ServiceTermReference" elements
     */
    public org.apache.xmlbeans.XmlString[] xgetServiceTermReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SERVICETERMREFERENCE$0, targetList);
            org.apache.xmlbeans.XmlString[] result = new org.apache.xmlbeans.XmlString[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets (as xml) ith "ServiceTermReference" element
     */
    public org.apache.xmlbeans.XmlString xgetServiceTermReferenceArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SERVICETERMREFERENCE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return (org.apache.xmlbeans.XmlString)target;
        }
    }
    
    /**
     * Returns number of "ServiceTermReference" element
     */
    public int sizeOfServiceTermReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SERVICETERMREFERENCE$0);
        }
    }
    
    /**
     * Sets array of all "ServiceTermReference" element
     */
    public void setServiceTermReferenceArray(java.lang.String[] serviceTermReferenceArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(serviceTermReferenceArray, SERVICETERMREFERENCE$0);
        }
    }
    
    /**
     * Sets ith "ServiceTermReference" element
     */
    public void setServiceTermReferenceArray(int i, java.lang.String serviceTermReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVICETERMREFERENCE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(serviceTermReference);
        }
    }
    
    /**
     * Sets (as xml) array of all "ServiceTermReference" element
     */
    public void xsetServiceTermReferenceArray(org.apache.xmlbeans.XmlString[]serviceTermReferenceArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(serviceTermReferenceArray, SERVICETERMREFERENCE$0);
        }
    }
    
    /**
     * Sets (as xml) ith "ServiceTermReference" element
     */
    public void xsetServiceTermReferenceArray(int i, org.apache.xmlbeans.XmlString serviceTermReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SERVICETERMREFERENCE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(serviceTermReference);
        }
    }
    
    /**
     * Inserts the value as the ith "ServiceTermReference" element
     */
    public void insertServiceTermReference(int i, java.lang.String serviceTermReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = 
                (org.apache.xmlbeans.SimpleValue)get_store().insert_element_user(SERVICETERMREFERENCE$0, i);
            target.setStringValue(serviceTermReference);
        }
    }
    
    /**
     * Appends the value as the last "ServiceTermReference" element
     */
    public void addServiceTermReference(java.lang.String serviceTermReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SERVICETERMREFERENCE$0);
            target.setStringValue(serviceTermReference);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ServiceTermReference" element
     */
    public org.apache.xmlbeans.XmlString insertNewServiceTermReference(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().insert_element_user(SERVICETERMREFERENCE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ServiceTermReference" element
     */
    public org.apache.xmlbeans.XmlString addNewServiceTermReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(SERVICETERMREFERENCE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "ServiceTermReference" element
     */
    public void removeServiceTermReference(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SERVICETERMREFERENCE$0, i);
        }
    }
    
    /**
     * Gets array of all "Utility" elements
     */
    public float[] getUtilityArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(UTILITY$2, targetList);
            float[] result = new float[targetList.size()];
            for (int i = 0, len = targetList.size() ; i < len ; i++)
                result[i] = ((org.apache.xmlbeans.SimpleValue)targetList.get(i)).getFloatValue();
            return result;
        }
    }
    
    /**
     * Gets ith "Utility" element
     */
    public float getUtilityArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(UTILITY$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target.getFloatValue();
        }
    }
    
    /**
     * Gets (as xml) array of all "Utility" elements
     */
    public org.apache.xmlbeans.XmlFloat[] xgetUtilityArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(UTILITY$2, targetList);
            org.apache.xmlbeans.XmlFloat[] result = new org.apache.xmlbeans.XmlFloat[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets (as xml) ith "Utility" element
     */
    public org.apache.xmlbeans.XmlFloat xgetUtilityArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlFloat target = null;
            target = (org.apache.xmlbeans.XmlFloat)get_store().find_element_user(UTILITY$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return (org.apache.xmlbeans.XmlFloat)target;
        }
    }
    
    /**
     * Returns number of "Utility" element
     */
    public int sizeOfUtilityArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(UTILITY$2);
        }
    }
    
    /**
     * Sets array of all "Utility" element
     */
    public void setUtilityArray(float[] utilityArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(utilityArray, UTILITY$2);
        }
    }
    
    /**
     * Sets ith "Utility" element
     */
    public void setUtilityArray(int i, float utility)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(UTILITY$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.setFloatValue(utility);
        }
    }
    
    /**
     * Sets (as xml) array of all "Utility" element
     */
    public void xsetUtilityArray(org.apache.xmlbeans.XmlFloat[]utilityArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(utilityArray, UTILITY$2);
        }
    }
    
    /**
     * Sets (as xml) ith "Utility" element
     */
    public void xsetUtilityArray(int i, org.apache.xmlbeans.XmlFloat utility)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlFloat target = null;
            target = (org.apache.xmlbeans.XmlFloat)get_store().find_element_user(UTILITY$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(utility);
        }
    }
    
    /**
     * Inserts the value as the ith "Utility" element
     */
    public void insertUtility(int i, float utility)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = 
                (org.apache.xmlbeans.SimpleValue)get_store().insert_element_user(UTILITY$2, i);
            target.setFloatValue(utility);
        }
    }
    
    /**
     * Appends the value as the last "Utility" element
     */
    public void addUtility(float utility)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(UTILITY$2);
            target.setFloatValue(utility);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Utility" element
     */
    public org.apache.xmlbeans.XmlFloat insertNewUtility(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlFloat target = null;
            target = (org.apache.xmlbeans.XmlFloat)get_store().insert_element_user(UTILITY$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Utility" element
     */
    public org.apache.xmlbeans.XmlFloat addNewUtility()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlFloat target = null;
            target = (org.apache.xmlbeans.XmlFloat)get_store().add_element_user(UTILITY$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "Utility" element
     */
    public void removeUtility(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(UTILITY$2, i);
        }
    }
}
