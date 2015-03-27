package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.data.Form
import play.api.data.Forms._
import models.User

object UserController extends Controller {
  /** Form定義 */
  val userForm = Form(
    mapping(
        "name" -> nonEmptyText,
        "email" -> email
    )
    (User.apply)
    (User.unapply)
  )

  def entryInit = Action {
    val filledForm = this.userForm.fill(User("user name", "email address"))
    Ok(views.html.user.entry(filledForm))
  }

  def entrySubmit = Action {implicit request =>
    this.userForm.bindFromRequest.fold(
        errors => {
          println("error")
          BadRequest(views.html.user.entry(errors))
        },
        success => {
          println("entry success!")
          //val user = this.userForm.bindFromRequest.get
          Ok(views.html.user.entrySubmit(success))
        })
//    val user = this.userForm.bindFromRequest.get
//    println(user)
//    Ok(views.html.user.entrySubmit())
  }
}