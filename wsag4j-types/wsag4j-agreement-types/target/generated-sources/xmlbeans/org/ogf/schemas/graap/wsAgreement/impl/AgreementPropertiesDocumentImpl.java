/*
 * An XML document type.
 * Localname: AgreementProperties
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AgreementPropertiesDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one AgreementProperties(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class AgreementPropertiesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.AgreementPropertiesDocument
{
    private static final long serialVersionUID = 1L;
    
    public AgreementPropertiesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AGREEMENTPROPERTIES$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "AgreementProperties");
    
    
    /**
     * Gets the "AgreementProperties" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType getAgreementProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType)get_store().find_element_user(AGREEMENTPROPERTIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AgreementProperties" element
     */
    public void setAgreementProperties(org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType agreementProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType)get_store().find_element_user(AGREEMENTPROPERTIES$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType)get_store().add_element_user(AGREEMENTPROPERTIES$0);
            }
            target.set(agreementProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "AgreementProperties" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType addNewAgreementProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType)get_store().add_element_user(AGREEMENTPROPERTIES$0);
            return target;
        }
    }
}
