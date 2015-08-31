/*
 * An XML document type.
 * Localname: All
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AllDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one All(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class AllDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.AllDocument
{
    private static final long serialVersionUID = 1L;
    
    public AllDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ALL$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "All");
    
    
    /**
     * Gets the "All" element
     */
    public org.ogf.schemas.graap.wsAgreement.TermCompositorType getAll()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermCompositorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermCompositorType)get_store().find_element_user(ALL$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "All" element
     */
    public void setAll(org.ogf.schemas.graap.wsAgreement.TermCompositorType all)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermCompositorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermCompositorType)get_store().find_element_user(ALL$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.TermCompositorType)get_store().add_element_user(ALL$0);
            }
            target.set(all);
        }
    }
    
    /**
     * Appends and returns a new empty "All" element
     */
    public org.ogf.schemas.graap.wsAgreement.TermCompositorType addNewAll()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermCompositorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermCompositorType)get_store().add_element_user(ALL$0);
            return target;
        }
    }
}
