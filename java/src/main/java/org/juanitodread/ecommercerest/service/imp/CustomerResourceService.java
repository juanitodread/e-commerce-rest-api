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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.juanitodread.ecommercerest.model.dao.CustomerDao;
import org.juanitodread.ecommercerest.model.dao.factory.DaoFactory;
import org.juanitodread.ecommercerest.model.dao.factory.DaoType;
import org.juanitodread.ecommercerest.model.dao.factory.imp.CustomerDaoFactory;
import org.juanitodread.ecommercerest.model.domain.Customer;
import org.juanitodread.ecommercerest.service.CustomerResource;

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
     * @see org.juanitodread.ecommercerest.service.CustomerResource#getAllCustomers()
     */
    public List<Customer> getAllCustomers( ) {
        return dao.findAll( );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.CustomerResource#getCustomerById(java.lang.String)
     */
    public Customer getCustomerById( String id ) {
        return dao.findById( id );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.CustomerResource#createCustomer(org.juanitodread.ecommercerest.model.domain.Customer)
     */
    public Response createCustomer( Customer customer ) {
        Customer newCustomer = dao.create( customer );
        return Response.status( Status.CREATED).entity( newCustomer ).build( );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.CustomerResource#updateCustomer(org.juanitodread.ecommercerest.model.domain.Customer)
     */
    public Response updateCustomer( Customer customer ) {
        dao.update( customer );
        String message = String.format( "Customer [%s] has been updated", customer.getId( ) );
        return Response.status( Status.OK ).entity( message ).build( );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.CustomerResource#removeCustomer(java.lang.String)
     */
    public Response removeCustomer( String id ) {
        dao.delete( id );
        return Response.status( Status.NO_CONTENT ).build( );
    }

}
