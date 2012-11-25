package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  def index = Action {
    Redirect(routes.Application.sports)
  }

  def sports = TODO

  def rugby = TODO

  def futsal = TODO

  def badminton = TODO
}
