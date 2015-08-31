/*
 * An XML document type.
 * Localname: AgreementId
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AgreementIdDocument
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement;


/**
 * A document containing one AgreementId(@http://schemas.ggf.org/graap/2007/03/ws-agreement) element.
 *
 * This is a complex type.
 */
public interface AgreementIdDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AgreementIdDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E82E5FFD6D93F7E928DD54BBB6CABD9").resolveHandle("agreementid14b2doctype");
    
    /**
     * Gets the "AgreementId" element
     */
    java.lang.String getAgreementId();
    
    /**
     * Gets (as xml) the "AgreementId" element
     */
    org.apache.xmlbeans.XmlString xgetAgreementId();
    
    /**
     * Sets the "AgreementId" element
     */
    void setAgreementId(java.lang.String agreementId);
    
    /**
     * Sets (as xml) the "AgreementId" element
     */
    void xsetAgreementId(org.apache.xmlbeans.XmlString agreementId);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument newInstance() {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.AgreementIdDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementIdDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
