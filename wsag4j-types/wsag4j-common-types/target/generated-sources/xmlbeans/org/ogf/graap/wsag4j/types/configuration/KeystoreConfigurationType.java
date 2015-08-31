/*
 * XML Type:  KeystoreConfigurationType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration;


/**
 * An XML KeystoreConfigurationType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public interface KeystoreConfigurationType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(KeystoreConfigurationType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s6EC9E79B893FA7F21001D53B5B1D318A").resolveHandle("keystoreconfigurationtypeb104type");
    
    /**
     * Gets the "KeystoreType" element
     */
    java.lang.String getKeystoreType();
    
    /**
     * Gets (as xml) the "KeystoreType" element
     */
    org.apache.xmlbeans.XmlString xgetKeystoreType();
    
    /**
     * Sets the "KeystoreType" element
     */
    void setKeystoreType(java.lang.String keystoreType);
    
    /**
     * Sets (as xml) the "KeystoreType" element
     */
    void xsetKeystoreType(org.apache.xmlbeans.XmlString keystoreType);
    
    /**
     * Gets the "KeystoreFile" element
     */
    java.lang.String getKeystoreFile();
    
    /**
     * Gets (as xml) the "KeystoreFile" element
     */
    org.apache.xmlbeans.XmlString xgetKeystoreFile();
    
    /**
     * Sets the "KeystoreFile" element
     */
    void setKeystoreFile(java.lang.String keystoreFile);
    
    /**
     * Sets (as xml) the "KeystoreFile" element
     */
    void xsetKeystoreFile(org.apache.xmlbeans.XmlString keystoreFile);
    
    /**
     * Gets the "KeystorePassword" element
     */
    java.lang.String getKeystorePassword();
    
    /**
     * Gets (as xml) the "KeystorePassword" element
     */
    org.apache.xmlbeans.XmlString xgetKeystorePassword();
    
    /**
     * Sets the "KeystorePassword" element
     */
    void setKeystorePassword(java.lang.String keystorePassword);
    
    /**
     * Sets (as xml) the "KeystorePassword" element
     */
    void xsetKeystorePassword(org.apache.xmlbeans.XmlString keystorePassword);
    
    /**
     * Gets the "Alias" element
     */
    java.lang.String getAlias();
    
    /**
     * Gets (as xml) the "Alias" element
     */
    org.apache.xmlbeans.XmlString xgetAlias();
    
    /**
     * Sets the "Alias" element
     */
    void setAlias(java.lang.String alias);
    
    /**
     * Sets (as xml) the "Alias" element
     */
    void xsetAlias(org.apache.xmlbeans.XmlString alias);
    
    /**
     * Gets the "AliasPassword" element
     */
    java.lang.String getAliasPassword();
    
    /**
     * Gets (as xml) the "AliasPassword" element
     */
    org.apache.xmlbeans.XmlString xgetAliasPassword();
    
    /**
     * Sets the "AliasPassword" element
     */
    void setAliasPassword(java.lang.String aliasPassword);
    
    /**
     * Sets (as xml) the "AliasPassword" element
     */
    void xsetAliasPassword(org.apache.xmlbeans.XmlString aliasPassword);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType newInstance() {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
