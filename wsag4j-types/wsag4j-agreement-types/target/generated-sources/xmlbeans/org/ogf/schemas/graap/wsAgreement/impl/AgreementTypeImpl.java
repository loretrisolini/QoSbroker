/*
 * XML Type:  AgreementType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AgreementType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML AgreementType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class AgreementTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.AgreementType
{
    private static final long serialVersionUID = 1L;
    
    public AgreementTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NAME$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Name");
    private static final javax.xml.namespace.QName CONTEXT$2 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Context");
    private static final javax.xml.namespace.QName TERMS$4 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Terms");
    private static final javax.xml.namespace.QName AGREEMENTID$6 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "AgreementId");
    
    
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
     * Gets the "Context" element
     */
    public org.ogf.schemas.graap.wsAgreement.AgreementContextType getContext()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.AgreementContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.AgreementContextType)get_store().find_element_user(CONTEXT$2, 0);
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
            target = (org.ogf.schemas.graap.wsAgreement.AgreementContextType)get_store().find_element_user(CONTEXT$2, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.AgreementContextType)get_store().add_element_user(CONTEXT$2);
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
            target = (org.ogf.schemas.graap.wsAgreement.AgreementContextType)get_store().add_element_user(CONTEXT$2);
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
            target = (org.ogf.schemas.graap.wsAgreement.TermTreeType)get_store().find_element_user(TERMS$4, 0);
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
            target = (org.ogf.schemas.graap.wsAgreement.TermTreeType)get_store().find_element_user(TERMS$4, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.TermTreeType)get_store().add_element_user(TERMS$4);
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
            target = (org.ogf.schemas.graap.wsAgreement.TermTreeType)get_store().add_element_user(TERMS$4);
            return target;
        }
    }
    
    /**
     * Gets the "AgreementId" attribute
     */
    public java.lang.String getAgreementId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(AGREEMENTID$6);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "AgreementId" attribute
     */
    public org.apache.xmlbeans.XmlString xgetAgreementId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(AGREEMENTID$6);
            return target;
        }
    }
    
    /**
     * True if has "AgreementId" attribute
     */
    public boolean isSetAgreementId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(AGREEMENTID$6) != null;
        }
    }
    
    /**
     * Sets the "AgreementId" attribute
     */
    public void setAgreementId(java.lang.String agreementId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(AGREEMENTID$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(AGREEMENTID$6);
            }
            target.setStringValue(agreementId);
        }
    }
    
    /**
     * Sets (as xml) the "AgreementId" attribute
     */
    public void xsetAgreementId(org.apache.xmlbeans.XmlString agreementId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(AGREEMENTID$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(AGREEMENTID$6);
            }
            target.set(agreementId);
        }
    }
    
    /**
     * Unsets the "AgreementId" attribute
     */
    public void unsetAgreementId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(AGREEMENTID$6);
        }
    }
}
