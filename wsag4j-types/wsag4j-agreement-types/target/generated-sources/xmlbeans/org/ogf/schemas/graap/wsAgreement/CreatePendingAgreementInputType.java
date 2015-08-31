/*
 * XML Type:  CreatePendingAgreementInputType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement;


/**
 * An XML CreatePendingAgreementInputType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public interface CreatePendingAgreementInputType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CreatePendingAgreementInputType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E82E5FFD6D93F7E928DD54BBB6CABD9").resolveHandle("creatependingagreementinputtype3212type");
    
    /**
     * Gets the "AgreementAcceptanceEPR" element
     */
    org.w3.x2005.x08.addressing.EndpointReferenceType getAgreementAcceptanceEPR();
    
    /**
     * True if has "AgreementAcceptanceEPR" element
     */
    boolean isSetAgreementAcceptanceEPR();
    
    /**
     * Sets the "AgreementAcceptanceEPR" element
     */
    void setAgreementAcceptanceEPR(org.w3.x2005.x08.addressing.EndpointReferenceType agreementAcceptanceEPR);
    
    /**
     * Appends and returns a new empty "AgreementAcceptanceEPR" element
     */
    org.w3.x2005.x08.addressing.EndpointReferenceType addNewAgreementAcceptanceEPR();
    
    /**
     * Unsets the "AgreementAcceptanceEPR" element
     */
    void unsetAgreementAcceptanceEPR();
    
    /**
     * Gets the "InitiatorAgreementEPR" element
     */
    org.w3.x2005.x08.addressing.EndpointReferenceType getInitiatorAgreementEPR();
    
    /**
     * True if has "InitiatorAgreementEPR" element
     */
    boolean isSetInitiatorAgreementEPR();
    
    /**
     * Sets the "InitiatorAgreementEPR" element
     */
    void setInitiatorAgreementEPR(org.w3.x2005.x08.addressing.EndpointReferenceType initiatorAgreementEPR);
    
    /**
     * Appends and returns a new empty "InitiatorAgreementEPR" element
     */
    org.w3.x2005.x08.addressing.EndpointReferenceType addNewInitiatorAgreementEPR();
    
    /**
     * Unsets the "InitiatorAgreementEPR" element
     */
    void unsetInitiatorAgreementEPR();
    
    /**
     * Gets the "AgreementOffer" element
     */
    org.ogf.schemas.graap.wsAgreement.AgreementType getAgreementOffer();
    
    /**
     * Sets the "AgreementOffer" element
     */
    void setAgreementOffer(org.ogf.schemas.graap.wsAgreement.AgreementType agreementOffer);
    
    /**
     * Appends and returns a new empty "AgreementOffer" element
     */
    org.ogf.schemas.graap.wsAgreement.AgreementType addNewAgreementOffer();
    
    /**
     * Gets array of all "NoncriticalExtension" elements
     */
    org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType[] getNoncriticalExtensionArray();
    
    /**
     * Gets ith "NoncriticalExtension" element
     */
    org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType getNoncriticalExtensionArray(int i);
    
    /**
     * Returns number of "NoncriticalExtension" element
     */
    int sizeOfNoncriticalExtensionArray();
    
    /**
     * Sets array of all "NoncriticalExtension" element
     */
    void setNoncriticalExtensionArray(org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType[] noncriticalExtensionArray);
    
    /**
     * Sets ith "NoncriticalExtension" element
     */
    void setNoncriticalExtensionArray(int i, org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType noncriticalExtension);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "NoncriticalExtension" element
     */
    org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType insertNewNoncriticalExtension(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "NoncriticalExtension" element
     */
    org.ogf.schemas.graap.wsAgreement.NoncriticalExtensionType addNewNoncriticalExtension();
    
    /**
     * Removes the ith "NoncriticalExtension" element
     */
    void removeNoncriticalExtension(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType newInstance() {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.CreatePendingAgreementInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
