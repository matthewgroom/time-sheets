package services

import model.{LoginDetails, User}
import repository.MongoUserRepository

import scala.concurrent.Future

class AuthService (database : MongoUserRepository){

  def exists(username: String) : Future[Option[User]] = {
    database.checkUsername(username)
  }

  def addUserDetails() = {
    println("User Name: ")
    val username = scala.io.StdIn.readLine()
    println("Password: ")
    val password = scala.io.StdIn.readLine()
    LoginDetails(username, password)
  }

  def remove(username: String) ={
    database.remove(username)
  }


}
