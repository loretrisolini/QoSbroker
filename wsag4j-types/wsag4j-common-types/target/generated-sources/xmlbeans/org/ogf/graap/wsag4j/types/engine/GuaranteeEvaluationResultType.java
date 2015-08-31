/*
 * XML Type:  GuaranteeEvaluationResultType
 * Namespace: http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine
 * Java type: org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.engine;


/**
 * An XML GuaranteeEvaluationResultType(@http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine).
 *
 * This is a complex type.
 */
public interface GuaranteeEvaluationResultType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(GuaranteeEvaluationResultType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s6EC9E79B893FA7F21001D53B5B1D318A").resolveHandle("guaranteeevaluationresulttypefcbftype");
    
    /**
     * Gets the "Name" element
     */
    java.lang.String getName();
    
    /**
     * Gets (as xml) the "Name" element
     */
    org.apache.xmlbeans.XmlString xgetName();
    
    /**
     * Sets the "Name" element
     */
    void setName(java.lang.String name);
    
    /**
     * Sets (as xml) the "Name" element
     */
    void xsetName(org.apache.xmlbeans.XmlString name);
    
    /**
     * Gets the "Type" element
     */
    org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType.Enum getType();
    
    /**
     * Gets (as xml) the "Type" element
     */
    org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType xgetType();
    
    /**
     * Sets the "Type" element
     */
    void setType(org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType.Enum type);
    
    /**
     * Sets (as xml) the "Type" element
     */
    void xsetType(org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType type);
    
    /**
     * Gets the "Importance" element
     */
    int getImportance();
    
    /**
     * Gets (as xml) the "Importance" element
     */
    org.apache.xmlbeans.XmlInt xgetImportance();
    
    /**
     * Sets the "Importance" element
     */
    void setImportance(int importance);
    
    /**
     * Sets (as xml) the "Importance" element
     */
    void xsetImportance(org.apache.xmlbeans.XmlInt importance);
    
    /**
     * Gets the "Compensation" element
     */
    org.ogf.graap.wsag4j.types.engine.CompensationType getCompensation();
    
    /**
     * True if has "Compensation" element
     */
    boolean isSetCompensation();
    
    /**
     * Sets the "Compensation" element
     */
    void setCompensation(org.ogf.graap.wsag4j.types.engine.CompensationType compensation);
    
    /**
     * Appends and returns a new empty "Compensation" element
     */
    org.ogf.graap.wsag4j.types.engine.CompensationType addNewCompensation();
    
    /**
     * Unsets the "Compensation" element
     */
    void unsetCompensation();
    
    /**
     * Gets the "Details" element
     */
    org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationDetailType getDetails();
    
    /**
     * Sets the "Details" element
     */
    void setDetails(org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationDetailType details);
    
    /**
     * Appends and returns a new empty "Details" element
     */
    org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationDetailType addNewDetails();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType newInstance() {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.graap.wsag4j.types.engine.GuaranteeEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
