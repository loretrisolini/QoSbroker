/*
 * XML Type:  AgreementStateType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AgreementStateType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML AgreementStateType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class AgreementStateTypeImpl extends org.ogf.schemas.graap.wsAgreement.impl.InnerAgreementStateTypeImpl implements org.ogf.schemas.graap.wsAgreement.AgreementStateType
{
    private static final long serialVersionUID = 1L;
    
    public AgreementStateTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName STATE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "State");
    
    
    /**
     * Gets the "State" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementStateDefinition.Enum getState()
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
            return (org.ogf.schemas.graap.wsAgreement.AgreementStateDefinition.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "State" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementStateDefinition xgetState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementStateDefinition target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementStateDefinition)get_store().find_element_user(STATE$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "State" element
     */
    public void setState(org.ogf.schemas.graap.wsAgreement.AgreementStateDefinition.Enum state)
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
    public void xsetState(org.ogf.schemas.graap.wsAgreement.AgreementStateDefinition state)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementStateDefinition target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementStateDefinition)get_store().find_element_user(STATE$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementStateDefinition)get_store().add_element_user(STATE$0);
            }
            target.set(state);
        }
    }
}
