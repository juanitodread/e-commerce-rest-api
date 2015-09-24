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
package org.juanitodread.ecommercerest.model.dao;

import java.util.List;

import org.juanitodread.ecommercerest.model.domain.Order;

/**
 * DAO actions supported for Orders.
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 22, 2015
 */
public interface OrderDao extends Dao<Order> {
    /**
     * Create a new order in the store.
     * 
     * @param o Order to be created.
     * @return The order with its new id.
     */
    public Order create( Order o );

    /**
     * Update an existent order in the store with new values of the
     * order
     * 
     * @param o Order to be updated
     */
    public void update( Order o );

    /**
     * Delete an existing order in the store
     * 
     * @param id The identifier of the order to be deleted from the store
     */
    public void delete( final String id );

    /**
     * Get an order by its identifier from the store
     * 
     * @param id Identifier of the order
     * @return The order or null if the entity is not found
     */
    public Order findById( final String id );

    /**
     * Get all the orders from the store.
     * 
     * @return A list of orders
     */
    public List<Order> findAll( );
    
    /**
     * Filter a set of orders from the store using a numeric range.
     * 
     * @param page Represents where will start to get orders from the store.
     * @param size Represents the number of orders to get from the store.
     * @return A list of orders
     */
    public List<Order> filterByRange( int page, int size );
}
