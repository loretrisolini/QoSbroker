/*
 * An XML document type.
 * Localname: ProblemAction
 * Namespace: http://www.w3.org/2005/08/addressing
 * Java type: org.w3.x2005.x08.addressing.ProblemActionDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2005.x08.addressing.impl;
/**
 * A document containing one ProblemAction(@http://www.w3.org/2005/08/addressing) element.
 *
 * This is a complex type.
 */
public class ProblemActionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2005.x08.addressing.ProblemActionDocument
{
    private static final long serialVersionUID = 1L;
    
    public ProblemActionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PROBLEMACTION$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2005/08/addressing", "ProblemAction");
    
    
    /**
     * Gets the "ProblemAction" element
     */
    public org.w3.x2005.x08.addressing.ProblemActionType getProblemAction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.ProblemActionType target = null;
            target = (org.w3.x2005.x08.addressing.ProblemActionType)get_store().find_element_user(PROBLEMACTION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ProblemAction" element
     */
    public void setProblemAction(org.w3.x2005.x08.addressing.ProblemActionType problemAction)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.ProblemActionType target = null;
            target = (org.w3.x2005.x08.addressing.ProblemActionType)get_store().find_element_user(PROBLEMACTION$0, 0);
            if (target == null)
            {
                target = (org.w3.x2005.x08.addressing.ProblemActionType)get_store().add_element_user(PROBLEMACTION$0);
            }
            target.set(problemAction);
        }
    }
    
    /**
     * Appends and returns a new empty "ProblemAction" element
     */
    public org.w3.x2005.x08.addressing.ProblemActionType addNewProblemAction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2005.x08.addressing.ProblemActionType target = null;
            target = (org.w3.x2005.x08.addressing.ProblemActionType)get_store().add_element_user(PROBLEMACTION$0);
            return target;
        }
    }
}
