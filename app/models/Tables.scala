package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = scala.slick.driver.H2Driver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._
  import scala.slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import scala.slick.jdbc.{GetResult => GR}
  
  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = Event.ddl ++ PlayEvolutions.ddl ++ Post.ddl ++ User.ddl
  
  /** Entity class storing rows of table Event
   *  @param id Database column ID DBType(INTEGER), AutoInc, PrimaryKey
   *  @param eventId Database column EVENT_ID DBType(VARCHAR), Length(100,true)
   *  @param eventNm Database column EVENT_NM DBType(VARCHAR), Length(100,true)
   *  @param eventDate Database column EVENT_DATE DBType(DATE)
   *  @param homepage Database column HOMEPAGE DBType(VARCHAR), Length(256,true) */
  case class EventRow(id: Int, eventId: String, eventNm: String, eventDate: Option[java.sql.Date], homepage: Option[String])
  /** GetResult implicit for fetching EventRow objects using plain SQL queries */
  implicit def GetResultEventRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[java.sql.Date]], e3: GR[Option[String]]): GR[EventRow] = GR{
    prs => import prs._
    EventRow.tupled((<<[Int], <<[String], <<[String], <<?[java.sql.Date], <<?[String]))
  }
  /** Table description of table EVENT. Objects of this class serve as prototypes for rows in queries. */
  class Event(_tableTag: Tag) extends Table[EventRow](_tableTag, Some("TECHAPP"), "EVENT") {
    def * = (id, eventId, eventNm, eventDate, homepage) <> (EventRow.tupled, EventRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, eventId.?, eventNm.?, eventDate, homepage).shaped.<>({r=>import r._; _1.map(_=> EventRow.tupled((_1.get, _2.get, _3.get, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column ID DBType(INTEGER), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column EVENT_ID DBType(VARCHAR), Length(100,true) */
    val eventId: Column[String] = column[String]("EVENT_ID", O.Length(100,varying=true))
    /** Database column EVENT_NM DBType(VARCHAR), Length(100,true) */
    val eventNm: Column[String] = column[String]("EVENT_NM", O.Length(100,varying=true))
    /** Database column EVENT_DATE DBType(DATE) */
    val eventDate: Column[Option[java.sql.Date]] = column[Option[java.sql.Date]]("EVENT_DATE")
    /** Database column HOMEPAGE DBType(VARCHAR), Length(256,true) */
    val homepage: Column[Option[String]] = column[Option[String]]("HOMEPAGE", O.Length(256,varying=true))
  }
  /** Collection-like TableQuery object for table Event */
  lazy val Event = new TableQuery(tag => new Event(tag))
  
  /** Entity class storing rows of table PlayEvolutions
   *  @param id Database column ID DBType(INTEGER), PrimaryKey
   *  @param hash Database column HASH DBType(VARCHAR), Length(255,true)
   *  @param appliedAt Database column APPLIED_AT DBType(TIMESTAMP)
   *  @param applyScript Database column APPLY_SCRIPT DBType(CLOB)
   *  @param revertScript Database column REVERT_SCRIPT DBType(CLOB)
   *  @param state Database column STATE DBType(VARCHAR), Length(255,true)
   *  @param lastProblem Database column LAST_PROBLEM DBType(CLOB) */
  case class PlayEvolutionsRow(id: Int, hash: String, appliedAt: java.sql.Timestamp, applyScript: Option[java.sql.Clob], revertScript: Option[java.sql.Clob], state: Option[String], lastProblem: Option[java.sql.Clob])
  /** GetResult implicit for fetching PlayEvolutionsRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[java.sql.Clob]], e4: GR[Option[String]]): GR[PlayEvolutionsRow] = GR{
    prs => import prs._
    PlayEvolutionsRow.tupled((<<[Int], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Clob], <<?[java.sql.Clob], <<?[String], <<?[java.sql.Clob]))
  }
  /** Table description of table PLAY_EVOLUTIONS. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutions(_tableTag: Tag) extends Table[PlayEvolutionsRow](_tableTag, Some("TECHAPP"), "PLAY_EVOLUTIONS") {
    def * = (id, hash, appliedAt, applyScript, revertScript, state, lastProblem) <> (PlayEvolutionsRow.tupled, PlayEvolutionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, hash.?, appliedAt.?, applyScript, revertScript, state, lastProblem).shaped.<>({r=>import r._; _1.map(_=> PlayEvolutionsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column ID DBType(INTEGER), PrimaryKey */
    val id: Column[Int] = column[Int]("ID", O.PrimaryKey)
    /** Database column HASH DBType(VARCHAR), Length(255,true) */
    val hash: Column[String] = column[String]("HASH", O.Length(255,varying=true))
    /** Database column APPLIED_AT DBType(TIMESTAMP) */
    val appliedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("APPLIED_AT")
    /** Database column APPLY_SCRIPT DBType(CLOB) */
    val applyScript: Column[Option[java.sql.Clob]] = column[Option[java.sql.Clob]]("APPLY_SCRIPT")
    /** Database column REVERT_SCRIPT DBType(CLOB) */
    val revertScript: Column[Option[java.sql.Clob]] = column[Option[java.sql.Clob]]("REVERT_SCRIPT")
    /** Database column STATE DBType(VARCHAR), Length(255,true) */
    val state: Column[Option[String]] = column[Option[String]]("STATE", O.Length(255,varying=true))
    /** Database column LAST_PROBLEM DBType(CLOB) */
    val lastProblem: Column[Option[java.sql.Clob]] = column[Option[java.sql.Clob]]("LAST_PROBLEM")
  }
  /** Collection-like TableQuery object for table PlayEvolutions */
  lazy val PlayEvolutions = new TableQuery(tag => new PlayEvolutions(tag))
  
  /** Entity class storing rows of table Post
   *  @param id Database column ID DBType(INTEGER), AutoInc, PrimaryKey
   *  @param userid Database column USERID DBType(INTEGER)
   *  @param title Database column TITLE DBType(VARCHAR), Length(100,true)
   *  @param body Database column BODY DBType(CLOB)
   *  @param createdate Database column CREATEDATE DBType(TIMESTAMP) */
  case class PostRow(id: Int, userid: Int, title: String, body: Option[java.sql.Clob], createdate: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching PostRow objects using plain SQL queries */
  implicit def GetResultPostRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[java.sql.Clob]], e3: GR[Option[java.sql.Timestamp]]): GR[PostRow] = GR{
    prs => import prs._
    PostRow.tupled((<<[Int], <<[Int], <<[String], <<?[java.sql.Clob], <<?[java.sql.Timestamp]))
  }
  /** Table description of table POST. Objects of this class serve as prototypes for rows in queries. */
  class Post(_tableTag: Tag) extends Table[PostRow](_tableTag, Some("TECHAPP"), "POST") {
    def * = (id, userid, title, body, createdate) <> (PostRow.tupled, PostRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, userid.?, title.?, body, createdate).shaped.<>({r=>import r._; _1.map(_=> PostRow.tupled((_1.get, _2.get, _3.get, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column ID DBType(INTEGER), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column USERID DBType(INTEGER) */
    val userid: Column[Int] = column[Int]("USERID")
    /** Database column TITLE DBType(VARCHAR), Length(100,true) */
    val title: Column[String] = column[String]("TITLE", O.Length(100,varying=true))
    /** Database column BODY DBType(CLOB) */
    val body: Column[Option[java.sql.Clob]] = column[Option[java.sql.Clob]]("BODY")
    /** Database column CREATEDATE DBType(TIMESTAMP) */
    val createdate: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("CREATEDATE")
    
    /** Foreign key referencing User (database name CONSTRAINT_259) */
    lazy val userFk = foreignKey("CONSTRAINT_259", userid, User)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table Post */
  lazy val Post = new TableQuery(tag => new Post(tag))
  
  /** Entity class storing rows of table User
   *  @param id Database column ID DBType(INTEGER), AutoInc, PrimaryKey
   *  @param name Database column NAME DBType(VARCHAR), Length(100,true)
   *  @param email Database column EMAIL DBType(VARCHAR), Length(100,true)
   *  @param password Database column PASSWORD DBType(VARCHAR), Length(100,true)
   *  @param createdate Database column CREATEDATE DBType(TIMESTAMP) */
  case class UserRow(id: Int, name: Option[String], email: Option[String], password: Option[String], createdate: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[java.sql.Timestamp]]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String], <<?[java.sql.Timestamp]))
  }
  /** Table description of table USER. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends Table[UserRow](_tableTag, Some("TECHAPP"), "USER") {
    def * = (id, name, email, password, createdate) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name, email, password, createdate).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2, _3, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column ID DBType(INTEGER), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column NAME DBType(VARCHAR), Length(100,true) */
    val name: Column[Option[String]] = column[Option[String]]("NAME", O.Length(100,varying=true))
    /** Database column EMAIL DBType(VARCHAR), Length(100,true) */
    val email: Column[Option[String]] = column[Option[String]]("EMAIL", O.Length(100,varying=true))
    /** Database column PASSWORD DBType(VARCHAR), Length(100,true) */
    val password: Column[Option[String]] = column[Option[String]]("PASSWORD", O.Length(100,varying=true))
    /** Database column CREATEDATE DBType(TIMESTAMP) */
    val createdate: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("CREATEDATE")
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))
}