/*
 * XML Type:  CreatePendingAgreementInputType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML CreatePendingAgreementInputType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class CreatePendingAgreementInputTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType
{
    private static final long serialVersionUID = 1L;
    
    public CreatePendingAgreementInputTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName AGREEMENTACCEPTANCEEPR$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "AgreementAcceptanceEPR");
    private static final javax.xml.namespace.QName INITIATORAGREEMENTEPR$2 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "InitiatorAgreementEPR");
    private static final javax.xml.namespace.QName AGREEMENTOFFER$4 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "AgreementOffer");
    private static final javax.xml.namespace.QName NONCRITICALEXTENSION$6 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "NoncriticalExtension");
    
    
    /**
     * Gets the "AgreementAcceptanceEPR" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType getAgreementAcceptanceEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(AGREEMENTACCEPTANCEEPR$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "AgreementAcceptanceEPR" element
     */
    public boolean isSetAgreementAcceptanceEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(AGREEMENTACCEPTANCEEPR$0) != 0;
        }
    }
    
    /**
     * Sets the "AgreementAcceptanceEPR" element
     */
    public void setAgreementAcceptanceEPR(org.w3.x2005.x08.addressing.EndpointReferenceType agreementAcceptanceEPR)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(AGREEMENTACCEPTANCEEPR$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(AGREEMENTACCEPTANCEEPR$0);
            }
            target.set(agreementAcceptanceEPR);
        }
    }
    
    /**
     * Appends and returns a new empty "AgreementAcceptanceEPR" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType addNewAgreementAcceptanceEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(AGREEMENTACCEPTANCEEPR$0);
            return target;
        }
    }
    
    /**
     * Unsets the "AgreementAcceptanceEPR" element
     */
    public void unsetAgreementAcceptanceEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(AGREEMENTACCEPTANCEEPR$0, 0);
        }
    }
    
    /**
     * Gets the "InitiatorAgreementEPR" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType getInitiatorAgreementEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(INITIATORAGREEMENTEPR$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "InitiatorAgreementEPR" element
     */
    public boolean isSetInitiatorAgreementEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(INITIATORAGREEMENTEPR$2) != 0;
        }
    }
    
    /**
     * Sets the "InitiatorAgreementEPR" element
     */
    public void setInitiatorAgreementEPR(org.w3.x2005.x08.addressing.EndpointReferenceType initiatorAgreementEPR)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(INITIATORAGREEMENTEPR$2, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(INITIATORAGREEMENTEPR$2);
            }
            target.set(initiatorAgreementEPR);
        }
    }
    
    /**
     * Appends and returns a new empty "InitiatorAgreementEPR" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType addNewInitiatorAgreementEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(INITIATORAGREEMENTEPR$2);
            return target;
        }
    }
    
    /**
     * Unsets the "InitiatorAgreementEPR" element
     */
    public void unsetInitiatorAgreementEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(INITIATORAGREEMENTEPR$2, 0);
        }
    }
    
    /**
     * Gets the "AgreementOffer" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementType getAgreementOffer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementType)get_store().find_element_user(AGREEMENTOFFER$4, 0);
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
            target = (org.ogf.schemas.graap.wsAgreement.AgreementType)get_store().find_element_user(AGREEMENTOFFER$4, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementType)get_store().add_element_user(AGREEMENTOFFER$4);
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
            target = (org.ogf.schemas.graap.wsAgreement.AgreementType)get_store().add_element_user(AGREEMENTOFFER$4);
            return target;
        }
    }
    
    /**
     * Gets array of all "NoncriticalExtension" elements
     */
    public org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType[] getNoncriticalExtensionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(NONCRITICALEXTENSION$6, targetList);
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType[] result = new org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "NoncriticalExtension" element
     */
    public org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType getNoncriticalExtensionArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType)get_store().find_element_user(NONCRITICALEXTENSION$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "NoncriticalExtension" element
     */
    public int sizeOfNoncriticalExtensionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NONCRITICALEXTENSION$6);
        }
    }
    
    /**
     * Sets array of all "NoncriticalExtension" element
     */
    public void setNoncriticalExtensionArray(org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType[] noncriticalExtensionArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(noncriticalExtensionArray, NONCRITICALEXTENSION$6);
        }
    }
    
    /**
     * Sets ith "NoncriticalExtension" element
     */
    public void setNoncriticalExtensionArray(int i, org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType noncriticalExtension)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType)get_store().find_element_user(NONCRITICALEXTENSION$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(noncriticalExtension);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "NoncriticalExtension" element
     */
    public org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType insertNewNoncriticalExtension(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType)get_store().insert_element_user(NONCRITICALEXTENSION$6, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "NoncriticalExtension" element
     */
    public org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType addNewNoncriticalExtension()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType)get_store().add_element_user(NONCRITICALEXTENSION$6);
            return target;
        }
    }
    
    /**
     * Removes the ith "NoncriticalExtension" element
     */
    public void removeNoncriticalExtension(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NONCRITICALEXTENSION$6, i);
        }
    }
}
