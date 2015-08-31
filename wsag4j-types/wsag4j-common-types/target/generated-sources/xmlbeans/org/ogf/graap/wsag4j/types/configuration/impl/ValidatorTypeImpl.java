/*
 * XML Type:  ValidatorType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.ValidatorType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * An XML ValidatorType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public class ValidatorTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.ValidatorType
{
    private static final long serialVersionUID = 1L;
    
    public ValidatorTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SCHEMAIMPORTS$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "SchemaImports");
    
    
    /**
     * Gets the "SchemaImports" element
     */
    public org.ogf.graap.wsag4j.types.configuration.SchemaImportType getSchemaImports()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.SchemaImportType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.SchemaImportType)get_store().find_element_user(SCHEMAIMPORTS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SchemaImports" element
     */
    public void setSchemaImports(org.ogf.graap.wsag4j.types.configuration.SchemaImportType schemaImports)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.SchemaImportType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.SchemaImportType)get_store().find_element_user(SCHEMAIMPORTS$0, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.SchemaImportType)get_store().add_element_user(SCHEMAIMPORTS$0);
            }
            target.set(schemaImports);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemaImports" element
     */
    public org.ogf.graap.wsag4j.types.configuration.SchemaImportType addNewSchemaImports()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.SchemaImportType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.SchemaImportType)get_store().add_element_user(SCHEMAIMPORTS$0);
            return target;
        }
    }
}
