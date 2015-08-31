/*
 * An XML document type.
 * Localname: ProblemIRI
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.ProblemIRIDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * A document containing one ProblemIRI(@http://www.w3.org/2005/08/addressing) element.
 *
 * This is a complex type.
 */
public class ProblemIRIDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.ProblemIRIDocument
{
    private static final long serialVersionUID = 1L;
    
    public ProblemIRIDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PROBLEMIRI$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "ProblemIRI");
    
    
    /**
     * Gets the "ProblemIRI" element
     */
    public org.w3.x2005.x08.addressing.AttributedURIType getProblemIRI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedURIType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().find_element_user(PROBLEMIRI$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ProblemIRI" element
     */
    public void setProblemIRI(org.w3.x2005.x08.addressing.AttributedURIType problemIRI)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedURIType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().find_element_user(PROBLEMIRI$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().add_element_user(PROBLEMIRI$0);
            }
            target.set(problemIRI);
        }
    }
    
    /**
     * Appends and returns a new empty "ProblemIRI" element
     */
    public org.w3.x2005.x08.addressing.AttributedURIType addNewProblemIRI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.AttributedURIType target = null;
            target = (org.w3.x2005.x08.addressing.AttributedURIType)get_store().add_element_user(PROBLEMIRI$0);
            return target;
        }
    }
}
