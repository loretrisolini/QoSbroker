/*
 * XML Type:  BaseFaultType
 * Namespace: http://docs.oasis-open.org/wsrf/bf-2
 * Java type: org.oasisOpen.docs.wsrf.bf2.BaseFaultType
 *
 * Automatically generated - do not modify.
 */
package org.oasisOpen.docs.wsrf.bf2;


/**
 * An XML BaseFaultType(@http://docs.oasis-open.org/wsrf/bf-2).
 *
 * This is a complex type.
 */
public interface BaseFaultType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(BaseFaultType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E82E5FFD6D93F7E928DD54BBB6CABD9").resolveHandle("basefaulttype46fatype");
    
    /**
     * Gets the "Timestamp" element
     */
    java.util.Calendar getTimestamp();
    
    /**
     * Gets (as xml) the "Timestamp" element
     */
    org.apache.xmlbeans.XmlDateTime xgetTimestamp();
    
    /**
     * Sets the "Timestamp" element
     */
    void setTimestamp(java.util.Calendar timestamp);
    
    /**
     * Sets (as xml) the "Timestamp" element
     */
    void xsetTimestamp(org.apache.xmlbeans.XmlDateTime timestamp);
    
    /**
     * Gets the "Originator" element
     */
    org.w3.x2005.x08.addressing.EndpointReferenceType getOriginator();
    
    /**
     * True if has "Originator" element
     */
    boolean isSetOriginator();
    
    /**
     * Sets the "Originator" element
     */
    void setOriginator(org.w3.x2005.x08.addressing.EndpointReferenceType originator);
    
    /**
     * Appends and returns a new empty "Originator" element
     */
    org.w3.x2005.x08.addressing.EndpointReferenceType addNewOriginator();
    
    /**
     * Unsets the "Originator" element
     */
    void unsetOriginator();
    
    /**
     * Gets the "ErrorCode" element
     */
    org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode getErrorCode();
    
    /**
     * True if has "ErrorCode" element
     */
    boolean isSetErrorCode();
    
    /**
     * Sets the "ErrorCode" element
     */
    void setErrorCode(org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode errorCode);
    
    /**
     * Appends and returns a new empty "ErrorCode" element
     */
    org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode addNewErrorCode();
    
    /**
     * Unsets the "ErrorCode" element
     */
    void unsetErrorCode();
    
    /**
     * Gets array of all "Description" elements
     */
    org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description[] getDescriptionArray();
    
    /**
     * Gets ith "Description" element
     */
    org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description getDescriptionArray(int i);
    
    /**
     * Returns number of "Description" element
     */
    int sizeOfDescriptionArray();
    
    /**
     * Sets array of all "Description" element
     */
    void setDescriptionArray(org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description[] descriptionArray);
    
    /**
     * Sets ith "Description" element
     */
    void setDescriptionArray(int i, org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description description);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Description" element
     */
    org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description insertNewDescription(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Description" element
     */
    org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description addNewDescription();
    
    /**
     * Removes the ith "Description" element
     */
    void removeDescription(int i);
    
    /**
     * Gets the "FaultCause" element
     */
    org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause getFaultCause();
    
    /**
     * True if has "FaultCause" element
     */
    boolean isSetFaultCause();
    
    /**
     * Sets the "FaultCause" element
     */
    void setFaultCause(org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause faultCause);
    
    /**
     * Appends and returns a new empty "FaultCause" element
     */
    org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause addNewFaultCause();
    
    /**
     * Unsets the "FaultCause" element
     */
    void unsetFaultCause();
    
    /**
     * An XML ErrorCode(@http://docs.oasis-open.org/wsrf/bf-2).
     *
     * This is a complex type.
     */
    public interface ErrorCode extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ErrorCode.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E82E5FFD6D93F7E928DD54BBB6CABD9").resolveHandle("errorcode8f39elemtype");
        
        /**
         * Gets the "dialect" attribute
         */
        java.lang.String getDialect();
        
        /**
         * Gets (as xml) the "dialect" attribute
         */
        org.apache.xmlbeans.XmlAnyURI xgetDialect();
        
        /**
         * Sets the "dialect" attribute
         */
        void setDialect(java.lang.String dialect);
        
        /**
         * Sets (as xml) the "dialect" attribute
         */
        void xsetDialect(org.apache.xmlbeans.XmlAnyURI dialect);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode newInstance() {
              return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.ErrorCode) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * An XML Description(@http://docs.oasis-open.org/wsrf/bf-2).
     *
     * This is an atomic type that is a restriction of org.oasisOpen.docs.wsrf.bf2.BaseFaultType$Description.
     */
    public interface Description extends org.apache.xmlbeans.XmlString
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Description.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E82E5FFD6D93F7E928DD54BBB6CABD9").resolveHandle("description0fb2elemtype");
        
        /**
         * Gets the "lang" attribute
         */
        java.lang.String getLang();
        
        /**
         * Gets (as xml) the "lang" attribute
         */
        org.apache.xmlbeans.XmlLanguage xgetLang();
        
        /**
         * True if has "lang" attribute
         */
        boolean isSetLang();
        
        /**
         * Sets the "lang" attribute
         */
        void setLang(java.lang.String lang);
        
        /**
         * Sets (as xml) the "lang" attribute
         */
        void xsetLang(org.apache.xmlbeans.XmlLanguage lang);
        
        /**
         * Unsets the "lang" attribute
         */
        void unsetLang();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description newInstance() {
              return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.Description) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * An XML FaultCause(@http://docs.oasis-open.org/wsrf/bf-2).
     *
     * This is a complex type.
     */
    public interface FaultCause extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(FaultCause.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E82E5FFD6D93F7E928DD54BBB6CABD9").resolveHandle("faultcause80b7elemtype");
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause newInstance() {
              return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType.FaultCause) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType newInstance() {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.oasisOpen.docs.wsrf.bf2.BaseFaultType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.oasisOpen.docs.wsrf.bf2.BaseFaultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
