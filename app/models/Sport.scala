package models
import java.io.InputStream
import scala.collection.mutable._
import scala.xml.Node
import scala.xml.parsing.NoBindingFactoryAdapter
import dispatch._
import nu.validator.htmlparser.common.XmlViolationPolicy
import nu.validator.htmlparser.sax.HtmlParser
import org.xml.sax.InputSource

class Sport(val name: String) {
  var schedule = List[String]()

  def toNode(is: InputStream): Node = {
    val hp = new HtmlParser
    hp.setNamePolicy(XmlViolationPolicy.ALLOW)

    val saxer = new NoBindingFactoryAdapter
    hp.setContentHandler(saxer)
    hp.parse(new InputSource(is))

    saxer.rootElem
  }

  def request(siteUrl: String): Http.HttpPackage[scala.xml.Node] = { 
    val u = url(siteUrl) >> { is => toNode(is) }
    val h = new Http
    h(u)
  }
}
