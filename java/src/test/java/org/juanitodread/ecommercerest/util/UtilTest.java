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
package org.juanitodread.ecommercerest.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.juanitodread.ecommercerest.model.domain.Customer;
import org.juanitodread.ecommercerest.model.domain.Item;
import org.juanitodread.ecommercerest.model.domain.Order;
import org.juanitodread.ecommercerest.model.domain.OrderStatus;
import org.juanitodread.ecommercerest.model.domain.Product;

/**
 * Utility class for unit tests.
 *
 * @author juanitodread
 * @version 1.0
 * 
 *          Sep 3, 2015
 */
public class UtilTest {
    
    @SuppressWarnings( "serial" )
    private static final List<String> langs = new ArrayList<String>( ){{
        add("C");
        add("C++"); 
        add("Haskell");
        add("Java"); 
        add("Javascript");
        add("Scala"); 
    }};
    
    @SuppressWarnings( "serial" )
    private static final List<String> titles = new ArrayList<String>( ){{
        add("Professional ");
        add("How to program in "); 
        add("The art of programming in ");
        add("Master in "); 
    }};

    private UtilTest( ) {

    }
    
    /**
     * Utility method to create Order objects.
     * 
     * @return A Order object.
     */
    public static Order buildOrder( ) {
        Order o = new Order( getRandomDouble( ( int ) getRandomId( ) ),
                        new Date( ),
                        OrderStatus.IN_TRANSIT,
                        buildCustomer( ),
                        buildItems( ) );
        return o;
    }
    
    /**
     * Generates a list of items.
     * 
     * @return List<Item>.
     */
    public static List<Item> buildItems( ) {
        int itemCount = ( int ) getRandomId( );
        List<Item> items = new ArrayList<>( itemCount );

        for ( int ii = 0; ii < itemCount; ii++ ) {
            items.add( new Item( 2, buildProduct( ) ) );
        }

        return items;
    }
    
    /**
     * Utility method to create Customer objects.
     * 
     * @return A Customer object.
     */
    public static Customer buildCustomer( ) {
        Customer c = new Customer( "firstName", 
                                    "lastName",
                                    "street",
                                    "city",
                                    "state",
                                    "zip",
                                    "country" );
        return c;
    }

    /**
     * Utility method to create Product objects.
     * 
     * @return A Product object.
     */
    public static Product buildProduct( ) {
        Collections.shuffle( titles );
        Collections.shuffle( langs );
        Product p = new Product( titles.get( 0 ) + langs.get( 0 ),
                        getRandomDouble( ( int ) getRandomId( ) ) );
        return p;
    }
    
    
    /**
     * Generates a Random long from 0 to 1000.
     * 
     * @return A random long number
     */
    public static long getRandomId( ) {
        Random r = new Random( );
        int n = r.nextInt( 1000 );
        return n;
    }

    /**
     * Generates a Random double number.
     * 
     * @param id An integer.
     * @return A random double number
     */
    public static double getRandomDouble( int id ) {
        Random r = new Random( );
        double n = r.nextDouble( );
        return ( id * ( n + 1 ) ) + n;
    }
}
