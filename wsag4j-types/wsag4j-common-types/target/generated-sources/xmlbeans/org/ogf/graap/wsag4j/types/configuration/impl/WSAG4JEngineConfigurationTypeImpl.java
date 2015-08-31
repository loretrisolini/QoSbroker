/*
 * XML Type:  WSAG4JEngineConfigurationType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineConfigurationType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * An XML WSAG4JEngineConfigurationType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public class WSAG4JEngineConfigurationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineConfigurationType
{
    private static final long serialVersionUID = 1L;
    
    public WSAG4JEngineConfigurationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESOURCEID$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "ResourceId");
    private static final javax.xml.namespace.QName FACTORY$2 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "Factory");
    private static final javax.xml.namespace.QName VALIDATOR$4 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "Validator");
    private static final javax.xml.namespace.QName ACTION$6 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "Action");
    
    
    /**
     * Gets the "ResourceId" element
     */
    public java.lang.String getResourceId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RESOURCEID$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "ResourceId" element
     */
    public org.apache.xmlbeans.XmlString xgetResourceId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(RESOURCEID$0, 0);
            return target;
        }
    }
    
    /**
     * True if has "ResourceId" element
     */
    public boolean isSetResourceId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(RESOURCEID$0) != 0;
        }
    }
    
    /**
     * Sets the "ResourceId" element
     */
    public void setResourceId(java.lang.String resourceId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RESOURCEID$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(RESOURCEID$0);
            }
            target.setStringValue(resourceId);
        }
    }
    
    /**
     * Sets (as xml) the "ResourceId" element
     */
    public void xsetResourceId(org.apache.xmlbeans.XmlString resourceId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(RESOURCEID$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(RESOURCEID$0);
            }
            target.set(resourceId);
        }
    }
    
    /**
     * Unsets the "ResourceId" element
     */
    public void unsetResourceId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(RESOURCEID$0, 0);
        }
    }
    
    /**
     * Gets the "Factory" element
     */
    public org.ogf.graap.wsag4j.types.configuration.FactoryConfigurationType getFactory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.FactoryConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.FactoryConfigurationType)get_store().find_element_user(FACTORY$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Factory" element
     */
    public void setFactory(org.ogf.graap.wsag4j.types.configuration.FactoryConfigurationType factory)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.FactoryConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.FactoryConfigurationType)get_store().find_element_user(FACTORY$2, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.FactoryConfigurationType)get_store().add_element_user(FACTORY$2);
            }
            target.set(factory);
        }
    }
    
    /**
     * Appends and returns a new empty "Factory" element
     */
    public org.ogf.graap.wsag4j.types.configuration.FactoryConfigurationType addNewFactory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.FactoryConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.FactoryConfigurationType)get_store().add_element_user(FACTORY$2);
            return target;
        }
    }
    
    /**
     * Gets the "Validator" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ValidatorType getValidator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ValidatorType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ValidatorType)get_store().find_element_user(VALIDATOR$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Validator" element
     */
    public boolean isSetValidator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(VALIDATOR$4) != 0;
        }
    }
    
    /**
     * Sets the "Validator" element
     */
    public void setValidator(org.ogf.graap.wsag4j.types.configuration.ValidatorType validator)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ValidatorType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ValidatorType)get_store().find_element_user(VALIDATOR$4, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.ValidatorType)get_store().add_element_user(VALIDATOR$4);
            }
            target.set(validator);
        }
    }
    
    /**
     * Appends and returns a new empty "Validator" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ValidatorType addNewValidator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ValidatorType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ValidatorType)get_store().add_element_user(VALIDATOR$4);
            return target;
        }
    }
    
    /**
     * Unsets the "Validator" element
     */
    public void unsetValidator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(VALIDATOR$4, 0);
        }
    }
    
    /**
     * Gets array of all "Action" elements
     */
    public org.ogf.graap.wsag4j.types.configuration.ActionType[] getActionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ACTION$6, targetList);
            org.ogf.graap.wsag4j.types.configuration.ActionType[] result = new org.ogf.graap.wsag4j.types.configuration.ActionType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Action" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ActionType getActionArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ActionType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ActionType)get_store().find_element_user(ACTION$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Action" element
     */
    public int sizeOfActionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ACTION$6);
        }
    }
    
    /**
     * Sets array of all "Action" element
     */
    public void setActionArray(org.ogf.graap.wsag4j.types.configuration.ActionType[] actionArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(actionArray, ACTION$6);
        }
    }
    
    /**
     * Sets ith "Action" element
     */
    public void setActionArray(int i, org.ogf.graap.wsag4j.types.configuration.ActionType action)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ActionType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ActionType)get_store().find_element_user(ACTION$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(action);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Action" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ActionType insertNewAction(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ActionType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ActionType)get_store().insert_element_user(ACTION$6, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Action" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ActionType addNewAction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ActionType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ActionType)get_store().add_element_user(ACTION$6);
            return target;
        }
    }
    
    /**
     * Removes the ith "Action" element
     */
    public void removeAction(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ACTION$6, i);
        }
    }
}
