package domain.service

import domain.model.User
import scalikejdbc._

class UserService {
  def exists(user: User): Boolean = {
    // User からuser名がひとしいものをfindし、いなければtrue さもなければ false
    // これもDBにさわりにいくので大変。。。
    Class.forName("com.mysql.cj.jdbc.Driver")
    ConnectionPool.singleton("jdbc:mysql://localhost/sample?useSSL=false", "root", "root")
    implicit val session = AutoSession
  
    val count =
      sql"select count(id)  cnt from user where name = ${user.name.value}"
        .map(rs => rs.int("cnt"))
        .single.apply()
  
    count.contains(0)
  }
}
