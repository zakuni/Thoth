package models
import java.io.InputStream
import scala.collection.mutable._
import scala.xml.Node
import scala.xml.parsing.NoBindingFactoryAdapter
import dispatch._
import nu.validator.htmlparser.common.XmlViolationPolicy
import nu.validator.htmlparser.sax.HtmlParser
import com.github.nscala_time.time.Imports._
import org.xml.sax.InputSource

abstract class Sport(val name: String, var date: DateTime=DateTime.now) {
  val ref: List[String]
  lazy val schedule = requestSchedule(date)

  protected def requestSchedule(date: DateTime): Schedule

  protected def request(siteUrl: String): Http.HttpPackage[Node] = { 
    val u = url(siteUrl) >> { is => toNode(is) }
    val http = new Http
    http(u)
  }

  private def toNode(inputStream: InputStream): Node = {
    val htmlParser = new HtmlParser
    htmlParser.setNamePolicy(XmlViolationPolicy.ALLOW)

    val saxer = new NoBindingFactoryAdapter
    htmlParser.setContentHandler(saxer)
    htmlParser.parse(new InputSource(inputStream))

    saxer.rootElem
  }
}
