/*
 * XML Type:  AgreementTemplateType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AgreementTemplateType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML AgreementTemplateType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class AgreementTemplateTypeImpl extends org.ogf.schemas.graap.wsAgreement.impl.AgreementTypeImpl implements org.ogf.schemas.graap.wsAgreement.AgreementTemplateType
{
    private static final long serialVersionUID = 1L;
    
    public AgreementTemplateTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CREATIONCONSTRAINTS$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "CreationConstraints");
    private static final javax.xml.namespace.QName TEMPLATEID$2 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "TemplateId");
    
    
    /**
     * Gets the "CreationConstraints" element
     */
    public org.ogf.schemas.graap.wsAgreement.ConstraintSectionType getCreationConstraints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ConstraintSectionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ConstraintSectionType)get_store().find_element_user(CREATIONCONSTRAINTS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CreationConstraints" element
     */
    public void setCreationConstraints(org.ogf.schemas.graap.wsAgreement.ConstraintSectionType creationConstraints)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ConstraintSectionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ConstraintSectionType)get_store().find_element_user(CREATIONCONSTRAINTS$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.ConstraintSectionType)get_store().add_element_user(CREATIONCONSTRAINTS$0);
            }
            target.set(creationConstraints);
        }
    }
    
    /**
     * Appends and returns a new empty "CreationConstraints" element
     */
    public org.ogf.schemas.graap.wsAgreement.ConstraintSectionType addNewCreationConstraints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ConstraintSectionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ConstraintSectionType)get_store().add_element_user(CREATIONCONSTRAINTS$0);
            return target;
        }
    }
    
    /**
     * Gets the "TemplateId" attribute
     */
    public java.lang.String getTemplateId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TEMPLATEID$2);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "TemplateId" attribute
     */
    public org.apache.xmlbeans.XmlString xgetTemplateId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(TEMPLATEID$2);
            return target;
        }
    }
    
    /**
     * True if has "TemplateId" attribute
     */
    public boolean isSetTemplateId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(TEMPLATEID$2) != null;
        }
    }
    
    /**
     * Sets the "TemplateId" attribute
     */
    public void setTemplateId(java.lang.String templateId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TEMPLATEID$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(TEMPLATEID$2);
            }
            target.setStringValue(templateId);
        }
    }
    
    /**
     * Sets (as xml) the "TemplateId" attribute
     */
    public void xsetTemplateId(org.apache.xmlbeans.XmlString templateId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(TEMPLATEID$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(TEMPLATEID$2);
            }
            target.set(templateId);
        }
    }
    
    /**
     * Unsets the "TemplateId" attribute
     */
    public void unsetTemplateId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(TEMPLATEID$2);
        }
    }
}
