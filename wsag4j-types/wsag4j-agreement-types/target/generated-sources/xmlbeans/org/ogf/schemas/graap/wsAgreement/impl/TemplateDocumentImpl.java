/*
 * An XML document type.
 * Localname: Template
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.TemplateDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one Template(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class TemplateDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.TemplateDocument
{
    private static final long serialVersionUID = 1L;
    
    public TemplateDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TEMPLATE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Template");
    
    
    /**
     * Gets the "Template" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementTemplateType getTemplate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementTemplateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementTemplateType)get_store().find_element_user(TEMPLATE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Template" element
     */
    public void setTemplate(org.ogf.schemas.graap.wsAgreement.AgreementTemplateType template)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementTemplateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementTemplateType)get_store().find_element_user(TEMPLATE$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementTemplateType)get_store().add_element_user(TEMPLATE$0);
            }
            target.set(template);
        }
    }
    
    /**
     * Appends and returns a new empty "Template" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementTemplateType addNewTemplate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementTemplateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementTemplateType)get_store().add_element_user(TEMPLATE$0);
            return target;
        }
    }
}
