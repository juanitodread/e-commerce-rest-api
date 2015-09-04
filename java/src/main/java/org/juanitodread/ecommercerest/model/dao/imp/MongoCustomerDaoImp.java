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
import org.juanitodread.ecommercerest.model.dao.CustomerDao;
import org.juanitodread.ecommercerest.model.domain.Customer;
import org.juanitodread.ecommercerest.model.domain.adapter.CustomerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Customer DAO for MongoDB.
 *
 * @author juanitodread
 * @version 1.0
 * 
 *          Aug 24, 2015
 */
public class MongoCustomerDaoImp implements CustomerDao {

    private static final Logger log = LoggerFactory.getLogger( MongoCustomerDaoImp.class );

    public static final String COLLECTION = "customers";

    private final MongoCollection<Document> customersCollection;

    public MongoCustomerDaoImp( final MongoDatabase customersDatabase ) {
        customersCollection = customersDatabase.getCollection( COLLECTION );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.CustomerDao#create(org.juanitodread.ecommercerest.model.domain.Customer)
     */
    public Customer create( Customer c ) {
        Document customer = CustomerAdapter.toDocument( c );
        customersCollection.insertOne( customer );
        Customer cust = CustomerAdapter.toCustomer( customer );
        log.info( "New customer created: {}", cust );
        return cust;
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.CustomerDao#update(org.juanitodread.ecommercerest.model.domain.Customer)
     */
    public void update( Customer c ) {
        customersCollection.replaceOne( eq( "_id", new ObjectId( c.getId( ) ) ),
                        CustomerAdapter.toDocument( c ) );
        log.info( "Customer {} updated", c );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.CustomerDao#delete(java.lang.String)
     */
    public void delete( String id ) {
        customersCollection.deleteOne( eq( "_id", new ObjectId( id ) ) );
        log.info( "Customer {} deleted", id );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.CustomerDao#findById(java.lang.String)
     */
    public Customer findById( String id ) {
        Document d = customersCollection.find( eq( "_id", new ObjectId( id ) ) )
                        .first( );
        Customer c = null;
        if ( d != null ) {
            c = CustomerAdapter.toCustomer( d );
            log.info( "Customer found by id: {}", c );
        } else {
            log.info( "Customer not found [id: {}]", id );
        }

        return c;
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.CustomerDao#findAll()
     */
    public List<Customer> findAll( ) {
        List<Document> docs = customersCollection.find( ).into( new ArrayList<Document>( ) );
        List<Customer> customers = new ArrayList<>( docs.size( ) );
        for ( Document d : docs ) {
            customers.add( CustomerAdapter.toCustomer( d ) );
        }
        log.info( "All customers found {}", customers );

        return customers;
    }
}
