/*
 * XML Type:  ConstraintAnnotationType
 * Namespace: http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine
 * Java type: org.ogf.graap.wsag4j.types.engine.ConstraintAnnotationType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.engine.impl;
/**
 * An XML ConstraintAnnotationType(@http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine).
 *
 * This is a complex type.
 */
public class ConstraintAnnotationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.engine.ConstraintAnnotationType
{
    private static final long serialVersionUID = 1L;
    
    public ConstraintAnnotationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName MULTIPLICITY$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "Multiplicity");
    
    
    /**
     * Gets the "Multiplicity" element
     */
    public org.ogf.graap.wsag4j.types.engine.ItemCardinalityType.Enum getMultiplicity()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MULTIPLICITY$0, 0);
            if (target == null)
            {
                return null;
            }
            return (org.ogf.graap.wsag4j.types.engine.ItemCardinalityType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "Multiplicity" element
     */
    public org.ogf.graap.wsag4j.types.engine.ItemCardinalityType xgetMultiplicity()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.ItemCardinalityType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.ItemCardinalityType)get_store().find_element_user(MULTIPLICITY$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Multiplicity" element
     */
    public void setMultiplicity(org.ogf.graap.wsag4j.types.engine.ItemCardinalityType.Enum multiplicity)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MULTIPLICITY$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(MULTIPLICITY$0);
            }
            target.setEnumValue(multiplicity);
        }
    }
    
    /**
     * Sets (as xml) the "Multiplicity" element
     */
    public void xsetMultiplicity(org.ogf.graap.wsag4j.types.engine.ItemCardinalityType multiplicity)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.ItemCardinalityType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.ItemCardinalityType)get_store().find_element_user(MULTIPLICITY$0, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.engine.ItemCardinalityType)get_store().add_element_user(MULTIPLICITY$0);
            }
            target.set(multiplicity);
        }
    }
}
