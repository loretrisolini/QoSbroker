/*
 * An XML document type.
 * Localname: CreatePendingAgreementResponse
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementResponseDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one CreatePendingAgreementResponse(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class CreatePendingAgreementResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementResponseDocument
{
    private static final long serialVersionUID = 1L;
    
    public CreatePendingAgreementResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CREATEPENDINGAGREEMENTRESPONSE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "CreatePendingAgreementResponse");
    
    
    /**
     * Gets the "CreatePendingAgreementResponse" element
     */
    public org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementOutputType getCreatePendingAgreementResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementOutputType)get_store().find_element_user(CREATEPENDINGAGREEMENTRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CreatePendingAgreementResponse" element
     */
    public void setCreatePendingAgreementResponse(org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementOutputType createPendingAgreementResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementOutputType)get_store().find_element_user(CREATEPENDINGAGREEMENTRESPONSE$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementOutputType)get_store().add_element_user(CREATEPENDINGAGREEMENTRESPONSE$0);
            }
            target.set(createPendingAgreementResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "CreatePendingAgreementResponse" element
     */
    public org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementOutputType addNewCreatePendingAgreementResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementOutputType)get_store().add_element_user(CREATEPENDINGAGREEMENTRESPONSE$0);
            return target;
        }
    }
}
