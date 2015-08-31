/*
 * An XML document type.
 * Localname: FaultTo
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.FaultToDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * A document containing one FaultTo(@http://www.w3.org/2005/08/addressing) element.
 *
 * This is a complex type.
 */
public class FaultToDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.FaultToDocument
{
    private static final long serialVersionUID = 1L;
    
    public FaultToDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FAULTTO$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "FaultTo");
    
    
    /**
     * Gets the "FaultTo" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType getFaultTo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(FAULTTO$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "FaultTo" element
     */
    public void setFaultTo(org.w3.x2005.x08.addressing.EndpointReferenceType faultTo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(FAULTTO$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(FAULTTO$0);
            }
            target.set(faultTo);
        }
    }
    
    /**
     * Appends and returns a new empty "FaultTo" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType addNewFaultTo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(FAULTTO$0);
            return target;
        }
    }
}
