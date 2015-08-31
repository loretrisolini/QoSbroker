/*
 * An XML attribute type.
 * Localname: IsReferenceParameter
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.IsReferenceParameterAttribute
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * A document containing one IsReferenceParameter(@http://www.w3.org/2005/08/addressing) attribute.
 *
 * This is a complex type.
 */
public class IsReferenceParameterAttributeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.IsReferenceParameterAttribute
{
    private static final long serialVersionUID = 1L;
    
    public IsReferenceParameterAttributeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ISREFERENCEPARAMETER$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "IsReferenceParameter");
    
    
    /**
     * Gets the "IsReferenceParameter" attribute
     */
    public boolean getIsReferenceParameter()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ISREFERENCEPARAMETER$0);
            if (target == null)
            {
                return false;
            }
            return target.getBooleanValue();
        }
    }
    
    /**
     * Gets (as xml) the "IsReferenceParameter" attribute
     */
    public org.apache.xmlbeans.XmlBoolean xgetIsReferenceParameter()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBoolean target = null;
            target = (org.apache.xmlbeans.XmlBoolean)get_store().find_attribute_user(ISREFERENCEPARAMETER$0);
            return target;
        }
    }
    
    /**
     * True if has "IsReferenceParameter" attribute
     */
    public boolean isSetIsReferenceParameter()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(ISREFERENCEPARAMETER$0) != null;
        }
    }
    
    /**
     * Sets the "IsReferenceParameter" attribute
     */
    public void setIsReferenceParameter(boolean isReferenceParameter)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ISREFERENCEPARAMETER$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ISREFERENCEPARAMETER$0);
            }
            target.setBooleanValue(isReferenceParameter);
        }
    }
    
    /**
     * Sets (as xml) the "IsReferenceParameter" attribute
     */
    public void xsetIsReferenceParameter(org.apache.xmlbeans.XmlBoolean isReferenceParameter)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBoolean target = null;
            target = (org.apache.xmlbeans.XmlBoolean)get_store().find_attribute_user(ISREFERENCEPARAMETER$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlBoolean)get_store().add_attribute_user(ISREFERENCEPARAMETER$0);
            }
            target.set(isReferenceParameter);
        }
    }
    
    /**
     * Unsets the "IsReferenceParameter" attribute
     */
    public void unsetIsReferenceParameter()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(ISREFERENCEPARAMETER$0);
        }
    }
}
