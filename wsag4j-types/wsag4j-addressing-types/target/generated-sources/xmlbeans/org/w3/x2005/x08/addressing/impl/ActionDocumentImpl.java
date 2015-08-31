/*
 * An XML document type.
 * Localname: Action
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.ActionDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * A document containing one Action(@http://www.w3.org/2005/08/addressing) element.
 *
 * This is a complex type.
 */
public class ActionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.ActionDocument
{
    private static final long serialVersionUID = 1L;
    
    public ActionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ACTION$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "Action");
    
    
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
}
