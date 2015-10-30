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

import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.libs.json.Json
import models.{Product => PBean}
import play.api.Logger
import play.api.mvc.BodyParsers

/**
 * RESTful service for products
 *
 * @author juanitodread
 * @version 1.0.0
 * @since   1.0.0
 * 
 * 	        Oct 25, 2015
 */
object Product extends Controller {

  val log = Logger( Product.getClass )

  def getAll() = Action { implicit request =>
    log.info( "getAllProducts()" )
    val defaultProduct = PBean( "1", "Scala book", 23.50 )
    Ok( Json.toJson( defaultProduct ) )
  }

  def getProductById( id: String ) = Action { implicit request =>
    log.info( s"getProductById($id)" )
    val defaultProduct = PBean( id, "Scala book", 23.50 )
    Ok( Json.toJson( defaultProduct ) )
  }
  
  def create() = Action(BodyParsers.parse.json) { implicit request =>
    log.info(s"create(): Body: ${request.body}")
    val valid = request.body.validate[PBean]
    log.info(s"JSON valid: $valid")
    if(valid.isError) BadRequest(valid.toString) else Ok
  }

  def update(id: String) = Action {
    NotImplemented
  }
  
  def delete(id: String) = Action {
    NotImplemented
  }
  
}