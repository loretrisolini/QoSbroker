/*
 * An XML document type.
 * Localname: ValidatorConfiguration
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.ValidatorConfigurationDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration.impl;
/**
 * A document containing one ValidatorConfiguration(@http://schemas.scai.fraunhofer.de/config/wsag4j) element.
 *
 * This is a complex type.
 */
public class ValidatorConfigurationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.configuration.ValidatorConfigurationDocument
{
    private static final long serialVersionUID = 1L;
    
    public ValidatorConfigurationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName VALIDATORCONFIGURATION$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/config/wsag4j", "ValidatorConfiguration");
    
    
    /**
     * Gets the "ValidatorConfiguration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ValidatorType getValidatorConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ValidatorType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ValidatorType)get_store().find_element_user(VALIDATORCONFIGURATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ValidatorConfiguration" element
     */
    public void setValidatorConfiguration(org.ogf.graap.wsag4j.types.configuration.ValidatorType validatorConfiguration)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ValidatorType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ValidatorType)get_store().find_element_user(VALIDATORCONFIGURATION$0, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.configuration.ValidatorType)get_store().add_element_user(VALIDATORCONFIGURATION$0);
            }
            target.set(validatorConfiguration);
        }
    }
    
    /**
     * Appends and returns a new empty "ValidatorConfiguration" element
     */
    public org.ogf.graap.wsag4j.types.configuration.ValidatorType addNewValidatorConfiguration()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.configuration.ValidatorType target = null;
            target = (org.ogf.graap.wsag4j.types.configuration.ValidatorType)get_store().add_element_user(VALIDATORCONFIGURATION$0);
            return target;
        }
    }
}
