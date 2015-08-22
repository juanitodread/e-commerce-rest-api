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
package org.juanitodread.ecommercerest.model.domain;

import javax.xml.bind.annotation.XmlEnum;

/**
 * Enumeration that represents the status of the Order.
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 22, 2015
 */
@XmlEnum
public enum OrderStatus {
    IN_TRANSIT( 1, "In transit" ), 
    FINISHED( 2, "Finished" ), 
    CANCELLED( 3, "Cancelled" );
    
    private int id;
    
    private String name;
    
    private OrderStatus( int id, String name ) {
        this.id = id;
        this.name = name;
    }

    public int getId( ) {
        return id;
    }

    public String getName( ) {
        return name;
    }
}
