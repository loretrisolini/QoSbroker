/*
 * XML Type:  VariableSetType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.VariableSetType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML VariableSetType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class VariableSetTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.VariableSetType
{
    private static final long serialVersionUID = 1L;
    
    public VariableSetTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName VARIABLE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Variable");
    
    
    /**
     * Gets array of all "Variable" elements
     */
    public org.ogf.schemas.graap.wsAgreement.VariableType[] getVariableArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(VARIABLE$0, targetList);
            org.ogf.schemas.graap.wsAgreement.VariableType[] result = new org.ogf.schemas.graap.wsAgreement.VariableType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Variable" element
     */
    public org.ogf.schemas.graap.wsAgreement.VariableType getVariableArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.VariableType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.VariableType)get_store().find_element_user(VARIABLE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Variable" element
     */
    public int sizeOfVariableArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(VARIABLE$0);
        }
    }
    
    /**
     * Sets array of all "Variable" element
     */
    public void setVariableArray(org.ogf.schemas.graap.wsAgreement.VariableType[] variableArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(variableArray, VARIABLE$0);
        }
    }
    
    /**
     * Sets ith "Variable" element
     */
    public void setVariableArray(int i, org.ogf.schemas.graap.wsAgreement.VariableType variable)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.VariableType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.VariableType)get_store().find_element_user(VARIABLE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(variable);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Variable" element
     */
    public org.ogf.schemas.graap.wsAgreement.VariableType insertNewVariable(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.VariableType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.VariableType)get_store().insert_element_user(VARIABLE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Variable" element
     */
    public org.ogf.schemas.graap.wsAgreement.VariableType addNewVariable()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.VariableType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.VariableType)get_store().add_element_user(VARIABLE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Variable" element
     */
    public void removeVariable(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(VARIABLE$0, i);
        }
    }
}
