/*
 * XML Type:  ServiceLevelObjectiveType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * An XML ServiceLevelObjectiveType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public class ServiceLevelObjectiveTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType
{
    private static final long serialVersionUID = 1L;
    
    public ServiceLevelObjectiveTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName KPITARGET$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "KPITarget");
    private static final javax.xml.namespace.QName CUSTOMSERVICELEVEL$2 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "CustomServiceLevel");
    
    
    /**
     * Gets the "KPITarget" element
     */
    public org.ogf.schemas.graap.wsAgreement.KPITargetType getKPITarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.KPITargetType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.KPITargetType)get_store().find_element_user(KPITARGET$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "KPITarget" element
     */
    public boolean isSetKPITarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(KPITARGET$0) != 0;
        }
    }
    
    /**
     * Sets the "KPITarget" element
     */
    public void setKPITarget(org.ogf.schemas.graap.wsAgreement.KPITargetType kpiTarget)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.KPITargetType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.KPITargetType)get_store().find_element_user(KPITARGET$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.KPITargetType)get_store().add_element_user(KPITARGET$0);
            }
            target.set(kpiTarget);
        }
    }
    
    /**
     * Appends and returns a new empty "KPITarget" element
     */
    public org.ogf.schemas.graap.wsAgreement.KPITargetType addNewKPITarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.KPITargetType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.KPITargetType)get_store().add_element_user(KPITARGET$0);
            return target;
        }
    }
    
    /**
     * Unsets the "KPITarget" element
     */
    public void unsetKPITarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(KPITARGET$0, 0);
        }
    }
    
    /**
     * Gets the "CustomServiceLevel" element
     */
    public org.apache.xmlbeans.XmlObject getCustomServiceLevel()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(CUSTOMSERVICELEVEL$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "CustomServiceLevel" element
     */
    public boolean isSetCustomServiceLevel()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CUSTOMSERVICELEVEL$2) != 0;
        }
    }
    
    /**
     * Sets the "CustomServiceLevel" element
     */
    public void setCustomServiceLevel(org.apache.xmlbeans.XmlObject customServiceLevel)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(CUSTOMSERVICELEVEL$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(CUSTOMSERVICELEVEL$2);
            }
            target.set(customServiceLevel);
        }
    }
    
    /**
     * Appends and returns a new empty "CustomServiceLevel" element
     */
    public org.apache.xmlbeans.XmlObject addNewCustomServiceLevel()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(CUSTOMSERVICELEVEL$2);
            return target;
        }
    }
    
    /**
     * Unsets the "CustomServiceLevel" element
     */
    public void unsetCustomServiceLevel()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CUSTOMSERVICELEVEL$2, 0);
        }
    }
}
