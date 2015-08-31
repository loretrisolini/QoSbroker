/*
 * XML Type:  BaseFaultType
 * Namespace: http://docs.oasis-open.org/wsrf/bf-2
 * Java type: org.oasisOpen.docs.wsrf.bf2.BaseFaultType
 *
 * Automatically generated - do not modify.
 */
package org.oasisOpen.docs.wsrf.bf2.impl;
/**
 * An XML BaseFaultType(@http://docs.oasis-open.org/wsrf/bf-2).
 *
 * This is a complex type.
 */
public class BaseFaultTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.oasisOpen.docs.wsrf.bf2.BaseFaultType
{
    private static final long serialVersionUID = 1L;
    
    public BaseFaultTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TIMESTAMP$0 = 
        new javax.xml.namespace.QName("http://docs.oasis-open.org/wsrf/bf-2", "Timestamp");
    private static final javax.xml.namespace.QName ORIGINATOR$2 = 
        new javax.xml.namespace.QName("http://docs.oasis-open.org/wsrf/bf-2", "Originator");
    private static final javax.xml.namespace.QName ERRORCODE$4 = 
        new javax.xml.namespace.QName("http://docs.oasis-open.org/wsrf/bf-2", "ErrorCode");
    private static final javax.xml.namespace.QName DESCRIPTION$6 = 
        new javax.xml.namespace.QName("http://docs.oasis-open.org/wsrf/bf-2", "Description");
    private static final javax.xml.namespace.QName FAULTCAUSE$8 = 
        new javax.xml.namespace.QName("http://docs.oasis-open.org/wsrf/bf-2", "FaultCause");
    
    
    /**
     * Gets the "Timestamp" element
     */
    public java.util.Calendar getTimestamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIMESTAMP$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "Timestamp" element
     */
    public org.apache.xmlbeans.XmlDateTime xgetTimestamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIMESTAMP$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Timestamp" element
     */
    public void setTimestamp(java.util.Calendar timestamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TIMESTAMP$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TIMESTAMP$0);
            }
            target.setCalendarValue(timestamp);
        }
    }
    
    /**
     * Sets (as xml) the "Timestamp" element
     */
    public void xsetTimestamp(org.apache.xmlbeans.XmlDateTime timestamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(TIMESTAMP$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(TIMESTAMP$0);
            }
            target.set(timestamp);
        }
    }
    
    /**
     * Gets the "Originator" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType getOriginator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(ORIGINATOR$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Originator" element
     */
    public boolean isSetOriginator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ORIGINATOR$2) != 0;
        }
    }
    
    /**
     * Sets the "Originator" element
     */
    public void setOriginator(org.w3.x2005.x08.addressing.EndpointReferenceType originator)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(ORIGINATOR$2, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(ORIGINATOR$2);
            }
            target.set(originator);
        }
    }
    
    /**
     * Appends and returns a new empty "Originator" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType addNewOriginator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(ORIGINATOR$2);
            return target;
        }
    }
    
    /**
     * Unsets the "Originator" element
     */
    public void unsetOriginator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ORIGINATOR$2, 0);
        }
    }
    
    /**
     * Gets the "ErrorCode" element
     */
    public org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode getErrorCode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode target = null;
            target = (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode)get_store().find_element_user(ERRORCODE$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "ErrorCode" element
     */
    public boolean isSetErrorCode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ERRORCODE$4) != 0;
        }
    }
    
    /**
     * Sets the "ErrorCode" element
     */
    public void setErrorCode(org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode errorCode)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode target = null;
            target = (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode)get_store().find_element_user(ERRORCODE$4, 0);
            if (target == null)
            {
                target = (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode)get_store().add_element_user(ERRORCODE$4);
            }
            target.set(errorCode);
        }
    }
    
    /**
     * Appends and returns a new empty "ErrorCode" element
     */
    public org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode addNewErrorCode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode target = null;
            target = (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode)get_store().add_element_user(ERRORCODE$4);
            return target;
        }
    }
    
    /**
     * Unsets the "ErrorCode" element
     */
    public void unsetErrorCode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ERRORCODE$4, 0);
        }
    }
    
    /**
     * Gets array of all "Description" elements
     */
    public org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description[] getDescriptionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(DESCRIPTION$6, targetList);
            org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description[] result = new org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Description" element
     */
    public org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description getDescriptionArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description target = null;
            target = (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description)get_store().find_element_user(DESCRIPTION$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Description" element
     */
    public int sizeOfDescriptionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DESCRIPTION$6);
        }
    }
    
    /**
     * Sets array of all "Description" element
     */
    public void setDescriptionArray(org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description[] descriptionArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(descriptionArray, DESCRIPTION$6);
        }
    }
    
    /**
     * Sets ith "Description" element
     */
    public void setDescriptionArray(int i, org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description description)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description target = null;
            target = (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description)get_store().find_element_user(DESCRIPTION$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(description);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Description" element
     */
    public org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description insertNewDescription(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description target = null;
            target = (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description)get_store().insert_element_user(DESCRIPTION$6, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Description" element
     */
    public org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description addNewDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description target = null;
            target = (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description)get_store().add_element_user(DESCRIPTION$6);
            return target;
        }
    }
    
    /**
     * Removes the ith "Description" element
     */
    public void removeDescription(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DESCRIPTION$6, i);
        }
    }
    
    /**
     * Gets the "FaultCause" element
     */
    public org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause getFaultCause()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause target = null;
            target = (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause)get_store().find_element_user(FAULTCAUSE$8, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "FaultCause" element
     */
    public boolean isSetFaultCause()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(FAULTCAUSE$8) != 0;
        }
    }
    
    /**
     * Sets the "FaultCause" element
     */
    public void setFaultCause(org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause faultCause)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause target = null;
            target = (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause)get_store().find_element_user(FAULTCAUSE$8, 0);
            if (target == null)
            {
                target = (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause)get_store().add_element_user(FAULTCAUSE$8);
            }
            target.set(faultCause);
        }
    }
    
    /**
     * Appends and returns a new empty "FaultCause" element
     */
    public org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause addNewFaultCause()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause target = null;
            target = (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause)get_store().add_element_user(FAULTCAUSE$8);
            return target;
        }
    }
    
    /**
     * Unsets the "FaultCause" element
     */
    public void unsetFaultCause()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(FAULTCAUSE$8, 0);
        }
    }
    /**
     * An XML ErrorCode(@http://docs.oasis-open.org/wsrf/bf-2).
     *
     * This is a complex type.
     */
    public static class ErrorCodeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode
    {
        private static final long serialVersionUID = 1L;
        
        public ErrorCodeImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName DIALECT$0 = 
            new javax.xml.namespace.QName("", "dialect");
        
        
        /**
         * Gets the "dialect" attribute
         */
        public java.lang.String getDialect()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(DIALECT$0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "dialect" attribute
         */
        public org.apache.xmlbeans.XmlAnyURI xgetDialect()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlAnyURI target = null;
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(DIALECT$0);
                return target;
            }
        }
        
        /**
         * Sets the "dialect" attribute
         */
        public void setDialect(java.lang.String dialect)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(DIALECT$0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(DIALECT$0);
                }
                target.setStringValue(dialect);
            }
        }
        
        /**
         * Sets (as xml) the "dialect" attribute
         */
        public void xsetDialect(org.apache.xmlbeans.XmlAnyURI dialect)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlAnyURI target = null;
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(DIALECT$0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(DIALECT$0);
                }
                target.set(dialect);
            }
        }
    }
    /**
     * An XML Description(@http://docs.oasis-open.org/wsrf/bf-2).
     *
     * This is an atomic type that is a restriction of org.oasisOpen.docs.wsrf.bf2.BaseFaultType$Description.
     */
    public static class DescriptionImpl extends org.apache.xmlbeans.impl.values.JavaStringHolderEx implements org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description
    {
        private static final long serialVersionUID = 1L;
        
        public DescriptionImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, true);
        }
        
        protected DescriptionImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
        
        private static final javax.xml.namespace.QName LANG$0 = 
            new javax.xml.namespace.QName("http://www.w3.org/XML/1998/namespace", "lang");
        
        
        /**
         * Gets the "lang" attribute
         */
        public java.lang.String getLang()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(LANG$0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "lang" attribute
         */
        public org.apache.xmlbeans.XmlLanguage xgetLang()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlLanguage target = null;
                target = (org.apache.xmlbeans.XmlLanguage)get_store().find_attribute_user(LANG$0);
                return target;
            }
        }
        
        /**
         * True if has "lang" attribute
         */
        public boolean isSetLang()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(LANG$0) != null;
            }
        }
        
        /**
         * Sets the "lang" attribute
         */
        public void setLang(java.lang.String lang)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(LANG$0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(LANG$0);
                }
                target.setStringValue(lang);
            }
        }
        
        /**
         * Sets (as xml) the "lang" attribute
         */
        public void xsetLang(org.apache.xmlbeans.XmlLanguage lang)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlLanguage target = null;
                target = (org.apache.xmlbeans.XmlLanguage)get_store().find_attribute_user(LANG$0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlLanguage)get_store().add_attribute_user(LANG$0);
                }
                target.set(lang);
            }
        }
        
        /**
         * Unsets the "lang" attribute
         */
        public void unsetLang()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(LANG$0);
            }
        }
    }
    /**
     * An XML FaultCause(@http://docs.oasis-open.org/wsrf/bf-2).
     *
     * This is a complex type.
     */
    public static class FaultCauseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause
    {
        private static final long serialVersionUID = 1L;
        
        public FaultCauseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        
    }
}
