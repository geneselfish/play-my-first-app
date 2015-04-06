package controllers.event

import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.data.Form
import play.api.data.Forms._
import xenlon.api.data.validation.XenlonConstraints._
import models.{ EventSearchForm, Events }

object EventSearch extends Controller {
  /** イベントフォーム */
  val eventForm = Form(
    mapping(
      "eventId" -> text.verifying(fixLength(5)),
      "eventNm" -> text.verifying(maxLength(5)),
      "eventDateFrom" -> optional(sqlDate),
      "eventDateTo" -> optional(sqlDate)
      )(EventSearchForm.apply)(EventSearchForm.unapply))

  /** 初期表示 */
  def index = Action { implicit request =>
    Ok(views.html.event.eventSearch(eventForm, null))
  }

  /** 検索 */
  def search = Action { implicit request =>
    this.eventForm.bindFromRequest.fold(
        errors => Ok(views.html.event.eventSearch(errors, null)),
        success => {
          if (success.eventDateFrom.isDefined
              && success.eventDateTo.isDefined
              && success.eventDateFrom.get.after(success.eventDateTo.get)) {
            BadRequest(views.html.event.eventSearch(eventForm.bindFromRequest
            .withError("eventDateFrom", "開催日（From）は開催日（To）より前の日付を入力してください。")
            .withError("eventDateTo", ""), null))
          } else {
            val events = Events.find(success.eventId, success.eventNm, success.eventDateFrom, success.eventDateTo)
            Ok(views.html.event.eventSearch(this.eventForm.bindFromRequest, events))
          }
        }
    )
//    val form = eventForm.bindFromRequest
//    val events = Events.find(form.get.eventId, form.get.eventNm)
//    Ok(views.html.event.eventSearch(form, events))
  }

  /** 削除 */
  def delete(id: Int) = Action { implicit request =>
    Events.delete(id)
//    Ok(views.html.event.eventSearch(eventForm, null)).flashing("success" -> "削除しました。")
    Redirect(controllers.event.routes.EventSearch.index).flashing("success" -> "削除しました。")
  }
}