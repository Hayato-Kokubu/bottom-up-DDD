package application

import domain.model.{IUserRepository, User}
import domain.service.UserService

// repository は外から渡すことで、振る舞いを外から帰ることができる
// userService についてもやっておきたい？
case class UserApplicationService ( userRepository: IUserRepository) {
  
  def createUser(userName: String): User = {
    val user = User(userName)
    
    val userService = UserService(userRepository) // なんか気持ち悪い: DIしたい
    
    if(!userService.exists(user)) {
      userRepository.add(user)
  
      user
    }
    else throw new IllegalArgumentException(s"その名前はすでに存在しています: ${user.name.value}") //はじく
  }
}
