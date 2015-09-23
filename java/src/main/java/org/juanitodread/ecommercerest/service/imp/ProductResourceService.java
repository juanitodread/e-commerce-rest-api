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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.juanitodread.ecommercerest.model.dao.ProductDao;
import org.juanitodread.ecommercerest.model.dao.factory.DaoFactory;
import org.juanitodread.ecommercerest.model.dao.factory.DaoType;
import org.juanitodread.ecommercerest.model.dao.factory.imp.ProductDaoFactory;
import org.juanitodread.ecommercerest.model.domain.Product;
import org.juanitodread.ecommercerest.service.ProductResource;

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
     * @see org.juanitodread.ecommercerest.service.ProductResource#getAllProducts()
     */
    public List<Product> getAllProducts( ) {
        return dao.findAll( );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.ProductResource#getProductById(java.lang.String)
     */
    public Product getProductById( String id ) {
        return dao.findById( id );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.ProductResource#createProduct(org.juanitodread.ecommercerest.model.domain.Product)
     */
    public Response createProduct( Product product ) {
        Product newProduct = dao.create( product );
        return Response.status( Status.CREATED ).entity( newProduct ).build( );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.ProductResource#updateProduct(org.juanitodread.ecommercerest.model.domain.Product)
     */
    public Response updateProduct( Product product ) {
        dao.update( product );
        String message = String.format( "Product [%s] has been updated", product.getId( ) );
        return Response.status( Status.OK ).entity( message ).build( );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.ProductResource#removeProduct(java.lang.String)
     */
    public Response removeProduct( String id ) {
        dao.delete( id );
        return Response.status( Status.NO_CONTENT ).build( );
    }

}
