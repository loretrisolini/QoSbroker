/*
 * An XML document type.
 * Localname: From
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.FromDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * A document containing one From(@http://www.w3.org/2005/08/addressing) element.
 *
 * This is a complex type.
 */
public class FromDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.FromDocument
{
    private static final long serialVersionUID = 1L;
    
    public FromDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FROM$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "From");
    
    
    /**
     * Gets the "From" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType getFrom()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(FROM$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "From" element
     */
    public void setFrom(org.w3.x2005.x08.addressing.EndpointReferenceType from)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(FROM$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(FROM$0);
            }
            target.set(from);
        }
    }
    
    /**
     * Appends and returns a new empty "From" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType addNewFrom()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(FROM$0);
            return target;
        }
    }
}
