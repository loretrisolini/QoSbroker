/*
 * An XML document type.
 * Localname: AgreementOffer
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AgreementOfferDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one AgreementOffer(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class AgreementOfferDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.AgreementOfferDocument
{
    private static final long serialVersionUID = 1L;
    
    public AgreementOfferDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AGREEMENTOFFER$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "AgreementOffer");
    
    
    /**
     * Gets the "AgreementOffer" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementType getAgreementOffer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementType)get_store().find_element_user(AGREEMENTOFFER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AgreementOffer" element
     */
    public void setAgreementOffer(org.ogf.schemas.graap.wsAgreement.AgreementType agreementOffer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementType)get_store().find_element_user(AGREEMENTOFFER$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementType)get_store().add_element_user(AGREEMENTOFFER$0);
            }
            target.set(agreementOffer);
        }
    }
    
    /**
     * Appends and returns a new empty "AgreementOffer" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementType addNewAgreementOffer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementType)get_store().add_element_user(AGREEMENTOFFER$0);
            return target;
        }
    }
}
