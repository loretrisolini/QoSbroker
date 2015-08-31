/*
 * XML Type:  ImplementationConfigurationType
 * Namespace: http://schemas.scai.fraunhofer.de/config/wsag4j
 * Java type: org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.configuration;


/**
 * An XML ImplementationConfigurationType(@http://schemas.scai.fraunhofer.de/config/wsag4j).
 *
 * This is a complex type.
 */
public interface ImplementationConfigurationType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ImplementationConfigurationType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s6EC9E79B893FA7F21001D53B5B1D318A").resolveHandle("implementationconfigurationtype6ff4type");
    
    /**
     * Gets the "ImplementationClass" element
     */
    java.lang.String getImplementationClass();
    
    /**
     * Gets (as xml) the "ImplementationClass" element
     */
    org.apache.xmlbeans.XmlString xgetImplementationClass();
    
    /**
     * Sets the "ImplementationClass" element
     */
    void setImplementationClass(java.lang.String implementationClass);
    
    /**
     * Sets (as xml) the "ImplementationClass" element
     */
    void xsetImplementationClass(org.apache.xmlbeans.XmlString implementationClass);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType newInstance() {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.graap.wsag4j.types.configuration.ImplementationConfigurationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
