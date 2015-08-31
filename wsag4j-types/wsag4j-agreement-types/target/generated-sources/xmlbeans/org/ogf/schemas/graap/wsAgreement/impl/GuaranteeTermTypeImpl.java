/*
 * XML Type:  GuaranteeTermType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.GuaranteeTermType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML GuaranteeTermType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class GuaranteeTermTypeImpl extends org.ogf.schemas.graap.wsAgreement.impl.TermTypeImpl implements org.ogf.schemas.graap.wsAgreement.GuaranteeTermType
{
    private static final long serialVersionUID = 1L;
    
    public GuaranteeTermTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICESCOPE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ServiceScope");
    private static final javax.xml.namespace.QName QUALIFYINGCONDITION$2 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "QualifyingCondition");
    private static final javax.xml.namespace.QName SERVICELEVELOBJECTIVE$4 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ServiceLevelObjective");
    private static final javax.xml.namespace.QName BUSINESSVALUELIST$6 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "BusinessValueList");
    private static final javax.xml.namespace.QName OBLIGATED$8 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Obligated");
    
    
    /**
     * Gets array of all "ServiceScope" elements
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceSelectorType[] getServiceScopeArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SERVICESCOPE$0, targetList);
            org.ogf.schemas.graap.wsAgreement.ServiceSelectorType[] result = new org.ogf.schemas.graap.wsAgreement.ServiceSelectorType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ServiceScope" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceSelectorType getServiceScopeArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceSelectorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceSelectorType)get_store().find_element_user(SERVICESCOPE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ServiceScope" element
     */
    public int sizeOfServiceScopeArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SERVICESCOPE$0);
        }
    }
    
    /**
     * Sets array of all "ServiceScope" element
     */
    public void setServiceScopeArray(org.ogf.schemas.graap.wsAgreement.ServiceSelectorType[] serviceScopeArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(serviceScopeArray, SERVICESCOPE$0);
        }
    }
    
    /**
     * Sets ith "ServiceScope" element
     */
    public void setServiceScopeArray(int i, org.ogf.schemas.graap.wsAgreement.ServiceSelectorType serviceScope)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceSelectorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceSelectorType)get_store().find_element_user(SERVICESCOPE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(serviceScope);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ServiceScope" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceSelectorType insertNewServiceScope(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceSelectorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceSelectorType)get_store().insert_element_user(SERVICESCOPE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ServiceScope" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceSelectorType addNewServiceScope()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceSelectorType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceSelectorType)get_store().add_element_user(SERVICESCOPE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "ServiceScope" element
     */
    public void removeServiceScope(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SERVICESCOPE$0, i);
        }
    }
    
    /**
     * Gets the "QualifyingCondition" element
     */
    public org.apache.xmlbeans.XmlObject getQualifyingCondition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(QUALIFYINGCONDITION$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "QualifyingCondition" element
     */
    public boolean isSetQualifyingCondition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(QUALIFYINGCONDITION$2) != 0;
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
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(QUALIFYINGCONDITION$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(QUALIFYINGCONDITION$2);
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
            target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(QUALIFYINGCONDITION$2);
            return target;
        }
    }
    
    /**
     * Unsets the "QualifyingCondition" element
     */
    public void unsetQualifyingCondition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(QUALIFYINGCONDITION$2, 0);
        }
    }
    
    /**
     * Gets the "ServiceLevelObjective" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType getServiceLevelObjective()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType)get_store().find_element_user(SERVICELEVELOBJECTIVE$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ServiceLevelObjective" element
     */
    public void setServiceLevelObjective(org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType serviceLevelObjective)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType)get_store().find_element_user(SERVICELEVELOBJECTIVE$4, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType)get_store().add_element_user(SERVICELEVELOBJECTIVE$4);
            }
            target.set(serviceLevelObjective);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceLevelObjective" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType addNewServiceLevelObjective()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType)get_store().add_element_user(SERVICELEVELOBJECTIVE$4);
            return target;
        }
    }
    
    /**
     * Gets the "BusinessValueList" element
     */
    public org.ogf.schemas.graap.wsAgreement.BusinessValueListType getBusinessValueList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.BusinessValueListType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.BusinessValueListType)get_store().find_element_user(BUSINESSVALUELIST$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "BusinessValueList" element
     */
    public void setBusinessValueList(org.ogf.schemas.graap.wsAgreement.BusinessValueListType businessValueList)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.BusinessValueListType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.BusinessValueListType)get_store().find_element_user(BUSINESSVALUELIST$6, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.BusinessValueListType)get_store().add_element_user(BUSINESSVALUELIST$6);
            }
            target.set(businessValueList);
        }
    }
    
    /**
     * Appends and returns a new empty "BusinessValueList" element
     */
    public org.ogf.schemas.graap.wsAgreement.BusinessValueListType addNewBusinessValueList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.BusinessValueListType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.BusinessValueListType)get_store().add_element_user(BUSINESSVALUELIST$6);
            return target;
        }
    }
    
    /**
     * Gets the "Obligated" attribute
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceRoleType.Enum getObligated()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(OBLIGATED$8);
            if (target == null)
            {
                return null;
            }
            return (org.ogf.schemas.graap.wsAgreement.ServiceRoleType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "Obligated" attribute
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceRoleType xgetObligated()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceRoleType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceRoleType)get_store().find_attribute_user(OBLIGATED$8);
            return target;
        }
    }
    
    /**
     * True if has "Obligated" attribute
     */
    public boolean isSetObligated()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(OBLIGATED$8) != null;
        }
    }
    
    /**
     * Sets the "Obligated" attribute
     */
    public void setObligated(org.ogf.schemas.graap.wsAgreement.ServiceRoleType.Enum obligated)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(OBLIGATED$8);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(OBLIGATED$8);
            }
            target.setEnumValue(obligated);
        }
    }
    
    /**
     * Sets (as xml) the "Obligated" attribute
     */
    public void xsetObligated(org.ogf.schemas.graap.wsAgreement.ServiceRoleType obligated)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceRoleType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceRoleType)get_store().find_attribute_user(OBLIGATED$8);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.ServiceRoleType)get_store().add_attribute_user(OBLIGATED$8);
            }
            target.set(obligated);
        }
    }
    
    /**
     * Unsets the "Obligated" attribute
     */
    public void unsetObligated()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(OBLIGATED$8);
        }
    }
}
