/*
 * XML Type:  BusinessValueListType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.BusinessValueListType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML BusinessValueListType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class BusinessValueListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.BusinessValueListType
{
    private static final long serialVersionUID = 1L;
    
    public BusinessValueListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName IMPORTANCE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Importance");
    private static final javax.xml.namespace.QName PENALTY$2 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Penalty");
    private static final javax.xml.namespace.QName REWARD$4 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Reward");
    private static final javax.xml.namespace.QName PREFERENCE$6 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "Preference");
    private static final javax.xml.namespace.QName CUSTOMBUSINESSVALUE$8 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "CustomBusinessValue");
    
    
    /**
     * Gets the "Importance" element
     */
    public java.math.BigInteger getImportance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(IMPORTANCE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "Importance" element
     */
    public org.apache.xmlbeans.XmlInteger xgetImportance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(IMPORTANCE$0, 0);
            return target;
        }
    }
    
    /**
     * True if has "Importance" element
     */
    public boolean isSetImportance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(IMPORTANCE$0) != 0;
        }
    }
    
    /**
     * Sets the "Importance" element
     */
    public void setImportance(java.math.BigInteger importance)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(IMPORTANCE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(IMPORTANCE$0);
            }
            target.setBigIntegerValue(importance);
        }
    }
    
    /**
     * Sets (as xml) the "Importance" element
     */
    public void xsetImportance(org.apache.xmlbeans.XmlInteger importance)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(IMPORTANCE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(IMPORTANCE$0);
            }
            target.set(importance);
        }
    }
    
    /**
     * Unsets the "Importance" element
     */
    public void unsetImportance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(IMPORTANCE$0, 0);
        }
    }
    
    /**
     * Gets array of all "Penalty" elements
     */
    public org.ogf.schemas.graap.wsAgreement.CompensationType[] getPenaltyArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(PENALTY$2, targetList);
            org.ogf.schemas.graap.wsAgreement.CompensationType[] result = new org.ogf.schemas.graap.wsAgreement.CompensationType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Penalty" element
     */
    public org.ogf.schemas.graap.wsAgreement.CompensationType getPenaltyArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CompensationType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CompensationType)get_store().find_element_user(PENALTY$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Penalty" element
     */
    public int sizeOfPenaltyArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(PENALTY$2);
        }
    }
    
    /**
     * Sets array of all "Penalty" element
     */
    public void setPenaltyArray(org.ogf.schemas.graap.wsAgreement.CompensationType[] penaltyArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(penaltyArray, PENALTY$2);
        }
    }
    
    /**
     * Sets ith "Penalty" element
     */
    public void setPenaltyArray(int i, org.ogf.schemas.graap.wsAgreement.CompensationType penalty)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CompensationType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CompensationType)get_store().find_element_user(PENALTY$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(penalty);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Penalty" element
     */
    public org.ogf.schemas.graap.wsAgreement.CompensationType insertNewPenalty(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CompensationType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CompensationType)get_store().insert_element_user(PENALTY$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Penalty" element
     */
    public org.ogf.schemas.graap.wsAgreement.CompensationType addNewPenalty()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CompensationType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CompensationType)get_store().add_element_user(PENALTY$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "Penalty" element
     */
    public void removePenalty(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(PENALTY$2, i);
        }
    }
    
    /**
     * Gets array of all "Reward" elements
     */
    public org.ogf.schemas.graap.wsAgreement.CompensationType[] getRewardArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(REWARD$4, targetList);
            org.ogf.schemas.graap.wsAgreement.CompensationType[] result = new org.ogf.schemas.graap.wsAgreement.CompensationType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Reward" element
     */
    public org.ogf.schemas.graap.wsAgreement.CompensationType getRewardArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CompensationType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CompensationType)get_store().find_element_user(REWARD$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Reward" element
     */
    public int sizeOfRewardArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(REWARD$4);
        }
    }
    
    /**
     * Sets array of all "Reward" element
     */
    public void setRewardArray(org.ogf.schemas.graap.wsAgreement.CompensationType[] rewardArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(rewardArray, REWARD$4);
        }
    }
    
    /**
     * Sets ith "Reward" element
     */
    public void setRewardArray(int i, org.ogf.schemas.graap.wsAgreement.CompensationType reward)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CompensationType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CompensationType)get_store().find_element_user(REWARD$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(reward);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Reward" element
     */
    public org.ogf.schemas.graap.wsAgreement.CompensationType insertNewReward(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CompensationType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CompensationType)get_store().insert_element_user(REWARD$4, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Reward" element
     */
    public org.ogf.schemas.graap.wsAgreement.CompensationType addNewReward()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.CompensationType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.CompensationType)get_store().add_element_user(REWARD$4);
            return target;
        }
    }
    
    /**
     * Removes the ith "Reward" element
     */
    public void removeReward(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(REWARD$4, i);
        }
    }
    
    /**
     * Gets the "Preference" element
     */
    public org.ogf.schemas.graap.wsAgreement.PreferenceType getPreference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.PreferenceType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.PreferenceType)get_store().find_element_user(PREFERENCE$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Preference" element
     */
    public boolean isSetPreference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(PREFERENCE$6) != 0;
        }
    }
    
    /**
     * Sets the "Preference" element
     */
    public void setPreference(org.ogf.schemas.graap.wsAgreement.PreferenceType preference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.PreferenceType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.PreferenceType)get_store().find_element_user(PREFERENCE$6, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.PreferenceType)get_store().add_element_user(PREFERENCE$6);
            }
            target.set(preference);
        }
    }
    
    /**
     * Appends and returns a new empty "Preference" element
     */
    public org.ogf.schemas.graap.wsAgreement.PreferenceType addNewPreference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.PreferenceType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.PreferenceType)get_store().add_element_user(PREFERENCE$6);
            return target;
        }
    }
    
    /**
     * Unsets the "Preference" element
     */
    public void unsetPreference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(PREFERENCE$6, 0);
        }
    }
    
    /**
     * Gets array of all "CustomBusinessValue" elements
     */
    public org.apache.xmlbeans.XmlObject[] getCustomBusinessValueArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(CUSTOMBUSINESSVALUE$8, targetList);
            org.apache.xmlbeans.XmlObject[] result = new org.apache.xmlbeans.XmlObject[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "CustomBusinessValue" element
     */
    public org.apache.xmlbeans.XmlObject getCustomBusinessValueArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(CUSTOMBUSINESSVALUE$8, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "CustomBusinessValue" element
     */
    public int sizeOfCustomBusinessValueArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CUSTOMBUSINESSVALUE$8);
        }
    }
    
    /**
     * Sets array of all "CustomBusinessValue" element
     */
    public void setCustomBusinessValueArray(org.apache.xmlbeans.XmlObject[] customBusinessValueArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(customBusinessValueArray, CUSTOMBUSINESSVALUE$8);
        }
    }
    
    /**
     * Sets ith "CustomBusinessValue" element
     */
    public void setCustomBusinessValueArray(int i, org.apache.xmlbeans.XmlObject customBusinessValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(CUSTOMBUSINESSVALUE$8, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(customBusinessValue);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "CustomBusinessValue" element
     */
    public org.apache.xmlbeans.XmlObject insertNewCustomBusinessValue(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().insert_element_user(CUSTOMBUSINESSVALUE$8, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "CustomBusinessValue" element
     */
    public org.apache.xmlbeans.XmlObject addNewCustomBusinessValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(CUSTOMBUSINESSVALUE$8);
            return target;
        }
    }
    
    /**
     * Removes the ith "CustomBusinessValue" element
     */
    public void removeCustomBusinessValue(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CUSTOMBUSINESSVALUE$8, i);
        }
    }
}
