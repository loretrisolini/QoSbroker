/*
 * XML Type:  NegotiationType
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * An XML NegotiationType(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation).
 *
 * This is a complex type.
 */
public class NegotiationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType
{
    private static final long serialVersionUID = 1L;
    
    public NegotiationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEGOTIATION$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "Negotiation");
    private static final javax.xml.namespace.QName RENEGOTIATION$2 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "Renegotiation");
    
    
    /**
     * Gets the "Negotiation" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Negotiation getNegotiation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Negotiation target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Negotiation)get_store().find_element_user(NEGOTIATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Negotiation" element
     */
    public boolean isSetNegotiation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NEGOTIATION$0) != 0;
        }
    }
    
    /**
     * Sets the "Negotiation" element
     */
    public void setNegotiation(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Negotiation negotiation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Negotiation target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Negotiation)get_store().find_element_user(NEGOTIATION$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Negotiation)get_store().add_element_user(NEGOTIATION$0);
            }
            target.set(negotiation);
        }
    }
    
    /**
     * Appends and returns a new empty "Negotiation" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Negotiation addNewNegotiation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Negotiation target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Negotiation)get_store().add_element_user(NEGOTIATION$0);
            return target;
        }
    }
    
    /**
     * Unsets the "Negotiation" element
     */
    public void unsetNegotiation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NEGOTIATION$0, 0);
        }
    }
    
    /**
     * Gets the "Renegotiation" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Renegotiation getRenegotiation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Renegotiation target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Renegotiation)get_store().find_element_user(RENEGOTIATION$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Renegotiation" element
     */
    public boolean isSetRenegotiation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(RENEGOTIATION$2) != 0;
        }
    }
    
    /**
     * Sets the "Renegotiation" element
     */
    public void setRenegotiation(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Renegotiation renegotiation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Renegotiation target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Renegotiation)get_store().find_element_user(RENEGOTIATION$2, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Renegotiation)get_store().add_element_user(RENEGOTIATION$2);
            }
            target.set(renegotiation);
        }
    }
    
    /**
     * Appends and returns a new empty "Renegotiation" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Renegotiation addNewRenegotiation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Renegotiation target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Renegotiation)get_store().add_element_user(RENEGOTIATION$2);
            return target;
        }
    }
    
    /**
     * Unsets the "Renegotiation" element
     */
    public void unsetRenegotiation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(RENEGOTIATION$2, 0);
        }
    }
    /**
     * An XML Negotiation(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation).
     *
     * This is a complex type.
     */
    public static class NegotiationImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Negotiation
    {
        private static final long serialVersionUID = 1L;
        
        public NegotiationImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        
    }
    /**
     * An XML Renegotiation(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation).
     *
     * This is a complex type.
     */
    public static class RenegotiationImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType.Renegotiation
    {
        private static final long serialVersionUID = 1L;
        
        public RenegotiationImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName RESPONDERAGREEMENTEPR$0 = 
            new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "ResponderAgreementEPR");
        private static final javax.xml.namespace.QName INITIATORAGREEMENTEPR$2 = 
            new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "InitiatorAgreementEPR");
        
        
        /**
         * Gets the "ResponderAgreementEPR" element
         */
        public org.w3.x2005.x08.addressing.EndpointReferenceType getResponderAgreementEPR()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(RESPONDERAGREEMENTEPR$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "ResponderAgreementEPR" element
         */
        public void setResponderAgreementEPR(org.w3.x2005.x08.addressing.EndpointReferenceType responderAgreementEPR)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(RESPONDERAGREEMENTEPR$0, 0);
                if (target == null)
                {
                    target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(RESPONDERAGREEMENTEPR$0);
                }
                target.set(responderAgreementEPR);
            }
        }
        
        /**
         * Appends and returns a new empty "ResponderAgreementEPR" element
         */
        public org.w3.x2005.x08.addressing.EndpointReferenceType addNewResponderAgreementEPR()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(RESPONDERAGREEMENTEPR$0);
                return target;
            }
        }
        
        /**
         * Gets the "InitiatorAgreementEPR" element
         */
        public org.w3.x2005.x08.addressing.EndpointReferenceType getInitiatorAgreementEPR()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(INITIATORAGREEMENTEPR$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "InitiatorAgreementEPR" element
         */
        public boolean isSetInitiatorAgreementEPR()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(INITIATORAGREEMENTEPR$2) != 0;
            }
        }
        
        /**
         * Sets the "InitiatorAgreementEPR" element
         */
        public void setInitiatorAgreementEPR(org.w3.x2005.x08.addressing.EndpointReferenceType initiatorAgreementEPR)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(INITIATORAGREEMENTEPR$2, 0);
                if (target == null)
                {
                    target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(INITIATORAGREEMENTEPR$2);
                }
                target.set(initiatorAgreementEPR);
            }
        }
        
        /**
         * Appends and returns a new empty "InitiatorAgreementEPR" element
         */
        public org.w3.x2005.x08.addressing.EndpointReferenceType addNewInitiatorAgreementEPR()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(INITIATORAGREEMENTEPR$2);
                return target;
            }
        }
        
        /**
         * Unsets the "InitiatorAgreementEPR" element
         */
        public void unsetInitiatorAgreementEPR()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(INITIATORAGREEMENTEPR$2, 0);
            }
        }
    }
}
