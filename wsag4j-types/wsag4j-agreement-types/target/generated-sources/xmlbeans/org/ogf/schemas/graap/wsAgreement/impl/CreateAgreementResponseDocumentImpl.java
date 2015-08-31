/*
 * An XML document type.
 * Localname: CreateAgreementResponse
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.CreateAgreementResponseDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one CreateAgreementResponse(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class CreateAgreementResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.CreateAgreementResponseDocument
{
    private static final long serialVersionUID = 1L;
    
    public CreateAgreementResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CREATEAGREEMENTRESPONSE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "CreateAgreementResponse");
    
    
    /**
     * Gets the "CreateAgreementResponse" element
     */
    public org.ogf.schemas.graap.wsAgreement.CreateAgreementOutputType getCreateAgreementResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CreateAgreementOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CreateAgreementOutputType)get_store().find_element_user(CREATEAGREEMENTRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CreateAgreementResponse" element
     */
    public void setCreateAgreementResponse(org.ogf.schemas.graap.wsAgreement.CreateAgreementOutputType createAgreementResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CreateAgreementOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CreateAgreementOutputType)get_store().find_element_user(CREATEAGREEMENTRESPONSE$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.CreateAgreementOutputType)get_store().add_element_user(CREATEAGREEMENTRESPONSE$0);
            }
            target.set(createAgreementResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "CreateAgreementResponse" element
     */
    public org.ogf.schemas.graap.wsAgreement.CreateAgreementOutputType addNewCreateAgreementResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CreateAgreementOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CreateAgreementOutputType)get_store().add_element_user(CREATEAGREEMENTRESPONSE$0);
            return target;
        }
    }
}
