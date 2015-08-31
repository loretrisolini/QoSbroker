/*
 * XML Type:  NegotiationOfferItemType
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferItemType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * An XML NegotiationOfferItemType(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation).
 *
 * This is a complex type.
 */
public class NegotiationOfferItemTypeImpl extends org.ogf.schemas.graap.wsAgreement.impl.OfferItemTypeImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferItemType
{
    private static final long serialVersionUID = 1L;
    
    public NegotiationOfferItemTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TYPE$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "Type");
    private static final javax.xml.namespace.QName IMPORTANCE$2 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "Importance");
    
    
    /**
     * Gets the "Type" attribute
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintType.Enum getType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TYPE$0);
            if (target == null)
            {
                return null;
            }
            return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "Type" attribute
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintType xgetType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintType)get_store().find_attribute_user(TYPE$0);
            return target;
        }
    }
    
    /**
     * Sets the "Type" attribute
     */
    public void setType(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintType.Enum type)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TYPE$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(TYPE$0);
            }
            target.setEnumValue(type);
        }
    }
    
    /**
     * Sets (as xml) the "Type" attribute
     */
    public void xsetType(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintType type)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintType)get_store().find_attribute_user(TYPE$0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationConstraintType)get_store().add_attribute_user(TYPE$0);
            }
            target.set(type);
        }
    }
    
    /**
     * Gets the "Importance" attribute
     */
    public java.math.BigInteger getImportance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(IMPORTANCE$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_default_attribute_value(IMPORTANCE$2);
            }
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "Importance" attribute
     */
    public org.apache.xmlbeans.XmlInteger xgetImportance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_attribute_user(IMPORTANCE$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInteger)get_default_attribute_value(IMPORTANCE$2);
            }
            return target;
        }
    }
    
    /**
     * True if has "Importance" attribute
     */
    public boolean isSetImportance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(IMPORTANCE$2) != null;
        }
    }
    
    /**
     * Sets the "Importance" attribute
     */
    public void setImportance(java.math.BigInteger importance)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(IMPORTANCE$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(IMPORTANCE$2);
            }
            target.setBigIntegerValue(importance);
        }
    }
    
    /**
     * Sets (as xml) the "Importance" attribute
     */
    public void xsetImportance(org.apache.xmlbeans.XmlInteger importance)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_attribute_user(IMPORTANCE$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInteger)get_store().add_attribute_user(IMPORTANCE$2);
            }
            target.set(importance);
        }
    }
    
    /**
     * Unsets the "Importance" attribute
     */
    public void unsetImportance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(IMPORTANCE$2);
        }
    }
}
