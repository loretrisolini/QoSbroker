/*
 * An XML document type.
 * Localname: ServerIdentity
 * Namespace: http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine
 * Java type: org.ogf.graap.wsag4j.types.engine.ServerIdentityDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.engine.impl;
/**
 * A document containing one ServerIdentity(@http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine) element.
 *
 * This is a complex type.
 */
public class ServerIdentityDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.engine.ServerIdentityDocument
{
    private static final long serialVersionUID = 1L;
    
    public ServerIdentityDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVERIDENTITY$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "ServerIdentity");
    
    
    /**
     * Gets the "ServerIdentity" element
     */
    public java.lang.String getServerIdentity()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVERIDENTITY$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "ServerIdentity" element
     */
    public org.apache.xmlbeans.XmlString xgetServerIdentity()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SERVERIDENTITY$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "ServerIdentity" element
     */
    public void setServerIdentity(java.lang.String serverIdentity)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVERIDENTITY$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SERVERIDENTITY$0);
            }
            target.setStringValue(serverIdentity);
        }
    }
    
    /**
     * Sets (as xml) the "ServerIdentity" element
     */
    public void xsetServerIdentity(org.apache.xmlbeans.XmlString serverIdentity)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SERVERIDENTITY$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(SERVERIDENTITY$0);
            }
            target.set(serverIdentity);
        }
    }
}
