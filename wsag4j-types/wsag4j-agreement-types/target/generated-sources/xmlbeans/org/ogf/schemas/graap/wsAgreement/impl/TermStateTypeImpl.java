/*
 * XML Type:  TermStateType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.TermStateType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML TermStateType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class TermStateTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.TermStateType
{
    private static final long serialVersionUID = 1L;
    
    public TermStateTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TERMNAME$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "termName");
    
    
    /**
     * Gets the "termName" attribute
     */
    public java.lang.String getTermName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TERMNAME$0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "termName" attribute
     */
    public org.apache.xmlbeans.XmlString xgetTermName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(TERMNAME$0);
            return target;
        }
    }
    
    /**
     * True if has "termName" attribute
     */
    public boolean isSetTermName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(TERMNAME$0) != null;
        }
    }
    
    /**
     * Sets the "termName" attribute
     */
    public void setTermName(java.lang.String termName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TERMNAME$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(TERMNAME$0);
            }
            target.setStringValue(termName);
        }
    }
    
    /**
     * Sets (as xml) the "termName" attribute
     */
    public void xsetTermName(org.apache.xmlbeans.XmlString termName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(TERMNAME$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(TERMNAME$0);
            }
            target.set(termName);
        }
    }
    
    /**
     * Unsets the "termName" attribute
     */
    public void unsetTermName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(TERMNAME$0);
        }
    }
}
