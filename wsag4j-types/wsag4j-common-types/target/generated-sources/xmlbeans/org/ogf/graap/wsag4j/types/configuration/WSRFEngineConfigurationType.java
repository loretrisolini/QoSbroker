/*
 * XML Type:  WSRFEngineConfigurationType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration;


/**
 * An XML WSRFEngineConfigurationType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public interface WSRFEngineConfigurationType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(WSRFEngineConfigurationType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s6EC9E79B893FA7F21001D53B5B1D318A").resolveHandle("wsrfengineconfigurationtypee6d4type");
    
    /**
     * Gets the "GatewayAddress" element
     */
    java.lang.String getGatewayAddress();
    
    /**
     * Gets (as xml) the "GatewayAddress" element
     */
    org.apache.xmlbeans.XmlString xgetGatewayAddress();
    
    /**
     * Sets the "GatewayAddress" element
     */
    void setGatewayAddress(java.lang.String gatewayAddress);
    
    /**
     * Sets (as xml) the "GatewayAddress" element
     */
    void xsetGatewayAddress(org.apache.xmlbeans.XmlString gatewayAddress);
    
    /**
     * Gets the "Keystore" element
     */
    org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType getKeystore();
    
    /**
     * Sets the "Keystore" element
     */
    void setKeystore(org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType keystore);
    
    /**
     * Appends and returns a new empty "Keystore" element
     */
    org.ogf.graap.wsag4j.types.configuration.KeystoreConfigurationType addNewKeystore();
    
    /**
     * Gets the "Truststore" element
     */
    org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType getTruststore();
    
    /**
     * Sets the "Truststore" element
     */
    void setTruststore(org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType truststore);
    
    /**
     * Appends and returns a new empty "Truststore" element
     */
    org.ogf.graap.wsag4j.types.configuration.TruststoreConfigurationType addNewTruststore();
    
    /**
     * Gets the "SecurityHandlerChain" element
     */
    org.ogf.graap.wsag4j.types.configuration.HandlerChainType getSecurityHandlerChain();
    
    /**
     * True if has "SecurityHandlerChain" element
     */
    boolean isSetSecurityHandlerChain();
    
    /**
     * Sets the "SecurityHandlerChain" element
     */
    void setSecurityHandlerChain(org.ogf.graap.wsag4j.types.configuration.HandlerChainType securityHandlerChain);
    
    /**
     * Appends and returns a new empty "SecurityHandlerChain" element
     */
    org.ogf.graap.wsag4j.types.configuration.HandlerChainType addNewSecurityHandlerChain();
    
    /**
     * Unsets the "SecurityHandlerChain" element
     */
    void unsetSecurityHandlerChain();
    
    /**
     * Gets the "WSAG4JEngineInstances" element
     */
    org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceListType getWSAG4JEngineInstances();
    
    /**
     * True if has "WSAG4JEngineInstances" element
     */
    boolean isSetWSAG4JEngineInstances();
    
    /**
     * Sets the "WSAG4JEngineInstances" element
     */
    void setWSAG4JEngineInstances(org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceListType wsag4JEngineInstances);
    
    /**
     * Appends and returns a new empty "WSAG4JEngineInstances" element
     */
    org.ogf.graap.wsag4j.types.configuration.WSAG4JEngineInstanceListType addNewWSAG4JEngineInstances();
    
    /**
     * Unsets the "WSAG4JEngineInstances" element
     */
    void unsetWSAG4JEngineInstances();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType newInstance() {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.graap.wsag4j.types.configuration.WSRFEngineConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
