/*
 * An XML document type.
 * Localname: Terms
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.TermsDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one Terms(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class TermsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.TermsDocument
{
    private static final long serialVersionUID = 1L;
    
    public TermsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TERMS$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Terms");
    
    
    /**
     * Gets the "Terms" element
     */
    public org.ogf.schemas.graap.wsAgreement.TermTreeType getTerms()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermTreeType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermTreeType)get_store().find_element_user(TERMS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Terms" element
     */
    public void setTerms(org.ogf.schemas.graap.wsAgreement.TermTreeType terms)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermTreeType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermTreeType)get_store().find_element_user(TERMS$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.TermTreeType)get_store().add_element_user(TERMS$0);
            }
            target.set(terms);
        }
    }
    
    /**
     * Appends and returns a new empty "Terms" element
     */
    public org.ogf.schemas.graap.wsAgreement.TermTreeType addNewTerms()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermTreeType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermTreeType)get_store().add_element_user(TERMS$0);
            return target;
        }
    }
}
