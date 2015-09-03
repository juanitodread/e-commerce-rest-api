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

import org.juanitodread.ecommercerest.model.domain.Product;

/**
 * DAO actions supported for Products.
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 22, 2015
 */
public interface ProductDao extends Dao<Product> {

    /**
     * Create a new product in the store.
     * 
     * @param p Product to create
     * @return The product created with its new id.
     */
    public Product create( Product p );

    /** 
     * Update a product in the store.
     * 
     * @param p The product with the new information.
     */
    public void update( Product p );

    /**
     * Remove a product of the store.
     * 
     * @param id The identifier of the product to remove.
     */
    public void delete( String id );

    /**
     * Get a product by its identifier.
     * 
     * @param id The identifier of the product.
     * @return The product found or null if the product is not found.
     */
    public Product findById( String id );

    /**
     * Get all the Products from the store.
     * 
     * @return A list with all the products from the store.
     */
    public List<Product> findAll( );

}
