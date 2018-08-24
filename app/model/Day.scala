package model

import play.api.libs.json._

case class Day(date: java.time.LocalDate, cost: Int, hour: Int)

object Day {
  implicit val format = Json.format[Day]
}
