/*
 * XML Type:  CompensationType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.CompensationType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement;


/**
 * An XML CompensationType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public interface CompensationType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CompensationType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E82E5FFD6D93F7E928DD54BBB6CABD9").resolveHandle("compensationtype6323type");
    
    /**
     * Gets the "AssessmentInterval" element
     */
    org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval getAssessmentInterval();
    
    /**
     * Sets the "AssessmentInterval" element
     */
    void setAssessmentInterval(org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval assessmentInterval);
    
    /**
     * Appends and returns a new empty "AssessmentInterval" element
     */
    org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval addNewAssessmentInterval();
    
    /**
     * Gets the "ValueUnit" element
     */
    java.lang.String getValueUnit();
    
    /**
     * Gets (as xml) the "ValueUnit" element
     */
    org.apache.xmlbeans.XmlString xgetValueUnit();
    
    /**
     * True if has "ValueUnit" element
     */
    boolean isSetValueUnit();
    
    /**
     * Sets the "ValueUnit" element
     */
    void setValueUnit(java.lang.String valueUnit);
    
    /**
     * Sets (as xml) the "ValueUnit" element
     */
    void xsetValueUnit(org.apache.xmlbeans.XmlString valueUnit);
    
    /**
     * Unsets the "ValueUnit" element
     */
    void unsetValueUnit();
    
    /**
     * Gets the "ValueExpression" element
     */
    org.apache.xmlbeans.XmlObject getValueExpression();
    
    /**
     * Sets the "ValueExpression" element
     */
    void setValueExpression(org.apache.xmlbeans.XmlObject valueExpression);
    
    /**
     * Appends and returns a new empty "ValueExpression" element
     */
    org.apache.xmlbeans.XmlObject addNewValueExpression();
    
    /**
     * An XML AssessmentInterval(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
     *
     * This is a complex type.
     */
    public interface AssessmentInterval extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AssessmentInterval.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E82E5FFD6D93F7E928DD54BBB6CABD9").resolveHandle("assessmentintervald4a0elemtype");
        
        /**
         * Gets the "TimeInterval" element
         */
        org.apache.xmlbeans.GDuration getTimeInterval();
        
        /**
         * Gets (as xml) the "TimeInterval" element
         */
        org.apache.xmlbeans.XmlDuration xgetTimeInterval();
        
        /**
         * True if has "TimeInterval" element
         */
        boolean isSetTimeInterval();
        
        /**
         * Sets the "TimeInterval" element
         */
        void setTimeInterval(org.apache.xmlbeans.GDuration timeInterval);
        
        /**
         * Sets (as xml) the "TimeInterval" element
         */
        void xsetTimeInterval(org.apache.xmlbeans.XmlDuration timeInterval);
        
        /**
         * Unsets the "TimeInterval" element
         */
        void unsetTimeInterval();
        
        /**
         * Gets the "Count" element
         */
        java.math.BigInteger getCount();
        
        /**
         * Gets (as xml) the "Count" element
         */
        org.apache.xmlbeans.XmlPositiveInteger xgetCount();
        
        /**
         * True if has "Count" element
         */
        boolean isSetCount();
        
        /**
         * Sets the "Count" element
         */
        void setCount(java.math.BigInteger count);
        
        /**
         * Sets (as xml) the "Count" element
         */
        void xsetCount(org.apache.xmlbeans.XmlPositiveInteger count);
        
        /**
         * Unsets the "Count" element
         */
        void unsetCount();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval newInstance() {
              return (org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (org.ogf.schemas.graap.wsAgreement.CompensationType.AssessmentInterval) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.schemas.graap.wsAgreement.CompensationType newInstance() {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CompensationType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.schemas.graap.wsAgreement.CompensationType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CompensationType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.schemas.graap.wsAgreement.CompensationType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CompensationType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CompensationType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CompensationType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CompensationType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CompensationType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CompensationType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CompensationType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CompensationType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CompensationType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CompensationType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.CompensationType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.CompensationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.CompensationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.CompensationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
