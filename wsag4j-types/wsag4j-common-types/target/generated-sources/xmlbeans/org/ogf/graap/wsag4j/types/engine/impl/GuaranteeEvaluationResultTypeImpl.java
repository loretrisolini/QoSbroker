/*
 * XML Type:  GuaranteeEvaluationResultType
 * Namespace: http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine
 * Java type: org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.engine.impl;
/**
 * An XML GuaranteeEvaluationResultType(@http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine).
 *
 * This is a complex type.
 */
public class GuaranteeEvaluationResultTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType
{
    private static final long serialVersionUID = 1L;
    
    public GuaranteeEvaluationResultTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NAME$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "Name");
    private static final javax.xml.namespace.QName TYPE$2 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "Type");
    private static final javax.xml.namespace.QName IMPORTANCE$4 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "Importance");
    private static final javax.xml.namespace.QName COMPENSATION$6 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "Compensation");
    private static final javax.xml.namespace.QName DETAILS$8 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "Details");
    
    
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
     * Gets the "Type" element
     */
    public org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType.Enum getType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TYPE$2, 0);
            if (target == null)
            {
                return null;
            }
            return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "Type" element
     */
    public org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType xgetType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType)get_store().find_element_user(TYPE$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Type" element
     */
    public void setType(org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType.Enum type)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TYPE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TYPE$2);
            }
            target.setEnumValue(type);
        }
    }
    
    /**
     * Sets (as xml) the "Type" element
     */
    public void xsetType(org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType type)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType)get_store().find_element_user(TYPE$2, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType)get_store().add_element_user(TYPE$2);
            }
            target.set(type);
        }
    }
    
    /**
     * Gets the "Importance" element
     */
    public int getImportance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(IMPORTANCE$4, 0);
            if (target == null)
            {
                return 0;
            }
            return target.getIntValue();
        }
    }
    
    /**
     * Gets (as xml) the "Importance" element
     */
    public org.apache.xmlbeans.XmlInt xgetImportance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInt target = null;
            target = (org.apache.xmlbeans.XmlInt)get_store().find_element_user(IMPORTANCE$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Importance" element
     */
    public void setImportance(int importance)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(IMPORTANCE$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(IMPORTANCE$4);
            }
            target.setIntValue(importance);
        }
    }
    
    /**
     * Sets (as xml) the "Importance" element
     */
    public void xsetImportance(org.apache.xmlbeans.XmlInt importance)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInt target = null;
            target = (org.apache.xmlbeans.XmlInt)get_store().find_element_user(IMPORTANCE$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInt)get_store().add_element_user(IMPORTANCE$4);
            }
            target.set(importance);
        }
    }
    
    /**
     * Gets the "Compensation" element
     */
    public org.ogf.graap.wsag4j.types.engine.CompensationType getCompensation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.CompensationType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.CompensationType)get_store().find_element_user(COMPENSATION$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Compensation" element
     */
    public boolean isSetCompensation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(COMPENSATION$6) != 0;
        }
    }
    
    /**
     * Sets the "Compensation" element
     */
    public void setCompensation(org.ogf.graap.wsag4j.types.engine.CompensationType compensation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.CompensationType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.CompensationType)get_store().find_element_user(COMPENSATION$6, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.engine.CompensationType)get_store().add_element_user(COMPENSATION$6);
            }
            target.set(compensation);
        }
    }
    
    /**
     * Appends and returns a new empty "Compensation" element
     */
    public org.ogf.graap.wsag4j.types.engine.CompensationType addNewCompensation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.CompensationType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.CompensationType)get_store().add_element_user(COMPENSATION$6);
            return target;
        }
    }
    
    /**
     * Unsets the "Compensation" element
     */
    public void unsetCompensation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(COMPENSATION$6, 0);
        }
    }
    
    /**
     * Gets the "Details" element
     */
    public org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationDetailType getDetails()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationDetailType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationDetailType)get_store().find_element_user(DETAILS$8, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Details" element
     */
    public void setDetails(org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationDetailType details)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationDetailType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationDetailType)get_store().find_element_user(DETAILS$8, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationDetailType)get_store().add_element_user(DETAILS$8);
            }
            target.set(details);
        }
    }
    
    /**
     * Appends and returns a new empty "Details" element
     */
    public org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationDetailType addNewDetails()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationDetailType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationDetailType)get_store().add_element_user(DETAILS$8);
            return target;
        }
    }
}
