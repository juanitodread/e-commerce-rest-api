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
package org.juanitodread.ecommercerest.model.dao.imp;

import java.util.List;

import org.juanitodread.ecommercerest.model.dao.CustomerDao;
import org.juanitodread.ecommercerest.model.domain.Customer;
import org.juanitodread.ecommercerest.util.UtilTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * MongoCustomerDaoImp test suite
 *
 * @author juanitodread
 * @version 1.0
 * 
 *          Sep 3, 2015
 */
public class MongoCustomerDaoImpTest {
    private static final Logger log = LoggerFactory.getLogger( MongoCustomerDaoImpTest.class );
    final String mongoUriString = "mongodb://localhost";
    
    String id = "";

    CustomerDao customerDao = null;
    
    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass( ) throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp( ) throws Exception {
        @SuppressWarnings( "resource" )
        MongoClient mongoClient = new MongoClient( new MongoClientURI( mongoUriString ) );
        customerDao = new MongoCustomerDaoImp( mongoClient.getDatabase( "ecommerce" ) );
    }
    
    /**
     * Test Product CRUD
     */
    @Test
    public final void testCRUD( ) {
        // Create customer
        Customer c = UtilTest.buildCustomer( );
        c = customerDao.create( c );
        log.info( "Customer created: {}", c );

        String id = c.getId( );

        // Get customer by id
        c = null;
        c = customerDao.findById( id );
        log.info( "Customer findById: {}", c );

        // Update customer
        c = new Customer( c.getId( ), 
                "updatedFirstName",
                "updatedLastName",
                "updatedStreet",
                "updatedCity",
                "updatedState",
                "updatedZip",
                "updatedCountry" );
        customerDao.update( c );
        log.info( "Customer updated: {}", c );

        // Get all customers
        List<Customer> customers = customerDao.findAll( );
        log.info( "Customer findAll" );
        for ( Customer cc : customers ) {
            log.info( "Customer: {}", cc );
        }

        // Remove customer
        customerDao.delete( id );
        log.info( "Customer delete: {}", id );

        // Get all customers
        customers = customerDao.findAll( );
        log.info( "Customer findAll" );
        for ( Customer cc : customers ) {
            log.info( "Customer: {}", cc );
        }
    }
}
