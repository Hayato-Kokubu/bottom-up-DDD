import domain.model.User
import domain.service.UserService

object Main extends App {
  def createUser(userName: String): User = {
    val user = User(userName)
    
    val userService = new UserService // なんか気持ち悪い: DIしたい

    // 重複確認
    if (userService.exists(user)) {
       //DBに登録しに行くが、これがクソ長いのでどうにかしたい
      ???
    }
    else throw new Exception("その名前はすでに存在しています") //はじく
  }
  
  
  val u1 = createUser("firstUser")
  
  println(u1)
}
