package controllers.event

import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.data.Form
import play.api.data.Forms._
import xenlon.api.data.validation.XenlonConstraints._
import xenlon.api.data.XenlonForms._
import models.{ EventForm, Events }
import models.Tables.EventRow

object EventUpdate extends Controller {
  /** イベントフォーム */
  val eventForm = Form(
    mapping(
      "eventId" -> nonEmptyText.verifying(fixLength(5)),
      "eventNm" -> nonEmptyText.verifying(maxLength(5)),
      "eventDate" -> optional(sqlDate),
      "homepage" -> optional(url)//text.verifying(isUrl)
      )
      (EventForm.apply)(EventForm.unapply))

  /** 初期表示 */
  def index(id: Int) = Action {
    val event = Events.findById(id)
    val form = EventForm(event.eventId, event.eventNm, event.eventDate, event.homepage)

    Ok(views.html.event.eventUpdate(eventForm.fill(form), id))
  }

  /** 更新 */
  def update(id: Int) = Action { implicit request =>
    this.eventForm.bindFromRequest.fold(
        errors => Ok(views.html.event.eventUpdate(errors, id)),
        success => {
          val event = EventRow(id, success.eventId, success.eventNm, success.eventDate, success.homepage)
          Events.update(event)
          Redirect(controllers.event.routes.EventUpdate.index(id))
        }
    )
//    val form = eventForm.bindFromRequest.get
//    val event = EventRow(id, form.eventId, form.eventNm)
//    Events.update(event)
//    Ok(views.html.event.eventUpdate(eventForm, id))
  }
}