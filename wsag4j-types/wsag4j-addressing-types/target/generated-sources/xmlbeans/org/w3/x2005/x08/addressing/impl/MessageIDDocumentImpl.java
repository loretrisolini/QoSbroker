/*
 * An XML document type.
 * Localname: MessageID
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.MessageIDDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * A document containing one MessageID(@http://www.w3.org/2005/08/addressing) element.
 *
 * This is a complex type.
 */
public class MessageIDDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.MessageIDDocument
{
    private static final long serialVersionUID = 1L;
    
    public MessageIDDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName MESSAGEID$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "MessageID");
    
    
    /**
     * Gets the "MessageID" element
     */
    public org.w3.x2005.x08.addressing.AttributedURIType getMessageID()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedURIType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().find_element_user(MESSAGEID$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "MessageID" element
     */
    public void setMessageID(org.w3.x2005.x08.addressing.AttributedURIType messageID)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedURIType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().find_element_user(MESSAGEID$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().add_element_user(MESSAGEID$0);
            }
            target.set(messageID);
        }
    }
    
    /**
     * Appends and returns a new empty "MessageID" element
     */
    public org.w3.x2005.x08.addressing.AttributedURIType addNewMessageID()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedURIType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().add_element_user(MESSAGEID$0);
            return target;
        }
    }
}
