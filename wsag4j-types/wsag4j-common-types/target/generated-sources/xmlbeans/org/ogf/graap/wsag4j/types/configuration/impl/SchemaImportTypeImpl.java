/*
 * XML Type:  SchemaImportType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.SchemaImportType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * An XML SchemaImportType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public class SchemaImportTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.SchemaImportType
{
    private static final long serialVersionUID = 1L;
    
    public SchemaImportTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SCHEMAFILENAME$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "SchemaFilename");
    
    
    /**
     * Gets array of all "SchemaFilename" elements
     */
    public java.lang.String[] getSchemaFilenameArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SCHEMAFILENAME$0, targetList);
            java.lang.String[] result = new java.lang.String[targetList.size()];
            for (int i = 0, len = targetList.size() ; i < len ; i++)
                result[i] = ((org.apache.xmlbeans.SimpleValue)targetList.get(i)).getStringValue();
            return result;
        }
    }
    
    /**
     * Gets ith "SchemaFilename" element
     */
    public java.lang.String getSchemaFilenameArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SCHEMAFILENAME$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) array of all "SchemaFilename" elements
     */
    public org.apache.xmlbeans.XmlString[] xgetSchemaFilenameArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SCHEMAFILENAME$0, targetList);
            org.apache.xmlbeans.XmlString[] result = new org.apache.xmlbeans.XmlString[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets (as xml) ith "SchemaFilename" element
     */
    public org.apache.xmlbeans.XmlString xgetSchemaFilenameArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SCHEMAFILENAME$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return (org.apache.xmlbeans.XmlString)target;
        }
    }
    
    /**
     * Returns number of "SchemaFilename" element
     */
    public int sizeOfSchemaFilenameArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SCHEMAFILENAME$0);
        }
    }
    
    /**
     * Sets array of all "SchemaFilename" element
     */
    public void setSchemaFilenameArray(java.lang.String[] schemaFilenameArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(schemaFilenameArray, SCHEMAFILENAME$0);
        }
    }
    
    /**
     * Sets ith "SchemaFilename" element
     */
    public void setSchemaFilenameArray(int i, java.lang.String schemaFilename)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SCHEMAFILENAME$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(schemaFilename);
        }
    }
    
    /**
     * Sets (as xml) array of all "SchemaFilename" element
     */
    public void xsetSchemaFilenameArray(org.apache.xmlbeans.XmlString[]schemaFilenameArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(schemaFilenameArray, SCHEMAFILENAME$0);
        }
    }
    
    /**
     * Sets (as xml) ith "SchemaFilename" element
     */
    public void xsetSchemaFilenameArray(int i, org.apache.xmlbeans.XmlString schemaFilename)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SCHEMAFILENAME$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(schemaFilename);
        }
    }
    
    /**
     * Inserts the value as the ith "SchemaFilename" element
     */
    public void insertSchemaFilename(int i, java.lang.String schemaFilename)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = 
                (org.apache.xmlbeans.SimpleValue)get_store().insert_element_user(SCHEMAFILENAME$0, i);
            target.setStringValue(schemaFilename);
        }
    }
    
    /**
     * Appends the value as the last "SchemaFilename" element
     */
    public void addSchemaFilename(java.lang.String schemaFilename)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SCHEMAFILENAME$0);
            target.setStringValue(schemaFilename);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "SchemaFilename" element
     */
    public org.apache.xmlbeans.XmlString insertNewSchemaFilename(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().insert_element_user(SCHEMAFILENAME$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "SchemaFilename" element
     */
    public org.apache.xmlbeans.XmlString addNewSchemaFilename()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(SCHEMAFILENAME$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "SchemaFilename" element
     */
    public void removeSchemaFilename(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SCHEMAFILENAME$0, i);
        }
    }
}
