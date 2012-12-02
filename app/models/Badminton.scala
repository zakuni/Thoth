package models
import scala.collection.mutable.ListBuffer
import org.scala_tools.time.Imports._

class Badminton extends Sport("Badminton") { 

  def requestSchedule(): Schedule = { 
    new Schedule(Map(new DateTime() -> ListBuffer("")))
  }
}
