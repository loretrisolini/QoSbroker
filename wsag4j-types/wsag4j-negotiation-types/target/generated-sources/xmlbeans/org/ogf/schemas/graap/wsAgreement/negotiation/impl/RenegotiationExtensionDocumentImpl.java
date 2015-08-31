/*
 * An XML document type.
 * Localname: RenegotiationExtension
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.RenegotiationExtensionDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * A document containing one RenegotiationExtension(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation) element.
 *
 * This is a complex type.
 */
public class RenegotiationExtensionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.RenegotiationExtensionDocument
{
    private static final long serialVersionUID = 1L;
    
    public RenegotiationExtensionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RENEGOTIATIONEXTENSION$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "RenegotiationExtension");
    
    
    /**
     * Gets the "RenegotiationExtension" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.RenegotiationExtensionType getRenegotiationExtension()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.RenegotiationExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.RenegotiationExtensionType)get_store().find_element_user(RENEGOTIATIONEXTENSION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "RenegotiationExtension" element
     */
    public void setRenegotiationExtension(org.ogf.schemas.graap.wsAgreement.negotiation.RenegotiationExtensionType renegotiationExtension)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.RenegotiationExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.RenegotiationExtensionType)get_store().find_element_user(RENEGOTIATIONEXTENSION$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.RenegotiationExtensionType)get_store().add_element_user(RENEGOTIATIONEXTENSION$0);
            }
            target.set(renegotiationExtension);
        }
    }
    
    /**
     * Appends and returns a new empty "RenegotiationExtension" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.RenegotiationExtensionType addNewRenegotiationExtension()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.RenegotiationExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.RenegotiationExtensionType)get_store().add_element_user(RENEGOTIATIONEXTENSION$0);
            return target;
        }
    }
}
