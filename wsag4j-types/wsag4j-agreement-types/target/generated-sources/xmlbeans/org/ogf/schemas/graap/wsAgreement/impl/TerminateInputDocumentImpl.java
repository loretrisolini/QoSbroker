/*
 * An XML document type.
 * Localname: TerminateInput
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.TerminateInputDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one TerminateInput(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class TerminateInputDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.TerminateInputDocument
{
    private static final long serialVersionUID = 1L;
    
    public TerminateInputDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TERMINATEINPUT$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "TerminateInput");
    
    
    /**
     * Gets the "TerminateInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.TerminateInputType getTerminateInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TerminateInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TerminateInputType)get_store().find_element_user(TERMINATEINPUT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TerminateInput" element
     */
    public void setTerminateInput(org.ogf.schemas.graap.wsAgreement.TerminateInputType terminateInput)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TerminateInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TerminateInputType)get_store().find_element_user(TERMINATEINPUT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.TerminateInputType)get_store().add_element_user(TERMINATEINPUT$0);
            }
            target.set(terminateInput);
        }
    }
    
    /**
     * Appends and returns a new empty "TerminateInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.TerminateInputType addNewTerminateInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TerminateInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TerminateInputType)get_store().add_element_user(TERMINATEINPUT$0);
            return target;
        }
    }
}
