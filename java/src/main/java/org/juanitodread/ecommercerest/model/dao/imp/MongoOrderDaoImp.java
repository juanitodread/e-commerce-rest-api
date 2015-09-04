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
import org.juanitodread.ecommercerest.model.dao.OrderDao;
import org.juanitodread.ecommercerest.model.domain.Order;
import org.juanitodread.ecommercerest.model.domain.adapter.OrderAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Order DAO for MongoDB.
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 31, 2015
 */
public class MongoOrderDaoImp implements OrderDao {
    
    private static final Logger log = LoggerFactory.getLogger( MongoOrderDaoImp.class );

    public static final String COLLECTION = "orders";
    
    private final MongoCollection<Document> ordersCollection;
    
    public MongoOrderDaoImp( final MongoDatabase ordersDatabase ) {
        ordersCollection = ordersDatabase.getCollection( COLLECTION );
    }
    
    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.OrderDao#create(org.juanitodread.ecommercerest.model.domain.Order)
     */
    public Order create( Order o ) {
        Document order = OrderAdapter.toDocument( o );
        ordersCollection.insertOne( order );
        Order ord = OrderAdapter.toOrder( order );
        log.info( "New order created: {}", ord );
        return ord;
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.OrderDao#update(org.juanitodread.ecommercerest.model.domain.Order)
     */
    public void update( Order o ) {
        ordersCollection.replaceOne( eq( "_id", new ObjectId( o.getId( ) ) ),
                                    OrderAdapter.toDocument( o ) );
        log.info( "Order {} updated", o );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.OrderDao#delete(java.lang.String)
     */
    public void delete( String id ) {
        ordersCollection.deleteOne( eq( "_id", new ObjectId( id ) ) );
        log.info( "Order {} deleted", id );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.OrderDao#findById(java.lang.String)
     */
    public Order findById( String id ) {
        Document d = ordersCollection.find( eq( "_id", new ObjectId( id ) ) )
                        .first( );
        Order o = null;
        if ( d != null ) {
            o = OrderAdapter.toOrder( d );
            log.info( "Order found by id: {}", o );
        } else {
            log.info( "Order not found [id: {}]", id );
        }

        return o;
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.OrderDao#findAll()
     */
    public List<Order> findAll( ) {
        List<Document> docs = ordersCollection.find( ).into( new ArrayList<Document>( ) );
        List<Order> orders = new ArrayList<>( docs.size( ) );
        for ( Document d : docs ) {
            orders.add( OrderAdapter.toOrder( d ) );
        }
        log.info( "All orders found {}", orders );

        return orders;
    }

}
