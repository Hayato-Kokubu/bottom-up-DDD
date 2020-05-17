package application

import domain.model.{IUserRepository, User, UserId}
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
  
  def getUser(userId: UserId): Option[UserQueryData] = {
    val target = userRepository.findById(userId)
    
    target.map(UserQueryData(_))
  }
}

case class UserQueryData( id:String, name: String)
case object UserQueryData {
  def apply(user: User): UserQueryData =
    UserQueryData(user.id.value, user.name.value)
}