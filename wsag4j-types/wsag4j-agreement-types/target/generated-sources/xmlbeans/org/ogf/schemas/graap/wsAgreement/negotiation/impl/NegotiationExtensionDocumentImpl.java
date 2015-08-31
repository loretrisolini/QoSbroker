/*
 * An XML document type.
 * Localname: NegotiationExtension
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationExtensionDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * A document containing one NegotiationExtension(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation) element.
 *
 * This is a complex type.
 */
public class NegotiationExtensionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationExtensionDocument
{
    private static final long serialVersionUID = 1L;
    
    public NegotiationExtensionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEGOTIATIONEXTENSION$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationExtension");
    
    
    /**
     * Gets the "NegotiationExtension" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationExtensionType getNegotiationExtension()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationExtensionType)get_store().find_element_user(NEGOTIATIONEXTENSION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NegotiationExtension" element
     */
    public void setNegotiationExtension(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationExtensionType negotiationExtension)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationExtensionType)get_store().find_element_user(NEGOTIATIONEXTENSION$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationExtensionType)get_store().add_element_user(NEGOTIATIONEXTENSION$0);
            }
            target.set(negotiationExtension);
        }
    }
    
    /**
     * Appends and returns a new empty "NegotiationExtension" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationExtensionType addNewNegotiationExtension()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationExtensionType)get_store().add_element_user(NEGOTIATIONEXTENSION$0);
            return target;
        }
    }
}
