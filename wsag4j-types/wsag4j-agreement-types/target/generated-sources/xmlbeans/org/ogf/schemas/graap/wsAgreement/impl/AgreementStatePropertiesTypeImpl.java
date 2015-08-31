/*
 * XML Type:  AgreementStatePropertiesType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AgreementStatePropertiesType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML AgreementStatePropertiesType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class AgreementStatePropertiesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.AgreementStatePropertiesType
{
    private static final long serialVersionUID = 1L;
    
    public AgreementStatePropertiesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NAME$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Name");
    private static final javax.xml.namespace.QName AGREEMENTID$2 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "AgreementId");
    private static final javax.xml.namespace.QName CONTEXT$4 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Context");
    private static final javax.xml.namespace.QName TERMS$6 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Terms");
    private static final javax.xml.namespace.QName AGREEMENTSTATE$8 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "AgreementState");
    private static final javax.xml.namespace.QName GUARANTEETERMSTATE$10 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "GuaranteeTermState");
    private static final javax.xml.namespace.QName SERVICETERMSTATE$12 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ServiceTermState");
    
    
    /**
     * Gets the "Name" element
     */
    public java.lang.String getName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NAME$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Name" element
     */
    public org.apache.xmlbeans.XmlString xgetName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NAME$0, 0);
            return target;
        }
    }
    
    /**
     * True if has "Name" element
     */
    public boolean isSetName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NAME$0) != 0;
        }
    }
    
    /**
     * Sets the "Name" element
     */
    public void setName(java.lang.String name)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NAME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NAME$0);
            }
            target.setStringValue(name);
        }
    }
    
    /**
     * Sets (as xml) the "Name" element
     */
    public void xsetName(org.apache.xmlbeans.XmlString name)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NAME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(NAME$0);
            }
            target.set(name);
        }
    }
    
    /**
     * Unsets the "Name" element
     */
    public void unsetName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NAME$0, 0);
        }
    }
    
    /**
     * Gets the "AgreementId" element
     */
    public java.lang.String getAgreementId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AGREEMENTID$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "AgreementId" element
     */
    public org.apache.xmlbeans.XmlString xgetAgreementId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(AGREEMENTID$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "AgreementId" element
     */
    public void setAgreementId(java.lang.String agreementId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(AGREEMENTID$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(AGREEMENTID$2);
            }
            target.setStringValue(agreementId);
        }
    }
    
    /**
     * Sets (as xml) the "AgreementId" element
     */
    public void xsetAgreementId(org.apache.xmlbeans.XmlString agreementId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(AGREEMENTID$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(AGREEMENTID$2);
            }
            target.set(agreementId);
        }
    }
    
    /**
     * Gets the "Context" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementContextType getContext()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementContextType)get_store().find_element_user(CONTEXT$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Context" element
     */
    public void setContext(org.ogf.schemas.graap.wsAgreement.AgreementContextType context)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementContextType)get_store().find_element_user(CONTEXT$4, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementContextType)get_store().add_element_user(CONTEXT$4);
            }
            target.set(context);
        }
    }
    
    /**
     * Appends and returns a new empty "Context" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementContextType addNewContext()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementContextType)get_store().add_element_user(CONTEXT$4);
            return target;
        }
    }
    
    /**
     * Gets the "Terms" element
     */
    public org.ogf.schemas.graap.wsAgreement.TermTreeType getTerms()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermTreeType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermTreeType)get_store().find_element_user(TERMS$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Terms" element
     */
    public void setTerms(org.ogf.schemas.graap.wsAgreement.TermTreeType terms)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermTreeType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermTreeType)get_store().find_element_user(TERMS$6, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.TermTreeType)get_store().add_element_user(TERMS$6);
            }
            target.set(terms);
        }
    }
    
    /**
     * Appends and returns a new empty "Terms" element
     */
    public org.ogf.schemas.graap.wsAgreement.TermTreeType addNewTerms()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.TermTreeType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.TermTreeType)get_store().add_element_user(TERMS$6);
            return target;
        }
    }
    
    /**
     * Gets the "AgreementState" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementStateType getAgreementState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementStateType)get_store().find_element_user(AGREEMENTSTATE$8, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AgreementState" element
     */
    public void setAgreementState(org.ogf.schemas.graap.wsAgreement.AgreementStateType agreementState)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementStateType)get_store().find_element_user(AGREEMENTSTATE$8, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementStateType)get_store().add_element_user(AGREEMENTSTATE$8);
            }
            target.set(agreementState);
        }
    }
    
    /**
     * Appends and returns a new empty "AgreementState" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementStateType addNewAgreementState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementStateType)get_store().add_element_user(AGREEMENTSTATE$8);
            return target;
        }
    }
    
    /**
     * Gets array of all "GuaranteeTermState" elements
     */
    public org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType[] getGuaranteeTermStateArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(GUARANTEETERMSTATE$10, targetList);
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType[] result = new org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "GuaranteeTermState" element
     */
    public org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType getGuaranteeTermStateArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType)get_store().find_element_user(GUARANTEETERMSTATE$10, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "GuaranteeTermState" element
     */
    public int sizeOfGuaranteeTermStateArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(GUARANTEETERMSTATE$10);
        }
    }
    
    /**
     * Sets array of all "GuaranteeTermState" element
     */
    public void setGuaranteeTermStateArray(org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType[] guaranteeTermStateArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(guaranteeTermStateArray, GUARANTEETERMSTATE$10);
        }
    }
    
    /**
     * Sets ith "GuaranteeTermState" element
     */
    public void setGuaranteeTermStateArray(int i, org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType guaranteeTermState)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType)get_store().find_element_user(GUARANTEETERMSTATE$10, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(guaranteeTermState);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "GuaranteeTermState" element
     */
    public org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType insertNewGuaranteeTermState(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType)get_store().insert_element_user(GUARANTEETERMSTATE$10, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "GuaranteeTermState" element
     */
    public org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType addNewGuaranteeTermState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType)get_store().add_element_user(GUARANTEETERMSTATE$10);
            return target;
        }
    }
    
    /**
     * Removes the ith "GuaranteeTermState" element
     */
    public void removeGuaranteeTermState(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(GUARANTEETERMSTATE$10, i);
        }
    }
    
    /**
     * Gets array of all "ServiceTermState" elements
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceTermStateType[] getServiceTermStateArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SERVICETERMSTATE$12, targetList);
            org.ogf.schemas.graap.wsAgreement.ServiceTermStateType[] result = new org.ogf.schemas.graap.wsAgreement.ServiceTermStateType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ServiceTermState" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceTermStateType getServiceTermStateArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceTermStateType)get_store().find_element_user(SERVICETERMSTATE$12, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ServiceTermState" element
     */
    public int sizeOfServiceTermStateArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SERVICETERMSTATE$12);
        }
    }
    
    /**
     * Sets array of all "ServiceTermState" element
     */
    public void setServiceTermStateArray(org.ogf.schemas.graap.wsAgreement.ServiceTermStateType[] serviceTermStateArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(serviceTermStateArray, SERVICETERMSTATE$12);
        }
    }
    
    /**
     * Sets ith "ServiceTermState" element
     */
    public void setServiceTermStateArray(int i, org.ogf.schemas.graap.wsAgreement.ServiceTermStateType serviceTermState)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceTermStateType)get_store().find_element_user(SERVICETERMSTATE$12, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(serviceTermState);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ServiceTermState" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceTermStateType insertNewServiceTermState(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceTermStateType)get_store().insert_element_user(SERVICETERMSTATE$12, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ServiceTermState" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceTermStateType addNewServiceTermState()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceTermStateType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceTermStateType)get_store().add_element_user(SERVICETERMSTATE$12);
            return target;
        }
    }
    
    /**
     * Removes the ith "ServiceTermState" element
     */
    public void removeServiceTermState(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SERVICETERMSTATE$12, i);
        }
    }
}
