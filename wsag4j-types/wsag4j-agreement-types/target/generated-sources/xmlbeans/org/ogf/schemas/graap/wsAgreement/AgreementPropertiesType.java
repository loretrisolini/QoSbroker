/*
 * XML Type:  AgreementPropertiesType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement;


/**
 * An XML AgreementPropertiesType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public interface AgreementPropertiesType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AgreementPropertiesType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E82E5FFD6D93F7E928DD54BBB6CABD9").resolveHandle("agreementpropertiestype10b4type");
    
    /**
     * Gets the "Name" element
     */
    java.lang.String getName();
    
    /**
     * Gets (as xml) the "Name" element
     */
    org.apache.xmlbeans.XmlString xgetName();
    
    /**
     * True if has "Name" element
     */
    boolean isSetName();
    
    /**
     * Sets the "Name" element
     */
    void setName(java.lang.String name);
    
    /**
     * Sets (as xml) the "Name" element
     */
    void xsetName(org.apache.xmlbeans.XmlString name);
    
    /**
     * Unsets the "Name" element
     */
    void unsetName();
    
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
     * Gets the "Context" element
     */
    org.ogf.schemas.graap.wsAgreement.AgreementContextType getContext();
    
    /**
     * Sets the "Context" element
     */
    void setContext(org.ogf.schemas.graap.wsAgreement.AgreementContextType context);
    
    /**
     * Appends and returns a new empty "Context" element
     */
    org.ogf.schemas.graap.wsAgreement.AgreementContextType addNewContext();
    
    /**
     * Gets the "Terms" element
     */
    org.ogf.schemas.graap.wsAgreement.TermTreeType getTerms();
    
    /**
     * Sets the "Terms" element
     */
    void setTerms(org.ogf.schemas.graap.wsAgreement.TermTreeType terms);
    
    /**
     * Appends and returns a new empty "Terms" element
     */
    org.ogf.schemas.graap.wsAgreement.TermTreeType addNewTerms();
    
    /**
     * Gets the "AgreementState" element
     */
    org.ogf.schemas.graap.wsAgreement.AgreementStateType getAgreementState();
    
    /**
     * Sets the "AgreementState" element
     */
    void setAgreementState(org.ogf.schemas.graap.wsAgreement.AgreementStateType agreementState);
    
    /**
     * Appends and returns a new empty "AgreementState" element
     */
    org.ogf.schemas.graap.wsAgreement.AgreementStateType addNewAgreementState();
    
    /**
     * Gets array of all "GuaranteeTermState" elements
     */
    org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType[] getGuaranteeTermStateArray();
    
    /**
     * Gets ith "GuaranteeTermState" element
     */
    org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType getGuaranteeTermStateArray(int i);
    
    /**
     * Returns number of "GuaranteeTermState" element
     */
    int sizeOfGuaranteeTermStateArray();
    
    /**
     * Sets array of all "GuaranteeTermState" element
     */
    void setGuaranteeTermStateArray(org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType[] guaranteeTermStateArray);
    
    /**
     * Sets ith "GuaranteeTermState" element
     */
    void setGuaranteeTermStateArray(int i, org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType guaranteeTermState);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "GuaranteeTermState" element
     */
    org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType insertNewGuaranteeTermState(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "GuaranteeTermState" element
     */
    org.ogf.schemas.graap.wsAgreement.GuaranteeTermStateType addNewGuaranteeTermState();
    
    /**
     * Removes the ith "GuaranteeTermState" element
     */
    void removeGuaranteeTermState(int i);
    
    /**
     * Gets array of all "ServiceTermState" elements
     */
    org.ogf.schemas.graap.wsAgreement.ServiceTermStateType[] getServiceTermStateArray();
    
    /**
     * Gets ith "ServiceTermState" element
     */
    org.ogf.schemas.graap.wsAgreement.ServiceTermStateType getServiceTermStateArray(int i);
    
    /**
     * Returns number of "ServiceTermState" element
     */
    int sizeOfServiceTermStateArray();
    
    /**
     * Sets array of all "ServiceTermState" element
     */
    void setServiceTermStateArray(org.ogf.schemas.graap.wsAgreement.ServiceTermStateType[] serviceTermStateArray);
    
    /**
     * Sets ith "ServiceTermState" element
     */
    void setServiceTermStateArray(int i, org.ogf.schemas.graap.wsAgreement.ServiceTermStateType serviceTermState);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ServiceTermState" element
     */
    org.ogf.schemas.graap.wsAgreement.ServiceTermStateType insertNewServiceTermState(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ServiceTermState" element
     */
    org.ogf.schemas.graap.wsAgreement.ServiceTermStateType addNewServiceTermState();
    
    /**
     * Removes the ith "ServiceTermState" element
     */
    void removeServiceTermState(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType newInstance() {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.AgreementPropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
