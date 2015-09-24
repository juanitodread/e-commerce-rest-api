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

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.juanitodread.ecommercerest.model.domain.Link;

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
     * Build a self link for an entity.
     * 
     * @param url Base URL of the entity.
     * @param id The identifier of the entity.
     * @return A Link object with the self information of the entity.
     */
    public static Link buildSelfLink( String url,
                                      String id ) {
        Link self = new Link( "self", String.format( "%s/%s", url, id ), "" );
        return self;
    }
    
    /**
     * Build a list of links to navigate through a filtered set of data.
     * 
     * @param url Base URL of the entities.
     * @param page Page number used for filter the data.
     * @param size Size number used for filter the data.
     * @return A list of Link objects with the navigation of the filtered data.
     */
    public static List<Link> buildFilterLinks( String url,
                                               int page,
                                               int size ) {
        List<Link> links = new ArrayList<>( 3 );
        
        // first
        links.add( new Link( "first", String.format( "%s?page=1&size=%d", url,
                                                     size ), 
                                                     "" ) );
        
        // next
        links.add( new Link( "next", String.format( "%s?page=%s&size=%d", url,
                                                     page + 1, size ), 
                                                     "" ) );
        
        // prev
        links.add( new Link( "prev", String.format( "%s?page=%s&size=%d", url,
                                                    page - 1, size ), 
                                                    "" ) );
        
        return links;
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
