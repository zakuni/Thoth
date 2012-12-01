package models
import scala.xml.Text
import scala.xml.XML

class Rugby extends Sport("Rugby") {
  val node = request("http://www.rugby-japan.jp/calendar/calendar_2012_11.html")
  val cal_main = node \\ "div" filter (_ \ "@id" contains Text("cal_main"))
  val trs = (cal_main \\ "tr").theSeq

  trs.foreach{ tr =>
    tr.child.collect { 
      case td if (td \ "@class" contains Text("cal_dtl")) ||
        (td \ "@class" contains Text("cal_date")) || 
        (td \ "@class" contains Text("cal_week")) =>
        schedule = schedule :+ td.text
    }
  }
}
