/*
 * An XML document type.
 * Localname: ContinuingFault
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.ContinuingFaultDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one ContinuingFault(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class ContinuingFaultDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.ContinuingFaultDocument
{
    private static final long serialVersionUID = 1L;
    
    public ContinuingFaultDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CONTINUINGFAULT$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ContinuingFault");
    
    
    /**
     * Gets the "ContinuingFault" element
     */
    public org.ogf.schemas.graap.wsAgreement.ContinuingFaultType getContinuingFault()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ContinuingFaultType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ContinuingFaultType)get_store().find_element_user(CONTINUINGFAULT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ContinuingFault" element
     */
    public void setContinuingFault(org.ogf.schemas.graap.wsAgreement.ContinuingFaultType continuingFault)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ContinuingFaultType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ContinuingFaultType)get_store().find_element_user(CONTINUINGFAULT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.ContinuingFaultType)get_store().add_element_user(CONTINUINGFAULT$0);
            }
            target.set(continuingFault);
        }
    }
    
    /**
     * Appends and returns a new empty "ContinuingFault" element
     */
    public org.ogf.schemas.graap.wsAgreement.ContinuingFaultType addNewContinuingFault()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ContinuingFaultType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ContinuingFaultType)get_store().add_element_user(CONTINUINGFAULT$0);
            return target;
        }
    }
}
