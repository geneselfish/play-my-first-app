package models

import play.api.db.DB
import play.api.Play.current
import play.Logger
import scala.slick.driver.H2Driver.simple._
import scala.slick.jdbc.meta.{ MTable, createModel }
import scala.slick.driver.JdbcDriver
import models.Tables.{ Event, EventRow }

object Events {

  /** DBコネクション */
  val database = Database.forDataSource(DB.getDataSource())

  /** 登録 */
  def create(e: EventRow) = database.withTransaction { implicit session: Session =>
    Logger.debug(Event.insertStatement)
    Event.insert(e)
  }

  /** 検索 */
  def find(eventId: String, eventNm: String): List[EventRow] =
    database.withTransaction { implicit session: Session =>

    var q = if (eventId.isEmpty) Event else Event.filter(_.eventId === eventId)
    q = if (eventNm.isEmpty) q else q.filter(_.eventNm like s"%$eventNm%")
    q = q.sortBy(_.eventNm)
    Logger.debug(q.selectStatement)

    return q.list
  }

  /** ID検索 */
  def findById(id: Int): EventRow =
    database.withTransaction { implicit session: Session =>
    Event.filter(_.id === id).first
  }

  /** 更新 */
  def update(e: EventRow) = database.withTransaction { implicit session: Session =>
    val q = Event.filter(_.id === e.id)
    Logger.debug(q.updateStatement)
    q.update(e)
  }

  /** 削除 */
  def delete(id: Int) = database.withTransaction { implicit session: Session =>
    val q = Event.filter(_.id === id)
    Logger.debug(q.deleteStatement)
    q.delete
  }


  /** Modelの取得 */
  def model = database.withSession { implicit session =>
    val tables = MTable.getTables(None, Some("TECHAPP"), None, None).list
    createModel(tables, JdbcDriver)
  }
}