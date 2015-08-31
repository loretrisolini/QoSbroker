/*
 * An XML document type.
 * Localname: PersistenceAgreementContext
 * Namespace: http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine
 * Java type: org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.engine.impl;
/**
 * A document containing one PersistenceAgreementContext(@http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine) element.
 *
 * This is a complex type.
 */
public class PersistenceAgreementContextDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextDocument
{
    private static final long serialVersionUID = 1L;
    
    public PersistenceAgreementContextDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PERSISTENCEAGREEMENTCONTEXT$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "PersistenceAgreementContext");
    
    
    /**
     * Gets the "PersistenceAgreementContext" element
     */
    public org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType getPersistenceAgreementContext()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType)get_store().find_element_user(PERSISTENCEAGREEMENTCONTEXT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "PersistenceAgreementContext" element
     */
    public void setPersistenceAgreementContext(org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType persistenceAgreementContext)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType)get_store().find_element_user(PERSISTENCEAGREEMENTCONTEXT$0, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType)get_store().add_element_user(PERSISTENCEAGREEMENTCONTEXT$0);
            }
            target.set(persistenceAgreementContext);
        }
    }
    
    /**
     * Appends and returns a new empty "PersistenceAgreementContext" element
     */
    public org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType addNewPersistenceAgreementContext()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.PersistenceAgreementContextType)get_store().add_element_user(PERSISTENCEAGREEMENTCONTEXT$0);
            return target;
        }
    }
}
