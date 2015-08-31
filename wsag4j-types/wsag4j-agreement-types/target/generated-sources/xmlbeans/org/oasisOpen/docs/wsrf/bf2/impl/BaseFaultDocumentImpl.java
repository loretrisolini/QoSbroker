/*
 * An XML document type.
 * Localname: BaseFault
 * Namespace: http://docs.oasis-open.org/wsrf/bf-2
 * Java type: org.oasisOpen.docs.wsrf.bf2.BaseFaultDocument
 *
 * Automatically generated - do not modify.
 */
package org.oasisOpen.docs.wsrf.bf2.impl;
/**
 * A document containing one BaseFault(@http://docs.oasis-open.org/wsrf/bf-2) element.
 *
 * This is a complex type.
 */
public class BaseFaultDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.oasisOpen.docs.wsrf.bf2.BaseFaultDocument
{
    private static final long serialVersionUID = 1L;
    
    public BaseFaultDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName BASEFAULT$0 = 
        new javax.xml.namespace.QName("http://docs.oasis-open.org/wsrf/bf-2", "BaseFault");
    
    
    /**
     * Gets the "BaseFault" element
     */
    public org.oasisOpen.docs.wsrf.bf2.BaseFaultType getBaseFault()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.oasisOpen.docs.wsrf.bf2.BaseFaultType target = null;
            target = (org.oasisOpen.docs.wsrf.bf2.BaseFaultType)get_store().find_element_user(BASEFAULT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "BaseFault" element
     */
    public void setBaseFault(org.oasisOpen.docs.wsrf.bf2.BaseFaultType baseFault)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.oasisOpen.docs.wsrf.bf2.BaseFaultType target = null;
            target = (org.oasisOpen.docs.wsrf.bf2.BaseFaultType)get_store().find_element_user(BASEFAULT$0, 0);
            if (target == null)
            {
                target = (org.oasisOpen.docs.wsrf.bf2.BaseFaultType)get_store().add_element_user(BASEFAULT$0);
            }
            target.set(baseFault);
        }
    }
    
    /**
     * Appends and returns a new empty "BaseFault" element
     */
    public org.oasisOpen.docs.wsrf.bf2.BaseFaultType addNewBaseFault()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.oasisOpen.docs.wsrf.bf2.BaseFaultType target = null;
            target = (org.oasisOpen.docs.wsrf.bf2.BaseFaultType)get_store().add_element_user(BASEFAULT$0);
            return target;
        }
    }
}
