package infrastructure.repository

import java.util.UUID

import domain.model.{IUserRepository, User, UserId, UserName}
import scalikejdbc._

class JdbcUserRepository extends IUserRepository {
  Class.forName("com.mysql.cj.jdbc.Driver")
  ConnectionPool.singleton("jdbc:mysql://localhost/bottom_up_ddd?useSSL=false", "bottom_up_ddd_user", "bottom_up_ddd")
  
  implicit val session = AutoSession
  val u = UserRecord.syntax("u")
  val cols = UserRecord.column
  
  override def add ( user: User ): Unit = {
    withSQL {
      insert.into(UserRecord)
        .namedValues(
          cols.id -> user.id.value,
          cols.name -> user.name.value
        )
    }.update.apply()
  }
  
  
  override def findByName ( userName: UserName ): Option[ User ] =
    withSQL {
      select
        .from(UserRecord as u)
        .where
        .eq(u.name, userName.value)
    }.map{ rs =>
      val r = UserRecord(u.resultName)(rs)
      User(
        UserId(r.id),
        UserName(r.name)
      )
    }.single.apply()
  
  override def findById ( userId: UserId ): Option[ User ] = {
    withSQL {
      select
        .from(UserRecord as u)
        .where.eq(u.id, userId.value)
    }.map { rs =>
      val r = UserRecord(u.resultName)(rs)
      User (
        id = UserId ( r.id ),
        name = UserName ( r.name )
      )
    }.single.apply()
  }
  
  override def save ( user: User ): Unit =
    withSQL {
      update(UserRecord as u)
        .set( cols.name -> user.name.value)
        .where
        .eq(u.id, user.id.value)
    }.update.apply()
  
  override def delete ( user: User ): Unit =
    withSQL {
      QueryDSL.delete // method のdelete と重複するため
        .from(UserRecord as u)
        .where
        .eq(u.id, user.id.value)
    }.update.apply()
}

case class UserRecord(
  id: String,
  name: String
)

object UserRecord extends SQLSyntaxSupport[UserRecord]{
  
  override val tableName = "User"
  override val columns = Seq("id", "name")
  
  def apply(st: ResultName[UserRecord])(rs: WrappedResultSet): UserRecord =
    autoConstruct(rs, st)
}