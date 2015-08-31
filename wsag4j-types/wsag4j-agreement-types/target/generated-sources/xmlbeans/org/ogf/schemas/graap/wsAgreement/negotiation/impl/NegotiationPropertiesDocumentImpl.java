/*
 * An XML document type.
 * Localname: NegotiationProperties
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationPropertiesDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * A document containing one NegotiationProperties(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation) element.
 *
 * This is a complex type.
 */
public class NegotiationPropertiesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationPropertiesDocument
{
    private static final long serialVersionUID = 1L;
    
    public NegotiationPropertiesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEGOTIATIONPROPERTIES$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationProperties");
    
    
    /**
     * Gets the "NegotiationProperties" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationPropertiesType getNegotiationProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationPropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationPropertiesType)get_store().find_element_user(NEGOTIATIONPROPERTIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NegotiationProperties" element
     */
    public void setNegotiationProperties(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationPropertiesType negotiationProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationPropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationPropertiesType)get_store().find_element_user(NEGOTIATIONPROPERTIES$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationPropertiesType)get_store().add_element_user(NEGOTIATIONPROPERTIES$0);
            }
            target.set(negotiationProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "NegotiationProperties" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationPropertiesType addNewNegotiationProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationPropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationPropertiesType)get_store().add_element_user(NEGOTIATIONPROPERTIES$0);
            return target;
        }
    }
}
