/*
 * XML Type:  InitiateNegotiationInputType
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationInputType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation.impl;
/**
 * An XML InitiateNegotiationInputType(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation).
 *
 * This is a complex type.
 */
public class InitiateNegotiationInputTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.schemas.graap.wsAgreement.negotiation.InitiateNegotiationInputType
{
    private static final long serialVersionUID = 1L;
    
    public InitiateNegotiationInputTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEGOTIATIONCONTEXT$0 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NegotiationContext");
    private static final javax.xml.namespace.QName INITIATORNEGOTIATIONEPR$2 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "InitiatorNegotiationEPR");
    private static final javax.xml.namespace.QName NONCRITICALEXTENSION$4 = 
        new javax.xml.namespace.QName("http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation", "NoncriticalExtension");
    
    
    /**
     * Gets the "NegotiationContext" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType getNegotiationContext()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType)get_store().find_element_user(NEGOTIATIONCONTEXT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NegotiationContext" element
     */
    public void setNegotiationContext(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType negotiationContext)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType)get_store().find_element_user(NEGOTIATIONCONTEXT$0, 0);
            if (target == null)
            {
                target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType)get_store().add_element_user(NEGOTIATIONCONTEXT$0);
            }
            target.set(negotiationContext);
        }
    }
    
    /**
     * Appends and returns a new empty "NegotiationContext" element
     */
    public org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType addNewNegotiationContext()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType)get_store().add_element_user(NEGOTIATIONCONTEXT$0);
            return target;
        }
    }
    
    /**
     * Gets the "InitiatorNegotiationEPR" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType getInitiatorNegotiationEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(INITIATORNEGOTIATIONEPR$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "InitiatorNegotiationEPR" element
     */
    public boolean isSetInitiatorNegotiationEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(INITIATORNEGOTIATIONEPR$2) != 0;
        }
    }
    
    /**
     * Sets the "InitiatorNegotiationEPR" element
     */
    public void setInitiatorNegotiationEPR(org.w3.x2005.x08.addressing.EndpointReferenceType initiatorNegotiationEPR)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().find_element_user(INITIATORNEGOTIATIONEPR$2, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(INITIATORNEGOTIATIONEPR$2);
            }
            target.set(initiatorNegotiationEPR);
        }
    }
    
    /**
     * Appends and returns a new empty "InitiatorNegotiationEPR" element
     */
    public org.w3.x2005.x08.addressing.EndpointReferenceType addNewInitiatorNegotiationEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.EndpointReferenceType target = null;
            target = (org.w3.x2005.x08.addressing.EndpointReferenceType)get_store().add_element_user(INITIATORNEGOTIATIONEPR$2);
            return target;
        }
    }
    
    /**
     * Unsets the "InitiatorNegotiationEPR" element
     */
    public void unsetInitiatorNegotiationEPR()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(INITIATORNEGOTIATIONEPR$2, 0);
        }
    }
    
    /**
     * Gets array of all "NoncriticalExtension" elements
     */
    public org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType[] getNoncriticalExtensionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(NONCRITICALEXTENSION$4, targetList);
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType[] result = new org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "NoncriticalExtension" element
     */
    public org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType getNoncriticalExtensionArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType)get_store().find_element_user(NONCRITICALEXTENSION$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "NoncriticalExtension" element
     */
    public int sizeOfNoncriticalExtensionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NONCRITICALEXTENSION$4);
        }
    }
    
    /**
     * Sets array of all "NoncriticalExtension" element
     */
    public void setNoncriticalExtensionArray(org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType[] noncriticalExtensionArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(noncriticalExtensionArray, NONCRITICALEXTENSION$4);
        }
    }
    
    /**
     * Sets ith "NoncriticalExtension" element
     */
    public void setNoncriticalExtensionArray(int i, org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType noncriticalExtension)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType)get_store().find_element_user(NONCRITICALEXTENSION$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(noncriticalExtension);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "NoncriticalExtension" element
     */
    public org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType insertNewNoncriticalExtension(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType)get_store().insert_element_user(NONCRITICALEXTENSION$4, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "NoncriticalExtension" element
     */
    public org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType addNewNoncriticalExtension()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType target = null;
            target = (org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType)get_store().add_element_user(NONCRITICALEXTENSION$4);
            return target;
        }
    }
    
    /**
     * Removes the ith "NoncriticalExtension" element
     */
    public void removeNoncriticalExtension(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NONCRITICALEXTENSION$4, i);
        }
    }
}
