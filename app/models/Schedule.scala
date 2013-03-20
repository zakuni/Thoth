package models
import scala.collection.immutable.SortedMap
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{Map => MutableMap}
import com.github.nscala_time.time.Imports._

class Schedule(val year: Int, val month:Int, m: Map[DateTime, ListBuffer[String]]){
  val date = new DateTime(year, month, 1, 0, 0)
  val games = SortedMap[DateTime, ListBuffer[String]]() ++ m
  val previousMonth = (date - 1.months).toString("yyyy/MM")
  val nextMonth = (date + 1.months).toString("yyyy/MM")
}
