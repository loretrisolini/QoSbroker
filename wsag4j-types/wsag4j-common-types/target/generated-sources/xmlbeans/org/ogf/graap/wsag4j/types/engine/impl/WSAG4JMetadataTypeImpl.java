/*
 * XML Type:  WSAG4JMetadataType
 * Namespace: http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine
 * Java type: org.ogf.graap.wsag4j.types.engine.WSAG4JMetadataType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.engine.impl;
/**
 * An XML WSAG4JMetadataType(@http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine).
 *
 * This is a complex type.
 */
public class WSAG4JMetadataTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.engine.WSAG4JMetadataType
{
    private static final long serialVersionUID = 1L;
    
    public WSAG4JMetadataTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName WSAG4JPORTTYPE$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "WSAG4JPortType");
    
    
    /**
     * Gets the "WSAG4JPortType" element
     */
    public javax.xml.namespace.QName getWSAG4JPortType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(WSAG4JPORTTYPE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getQNameValue();
        }
    }
    
    /**
     * Gets (as xml) the "WSAG4JPortType" element
     */
    public org.apache.xmlbeans.XmlQName xgetWSAG4JPortType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlQName target = null;
            target = (org.apache.xmlbeans.XmlQName)get_store().find_element_user(WSAG4JPORTTYPE$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "WSAG4JPortType" element
     */
    public void setWSAG4JPortType(javax.xml.namespace.QName wsag4JPortType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(WSAG4JPORTTYPE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(WSAG4JPORTTYPE$0);
            }
            target.setQNameValue(wsag4JPortType);
        }
    }
    
    /**
     * Sets (as xml) the "WSAG4JPortType" element
     */
    public void xsetWSAG4JPortType(org.apache.xmlbeans.XmlQName wsag4JPortType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlQName target = null;
            target = (org.apache.xmlbeans.XmlQName)get_store().find_element_user(WSAG4JPORTTYPE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlQName)get_store().add_element_user(WSAG4JPORTTYPE$0);
            }
            target.set(wsag4JPortType);
        }
    }
}
