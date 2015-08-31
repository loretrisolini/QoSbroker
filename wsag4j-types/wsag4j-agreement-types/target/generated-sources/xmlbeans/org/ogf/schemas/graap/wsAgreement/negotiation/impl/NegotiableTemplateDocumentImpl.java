/*
 * An XML document type.
 * Localname: NegotiableTemplate
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiableTemplateDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * A document containing one NegotiableTemplate(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation) element.
 *
 * This is a complex type.
 */
public class NegotiableTemplateDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiableTemplateDocument
{
    private static final long serialVersionUID = 1L;
    
    public NegotiableTemplateDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEGOTIABLETEMPLATE$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiableTemplate");
    
    
    /**
     * Gets the "NegotiableTemplate" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementTemplateType getNegotiableTemplate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementTemplateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementTemplateType)get_store().find_element_user(NEGOTIABLETEMPLATE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NegotiableTemplate" element
     */
    public void setNegotiableTemplate(org.ogf.schemas.graap.wsAgreement.AgreementTemplateType negotiableTemplate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementTemplateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementTemplateType)get_store().find_element_user(NEGOTIABLETEMPLATE$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementTemplateType)get_store().add_element_user(NEGOTIABLETEMPLATE$0);
            }
            target.set(negotiableTemplate);
        }
    }
    
    /**
     * Appends and returns a new empty "NegotiableTemplate" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementTemplateType addNewNegotiableTemplate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementTemplateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementTemplateType)get_store().add_element_user(NEGOTIABLETEMPLATE$0);
            return target;
        }
    }
}
