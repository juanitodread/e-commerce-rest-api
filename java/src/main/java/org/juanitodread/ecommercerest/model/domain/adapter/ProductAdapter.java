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
package org.juanitodread.ecommercerest.model.domain.adapter;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.juanitodread.ecommercerest.model.domain.Product;

/**
 * Simple Product adapter for Document class of Mongodb.
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 22, 2015
 */
public class ProductAdapter {
    
    private ProductAdapter( ) {

    }

    /**
     * Map a Product object to a Mongo Document object.
     * 
     * @param p Product
     * @return A Document object with all the information of the Product object.
     */
    public static final Document toDocument( Product p ) {
        Document d = new Document( "name", p.getName( ) )
                          .append( "price", p.getPrice( ) );
        if ( p.getId( ) != null ) {
            d.append( "_id", new ObjectId( p.getId( ) ) );
        }
        return d;
    }
    
    /**
     * Map a Mongo Document object to a Product object.
     * 
     * @param d Document
     * @return A Product object with all the information of the Document object.
     */
    public static final Product toProduct( Document d ) {
        ObjectId id = d.get( "_id", ObjectId.class );
        Product p = new Product( id == null ? "" : id.toString( ),
                        d.getString( "name" ),
                        d.getDouble( "price" ) );
        return p;
    }
    
}
