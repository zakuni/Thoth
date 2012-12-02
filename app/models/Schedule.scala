package models
import scala.collection.immutable.SortedMap
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{Map => MutableMap}
import org.scala_tools.time.Imports._
import Joda._

class Schedule(m: Map[DateTime, ListBuffer[String]]){
  val games = SortedMap[DateTime, ListBuffer[String]]() ++ m
}

object Joda {
  implicit def dateTimeOrdering: Ordering[DateTime] = Ordering.fromLessThan(_ isBefore _)
}
