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
package org.juanitodread.ecommercerest.service.imp;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.juanitodread.ecommercerest.model.dao.ProductDao;
import org.juanitodread.ecommercerest.model.dao.factory.DaoFactory;
import org.juanitodread.ecommercerest.model.dao.factory.DaoType;
import org.juanitodread.ecommercerest.model.dao.factory.imp.ProductDaoFactory;
import org.juanitodread.ecommercerest.model.domain.Link;
import org.juanitodread.ecommercerest.model.domain.Product;
import org.juanitodread.ecommercerest.service.ProductResource;
import org.juanitodread.ecommercerest.util.Util;

/**
 * Product service.
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 23, 2015
 */
@Path( "/products" )
public class ProductResourceService implements ProductResource {
    private final ProductDao dao;
    
    public ProductResourceService( ) {
        final DaoFactory factory = new ProductDaoFactory( );
        dao = ( ProductDao ) factory.getDao( DaoType.MONGO_DB );
    }
    
    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.ProductResource#getAllProducts(int, int, javax.ws.rs.core.UriInfo)
     */
    @Override
    public Response getAllProducts( int page,
                                    int size,
                                    UriInfo uriInfo ) {
        List<Product> products = null;
        ResponseBuilder responseBuilder = Response.status( Status.OK );
        String baseUri = uriInfo.getAbsolutePath( ).toString( );
        
        // If page is 0 (absent) then get all products
        if( page == 0 ) {
            products = dao.findAll( );
        } else {
            products = dao.filterByRange( page, size );
            responseBuilder.header( "Link",
                             Util.buildFilterLinks( baseUri, page, size ) );
        }
        
        for( Product p: products ) {
            p.setLink( Util.buildSelfLink( baseUri, p.getId( ) ) );
        }
        
        GenericEntity<List<Product>> entities = new GenericEntity<List<Product>>( products ) {
        };
        
        return responseBuilder.entity( entities ).build( );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.ProductResource#getProductById(java.lang.String)
     */
    public Product getProductById( String id,
                                   UriInfo uriInfo ) {
        Product p = dao.findById( id );
        
        if( p == null ) {
            throw new WebApplicationException( Status.NOT_FOUND );
        }
        
        String baseUri = uriInfo.getAbsolutePath( ).toString( );
        p.setLink( new Link( "self", baseUri, "" ) );
        return p;
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.ProductResource#createProduct(org.juanitodread.ecommercerest.model.domain.Product)
     */
    public Response createProduct( Product product,
                                   UriInfo uriInfo ) {
        Product newProduct = dao.create( product );
        String baseUri = uriInfo.getAbsolutePath( ).toString( );
        return Response
                .status( Status.CREATED )
                .header( "Link", Util.buildSelfLink( baseUri, newProduct.getId( ) ) )
                .build( );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.ProductResource#updateProduct(org.juanitodread.ecommercerest.model.domain.Product)
     */
    public Response updateProduct( Product product,
                                   UriInfo uriInfo ) {
        dao.update( product );
        String baseUri = uriInfo.getAbsolutePath( ).toString( );
        return Response
                .status( Status.OK )
                .header( "Link", new Link( "self", baseUri, "" ) ) 
                .build( );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.ProductResource#removeProduct(java.lang.String)
     */
    public Response removeProduct( String id ) {
        dao.delete( id );
        return Response.status( Status.NO_CONTENT ).build( );
    }
}
