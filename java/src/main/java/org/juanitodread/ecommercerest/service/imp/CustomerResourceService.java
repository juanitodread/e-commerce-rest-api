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
package org.juanitodread.ecommercerest.service.imp;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.juanitodread.ecommercerest.model.dao.CustomerDao;
import org.juanitodread.ecommercerest.model.dao.factory.DaoFactory;
import org.juanitodread.ecommercerest.model.dao.factory.DaoType;
import org.juanitodread.ecommercerest.model.dao.factory.imp.CustomerDaoFactory;
import org.juanitodread.ecommercerest.model.domain.Customer;
import org.juanitodread.ecommercerest.model.domain.Link;
import org.juanitodread.ecommercerest.service.CustomerResource;
import org.juanitodread.ecommercerest.util.Util;

/**
 * Customer service.
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 20, 2015
 */
@Path( "/customers" )
public class CustomerResourceService implements CustomerResource {
    private final CustomerDao dao;
    
    public CustomerResourceService(){
        final DaoFactory factory = new CustomerDaoFactory( );
        dao = ( CustomerDao ) factory.getDao( DaoType.MONGO_DB );
    }
    
    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.CustomerResource#getAllCustomers(int, int, javax.ws.rs.core.UriInfo)
     */
    @Override
    public Response getAllCustomers( int page,
                                     int size,
                                     UriInfo uriInfo ) {
        List<Customer> customers = null;
        ResponseBuilder responseBuilder = Response.status( Status.OK );
        String baseUri = uriInfo.getAbsolutePath( ).toString( );
        
        // If page is 0 (absent) then get all customers
        if( page == 0 ) {
            customers = dao.findAll( );
        } else {
            customers = dao.filterByRange( page, size );
            responseBuilder.header( "Link",
                             Util.buildFilterLinks( baseUri, page, size ) );
        }
        
        for( Customer c: customers ) {
            c.setLink( Util.buildSelfLink( baseUri, c.getId( ) ) );
        }
        
        GenericEntity<List<Customer>> entities = new GenericEntity<List<Customer>>( customers ) {
        };
        
        return responseBuilder.entity( entities ).build( );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.CustomerResource#getCustomerById(java.lang.String, javax.ws.rs.core.UriInfo)
     */
    @Override
    public Customer getCustomerById( String id,
                                     UriInfo uriInfo ) {
        Customer c = dao.findById( id );
        
        if( c == null ) {
            throw new WebApplicationException( Status.NOT_FOUND );
        }
        
        String baseUri = uriInfo.getAbsolutePath( ).toString( );
        c.setLink( new Link( "self", baseUri, "" ) );
        return c;
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.CustomerResource#createCustomer(org.juanitodread.ecommercerest.model.domain.Customer, javax.ws.rs.core.UriInfo)
     */
    @Override
    public Response createCustomer( Customer customer,
                                    UriInfo uriInfo ) {
        Customer newCustomer = dao.create( customer );
        String baseUri = uriInfo.getAbsolutePath( ).toString( );
        return Response
                .status( Status.CREATED )
                .header( "Link", Util.buildSelfLink( baseUri, newCustomer.getId( ) ) )
                .build( );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.CustomerResource#updateCustomer(org.juanitodread.ecommercerest.model.domain.Customer, javax.ws.rs.core.UriInfo)
     */
    @Override
    public Response updateCustomer( Customer customer,
                                    UriInfo uriInfo ) {
        dao.update( customer );
        String baseUri = uriInfo.getAbsolutePath( ).toString( );
        return Response
                .status( Status.OK )
                .header( "Link", new Link( "self", baseUri, "" ) ) 
                .build( );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.CustomerResource#removeCustomer(java.lang.String)
     */
    public Response removeCustomer( String id ) {
        dao.delete( id );
        return Response.status( Status.NO_CONTENT ).build( );
    }

}
