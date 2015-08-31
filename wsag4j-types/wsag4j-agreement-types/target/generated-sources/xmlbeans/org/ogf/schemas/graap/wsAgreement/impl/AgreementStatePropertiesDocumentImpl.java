/*
 * An XML document type.
 * Localname: AgreementStateProperties
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AgreementStatePropertiesDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one AgreementStateProperties(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class AgreementStatePropertiesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.AgreementStatePropertiesDocument
{
    private static final long serialVersionUID = 1L;
    
    public AgreementStatePropertiesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AGREEMENTSTATEPROPERTIES$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "AgreementStateProperties");
    
    
    /**
     * Gets the "AgreementStateProperties" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementStatePropertiesType getAgreementStateProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementStatePropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementStatePropertiesType)get_store().find_element_user(AGREEMENTSTATEPROPERTIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AgreementStateProperties" element
     */
    public void setAgreementStateProperties(org.ogf.schemas.graap.wsAgreement.AgreementStatePropertiesType agreementStateProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementStatePropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementStatePropertiesType)get_store().find_element_user(AGREEMENTSTATEPROPERTIES$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementStatePropertiesType)get_store().add_element_user(AGREEMENTSTATEPROPERTIES$0);
            }
            target.set(agreementStateProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "AgreementStateProperties" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementStatePropertiesType addNewAgreementStateProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementStatePropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementStatePropertiesType)get_store().add_element_user(AGREEMENTSTATEPROPERTIES$0);
            return target;
        }
    }
}
