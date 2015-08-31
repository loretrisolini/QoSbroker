/*
 * XML Type:  EndpointReferenceType
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.EndpointReferenceType
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * An XML EndpointReferenceType(@http://www.w3.org/2005/08/addressing).
 *
 * This is a complex type.
 */
public class EndpointReferenceTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.EndpointReferenceType
{
    private static final long serialVersionUID = 1L;
    
    public EndpointReferenceTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ADDRESS$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "Address");
    private static final javax.xml.namespace.QName REFERENCEPARAMETERS$2 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "ReferenceParameters");
    private static final javax.xml.namespace.QName METADATA$4 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "Metadata");
    
    
    /**
     * Gets the "Address" element
     */
    public org.w3.x2005.x08.addressing.AttributedURIType getAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedURIType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().find_element_user(ADDRESS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Address" element
     */
    public void setAddress(org.w3.x2005.x08.addressing.AttributedURIType address)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedURIType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().find_element_user(ADDRESS$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().add_element_user(ADDRESS$0);
            }
            target.set(address);
        }
    }
    
    /**
     * Appends and returns a new empty "Address" element
     */
    public org.w3.x2005.x08.addressing.AttributedURIType addNewAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedURIType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().add_element_user(ADDRESS$0);
            return target;
        }
    }
    
    /**
     * Gets the "ReferenceParameters" element
     */
    public org.w3.x2005.x08.addressing.ReferenceParametersType getReferenceParameters()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.ReferenceParametersType target = null;
            target = (org.w3.x2005.x08.addressing.ReferenceParametersType)get_store().find_element_user(REFERENCEPARAMETERS$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "ReferenceParameters" element
     */
    public boolean isSetReferenceParameters()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(REFERENCEPARAMETERS$2) != 0;
        }
    }
    
    /**
     * Sets the "ReferenceParameters" element
     */
    public void setReferenceParameters(org.w3.x2005.x08.addressing.ReferenceParametersType referenceParameters)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.ReferenceParametersType target = null;
            target = (org.w3.x2005.x08.addressing.ReferenceParametersType)get_store().find_element_user(REFERENCEPARAMETERS$2, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.ReferenceParametersType)get_store().add_element_user(REFERENCEPARAMETERS$2);
            }
            target.set(referenceParameters);
        }
    }
    
    /**
     * Appends and returns a new empty "ReferenceParameters" element
     */
    public org.w3.x2005.x08.addressing.ReferenceParametersType addNewReferenceParameters()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.ReferenceParametersType target = null;
            target = (org.w3.x2005.x08.addressing.ReferenceParametersType)get_store().add_element_user(REFERENCEPARAMETERS$2);
            return target;
        }
    }
    
    /**
     * Unsets the "ReferenceParameters" element
     */
    public void unsetReferenceParameters()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(REFERENCEPARAMETERS$2, 0);
        }
    }
    
    /**
     * Gets the "Metadata" element
     */
    public org.w3.x2005.x08.addressing.MetadataType getMetadata()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.MetadataType target = null;
            target = (org.w3.x2005.x08.addressing.MetadataType)get_store().find_element_user(METADATA$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Metadata" element
     */
    public boolean isSetMetadata()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(METADATA$4) != 0;
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
            target = (org.w3.x2005.x08.addressing.MetadataType)get_store().find_element_user(METADATA$4, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.MetadataType)get_store().add_element_user(METADATA$4);
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
            target = (org.w3.x2005.x08.addressing.MetadataType)get_store().add_element_user(METADATA$4);
            return target;
        }
    }
    
    /**
     * Unsets the "Metadata" element
     */
    public void unsetMetadata()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(METADATA$4, 0);
        }
    }
}
