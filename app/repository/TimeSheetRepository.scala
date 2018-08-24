package repository

import com.typesafe.config.ConfigException.Generic
import javax.inject.{Inject, Named}
import model.TimeSheet
import model.TimeSheet._
import play.api.libs.json.Json.JsValueWrapper
import play.api.libs.json._
import play.modules.reactivemongo.json.collection._
import play.modules.reactivemongo.{ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.commands.{UpdateWriteResult, WriteResult}
import reactivemongo.api.{DB, MongoConnection, MongoDriver, ReadPreference}
import reactivemongo.bson.BSONDocument
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by temp on 29/01/18.
  */
class MongoTimesheetRepository @Inject()(
                                          val reactiveMongoApi: ReactiveMongoApi,
                                          @Named("collectionName") val collectionName: String
                                        ) extends TimeSheetRepository {

  type A = WriteResult

  def create(timesheet: TimeSheet): Future[WriteResult] = {
    coll.flatMap(_.insert[TimeSheet](timesheet))
  }

  def get(username: String): Future[Option[TimeSheet]] = {
    coll.flatMap(
      _.find(Json.obj("_id" -> username))
        .one[TimeSheet](ReadPreference.primaryPreferred)
      )
  }

  def timeChange(username: String, timeSheet: TimeSheet): Future[MongoResult] = {
    change(username, timeSheet)
  }

  private def change(username: String, update: TimeSheet): Future[MongoResult] = {
    val query = Json.obj("_id" -> username)
    coll.flatMap(_.update(selector = query, update = update).map{
      case UpdateWriteResult(true, _, _, _, _, _, _, _)   => Success
      case UpdateWriteResult(false, _, _, _, _, _, _, _)  => Failure
    })
  }
}

trait TimeSheetRepository extends MongoTimeSheet {

  // type A

//  def create(timesheet: TimeSheet): Future[TimeSheet]

  def get(username: String): Future[Option[TimeSheet]]

  def timeChange(username: String, timeSheet: TimeSheet): Future[MongoResult]
}

trait MongoTimeSheet extends ReactiveMongoComponents {

  val collectionName: String
  val reactiveMongoApi: ReactiveMongoApi
  val coll = reactiveMongoApi.database.map(_.collection(collectionName))

sealed trait MongoResult

case object Success extends MongoResult

case object Failure extends MongoResult

}
