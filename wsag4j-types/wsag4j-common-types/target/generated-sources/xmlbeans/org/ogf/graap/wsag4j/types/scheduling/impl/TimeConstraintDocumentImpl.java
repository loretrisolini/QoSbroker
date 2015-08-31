/*
 * An XML document type.
 * Localname: TimeConstraint
 * Namespace: http://schemas.wsag4j.org/2009/07/wsag4j-scheduling-extensions
 * Java type: org.ogf.graap.wsag4j.types.scheduling.TimeConstraintDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.scheduling.impl;
/**
 * A document containing one TimeConstraint(@http://schemas.wsag4j.org/2009/07/wsag4j-scheduling-extensions) element.
 *
 * This is a complex type.
 */
public class TimeConstraintDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.scheduling.TimeConstraintDocument
{
    private static final long serialVersionUID = 1L;
    
    public TimeConstraintDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TIMECONSTRAINT$0 = 
        new javax.xml.namespace.QName("http://schemas.wsag4j.org/2009/07/wsag4j-scheduling-extensions", "TimeConstraint");
    
    
    /**
     * Gets the "TimeConstraint" element
     */
    public org.ogf.graap.wsag4j.types.scheduling.TimeConstraintType getTimeConstraint()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.scheduling.TimeConstraintType target = null;
            target = (org.ogf.graap.wsag4j.types.scheduling.TimeConstraintType)get_store().find_element_user(TIMECONSTRAINT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TimeConstraint" element
     */
    public void setTimeConstraint(org.ogf.graap.wsag4j.types.scheduling.TimeConstraintType timeConstraint)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.scheduling.TimeConstraintType target = null;
            target = (org.ogf.graap.wsag4j.types.scheduling.TimeConstraintType)get_store().find_element_user(TIMECONSTRAINT$0, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.scheduling.TimeConstraintType)get_store().add_element_user(TIMECONSTRAINT$0);
            }
            target.set(timeConstraint);
        }
    }
    
    /**
     * Appends and returns a new empty "TimeConstraint" element
     */
    public org.ogf.graap.wsag4j.types.scheduling.TimeConstraintType addNewTimeConstraint()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.scheduling.TimeConstraintType target = null;
            target = (org.ogf.graap.wsag4j.types.scheduling.TimeConstraintType)get_store().add_element_user(TIMECONSTRAINT$0);
            return target;
        }
    }
}
