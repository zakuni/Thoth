package models
import scala.collection.mutable.ListBuffer
import org.scala_tools.time.Imports._

class Futsal extends Sport("Futsal") { 
  def requestSchedule(date: DateTime): Schedule = { 
    val year = date.toString("yyyy")
    val month = date.toString("MM")
    new Schedule(year.toInt, month.toInt, Map(new DateTime() -> ListBuffer("")))
  }
}
