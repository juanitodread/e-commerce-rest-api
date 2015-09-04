/**
 * e-commerce-rest-java
 *
 * Copyright 2015 juanitodread
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.juanitodread.ecommercerest.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Utility class.
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 22, 2015
 */
public class Util {

    private Util() {
        
    }
    
    /**
     * Creates a generic error Response for exceptions thrown in the application.
     * 
     * @param ex Exception
     * @return A Response object
     */
    public static Response buildGenericExceptionResponse( Exception ex ) {
        return Response.status( Status.NOT_FOUND )
                       .entity( ex.getMessage( ) )
                       .type( "text/plain" ).build( );
    }
    
}
