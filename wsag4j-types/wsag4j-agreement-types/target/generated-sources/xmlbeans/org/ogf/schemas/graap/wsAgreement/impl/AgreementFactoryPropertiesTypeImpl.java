/*
 * XML Type:  AgreementFactoryPropertiesType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AgreementFactoryPropertiesType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML AgreementFactoryPropertiesType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class AgreementFactoryPropertiesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.AgreementFactoryPropertiesType
{
    private static final long serialVersionUID = 1L;
    
    public AgreementFactoryPropertiesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TEMPLATE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Template");
    
    
    /**
     * Gets array of all "Template" elements
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementTemplateType[] getTemplateArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(TEMPLATE$0, targetList);
            org.ogf.schemas.graap.wsAgreement.AgreementTemplateType[] result = new org.ogf.schemas.graap.wsAgreement.AgreementTemplateType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Template" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementTemplateType getTemplateArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementTemplateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementTemplateType)get_store().find_element_user(TEMPLATE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Template" element
     */
    public int sizeOfTemplateArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(TEMPLATE$0);
        }
    }
    
    /**
     * Sets array of all "Template" element
     */
    public void setTemplateArray(org.ogf.schemas.graap.wsAgreement.AgreementTemplateType[] templateArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(templateArray, TEMPLATE$0);
        }
    }
    
    /**
     * Sets ith "Template" element
     */
    public void setTemplateArray(int i, org.ogf.schemas.graap.wsAgreement.AgreementTemplateType template)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementTemplateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementTemplateType)get_store().find_element_user(TEMPLATE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(template);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Template" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementTemplateType insertNewTemplate(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementTemplateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementTemplateType)get_store().insert_element_user(TEMPLATE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Template" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementTemplateType addNewTemplate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementTemplateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementTemplateType)get_store().add_element_user(TEMPLATE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Template" element
     */
    public void removeTemplate(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(TEMPLATE$0, i);
        }
    }
}
