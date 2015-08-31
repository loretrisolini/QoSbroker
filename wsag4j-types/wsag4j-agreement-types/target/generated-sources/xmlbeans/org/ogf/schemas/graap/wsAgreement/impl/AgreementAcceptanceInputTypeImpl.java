/*
 * XML Type:  AgreementAcceptanceInputType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML AgreementAcceptanceInputType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class AgreementAcceptanceInputTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType
{
    private static final long serialVersionUID = 1L;
    
    public AgreementAcceptanceInputTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NONCRITICALEXTENSION$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "NoncriticalExtension");
    
    
    /**
     * Gets array of all "NoncriticalExtension" elements
     */
    public org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType[] getNoncriticalExtensionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(NONCRITICALEXTENSION$0, targetList);
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType[] result = new org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "NoncriticalExtension" element
     */
    public org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType getNoncriticalExtensionArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType)get_store().find_element_user(NONCRITICALEXTENSION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "NoncriticalExtension" element
     */
    public int sizeOfNoncriticalExtensionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NONCRITICALEXTENSION$0);
        }
    }
    
    /**
     * Sets array of all "NoncriticalExtension" element
     */
    public void setNoncriticalExtensionArray(org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType[] noncriticalExtensionArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(noncriticalExtensionArray, NONCRITICALEXTENSION$0);
        }
    }
    
    /**
     * Sets ith "NoncriticalExtension" element
     */
    public void setNoncriticalExtensionArray(int i, org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType noncriticalExtension)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType)get_store().find_element_user(NONCRITICALEXTENSION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(noncriticalExtension);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "NoncriticalExtension" element
     */
    public org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType insertNewNoncriticalExtension(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType)get_store().insert_element_user(NONCRITICALEXTENSION$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "NoncriticalExtension" element
     */
    public org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType addNewNoncriticalExtension()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType)get_store().add_element_user(NONCRITICALEXTENSION$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "NoncriticalExtension" element
     */
    public void removeNoncriticalExtension(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NONCRITICALEXTENSION$0, i);
        }
    }
}
