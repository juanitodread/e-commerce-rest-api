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
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple POJO to represent an order.
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 22, 2015
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class Order implements Serializable {

    private static final long serialVersionUID = -5232687450430353235L;
    
    @XmlElement
    private String id;
    @XmlElement
    private double total;
    @XmlElement
    private Date date;
    @XmlElement
    private OrderStatus status;
    @XmlElement
    private Customer customer;
    @XmlElement
    private Link link;
    @XmlElement
    private List<Item> items;
    
    public Order() {
    }
    
    /**
     * @param total
     * @param date
     * @param status
     * @param customer
     * @param items
     */
    public Order( double total,
                  Date date,
                  OrderStatus status,
                  Customer customer,
                  List<Item> items ) {
        this.total = total;
        this.date = date;
        this.status = status;
        this.customer = customer;
        this.items = items;
    }

    /**
     * @param id
     * @param total
     * @param date
     * @param status
     * @param customer
     * @param items
     */
    public Order( String id,
                  double total,
                  Date date,
                  OrderStatus status,
                  Customer customer,
                  List<Item> items ) {
        this.id = id;
        this.total = total;
        this.date = date;
        this.status = status;
        this.customer = customer;
        this.items = items;
    }

    public String getId( ) {
        return id;
    }

    public double getTotal( ) {
        return total;
    }

    public Date getDate( ) {
        return date;
    }

    public OrderStatus getStatus( ) {
        return status;
    }

    public Customer getCustomer( ) {
        return customer;
    }

    public List<Item> getItems( ) {
        return items;
    }
    
    public double calculateTotal( ) {
        total = 0;
        for( Item item: items ) {
            total += item.getQuantity( ) * item.getProduct( ).getPrice( );
        }
        return total;
    }
    
    /**
     * @return the self
     */
    public Link getLink( ) {
        return this.link;
    }
   
    /**
     * @param self the self to set
     */
    public void setLink( Link link ) {
        this.link = link;
    }

    @Override
    public String toString( ) {
        return String.format( "Order [id=%s, total=%s, date=%s, status=%s, customer=%s, link=%s, items=%s]",
                        id,
                        total,
                        date,
                        status,
                        customer,
                        link,
                        items );
    }
    
    
}
