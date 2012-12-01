package models
import dispatch._
import java.io.InputStream
import java.io.StringReader
import scala.xml.Node
import scala.xml.Text
import scala.xml.XML
import scala.xml.parsing.NoBindingFactoryAdapter
import nu.validator.htmlparser.sax.HtmlParser
import nu.validator.htmlparser.common.XmlViolationPolicy
import org.xml.sax.InputSource

class Rugby extends Sport("Rugby") {
  val u = url("http://www.rugby-japan.jp/calendar/calendar_2012_11.html") >> {is => toNode(is)}
  val h = new Http
  val r = h(u)
  println(r \\ "td")

  def toNode(is: InputStream): Node = {
    val hp = new HtmlParser
    hp.setNamePolicy(XmlViolationPolicy.ALLOW)

    val saxer = new NoBindingFactoryAdapter
    hp.setContentHandler(saxer)
    hp.parse(new InputSource(is))

    saxer.rootElem
  }
}
