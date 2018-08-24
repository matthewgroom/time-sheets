
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object main_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template is called from the `index` template. This template
 * handles the rendering of the page header and body tags. It takes
 * two arguments, a `String` for the title of the page and an `Html`
 * object to insert into the body of the page.
 */
  def apply/*7.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*7.32*/("""

"""),format.raw/*9.1*/("""<html lang="en">
    <head>
        """),format.raw/*11.62*/("""
        """),format.raw/*12.9*/("""<title>"""),_display_(/*12.17*/title),format.raw/*12.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*13.54*/routes/*13.60*/.Assets.versioned("stylesheets/main.css")),format.raw/*13.101*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*14.59*/routes/*14.65*/.Assets.versioned("images/favicon.png")),format.raw/*14.104*/("""">

    </head>
    <body>
        """),format.raw/*19.32*/("""
      """),format.raw/*20.7*/("""<script src=""""),_display_(/*20.21*/routes/*20.27*/.Assets.versioned("javascripts/main.js")),format.raw/*20.67*/("""" type="text/javascript"></body>
    </body>
</html>
"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


}

/*
 * This template is called from the `index` template. This template
 * handles the rendering of the page header and body tags. It takes
 * two arguments, a `String` for the title of the page and an `Html`
 * object to insert into the body of the page.
 */
object main extends main_Scope0.main
              /*
                  -- GENERATED --
                  DATE: Fri Aug 24 15:31:56 BST 2018
                  SOURCE: /home/temp/development/time-sheets/app/views/main.scala.html
                  HASH: ec755441eb52afa0c6b1930eddc50c05235f6874
                  MATRIX: 784->260|909->290|937->292|1001->381|1037->390|1072->398|1098->403|1187->465|1202->471|1265->512|1353->573|1368->579|1429->618|1492->743|1526->750|1567->764|1582->770|1643->810
                  LINES: 25->7|30->7|32->9|34->11|35->12|35->12|35->12|36->13|36->13|36->13|37->14|37->14|37->14|41->19|42->20|42->20|42->20|42->20
                  -- GENERATED --
              */
          