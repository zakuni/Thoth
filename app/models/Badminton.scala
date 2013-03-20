package models
import scala.collection.mutable.ListBuffer
import com.github.nscala_time.time.Imports._

class Badminton extends Sport("Badminton") { 
  val ref = List("", "")

  def requestSchedule(date: DateTime): Schedule = {
    val year = date.toString("yyyy")
    val month = date.toString("MM")
    new Schedule(year.toInt, month.toInt, Map(new DateTime() -> ListBuffer("")))
  }
}
