/*
 * XML Type:  ConstraintSectionType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.ConstraintSectionType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML ConstraintSectionType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class ConstraintSectionTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.ConstraintSectionType
{
    private static final long serialVersionUID = 1L;
    
    public ConstraintSectionTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ITEM$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Item");
    private static final javax.xml.namespace.QName CONSTRAINT$2 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Constraint");
    
    
    /**
     * Gets array of all "Item" elements
     */
    public org.ogf.schemas.graap.wsAgreement.OfferItemType[] getItemArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ITEM$0, targetList);
            org.ogf.schemas.graap.wsAgreement.OfferItemType[] result = new org.ogf.schemas.graap.wsAgreement.OfferItemType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Item" element
     */
    public org.ogf.schemas.graap.wsAgreement.OfferItemType getItemArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.OfferItemType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.OfferItemType)get_store().find_element_user(ITEM$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Item" element
     */
    public int sizeOfItemArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ITEM$0);
        }
    }
    
    /**
     * Sets array of all "Item" element
     */
    public void setItemArray(org.ogf.schemas.graap.wsAgreement.OfferItemType[] itemArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(itemArray, ITEM$0);
        }
    }
    
    /**
     * Sets ith "Item" element
     */
    public void setItemArray(int i, org.ogf.schemas.graap.wsAgreement.OfferItemType item)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.OfferItemType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.OfferItemType)get_store().find_element_user(ITEM$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(item);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Item" element
     */
    public org.ogf.schemas.graap.wsAgreement.OfferItemType insertNewItem(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.OfferItemType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.OfferItemType)get_store().insert_element_user(ITEM$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Item" element
     */
    public org.ogf.schemas.graap.wsAgreement.OfferItemType addNewItem()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.OfferItemType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.OfferItemType)get_store().add_element_user(ITEM$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Item" element
     */
    public void removeItem(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ITEM$0, i);
        }
    }
    
    /**
     * Gets array of all "Constraint" elements
     */
    public org.apache.xmlbeans.XmlObject[] getConstraintArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(CONSTRAINT$2, targetList);
            org.apache.xmlbeans.XmlObject[] result = new org.apache.xmlbeans.XmlObject[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Constraint" element
     */
    public org.apache.xmlbeans.XmlObject getConstraintArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(CONSTRAINT$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Constraint" element
     */
    public int sizeOfConstraintArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CONSTRAINT$2);
        }
    }
    
    /**
     * Sets array of all "Constraint" element
     */
    public void setConstraintArray(org.apache.xmlbeans.XmlObject[] constraintArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(constraintArray, CONSTRAINT$2);
        }
    }
    
    /**
     * Sets ith "Constraint" element
     */
    public void setConstraintArray(int i, org.apache.xmlbeans.XmlObject constraint)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(CONSTRAINT$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(constraint);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Constraint" element
     */
    public org.apache.xmlbeans.XmlObject insertNewConstraint(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().insert_element_user(CONSTRAINT$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Constraint" element
     */
    public org.apache.xmlbeans.XmlObject addNewConstraint()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(CONSTRAINT$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "Constraint" element
     */
    public void removeConstraint(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CONSTRAINT$2, i);
        }
    }
}
