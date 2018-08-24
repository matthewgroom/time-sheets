package repository

import javax.inject.{Inject, Named}
import model.User
import play.api.libs.json.Json
import play.modules.reactivemongo.{ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.commands.{UpdateWriteResult, WriteResult}
import reactivemongo.api.{Collection, DefaultDB, MongoConnection, MongoDriver}
import reactivemongo.bson.{BSONDocumentWriter, Macros}
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class MongoUserRepository @Inject()(
                                          val reactiveMongoApi: ReactiveMongoApi,
                                          @Named("collectionName") val collectionName: String
                                        ) extends MongoTimeSheet {

//  implicit val query = Json.obj("some" -> value)

  implicit def userWriter: BSONDocumentWriter[User] = Macros.writer[User]

  def create(user: User): Future[WriteResult] = {
    coll.flatMap(_.insert(user))
  }

  def checkUsername(username: String) =
    coll.flatMap(_.find(Json.obj("id" -> username)).one[User])

  private def get(username: String) = {
    coll.flatMap(_.find(Json.obj("id" -> "username")).one[User])
//      .findOneByID(username)
//      .map(user => grater[User].asObject(user))
  }

  private def change(username: String, u: User) = {
    val q = Json.obj("_id" -> username)
    coll.flatMap(_.update(q, update = u).map{
      case UpdateWriteResult(true, _, _, _, _, _, _, _)   => Success
      case UpdateWriteResult(false, _, _, _, _, _, _, _)  => Failure
    })
  }

  def passwordChange(user: User) = {
    change(user.userName, user)
  }

  def remove(username: String) = {
    coll.flatMap(_.remove(Json.obj("_id" -> username)))
  }
}

abstract class MongoStuff(collectionName: String) extends ReactiveMongoComponents {

  //val mongoClient = MongoClient("localhost", 27018)
  //val db = mongoClient("test")
  //val collection = db("test")

  //  val collection = db[BSONCollection](“demodb”

  //  private val mongoUri = "mongodb://localhost:27017/mydb?authMode=scram-sha1"
  //  import scala.concurrent.ExecutionContext.Implicits.global
  //  import scala.concurrent.Future // use any appropriate context
  //
  //  // Connect to the database: Must be done only once per application
  //  private val driver = MongoDriver()
  //  private val parsedUri = MongoConnection.parseURI(mongoUri)
  //  private val connection = parsedUri.map(driver.connection(_))
  //  // Database and collections: Get references
  //  private val futureConnection: Future[MongoConnection] = Future.fromTry(connection)
  //  private def db1: Future[DefaultDB] = futureConnection.flatMap(_.database("matt-project"))
  //  lazy val collection: Future[JSONCollection] = db1.map(_.collection(collectionName))
  //
  //}

  sealed trait MongoResult

  case object Success extends MongoResult

  case object Failure extends MongoResult

}
