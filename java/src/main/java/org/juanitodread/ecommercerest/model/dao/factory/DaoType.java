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

/**
 * All DAO types implemented by the system.
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 23, 2015
 */
public enum DaoType {

    MONGO_DB( 1, "mongodb://localhost", "ecommerce" );
    
    private int id;
    
    private String uri;
    
    private String database;
    
    private DaoType( int id, String uri, String database ) {
        this.id = id;
        this.uri = uri;
        this.database = database;
    }

    public int getId( ) {
        return id;
    }

    public String getUri( ) {
        return uri;
    }

    public String getDatabase( ) {
        return database;
    }
}
