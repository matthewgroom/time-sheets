package services

import java.time.temporal.TemporalAdjusters
import java.time.{DayOfWeek, LocalDate}

import javax.inject.Inject
import model.{Day, TimeSheet}
import play.api.mvc._
import repository.TimeSheetRepository
import scala.concurrent.ExecutionContext.Implicits.global
import model.Month

import scala.concurrent.Future

object Authenticated extends ActionBuilder[Request] with ActionTransformer[Request, UserRequest] {
  override protected def transform[A](request: Request[A]): Future[UserRequest[A]] = {
    Future.successful(new UserRequest(request.session.get("authToken"), request))
  }
}

class UserRequest[A](val username: Option[String], request: Request[A]) extends WrappedRequest[A](request)
// class TimeSheetRequest[A](val timesheet: Map[LocalDate, List[Day]], request: Request[A]) extends WrappedRequest[A](request)
class TimeSheetRequest[A](val timesheet: List[Month], request: Request[A]) extends WrappedRequest[A](request)

class UserAction @Inject()(authService: TimeSheetService) extends Results {

  def authentication = new ActionRefiner[UserRequest, TimeSheetRequest]{
    // override protected def refine[A](request: UserRequest[A]): Future[Either[Result, TimeSheetRequest[A]]] = Future.successful{
    //   request.username.map(authService.getTimeSheet) match {
    //     // case Some(authToken)  => Right(new TimeSheetRequest[A](authToken, request))
    //     case Some(timesheet)  => Right(new TimeSheetRequest[A](timesheet, request))
    //     case None             => Left(Unauthorized("auth token not present"))
    //   }
    // }

    override protected def refine[A](request: UserRequest[A]): Future[Either[Result, TimeSheetRequest[A]]] =
      request.username match {
        case Some(username) => 
          authService.getTimeSheet(username)
            .map(timesheet => Right(new TimeSheetRequest[A](timesheet, request)))
        case None =>
          Future.successful(Left(Unauthorized("auth token not present")))
      }
  }

  def filter = new ActionFilter[TimeSheetRequest] {
    override protected def filter[A](request: TimeSheetRequest[A]): Future[Option[Result]] = {
      request.timesheet.toList match {
        case Nil => Future.successful(Some(Redirect("somewhereelse")))
        case list => Future.successful(None)
      }
    }
  }
}

class TimeSheetService @Inject()(timesheetRepository: TimeSheetRepository){

  //@volatile
  //val x = new ListBuffer[]
  implicit val localDateOrdering: Ordering[LocalDate] = Ordering.by(_.toEpochDay)

  def getTimeSheet(username: String): Future[List[Month]] = {
    timesheetRepository.get(username).map {
      case Some(ts) => ts.months
      case None => List.empty
    }
  }

  def x(days: List[Day]): List[(LocalDate, List[Day])] = {
    days match {
      case head :: tail =>
        val monday = head.date.`with`(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        if(monday == head.date){
          List((head.date, head :: tail.drop(6))) ::: x(tail.drop(6))
        } else {
          val restOfWeek = 7-head.date.getDayOfWeek.getValue
          List((monday, head :: tail.drop(restOfWeek))) ::: x(tail.drop(restOfWeek))
        }
    }
  }

  private def parseTimesheet(timesheet: TimeSheet) : List[Day] = timesheet.months.flatMap(month => month.days)


  def toWeeks(listOfDay: List[Day]) : Map[LocalDate, List[Day]] = {
    listOfDay match {
      case Nil => Map.empty[LocalDate, List[Day]]
      case days =>
        days
          .sortBy(day => day.date)
          .sliding(7, 8)
          .map(week => week.head.date -> week)
          .toMap
    }
  }

//  def createNew(username: String) : WriteResult = {
//    timesheetRepository.create(TimeSheet(username,Nil))
//  }

  //till we know more
//  def toMonths (data: Map[LocalDate,List[Day]])(username: String) = {
//    val currentWeek: List[Day] = data.values.flatten.toList
//    val existingDays: List[Day] = timesheetRepository.get(username) match {
//      case Some() => x
//      case None => Nil
//    }
//
//    val days = (currentWeek ::: existingDays).sortBy(_.date)
//  }

}
