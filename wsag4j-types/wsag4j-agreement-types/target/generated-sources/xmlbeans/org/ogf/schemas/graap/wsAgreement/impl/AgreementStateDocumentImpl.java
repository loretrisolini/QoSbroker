/*
 * An XML document type.
 * Localname: AgreementState
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AgreementStateDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one AgreementState(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class AgreementStateDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.AgreementStateDocument
{
    private static final long serialVersionUID = 1L;
    
    public AgreementStateDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AGREEMENTSTATE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "AgreementState");
    
    
    /**
     * Gets the "AgreementState" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementStateType getAgreementState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementStateType)get_store().find_element_user(AGREEMENTSTATE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AgreementState" element
     */
    public void setAgreementState(org.ogf.schemas.graap.wsAgreement.AgreementStateType agreementState)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementStateType)get_store().find_element_user(AGREEMENTSTATE$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementStateType)get_store().add_element_user(AGREEMENTSTATE$0);
            }
            target.set(agreementState);
        }
    }
    
    /**
     * Appends and returns a new empty "AgreementState" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementStateType addNewAgreementState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementStateType)get_store().add_element_user(AGREEMENTSTATE$0);
            return target;
        }
    }
}
