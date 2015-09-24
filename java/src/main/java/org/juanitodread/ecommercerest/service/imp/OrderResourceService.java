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

import org.juanitodread.ecommercerest.model.dao.OrderDao;
import org.juanitodread.ecommercerest.model.dao.factory.DaoFactory;
import org.juanitodread.ecommercerest.model.dao.factory.DaoType;
import org.juanitodread.ecommercerest.model.dao.factory.imp.OrderDaoFactory;
import org.juanitodread.ecommercerest.model.domain.Link;
import org.juanitodread.ecommercerest.model.domain.Order;
import org.juanitodread.ecommercerest.service.OrderResource;
import org.juanitodread.ecommercerest.util.Util;

/**
 * Order service.
 *
 * @author juanitodread
 * @version 1.0
 * 
 *          Aug 31, 2015
 */
@Path( "/orders" )
public class OrderResourceService implements OrderResource {
    private final OrderDao dao;

    public OrderResourceService( ) {
        final DaoFactory factory = new OrderDaoFactory( );
        dao = ( OrderDao ) factory.getDao( DaoType.MONGO_DB );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.OrderResource#getAllOrders(int, int, javax.ws.rs.core.UriInfo)
     */
    @Override
    public Response getAllOrders( int page,
                                  int size,
                                  UriInfo uriInfo ) {
        List<Order> orders = null;
        ResponseBuilder responseBuilder = Response.status( Status.OK );
        String baseUri = uriInfo.getAbsolutePath( ).toString( );
        
        // If page is 0 (absent) then get all products
        if( page == 0 ) {
            orders = dao.findAll( );
        } else {
            orders = dao.filterByRange( page, size );
            responseBuilder.header( "Link",
                             Util.buildFilterLinks( baseUri, page, size ) );
        }
        
        for( Order o: orders ) {
            o.setLink( Util.buildSelfLink( baseUri, o.getId( ) ) );
        }
        
        GenericEntity<List<Order>> entities = new GenericEntity<List<Order>>( orders ) {
        };
        
        return responseBuilder.entity( entities ).build( );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.OrderResource#getOrderById(java.lang.String, javax.ws.rs.core.UriInfo)
     */
    @Override
    public Order getOrderById( String id,
                               UriInfo uriInfo ) {
        Order o = dao.findById( id );
        
        if( o == null ) {
            throw new WebApplicationException( Status.NOT_FOUND );
        }
        
        String baseUri = uriInfo.getAbsolutePath( ).toString( );
        o.setLink( new Link( "self", baseUri, "" ) );
        return o;
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.OrderResource#createOrder(org.juanitodread.ecommercerest.model.domain.Order, javax.ws.rs.core.UriInfo)
     */
    @Override
    public Response createOrder( Order order,
                                 UriInfo uriInfo ) {
        order.calculateTotal( );
        Order newProduct = dao.create( order );
        String baseUri = uriInfo.getAbsolutePath( ).toString( );
        return Response
                .status( Status.CREATED )
                .header( "Link", Util.buildSelfLink( baseUri, newProduct.getId( ) ) )
                .build( );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.service.OrderResource#updateOrder(org.juanitodread.ecommercerest.model.domain.Order, javax.ws.rs.core.UriInfo)
     */
    @Override
    public Response updateOrder( Order order,
                                 UriInfo uriInfo ) {
        order.calculateTotal( );
        dao.update( order );
        String baseUri = uriInfo.getAbsolutePath( ).toString( );
        return Response
                .status( Status.OK )
                .header( "Link", new Link( "self", baseUri, "" ) ) 
                .build( );
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.juanitodread.ecommercerest.service.OrderResource#removeOrder(java
     * .lang.String)
     */
    public Response removeOrder( String id ) {
        dao.delete( id );
        return Response.status( Status.NO_CONTENT ).build( );
    }

}
