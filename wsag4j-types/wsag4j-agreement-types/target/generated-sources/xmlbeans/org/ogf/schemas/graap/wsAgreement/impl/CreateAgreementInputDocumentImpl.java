/*
 * An XML document type.
 * Localname: CreateAgreementInput
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.CreateAgreementInputDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one CreateAgreementInput(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class CreateAgreementInputDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.CreateAgreementInputDocument
{
    private static final long serialVersionUID = 1L;
    
    public CreateAgreementInputDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CREATEAGREEMENTINPUT$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "CreateAgreementInput");
    
    
    /**
     * Gets the "CreateAgreementInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.CreateAgreementInputType getCreateAgreementInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CreateAgreementInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CreateAgreementInputType)get_store().find_element_user(CREATEAGREEMENTINPUT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CreateAgreementInput" element
     */
    public void setCreateAgreementInput(org.ogf.schemas.graap.wsAgreement.CreateAgreementInputType createAgreementInput)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CreateAgreementInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CreateAgreementInputType)get_store().find_element_user(CREATEAGREEMENTINPUT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.CreateAgreementInputType)get_store().add_element_user(CREATEAGREEMENTINPUT$0);
            }
            target.set(createAgreementInput);
        }
    }
    
    /**
     * Appends and returns a new empty "CreateAgreementInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.CreateAgreementInputType addNewCreateAgreementInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CreateAgreementInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CreateAgreementInputType)get_store().add_element_user(CREATEAGREEMENTINPUT$0);
            return target;
        }
    }
}
