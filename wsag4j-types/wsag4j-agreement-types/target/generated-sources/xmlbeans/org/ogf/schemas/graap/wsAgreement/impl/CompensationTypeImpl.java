/*
 * XML Type:  CompensationType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.CompensationType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML CompensationType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class CompensationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.CompensationType
{
    private static final long serialVersionUID = 1L;
    
    public CompensationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ASSESSMENTINTERVAL$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "AssessmentInterval");
    private static final javax.xml.namespace.QName VALUEUNIT$2 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ValueUnit");
    private static final javax.xml.namespace.QName VALUEEXPRESSION$4 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ValueExpression");
    
    
    /**
     * Gets the "AssessmentInterval" element
     */
    public org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval getAssessmentInterval()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval)get_store().find_element_user(ASSESSMENTINTERVAL$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AssessmentInterval" element
     */
    public void setAssessmentInterval(org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval assessmentInterval)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval)get_store().find_element_user(ASSESSMENTINTERVAL$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval)get_store().add_element_user(ASSESSMENTINTERVAL$0);
            }
            target.set(assessmentInterval);
        }
    }
    
    /**
     * Appends and returns a new empty "AssessmentInterval" element
     */
    public org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval addNewAssessmentInterval()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval)get_store().add_element_user(ASSESSMENTINTERVAL$0);
            return target;
        }
    }
    
    /**
     * Gets the "ValueUnit" element
     */
    public java.lang.String getValueUnit()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VALUEUNIT$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "ValueUnit" element
     */
    public org.apache.xmlbeans.XmlString xgetValueUnit()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VALUEUNIT$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "ValueUnit" element
     */
    public boolean isSetValueUnit()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(VALUEUNIT$2) != 0;
        }
    }
    
    /**
     * Sets the "ValueUnit" element
     */
    public void setValueUnit(java.lang.String valueUnit)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VALUEUNIT$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(VALUEUNIT$2);
            }
            target.setStringValue(valueUnit);
        }
    }
    
    /**
     * Sets (as xml) the "ValueUnit" element
     */
    public void xsetValueUnit(org.apache.xmlbeans.XmlString valueUnit)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VALUEUNIT$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(VALUEUNIT$2);
            }
            target.set(valueUnit);
        }
    }
    
    /**
     * Unsets the "ValueUnit" element
     */
    public void unsetValueUnit()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(VALUEUNIT$2, 0);
        }
    }
    
    /**
     * Gets the "ValueExpression" element
     */
    public org.apache.xmlbeans.XmlObject getValueExpression()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(VALUEEXPRESSION$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ValueExpression" element
     */
    public void setValueExpression(org.apache.xmlbeans.XmlObject valueExpression)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(VALUEEXPRESSION$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(VALUEEXPRESSION$4);
            }
            target.set(valueExpression);
        }
    }
    
    /**
     * Appends and returns a new empty "ValueExpression" element
     */
    public org.apache.xmlbeans.XmlObject addNewValueExpression()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(VALUEEXPRESSION$4);
            return target;
        }
    }
    /**
     * An XML AssessmentInterval(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
     *
     * This is a complex type.
     */
    public static class AssessmentIntervalImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval
    {
        private static final long serialVersionUID = 1L;
        
        public AssessmentIntervalImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName TIMEINTERVAL$0 = 
            new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "TimeInterval");
        private static final javax.xml.namespace.QName COUNT$2 = 
            new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Count");
        
        
        /**
         * Gets the "TimeInterval" element
         */
        public org.apache.xmlbeans.GDuration getTimeInterval()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIMEINTERVAL$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getGDurationValue();
            }
        }
        
        /**
         * Gets (as xml) the "TimeInterval" element
         */
        public org.apache.xmlbeans.XmlDuration xgetTimeInterval()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlDuration target = null;
                target = (org.apache.xmlbeans.XmlDuration)get_store().find_element_user(TIMEINTERVAL$0, 0);
                return target;
            }
        }
        
        /**
         * True if has "TimeInterval" element
         */
        public boolean isSetTimeInterval()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TIMEINTERVAL$0) != 0;
            }
        }
        
        /**
         * Sets the "TimeInterval" element
         */
        public void setTimeInterval(org.apache.xmlbeans.GDuration timeInterval)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIMEINTERVAL$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TIMEINTERVAL$0);
                }
                target.setGDurationValue(timeInterval);
            }
        }
        
        /**
         * Sets (as xml) the "TimeInterval" element
         */
        public void xsetTimeInterval(org.apache.xmlbeans.XmlDuration timeInterval)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlDuration target = null;
                target = (org.apache.xmlbeans.XmlDuration)get_store().find_element_user(TIMEINTERVAL$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlDuration)get_store().add_element_user(TIMEINTERVAL$0);
                }
                target.set(timeInterval);
            }
        }
        
        /**
         * Unsets the "TimeInterval" element
         */
        public void unsetTimeInterval()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TIMEINTERVAL$0, 0);
            }
        }
        
        /**
         * Gets the "Count" element
         */
        public java.math.BigInteger getCount()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COUNT$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getBigIntegerValue();
            }
        }
        
        /**
         * Gets (as xml) the "Count" element
         */
        public org.apache.xmlbeans.XmlPositiveInteger xgetCount()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlPositiveInteger target = null;
                target = (org.apache.xmlbeans.XmlPositiveInteger)get_store().find_element_user(COUNT$2, 0);
                return target;
            }
        }
        
        /**
         * True if has "Count" element
         */
        public boolean isSetCount()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(COUNT$2) != 0;
            }
        }
        
        /**
         * Sets the "Count" element
         */
        public void setCount(java.math.BigInteger count)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COUNT$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COUNT$2);
                }
                target.setBigIntegerValue(count);
            }
        }
        
        /**
         * Sets (as xml) the "Count" element
         */
        public void xsetCount(org.apache.xmlbeans.XmlPositiveInteger count)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlPositiveInteger target = null;
                target = (org.apache.xmlbeans.XmlPositiveInteger)get_store().find_element_user(COUNT$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlPositiveInteger)get_store().add_element_user(COUNT$2);
                }
                target.set(count);
            }
        }
        
        /**
         * Unsets the "Count" element
         */
        public void unsetCount()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(COUNT$2, 0);
            }
        }
    }
}
