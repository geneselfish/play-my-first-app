package controllers.event

import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import scala.slick.codegen.SourceCodeGenerator
import models.Tables.EventRow
import models.EventForm
import models.Events

object EventCreate extends Controller {
  /** イベントフォーム */
  val eventForm = Form(
    mapping(
      "eventId" -> nonEmptyText,
      "eventNm" -> nonEmptyText.verifying(maxLength(5))
      )
      (EventForm.apply)(EventForm.unapply)
  )

  /** 初期表示 */
  def index = Action {
    Ok(views.html.event.eventCreate(eventForm))
  }

  /** 登録 */
  def create = Action { implicit request =>
    this.eventForm.bindFromRequest.fold(
        errors => Ok(views.html.event.eventCreate(errors)),
        success => {
          val event = EventRow(0, success.eventId, success.eventNm)
          Events.create(event)
          Redirect(controllers.event.routes.EventCreate.index)
        }
    )
//    val form = eventForm.bindFromRequest.get
//    val event = EventRow(0, form.eventId, form.eventNm)
//    Events.create(event)
//    Ok(views.html.event.eventCreate(eventForm))
  }


  /** コード生成 */
  def generate = Action {
    val slickDriver = "scala.slick.driver.H2Driver"
    val outputFolder = "app"
    val pkg = "models"
    val model = Events.model
    new SourceCodeGenerator(model).writeToFile(slickDriver, outputFolder, pkg)

    Ok(views.html.event.eventCreate(eventForm))
  }
}