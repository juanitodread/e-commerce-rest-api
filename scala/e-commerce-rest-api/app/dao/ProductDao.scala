package dao

import scala.concurrent.{ ExecutionContext, Future }
import play.api.libs.json.{ JsObject, Json }
import play.modules.reactivemongo.ReactiveMongoApi
import play.modules.reactivemongo.json.collection.JSONCollection
import models.Product
import reactivemongo.bson.BSONDocument
import reactivemongo.api.commands.WriteResult
import reactivemongo.api.ReadPreference

trait ProductDao {
  def find()( implicit ec: ExecutionContext ): Future[ List[ Product ] ]

  def findById( id: String )( implicit ec: ExecutionContext ): Future[ Option[Product] ]

  def save( product: Product )( implicit ec: ExecutionContext ): Future[ WriteResult ]

  def update(id: String, product: Product )( implicit ec: ExecutionContext ): Future[ WriteResult ]
  
}

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
  
}