package models
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{Map => MutableMap}
import scala.xml.Text
import scala.xml.XML
import org.scala_tools.time.Imports._

class Rugby extends Sport("Rugby") {

  def requestSchedule(date: DateTime): Schedule = {
    val node = request("http://www.rugby-japan.jp/calendar/calendar_%s_%s.html".format(date.toString("yyyy"), date.toString("MM")))
    val cal_main = node \\ "div" filter (_ \ "@id" contains Text("cal_main"))
    val trs = (cal_main \\ "tr").theSeq

    val m: MutableMap[DateTime, ListBuffer[String]] = MutableMap()
    var keydate = date
    trs.foreach { tr =>
      tr.child.foreach { td =>
        // 日付
        if (td \ "@class" contains Text("cal_date")) { 
          keydate = keydate.day(td.text)
        }

        // 日付と試合のリストのマップを作る
        if (td \ "@class" contains Text("cal_dtl")) {
          /* 日付が既にマップのキーとして存在していたら、試合のリストを変更する
           * まだなければ、新しく日付と試合リストのペアをマップに追加する */
          if (m.contains(keydate)) {
            val tmplist = m(keydate) += td.text.trim
            m.remove(keydate)
            m.put(keydate, tmplist)
          } else {
            m.put(keydate, ListBuffer(td.text.trim))
          }
        }
      }
    }
    new Schedule(m.toMap)
  }
}
