/*
 * XML Type:  HandlerChainType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.HandlerChainType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * An XML HandlerChainType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public class HandlerChainTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.HandlerChainType
{
    private static final long serialVersionUID = 1L;
    
    public HandlerChainTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName HANDLER$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "Handler");
    
    
    /**
     * Gets array of all "Handler" elements
     */
    public org.ogf.graap.wsag4j.types.configuration.HandlerType[] getHandlerArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(HANDLER$0, targetList);
            org.ogf.graap.wsag4j.types.configuration.HandlerType[] result = new org.ogf.graap.wsag4j.types.configuration.HandlerType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Handler" element
     */
    public org.ogf.graap.wsag4j.types.configuration.HandlerType getHandlerArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.HandlerType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.HandlerType)get_store().find_element_user(HANDLER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Handler" element
     */
    public int sizeOfHandlerArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(HANDLER$0);
        }
    }
    
    /**
     * Sets array of all "Handler" element
     */
    public void setHandlerArray(org.ogf.graap.wsag4j.types.configuration.HandlerType[] handlerArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(handlerArray, HANDLER$0);
        }
    }
    
    /**
     * Sets ith "Handler" element
     */
    public void setHandlerArray(int i, org.ogf.graap.wsag4j.types.configuration.HandlerType handler)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.HandlerType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.HandlerType)get_store().find_element_user(HANDLER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(handler);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Handler" element
     */
    public org.ogf.graap.wsag4j.types.configuration.HandlerType insertNewHandler(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.HandlerType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.HandlerType)get_store().insert_element_user(HANDLER$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Handler" element
     */
    public org.ogf.graap.wsag4j.types.configuration.HandlerType addNewHandler()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.HandlerType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.HandlerType)get_store().add_element_user(HANDLER$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Handler" element
     */
    public void removeHandler(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(HANDLER$0, i);
        }
    }
}
