/*
 * An XML document type.
 * Localname: WSAG4JMetadata
 * Namespace: http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine
 * Java type: org.ogf.graap.wsag4j.types.engine.WSAG4JMetadataDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.engine.impl;
/**
 * A document containing one WSAG4JMetadata(@http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine) element.
 *
 * This is a complex type.
 */
public class WSAG4JMetadataDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.engine.WSAG4JMetadataDocument
{
    private static final long serialVersionUID = 1L;
    
    public WSAG4JMetadataDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName WSAG4JMETADATA$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "WSAG4JMetadata");
    
    
    /**
     * Gets the "WSAG4JMetadata" element
     */
    public org.ogf.graap.wsag4j.types.engine.WSAG4JMetadataType getWSAG4JMetadata()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.WSAG4JMetadataType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.WSAG4JMetadataType)get_store().find_element_user(WSAG4JMETADATA$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "WSAG4JMetadata" element
     */
    public void setWSAG4JMetadata(org.ogf.graap.wsag4j.types.engine.WSAG4JMetadataType wsag4JMetadata)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.WSAG4JMetadataType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.WSAG4JMetadataType)get_store().find_element_user(WSAG4JMETADATA$0, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.engine.WSAG4JMetadataType)get_store().add_element_user(WSAG4JMETADATA$0);
            }
            target.set(wsag4JMetadata);
        }
    }
    
    /**
     * Appends and returns a new empty "WSAG4JMetadata" element
     */
    public org.ogf.graap.wsag4j.types.engine.WSAG4JMetadataType addNewWSAG4JMetadata()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.WSAG4JMetadataType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.WSAG4JMetadataType)get_store().add_element_user(WSAG4JMETADATA$0);
            return target;
        }
    }
}
