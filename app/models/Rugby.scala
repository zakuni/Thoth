package models
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{Map => MutableMap}
import scala.xml.Text
import scala.xml.XML
import com.github.nscala_time.time.Imports._

class Rugby(date: DateTime=DateTime.now) extends Sport("Rugby", date) {
  val ref = List("ラグビーカレンダー - 日本ラグビーフットボール協会（JRFU）", "http://www.rugby-japan.jp/calendar/index.html")
  // 年月を指定する時用の補助コンストラクタ
  def this(year: Int, month: Int) = this(new DateTime(year, month, 1, 0, 0))

  def requestSchedule(date: DateTime): Schedule = {
    val year = date.toString("yyyy")
    val month = date.toString("MM")
    val node = request("http://www.rugby-japan.jp/calendar/calendar_%s_%s.html".format(year, month))
    val cal_main = node \\ "div" filter (_ \ "@id" contains Text("cal_main"))
    val trs = (cal_main \\ "tr").theSeq

    val m: MutableMap[DateTime, ListBuffer[String]] = MutableMap()
    var keydate = date
    trs.foreach { tr =>
      tr.child.foreach { td =>
        // 日付部分
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
    new Schedule(year.toInt, month.toInt, m.toMap)
  }
}
