QoSbroker

##############################
# Equinox settings
##############################
eclipse.ignoreApp=true
osgi.clean=true
osgi.noShutdown=true
osgi.bundles.defaultStartLevel=4
osgi.java.profile=java6-server.profile
osgi.java.profile.bootdelegation=override
# PaxLogging configuration folder (absolute path)
bundles.configuration.location=//root//IoTBroker_4.3.3//fiwareRelease//iotbrokerconfig//bundleConfigurations
# IoT Broker Server port
tomcat.init.port=80
# Internal Database folder
hsqldb.directory=.//SQL_database//database//linkDB
# Internal Database port
hsqldb.port=9001
# Enable the Database Logs
hsqldb.silent=false
# Absolute path to the config folder
dir.config=//root//IoTBroker_4.3.3//fiwareRelease//
ngsiclient.layer=connector 
java.awt.headless=true 
file.encoding=UTF-8
-server -Xms2048m -Xmx2048m
-XX:NewSize=1024m -XX:MaxNewSize=1024m -XX:PermSize=1024m
-XX:MaxPermSize=1024m -XX:+DisableExplicitGC
##############################
# Client bundles to install
##############################
osgi.bundles= plugins/equinox/org.eclipse.core.contenttype-3.4.100.v20100505-1235.jar@start, \
plugins/equinox/org.eclipse.equinox.common-3.6.0.v20110506.jar@2:start, \
plugins/equinox/org.eclipse.core.jobs-3.5.0.v20100515.jar@start, \
plugins/equinox/org.eclipse.equinox.app-1.3.0.v20100512.jar@start, \
plugins/equinox/org.eclipse.equinox.preferences-3.3.0.v20100503.jar@start, \
plugins/equinox/org.eclipse.equinox.registry-3.5.0.v20100503.jar@start, \
plugins/equinox/org.eclipse.osgi.services-3.2.100.v20100503.jar@start, \
plugins/equinox/org.eclipse.equinox.cm_3.2.0.v20070116.jar@1:start, \
plugins/pax/pax-confman-propsloader-0.2.2.jar@2:start, \
plugins/pax/pax-logging-api-1.7.0-20120710.130402-38.jar@2:start, \
plugins/pax/pax-logging-service-1.7.0-20120710.130445-38.jar@2:start, \
plugins/bundles/com.springsource.javax.activation-1.1.1.jar@start, \
plugins/bundles/javax.persistence-2.0.0.jar@start, \
plugins/bundles/httpclient-4.2.0-osgi.jar@start, \
plugins/bundles/httpcore-4.2.0-osgi.jar@start, \
plugins/bundles/com.springsource.org.apache.commons.io-1.4.0.jar, \
plugins/bundles/com.springsource.org.apache.commons.codec-1.6.0.jar@start, \
plugins/bundles/com.springsource.javax.annotation-1.0.0.jar@start, \
plugins/bundles/com.springsource.javax.ejb-3.0.0.jar@start, \
plugins/bundles/com.springsource.javax.el-1.0.0.jar@start, \
plugins/bundles/com.springsource.javax.mail-1.4.0.jar@start, \
plugins/bundles/com.springsource.javax.persistence-1.0.0.jar@start, \
plugins/bundles/com.springsource.javax.servlet.jsp.jstl-1.1.2.jar@start, \
plugins/bundles/com.springsource.javax.servlet.jsp-2.1.0.jar@start, \
plugins/bundles/com.springsource.javax.servlet-2.5.0.jar@start, \
plugins/bundles/com.springsource.javax.xml.bind-2.0.0.jar@start, \
plugins/bundles/com.springsource.javax.xml.stream-1.0.1.jar@start, \
plugins/bundles/com.springsource.javax.xml.rpc-1.1.0.jar@start, \
plugins/bundles/com.springsource.javax.xml.soap-1.3.0.jar@start, \
plugins/bundles/com.springsource.javax.xml.ws-2.1.1.jar@start, \
plugins/bundles/com.springsource.org.aopalliance-1.0.0.jar@start, \
plugins/bundles/com.springsource.org.apache.catalina-6.0.18.jar@start, \
plugins/bundles/com.springsource.org.apache.coyote-6.0.18.jar, \
plugins/bundles/com.springsource.org.apache.el-6.0.18.jar@start, \
plugins/bundles/com.springsource.org.apache.juli.extras-6.0.18.jar@start, \
plugins/bundles/com.springsource.org.apache.taglibs.standard-1.1.2.jar@start, \
plugins/bundles/catalina.start.osgi-1.0.0.jar@start, \
plugins/bundles/jasper.osgi-5.5.23-SNAPSHOT.jar@start, \
plugins/bundles/catalina-config-3.5.1.jar, \
plugins/jaxb/jaxb-impl-2.1.5_1.0.0.jar@start, \
plugins/db/hsqldb_1.0.0.jar@start, \
plugins/json/org.json_1.0.0.jar@start, \
plugins/json/gson-2.2.2.jar@start, \
plugins/json/jackson-core-asl-1.9.2.jar@start, \
plugins/json/jackson-mapper-asl-1.9.2.jar@start, \
plugins/spring 3.2.3/org.springframework.aop-3.2.3.RELEASE.jar@start, \
plugins/spring 3.2.3/org.springframework.aspects-3.2.3.RELEASE.jar@start, \
plugins/spring 3.2.3/org.springframework.beans-3.2.3.RELEASE.jar@start, \
plugins/spring 3.2.3/org.springframework.context.support-3.2.3.RELEASE.jar@start, \
plugins/spring 3.2.3/org.springframework.context-3.2.3.RELEASE.jar@start, \
plugins/spring 3.2.3/org.springframework.core-3.2.3.RELEASE.jar@start, \
plugins/spring 3.2.3/org.springframework.expression-3.2.3.RELEASE.jar@start, \
plugins/spring 3.2.3/org.springframework.jdbc-3.2.3.RELEASE.jar@start, \
plugins/spring 3.2.3/org.springframework.orm-3.2.3.RELEASE.jar@start, \
plugins/spring 3.2.3/org.springframework.oxm-3.2.3.RELEASE.jar@start, \
plugins/spring 3.2.3/org.springframework.transaction-3.2.3.RELEASE.jar@start, \
plugins/spring 3.2.3/org.springframework.web.servlet-3.2.3.RELEASE.jar@start, \
plugins/spring 3.2.3/org.springframework.web-3.2.3.RELEASE.jar@start, \
plugins/spring 3.2.3/org.springframework.security.config-3.1.4.RELEASE.jar@start, \
plugins/spring 3.2.3/org.springframework.security.core-3.1.4.RELEASE.jar@start, \
plugins/spring 3.2.3/org.springframework.security.web-3.1.4.RELEASE.jar@start, \
plugins/spring DM/spring-osgi-annotation-2.0.0.M1.jar@start, \
plugins/spring DM/spring-osgi-core-2.0.0.M1.jar@start, \
plugins/spring DM/spring-osgi-extender-2.0.0.M1.jar@start, \
plugins/spring DM/spring-osgi-io-2.0.0.M1.jar@start, \
plugins/spring DM/spring-osgi-web-2.0.0.M1.jar@start, \
plugins/spring DM/spring-osgi-web-extender-2.0.0.M1.jar@start, \
plugins/monitor/javamelodybundle_1.0.0.jar@start, \
plugins/monitor/jrobin_1.5.9.1.jar@start, \
plugins/bundles/guava-18.0.jar@start, \
plugins/broker/iotbroker.commons-4.3.3.jar@start, \
plugins/broker/iotbroker.storage-4.3.3.jar@start, \
plugins/broker/iotbroker.client-4.3.3.jar@start, \
plugins/broker/iotbroker.core-4.3.3.jar@start, \
plugins/broker/iotbroker.restcontroller-4.3.3.jar@start, \
plugins/broker/ngsi.api-4.3.3.jar@start, \
plugins/broker/iotbroker.ext.resultfilter-4.3.3.jar@start, \
plugins/broker/tomcat-configuration-fragment-4.3.3.jar, \
plugins/broker/iotbroker.couchdb-4.3.3.jar@start
```

Installing and Using the IoT Broker
---

Minimum System Requirements:
* Processor: 1 CPU 1.2 GHZ
* RAM: 1 GB
* DISK Space:50 MB
* JAVA : Java 7
* Operating System: 32 or 64-bit version Windows or Linux

The administration and programming manuals for the IoT Broker can be found on the FIWARE Catalogue page,
under the "Documentation" tab.

User and Programmers Guide: https://forge.fi-ware.org/plugins/mediawiki/wiki/fiware/index.php/IoT_Broker_-_User_and_Programmers_Guide

Installation and Administration Guide: https://forge.fi-ware.org/plugins/mediawiki/wiki/fiware/index.php/IoT_Broker_-_Installation_and_Administration_Guide

Pre-compiled and configured binaries: 
http://catalogue.fiware.org/enablers/iot-broker


Bugs & Questions
---
Please contact salvatore.longo@neclab.eu or tobias.jacobs@neclab.eu.
