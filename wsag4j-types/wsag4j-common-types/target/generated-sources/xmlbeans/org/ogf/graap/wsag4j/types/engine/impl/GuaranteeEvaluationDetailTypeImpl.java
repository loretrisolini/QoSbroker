/*
 * XML Type:  GuaranteeEvaluationDetailType
 * Namespace: http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine
 * Java type: org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationDetailType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.engine.impl;
/**
 * An XML GuaranteeEvaluationDetailType(@http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine).
 *
 * This is a complex type.
 */
public class GuaranteeEvaluationDetailTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationDetailType
{
    private static final long serialVersionUID = 1L;
    
    public GuaranteeEvaluationDetailTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GUARANTEE$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "Guarantee");
    private static final javax.xml.namespace.QName GUARANTEESTATE$2 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "GuaranteeState");
    
    
    /**
     * Gets the "Guarantee" element
     */
    public org.ogf.schemas.graap.wsAgreement.GuaranteeTermType getGuarantee()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType)get_store().find_element_user(GUARANTEE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Guarantee" element
     */
    public void setGuarantee(org.ogf.schemas.graap.wsAgreement.GuaranteeTermType guarantee)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType)get_store().find_element_user(GUARANTEE$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType)get_store().add_element_user(GUARANTEE$0);
            }
            target.set(guarantee);
        }
    }
    
    /**
     * Appends and returns a new empty "Guarantee" element
     */
    public org.ogf.schemas.graap.wsAgreement.GuaranteeTermType addNewGuarantee()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType)get_store().add_element_user(GUARANTEE$0);
            return target;
        }
    }
    
    /**
     * Gets the "GuaranteeState" element
     */
    public org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType getGuaranteeState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType)get_store().find_element_user(GUARANTEESTATE$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "GuaranteeState" element
     */
    public void setGuaranteeState(org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType guaranteeState)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType)get_store().find_element_user(GUARANTEESTATE$2, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType)get_store().add_element_user(GUARANTEESTATE$2);
            }
            target.set(guaranteeState);
        }
    }
    
    /**
     * Appends and returns a new empty "GuaranteeState" element
     */
    public org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType addNewGuaranteeState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType)get_store().add_element_user(GUARANTEESTATE$2);
            return target;
        }
    }
}
