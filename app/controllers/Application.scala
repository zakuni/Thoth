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

  def sport(sport: String) = Action {
    val s: Sport = sport match { 
      case "rugby"     => { new Rugby }
      case "futsal"    => { new Futsal }
      case "badminton" => { new Badminton }
    }
    Ok(views.html.schedule(s, s.schedule))
  }

  def schedule(sport: String, year: String, month: String) = Action {
    val s: Sport = sport match { 
      case "rugby"     => { new Rugby(year.toInt, month.toInt) }
      case "futsal"    => { new Futsal }
      case "badminton" => { new Badminton }
    }
    Ok(views.html.schedule(s, s.schedule))
  }
}
