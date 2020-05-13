import java.util.UUID

import domain.model.{User, UserId}
import domain.service.UserService
import scalikejdbc._

class Program {
  
  def createUser(userName: String): User = {
    val user = User(userName)
    
    val userService = new UserService // なんか気持ち悪い: DIしたい
    
    // initialize JDBC driver & connection pool
    
    if(userService.exists(user)) {
      // 重複確認
      //DBに登録しに行くが、これがクソ長いのでどうにかしたい
      val id = UUID.randomUUID.toString
      
      implicit val session = AutoSession
      
      Class.forName("com.mysql.cj.jdbc.Driver")
      ConnectionPool.singleton("jdbc:mysql://localhost/sample?useSSL=false", "root", "root")
      
      sql"insert into user values (${UUID.randomUUID.toString}, ${user.name.value})".update.apply()
      
      User(UserId(id), user.name)
    }
    else throw new Exception(s"その名前はすでに存在しています: ${user.name.value}") //はじく
  }
}
