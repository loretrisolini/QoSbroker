/*
 * An XML document type.
 * Localname: RejectAgreementResponse
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.RejectAgreementResponseDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one RejectAgreementResponse(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class RejectAgreementResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.RejectAgreementResponseDocument
{
    private static final long serialVersionUID = 1L;
    
    public RejectAgreementResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName REJECTAGREEMENTRESPONSE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "RejectAgreementResponse");
    
    
    /**
     * Gets the "RejectAgreementResponse" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType getRejectAgreementResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType)get_store().find_element_user(REJECTAGREEMENTRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "RejectAgreementResponse" element
     */
    public void setRejectAgreementResponse(org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType rejectAgreementResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType)get_store().find_element_user(REJECTAGREEMENTRESPONSE$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType)get_store().add_element_user(REJECTAGREEMENTRESPONSE$0);
            }
            target.set(rejectAgreementResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "RejectAgreementResponse" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType addNewRejectAgreementResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementAcceptanceOutputType)get_store().add_element_user(REJECTAGREEMENTRESPONSE$0);
            return target;
        }
    }
}
