/*
 * An XML document type.
 * Localname: AdvertiseOutput
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseOutputDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * A document containing one AdvertiseOutput(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation) element.
 *
 * This is a complex type.
 */
public class AdvertiseOutputDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseOutputDocument
{
    private static final long serialVersionUID = 1L;
    
    public AdvertiseOutputDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ADVERTISEOUTPUT$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "AdvertiseOutput");
    
    
    /**
     * Gets the "AdvertiseOutput" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseOutputType getAdvertiseOutput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseOutputType)get_store().find_element_user(ADVERTISEOUTPUT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AdvertiseOutput" element
     */
    public void setAdvertiseOutput(org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseOutputType advertiseOutput)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseOutputType)get_store().find_element_user(ADVERTISEOUTPUT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseOutputType)get_store().add_element_user(ADVERTISEOUTPUT$0);
            }
            target.set(advertiseOutput);
        }
    }
    
    /**
     * Appends and returns a new empty "AdvertiseOutput" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseOutputType addNewAdvertiseOutput()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseOutputType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.AdvertiseOutputType)get_store().add_element_user(ADVERTISEOUTPUT$0);
            return target;
        }
    }
}
