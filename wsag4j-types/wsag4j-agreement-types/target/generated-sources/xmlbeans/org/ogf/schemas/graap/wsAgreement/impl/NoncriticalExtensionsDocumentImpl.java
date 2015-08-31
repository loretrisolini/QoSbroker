/*
 * An XML document type.
 * Localname: NoncriticalExtensions
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionsDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one NoncriticalExtensions(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class NoncriticalExtensionsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionsDocument
{
    private static final long serialVersionUID = 1L;
    
    public NoncriticalExtensionsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NONCRITICALEXTENSIONS$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "NoncriticalExtensions");
    
    
    /**
     * Gets the "NoncriticalExtensions" element
     */
    public org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType getNoncriticalExtensions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType)get_store().find_element_user(NONCRITICALEXTENSIONS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NoncriticalExtensions" element
     */
    public void setNoncriticalExtensions(org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType noncriticalExtensions)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType)get_store().find_element_user(NONCRITICALEXTENSIONS$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType)get_store().add_element_user(NONCRITICALEXTENSIONS$0);
            }
            target.set(noncriticalExtensions);
        }
    }
    
    /**
     * Appends and returns a new empty "NoncriticalExtensions" element
     */
    public org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType addNewNoncriticalExtensions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType)get_store().add_element_user(NONCRITICALEXTENSIONS$0);
            return target;
        }
    }
}
