/* 
 * Copyright (c) 2007, Fraunhofer-Gesellschaft
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
package org.ogf.graap.wsag.server.api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * WsagSessionManager
 * 
 * @author Oliver Waeldrich
 * 
 */
public class WsagSessionManager
{

    private static Map<String, WsagSession> sessions = new HashMap<String, WsagSession>();

    /**
     * Creates a new WSAG4J session
     * 
     * @return the session
     */
    public static synchronized WsagSession createSession()
    {

        String id = UUID.randomUUID().toString();

        while ( sessions.containsKey( id ) )
        {
            id = UUID.randomUUID().toString();
        }

        WsagSession session = new WsagSession( id );
        sessions.put( id, session );

        return session;
    }

    /**
     * Returns a WSAG4J session with the given id.
     * 
     * @param id
     *            the session id
     * 
     * @return the WSAG4J session
     */
    public static WsagSession getSession( String id )
    {
        return sessions.get( id );
    }
}
