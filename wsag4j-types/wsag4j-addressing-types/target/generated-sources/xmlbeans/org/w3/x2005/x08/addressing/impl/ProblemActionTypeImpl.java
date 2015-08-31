/*
 * XML Type:  ProblemActionType
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.ProblemActionType
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * An XML ProblemActionType(@http://www.w3.org/2005/08/addressing).
 *
 * This is a complex type.
 */
public class ProblemActionTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.ProblemActionType
{
    private static final long serialVersionUID = 1L;
    
    public ProblemActionTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ACTION$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "Action");
    private static final javax.xml.namespace.QName SOAPACTION$2 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "SoapAction");
    
    
    /**
     * Gets the "Action" element
     */
    public org.w3.x2005.x08.addressing.AttributedURIType getAction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedURIType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().find_element_user(ACTION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Action" element
     */
    public boolean isSetAction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ACTION$0) != 0;
        }
    }
    
    /**
     * Sets the "Action" element
     */
    public void setAction(org.w3.x2005.x08.addressing.AttributedURIType action)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedURIType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().find_element_user(ACTION$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().add_element_user(ACTION$0);
            }
            target.set(action);
        }
    }
    
    /**
     * Appends and returns a new empty "Action" element
     */
    public org.w3.x2005.x08.addressing.AttributedURIType addNewAction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedURIType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().add_element_user(ACTION$0);
            return target;
        }
    }
    
    /**
     * Unsets the "Action" element
     */
    public void unsetAction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ACTION$0, 0);
        }
    }
    
    /**
     * Gets the "SoapAction" element
     */
    public java.lang.String getSoapAction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SOAPACTION$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "SoapAction" element
     */
    public org.apache.xmlbeans.XmlAnyURI xgetSoapAction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_element_user(SOAPACTION$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "SoapAction" element
     */
    public boolean isSetSoapAction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SOAPACTION$2) != 0;
        }
    }
    
    /**
     * Sets the "SoapAction" element
     */
    public void setSoapAction(java.lang.String soapAction)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SOAPACTION$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SOAPACTION$2);
            }
            target.setStringValue(soapAction);
        }
    }
    
    /**
     * Sets (as xml) the "SoapAction" element
     */
    public void xsetSoapAction(org.apache.xmlbeans.XmlAnyURI soapAction)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_element_user(SOAPACTION$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_element_user(SOAPACTION$2);
            }
            target.set(soapAction);
        }
    }
    
    /**
     * Unsets the "SoapAction" element
     */
    public void unsetSoapAction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SOAPACTION$2, 0);
        }
    }
}
