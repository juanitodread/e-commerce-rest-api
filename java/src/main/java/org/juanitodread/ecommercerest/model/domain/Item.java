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
 * Simple POJO to represent an item order.
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 20, 2015
 */
@XmlRootElement
public class Item implements Serializable {

    private static final long serialVersionUID = -5349483092067583129L;

    @XmlElement
    private long id;
    @XmlElement
    private int quantity;
    @XmlElement
    private Product product;

    public Item( ) {
    }

    /**
     * @param id
     * @param quantity
     * @param product
     */
    public Item( long id, int quantity, Product product ) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    public long getId( ) {
        return id;
    }

    public int getQuantity( ) {
        return quantity;
    }

    public Product getProduct( ) {
        return product;
    }

    @Override
    public String toString( ) {
        return String.format( "Item [id=%s, quantity=%s, product=%s]",
                id,
                quantity,
                product );
    }
}