/*
 * An XML document type.
 * Localname: QualifyingCondition
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.QualifyingConditionDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one QualifyingCondition(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class QualifyingConditionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.QualifyingConditionDocument
{
    private static final long serialVersionUID = 1L;
    
    public QualifyingConditionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName QUALIFYINGCONDITION$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "QualifyingCondition");
    
    
    /**
     * Gets the "QualifyingCondition" element
     */
    public org.apache.xmlbeans.XmlObject getQualifyingCondition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(QUALIFYINGCONDITION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "QualifyingCondition" element
     */
    public void setQualifyingCondition(org.apache.xmlbeans.XmlObject qualifyingCondition)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(QUALIFYINGCONDITION$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(QUALIFYINGCONDITION$0);
            }
            target.set(qualifyingCondition);
        }
    }
    
    /**
     * Appends and returns a new empty "QualifyingCondition" element
     */
    public org.apache.xmlbeans.XmlObject addNewQualifyingCondition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(QUALIFYINGCONDITION$0);
            return target;
        }
    }
}
