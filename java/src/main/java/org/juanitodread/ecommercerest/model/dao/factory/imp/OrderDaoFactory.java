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
package org.juanitodread.ecommercerest.model.dao.factory.imp;

import org.juanitodread.ecommercerest.model.dao.Dao;
import org.juanitodread.ecommercerest.model.dao.OrderDao;
import org.juanitodread.ecommercerest.model.dao.factory.DaoFactory;
import org.juanitodread.ecommercerest.model.dao.factory.DaoType;
import org.juanitodread.ecommercerest.model.dao.imp.MongoOrderDaoImp;
import org.juanitodread.ecommercerest.model.domain.Order;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 * Factory for OrderDao.
 *
 * @author juanitodread
 * @version 1.0
 * 
 * Aug 31, 2015
 */
public class OrderDaoFactory implements DaoFactory {
    static final MongoClient mongoClient;
    static final MongoDatabase db;
    
    private MongoOrderDaoImp mongoDao;
    
    static {
        mongoClient = new MongoClient( new MongoClientURI( DaoType.MONGO_DB.getUri( ) ) );
        db = mongoClient.getDatabase( DaoType.MONGO_DB.getDatabase( ) );
    }

    /* (non-Javadoc)
     * @see org.juanitodread.ecommercerest.model.dao.factory.DaoFactory#getDao(org.juanitodread.ecommercerest.model.dao.factory.DaoType)
     */
    public Dao<Order> getDao( final DaoType type ) {
        OrderDao dao = null;
        
        if ( type == DaoType.MONGO_DB ) {
            if ( mongoDao == null ) {
                mongoDao = new MongoOrderDaoImp( db );
            }
            dao = mongoDao;
        }
        
        return dao;
    }

}
