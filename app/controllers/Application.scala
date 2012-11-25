package controllers

import play.api._
import play.api.mvc._
import models._

object Application extends Controller {
  
  def index = Action {
    Redirect(routes.Application.sports)
  }

  def sports = Action { 
    val list = List(new Badminton,
                    new Futsal,
                    new Rugby)
    Ok(views.html.index(list))
  }

  def rugby = TODO

  def futsal = TODO

  def badminton = TODO
}
