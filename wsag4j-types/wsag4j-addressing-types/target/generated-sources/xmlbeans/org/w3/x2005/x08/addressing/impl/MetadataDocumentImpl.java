/*
 * An XML document type.
 * Localname: Metadata
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.MetadataDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * A document containing one Metadata(@http://www.w3.org/2005/08/addressing) element.
 *
 * This is a complex type.
 */
public class MetadataDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.MetadataDocument
{
    private static final long serialVersionUID = 1L;
    
    public MetadataDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName METADATA$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "Metadata");
    
    
    /**
     * Gets the "Metadata" element
     */
    public org.w3.x2005.x08.addressing.MetadataType getMetadata()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.MetadataType target = null;
            target = (org.w3.x2005.x08.addressing.MetadataType)get_store().find_element_user(METADATA$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Metadata" element
     */
    public void setMetadata(org.w3.x2005.x08.addressing.MetadataType metadata)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.MetadataType target = null;
            target = (org.w3.x2005.x08.addressing.MetadataType)get_store().find_element_user(METADATA$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.MetadataType)get_store().add_element_user(METADATA$0);
            }
            target.set(metadata);
        }
    }
    
    /**
     * Appends and returns a new empty "Metadata" element
     */
    public org.w3.x2005.x08.addressing.MetadataType addNewMetadata()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.MetadataType target = null;
            target = (org.w3.x2005.x08.addressing.MetadataType)get_store().add_element_user(METADATA$0);
            return target;
        }
    }
}
