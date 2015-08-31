/*
 * An XML document type.
 * Localname: ConstraintAnnotation
 * Namespace: http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine
 * Java type: org.ogf.graap.wsag4j.types.engine.ConstraintAnnotationDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.engine.impl;
/**
 * A document containing one ConstraintAnnotation(@http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine) element.
 *
 * This is a complex type.
 */
public class ConstraintAnnotationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.engine.ConstraintAnnotationDocument
{
    private static final long serialVersionUID = 1L;
    
    public ConstraintAnnotationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CONSTRAINTANNOTATION$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "ConstraintAnnotation");
    
    
    /**
     * Gets the "ConstraintAnnotation" element
     */
    public org.ogf.graap.wsag4j.types.engine.ConstraintAnnotationType getConstraintAnnotation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.ConstraintAnnotationType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.ConstraintAnnotationType)get_store().find_element_user(CONSTRAINTANNOTATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ConstraintAnnotation" element
     */
    public void setConstraintAnnotation(org.ogf.graap.wsag4j.types.engine.ConstraintAnnotationType constraintAnnotation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.ConstraintAnnotationType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.ConstraintAnnotationType)get_store().find_element_user(CONSTRAINTANNOTATION$0, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.engine.ConstraintAnnotationType)get_store().add_element_user(CONSTRAINTANNOTATION$0);
            }
            target.set(constraintAnnotation);
        }
    }
    
    /**
     * Appends and returns a new empty "ConstraintAnnotation" element
     */
    public org.ogf.graap.wsag4j.types.engine.ConstraintAnnotationType addNewConstraintAnnotation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.ConstraintAnnotationType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.ConstraintAnnotationType)get_store().add_element_user(CONSTRAINTANNOTATION$0);
            return target;
        }
    }
}
