package infrastructure.repository

import java.util.UUID

import domain.model.{IUserRepository, User, UserId, UserName}
import scalikejdbc._

class JdbcUserRepository extends IUserRepository {
  Class.forName("com.mysql.cj.jdbc.Driver")
  ConnectionPool.singleton("jdbc:mysql://localhost/sample?useSSL=false", "root", "root")
  
  implicit val session = AutoSession
  
  
  override def add ( user: User ): Unit =
    sql"insert into user values (${UUID.randomUUID.toString}, ${user.name.value})".update.apply()
  
  
  override def find ( userName: UserName ): Option[ User ] = {
    val entities: Option[Map[String, Any]] =
      sql"select * from User where name = ${userName.value}"
        .map(_.toMap).single.apply()
  
    val maybeUser =
      entities.map{ e =>
        User( UserId(e("id").toString), UserName(e("name").toString))
      }
  
    maybeUser
  }

}
