package dao

import scala.concurrent.{ ExecutionContext, Future }
import play.api.libs.json.JsObject
import play.modules.reactivemongo.ReactiveMongoApi
import play.api.libs.json.Json
import play.modules.reactivemongo.json.collection.JSONCollection
import play.modules.reactivemongo.json._

trait ProductDao {
  def find()( implicit ec: ExecutionContext ): Future[List[JsObject]]
}

class MongoProductDao( reactiveMongoApi: ReactiveMongoApi ) extends ProductDao {

  
  
  protected def prodCollection = reactiveMongoApi.db.collection[ JSONCollection ]( "products" )

  def find()( implicit ec: ExecutionContext ): Future[List[JsObject]] = {
    prodCollection.find(Json.obj()).cursor[JsObject].collect[List]()
  }

}