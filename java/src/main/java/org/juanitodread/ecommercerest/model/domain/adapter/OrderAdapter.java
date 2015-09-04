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

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.juanitodread.ecommercerest.model.domain.Item;
import org.juanitodread.ecommercerest.model.domain.Order;
import org.juanitodread.ecommercerest.model.domain.OrderStatus;

/**
 * Simple Order adapter for Document class of MongoDB
 *
 * @author juanitodread
 * @version 1.0
 * 
 *          Sep 2, 2015
 */
public class OrderAdapter {

    private OrderAdapter( ) {
        
    }
    
    /**
     * Map an Order object to a Mongo Document object.
     * 
     * @param o Order
     * @return A Document object with all the information of the Order.
     */
    public static final Document toDocument(Order o){
        Document c = CustomerAdapter.toDocument( o.getCustomer( ) );
        c.remove( "_id" );
        
        List<Document> itemsDoc = new ArrayList<>( o.getItems( ).size( ) );
        Document iDoc = null;
        
        for ( Item i : o.getItems( ) ) {
            iDoc = ItemAdapter.toDocument( i );
            iDoc.remove( "_id" );
            itemsDoc.add( iDoc );
        }
        
        Document d = new Document( "total", o.getTotal( ) )
                            .append( "date", o.getDate( ) )
                            .append( "status", o.getStatus( ).getName( ) )
                            .append( "customer", c )
                            .append( "items", itemsDoc );

        if ( o.getId( ) != null ) {
            d.append( "_id", new ObjectId( o.getId( ) ) );
        }
        return d;
    }
    
    /**
     * Map a Mongo Document object to an Order object.
     * 
     * @param d Document
     * @return An Order object with all the information of the Document object.
     */
    public static final Order toOrder( Document d ) {
        ObjectId id = d.get( "_id", ObjectId.class );

        Document c = d.get( "customer", Document.class );
        @SuppressWarnings( "unchecked" )
        List<Document> itemsDoc = ( List<Document> ) d.get( "items" );
        List<Item> items = new ArrayList<>( itemsDoc.size( ) );
        for ( Document itemDoc : itemsDoc ) {
            items.add( ItemAdapter.toItem( itemDoc ) );
        }       
        
        Order o = new Order( id == null ? "": id.toString( ),
                        d.getDouble( "total" ),
                        d.getDate( "date" ),
                        OrderStatus.findOrderStatus( d.getString( "status" ) ),
                        CustomerAdapter.toCustomer( c ),
                        items );

        return o;
    }

}
