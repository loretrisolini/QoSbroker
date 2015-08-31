/*
 * An XML document type.
 * Localname: ServiceLevelObjective
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.impl;
/**
 * A document containing one ServiceLevelObjective(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public class ServiceLevelObjectiveDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveDocument
{
    private static final long serialVersionUID = 1L;
    
    public ServiceLevelObjectiveDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICELEVELOBJECTIVE$0 = 
        new javax.xml.namespace.QName("http://schemas.ggf.org/graap/2007/03/ws-agreement", "ServiceLevelObjective");
    
    
    /**
     * Gets the "ServiceLevelObjective" element
     */
    public org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType getServiceLevelObjective()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType)get_store().find_element_user(SERVICELEVELOBJECTIVE$0, 0);
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
            target = (org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType)get_store().find_element_user(SERVICELEVELOBJECTIVE$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType)get_store().add_element_user(SERVICELEVELOBJECTIVE$0);
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
            target = (org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType)get_store().add_element_user(SERVICELEVELOBJECTIVE$0);
            return target;
        }
    }
}
