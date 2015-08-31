/*
 * An XML document type.
 * Localname: GuaranteeTermState
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one GuaranteeTermState(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class GuaranteeTermStateDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateDocument
{
    private static final long serialVersionUID = 1L;
    
    public GuaranteeTermStateDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GUARANTEETERMSTATE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "GuaranteeTermState");
    
    
    /**
     * Gets the "GuaranteeTermState" element
     */
    public org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType getGuaranteeTermState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType)get_store().find_element_user(GUARANTEETERMSTATE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "GuaranteeTermState" element
     */
    public void setGuaranteeTermState(org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType guaranteeTermState)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType)get_store().find_element_user(GUARANTEETERMSTATE$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType)get_store().add_element_user(GUARANTEETERMSTATE$0);
            }
            target.set(guaranteeTermState);
        }
    }
    
    /**
     * Appends and returns a new empty "GuaranteeTermState" element
     */
    public org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType addNewGuaranteeTermState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType)get_store().add_element_user(GUARANTEETERMSTATE$0);
            return target;
        }
    }
}
