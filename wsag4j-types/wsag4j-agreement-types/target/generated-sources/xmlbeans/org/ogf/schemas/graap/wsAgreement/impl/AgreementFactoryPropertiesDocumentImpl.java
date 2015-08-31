/*
 * An XML document type.
 * Localname: AgreementFactoryProperties
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AgreementFactoryPropertiesDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one AgreementFactoryProperties(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class AgreementFactoryPropertiesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.AgreementFactoryPropertiesDocument
{
    private static final long serialVersionUID = 1L;
    
    public AgreementFactoryPropertiesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AGREEMENTFACTORYPROPERTIES$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "AgreementFactoryProperties");
    
    
    /**
     * Gets the "AgreementFactoryProperties" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementFactoryPropertiesType getAgreementFactoryProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementFactoryPropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementFactoryPropertiesType)get_store().find_element_user(AGREEMENTFACTORYPROPERTIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AgreementFactoryProperties" element
     */
    public void setAgreementFactoryProperties(org.ogf.schemas.graap.wsAgreement.AgreementFactoryPropertiesType agreementFactoryProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementFactoryPropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementFactoryPropertiesType)get_store().find_element_user(AGREEMENTFACTORYPROPERTIES$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementFactoryPropertiesType)get_store().add_element_user(AGREEMENTFACTORYPROPERTIES$0);
            }
            target.set(agreementFactoryProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "AgreementFactoryProperties" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementFactoryPropertiesType addNewAgreementFactoryProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementFactoryPropertiesType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementFactoryPropertiesType)get_store().add_element_user(AGREEMENTFACTORYPROPERTIES$0);
            return target;
        }
    }
}
