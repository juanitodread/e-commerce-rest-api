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

import org.juanitodread.ecommercerest.model.domain.Product;

/**
 * Resource controller for Product
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 23, 2015
 */
public interface ProductResource {
    
    /**
     * Get a range of products resources according to the given range.
     * 
     * @param page Is the page number from the products will be obtained.
     * @param size Number of products that will be obtained.
     * @param uriInfo URL context of the resource.
     * 
     * @return A Response object with the list of products in the system 
     *         according to the range.
     */
    @GET
    @Produces( { "application/json;charset=UTF-8",
                 "application/xml;charset=UTF-8",
                 "application/vnd.ecommerce-v1+json;charset=UTF-8",
                 "application/vnd.ecommerce-v1+xml;charset=UTF-8" } )
    public Response getAllProducts( @QueryParam( "page" ) int page,
                                    @QueryParam( "size" ) @DefaultValue( "2" ) int size,
                                    @Context UriInfo uriInfo );

    /**
     * Get a product by its id.
     * 
     * @param id Identifier of the product.
     * @param uriInfo URL context of the resource.
     * 
     * @return A product object.
     */
    @GET
    @Path( "{id}" )
    @Produces( { "application/json;charset=UTF-8", 
                 "application/xml;charset=UTF-8",
                 "application/vnd.ecommerce-v1+json;charset=UTF-8", 
                 "application/vnd.ecommerce-v1+xml;charset=UTF-8" } )
    public Product getProductById( @PathParam( "id" ) final String id,
                                   @Context UriInfo uriInfo );
    
    /**
     * Create a new product in the system.
     * 
     * @param product The product to be created.
     * @param uriInfo URL context of the resource.
     * 
     * @return A response with the new id of the product created.
     */
    @POST
    @Consumes( { "application/json;charset=UTF-8" ,
                 "application/vnd.ecommerce-v1+json;charset=UTF-8" } )
    @Produces( { "application/json;charset=UTF-8", 
                 "application/xml;charset=UTF-8",
                 "application/vnd.ecommerce-v1+json;charset=UTF-8", 
                 "application/vnd.ecommerce-v1+xml;charset=UTF-8" } )
    public Response createProduct( final Product product,
                                   @Context UriInfo uriInfo );
    
    /**
     * Update a product in the system.
     * 
     * @param product The product to be updated.
     * @param uriInfo URL context of the resource.
     * 
     * @return A response with the confirmation of the product update.
     */
    @PUT
    @Path( "{id}" )
    @Consumes( { "application/json;charset=UTF-8" ,
                 "application/vnd.ecommerce-v1+json;charset=UTF-8" } )
    @Produces( { "application/json;charset=UTF-8", 
                 "application/xml;charset=UTF-8",
                 "application/vnd.ecommerce-v1+json;charset=UTF-8", 
                 "application/vnd.ecommerce-v1+xml;charset=UTF-8" } )
    public Response updateProduct( final Product product,
                                   @Context UriInfo uriInfo );
    
    /**
     * Remove a product from the system.
     * 
     * @return A response with the confirmation of the product removed.
     */
    @DELETE
    @Path( "{id}" )
    @Produces( { "application/json;charset=UTF-8", 
                 "application/xml;charset=UTF-8",
                 "application/vnd.ecommerce-v1+json;charset=UTF-8", 
                 "application/vnd.ecommerce-v1+xml;charset=UTF-8" } )
    public Response removeProduct( @PathParam( "id" ) final String id );
    
}
