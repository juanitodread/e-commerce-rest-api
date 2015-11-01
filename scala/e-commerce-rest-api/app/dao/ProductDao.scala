/**
 * e-commerce-rest-scala
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
package dao

import scala.annotation.implicitNotFound
import scala.concurrent.{ ExecutionContext, Future }

import models.Product
import play.api.libs.json.Json
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import play.modules.reactivemongo.ReactiveMongoApi
import play.modules.reactivemongo.json.JsObjectDocumentWriter
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.api.ReadPreference
import reactivemongo.api.commands.WriteResult

/**
 * Simple trait with the definition of the DAO interface.
 *
 * @author juanitodread
 * @version 1.0.0
 * @since   1.0.0
 * 
 * 	        Nov 1, 2015
 */
trait ProductDao {
  def find()( implicit ec: ExecutionContext ): Future[ List[ Product ] ]

  def findById( id: String )( implicit ec: ExecutionContext ): Future[ Option[Product] ]

  def save( product: Product )( implicit ec: ExecutionContext ): Future[ WriteResult ]

  def update(id: String, product: Product )( implicit ec: ExecutionContext ): Future[ WriteResult ]

  def delete( id: String )( implicit ec: ExecutionContext ): Future[ WriteResult ]
  
}

/**
 * Mongo DAO that supports the basic operations for products.
 *
 * @author juanitodread
 * @version 1.0.0
 * @since   1.0.0
 * 
 * 	        Nov 1, 2015
 */
class MongoProductDao( reactiveMongoApi: ReactiveMongoApi ) extends ProductDao {

  import play.modules.reactivemongo.json._
  import models.Product.{ MongoProductReads, MongoProductWrites }
  
  protected def prodCollection = reactiveMongoApi.db.collection[ JSONCollection ]( "products" )

  def find()( implicit ec: ExecutionContext ): Future[ List[ Product ] ] = {
    val cursor = prodCollection.find( Json.obj() ).cursor[ Product ]( ReadPreference.primary )
    cursor.collect[ List ]()
  }

  def findById( id: String )( implicit ec: ExecutionContext ): Future[ Option[ Product ] ] = {
    prodCollection.find( Json.obj( "_id" -> Json.obj( "$oid" -> id ) ) ).one[ Product ]
  }

  def save( product: Product )( implicit ec: ExecutionContext ): Future[ WriteResult ] = {
    prodCollection.insert( product )
  }

  def update(id: String, product: Product )( implicit ec: ExecutionContext ): Future[ WriteResult ] = {
    prodCollection.update( Json.obj( "_id" -> Json.obj( "$oid" -> id ) ), product )
  }

  def delete( id: String )( implicit ec: ExecutionContext ): Future[ WriteResult ] = {
    prodCollection.remove( Json.obj( "_id" -> Json.obj( "$oid" -> id ) ) )
  }  
}