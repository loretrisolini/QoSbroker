/*
 * XML Type:  TruststoreConfigurationType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration;


/**
 * An XML TruststoreConfigurationType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public interface TruststoreConfigurationType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(TruststoreConfigurationType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s6EC9E79B893FA7F21001D53B5B1D318A").resolveHandle("truststoreconfigurationtypee07dtype");
    
    /**
     * Gets the "TruststoreType" element
     */
    java.lang.String getTruststoreType();
    
    /**
     * Gets (as xml) the "TruststoreType" element
     */
    org.apache.xmlbeans.XmlString xgetTruststoreType();
    
    /**
     * Sets the "TruststoreType" element
     */
    void setTruststoreType(java.lang.String truststoreType);
    
    /**
     * Sets (as xml) the "TruststoreType" element
     */
    void xsetTruststoreType(org.apache.xmlbeans.XmlString truststoreType);
    
    /**
     * Gets the "TruststoreFile" element
     */
    java.lang.String getTruststoreFile();
    
    /**
     * Gets (as xml) the "TruststoreFile" element
     */
    org.apache.xmlbeans.XmlString xgetTruststoreFile();
    
    /**
     * Sets the "TruststoreFile" element
     */
    void setTruststoreFile(java.lang.String truststoreFile);
    
    /**
     * Sets (as xml) the "TruststoreFile" element
     */
    void xsetTruststoreFile(org.apache.xmlbeans.XmlString truststoreFile);
    
    /**
     * Gets the "TruststorePassword" element
     */
    java.lang.String getTruststorePassword();
    
    /**
     * Gets (as xml) the "TruststorePassword" element
     */
    org.apache.xmlbeans.XmlString xgetTruststorePassword();
    
    /**
     * Sets the "TruststorePassword" element
     */
    void setTruststorePassword(java.lang.String truststorePassword);
    
    /**
     * Sets (as xml) the "TruststorePassword" element
     */
    void xsetTruststorePassword(org.apache.xmlbeans.XmlString truststorePassword);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType newInstance() {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
