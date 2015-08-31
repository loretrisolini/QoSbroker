/*
 * An XML document type.
 * Localname: ServiceTermState
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.ServiceTermStateDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one ServiceTermState(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class ServiceTermStateDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.ServiceTermStateDocument
{
    private static final long serialVersionUID = 1L;
    
    public ServiceTermStateDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICETERMSTATE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ServiceTermState");
    
    
    /**
     * Gets the "ServiceTermState" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceTermStateType getServiceTermState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceTermStateType)get_store().find_element_user(SERVICETERMSTATE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ServiceTermState" element
     */
    public void setServiceTermState(org.ogf.schemas.graap.wsAgreement.ServiceTermStateType serviceTermState)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceTermStateType)get_store().find_element_user(SERVICETERMSTATE$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.ServiceTermStateType)get_store().add_element_user(SERVICETERMSTATE$0);
            }
            target.set(serviceTermState);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceTermState" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceTermStateType addNewServiceTermState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceTermStateType)get_store().add_element_user(SERVICETERMSTATE$0);
            return target;
        }
    }
}
