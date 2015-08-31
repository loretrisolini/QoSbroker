/*
 * An XML document type.
 * Localname: RelatesTo
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.RelatesToDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * A document containing one RelatesTo(@http://www.w3.org/2005/08/addressing) element.
 *
 * This is a complex type.
 */
public class RelatesToDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.RelatesToDocument
{
    private static final long serialVersionUID = 1L;
    
    public RelatesToDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RELATESTO$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "RelatesTo");
    
    
    /**
     * Gets the "RelatesTo" element
     */
    public org.w3.x2005.x08.addressing.RelatesToType getRelatesTo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.RelatesToType target = null;
            target = (org.w3.x2005.x08.addressing.RelatesToType)get_store().find_element_user(RELATESTO$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "RelatesTo" element
     */
    public void setRelatesTo(org.w3.x2005.x08.addressing.RelatesToType relatesTo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.RelatesToType target = null;
            target = (org.w3.x2005.x08.addressing.RelatesToType)get_store().find_element_user(RELATESTO$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.RelatesToType)get_store().add_element_user(RELATESTO$0);
            }
            target.set(relatesTo);
        }
    }
    
    /**
     * Appends and returns a new empty "RelatesTo" element
     */
    public org.w3.x2005.x08.addressing.RelatesToType addNewRelatesTo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.RelatesToType target = null;
            target = (org.w3.x2005.x08.addressing.RelatesToType)get_store().add_element_user(RELATESTO$0);
            return target;
        }
    }
}
