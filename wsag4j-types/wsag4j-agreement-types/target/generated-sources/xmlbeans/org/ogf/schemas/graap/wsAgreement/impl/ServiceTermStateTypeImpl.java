/*
 * XML Type:  ServiceTermStateType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.ServiceTermStateType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML ServiceTermStateType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class ServiceTermStateTypeImpl extends org.ogf.schemas.graap.wsAgreement.impl.TermStateTypeImpl implements org.ogf.schemas.graap.wsAgreement.ServiceTermStateType
{
    private static final long serialVersionUID = 1L;
    
    public ServiceTermStateTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName STATE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "State");
    private static final javax.xml.namespace.QName PROCESSING$2 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Processing");
    private static final javax.xml.namespace.QName IDLE$4 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Idle");
    
    
    /**
     * Gets the "State" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceTermStateDefinition.Enum getState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATE$0, 0);
            if (target == null)
            {
                return null;
            }
            return (org.ogf.schemas.graap.wsAgreement.ServiceTermStateDefinition.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "State" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceTermStateDefinition xgetState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceTermStateDefinition target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceTermStateDefinition)get_store().find_element_user(STATE$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "State" element
     */
    public void setState(org.ogf.schemas.graap.wsAgreement.ServiceTermStateDefinition.Enum state)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATE$0);
            }
            target.setEnumValue(state);
        }
    }
    
    /**
     * Sets (as xml) the "State" element
     */
    public void xsetState(org.ogf.schemas.graap.wsAgreement.ServiceTermStateDefinition state)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceTermStateDefinition target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceTermStateDefinition)get_store().find_element_user(STATE$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.ServiceTermStateDefinition)get_store().add_element_user(STATE$0);
            }
            target.set(state);
        }
    }
    
    /**
     * Gets the "Processing" element
     */
    public org.ogf.schemas.graap.wsAgreement.InnerTermStateType getProcessing()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.InnerTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.InnerTermStateType)get_store().find_element_user(PROCESSING$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Processing" element
     */
    public boolean isSetProcessing()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(PROCESSING$2) != 0;
        }
    }
    
    /**
     * Sets the "Processing" element
     */
    public void setProcessing(org.ogf.schemas.graap.wsAgreement.InnerTermStateType processing)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.InnerTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.InnerTermStateType)get_store().find_element_user(PROCESSING$2, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.InnerTermStateType)get_store().add_element_user(PROCESSING$2);
            }
            target.set(processing);
        }
    }
    
    /**
     * Appends and returns a new empty "Processing" element
     */
    public org.ogf.schemas.graap.wsAgreement.InnerTermStateType addNewProcessing()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.InnerTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.InnerTermStateType)get_store().add_element_user(PROCESSING$2);
            return target;
        }
    }
    
    /**
     * Unsets the "Processing" element
     */
    public void unsetProcessing()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(PROCESSING$2, 0);
        }
    }
    
    /**
     * Gets the "Idle" element
     */
    public org.ogf.schemas.graap.wsAgreement.InnerTermStateType getIdle()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.InnerTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.InnerTermStateType)get_store().find_element_user(IDLE$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Idle" element
     */
    public boolean isSetIdle()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(IDLE$4) != 0;
        }
    }
    
    /**
     * Sets the "Idle" element
     */
    public void setIdle(org.ogf.schemas.graap.wsAgreement.InnerTermStateType idle)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.InnerTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.InnerTermStateType)get_store().find_element_user(IDLE$4, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.InnerTermStateType)get_store().add_element_user(IDLE$4);
            }
            target.set(idle);
        }
    }
    
    /**
     * Appends and returns a new empty "Idle" element
     */
    public org.ogf.schemas.graap.wsAgreement.InnerTermStateType addNewIdle()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.InnerTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.InnerTermStateType)get_store().add_element_user(IDLE$4);
            return target;
        }
    }
    
    /**
     * Unsets the "Idle" element
     */
    public void unsetIdle()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(IDLE$4, 0);
        }
    }
}
