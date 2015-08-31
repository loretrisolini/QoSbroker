/*
 * XML Type:  RelatesToType
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.RelatesToType
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * An XML RelatesToType(@http://www.w3.org/2005/08/addressing).
 *
 * This is an atomic type that is a restriction of org.w3.x2005.x08.addressing.RelatesToType.
 */
public class RelatesToTypeImpl extends org.apache.xmlbeans.impl.values.JavaUriHolderEx implements org.w3.x2005.x08.addressing.RelatesToType
{
    private static final long serialVersionUID = 1L;
    
    public RelatesToTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType, true);
    }
    
    protected RelatesToTypeImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
    {
        super(sType, b);
    }
    
    private static final javax.xml.namespace.QName RELATIONSHIPTYPE$0 = 
        new javax.xml.namespace.QName("", "RelationshipType");
    
    
    /**
     * Gets the "RelationshipType" attribute
     */
    public java.lang.String getRelationshipType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(RELATIONSHIPTYPE$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_default_attribute_value(RELATIONSHIPTYPE$0);
            }
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "RelationshipType" attribute
     */
    public org.w3.x2005.x08.addressing.RelationshipTypeOpenEnum xgetRelationshipType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.RelationshipTypeOpenEnum target = null;
            target = (org.w3.x2005.x08.addressing.RelationshipTypeOpenEnum)get_store().find_attribute_user(RELATIONSHIPTYPE$0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.RelationshipTypeOpenEnum)get_default_attribute_value(RELATIONSHIPTYPE$0);
            }
            return target;
        }
    }
    
    /**
     * True if has "RelationshipType" attribute
     */
    public boolean isSetRelationshipType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(RELATIONSHIPTYPE$0) != null;
        }
    }
    
    /**
     * Sets the "RelationshipType" attribute
     */
    public void setRelationshipType(java.lang.String relationshipType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(RELATIONSHIPTYPE$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(RELATIONSHIPTYPE$0);
            }
            target.setStringValue(relationshipType);
        }
    }
    
    /**
     * Sets (as xml) the "RelationshipType" attribute
     */
    public void xsetRelationshipType(org.w3.x2005.x08.addressing.RelationshipTypeOpenEnum relationshipType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.RelationshipTypeOpenEnum target = null;
            target = (org.w3.x2005.x08.addressing.RelationshipTypeOpenEnum)get_store().find_attribute_user(RELATIONSHIPTYPE$0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.RelationshipTypeOpenEnum)get_store().add_attribute_user(RELATIONSHIPTYPE$0);
            }
            target.set(relationshipType);
        }
    }
    
    /**
     * Unsets the "RelationshipType" attribute
     */
    public void unsetRelationshipType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(RELATIONSHIPTYPE$0);
        }
    }
}
