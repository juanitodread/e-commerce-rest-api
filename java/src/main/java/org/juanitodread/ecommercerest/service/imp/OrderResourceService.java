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

import org.juanitodread.ecommercerest.model.dao.OrderDao;
import org.juanitodread.ecommercerest.model.dao.factory.DaoFactory;
import org.juanitodread.ecommercerest.model.dao.factory.DaoType;
import org.juanitodread.ecommercerest.model.dao.factory.imp.OrderDaoFactory;
import org.juanitodread.ecommercerest.model.domain.Order;
import org.juanitodread.ecommercerest.service.OrderResource;

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

    /*
     * (non-Javadoc)
     * 
     * @see org.juanitodread.ecommercerest.service.OrderResource#getAllOrders()
     */
    public List<Order> getAllOrders( ) {
        return dao.findAll( );
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.juanitodread.ecommercerest.service.OrderResource#getOrderById(java
     * .lang.String)
     */
    public Order getOrderById( String id ) {
        return dao.findById( id );
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.juanitodread.ecommercerest.service.OrderResource#createOrder(org.
     * juanitodread.ecommercerest.model.domain.Order)
     */
    public Response createOrder( Order order ) {
        Order newOrder = dao.create( order );
        return Response.status( Status.CREATED).entity( newOrder ).build( );
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.juanitodread.ecommercerest.service.OrderResource#updateOrder(org.
     * juanitodread.ecommercerest.model.domain.Order)
     */
    public Response updateOrder( Order order ) {
        dao.update( order );
        String message = String.format( "Order [%s] has been updated", order.getId( ) );
        return Response.status( Status.OK ).entity( message ).build( );
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
