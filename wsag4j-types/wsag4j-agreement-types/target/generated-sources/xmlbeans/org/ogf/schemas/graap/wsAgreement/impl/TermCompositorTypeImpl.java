/*
 * XML Type:  TermCompositorType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.TermCompositorType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML TermCompositorType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class TermCompositorTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.TermCompositorType
{
    private static final long serialVersionUID = 1L;
    
    public TermCompositorTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName EXACTLYONE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ExactlyOne");
    private static final javax.xml.namespace.QName ONEORMORE$2 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "OneOrMore");
    private static final javax.xml.namespace.QName ALL$4 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "All");
    private static final javax.xml.namespace.QName SERVICEDESCRIPTIONTERM$6 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ServiceDescriptionTerm");
    private static final javax.xml.namespace.QName SERVICEREFERENCE$8 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ServiceReference");
    private static final javax.xml.namespace.QName SERVICEPROPERTIES$10 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ServiceProperties");
    private static final javax.xml.namespace.QName GUARANTEETERM$12 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "GuaranteeTerm");
    
    
    /**
     * Gets array of all "ExactlyOne" elements
     */
    public org.ogf.schemas.graap.wsAgreement.TermCompositorType[] getExactlyOneArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(EXACTLYONE$0, targetList);
            org.ogf.schemas.graap.wsAgreement.TermCompositorType[] result = new org.ogf.schemas.graap.wsAgreement.TermCompositorType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ExactlyOne" element
     */
    public org.ogf.schemas.graap.wsAgreement.TermCompositorType getExactlyOneArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermCompositorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermCompositorType)get_store().find_element_user(EXACTLYONE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ExactlyOne" element
     */
    public int sizeOfExactlyOneArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(EXACTLYONE$0);
        }
    }
    
    /**
     * Sets array of all "ExactlyOne" element
     */
    public void setExactlyOneArray(org.ogf.schemas.graap.wsAgreement.TermCompositorType[] exactlyOneArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(exactlyOneArray, EXACTLYONE$0);
        }
    }
    
    /**
     * Sets ith "ExactlyOne" element
     */
    public void setExactlyOneArray(int i, org.ogf.schemas.graap.wsAgreement.TermCompositorType exactlyOne)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermCompositorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermCompositorType)get_store().find_element_user(EXACTLYONE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(exactlyOne);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ExactlyOne" element
     */
    public org.ogf.schemas.graap.wsAgreement.TermCompositorType insertNewExactlyOne(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermCompositorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermCompositorType)get_store().insert_element_user(EXACTLYONE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ExactlyOne" element
     */
    public org.ogf.schemas.graap.wsAgreement.TermCompositorType addNewExactlyOne()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermCompositorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermCompositorType)get_store().add_element_user(EXACTLYONE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "ExactlyOne" element
     */
    public void removeExactlyOne(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(EXACTLYONE$0, i);
        }
    }
    
    /**
     * Gets array of all "OneOrMore" elements
     */
    public org.ogf.schemas.graap.wsAgreement.TermCompositorType[] getOneOrMoreArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ONEORMORE$2, targetList);
            org.ogf.schemas.graap.wsAgreement.TermCompositorType[] result = new org.ogf.schemas.graap.wsAgreement.TermCompositorType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "OneOrMore" element
     */
    public org.ogf.schemas.graap.wsAgreement.TermCompositorType getOneOrMoreArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermCompositorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermCompositorType)get_store().find_element_user(ONEORMORE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "OneOrMore" element
     */
    public int sizeOfOneOrMoreArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ONEORMORE$2);
        }
    }
    
    /**
     * Sets array of all "OneOrMore" element
     */
    public void setOneOrMoreArray(org.ogf.schemas.graap.wsAgreement.TermCompositorType[] oneOrMoreArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(oneOrMoreArray, ONEORMORE$2);
        }
    }
    
    /**
     * Sets ith "OneOrMore" element
     */
    public void setOneOrMoreArray(int i, org.ogf.schemas.graap.wsAgreement.TermCompositorType oneOrMore)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermCompositorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermCompositorType)get_store().find_element_user(ONEORMORE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(oneOrMore);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "OneOrMore" element
     */
    public org.ogf.schemas.graap.wsAgreement.TermCompositorType insertNewOneOrMore(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermCompositorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermCompositorType)get_store().insert_element_user(ONEORMORE$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "OneOrMore" element
     */
    public org.ogf.schemas.graap.wsAgreement.TermCompositorType addNewOneOrMore()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermCompositorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermCompositorType)get_store().add_element_user(ONEORMORE$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "OneOrMore" element
     */
    public void removeOneOrMore(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ONEORMORE$2, i);
        }
    }
    
    /**
     * Gets array of all "All" elements
     */
    public org.ogf.schemas.graap.wsAgreement.TermCompositorType[] getAllArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ALL$4, targetList);
            org.ogf.schemas.graap.wsAgreement.TermCompositorType[] result = new org.ogf.schemas.graap.wsAgreement.TermCompositorType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "All" element
     */
    public org.ogf.schemas.graap.wsAgreement.TermCompositorType getAllArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermCompositorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermCompositorType)get_store().find_element_user(ALL$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "All" element
     */
    public int sizeOfAllArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ALL$4);
        }
    }
    
    /**
     * Sets array of all "All" element
     */
    public void setAllArray(org.ogf.schemas.graap.wsAgreement.TermCompositorType[] allArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(allArray, ALL$4);
        }
    }
    
    /**
     * Sets ith "All" element
     */
    public void setAllArray(int i, org.ogf.schemas.graap.wsAgreement.TermCompositorType all)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermCompositorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermCompositorType)get_store().find_element_user(ALL$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(all);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "All" element
     */
    public org.ogf.schemas.graap.wsAgreement.TermCompositorType insertNewAll(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermCompositorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermCompositorType)get_store().insert_element_user(ALL$4, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "All" element
     */
    public org.ogf.schemas.graap.wsAgreement.TermCompositorType addNewAll()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermCompositorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermCompositorType)get_store().add_element_user(ALL$4);
            return target;
        }
    }
    
    /**
     * Removes the ith "All" element
     */
    public void removeAll(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ALL$4, i);
        }
    }
    
    /**
     * Gets array of all "ServiceDescriptionTerm" elements
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType[] getServiceDescriptionTermArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SERVICEDESCRIPTIONTERM$6, targetList);
            org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType[] result = new org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ServiceDescriptionTerm" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType getServiceDescriptionTermArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType)get_store().find_element_user(SERVICEDESCRIPTIONTERM$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ServiceDescriptionTerm" element
     */
    public int sizeOfServiceDescriptionTermArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SERVICEDESCRIPTIONTERM$6);
        }
    }
    
    /**
     * Sets array of all "ServiceDescriptionTerm" element
     */
    public void setServiceDescriptionTermArray(org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType[] serviceDescriptionTermArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(serviceDescriptionTermArray, SERVICEDESCRIPTIONTERM$6);
        }
    }
    
    /**
     * Sets ith "ServiceDescriptionTerm" element
     */
    public void setServiceDescriptionTermArray(int i, org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType serviceDescriptionTerm)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType)get_store().find_element_user(SERVICEDESCRIPTIONTERM$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(serviceDescriptionTerm);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ServiceDescriptionTerm" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType insertNewServiceDescriptionTerm(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType)get_store().insert_element_user(SERVICEDESCRIPTIONTERM$6, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ServiceDescriptionTerm" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType addNewServiceDescriptionTerm()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceDescriptionTermType)get_store().add_element_user(SERVICEDESCRIPTIONTERM$6);
            return target;
        }
    }
    
    /**
     * Removes the ith "ServiceDescriptionTerm" element
     */
    public void removeServiceDescriptionTerm(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SERVICEDESCRIPTIONTERM$6, i);
        }
    }
    
    /**
     * Gets array of all "ServiceReference" elements
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceReferenceType[] getServiceReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SERVICEREFERENCE$8, targetList);
            org.ogf.schemas.graap.wsAgreement.ServiceReferenceType[] result = new org.ogf.schemas.graap.wsAgreement.ServiceReferenceType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ServiceReference" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceReferenceType getServiceReferenceArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceReferenceType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceReferenceType)get_store().find_element_user(SERVICEREFERENCE$8, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ServiceReference" element
     */
    public int sizeOfServiceReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SERVICEREFERENCE$8);
        }
    }
    
    /**
     * Sets array of all "ServiceReference" element
     */
    public void setServiceReferenceArray(org.ogf.schemas.graap.wsAgreement.ServiceReferenceType[] serviceReferenceArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(serviceReferenceArray, SERVICEREFERENCE$8);
        }
    }
    
    /**
     * Sets ith "ServiceReference" element
     */
    public void setServiceReferenceArray(int i, org.ogf.schemas.graap.wsAgreement.ServiceReferenceType serviceReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceReferenceType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceReferenceType)get_store().find_element_user(SERVICEREFERENCE$8, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(serviceReference);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ServiceReference" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceReferenceType insertNewServiceReference(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceReferenceType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceReferenceType)get_store().insert_element_user(SERVICEREFERENCE$8, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ServiceReference" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceReferenceType addNewServiceReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceReferenceType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceReferenceType)get_store().add_element_user(SERVICEREFERENCE$8);
            return target;
        }
    }
    
    /**
     * Removes the ith "ServiceReference" element
     */
    public void removeServiceReference(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SERVICEREFERENCE$8, i);
        }
    }
    
    /**
     * Gets array of all "ServiceProperties" elements
     */
    public org.ogf.schemas.graap.wsAgreement.ServicePropertiesType[] getServicePropertiesArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SERVICEPROPERTIES$10, targetList);
            org.ogf.schemas.graap.wsAgreement.ServicePropertiesType[] result = new org.ogf.schemas.graap.wsAgreement.ServicePropertiesType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ServiceProperties" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServicePropertiesType getServicePropertiesArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServicePropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServicePropertiesType)get_store().find_element_user(SERVICEPROPERTIES$10, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ServiceProperties" element
     */
    public int sizeOfServicePropertiesArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SERVICEPROPERTIES$10);
        }
    }
    
    /**
     * Sets array of all "ServiceProperties" element
     */
    public void setServicePropertiesArray(org.ogf.schemas.graap.wsAgreement.ServicePropertiesType[] servicePropertiesArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(servicePropertiesArray, SERVICEPROPERTIES$10);
        }
    }
    
    /**
     * Sets ith "ServiceProperties" element
     */
    public void setServicePropertiesArray(int i, org.ogf.schemas.graap.wsAgreement.ServicePropertiesType serviceProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServicePropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServicePropertiesType)get_store().find_element_user(SERVICEPROPERTIES$10, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(serviceProperties);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ServiceProperties" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServicePropertiesType insertNewServiceProperties(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServicePropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServicePropertiesType)get_store().insert_element_user(SERVICEPROPERTIES$10, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ServiceProperties" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServicePropertiesType addNewServiceProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServicePropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServicePropertiesType)get_store().add_element_user(SERVICEPROPERTIES$10);
            return target;
        }
    }
    
    /**
     * Removes the ith "ServiceProperties" element
     */
    public void removeServiceProperties(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SERVICEPROPERTIES$10, i);
        }
    }
    
    /**
     * Gets array of all "GuaranteeTerm" elements
     */
    public org.ogf.schemas.graap.wsAgreement.GuaranteeTermType[] getGuaranteeTermArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(GUARANTEETERM$12, targetList);
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermType[] result = new org.ogf.schemas.graap.wsAgreement.GuaranteeTermType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "GuaranteeTerm" element
     */
    public org.ogf.schemas.graap.wsAgreement.GuaranteeTermType getGuaranteeTermArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType)get_store().find_element_user(GUARANTEETERM$12, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "GuaranteeTerm" element
     */
    public int sizeOfGuaranteeTermArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(GUARANTEETERM$12);
        }
    }
    
    /**
     * Sets array of all "GuaranteeTerm" element
     */
    public void setGuaranteeTermArray(org.ogf.schemas.graap.wsAgreement.GuaranteeTermType[] guaranteeTermArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(guaranteeTermArray, GUARANTEETERM$12);
        }
    }
    
    /**
     * Sets ith "GuaranteeTerm" element
     */
    public void setGuaranteeTermArray(int i, org.ogf.schemas.graap.wsAgreement.GuaranteeTermType guaranteeTerm)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType)get_store().find_element_user(GUARANTEETERM$12, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(guaranteeTerm);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "GuaranteeTerm" element
     */
    public org.ogf.schemas.graap.wsAgreement.GuaranteeTermType insertNewGuaranteeTerm(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType)get_store().insert_element_user(GUARANTEETERM$12, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "GuaranteeTerm" element
     */
    public org.ogf.schemas.graap.wsAgreement.GuaranteeTermType addNewGuaranteeTerm()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType)get_store().add_element_user(GUARANTEETERM$12);
            return target;
        }
    }
    
    /**
     * Removes the ith "GuaranteeTerm" element
     */
    public void removeGuaranteeTerm(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(GUARANTEETERM$12, i);
        }
    }
}
