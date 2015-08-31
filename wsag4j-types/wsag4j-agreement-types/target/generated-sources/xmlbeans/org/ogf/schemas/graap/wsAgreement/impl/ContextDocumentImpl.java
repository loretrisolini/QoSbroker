/*
 * An XML document type.
 * Localname: Context
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.ContextDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one Context(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class ContextDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.ContextDocument
{
    private static final long serialVersionUID = 1L;
    
    public ContextDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CONTEXT$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Context");
    
    
    /**
     * Gets the "Context" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementContextType getContext()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementContextType)get_store().find_element_user(CONTEXT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Context" element
     */
    public void setContext(org.ogf.schemas.graap.wsAgreement.AgreementContextType context)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementContextType)get_store().find_element_user(CONTEXT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementContextType)get_store().add_element_user(CONTEXT$0);
            }
            target.set(context);
        }
    }
    
    /**
     * Appends and returns a new empty "Context" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementContextType addNewContext()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementContextType)get_store().add_element_user(CONTEXT$0);
            return target;
        }
    }
}
