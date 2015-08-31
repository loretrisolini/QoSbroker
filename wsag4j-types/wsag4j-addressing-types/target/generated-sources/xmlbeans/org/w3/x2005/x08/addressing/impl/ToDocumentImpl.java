/*
 * An XML document type.
 * Localname: To
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.ToDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * A document containing one To(@http://www.w3.org/2005/08/addressing) element.
 *
 * This is a complex type.
 */
public class ToDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.ToDocument
{
    private static final long serialVersionUID = 1L;
    
    public ToDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TO$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "To");
    
    
    /**
     * Gets the "To" element
     */
    public org.w3.x2005.x08.addressing.AttributedURIType getTo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedURIType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().find_element_user(TO$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "To" element
     */
    public void setTo(org.w3.x2005.x08.addressing.AttributedURIType to)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedURIType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().find_element_user(TO$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().add_element_user(TO$0);
            }
            target.set(to);
        }
    }
    
    /**
     * Appends and returns a new empty "To" element
     */
    public org.w3.x2005.x08.addressing.AttributedURIType addNewTo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedURIType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().add_element_user(TO$0);
            return target;
        }
    }
}
