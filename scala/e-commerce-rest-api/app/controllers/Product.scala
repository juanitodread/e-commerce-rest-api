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
package controllers

import dao.MongoProductDao

import javax.inject.Inject

import models.{ Product => PBean }

import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc.{
  Action,
  BodyParsers,
  Controller
}
import play.modules.reactivemongo.{ 
  MongoController,
  ReactiveMongoApi,
  ReactiveMongoComponents
}

import reactivemongo.core.actors.Exceptions.PrimaryUnavailableException

/**
 * RESTful service for products
 *
 * @author juanitodread
 * @version 1.0.0
 * @since   1.0.0
 *
 * 	        Oct 25, 2015
 */
class Product @Inject() ( val reactiveMongoApi: ReactiveMongoApi ) 
  extends Controller with MongoController with ReactiveMongoComponents {

  import models.Product.{ productWrites, productReads }
  
  val log = Logger( this.getClass )
  def postDao = new MongoProductDao( reactiveMongoApi )

  def getAll() = Action.async {
    log.info( "getAll()" )
    postDao.find().map( products => Ok( Json.toJson( products ) ) )
      .recover { case PrimaryUnavailableException => 
                      InternalServerError( "Please install MongoDB" ) }
  }

  def getProductById( id: String ) = Action.async { 
    log.info( s"getProductById($id)" )
    postDao.findById( id ).map( x => x match {
      case Some( x ) => Ok( Json.toJson( x ) )
      case None      => NotFound
    } ).recover { case PrimaryUnavailableException => 
                       InternalServerError( "Please install MongoDB" ) }
  }

  def create() = Action.async( BodyParsers.parse.json ) { implicit request =>
    log.info( s"create(): Body: ${request.body}" )
    val pr = request.body.validate[ PBean ]
    postDao.save( pr.get ).map( x => NoContent )
      .recover { case PrimaryUnavailableException => 
                      InternalServerError( "Please install MongoDB" ) }
  }

  def update( id: String ) = Action.async( BodyParsers.parse.json ) { implicit request =>
    log.info( s"update($id): Body: ${request.body}" )
    val pr = request.body.validate[ PBean ]
    postDao.update( id, pr.get ).map( x => Ok )
      .recover {
        case PrimaryUnavailableException =>
          InternalServerError( "Please install MongoDB" )
      }
  }
  
  def delete(id: String) = Action.async {
    log.info( s"delete($id)" )
    postDao.delete(id).map( x => Ok )
      .recover { case PrimaryUnavailableException => 
                      InternalServerError( "Please install MongoDB" ) }
  }
  
}