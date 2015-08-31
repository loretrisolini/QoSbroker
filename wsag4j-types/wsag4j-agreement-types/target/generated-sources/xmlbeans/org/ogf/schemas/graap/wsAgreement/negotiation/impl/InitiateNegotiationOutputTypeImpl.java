/*
 * XML Type:  InitiateNegotiationOutputType
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationOutputType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * An XML InitiateNegotiationOutputType(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation).
 *
 * This is a complex type.
 */
public class InitiateNegotiationOutputTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationOutputType
{
    private static final long serialVersionUID = 1L;
    
    public InitiateNegotiationOutputTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CREATEDNEGOTIATIONEPR$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "CreatedNegotiationEPR");
    
    
    /**
     * Gets the "CreatedNegotiationEPR" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType getCreatedNegotiationEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(CREATEDNEGOTIATIONEPR$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CreatedNegotiationEPR" element
     */
    public void setCreatedNegotiationEPR(org.w3.x2005.x08.addressing.EndpointReferenceType createdNegotiationEPR)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(CREATEDNEGOTIATIONEPR$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(CREATEDNEGOTIATIONEPR$0);
            }
            target.set(createdNegotiationEPR);
        }
    }
    
    /**
     * Appends and returns a new empty "CreatedNegotiationEPR" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType addNewCreatedNegotiationEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(CREATEDNEGOTIATIONEPR$0);
            return target;
        }
    }
}
