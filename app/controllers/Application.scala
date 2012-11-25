package controllers

import play.api._
import play.api.mvc._
import models._

object Application extends Controller {
  
  def index = Action {
    val list = List(new Badminton,
                    new Futsal,
                    new Rugby)
    Ok(views.html.index(list))
  }

  def rugby = Action { 
    Ok(views.html.schedule(new Rugby))
  }

  def futsal = Action { 
    Ok(views.html.schedule(new Futsal))
  }

  def badminton = Action { 
    Ok(views.html.schedule(new Badminton))
  }
}
