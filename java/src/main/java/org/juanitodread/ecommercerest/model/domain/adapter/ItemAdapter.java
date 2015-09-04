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
import org.juanitodread.ecommercerest.model.domain.Item;

/**
 * Simple Item adapter for Document class MongoDB.
 *
 * @author juanitodread
 * @version 1.0
 * 
 *          Sep 2, 2015
 */
public class ItemAdapter {

    private ItemAdapter( ) {
        
    }

    /**
     * Map an Item object to a Mongo Document object.
     * 
     * @param i Item
     * @return A Document object with all the information of the Item object.
     */
    public static final Document toDocument( Item i ) {
        Document p = ProductAdapter.toDocument( i.getProduct( ) );
        p.remove( "_id" );
        
        Document d = new Document( "quantity", i.getQuantity( ) )
                        .append( "product", p );

        if ( i.getId( ) != null ) {
            d.append( "_id", new ObjectId( i.getId( ) ) );
        }

        return d;
    }
    
    /**
     * Map a Mongo Document object to a Item object.
     * 
     * @param d Document
     * @return A Item object with all the information of the Document object.
     */
    public static final Item toItem( Document d ) {
        ObjectId id = d.get( "_id", ObjectId.class );
        Document p = d.get( "product", Document.class );
        Item c = new Item( id == null ? "" : id.toString( ),
                        d.getInteger( "quantity" ),
                        ProductAdapter.toProduct( p ) );
        return c;
    }
    
}
