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

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.juanitodread.ecommercerest.model.dao.ProductDao;
import org.juanitodread.ecommercerest.model.domain.Product;
import org.juanitodread.ecommercerest.model.domain.adapter.ProductAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Product DAO for MongoDB.
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 22, 2015
 */
public class MongoProductDaoImp implements ProductDao {
    
    private static final Logger log = LoggerFactory.getLogger( MongoProductDaoImp.class );
    
    public static final String COLLECTION = "products";
    
    private final MongoCollection<Document> productsCollection;
    
    public MongoProductDaoImp( final MongoDatabase productsDatabase ) {
        productsCollection = productsDatabase.getCollection( COLLECTION );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.ProductDao#create(org.juanitodread.ecommercerest.model.domain.Product)
     */
    public Product create( Product p ) {
        Document product = ProductAdapter.toDocument( p );
        productsCollection.insertOne( product );
        Product prod = ProductAdapter.toProduct( product );
        log.info( "New product created: {}", prod );
        return prod;
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.ProductDao#update(org.juanitodread.ecommercerest.model.domain.Product)
     */
    public void update( Product p ) {
        productsCollection.replaceOne( eq( "_id", new ObjectId( p.getId( ) ) ),
                                    ProductAdapter.toDocument( p ) );
        log.info( "Product {} updated", p );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.ProductDao#delete(java.lang.String)
     */
    public void delete( String id ) {
        productsCollection.deleteOne( eq( "_id", new ObjectId( id ) ) );
        log.info( "Product {} deleted", id );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.ProductDao#findById(java.lang.String)
     */
    public Product findById( String id ) {
        Document dp = productsCollection.find( eq( "_id", new ObjectId( id ) ) )
                        .first( );
        Product p = null;
        p = ProductAdapter.toProduct( dp );
        if ( dp != null ) {
            p = ProductAdapter.toProduct( dp );
            log.info( "Product found by id: {}", p );
        } else {
            log.info( "Product not found [id: {}]", id );
        }

        return p;
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.ProductDao#findAll()
     */
    public List<Product> findAll( ) {
        List<Document> docs = productsCollection.find( )
                                .into( new ArrayList<Document>( ) );
        List<Product> products = new ArrayList<>( docs.size( ) );
        for ( Document d : docs ) {
            products.add( ProductAdapter.toProduct( d ) );
        }
        log.info( "All products found {}", products );
        
        return products;
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.ProductDao#filterByRange(int, int)
     */
    @Override
    public List<Product> filterByRange( int page,
                                        int size ) {
        List<Document> docs = productsCollection.find( )
                                    .skip( size * ( page - 1 ) ).limit( size )
                                    .into( new ArrayList<Document>( ) );
        List<Product> products = new ArrayList<>( docs.size( ) );
        for( Document d: docs ) {
            products.add( ProductAdapter.toProduct( d ) );
        }
        log.info( "The filter(page={}, size{}) was used and these products were found {}",
                  page, size, products );
        
        return products;
    }
}
