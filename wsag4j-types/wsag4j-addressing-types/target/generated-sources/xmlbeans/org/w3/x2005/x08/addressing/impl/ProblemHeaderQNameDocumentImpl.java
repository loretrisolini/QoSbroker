/*
 * An XML document type.
 * Localname: ProblemHeaderQName
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.ProblemHeaderQNameDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * A document containing one ProblemHeaderQName(@http://www.w3.org/2005/08/addressing) element.
 *
 * This is a complex type.
 */
public class ProblemHeaderQNameDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.ProblemHeaderQNameDocument
{
    private static final long serialVersionUID = 1L;
    
    public ProblemHeaderQNameDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PROBLEMHEADERQNAME$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "ProblemHeaderQName");
    
    
    /**
     * Gets the "ProblemHeaderQName" element
     */
    public org.w3.x2005.x08.addressing.AttributedQNameType getProblemHeaderQName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedQNameType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedQNameType)get_store().find_element_user(PROBLEMHEADERQNAME$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ProblemHeaderQName" element
     */
    public void setProblemHeaderQName(org.w3.x2005.x08.addressing.AttributedQNameType problemHeaderQName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedQNameType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedQNameType)get_store().find_element_user(PROBLEMHEADERQNAME$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.AttributedQNameType)get_store().add_element_user(PROBLEMHEADERQNAME$0);
            }
            target.set(problemHeaderQName);
        }
    }
    
    /**
     * Appends and returns a new empty "ProblemHeaderQName" element
     */
    public org.w3.x2005.x08.addressing.AttributedQNameType addNewProblemHeaderQName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedQNameType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedQNameType)get_store().add_element_user(PROBLEMHEADERQNAME$0);
            return target;
        }
    }
}
