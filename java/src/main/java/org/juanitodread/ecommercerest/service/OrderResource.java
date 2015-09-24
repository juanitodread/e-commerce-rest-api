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
package org.juanitodread.ecommercerest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.juanitodread.ecommercerest.model.domain.Order;

/**
 * Resource controller for Order
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 31, 2015
 */
public interface OrderResource {

    /**
     * Get a range of orders resources according to the given range.
     * 
     * @param page Is the page number from the orders will be obtained.
     * @param size Number of orders that will be obtained.
     * @param uriInfo URL context of the resource.
     * 
     * @return A Response object with the list of orders in the system 
     *         according to the range.
     */
    @GET
    @Produces( { "application/json;charset=UTF-8", 
                 "application/xml;charset=UTF-8",
                 "application/vnd.ecommerce-v1+json;charset=UTF-8", 
                 "application/vnd.ecommerce-v1+xml;charset=UTF-8" } )
    public Response getAllOrders( @QueryParam( "page" ) int page,
                                     @QueryParam( "size" ) @DefaultValue( "2" ) int size,
                                     @Context UriInfo uriInfo );
    
    /**
     * Get an order by its id.
     * 
     * @param id Identifier of the order.
     * @param uriInfo URL context of the resource.
     * 
     * @return An order object.
     */
    @GET
    @Path( "{id}" )
    @Produces( { "application/json;charset=UTF-8", 
                 "application/xml;charset=UTF-8",
                 "application/vnd.ecommerce-v1+json;charset=UTF-8", 
                 "application/vnd.ecommerce-v1+xml;charset=UTF-8" } )
    public Order getOrderById( @PathParam( "id" ) final String id,
                               @Context UriInfo uriInfo );
    
    /**
     * Create a new order in the system.
     * 
     * @param order The order to be created.
     * @param uriInfo URL context of the resource.
     * 
     * @return A response with the new id of the order created.
     */
    @POST
    @Consumes( { "application/json;charset=UTF-8" ,
                 "application/vnd.ecommerce-v1+json;charset=UTF-8" } )
    @Produces( { "application/json;charset=UTF-8", 
                 "application/xml;charset=UTF-8",
                 "application/vnd.ecommerce-v1+json;charset=UTF-8", 
                 "application/vnd.ecommerce-v1+xml;charset=UTF-8" } )
    public Response createOrder( final Order order,
                                 @Context UriInfo uriInfo );
 
    /**
     * Update an order in the system.
     * 
     * @param order The order to be updated.
     * @param uriInfo URL context of the resource.
     * 
     * @return A response with the confirmation of the order update.
     */
    @PUT
    @Path( "{id}" )
    @Consumes( { "application/json;charset=UTF-8" ,
                 "application/vnd.ecommerce-v1+json;charset=UTF-8" } )
    @Produces( { "application/json;charset=UTF-8", 
                 "application/xml;charset=UTF-8",
                 "application/vnd.ecommerce-v1+json;charset=UTF-8", 
                 "application/vnd.ecommerce-v1+xml;charset=UTF-8" } )
    public Response updateOrder( final Order order,
                                 @Context UriInfo uriInfo );
    
    /**
     * Remove an order from the system.
     * 
     * @return A response with the confirmation of the order removed.
     */
    @DELETE
    @Path( "{id}" )
    @Produces( { "application/json;charset=UTF-8", 
                 "application/xml;charset=UTF-8",
                 "application/vnd.ecommerce-v1+json;charset=UTF-8", 
                 "application/vnd.ecommerce-v1+xml;charset=UTF-8" } )
    public Response removeOrder( @PathParam( "id" ) final String id );
}
