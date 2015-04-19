package services

import play.api.db.DB
import play.api.Play.current
import scala.slick.driver.H2Driver.simple._
import models.Tables.{User, UserRow}

object UserService {
    /** DBコネクション */
  val database = Database.forDataSource(DB.getDataSource())

  /** ID検索 */
  def findByPk(id: Int): Option[UserRow] =
    database.withTransaction { implicit session: Session =>
    val list = User.filter(_.id === id).list
    if(list.isEmpty) None else Some(list.head)
  }

  /** 登録 */
  def entry(name: String, email: String, password: String): Int = {
    this.entry(UserRow(0, Some(name), Some(email), Some(password), None))
  }

  def entry(u: UserRow) = database.withTransaction { implicit session: Session =>
    //Logger.debug(Event.insertStatement)
    User.insert(u)
  }
}