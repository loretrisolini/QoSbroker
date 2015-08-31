/*
 * XML Type:  NegotiationExtensionType
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationExtensionType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * An XML NegotiationExtensionType(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation).
 *
 * This is a complex type.
 */
public class NegotiationExtensionTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationExtensionType
{
    private static final long serialVersionUID = 1L;
    
    public NegotiationExtensionTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESPONDERNEGOTIATIONEPR$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "ResponderNegotiationEPR");
    private static final javax.xml.namespace.QName INITIATORNEGOTIATIONEPR$2 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "InitiatorNegotiationEPR");
    private static final javax.xml.namespace.QName NEGOTIATIONOFFERCONTEXT$4 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationOfferContext");
    
    
    /**
     * Gets the "ResponderNegotiationEPR" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType getResponderNegotiationEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(RESPONDERNEGOTIATIONEPR$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "ResponderNegotiationEPR" element
     */
    public boolean isSetResponderNegotiationEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(RESPONDERNEGOTIATIONEPR$0) != 0;
        }
    }
    
    /**
     * Sets the "ResponderNegotiationEPR" element
     */
    public void setResponderNegotiationEPR(org.w3.x2005.x08.addressing.EndpointReferenceType responderNegotiationEPR)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(RESPONDERNEGOTIATIONEPR$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(RESPONDERNEGOTIATIONEPR$0);
            }
            target.set(responderNegotiationEPR);
        }
    }
    
    /**
     * Appends and returns a new empty "ResponderNegotiationEPR" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType addNewResponderNegotiationEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(RESPONDERNEGOTIATIONEPR$0);
            return target;
        }
    }
    
    /**
     * Unsets the "ResponderNegotiationEPR" element
     */
    public void unsetResponderNegotiationEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(RESPONDERNEGOTIATIONEPR$0, 0);
        }
    }
    
    /**
     * Gets the "InitiatorNegotiationEPR" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType getInitiatorNegotiationEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(INITIATORNEGOTIATIONEPR$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "InitiatorNegotiationEPR" element
     */
    public boolean isSetInitiatorNegotiationEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(INITIATORNEGOTIATIONEPR$2) != 0;
        }
    }
    
    /**
     * Sets the "InitiatorNegotiationEPR" element
     */
    public void setInitiatorNegotiationEPR(org.w3.x2005.x08.addressing.EndpointReferenceType initiatorNegotiationEPR)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(INITIATORNEGOTIATIONEPR$2, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(INITIATORNEGOTIATIONEPR$2);
            }
            target.set(initiatorNegotiationEPR);
        }
    }
    
    /**
     * Appends and returns a new empty "InitiatorNegotiationEPR" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType addNewInitiatorNegotiationEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(INITIATORNEGOTIATIONEPR$2);
            return target;
        }
    }
    
    /**
     * Unsets the "InitiatorNegotiationEPR" element
     */
    public void unsetInitiatorNegotiationEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(INITIATORNEGOTIATIONEPR$2, 0);
        }
    }
    
    /**
     * Gets the "NegotiationOfferContext" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType getNegotiationOfferContext()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType)get_store().find_element_user(NEGOTIATIONOFFERCONTEXT$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NegotiationOfferContext" element
     */
    public void setNegotiationOfferContext(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType negotiationOfferContext)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType)get_store().find_element_user(NEGOTIATIONOFFERCONTEXT$4, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType)get_store().add_element_user(NEGOTIATIONOFFERCONTEXT$4);
            }
            target.set(negotiationOfferContext);
        }
    }
    
    /**
     * Appends and returns a new empty "NegotiationOfferContext" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType addNewNegotiationOfferContext()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType)get_store().add_element_user(NEGOTIATIONOFFERCONTEXT$4);
            return target;
        }
    }
}
