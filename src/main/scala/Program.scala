import java.util.UUID

import domain.model.{IUserRepository, User, UserId}
import domain.service.UserService

// repository は外から渡すことで、振る舞いを外から帰ることができる
// userService についてもやっておきたい？
case class Program(userRepository: IUserRepository) {
  
  def createUser(userName: String): User = {
    val user = User(userName)
    
    val userService = UserService(userRepository) // なんか気持ち悪い: DIしたい
    
    if(!userService.exists(user)) {
      userRepository.add(user)
  
      user
    }
    else throw new Exception(s"その名前はすでに存在しています: ${user.name.value}") //はじく
  }
}
