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

  def schedule(sport: String) = Action {
    sport match { 
      case "rugby" => { 
        Ok(views.html.schedule(new Rugby))
      }
      case "futsal" => { 
        Ok(views.html.schedule(new Futsal))
      }
      case "badminton" => { 
        Ok(views.html.schedule(new Badminton))
      }
    }
  }
}
