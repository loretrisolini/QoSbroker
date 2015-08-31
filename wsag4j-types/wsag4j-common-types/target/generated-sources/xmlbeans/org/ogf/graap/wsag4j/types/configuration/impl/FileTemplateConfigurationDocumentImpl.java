/*
 * An XML document type.
 * Localname: FileTemplateConfiguration
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.FileTemplateConfigurationDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * A document containing one FileTemplateConfiguration(@http://schemas.scai.fraunhofer.de/config/wsag4j) element.
 *
 * This is a complex type.
 */
public class FileTemplateConfigurationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.FileTemplateConfigurationDocument
{
    private static final long serialVersionUID = 1L;
    
    public FileTemplateConfigurationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FILETEMPLATECONFIGURATION$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "FileTemplateConfiguration");
    
    
    /**
     * Gets the "FileTemplateConfiguration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.FileTemplateConfigurationType getFileTemplateConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.FileTemplateConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.FileTemplateConfigurationType)get_store().find_element_user(FILETEMPLATECONFIGURATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "FileTemplateConfiguration" element
     */
    public void setFileTemplateConfiguration(org.ogf.graap.wsag4j.types.configuration.FileTemplateConfigurationType fileTemplateConfiguration)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.FileTemplateConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.FileTemplateConfigurationType)get_store().find_element_user(FILETEMPLATECONFIGURATION$0, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.FileTemplateConfigurationType)get_store().add_element_user(FILETEMPLATECONFIGURATION$0);
            }
            target.set(fileTemplateConfiguration);
        }
    }
    
    /**
     * Appends and returns a new empty "FileTemplateConfiguration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.FileTemplateConfigurationType addNewFileTemplateConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.FileTemplateConfigurationType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.FileTemplateConfigurationType)get_store().add_element_user(FILETEMPLATECONFIGURATION$0);
            return target;
        }
    }
}
