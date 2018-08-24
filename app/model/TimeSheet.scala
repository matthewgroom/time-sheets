package model

import play.api.libs.json._

case class TimeSheet(userName: String, months: List[Month])

object TimeSheet {
  import model.Month._

  implicit val format = Json.format[TimeSheet]

}
