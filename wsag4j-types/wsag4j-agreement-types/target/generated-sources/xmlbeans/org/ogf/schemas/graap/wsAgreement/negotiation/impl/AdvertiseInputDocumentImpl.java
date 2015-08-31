/*
 * An XML document type.
 * Localname: AdvertiseInput
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseInputDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * A document containing one AdvertiseInput(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation) element.
 *
 * This is a complex type.
 */
public class AdvertiseInputDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseInputDocument
{
    private static final long serialVersionUID = 1L;
    
    public AdvertiseInputDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ADVERTISEINPUT$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "AdvertiseInput");
    
    
    /**
     * Gets the "AdvertiseInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseInputType getAdvertiseInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseInputType)get_store().find_element_user(ADVERTISEINPUT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AdvertiseInput" element
     */
    public void setAdvertiseInput(org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseInputType advertiseInput)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseInputType)get_store().find_element_user(ADVERTISEINPUT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseInputType)get_store().add_element_user(ADVERTISEINPUT$0);
            }
            target.set(advertiseInput);
        }
    }
    
    /**
     * Appends and returns a new empty "AdvertiseInput" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseInputType addNewAdvertiseInput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseInputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseInputType)get_store().add_element_user(ADVERTISEINPUT$0);
            return target;
        }
    }
}
