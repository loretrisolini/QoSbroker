/*
 * XML Type:  AgreementContextType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AgreementContextType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement;


/**
 * An XML AgreementContextType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public interface AgreementContextType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AgreementContextType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E82E5FFD6D93F7E928DD54BBB6CABD9").resolveHandle("agreementcontexttype304etype");
    
    /**
     * Gets the "AgreementInitiator" element
     */
    org.apache.xmlbeans.XmlObject getAgreementInitiator();
    
    /**
     * True if has "AgreementInitiator" element
     */
    boolean isSetAgreementInitiator();
    
    /**
     * Sets the "AgreementInitiator" element
     */
    void setAgreementInitiator(org.apache.xmlbeans.XmlObject agreementInitiator);
    
    /**
     * Appends and returns a new empty "AgreementInitiator" element
     */
    org.apache.xmlbeans.XmlObject addNewAgreementInitiator();
    
    /**
     * Unsets the "AgreementInitiator" element
     */
    void unsetAgreementInitiator();
    
    /**
     * Gets the "AgreementResponder" element
     */
    org.apache.xmlbeans.XmlObject getAgreementResponder();
    
    /**
     * True if has "AgreementResponder" element
     */
    boolean isSetAgreementResponder();
    
    /**
     * Sets the "AgreementResponder" element
     */
    void setAgreementResponder(org.apache.xmlbeans.XmlObject agreementResponder);
    
    /**
     * Appends and returns a new empty "AgreementResponder" element
     */
    org.apache.xmlbeans.XmlObject addNewAgreementResponder();
    
    /**
     * Unsets the "AgreementResponder" element
     */
    void unsetAgreementResponder();
    
    /**
     * Gets the "ServiceProvider" element
     */
    org.ogf.schemas.graap.wsAgreement.AgreementRoleType.Enum getServiceProvider();
    
    /**
     * Gets (as xml) the "ServiceProvider" element
     */
    org.ogf.schemas.graap.wsAgreement.AgreementRoleType xgetServiceProvider();
    
    /**
     * Sets the "ServiceProvider" element
     */
    void setServiceProvider(org.ogf.schemas.graap.wsAgreement.AgreementRoleType.Enum serviceProvider);
    
    /**
     * Sets (as xml) the "ServiceProvider" element
     */
    void xsetServiceProvider(org.ogf.schemas.graap.wsAgreement.AgreementRoleType serviceProvider);
    
    /**
     * Gets the "ExpirationTime" element
     */
    java.util.Calendar getExpirationTime();
    
    /**
     * Gets (as xml) the "ExpirationTime" element
     */
    org.apache.xmlbeans.XmlDateTime xgetExpirationTime();
    
    /**
     * True if has "ExpirationTime" element
     */
    boolean isSetExpirationTime();
    
    /**
     * Sets the "ExpirationTime" element
     */
    void setExpirationTime(java.util.Calendar expirationTime);
    
    /**
     * Sets (as xml) the "ExpirationTime" element
     */
    void xsetExpirationTime(org.apache.xmlbeans.XmlDateTime expirationTime);
    
    /**
     * Unsets the "ExpirationTime" element
     */
    void unsetExpirationTime();
    
    /**
     * Gets the "TemplateId" element
     */
    java.lang.String getTemplateId();
    
    /**
     * Gets (as xml) the "TemplateId" element
     */
    org.apache.xmlbeans.XmlString xgetTemplateId();
    
    /**
     * True if has "TemplateId" element
     */
    boolean isSetTemplateId();
    
    /**
     * Sets the "TemplateId" element
     */
    void setTemplateId(java.lang.String templateId);
    
    /**
     * Sets (as xml) the "TemplateId" element
     */
    void xsetTemplateId(org.apache.xmlbeans.XmlString templateId);
    
    /**
     * Unsets the "TemplateId" element
     */
    void unsetTemplateId();
    
    /**
     * Gets the "TemplateName" element
     */
    java.lang.String getTemplateName();
    
    /**
     * Gets (as xml) the "TemplateName" element
     */
    org.apache.xmlbeans.XmlString xgetTemplateName();
    
    /**
     * True if has "TemplateName" element
     */
    boolean isSetTemplateName();
    
    /**
     * Sets the "TemplateName" element
     */
    void setTemplateName(java.lang.String templateName);
    
    /**
     * Sets (as xml) the "TemplateName" element
     */
    void xsetTemplateName(org.apache.xmlbeans.XmlString templateName);
    
    /**
     * Unsets the "TemplateName" element
     */
    void unsetTemplateName();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType newInstance() {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.AgreementContextType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
