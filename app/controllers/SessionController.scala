package controllers

//import play.api._
import play.api.mvc._
//import play.api.libs.iteratee.Enumerator

object SessionController extends Controller {
  def setSession = Action {implicit request =>
    Ok(<h1>save session.</h1>).as(HTML).withSession(
        request.session + ("date" -> new java.util.Date().toString())
        )
  }

  def getSession = Action {implicit request =>
    request.session.get("date") match {
      case Some(date) => Ok("save session page access time:%s".format(date))
      case None => Ok("you have never access in save session page.")
    }
  }

  def getFlash = Action {implicit request =>
    Ok {
        request.flash.get("msg").getOrElse("no msg!")
    }
  }

  def setFlash = Action {implicit request =>
    Redirect(routes.SessionController.getFlash).flashing("msg"->"save Flash!")
  }
}