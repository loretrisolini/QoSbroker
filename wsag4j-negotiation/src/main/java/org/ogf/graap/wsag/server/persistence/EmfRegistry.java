/* 
 * Copyright (c) 2005-2011, Fraunhofer-Gesellschaft
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 * 
 * (1) Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the disclaimer at the end.
 *     Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in
 *     the documentation and/or other materials provided with the
 *     distribution.
 * 
 * (2) Neither the name of Fraunhofer nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 * 
 * DISCLAIMER
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 */
package org.ogf.graap.wsag.server.persistence;

import java.text.MessageFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.ogf.graap.wsag.api.logging.LogMessage;

/**
 * @author T.Weuffel
 */
public class EmfRegistry
{

    private static final Logger LOG = Logger.getLogger( EmfRegistry.class );

    /**
     * System property to set the WSAG4J database location.
     */
    public static final String WSAG4J_DATAPATH = "org.wsag4j.persistence.datapath";

    /**
     * Default location of the wsag4j hsql database.
     */
    public static final String WSAG4J_DATAPATH_DEFAULT = System.getProperty( "java.io.tmpdir" )
        + "/wsag-data";

    /**
     * Persistence mode memory
     * 
     * <p>
     * This mode starts wsag4j with an in-memory database. It supports persistence for the wsag4j-server and
     * wsag4j-webservice modules. Since an in-memory db is used agreements will not be stored on disk. After
     * restarting the wsag4j engine all agreements disappear. This mode is usually used for unit testing.
     * </p>
     * 
     */
    public static final String PERSISTENCE_MODE_MEM = "MEM";

    /**
     * Persistence unit name for memory mode (see wsag4j-server-resources /META-INF/persistence.xml)
     */
    public static final String PERSISTENCE_MODE_MEM_PU_NAME = "wsag4j_mem";

    /**
     * Persistence mode memory for server tests
     * 
     * <p>
     * This mode starts wsag4j with an in-memory database. It supports persistence for the wsag4j-server
     * module only. Since an in-memory db is used agreements will not be stored on disk. After restarting the
     * wsag4j engine all agreements disappear. This mode is usually used for unit testing.
     * </p>
     */
    public static final String PERSISTENCE_MODE_MEM_SERVER = "MEM_SERVER";

    /**
     * Persistence unit name for memory mode for server tests (see wsag4j-server-resources
     * /META-INF/persistence.xml)
     */
    public static final String PERSISTENCE_MODE_MEM_SERVER_PU_NAME = "wsag4j_mem_server";

    /**
     * Persistence mode file
     * 
     * <p>
     * This mode starts wsag4j with an file database. It supports persistence for the wsag4j-server and
     * wsag4j-webservice modules. Since an file db is used agreements will be stored on disk. After restarting
     * the wsag4j engine all agreements are reloaded. This mode is usually used for productive mode.
     * </p>
     * 
     * <p>
     * <b>Important:</b> Make sure that the <code>org.wsag4j.persistence.datapath</code> system property is
     * set to the correct location of the hsqldb database directory. Otherwise the database will be stored in
     * {java.io.tmpdir}/wsag-data
     * </p>
     */
    public static final String PERSISTENCE_MODE_FILE = "FILE";

    /**
     * Persistence unit name for file mode (see wsag4j-server-resources /META-INF/persistence.xml)
     */
    public static final String PERSISTENCE_MODE_FILE_PU_NAME = "wsag4j_file";

    //public static final String PERSISTENCE_MODE_FILE_PU_NAME = "taas-jpa";

    private static EntityManagerFactory emf;

    private static String persistenceMode = PERSISTENCE_MODE_FILE;
    

    /**
     * Sets the persistence mode.
     * 
     * @param persistenceMode
     *            the persistence mode to set
     */
    public static void setPersistenceMode( String persistenceMode )
    {
        EmfRegistry.persistenceMode = persistenceMode;

        LOG.debug( LogMessage.getMessage( "Set wsag4j persistence mode to: {0}", persistenceMode ) );
    }

    /**
     * Returns an entity manager factory, if no instance exists a new is created.
     * 
     * @return the entity manager factory
     */
    private static synchronized EntityManagerFactory getEntityManagerFactory()
    {

        // if no EMF exists, create a new one
        if ( emf == null )
        {
            createEmf();

            LOG.info( "No EMF instance exists. Created new instance." );

            return emf;
        }

        // if EMF was closed, create a new one
        if ( !emf.isOpen() )
        {
            createEmf();

            if ( LOG.isInfoEnabled() )
            {
                LOG.info( "Former EMF instance is/was closed. Created a new instance." );
            }
        }

        return emf;
    }

    private static void createEmf()
    {
        //
        // Fixes shutdown errors in Tomcat for HSQL DB
        //

        // System.setProperty("org.quartz.scheduler.makeSchedulerThreadDaemon", "true");
        // System.setProperty("org.quartz.threadPool.makeThreadsDaemons", "true");

        //
        // initialize DB
        //
        String configuredPersistenceMode = System.getProperty( "wsag4j.persistence.mode" );
        if ( configuredPersistenceMode != null )
        {
            persistenceMode = configuredPersistenceMode;

            if ( LOG.isInfoEnabled() )
            {
                LOG.info( LogMessage.getMessage(
                    "Set wsag4j persistence mode to: {0} (by System.getProperty)", persistenceMode ) );
            }
        }

        if ( persistenceMode.equals( PERSISTENCE_MODE_MEM ) )
        {
            emf = Persistence.createEntityManagerFactory( PERSISTENCE_MODE_MEM_PU_NAME );
        }
        if ( persistenceMode.equals( PERSISTENCE_MODE_MEM_SERVER ) )
        {
            emf = Persistence.createEntityManagerFactory( PERSISTENCE_MODE_MEM_SERVER_PU_NAME );
        }
        else if ( persistenceMode.equals( PERSISTENCE_MODE_FILE ) )
        {
            // check WSAG4J_DATAPATH configuration
            String configuredDataPath =
                System.getProperties().getProperty( WSAG4J_DATAPATH, WSAG4J_DATAPATH_DEFAULT );
            if ( !"".equals( configuredDataPath ) )
            {

                if ( !( configuredDataPath.endsWith( "/" ) || configuredDataPath.endsWith( "\\" ) ) )
                {
                    configuredDataPath += "/";
                }
                LOG.debug("EmfRegistry LOG");
                LOG.debug(persistenceMode);
                LOG.debug( LogMessage.getMessage( "WSAG4J persistence data path configuration: {0}",
                    configuredDataPath ) );
            }
            else
            {
                String message =
                    MessageFormat.format(
                        "No wsag4j persistence data path configured. Use default location {0}",
                        WSAG4J_DATAPATH_DEFAULT );
                LOG.warn( message );
            }

            //
            // set data path in system properties so that HSQLDB can write the database to the
            // configured/default location
            //
            System.setProperty( WSAG4J_DATAPATH, configuredDataPath );

            //
            // try to create the file-based entity manager factory
            //
            emf = Persistence.createEntityManagerFactory( PERSISTENCE_MODE_FILE_PU_NAME );
        }
        else
        {
            LOG.error( "No persistence operation mode configured." );
        }
    }

    /**
     * Creates a new entity manager.
     * 
     * @return the new entity manager
     */
    public static EntityManager getEntityManager()
    {
        return EmfRegistry.getEntityManagerFactory().createEntityManager();
    }

    /**
     * Returns the operation mode info message.
     * 
     * @return info message
     */
    public static String printInfo()
    {
        return MessageFormat.format(
            "EmfRegistry [EmfRegistry.persistenceMode: ''{0}'', WSAG4J_DATAPATH: ''{1}'']",
            EmfRegistry.persistenceMode, WSAG4J_DATAPATH );
    }

    /**
     * Closes the entity manager factory and all entity managers.
     */
    public static synchronized void finalizeEmfRegistry()
    {
        if ( emf != null )
        {
            synchronized ( emf )
            {
                if ( emf.isOpen() )
                {
                    emf.close();
                }
                emf = null;
            }
        }
    }
}
