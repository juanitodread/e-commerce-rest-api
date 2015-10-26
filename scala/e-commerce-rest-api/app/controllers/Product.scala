package controllers

import play.api.mvc.Controller
import play.api.mvc.Action

object Product extends Controller {
  
  def getAllProducts = Action {
    Ok("Get all products")
  }
  
}