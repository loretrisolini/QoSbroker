/*
 * XML Type:  SLAMonitoringNotificationEventType
 * Namespace: http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine
 * Java type: org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.engine.impl;
/**
 * An XML SLAMonitoringNotificationEventType(@http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine).
 *
 * This is a complex type.
 */
public class SLAMonitoringNotificationEventTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType
{
    private static final long serialVersionUID = 1L;
    
    public SLAMonitoringNotificationEventTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GUARANTEEEVALUATIONRESULT$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "GuaranteeEvaluationResult");
    private static final javax.xml.namespace.QName AGREEMENTID$2 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "agreementId");
    
    
    /**
     * Gets array of all "GuaranteeEvaluationResult" elements
     */
    public org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType[] getGuaranteeEvaluationResultArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(GUARANTEEEVALUATIONRESULT$0, targetList);
            org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType[] result = new org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "GuaranteeEvaluationResult" element
     */
    public org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType getGuaranteeEvaluationResultArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType)get_store().find_element_user(GUARANTEEEVALUATIONRESULT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "GuaranteeEvaluationResult" element
     */
    public int sizeOfGuaranteeEvaluationResultArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(GUARANTEEEVALUATIONRESULT$0);
        }
    }
    
    /**
     * Sets array of all "GuaranteeEvaluationResult" element
     */
    public void setGuaranteeEvaluationResultArray(org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType[] guaranteeEvaluationResultArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(guaranteeEvaluationResultArray, GUARANTEEEVALUATIONRESULT$0);
        }
    }
    
    /**
     * Sets ith "GuaranteeEvaluationResult" element
     */
    public void setGuaranteeEvaluationResultArray(int i, org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType guaranteeEvaluationResult)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType)get_store().find_element_user(GUARANTEEEVALUATIONRESULT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(guaranteeEvaluationResult);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "GuaranteeEvaluationResult" element
     */
    public org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType insertNewGuaranteeEvaluationResult(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType)get_store().insert_element_user(GUARANTEEEVALUATIONRESULT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "GuaranteeEvaluationResult" element
     */
    public org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType addNewGuaranteeEvaluationResult()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType)get_store().add_element_user(GUARANTEEEVALUATIONRESULT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "GuaranteeEvaluationResult" element
     */
    public void removeGuaranteeEvaluationResult(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(GUARANTEEEVALUATIONRESULT$0, i);
        }
    }
    
    /**
     * Gets the "agreementId" attribute
     */
    public java.lang.String getAgreementId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(AGREEMENTID$2);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "agreementId" attribute
     */
    public org.apache.xmlbeans.XmlString xgetAgreementId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(AGREEMENTID$2);
            return target;
        }
    }
    
    /**
     * True if has "agreementId" attribute
     */
    public boolean isSetAgreementId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(AGREEMENTID$2) != null;
        }
    }
    
    /**
     * Sets the "agreementId" attribute
     */
    public void setAgreementId(java.lang.String agreementId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(AGREEMENTID$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(AGREEMENTID$2);
            }
            target.setStringValue(agreementId);
        }
    }
    
    /**
     * Sets (as xml) the "agreementId" attribute
     */
    public void xsetAgreementId(org.apache.xmlbeans.XmlString agreementId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(AGREEMENTID$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(AGREEMENTID$2);
            }
            target.set(agreementId);
        }
    }
    
    /**
     * Unsets the "agreementId" attribute
     */
    public void unsetAgreementId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(AGREEMENTID$2);
        }
    }
}
