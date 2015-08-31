/*
 * An XML document type.
 * Localname: AcceptAgreementInput
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AcceptAgreementInputDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one AcceptAgreementInput(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class AcceptAgreementInputDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.AcceptAgreementInputDocument
{
    private static final long serialVersionUID = 1L;
    
    public AcceptAgreementInputDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ACCEPTAGREEMENTINPUT$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "AcceptAgreementInput");
    
    
    /**
     * Gets the "AcceptAgreementInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType getAcceptAgreementInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType)get_store().find_element_user(ACCEPTAGREEMENTINPUT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AcceptAgreementInput" element
     */
    public void setAcceptAgreementInput(org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType acceptAgreementInput)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType)get_store().find_element_user(ACCEPTAGREEMENTINPUT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType)get_store().add_element_user(ACCEPTAGREEMENTINPUT$0);
            }
            target.set(acceptAgreementInput);
        }
    }
    
    /**
     * Appends and returns a new empty "AcceptAgreementInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType addNewAcceptAgreementInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType)get_store().add_element_user(ACCEPTAGREEMENTINPUT$0);
            return target;
        }
    }
}
