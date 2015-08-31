/*
 * An XML document type.
 * Localname: RetryAfter
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.RetryAfterDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * A document containing one RetryAfter(@http://www.w3.org/2005/08/addressing) element.
 *
 * This is a complex type.
 */
public class RetryAfterDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.RetryAfterDocument
{
    private static final long serialVersionUID = 1L;
    
    public RetryAfterDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RETRYAFTER$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "RetryAfter");
    
    
    /**
     * Gets the "RetryAfter" element
     */
    public org.w3.x2005.x08.addressing.AttributedUnsignedLongType getRetryAfter()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedUnsignedLongType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedUnsignedLongType)get_store().find_element_user(RETRYAFTER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "RetryAfter" element
     */
    public void setRetryAfter(org.w3.x2005.x08.addressing.AttributedUnsignedLongType retryAfter)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedUnsignedLongType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedUnsignedLongType)get_store().find_element_user(RETRYAFTER$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.AttributedUnsignedLongType)get_store().add_element_user(RETRYAFTER$0);
            }
            target.set(retryAfter);
        }
    }
    
    /**
     * Appends and returns a new empty "RetryAfter" element
     */
    public org.w3.x2005.x08.addressing.AttributedUnsignedLongType addNewRetryAfter()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedUnsignedLongType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedUnsignedLongType)get_store().add_element_user(RETRYAFTER$0);
            return target;
        }
    }
}
