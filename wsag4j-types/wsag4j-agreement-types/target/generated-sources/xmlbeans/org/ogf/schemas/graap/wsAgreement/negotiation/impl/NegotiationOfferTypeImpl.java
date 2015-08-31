/*
 * XML Type:  NegotiationOfferType
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * An XML NegotiationOfferType(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation).
 *
 * This is a complex type.
 */
public class NegotiationOfferTypeImpl extends org.ogf.schemas.graap.wsAgreement.impl.AgreementTypeImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferType
{
    private static final long serialVersionUID = 1L;
    
    public NegotiationOfferTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEGOTIATIONOFFERCONTEXT$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationOfferContext");
    private static final javax.xml.namespace.QName NEGOTIATIONCONSTRAINTS$2 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationConstraints");
    private static final javax.xml.namespace.QName OFFERID$4 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "OfferId");
    
    
    /**
     * Gets the "NegotiationOfferContext" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType getNegotiationOfferContext()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType)get_store().find_element_user(NEGOTIATIONOFFERCONTEXT$0, 0);
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
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType)get_store().find_element_user(NEGOTIATIONOFFERCONTEXT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType)get_store().add_element_user(NEGOTIATIONOFFERCONTEXT$0);
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
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType)get_store().add_element_user(NEGOTIATIONOFFERCONTEXT$0);
            return target;
        }
    }
    
    /**
     * Gets the "NegotiationConstraints" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintSectionType getNegotiationConstraints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintSectionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintSectionType)get_store().find_element_user(NEGOTIATIONCONSTRAINTS$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NegotiationConstraints" element
     */
    public void setNegotiationConstraints(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintSectionType negotiationConstraints)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintSectionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintSectionType)get_store().find_element_user(NEGOTIATIONCONSTRAINTS$2, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintSectionType)get_store().add_element_user(NEGOTIATIONCONSTRAINTS$2);
            }
            target.set(negotiationConstraints);
        }
    }
    
    /**
     * Appends and returns a new empty "NegotiationConstraints" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintSectionType addNewNegotiationConstraints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintSectionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintSectionType)get_store().add_element_user(NEGOTIATIONCONSTRAINTS$2);
            return target;
        }
    }
    
    /**
     * Gets the "OfferId" attribute
     */
    public java.lang.String getOfferId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(OFFERID$4);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "OfferId" attribute
     */
    public org.apache.xmlbeans.XmlString xgetOfferId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(OFFERID$4);
            return target;
        }
    }
    
    /**
     * True if has "OfferId" attribute
     */
    public boolean isSetOfferId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(OFFERID$4) != null;
        }
    }
    
    /**
     * Sets the "OfferId" attribute
     */
    public void setOfferId(java.lang.String offerId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(OFFERID$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(OFFERID$4);
            }
            target.setStringValue(offerId);
        }
    }
    
    /**
     * Sets (as xml) the "OfferId" attribute
     */
    public void xsetOfferId(org.apache.xmlbeans.XmlString offerId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(OFFERID$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(OFFERID$4);
            }
            target.set(offerId);
        }
    }
    
    /**
     * Unsets the "OfferId" attribute
     */
    public void unsetOfferId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(OFFERID$4);
        }
    }
}
