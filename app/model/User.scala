package model

import play.api.libs.json._

case class User(userName: String, accountType: String, password: String)

object User {
  implicit val format = Json.format[User]
}
