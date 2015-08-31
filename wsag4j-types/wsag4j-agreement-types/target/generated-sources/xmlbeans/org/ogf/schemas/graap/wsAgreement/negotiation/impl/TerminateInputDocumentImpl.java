/*
 * An XML document type.
 * Localname: TerminateInput
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.TerminateInputDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * A document containing one TerminateInput(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation) element.
 *
 * This is a complex type.
 */
public class TerminateInputDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.TerminateInputDocument
{
    private static final long serialVersionUID = 1L;
    
    public TerminateInputDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TERMINATEINPUT$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "TerminateInput");
    
    
    /**
     * Gets the "TerminateInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.TerminateInputType getTerminateInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.TerminateInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.TerminateInputType)get_store().find_element_user(TERMINATEINPUT$0, 0);
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
    public void setTerminateInput(org.ogf.schemas.graap.wsAgreement.negotiation.TerminateInputType terminateInput)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.TerminateInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.TerminateInputType)get_store().find_element_user(TERMINATEINPUT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.TerminateInputType)get_store().add_element_user(TERMINATEINPUT$0);
            }
            target.set(terminateInput);
        }
    }
    
    /**
     * Appends and returns a new empty "TerminateInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.TerminateInputType addNewTerminateInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.TerminateInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.TerminateInputType)get_store().add_element_user(TERMINATEINPUT$0);
            return target;
        }
    }
}
