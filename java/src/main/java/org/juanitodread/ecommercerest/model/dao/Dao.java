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

import java.io.Serializable;
import java.util.List;

/**
 * Generic DAO interface.
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 23, 2015
 */
public interface Dao<T extends Serializable> {

    /**
     * Create a new entity in the store.
     * 
     * @param o Entity to be stored.
     * @return The entity stored.
     */
    public T create( T o );

    /**
     * Update an existent entity in the store with new values of the
     * entity
     * 
     * @param o Entity to be updated
     */
    public void update( T o );

    /**
     * Delete an existing entity in the store
     * 
     * @param id The identifier of the entity to be deleted from the store
     */
    public void delete( final String id );

    /**
     * Get an entity by its identifier from the store
     * 
     * @param id Identifier of the entity
     * @return The entity or null if the entity is not found
     */
    public T findById( final String id );

    /**
     * Get all the entities from the store.
     * 
     * @return A list of entities
     */
    public List<T> findAll( );
    
    
    /**
     * Filter a set of entities from the store using a numeric range.
     * 
     * @param page Represents where will start to get entities from the store.
     * @param size Represents the number of entities to get from the store.
     * @return A list of entities
     */
    public List<T> filterByRange( int page, int size );
}
