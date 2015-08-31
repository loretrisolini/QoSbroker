/*
 * An XML document type.
 * Localname: ProblemHeader
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.ProblemHeaderDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * A document containing one ProblemHeader(@http://www.w3.org/2005/08/addressing) element.
 *
 * This is a complex type.
 */
public class ProblemHeaderDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.ProblemHeaderDocument
{
    private static final long serialVersionUID = 1L;
    
    public ProblemHeaderDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PROBLEMHEADER$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "ProblemHeader");
    
    
    /**
     * Gets the "ProblemHeader" element
     */
    public org.w3.x2005.x08.addressing.AttributedAnyType getProblemHeader()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedAnyType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedAnyType)get_store().find_element_user(PROBLEMHEADER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ProblemHeader" element
     */
    public void setProblemHeader(org.w3.x2005.x08.addressing.AttributedAnyType problemHeader)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedAnyType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedAnyType)get_store().find_element_user(PROBLEMHEADER$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.AttributedAnyType)get_store().add_element_user(PROBLEMHEADER$0);
            }
            target.set(problemHeader);
        }
    }
    
    /**
     * Appends and returns a new empty "ProblemHeader" element
     */
    public org.w3.x2005.x08.addressing.AttributedAnyType addNewProblemHeader()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedAnyType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedAnyType)get_store().add_element_user(PROBLEMHEADER$0);
            return target;
        }
    }
}
