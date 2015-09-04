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

import java.util.Date;
import java.util.List;

import org.juanitodread.ecommercerest.model.dao.OrderDao;
import org.juanitodread.ecommercerest.model.domain.Order;
import org.juanitodread.ecommercerest.model.domain.OrderStatus;
import org.juanitodread.ecommercerest.util.UtilTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * MongoOrderDaoImp test suite
 *
 * @author juanitodread
 * @version 1.0
 * 
 *          Sep 3, 2015
 */
public class MongoOrderDaoImpTest {
    private static final Logger log = LoggerFactory.getLogger( MongoOrderDaoImpTest.class );
    final String mongoUriString = "mongodb://localhost";
    
    String id = "";

    OrderDao orderDao = null;
    
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
        orderDao = new MongoOrderDaoImp( mongoClient.getDatabase( "ecommerce" ) );
    }
    
    /**
     * Test Product CRUD
     */
    @Test
    public final void testCRUD( ) {
        // Create order
        Order o = UtilTest.buildOrder( );
        o = orderDao.create( o );
        log.info( "Order created: {}", o );

        String id = o.getId( );

        // Get order by id
        o = null;
        o = orderDao.findById( id );
        log.info( "Order findById: {}", o );

        // Update order
        o = new Order( o.getId( ), 
                       o.getTotal( ) / 41, 
                       new Date( ), 
                       OrderStatus.CANCELLED, 
                       UtilTest.buildCustomer( ), 
                       UtilTest.buildItems( ) );
        orderDao.update( o );
        log.info( "Order updated: {}", o );

        // Get all orders
        List<Order> orders = orderDao.findAll( );
        log.info( "Order findAll" );
        for ( Order oo : orders ) {
            log.info( "Order: {}", oo );
        }

        // Remove order
        orderDao.delete( id );
        log.info( "Order delete: {}", id );

        // Get all orders
        orders = orderDao.findAll( );
        log.info( "Order findAll" );
        for ( Order oo : orders ) {
            log.info( "Order: {}", oo );
        }
    }
}
