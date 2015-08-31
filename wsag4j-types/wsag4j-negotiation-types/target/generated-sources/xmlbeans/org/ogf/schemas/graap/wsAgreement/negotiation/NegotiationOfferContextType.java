/*
 * XML Type:  NegotiationOfferContextType
 * Namespace: http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation
 * Java type: org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement.negotiation;


/**
 * An XML NegotiationOfferContextType(@http://schemas.ogf.org/graap/2009/11/ws-agreement-negotiation).
 *
 * This is a complex type.
 */
public interface NegotiationOfferContextType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(NegotiationOfferContextType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s6A69BF76A388D221204F516E888D846F").resolveHandle("negotiationoffercontexttype1edctype");
    
    /**
     * Gets the "CounterOfferTo" element
     */
    java.lang.String getCounterOfferTo();
    
    /**
     * Gets (as xml) the "CounterOfferTo" element
     */
    org.apache.xmlbeans.XmlString xgetCounterOfferTo();
    
    /**
     * Sets the "CounterOfferTo" element
     */
    void setCounterOfferTo(java.lang.String counterOfferTo);
    
    /**
     * Sets (as xml) the "CounterOfferTo" element
     */
    void xsetCounterOfferTo(org.apache.xmlbeans.XmlString counterOfferTo);
    
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
     * Gets the "Creator" element
     */
    org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType.Enum getCreator();
    
    /**
     * Gets (as xml) the "Creator" element
     */
    org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType xgetCreator();
    
    /**
     * Sets the "Creator" element
     */
    void setCreator(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType.Enum creator);
    
    /**
     * Sets (as xml) the "Creator" element
     */
    void xsetCreator(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationRoleType creator);
    
    /**
     * Gets the "State" element
     */
    org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferStateType getState();
    
    /**
     * Sets the "State" element
     */
    void setState(org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferStateType state);
    
    /**
     * Appends and returns a new empty "State" element
     */
    org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferStateType addNewState();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType newInstance() {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.negotiation.NegotiationOfferContextType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
