/*
 * XML Type:  PreferenceType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.PreferenceType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement;


/**
 * An XML PreferenceType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public interface PreferenceType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(PreferenceType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E82E5FFD6D93F7E928DD54BBB6CABD9").resolveHandle("preferencetypeaf24type");
    
    /**
     * Gets array of all "ServiceTermReference" elements
     */
    java.lang.String[] getServiceTermReferenceArray();
    
    /**
     * Gets ith "ServiceTermReference" element
     */
    java.lang.String getServiceTermReferenceArray(int i);
    
    /**
     * Gets (as xml) array of all "ServiceTermReference" elements
     */
    org.apache.xmlbeans.XmlString[] xgetServiceTermReferenceArray();
    
    /**
     * Gets (as xml) ith "ServiceTermReference" element
     */
    org.apache.xmlbeans.XmlString xgetServiceTermReferenceArray(int i);
    
    /**
     * Returns number of "ServiceTermReference" element
     */
    int sizeOfServiceTermReferenceArray();
    
    /**
     * Sets array of all "ServiceTermReference" element
     */
    void setServiceTermReferenceArray(java.lang.String[] serviceTermReferenceArray);
    
    /**
     * Sets ith "ServiceTermReference" element
     */
    void setServiceTermReferenceArray(int i, java.lang.String serviceTermReference);
    
    /**
     * Sets (as xml) array of all "ServiceTermReference" element
     */
    void xsetServiceTermReferenceArray(org.apache.xmlbeans.XmlString[] serviceTermReferenceArray);
    
    /**
     * Sets (as xml) ith "ServiceTermReference" element
     */
    void xsetServiceTermReferenceArray(int i, org.apache.xmlbeans.XmlString serviceTermReference);
    
    /**
     * Inserts the value as the ith "ServiceTermReference" element
     */
    void insertServiceTermReference(int i, java.lang.String serviceTermReference);
    
    /**
     * Appends the value as the last "ServiceTermReference" element
     */
    void addServiceTermReference(java.lang.String serviceTermReference);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ServiceTermReference" element
     */
    org.apache.xmlbeans.XmlString insertNewServiceTermReference(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ServiceTermReference" element
     */
    org.apache.xmlbeans.XmlString addNewServiceTermReference();
    
    /**
     * Removes the ith "ServiceTermReference" element
     */
    void removeServiceTermReference(int i);
    
    /**
     * Gets array of all "Utility" elements
     */
    float[] getUtilityArray();
    
    /**
     * Gets ith "Utility" element
     */
    float getUtilityArray(int i);
    
    /**
     * Gets (as xml) array of all "Utility" elements
     */
    org.apache.xmlbeans.XmlFloat[] xgetUtilityArray();
    
    /**
     * Gets (as xml) ith "Utility" element
     */
    org.apache.xmlbeans.XmlFloat xgetUtilityArray(int i);
    
    /**
     * Returns number of "Utility" element
     */
    int sizeOfUtilityArray();
    
    /**
     * Sets array of all "Utility" element
     */
    void setUtilityArray(float[] utilityArray);
    
    /**
     * Sets ith "Utility" element
     */
    void setUtilityArray(int i, float utility);
    
    /**
     * Sets (as xml) array of all "Utility" element
     */
    void xsetUtilityArray(org.apache.xmlbeans.XmlFloat[] utilityArray);
    
    /**
     * Sets (as xml) ith "Utility" element
     */
    void xsetUtilityArray(int i, org.apache.xmlbeans.XmlFloat utility);
    
    /**
     * Inserts the value as the ith "Utility" element
     */
    void insertUtility(int i, float utility);
    
    /**
     * Appends the value as the last "Utility" element
     */
    void addUtility(float utility);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Utility" element
     */
    org.apache.xmlbeans.XmlFloat insertNewUtility(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Utility" element
     */
    org.apache.xmlbeans.XmlFloat addNewUtility();
    
    /**
     * Removes the ith "Utility" element
     */
    void removeUtility(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType newInstance() {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.PreferenceType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.PreferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
