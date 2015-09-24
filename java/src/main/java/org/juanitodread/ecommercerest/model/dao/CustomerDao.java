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

import org.juanitodread.ecommercerest.model.domain.Customer;

/**
 * DAO actions supported for Customers.
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 22, 2015
 */
public interface CustomerDao extends Dao<Customer> {

    /**
     * Create a new customer in the store.
     * 
     * @param c Customer to be created.
     * @return The customer created with its new id.
     */
    public Customer create( Customer c );

    /** 
     * Update a customer in the store.
     * 
     * @param c The customer with the new information.
     */
    public void update( Customer c );

    /**
     * Remove a customer of the store.
     * 
     * @param id The identifier of the customer to remove.
     */
    public void delete( String id );

    /**
     * Get a customer by its identifier.
     * 
     * @param id The identifier of the customer.
     * @return The customer found or null if the customer is not found.
     */
    public Customer findById( String id );

    /**
     * Get all the Customers from the store.
     * 
     * @return A list with all the customers from the store.
     */
    public List<Customer> findAll( );
    
    /**
     * Filter a set of customers from the store using a numeric range.
     * 
     * @param page Represents where will start to get customers from the store.
     * @param size Represents the number of customers to get from the store.
     * @return A list of customers
     */
    public List<Customer> filterByRange( int page, int size );
    
}
