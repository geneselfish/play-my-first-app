package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.data.Form
import play.api.data.Forms._
import models._
import services.UserService

object UserController extends Controller {
  /** Form定義 */
  val userForm = Form(
//    mapping(
    tuple(
        "name" -> nonEmptyText,
        "email" -> email,
        "password" -> nonEmptyText
    )
//    (User.apply)
//    (User.unapply)
  )

  def entryInit = Action { implicit request =>
//    val filledForm = this.userForm.fill(User("user name", "email address"))
    val filledForm = this.userForm.fill("user name", "email address", "password")
    Ok(views.html.user.entry(request.flash.get("result").getOrElse(""), filledForm))
  }

  def entrySubmit = Action {implicit request =>
    this.userForm.bindFromRequest.fold(
        errors => {
          println("error")
          BadRequest(views.html.user.entry("error", errors))
        },
        success => {
          println("entry success!")
          val (name, email, password) = success
          val id = UserService.entry(name, email, password)
          UserService.findByPk(id) match {
            case Some(u) => Ok(views.html.user.entrySubmit(u))
            case None => Redirect(controllers.routes.UserController.entryInit)//"/user/entry"
                         .flashing("result" -> "user (%d) not found".format(id))
          }
          //val user = this.userForm.bindFromRequest.get
          //Ok(views.html.user.entrySubmit(success))
        }
    )
//    val user = this.userForm.bindFromRequest.get
//    println(user)
//    Ok(views.html.user.entrySubmit())
  }
}