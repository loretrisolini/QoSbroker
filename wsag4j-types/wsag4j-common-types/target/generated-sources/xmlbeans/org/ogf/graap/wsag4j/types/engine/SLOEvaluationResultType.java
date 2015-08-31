/*
 * XML Type:  SLOEvaluationResultType
 * Namespace: http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine
 * Java type: org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType
 *
 * Automatically generated - do not modify.
 */
package org.ogf.graap.wsag4j.types.engine;


/**
 * An XML SLOEvaluationResultType(@http://schemas.scai.fraunhofer.de/2008/11/wsag4j/engine).
 *
 * This is an atomic type that is a restriction of org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType.
 */
public interface SLOEvaluationResultType extends org.apache.xmlbeans.XmlString
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(SLOEvaluationResultType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s6EC9E79B893FA7F21001D53B5B1D318A").resolveHandle("sloevaluationresulttype4fadtype");
    
    org.apache.xmlbeans.StringEnumAbstractBase enumValue();
    void set(org.apache.xmlbeans.StringEnumAbstractBase e);
    
    static final Enum SLO_FULFILLED = Enum.forString("SLO_FULFILLED");
    static final Enum SLO_VIOLATED = Enum.forString("SLO_VIOLATED");
    static final Enum SLO_NOT_DETERMINED = Enum.forString("SLO_NOT_DETERMINED");
    
    static final int INT_SLO_FULFILLED = Enum.INT_SLO_FULFILLED;
    static final int INT_SLO_VIOLATED = Enum.INT_SLO_VIOLATED;
    static final int INT_SLO_NOT_DETERMINED = Enum.INT_SLO_NOT_DETERMINED;
    
    /**
     * Enumeration value class for org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType.
     * These enum values can be used as follows:
     * <pre>
     * enum.toString(); // returns the string value of the enum
     * enum.intValue(); // returns an int value, useful for switches
     * // e.g., case Enum.INT_SLO_FULFILLED
     * Enum.forString(s); // returns the enum value for a string
     * Enum.forInt(i); // returns the enum value for an int
     * </pre>
     * Enumeration objects are immutable singleton objects that
     * can be compared using == object equality. They have no
     * public constructor. See the constants defined within this
     * class for all the valid values.
     */
    static final class Enum extends org.apache.xmlbeans.StringEnumAbstractBase
    {
        /**
         * Returns the enum value for a string, or null if none.
         */
        public static Enum forString(java.lang.String s)
            { return (Enum)table.forString(s); }
        /**
         * Returns the enum value corresponding to an int, or null if none.
         */
        public static Enum forInt(int i)
            { return (Enum)table.forInt(i); }
        
        private Enum(java.lang.String s, int i)
            { super(s, i); }
        
        static final int INT_SLO_FULFILLED = 1;
        static final int INT_SLO_VIOLATED = 2;
        static final int INT_SLO_NOT_DETERMINED = 3;
        
        public static final org.apache.xmlbeans.StringEnumAbstractBase.Table table =
            new org.apache.xmlbeans.StringEnumAbstractBase.Table
        (
            new Enum[]
            {
                new Enum("SLO_FULFILLED", INT_SLO_FULFILLED),
                new Enum("SLO_VIOLATED", INT_SLO_VIOLATED),
                new Enum("SLO_NOT_DETERMINED", INT_SLO_NOT_DETERMINED),
            }
        );
        private static final long serialVersionUID = 1L;
        private java.lang.Object readResolve() { return forInt(intValue()); } 
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType newValue(java.lang.Object obj) {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) type.newValue( obj ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType newInstance() {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.ogf.graap.wsag4j.types.engine.SLOEvaluationResultType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
