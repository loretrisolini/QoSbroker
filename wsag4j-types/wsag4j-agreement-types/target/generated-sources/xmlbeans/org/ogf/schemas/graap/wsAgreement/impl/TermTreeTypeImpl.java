/*
 * XML Type:  TermTreeType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.TermTreeType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML TermTreeType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class TermTreeTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.TermTreeType
{
    private static final long serialVersionUID = 1L;
    
    public TermTreeTypeImpl(org.apache.xmlbeans.SchemaType sType)
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
     * True if has "All" element
     */
    public boolean isSetAll()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ALL$0) != 0;
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
    
    /**
     * Unsets the "All" element
     */
    public void unsetAll()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ALL$0, 0);
        }
    }
}
