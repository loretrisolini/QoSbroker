/*
 * An XML document type.
 * Localname: AcceptAgreementResponse
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AcceptAgreementResponseDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one AcceptAgreementResponse(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class AcceptAgreementResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.AcceptAgreementResponseDocument
{
    private static final long serialVersionUID = 1L;
    
    public AcceptAgreementResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ACCEPTAGREEMENTRESPONSE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "AcceptAgreementResponse");
    
    
    /**
     * Gets the "AcceptAgreementResponse" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType getAcceptAgreementResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType)get_store().find_element_user(ACCEPTAGREEMENTRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AcceptAgreementResponse" element
     */
    public void setAcceptAgreementResponse(org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType acceptAgreementResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType)get_store().find_element_user(ACCEPTAGREEMENTRESPONSE$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType)get_store().add_element_user(ACCEPTAGREEMENTRESPONSE$0);
            }
            target.set(acceptAgreementResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "AcceptAgreementResponse" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType addNewAcceptAgreementResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType)get_store().add_element_user(ACCEPTAGREEMENTRESPONSE$0);
            return target;
        }
    }
}
