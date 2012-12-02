package models
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{Map => MutableMap}
import scala.xml.Text
import scala.xml.XML
import org.scala_tools.time.Imports._

class Rugby extends Sport("Rugby") {

  def requestSchedule(): Schedule = {
    val node = request("http://www.rugby-japan.jp/calendar/calendar_2012_12.html")
    val cal_main = node \\ "div" filter (_ \ "@id" contains Text("cal_main"))
    val trs = (cal_main \\ "tr").theSeq

    var date = new DateTime(2012, 12, 1, 0, 0)

    val m: MutableMap[DateTime, ListBuffer[String]] = MutableMap()
    trs.foreach { tr =>
      tr.child.foreach { td =>
        if (td \ "@class" contains Text("cal_date")) { 
          date = date.day(td.text)
        }
        
        if (td \ "@class" contains Text("cal_dtl")) {
          if (m.contains(date)) {
            var tmplist = m(date) += td.text.trim
            m.remove(date)
            m.put(date, tmplist)
          } else {
            m.put(date, ListBuffer(td.text.trim))
          }
        }
      }
    }
    new Schedule(m.toMap)
  }
}
