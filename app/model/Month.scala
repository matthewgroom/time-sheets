package model

import java.time.temporal.{TemporalField, WeekFields}
import java.time.{LocalDate, Month => JodaMonth}

import akka.io.Tcp.Write
import play.api.libs.json
import play.api.libs.json._
import play.api.libs.json.Json

import scala.annotation.tailrec

trait Month {
  import model.Day._

  val month: java.time.Month
  val max: Int = LocalDate.of(2018, month, 1).lengthOfMonth()
  val days: List[Day]

//  implicit val writes = {
//    def write(name: String, days: List[Day]) = Json.obj("months" -> name, "days" -> Json.toJson(days))
//    Writes[Month] { obj =>
//      obj.month.get(TemporalField) match {
//            case jan: January => write("January", jan.days)
//          }
//    }
//  }

  def f(list: List[Day]): Month

  def add(day: List[Day]): (Month, List[Day]) = {
    val (current, remainder) = day.zipWithIndex.partition(_._2 < max)
    f(current.map(_._1)) -> remainder.map(_._1)
  }

  // @tailrec
  // final def fillMonths(days: List[Day])(currentMonth: Month)(list: List[Month]) {
  //   currentMonth.writes(days) match {
  //     case (month, Nil) => month[Month] :: list
  //     case (month, remainder: List[Day]) => fillMonths(remainder)(nextMonth(month[Month]))(month[Month] :: list)
  //   }
  // }

  def nextMonth(month: Month): Month = month match {
    case _: January => new February(Nil)
    case _: February => new March(Nil)
    case _: March => new April(Nil)
    case _: April => new May(Nil)
    case _: May => new June(Nil)
    case _: June => new July(Nil)
    case _: July => new August(Nil)
    case _: August => new September(Nil)
    case _: September => new October(Nil)
    case _: October => new November(Nil)
    case _: November => new December(Nil)
    case _: December => new January(Nil)
  }

}

object Month {

  implicit val reads = new Reads[Month] {
    def reads(json: JsValue): JsResult[Month] = {
      (json \ "month").as[String] match {
        case "January"  => JsSuccess[Month](new January((json \ "days").as[List[Day]]))
        case "February" => JsSuccess[Month](new February((json \ "days").as[List[Day]]))
        case "March"    => JsSuccess[Month](new March((json \ "days").as[List[Day]]))
        case "April"    => JsSuccess[Month](new March((json \ "days").as[List[Day]]))
        case "May"    => JsSuccess[Month](new March((json \ "days").as[List[Day]]))
        case "June"    => JsSuccess[Month](new March((json \ "days").as[List[Day]]))
        case "July"    => JsSuccess[Month](new March((json \ "days").as[List[Day]]))
        case "August"    => JsSuccess[Month](new March((json \ "days").as[List[Day]]))
        case "September"    => JsSuccess[Month](new March((json \ "days").as[List[Day]]))
        case "October"    => JsSuccess[Month](new March((json \ "days").as[List[Day]]))
        case "November"    => JsSuccess[Month](new March((json \ "days").as[List[Day]]))
        case "December"    => JsSuccess[Month](new March((json \ "days").as[List[Day]]))
      }
    }
  }

  implicit val writes = new Writes[Month] {
    def writes(obj: Month): JsValue = {
      obj match {
        case jan  => Json.obj("months" -> "January", "days" -> Json.toJson(jan.days))
        case feb  => Json.obj("months" -> "Febuary", "days" -> Json.toJson(feb.days))
        case mar  => Json.obj("months" -> "March", "days" -> Json.toJson(mar.days))
        case apr  => Json.obj("months" -> "April", "days" -> Json.toJson(apr.days))
        case may  => Json.obj("months" -> "May", "days" -> Json.toJson(may.days))
        case june => Json.obj("months" -> "June", "days" -> Json.toJson(june.days))
        case july => Json.obj("months" -> "July", "days" -> Json.toJson(july.days))
        case aug  => Json.obj("months" -> "August", "days" -> Json.toJson(aug.days))
        case sep  => Json.obj("months" -> "September", "days" -> Json.toJson(sep.days))
        case oct  => Json.obj("months" -> "October", "days" -> Json.toJson(oct.days))
        case nov  => Json.obj("months" -> "November", "days" -> Json.toJson(nov.days))
        case dec  => Json.obj("months" -> "December", "days" -> Json.toJson(dec.days))

      }
    }
  }
}

class January(val days: List[Day]) extends Month{
  val month: java.time.Month = JodaMonth.JANUARY

  override def f(list: List[Day]): Month = new January(list)
}

class February(val days: List[Day]) extends Month{
  val month: java.time.Month = JodaMonth.FEBRUARY

  override def f(list: List[Day]): Month = new February(list)
}

class March(val days: List[Day]) extends Month{
  val month: java.time.Month = JodaMonth.MARCH

  override def f(list: List[Day]): Month = new March(list)
}

class April(val days: List[Day]) extends Month{
  val month: java.time.Month = JodaMonth.APRIL

  override def f(list: List[Day]): Month = new April(list)
}

class May(val days: List[Day]) extends Month{
  val month: java.time.Month = JodaMonth.MAY

  override def f(list: List[Day]): Month = new May(list)
}

class June(val days: List [Day]) extends Month{
  val month: java.time.Month = JodaMonth.JUNE

  override def f(list: List[Day]): Month = new June(list)
}

class July(val days: List [Day]) extends Month{
  val month: java.time.Month = JodaMonth.JULY

  override def f(list: List[Day]): Month = new July(list)
}

class August(val days: List [Day]) extends Month{
  val month: java.time.Month = JodaMonth.AUGUST

  override def f(list: List[Day]): Month = new August(list)
}

class September(val days: List [Day]) extends Month{
  val month: java.time.Month = JodaMonth.SEPTEMBER

  override def f(list: List[Day]): Month = new September(list)
}

class October(val days: List [Day]) extends Month{
  val month: java.time.Month = JodaMonth.OCTOBER

  override def f(list: List[Day]): Month = new October(list)
}

class November(val days: List [Day]) extends Month{
  val month: java.time.Month = JodaMonth.NOVEMBER

  override def f(list: List[Day]): Month = new November(list)

}

class December(val days: List [Day]) extends Month{
  val month: java.time.Month = JodaMonth.DECEMBER

  override def f(list: List[Day]): Month = new December(list)

}
