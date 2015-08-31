/*
 * An XML document type.
 * Localname: RejectAgreementInput
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.RejectAgreementInputDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one RejectAgreementInput(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class RejectAgreementInputDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.RejectAgreementInputDocument
{
    private static final long serialVersionUID = 1L;
    
    public RejectAgreementInputDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName REJECTAGREEMENTINPUT$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "RejectAgreementInput");
    
    
    /**
     * Gets the "RejectAgreementInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType getRejectAgreementInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType)get_store().find_element_user(REJECTAGREEMENTINPUT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "RejectAgreementInput" element
     */
    public void setRejectAgreementInput(org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType rejectAgreementInput)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType)get_store().find_element_user(REJECTAGREEMENTINPUT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType)get_store().add_element_user(REJECTAGREEMENTINPUT$0);
            }
            target.set(rejectAgreementInput);
        }
    }
    
    /**
     * Appends and returns a new empty "RejectAgreementInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType addNewRejectAgreementInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceInputType)get_store().add_element_user(REJECTAGREEMENTINPUT$0);
            return target;
        }
    }
}
