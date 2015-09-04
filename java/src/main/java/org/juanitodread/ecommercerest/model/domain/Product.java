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

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple POJO to represent a product in an order.
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 20, 2015
 */
@XmlRootElement
public class Product implements Serializable {

    private static final long serialVersionUID = -1656888921302078194L;

    @XmlElement
    private String id;
    @XmlElement
    private String name;
    @XmlElement
    private double price;
    
    /**
     * @param name
     * @param price
     */
    public Product(String name, double price ) {
        this.name = name;
        this.price = price;
    }

    /**
     * @param id
     * @param name
     * @param price
     */
    public Product( String id, String name, double price ) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId( ) {
        return id;
    }

    public String getName( ) {
        return name;
    }

    public double getPrice( ) {
        return price;
    }

    @Override
    public String toString( ) {
        return String.format( "Product [id=%s, name=%s, price=%s]",
                id,
                name,
                price );
    }
}