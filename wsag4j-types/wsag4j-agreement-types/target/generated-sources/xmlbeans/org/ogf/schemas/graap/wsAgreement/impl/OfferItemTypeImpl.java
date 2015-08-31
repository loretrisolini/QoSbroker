/*
 * XML Type:  OfferItemType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.OfferItemType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML OfferItemType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class OfferItemTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.OfferItemType
{
    private static final long serialVersionUID = 1L;
    
    public OfferItemTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName LOCATION$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Location");
    private static final javax.xml.namespace.QName ITEMCONSTRAINT$2 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ItemConstraint");
    private static final javax.xml.namespace.QName NAME$4 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Name");
    
    
    /**
     * Gets the "Location" element
     */
    public java.lang.String getLocation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(LOCATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Location" element
     */
    public org.apache.xmlbeans.XmlString xgetLocation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(LOCATION$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Location" element
     */
    public void setLocation(java.lang.String location)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(LOCATION$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(LOCATION$0);
            }
            target.setStringValue(location);
        }
    }
    
    /**
     * Sets (as xml) the "Location" element
     */
    public void xsetLocation(org.apache.xmlbeans.XmlString location)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(LOCATION$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(LOCATION$0);
            }
            target.set(location);
        }
    }
    
    /**
     * Gets the "ItemConstraint" element
     */
    public org.ogf.schemas.graap.wsAgreement.OfferItemType.ItemConstraint getItemConstraint()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.OfferItemType.ItemConstraint target = null;
            target = (org.ogf.schemas.graap.wsAgreement.OfferItemType.ItemConstraint)get_store().find_element_user(ITEMCONSTRAINT$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ItemConstraint" element
     */
    public void setItemConstraint(org.ogf.schemas.graap.wsAgreement.OfferItemType.ItemConstraint itemConstraint)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.OfferItemType.ItemConstraint target = null;
            target = (org.ogf.schemas.graap.wsAgreement.OfferItemType.ItemConstraint)get_store().find_element_user(ITEMCONSTRAINT$2, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.OfferItemType.ItemConstraint)get_store().add_element_user(ITEMCONSTRAINT$2);
            }
            target.set(itemConstraint);
        }
    }
    
    /**
     * Appends and returns a new empty "ItemConstraint" element
     */
    public org.ogf.schemas.graap.wsAgreement.OfferItemType.ItemConstraint addNewItemConstraint()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.OfferItemType.ItemConstraint target = null;
            target = (org.ogf.schemas.graap.wsAgreement.OfferItemType.ItemConstraint)get_store().add_element_user(ITEMCONSTRAINT$2);
            return target;
        }
    }
    
    /**
     * Gets the "Name" attribute
     */
    public java.lang.String getName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$4);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Name" attribute
     */
    public org.apache.xmlbeans.XmlString xgetName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$4);
            return target;
        }
    }
    
    /**
     * True if has "Name" attribute
     */
    public boolean isSetName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(NAME$4) != null;
        }
    }
    
    /**
     * Sets the "Name" attribute
     */
    public void setName(java.lang.String name)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(NAME$4);
            }
            target.setStringValue(name);
        }
    }
    
    /**
     * Sets (as xml) the "Name" attribute
     */
    public void xsetName(org.apache.xmlbeans.XmlString name)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(NAME$4);
            }
            target.set(name);
        }
    }
    
    /**
     * Unsets the "Name" attribute
     */
    public void unsetName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(NAME$4);
        }
    }
    /**
     * An XML ItemConstraint(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
     *
     * This is a complex type.
     */
    public static class ItemConstraintImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.OfferItemType.ItemConstraint
    {
        private static final long serialVersionUID = 1L;
        
        public ItemConstraintImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName SIMPLETYPE$0 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "simpleType");
        private static final javax.xml.namespace.QName MINEXCLUSIVE$2 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "minExclusive");
        private static final javax.xml.namespace.QName MININCLUSIVE$4 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "minInclusive");
        private static final javax.xml.namespace.QName MAXEXCLUSIVE$6 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "maxExclusive");
        private static final javax.xml.namespace.QName MAXINCLUSIVE$8 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "maxInclusive");
        private static final javax.xml.namespace.QName TOTALDIGITS$10 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "totalDigits");
        private static final javax.xml.namespace.QName FRACTIONDIGITS$12 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "fractionDigits");
        private static final javax.xml.namespace.QName LENGTH$14 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "length");
        private static final javax.xml.namespace.QName MINLENGTH$16 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "minLength");
        private static final javax.xml.namespace.QName MAXLENGTH$18 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "maxLength");
        private static final javax.xml.namespace.QName ENUMERATION$20 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "enumeration");
        private static final javax.xml.namespace.QName WHITESPACE$22 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "whiteSpace");
        private static final javax.xml.namespace.QName PATTERN$24 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "pattern");
        private static final javax.xml.namespace.QName GROUP$26 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "group");
        private static final javax.xml.namespace.QName ALL$28 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "all");
        private static final javax.xml.namespace.QName CHOICE$30 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "choice");
        private static final javax.xml.namespace.QName SEQUENCE$32 = 
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "sequence");
        
        
        /**
         * Gets the "simpleType" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType getSimpleType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType)get_store().find_element_user(SIMPLETYPE$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "simpleType" element
         */
        public boolean isSetSimpleType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(SIMPLETYPE$0) != 0;
            }
        }
        
        /**
         * Sets the "simpleType" element
         */
        public void setSimpleType(org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType simpleType)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType)get_store().find_element_user(SIMPLETYPE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType)get_store().add_element_user(SIMPLETYPE$0);
                }
                target.set(simpleType);
            }
        }
        
        /**
         * Appends and returns a new empty "simpleType" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType addNewSimpleType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType)get_store().add_element_user(SIMPLETYPE$0);
                return target;
            }
        }
        
        /**
         * Unsets the "simpleType" element
         */
        public void unsetSimpleType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(SIMPLETYPE$0, 0);
            }
        }
        
        /**
         * Gets array of all "minExclusive" elements
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.Facet[] getMinExclusiveArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(MINEXCLUSIVE$2, targetList);
                org.apache.xmlbeans.impl.xb.xsdschema.Facet[] result = new org.apache.xmlbeans.impl.xb.xsdschema.Facet[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "minExclusive" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.Facet getMinExclusiveArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.Facet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.Facet)get_store().find_element_user(MINEXCLUSIVE$2, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "minExclusive" element
         */
        public int sizeOfMinExclusiveArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(MINEXCLUSIVE$2);
            }
        }
        
        /**
         * Sets array of all "minExclusive" element
         */
        public void setMinExclusiveArray(org.apache.xmlbeans.impl.xb.xsdschema.Facet[] minExclusiveArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(minExclusiveArray, MINEXCLUSIVE$2);
            }
        }
        
        /**
         * Sets ith "minExclusive" element
         */
        public void setMinExclusiveArray(int i, org.apache.xmlbeans.impl.xb.xsdschema.Facet minExclusive)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.Facet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.Facet)get_store().find_element_user(MINEXCLUSIVE$2, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(minExclusive);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "minExclusive" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.Facet insertNewMinExclusive(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.Facet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.Facet)get_store().insert_element_user(MINEXCLUSIVE$2, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "minExclusive" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.Facet addNewMinExclusive()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.Facet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.Facet)get_store().add_element_user(MINEXCLUSIVE$2);
                return target;
            }
        }
        
        /**
         * Removes the ith "minExclusive" element
         */
        public void removeMinExclusive(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(MINEXCLUSIVE$2, i);
            }
        }
        
        /**
         * Gets array of all "minInclusive" elements
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.Facet[] getMinInclusiveArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(MININCLUSIVE$4, targetList);
                org.apache.xmlbeans.impl.xb.xsdschema.Facet[] result = new org.apache.xmlbeans.impl.xb.xsdschema.Facet[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "minInclusive" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.Facet getMinInclusiveArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.Facet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.Facet)get_store().find_element_user(MININCLUSIVE$4, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "minInclusive" element
         */
        public int sizeOfMinInclusiveArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(MININCLUSIVE$4);
            }
        }
        
        /**
         * Sets array of all "minInclusive" element
         */
        public void setMinInclusiveArray(org.apache.xmlbeans.impl.xb.xsdschema.Facet[] minInclusiveArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(minInclusiveArray, MININCLUSIVE$4);
            }
        }
        
        /**
         * Sets ith "minInclusive" element
         */
        public void setMinInclusiveArray(int i, org.apache.xmlbeans.impl.xb.xsdschema.Facet minInclusive)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.Facet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.Facet)get_store().find_element_user(MININCLUSIVE$4, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(minInclusive);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "minInclusive" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.Facet insertNewMinInclusive(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.Facet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.Facet)get_store().insert_element_user(MININCLUSIVE$4, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "minInclusive" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.Facet addNewMinInclusive()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.Facet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.Facet)get_store().add_element_user(MININCLUSIVE$4);
                return target;
            }
        }
        
        /**
         * Removes the ith "minInclusive" element
         */
        public void removeMinInclusive(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(MININCLUSIVE$4, i);
            }
        }
        
        /**
         * Gets array of all "maxExclusive" elements
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.Facet[] getMaxExclusiveArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(MAXEXCLUSIVE$6, targetList);
                org.apache.xmlbeans.impl.xb.xsdschema.Facet[] result = new org.apache.xmlbeans.impl.xb.xsdschema.Facet[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "maxExclusive" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.Facet getMaxExclusiveArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.Facet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.Facet)get_store().find_element_user(MAXEXCLUSIVE$6, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "maxExclusive" element
         */
        public int sizeOfMaxExclusiveArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(MAXEXCLUSIVE$6);
            }
        }
        
        /**
         * Sets array of all "maxExclusive" element
         */
        public void setMaxExclusiveArray(org.apache.xmlbeans.impl.xb.xsdschema.Facet[] maxExclusiveArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(maxExclusiveArray, MAXEXCLUSIVE$6);
            }
        }
        
        /**
         * Sets ith "maxExclusive" element
         */
        public void setMaxExclusiveArray(int i, org.apache.xmlbeans.impl.xb.xsdschema.Facet maxExclusive)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.Facet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.Facet)get_store().find_element_user(MAXEXCLUSIVE$6, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(maxExclusive);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "maxExclusive" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.Facet insertNewMaxExclusive(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.Facet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.Facet)get_store().insert_element_user(MAXEXCLUSIVE$6, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "maxExclusive" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.Facet addNewMaxExclusive()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.Facet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.Facet)get_store().add_element_user(MAXEXCLUSIVE$6);
                return target;
            }
        }
        
        /**
         * Removes the ith "maxExclusive" element
         */
        public void removeMaxExclusive(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(MAXEXCLUSIVE$6, i);
            }
        }
        
        /**
         * Gets array of all "maxInclusive" elements
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.Facet[] getMaxInclusiveArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(MAXINCLUSIVE$8, targetList);
                org.apache.xmlbeans.impl.xb.xsdschema.Facet[] result = new org.apache.xmlbeans.impl.xb.xsdschema.Facet[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "maxInclusive" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.Facet getMaxInclusiveArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.Facet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.Facet)get_store().find_element_user(MAXINCLUSIVE$8, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "maxInclusive" element
         */
        public int sizeOfMaxInclusiveArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(MAXINCLUSIVE$8);
            }
        }
        
        /**
         * Sets array of all "maxInclusive" element
         */
        public void setMaxInclusiveArray(org.apache.xmlbeans.impl.xb.xsdschema.Facet[] maxInclusiveArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(maxInclusiveArray, MAXINCLUSIVE$8);
            }
        }
        
        /**
         * Sets ith "maxInclusive" element
         */
        public void setMaxInclusiveArray(int i, org.apache.xmlbeans.impl.xb.xsdschema.Facet maxInclusive)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.Facet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.Facet)get_store().find_element_user(MAXINCLUSIVE$8, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(maxInclusive);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "maxInclusive" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.Facet insertNewMaxInclusive(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.Facet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.Facet)get_store().insert_element_user(MAXINCLUSIVE$8, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "maxInclusive" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.Facet addNewMaxInclusive()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.Facet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.Facet)get_store().add_element_user(MAXINCLUSIVE$8);
                return target;
            }
        }
        
        /**
         * Removes the ith "maxInclusive" element
         */
        public void removeMaxInclusive(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(MAXINCLUSIVE$8, i);
            }
        }
        
        /**
         * Gets array of all "totalDigits" elements
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits[] getTotalDigitsArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(TOTALDIGITS$10, targetList);
                org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits[] result = new org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "totalDigits" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits getTotalDigitsArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits)get_store().find_element_user(TOTALDIGITS$10, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "totalDigits" element
         */
        public int sizeOfTotalDigitsArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(TOTALDIGITS$10);
            }
        }
        
        /**
         * Sets array of all "totalDigits" element
         */
        public void setTotalDigitsArray(org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits[] totalDigitsArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(totalDigitsArray, TOTALDIGITS$10);
            }
        }
        
        /**
         * Sets ith "totalDigits" element
         */
        public void setTotalDigitsArray(int i, org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits totalDigits)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits)get_store().find_element_user(TOTALDIGITS$10, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(totalDigits);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "totalDigits" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits insertNewTotalDigits(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits)get_store().insert_element_user(TOTALDIGITS$10, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "totalDigits" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits addNewTotalDigits()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits)get_store().add_element_user(TOTALDIGITS$10);
                return target;
            }
        }
        
        /**
         * Removes the ith "totalDigits" element
         */
        public void removeTotalDigits(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(TOTALDIGITS$10, i);
            }
        }
        
        /**
         * Gets array of all "fractionDigits" elements
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NumFacet[] getFractionDigitsArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(FRACTIONDIGITS$12, targetList);
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet[] result = new org.apache.xmlbeans.impl.xb.xsdschema.NumFacet[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "fractionDigits" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NumFacet getFractionDigitsArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NumFacet)get_store().find_element_user(FRACTIONDIGITS$12, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "fractionDigits" element
         */
        public int sizeOfFractionDigitsArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(FRACTIONDIGITS$12);
            }
        }
        
        /**
         * Sets array of all "fractionDigits" element
         */
        public void setFractionDigitsArray(org.apache.xmlbeans.impl.xb.xsdschema.NumFacet[] fractionDigitsArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(fractionDigitsArray, FRACTIONDIGITS$12);
            }
        }
        
        /**
         * Sets ith "fractionDigits" element
         */
        public void setFractionDigitsArray(int i, org.apache.xmlbeans.impl.xb.xsdschema.NumFacet fractionDigits)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NumFacet)get_store().find_element_user(FRACTIONDIGITS$12, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(fractionDigits);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "fractionDigits" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NumFacet insertNewFractionDigits(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NumFacet)get_store().insert_element_user(FRACTIONDIGITS$12, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "fractionDigits" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NumFacet addNewFractionDigits()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NumFacet)get_store().add_element_user(FRACTIONDIGITS$12);
                return target;
            }
        }
        
        /**
         * Removes the ith "fractionDigits" element
         */
        public void removeFractionDigits(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(FRACTIONDIGITS$12, i);
            }
        }
        
        /**
         * Gets array of all "length" elements
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NumFacet[] getLengthArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(LENGTH$14, targetList);
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet[] result = new org.apache.xmlbeans.impl.xb.xsdschema.NumFacet[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "length" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NumFacet getLengthArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NumFacet)get_store().find_element_user(LENGTH$14, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "length" element
         */
        public int sizeOfLengthArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(LENGTH$14);
            }
        }
        
        /**
         * Sets array of all "length" element
         */
        public void setLengthArray(org.apache.xmlbeans.impl.xb.xsdschema.NumFacet[] lengthArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(lengthArray, LENGTH$14);
            }
        }
        
        /**
         * Sets ith "length" element
         */
        public void setLengthArray(int i, org.apache.xmlbeans.impl.xb.xsdschema.NumFacet length)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NumFacet)get_store().find_element_user(LENGTH$14, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(length);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "length" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NumFacet insertNewLength(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NumFacet)get_store().insert_element_user(LENGTH$14, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "length" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NumFacet addNewLength()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NumFacet)get_store().add_element_user(LENGTH$14);
                return target;
            }
        }
        
        /**
         * Removes the ith "length" element
         */
        public void removeLength(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(LENGTH$14, i);
            }
        }
        
        /**
         * Gets array of all "minLength" elements
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NumFacet[] getMinLengthArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(MINLENGTH$16, targetList);
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet[] result = new org.apache.xmlbeans.impl.xb.xsdschema.NumFacet[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "minLength" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NumFacet getMinLengthArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NumFacet)get_store().find_element_user(MINLENGTH$16, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "minLength" element
         */
        public int sizeOfMinLengthArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(MINLENGTH$16);
            }
        }
        
        /**
         * Sets array of all "minLength" element
         */
        public void setMinLengthArray(org.apache.xmlbeans.impl.xb.xsdschema.NumFacet[] minLengthArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(minLengthArray, MINLENGTH$16);
            }
        }
        
        /**
         * Sets ith "minLength" element
         */
        public void setMinLengthArray(int i, org.apache.xmlbeans.impl.xb.xsdschema.NumFacet minLength)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NumFacet)get_store().find_element_user(MINLENGTH$16, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(minLength);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "minLength" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NumFacet insertNewMinLength(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NumFacet)get_store().insert_element_user(MINLENGTH$16, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "minLength" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NumFacet addNewMinLength()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NumFacet)get_store().add_element_user(MINLENGTH$16);
                return target;
            }
        }
        
        /**
         * Removes the ith "minLength" element
         */
        public void removeMinLength(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(MINLENGTH$16, i);
            }
        }
        
        /**
         * Gets array of all "maxLength" elements
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NumFacet[] getMaxLengthArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(MAXLENGTH$18, targetList);
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet[] result = new org.apache.xmlbeans.impl.xb.xsdschema.NumFacet[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "maxLength" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NumFacet getMaxLengthArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NumFacet)get_store().find_element_user(MAXLENGTH$18, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "maxLength" element
         */
        public int sizeOfMaxLengthArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(MAXLENGTH$18);
            }
        }
        
        /**
         * Sets array of all "maxLength" element
         */
        public void setMaxLengthArray(org.apache.xmlbeans.impl.xb.xsdschema.NumFacet[] maxLengthArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(maxLengthArray, MAXLENGTH$18);
            }
        }
        
        /**
         * Sets ith "maxLength" element
         */
        public void setMaxLengthArray(int i, org.apache.xmlbeans.impl.xb.xsdschema.NumFacet maxLength)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NumFacet)get_store().find_element_user(MAXLENGTH$18, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(maxLength);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "maxLength" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NumFacet insertNewMaxLength(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NumFacet)get_store().insert_element_user(MAXLENGTH$18, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "maxLength" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NumFacet addNewMaxLength()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NumFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NumFacet)get_store().add_element_user(MAXLENGTH$18);
                return target;
            }
        }
        
        /**
         * Removes the ith "maxLength" element
         */
        public void removeMaxLength(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(MAXLENGTH$18, i);
            }
        }
        
        /**
         * Gets array of all "enumeration" elements
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet[] getEnumerationArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(ENUMERATION$20, targetList);
                org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet[] result = new org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "enumeration" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet getEnumerationArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet)get_store().find_element_user(ENUMERATION$20, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "enumeration" element
         */
        public int sizeOfEnumerationArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(ENUMERATION$20);
            }
        }
        
        /**
         * Sets array of all "enumeration" element
         */
        public void setEnumerationArray(org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet[] enumerationArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(enumerationArray, ENUMERATION$20);
            }
        }
        
        /**
         * Sets ith "enumeration" element
         */
        public void setEnumerationArray(int i, org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet enumeration)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet)get_store().find_element_user(ENUMERATION$20, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(enumeration);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "enumeration" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet insertNewEnumeration(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet)get_store().insert_element_user(ENUMERATION$20, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "enumeration" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet addNewEnumeration()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet)get_store().add_element_user(ENUMERATION$20);
                return target;
            }
        }
        
        /**
         * Removes the ith "enumeration" element
         */
        public void removeEnumeration(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(ENUMERATION$20, i);
            }
        }
        
        /**
         * Gets array of all "whiteSpace" elements
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument.WhiteSpace[] getWhiteSpaceArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(WHITESPACE$22, targetList);
                org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument.WhiteSpace[] result = new org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument.WhiteSpace[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "whiteSpace" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument.WhiteSpace getWhiteSpaceArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument.WhiteSpace target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument.WhiteSpace)get_store().find_element_user(WHITESPACE$22, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "whiteSpace" element
         */
        public int sizeOfWhiteSpaceArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(WHITESPACE$22);
            }
        }
        
        /**
         * Sets array of all "whiteSpace" element
         */
        public void setWhiteSpaceArray(org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument.WhiteSpace[] whiteSpaceArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(whiteSpaceArray, WHITESPACE$22);
            }
        }
        
        /**
         * Sets ith "whiteSpace" element
         */
        public void setWhiteSpaceArray(int i, org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument.WhiteSpace whiteSpace)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument.WhiteSpace target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument.WhiteSpace)get_store().find_element_user(WHITESPACE$22, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(whiteSpace);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "whiteSpace" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument.WhiteSpace insertNewWhiteSpace(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument.WhiteSpace target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument.WhiteSpace)get_store().insert_element_user(WHITESPACE$22, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "whiteSpace" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument.WhiteSpace addNewWhiteSpace()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument.WhiteSpace target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument.WhiteSpace)get_store().add_element_user(WHITESPACE$22);
                return target;
            }
        }
        
        /**
         * Removes the ith "whiteSpace" element
         */
        public void removeWhiteSpace(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(WHITESPACE$22, i);
            }
        }
        
        /**
         * Gets array of all "pattern" elements
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern[] getPatternArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(PATTERN$24, targetList);
                org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern[] result = new org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "pattern" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern getPatternArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern)get_store().find_element_user(PATTERN$24, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "pattern" element
         */
        public int sizeOfPatternArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(PATTERN$24);
            }
        }
        
        /**
         * Sets array of all "pattern" element
         */
        public void setPatternArray(org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern[] patternArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(patternArray, PATTERN$24);
            }
        }
        
        /**
         * Sets ith "pattern" element
         */
        public void setPatternArray(int i, org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern pattern)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern)get_store().find_element_user(PATTERN$24, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(pattern);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "pattern" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern insertNewPattern(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern)get_store().insert_element_user(PATTERN$24, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "pattern" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern addNewPattern()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern)get_store().add_element_user(PATTERN$24);
                return target;
            }
        }
        
        /**
         * Removes the ith "pattern" element
         */
        public void removePattern(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(PATTERN$24, i);
            }
        }
        
        /**
         * Gets the "group" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.GroupRef getGroup()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.GroupRef target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.GroupRef)get_store().find_element_user(GROUP$26, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "group" element
         */
        public boolean isSetGroup()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(GROUP$26) != 0;
            }
        }
        
        /**
         * Sets the "group" element
         */
        public void setGroup(org.apache.xmlbeans.impl.xb.xsdschema.GroupRef group)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.GroupRef target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.GroupRef)get_store().find_element_user(GROUP$26, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.impl.xb.xsdschema.GroupRef)get_store().add_element_user(GROUP$26);
                }
                target.set(group);
            }
        }
        
        /**
         * Appends and returns a new empty "group" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.GroupRef addNewGroup()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.GroupRef target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.GroupRef)get_store().add_element_user(GROUP$26);
                return target;
            }
        }
        
        /**
         * Unsets the "group" element
         */
        public void unsetGroup()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(GROUP$26, 0);
            }
        }
        
        /**
         * Gets the "all" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.All getAll()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.All target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.All)get_store().find_element_user(ALL$28, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "all" element
         */
        public boolean isSetAll()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(ALL$28) != 0;
            }
        }
        
        /**
         * Sets the "all" element
         */
        public void setAll(org.apache.xmlbeans.impl.xb.xsdschema.All all)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.All target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.All)get_store().find_element_user(ALL$28, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.impl.xb.xsdschema.All)get_store().add_element_user(ALL$28);
                }
                target.set(all);
            }
        }
        
        /**
         * Appends and returns a new empty "all" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.All addNewAll()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.All target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.All)get_store().add_element_user(ALL$28);
                return target;
            }
        }
        
        /**
         * Unsets the "all" element
         */
        public void unsetAll()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(ALL$28, 0);
            }
        }
        
        /**
         * Gets the "choice" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup getChoice()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup)get_store().find_element_user(CHOICE$30, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "choice" element
         */
        public boolean isSetChoice()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(CHOICE$30) != 0;
            }
        }
        
        /**
         * Sets the "choice" element
         */
        public void setChoice(org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup choice)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup)get_store().find_element_user(CHOICE$30, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup)get_store().add_element_user(CHOICE$30);
                }
                target.set(choice);
            }
        }
        
        /**
         * Appends and returns a new empty "choice" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup addNewChoice()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup)get_store().add_element_user(CHOICE$30);
                return target;
            }
        }
        
        /**
         * Unsets the "choice" element
         */
        public void unsetChoice()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(CHOICE$30, 0);
            }
        }
        
        /**
         * Gets the "sequence" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup getSequence()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup)get_store().find_element_user(SEQUENCE$32, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "sequence" element
         */
        public boolean isSetSequence()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(SEQUENCE$32) != 0;
            }
        }
        
        /**
         * Sets the "sequence" element
         */
        public void setSequence(org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup sequence)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup)get_store().find_element_user(SEQUENCE$32, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup)get_store().add_element_user(SEQUENCE$32);
                }
                target.set(sequence);
            }
        }
        
        /**
         * Appends and returns a new empty "sequence" element
         */
        public org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup addNewSequence()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup target = null;
                target = (org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup)get_store().add_element_user(SEQUENCE$32);
                return target;
            }
        }
        
        /**
         * Unsets the "sequence" element
         */
        public void unsetSequence()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(SEQUENCE$32, 0);
            }
        }
    }
}
