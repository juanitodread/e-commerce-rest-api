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
import org.juanitodread.ecommercerest.model.domain.Customer;

/**
 * Simple Customer adapter for Document class of MongoDB.
 *
 * @author juanitodread
 * @version 1.0
 * 
 *          Aug 24, 2015
 */
public class CustomerAdapter {

    private CustomerAdapter( ) {

    }

    /**
     * Map a Customer object to a Mongo Document object.
     * 
     * @param c Customer
     * @return A Document object with all the information of the Customer object.
     */
    public static final Document toDocument( Customer c ) {
        Document d = new Document( "firstName", c.getFirstName( ) )
                        .append( "lastName", c.getLastName( ) )
                        .append( "street", c.getStreet( ) )
                        .append( "city", c.getCity( ) )
                        .append( "state", c.getState( ) )
                        .append( "zip", c.getZip( ) )
                        .append( "country", c.getCountry( ) );

        if ( c.getId( ) != null ) {
            d.append( "_id", new ObjectId( c.getId( ) ) );
        }
        return d;
    }

    /**
     * Map a Mongo Document object to a Customer object.
     * 
     * @param d Document
     * @return A Customer object with all the information of the Document object.
     */
    public static final Customer toCustomer( Document d ) {
        ObjectId id = d.get( "_id", ObjectId.class );
        Customer c = new Customer( id == null ? "" : id.toString( ),
                        d.getString( "firstName" ),
                        d.getString( "lastName" ),
                        d.getString( "street" ),
                        d.getString( "city" ),
                        d.getString( "state" ),
                        d.getString( "zip" ),
                        d.getString( "country" ) );

        return c;
    }

}
