package models
import java.io.InputStream
import java.io.StringReader
import scala.collection.mutable._
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.xml.Node
import scala.xml.parsing.NoBindingFactoryAdapter
import play.api.libs.ws._
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
    val response: Future[Response] = WS.url(siteUrl).get()
    val result = Await.result(response, duration.Duration.Inf).body
    val converted_result = new String(result.getBytes("iso-8859-1"), "utf-8")
    toNode(converted_result)
  }

  private def toNode(str: String): Node = {
    val htmlParser = new HtmlParser
    htmlParser.setNamePolicy(XmlViolationPolicy.ALLOW)

    val saxer = new NoBindingFactoryAdapter
    htmlParser.setContentHandler(saxer)
    htmlParser.parse(new InputSource(new StringReader(str)))

    saxer.rootElem
  }
}
