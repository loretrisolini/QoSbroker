/*
 * An XML document type.
 * Localname: CreatePendingAgreementInput
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one CreatePendingAgreementInput(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class CreatePendingAgreementInputDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputDocument
{
    private static final long serialVersionUID = 1L;
    
    public CreatePendingAgreementInputDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CREATEPENDINGAGREEMENTINPUT$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "CreatePendingAgreementInput");
    
    
    /**
     * Gets the "CreatePendingAgreementInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType getCreatePendingAgreementInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType)get_store().find_element_user(CREATEPENDINGAGREEMENTINPUT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CreatePendingAgreementInput" element
     */
    public void setCreatePendingAgreementInput(org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType createPendingAgreementInput)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType)get_store().find_element_user(CREATEPENDINGAGREEMENTINPUT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType)get_store().add_element_user(CREATEPENDINGAGREEMENTINPUT$0);
            }
            target.set(createPendingAgreementInput);
        }
    }
    
    /**
     * Appends and returns a new empty "CreatePendingAgreementInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType addNewCreatePendingAgreementInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType)get_store().add_element_user(CREATEPENDINGAGREEMENTINPUT$0);
            return target;
        }
    }
}
