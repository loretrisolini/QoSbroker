/*
 * An XML document type.
 * Localname: SLAMonitoringNotificationEvent
 * Namespace: http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine
 * Java type: org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.engine.impl;
/**
 * A document containing one SLAMonitoringNotificationEvent(@http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine) element.
 *
 * This is a complex type.
 */
public class SLAMonitoringNotificationEventDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventDocument
{
    private static final long serialVersionUID = 1L;
    
    public SLAMonitoringNotificationEventDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SLAMONITORINGNOTIFICATIONEVENT$0 = 
        new javax.xml.namespace.QName("http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine", "SLAMonitoringNotificationEvent");
    
    
    /**
     * Gets the "SLAMonitoringNotificationEvent" element
     */
    public org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType getSLAMonitoringNotificationEvent()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType)get_store().find_element_user(SLAMONITORINGNOTIFICATIONEVENT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SLAMonitoringNotificationEvent" element
     */
    public void setSLAMonitoringNotificationEvent(org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType slaMonitoringNotificationEvent)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType)get_store().find_element_user(SLAMONITORINGNOTIFICATIONEVENT$0, 0);
            if (target == null)
            {
                target = (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType)get_store().add_element_user(SLAMONITORINGNOTIFICATIONEVENT$0);
            }
            target.set(slaMonitoringNotificationEvent);
        }
    }
    
    /**
     * Appends and returns a new empty "SLAMonitoringNotificationEvent" element
     */
    public org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType addNewSLAMonitoringNotificationEvent()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType target = null;
            target = (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType)get_store().add_element_user(SLAMONITORINGNOTIFICATIONEVENT$0);
            return target;
        }
    }
}
