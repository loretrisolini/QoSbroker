/*
 * XML Type:  ServicePropertiesType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.ServicePropertiesType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML ServicePropertiesType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class ServicePropertiesTypeImpl extends org.ogf.schemas.graap.wsAgreement.impl.ServiceTermTypeImpl implements org.ogf.schemas.graap.wsAgreement.ServicePropertiesType
{
    private static final long serialVersionUID = 1L;
    
    public ServicePropertiesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName VARIABLESET$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "VariableSet");
    
    
    /**
     * Gets the "VariableSet" element
     */
    public org.ogf.schemas.graap.wsAgreement.VariableSetType getVariableSet()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.VariableSetType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.VariableSetType)get_store().find_element_user(VARIABLESET$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "VariableSet" element
     */
    public void setVariableSet(org.ogf.schemas.graap.wsAgreement.VariableSetType variableSet)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.VariableSetType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.VariableSetType)get_store().find_element_user(VARIABLESET$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.VariableSetType)get_store().add_element_user(VARIABLESET$0);
            }
            target.set(variableSet);
        }
    }
    
    /**
     * Appends and returns a new empty "VariableSet" element
     */
    public org.ogf.schemas.graap.wsAgreement.VariableSetType addNewVariableSet()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.VariableSetType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.VariableSetType)get_store().add_element_user(VARIABLESET$0);
            return target;
        }
    }
}
