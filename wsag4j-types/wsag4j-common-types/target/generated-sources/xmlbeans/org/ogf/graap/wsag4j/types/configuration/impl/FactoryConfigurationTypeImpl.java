/*
 * XML Type:  FactoryConfigurationType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.FactoryConfigurationType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * An XML FactoryConfigurationType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public class FactoryConfigurationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.FactoryConfigurationType
{
    private static final long serialVersionUID = 1L;
    
    public FactoryConfigurationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FACTORYIMPLEMENTATION$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "FactoryImplementation");
    private static final javax.xml.namespace.QName PERSISTENCEIMPLEMENTATION$2 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "PersistenceImplementation");
    
    
    /**
     * Gets the "FactoryImplementation" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType getFactoryImplementation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().find_element_user(FACTORYIMPLEMENTATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "FactoryImplementation" element
     */
    public void setFactoryImplementation(org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType factoryImplementation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().find_element_user(FACTORYIMPLEMENTATION$0, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().add_element_user(FACTORYIMPLEMENTATION$0);
            }
            target.set(factoryImplementation);
        }
    }
    
    /**
     * Appends and returns a new empty "FactoryImplementation" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType addNewFactoryImplementation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().add_element_user(FACTORYIMPLEMENTATION$0);
            return target;
        }
    }
    
    /**
     * Gets the "PersistenceImplementation" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType getPersistenceImplementation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().find_element_user(PERSISTENCEIMPLEMENTATION$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "PersistenceImplementation" element
     */
    public void setPersistenceImplementation(org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType persistenceImplementation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().find_element_user(PERSISTENCEIMPLEMENTATION$2, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().add_element_user(PERSISTENCEIMPLEMENTATION$2);
            }
            target.set(persistenceImplementation);
        }
    }
    
    /**
     * Appends and returns a new empty "PersistenceImplementation" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType addNewPersistenceImplementation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType)get_store().add_element_user(PERSISTENCEIMPLEMENTATION$2);
            return target;
        }
    }
}
