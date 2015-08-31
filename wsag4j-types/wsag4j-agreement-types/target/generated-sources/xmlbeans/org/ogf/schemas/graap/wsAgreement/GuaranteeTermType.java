/*
 * XML Type:  GuaranteeTermType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.GuaranteeTermType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement;


/**
 * An XML GuaranteeTermType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public interface GuaranteeTermType extends org.ogf.schemas.graap.wsAgreement.TermType
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(GuaranteeTermType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E82E5FFD6D93F7E928DD54BBB6CABD9").resolveHandle("guaranteetermtype5aebtype");
    
    /**
     * Gets array of all "ServiceScope" elements
     */
    org.ogf.schemas.graap.wsAgreement.ServiceSelectorType[] getServiceScopeArray();
    
    /**
     * Gets ith "ServiceScope" element
     */
    org.ogf.schemas.graap.wsAgreement.ServiceSelectorType getServiceScopeArray(int i);
    
    /**
     * Returns number of "ServiceScope" element
     */
    int sizeOfServiceScopeArray();
    
    /**
     * Sets array of all "ServiceScope" element
     */
    void setServiceScopeArray(org.ogf.schemas.graap.wsAgreement.ServiceSelectorType[] serviceScopeArray);
    
    /**
     * Sets ith "ServiceScope" element
     */
    void setServiceScopeArray(int i, org.ogf.schemas.graap.wsAgreement.ServiceSelectorType serviceScope);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ServiceScope" element
     */
    org.ogf.schemas.graap.wsAgreement.ServiceSelectorType insertNewServiceScope(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ServiceScope" element
     */
    org.ogf.schemas.graap.wsAgreement.ServiceSelectorType addNewServiceScope();
    
    /**
     * Removes the ith "ServiceScope" element
     */
    void removeServiceScope(int i);
    
    /**
     * Gets the "QualifyingCondition" element
     */
    org.apache.xmlbeans.XmlObject getQualifyingCondition();
    
    /**
     * True if has "QualifyingCondition" element
     */
    boolean isSetQualifyingCondition();
    
    /**
     * Sets the "QualifyingCondition" element
     */
    void setQualifyingCondition(org.apache.xmlbeans.XmlObject qualifyingCondition);
    
    /**
     * Appends and returns a new empty "QualifyingCondition" element
     */
    org.apache.xmlbeans.XmlObject addNewQualifyingCondition();
    
    /**
     * Unsets the "QualifyingCondition" element
     */
    void unsetQualifyingCondition();
    
    /**
     * Gets the "ServiceLevelObjective" element
     */
    org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType getServiceLevelObjective();
    
    /**
     * Sets the "ServiceLevelObjective" element
     */
    void setServiceLevelObjective(org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType serviceLevelObjective);
    
    /**
     * Appends and returns a new empty "ServiceLevelObjective" element
     */
    org.ogf.schemas.graap.wsAgreement.ServiceLevelObjectiveType addNewServiceLevelObjective();
    
    /**
     * Gets the "BusinessValueList" element
     */
    org.ogf.schemas.graap.wsAgreement.BusinessValueListType getBusinessValueList();
    
    /**
     * Sets the "BusinessValueList" element
     */
    void setBusinessValueList(org.ogf.schemas.graap.wsAgreement.BusinessValueListType businessValueList);
    
    /**
     * Appends and returns a new empty "BusinessValueList" element
     */
    org.ogf.schemas.graap.wsAgreement.BusinessValueListType addNewBusinessValueList();
    
    /**
     * Gets the "Obligated" attribute
     */
    org.ogf.schemas.graap.wsAgreement.ServiceRoleType.Enum getObligated();
    
    /**
     * Gets (as xml) the "Obligated" attribute
     */
    org.ogf.schemas.graap.wsAgreement.ServiceRoleType xgetObligated();
    
    /**
     * True if has "Obligated" attribute
     */
    boolean isSetObligated();
    
    /**
     * Sets the "Obligated" attribute
     */
    void setObligated(org.ogf.schemas.graap.wsAgreement.ServiceRoleType.Enum obligated);
    
    /**
     * Sets (as xml) the "Obligated" attribute
     */
    void xsetObligated(org.ogf.schemas.graap.wsAgreement.ServiceRoleType obligated);
    
    /**
     * Unsets the "Obligated" attribute
     */
    void unsetObligated();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType newInstance() {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.GuaranteeTermType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.GuaranteeTermType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
