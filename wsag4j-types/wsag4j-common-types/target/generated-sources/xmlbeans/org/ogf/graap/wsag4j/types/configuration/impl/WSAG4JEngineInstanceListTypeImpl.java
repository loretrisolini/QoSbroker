/*
 * XML Type:  WSAG4JEngineInstanceListType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceListType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * An XML WSAG4JEngineInstanceListType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public class WSAG4JEngineInstanceListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceListType
{
    private static final long serialVersionUID = 1L;
    
    public WSAG4JEngineInstanceListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName WSAG4JENGINE$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "WSAG4JEngine");
    
    
    /**
     * Gets array of all "WSAG4JEngine" elements
     */
    public org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType[] getWSAG4JEngineArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(WSAG4JENGINE$0, targetList);
            org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType[] result = new org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "WSAG4JEngine" element
     */
    public org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType getWSAG4JEngineArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType)get_store().find_element_user(WSAG4JENGINE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "WSAG4JEngine" element
     */
    public int sizeOfWSAG4JEngineArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(WSAG4JENGINE$0);
        }
    }
    
    /**
     * Sets array of all "WSAG4JEngine" element
     */
    public void setWSAG4JEngineArray(org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType[] wsag4JEngineArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(wsag4JEngineArray, WSAG4JENGINE$0);
        }
    }
    
    /**
     * Sets ith "WSAG4JEngine" element
     */
    public void setWSAG4JEngineArray(int i, org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType wsag4JEngine)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType)get_store().find_element_user(WSAG4JENGINE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(wsag4JEngine);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "WSAG4JEngine" element
     */
    public org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType insertNewWSAG4JEngine(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType)get_store().insert_element_user(WSAG4JENGINE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "WSAG4JEngine" element
     */
    public org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType addNewWSAG4JEngine()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceType)get_store().add_element_user(WSAG4JENGINE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "WSAG4JEngine" element
     */
    public void removeWSAG4JEngine(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(WSAG4JENGINE$0, i);
        }
    }
}
