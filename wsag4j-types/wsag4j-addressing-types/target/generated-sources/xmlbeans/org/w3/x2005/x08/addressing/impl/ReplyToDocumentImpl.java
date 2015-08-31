/*
 * An XML document type.
 * Localname: ReplyTo
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.ReplyToDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * A document containing one ReplyTo(@http://www.w3.org/2005/08/addressing) element.
 *
 * This is a complex type.
 */
public class ReplyToDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.ReplyToDocument
{
    private static final long serialVersionUID = 1L;
    
    public ReplyToDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName REPLYTO$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "ReplyTo");
    
    
    /**
     * Gets the "ReplyTo" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType getReplyTo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(REPLYTO$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ReplyTo" element
     */
    public void setReplyTo(org.w3.x2005.x08.addressing.EndpointReferenceType replyTo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(REPLYTO$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(REPLYTO$0);
            }
            target.set(replyTo);
        }
    }
    
    /**
     * Appends and returns a new empty "ReplyTo" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType addNewReplyTo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(REPLYTO$0);
            return target;
        }
    }
}
