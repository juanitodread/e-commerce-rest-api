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
package org.juanitodread.ecommercerest.model.dao.factory;

import org.juanitodread.ecommercerest.model.dao.Dao;

/**
 * Generic DAO factory
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 23, 2015
 */
public interface DaoFactory {

    /**
     * Get a DAO according the DAO type.
     * 
     * @param type Type of the DAO to create.
     * @return A DAO for the type sent as parameter.
     */
    public Dao<?> getDao(final DaoType type);
    
}
