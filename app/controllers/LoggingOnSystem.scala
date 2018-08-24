package controllers

import java.time.{LocalDate, Month}

import javax.inject.Inject
import play.api.mvc._
import services.{Authenticated, UserAction}

import scala.annotation.tailrec
import scala.concurrent.Future
import views._
/**
  * Created by temp on 11/01/18.
  */

class LoggingOnSystem @Inject()(auth: UserAction) extends Controller {

  def displayAuth =  Action.async { implicit request =>

   // Future.successful(Ok(html.index()))
   Future.successful(Ok)

  }

  def inputUsername(): String = {
    println("User Name: ")
    scala.io.StdIn.readLine()
  }


  def successfulAuth = Authenticated.async { implicit request =>
    ???
  }

  // def displayTimesheet = Authenticated.andThen(auth.authentication).andThen(auth.filter){

  // }
  //form mappings

}
