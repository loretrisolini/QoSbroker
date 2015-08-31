/*
 * XML Type:  PersistenceAgreementContextType
 * Namespace: http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine
 * Java type: org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.engine.impl;
/**
 * An XML PersistenceAgreementContextType(@http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine).
 *
 * This is a complex type.
 */
public class PersistenceAgreementContextTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType
{
    private static final long serialVersionUID = 1L;
    
    public PersistenceAgreementContextTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AGREEMENTPROPERTIES$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "AgreementProperties");
    private static final javax.xml.namespace.QName ENTRY$2 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "Entry");
    
    
    /**
     * Gets the "AgreementProperties" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType getAgreementProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType)get_store().find_element_user(AGREEMENTPROPERTIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AgreementProperties" element
     */
    public void setAgreementProperties(org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType agreementProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType)get_store().find_element_user(AGREEMENTPROPERTIES$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType)get_store().add_element_user(AGREEMENTPROPERTIES$0);
            }
            target.set(agreementProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "AgreementProperties" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType addNewAgreementProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType)get_store().add_element_user(AGREEMENTPROPERTIES$0);
            return target;
        }
    }
    
    /**
     * Gets array of all "Entry" elements
     */
    public org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry[] getEntryArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ENTRY$2, targetList);
            org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry[] result = new org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Entry" element
     */
    public org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry getEntryArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry target = null;
            target = (org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry)get_store().find_element_user(ENTRY$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Entry" element
     */
    public int sizeOfEntryArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ENTRY$2);
        }
    }
    
    /**
     * Sets array of all "Entry" element
     */
    public void setEntryArray(org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry[] entryArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(entryArray, ENTRY$2);
        }
    }
    
    /**
     * Sets ith "Entry" element
     */
    public void setEntryArray(int i, org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry entry)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry target = null;
            target = (org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry)get_store().find_element_user(ENTRY$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(entry);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Entry" element
     */
    public org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry insertNewEntry(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry target = null;
            target = (org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry)get_store().insert_element_user(ENTRY$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Entry" element
     */
    public org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry addNewEntry()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry target = null;
            target = (org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry)get_store().add_element_user(ENTRY$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "Entry" element
     */
    public void removeEntry(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ENTRY$2, i);
        }
    }
    /**
     * An XML Entry(@http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine).
     *
     * This is a complex type.
     */
    public static class EntryImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType.Entry
    {
        private static final long serialVersionUID = 1L;
        
        public EntryImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName VALUE$0 = 
            new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "Value");
        private static final javax.xml.namespace.QName NAME$2 = 
            new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "Name");
        
        
        /**
         * Gets the "Value" element
         */
        public org.apache.xmlbeans.XmlObject getValue()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlObject target = null;
                target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(VALUE$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "Value" element
         */
        public void setValue(org.apache.xmlbeans.XmlObject value)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlObject target = null;
                target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(VALUE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(VALUE$0);
                }
                target.set(value);
            }
        }
        
        /**
         * Appends and returns a new empty "Value" element
         */
        public org.apache.xmlbeans.XmlObject addNewValue()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlObject target = null;
                target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(VALUE$0);
                return target;
            }
        }
        
        /**
         * Gets the "Name" attribute
         */
        public java.lang.String getName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$2);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "Name" attribute
         */
        public org.apache.xmlbeans.XmlString xgetName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$2);
                return target;
            }
        }
        
        /**
         * Sets the "Name" attribute
         */
        public void setName(java.lang.String name)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$2);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(NAME$2);
                }
                target.setStringValue(name);
            }
        }
        
        /**
         * Sets (as xml) the "Name" attribute
         */
        public void xsetName(org.apache.xmlbeans.XmlString name)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$2);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(NAME$2);
                }
                target.set(name);
            }
        }
    }
}
