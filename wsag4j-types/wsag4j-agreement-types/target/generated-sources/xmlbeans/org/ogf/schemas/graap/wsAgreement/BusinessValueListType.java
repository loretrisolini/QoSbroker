/*
 * XML Type:  BusinessValueListType
 * Namespace: http://schemas.ggf.org/graap/2007/03/ws-agreement
 * Java type: org.ogf.schemas.graap.wsAgreement.BusinessValueListType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.schemas.graap.wsAgreement;


/**
 * An XML BusinessValueListType(@http://schemas.ggf.org/graap/2007/03/ws-agreement).
 *
 * This is a complex type.
 */
public interface BusinessValueListType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(BusinessValueListType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1E82E5FFD6D93F7E928DD54BBB6CABD9").resolveHandle("businessvaluelisttype97e6type");
    
    /**
     * Gets the "Importance" element
     */
    java.math.BigInteger getImportance();
    
    /**
     * Gets (as xml) the "Importance" element
     */
    org.apache.xmlbeans.XmlInteger xgetImportance();
    
    /**
     * True if has "Importance" element
     */
    boolean isSetImportance();
    
    /**
     * Sets the "Importance" element
     */
    void setImportance(java.math.BigInteger importance);
    
    /**
     * Sets (as xml) the "Importance" element
     */
    void xsetImportance(org.apache.xmlbeans.XmlInteger importance);
    
    /**
     * Unsets the "Importance" element
     */
    void unsetImportance();
    
    /**
     * Gets array of all "Penalty" elements
     */
    org.ogf.schemas.graap.wsAgreement.CompensationType[] getPenaltyArray();
    
    /**
     * Gets ith "Penalty" element
     */
    org.ogf.schemas.graap.wsAgreement.CompensationType getPenaltyArray(int i);
    
    /**
     * Returns number of "Penalty" element
     */
    int sizeOfPenaltyArray();
    
    /**
     * Sets array of all "Penalty" element
     */
    void setPenaltyArray(org.ogf.schemas.graap.wsAgreement.CompensationType[] penaltyArray);
    
    /**
     * Sets ith "Penalty" element
     */
    void setPenaltyArray(int i, org.ogf.schemas.graap.wsAgreement.CompensationType penalty);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Penalty" element
     */
    org.ogf.schemas.graap.wsAgreement.CompensationType insertNewPenalty(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Penalty" element
     */
    org.ogf.schemas.graap.wsAgreement.CompensationType addNewPenalty();
    
    /**
     * Removes the ith "Penalty" element
     */
    void removePenalty(int i);
    
    /**
     * Gets array of all "Reward" elements
     */
    org.ogf.schemas.graap.wsAgreement.CompensationType[] getRewardArray();
    
    /**
     * Gets ith "Reward" element
     */
    org.ogf.schemas.graap.wsAgreement.CompensationType getRewardArray(int i);
    
    /**
     * Returns number of "Reward" element
     */
    int sizeOfRewardArray();
    
    /**
     * Sets array of all "Reward" element
     */
    void setRewardArray(org.ogf.schemas.graap.wsAgreement.CompensationType[] rewardArray);
    
    /**
     * Sets ith "Reward" element
     */
    void setRewardArray(int i, org.ogf.schemas.graap.wsAgreement.CompensationType reward);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Reward" element
     */
    org.ogf.schemas.graap.wsAgreement.CompensationType insertNewReward(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Reward" element
     */
    org.ogf.schemas.graap.wsAgreement.CompensationType addNewReward();
    
    /**
     * Removes the ith "Reward" element
     */
    void removeReward(int i);
    
    /**
     * Gets the "Preference" element
     */
    org.ogf.schemas.graap.wsAgreement.PreferenceType getPreference();
    
    /**
     * True if has "Preference" element
     */
    boolean isSetPreference();
    
    /**
     * Sets the "Preference" element
     */
    void setPreference(org.ogf.schemas.graap.wsAgreement.PreferenceType preference);
    
    /**
     * Appends and returns a new empty "Preference" element
     */
    org.ogf.schemas.graap.wsAgreement.PreferenceType addNewPreference();
    
    /**
     * Unsets the "Preference" element
     */
    void unsetPreference();
    
    /**
     * Gets array of all "CustomBusinessValue" elements
     */
    org.apache.xmlbeans.XmlObject[] getCustomBusinessValueArray();
    
    /**
     * Gets ith "CustomBusinessValue" element
     */
    org.apache.xmlbeans.XmlObject getCustomBusinessValueArray(int i);
    
    /**
     * Returns number of "CustomBusinessValue" element
     */
    int sizeOfCustomBusinessValueArray();
    
    /**
     * Sets array of all "CustomBusinessValue" element
     */
    void setCustomBusinessValueArray(org.apache.xmlbeans.XmlObject[] customBusinessValueArray);
    
    /**
     * Sets ith "CustomBusinessValue" element
     */
    void setCustomBusinessValueArray(int i, org.apache.xmlbeans.XmlObject customBusinessValue);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "CustomBusinessValue" element
     */
    org.apache.xmlbeans.XmlObject insertNewCustomBusinessValue(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "CustomBusinessValue" element
     */
    org.apache.xmlbeans.XmlObject addNewCustomBusinessValue();
    
    /**
     * Removes the ith "CustomBusinessValue" element
     */
    void removeCustomBusinessValue(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType newInstance() {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.schemas.graap.wsAgreement.BusinessValueListType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.schemas.graap.wsAgreement.BusinessValueListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
