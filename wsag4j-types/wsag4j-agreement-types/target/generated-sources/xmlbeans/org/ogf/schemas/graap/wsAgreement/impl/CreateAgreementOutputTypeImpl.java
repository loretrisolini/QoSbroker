/*
 * XML Type:  CreateAgreementOutputType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.CreateAgreementOutputType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML CreateAgreementOutputType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class CreateAgreementOutputTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.CreateAgreementOutputType
{
    private static final long serialVersionUID = 1L;
    
    public CreateAgreementOutputTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CREATEDAGREEMENTEPR$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "CreatedAgreementEPR");
    
    
    /**
     * Gets the "CreatedAgreementEPR" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType getCreatedAgreementEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(CREATEDAGREEMENTEPR$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CreatedAgreementEPR" element
     */
    public void setCreatedAgreementEPR(org.w3.x2005.x08.addressing.EndpointReferenceType createdAgreementEPR)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(CREATEDAGREEMENTEPR$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(CREATEDAGREEMENTEPR$0);
            }
            target.set(createdAgreementEPR);
        }
    }
    
    /**
     * Appends and returns a new empty "CreatedAgreementEPR" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType addNewCreatedAgreementEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(CREATEDAGREEMENTEPR$0);
            return target;
        }
    }
}
