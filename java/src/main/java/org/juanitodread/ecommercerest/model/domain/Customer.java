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
 * Simple POJO to represent a customer
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 20, 2015
 */
@XmlRootElement
public class Customer implements Serializable {

    private static final long serialVersionUID = -6556822220891168194L;

    @XmlElement
    private String id;
    @XmlElement
    private String firstName;
    @XmlElement
    private String lastName;
    @XmlElement
    private String street;
    @XmlElement
    private String city;
    @XmlElement
    private String state;
    @XmlElement
    private String zip;
    @XmlElement
    private String country;

    
    /**
     * @param firstName
     * @param lastName
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param country
     */
    public Customer( String firstName,
            String lastName,
            String street,
            String city,
            String state,
            String zip,
            String country ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    /**
     * @param id
     * @param firstName
     * @param lastName
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param country
     */
    public Customer( String id,
            String firstName,
            String lastName,
            String street,
            String city,
            String state,
            String zip,
            String country ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public String getId( ) {
        return id;
    }

    public String getFirstName( ) {
        return firstName;
    }

    public String getLastName( ) {
        return lastName;
    }

    public String getStreet( ) {
        return street;
    }

    public String getCity( ) {
        return city;
    }

    public String getState( ) {
        return state;
    }

    public String getZip( ) {
        return zip;
    }

    public String getCountry( ) {
        return country;
    }

    @Override
    public String toString( ) {
        return String.format( "Customer [id=%s, firstName=%s, lastName=%s, street=%s, city=%s, state=%s, zip=%s, country=%s]",
                        id,
                        firstName,
                        lastName,
                        street,
                        city,
                        state,
                        zip,
                        country );
    }
    
}