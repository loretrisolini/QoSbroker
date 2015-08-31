/*
 * An XML document type.
 * Localname: EndpointReference
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.EndpointReferenceDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * A document containing one EndpointReference(@http://www.w3.org/2005/08/addressing) element.
 *
 * This is a complex type.
 */
public class EndpointReferenceDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.EndpointReferenceDocument
{
    private static final long serialVersionUID = 1L;
    
    public EndpointReferenceDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ENDPOINTREFERENCE$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "EndpointReference");
    
    
    /**
     * Gets the "EndpointReference" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType getEndpointReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(ENDPOINTREFERENCE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "EndpointReference" element
     */
    public void setEndpointReference(org.w3.x2005.x08.addressing.EndpointReferenceType endpointReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(ENDPOINTREFERENCE$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(ENDPOINTREFERENCE$0);
            }
            target.set(endpointReference);
        }
    }
    
    /**
     * Appends and returns a new empty "EndpointReference" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType addNewEndpointReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(ENDPOINTREFERENCE$0);
            return target;
        }
    }
}
