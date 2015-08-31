/*
 * XML Type:  SLAMonitoringNotificationEventType
 * Namespace: http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine
 * Java type: org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.engine;


/**
 * An XML SLAMonitoringNotificationEventType(@http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine).
 *
 * This is a complex type.
 */
public interface SLAMonitoringNotificationEventType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(SLAMonitoringNotificationEventType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s6EC9E79B893FA7F21001D53B5B1D318A").resolveHandle("slamonitoringnotificationeventtypedd8ftype");
    
    /**
     * Gets array of all "GuaranteeEvaluationResult" elements
     */
    org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType[] getGuaranteeEvaluationResultArray();
    
    /**
     * Gets ith "GuaranteeEvaluationResult" element
     */
    org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType getGuaranteeEvaluationResultArray(int i);
    
    /**
     * Returns number of "GuaranteeEvaluationResult" element
     */
    int sizeOfGuaranteeEvaluationResultArray();
    
    /**
     * Sets array of all "GuaranteeEvaluationResult" element
     */
    void setGuaranteeEvaluationResultArray(org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType[] guaranteeEvaluationResultArray);
    
    /**
     * Sets ith "GuaranteeEvaluationResult" element
     */
    void setGuaranteeEvaluationResultArray(int i, org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType guaranteeEvaluationResult);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "GuaranteeEvaluationResult" element
     */
    org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType insertNewGuaranteeEvaluationResult(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "GuaranteeEvaluationResult" element
     */
    org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType addNewGuaranteeEvaluationResult();
    
    /**
     * Removes the ith "GuaranteeEvaluationResult" element
     */
    void removeGuaranteeEvaluationResult(int i);
    
    /**
     * Gets the "agreementId" attribute
     */
    java.lang.String getAgreementId();
    
    /**
     * Gets (as xml) the "agreementId" attribute
     */
    org.apache.xmlbeans.XmlString xgetAgreementId();
    
    /**
     * True if has "agreementId" attribute
     */
    boolean isSetAgreementId();
    
    /**
     * Sets the "agreementId" attribute
     */
    void setAgreementId(java.lang.String agreementId);
    
    /**
     * Sets (as xml) the "agreementId" attribute
     */
    void xsetAgreementId(org.apache.xmlbeans.XmlString agreementId);
    
    /**
     * Unsets the "agreementId" attribute
     */
    void unsetAgreementId();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType newInstance() {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.graap.wsag4j.types.engine.SLAMonitoringNotificationEventType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
