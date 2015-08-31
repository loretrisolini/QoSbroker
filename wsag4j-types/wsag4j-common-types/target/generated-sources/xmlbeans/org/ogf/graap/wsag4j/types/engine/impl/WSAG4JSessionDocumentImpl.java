/*
 * An XML document type.
 * Localname: WSAG4JSession
 * Namespace: http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine
 * Java type: org.ogf.graap.wsag4j.types.engine.WSAG4JSessionDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.engine.impl;
/**
 * A document containing one WSAG4JSession(@http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine) element.
 *
 * This is a complex type.
 */
public class WSAG4JSessionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.engine.WSAG4JSessionDocument
{
    private static final long serialVersionUID = 1L;
    
    public WSAG4JSessionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName WSAG4JSESSION$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "WSAG4JSession");
    
    
    /**
     * Gets the "WSAG4JSession" element
     */
    public org.ogf.graap.wsag4j.types.engine.WSAG4JSessionType getWSAG4JSession()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.WSAG4JSessionType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.WSAG4JSessionType)get_store().find_element_user(WSAG4JSESSION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "WSAG4JSession" element
     */
    public void setWSAG4JSession(org.ogf.graap.wsag4j.types.engine.WSAG4JSessionType wsag4JSession)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.WSAG4JSessionType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.WSAG4JSessionType)get_store().find_element_user(WSAG4JSESSION$0, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.engine.WSAG4JSessionType)get_store().add_element_user(WSAG4JSESSION$0);
            }
            target.set(wsag4JSession);
        }
    }
    
    /**
     * Appends and returns a new empty "WSAG4JSession" element
     */
    public org.ogf.graap.wsag4j.types.engine.WSAG4JSessionType addNewWSAG4JSession()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.WSAG4JSessionType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.WSAG4JSessionType)get_store().add_element_user(WSAG4JSESSION$0);
            return target;
        }
    }
}
