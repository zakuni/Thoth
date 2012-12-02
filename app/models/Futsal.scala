package models
import scala.collection.mutable.ListBuffer
import org.scala_tools.time.Imports._

class Futsal extends Sport("Futsal") { 
  def requestSchedule(): Schedule = { 
    new Schedule(Map(new DateTime() -> ListBuffer("")))
  }
}
