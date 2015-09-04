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

import org.juanitodread.ecommercerest.model.dao.ProductDao;
import org.juanitodread.ecommercerest.model.domain.Product;
import org.juanitodread.ecommercerest.util.UtilTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * MondoProductDaoImp test suite
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 22, 2015
 */
public class MongoProductDaoImpTest {
    private static final Logger log = LoggerFactory.getLogger( MongoProductDaoImpTest.class );
    final String mongoUriString = "mongodb://localhost";
    
    String id = "";

    ProductDao productDao = null;

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
        productDao = new MongoProductDaoImp( mongoClient.getDatabase( "ecommerce" ) );
    }
    
    /**
     * Test Product CRUD
     */
    @Test
    public final void testCRUD( ) {
        // Create product
        Product p = UtilTest.buildProduct( );
        p = productDao.create( p );
        log.info( "Product created: {}", p );

        String id = p.getId( );

        // Get product by id
        p = null;
        p = productDao.findById( id );
        log.info( "Product findById: {}", p );

        // Update product
        p = new Product( p.getId( ), p.getName( ), 45.50 );
        productDao.update( p );
        log.info( "Product updated: {}", p );

        // Get all products
        List<Product> products = productDao.findAll( );
        log.info( "Product findAll" );
        for ( Product pp : products ) {
            log.info( "Product: {}", pp );
        }

        // Remove product
        productDao.delete( id );
        log.info( "Product delete: {}", id );

        // Get all products
        products = productDao.findAll( );
        log.info( "Product findAll" );
        for ( Product pp : products ) {
            log.info( "Product: {}", pp );
        }
    }
}
