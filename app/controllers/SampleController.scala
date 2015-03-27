/**
 *
 */
package controllers

import play.api.mvc._

/**
 * @author 1134310008768
 *
 */
object SampleController extends Controller {

  def sample1 = Action {
    Ok(views.html.index("Sample Controller#sample1"))
  }

  def sample2 = Action { request =>
    Ok(views.html.index("Sample Controller#sample2; " + request.path))
  }

  def sample3(id: Long) = Action {
    Ok(views.html.index("id:%d".format(id)))
  }

  def sample4 = Action{
    Redirect(routes.SampleController.sample1())
  }

  /** 引数に定数を取る */
  def sample5(fixedValue: String) = Action {
    println("fixedValue:" + fixedValue)
    Ok(views.html.index("fixedValue:" + fixedValue))
  }

  /** 引数にデフォルト値を取る */
  def sample6(defaultValue: Int) = Action {
    println("defaultValue:" + defaultValue)
    Ok(views.html.index("defaultValue:" + defaultValue))
  }

  def sample7(optValue: Option[String]) = Action {
    println("optValue:" + optValue)
    val res = optValue match {
        case Some(opt) => opt
        case None => "nothing"
    }
    Ok(views.html.index("optValue:" + res))
  }
}