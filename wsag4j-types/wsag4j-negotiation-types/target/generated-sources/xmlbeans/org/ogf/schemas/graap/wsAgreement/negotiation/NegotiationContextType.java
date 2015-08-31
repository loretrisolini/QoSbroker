/*
 * XML Type:  NegotiationContextType
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation;


/**
 * An XML NegotiationContextType(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation).
 *
 * This is a complex type.
 */
public interface NegotiationContextType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(NegotiationContextType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s6A69BF76A388D221204F516E888D846F").resolveHandle("negotiationcontexttype1b28type");
    
    /**
     * Gets the "NegotiationType" element
     */
    org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType getNegotiationType();
    
    /**
     * Sets the "NegotiationType" element
     */
    void setNegotiationType(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType negotiationType);
    
    /**
     * Appends and returns a new empty "NegotiationType" element
     */
    org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationType addNewNegotiationType();
    
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
     * Gets the "NegotiationInitiator" element
     */
    org.apache.xmlbeans.XmlObject getNegotiationInitiator();
    
    /**
     * True if has "NegotiationInitiator" element
     */
    boolean isSetNegotiationInitiator();
    
    /**
     * Sets the "NegotiationInitiator" element
     */
    void setNegotiationInitiator(org.apache.xmlbeans.XmlObject negotiationInitiator);
    
    /**
     * Appends and returns a new empty "NegotiationInitiator" element
     */
    org.apache.xmlbeans.XmlObject addNewNegotiationInitiator();
    
    /**
     * Unsets the "NegotiationInitiator" element
     */
    void unsetNegotiationInitiator();
    
    /**
     * Gets the "NegotiationResponder" element
     */
    org.apache.xmlbeans.XmlObject getNegotiationResponder();
    
    /**
     * True if has "NegotiationResponder" element
     */
    boolean isSetNegotiationResponder();
    
    /**
     * Sets the "NegotiationResponder" element
     */
    void setNegotiationResponder(org.apache.xmlbeans.XmlObject negotiationResponder);
    
    /**
     * Appends and returns a new empty "NegotiationResponder" element
     */
    org.apache.xmlbeans.XmlObject addNewNegotiationResponder();
    
    /**
     * Unsets the "NegotiationResponder" element
     */
    void unsetNegotiationResponder();
    
    /**
     * Gets the "AgreementResponder" element
     */
    org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType.Enum getAgreementResponder();
    
    /**
     * Gets (as xml) the "AgreementResponder" element
     */
    org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType xgetAgreementResponder();
    
    /**
     * Sets the "AgreementResponder" element
     */
    void setAgreementResponder(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType.Enum agreementResponder);
    
    /**
     * Sets (as xml) the "AgreementResponder" element
     */
    void xsetAgreementResponder(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType agreementResponder);
    
    /**
     * Gets the "AgreementFactoryEPR" element
     */
    org.w3.x2005.x08.addressing.EndpointReferenceType getAgreementFactoryEPR();
    
    /**
     * True if has "AgreementFactoryEPR" element
     */
    boolean isSetAgreementFactoryEPR();
    
    /**
     * Sets the "AgreementFactoryEPR" element
     */
    void setAgreementFactoryEPR(org.w3.x2005.x08.addressing.EndpointReferenceType agreementFactoryEPR);
    
    /**
     * Appends and returns a new empty "AgreementFactoryEPR" element
     */
    org.w3.x2005.x08.addressing.EndpointReferenceType addNewAgreementFactoryEPR();
    
    /**
     * Unsets the "AgreementFactoryEPR" element
     */
    void unsetAgreementFactoryEPR();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType newInstance() {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
