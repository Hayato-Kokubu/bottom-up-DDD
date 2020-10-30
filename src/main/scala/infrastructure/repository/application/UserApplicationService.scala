package infrastructure.repository.application

import application.IUserApplicationService
import domain.model._
import domain.service.UserService

// repository は外から渡すことで、振る舞いを外から帰ることができる
// userService についてもやっておきたい？
case class UserApplicationService (
  userRepository: IUserRepository,
  userFactory: IUserFactory,
) extends IUserApplicationService {
  
  override def createUser(userName: UserName): User = {
    
    val user = userFactory.create(userName)
//  val user = User(userName)// ここでつくっている
    
    val userService = UserService(userRepository) // なんか気持ち悪い: DIしたい
    
    if(!userService.exists(user)) {
      userRepository.add(user)
  
      user
    }
    else throw new IllegalArgumentException(s"その名前はすでに存在しています: ${user.name.value}") //はじく
  }
  
  override def getUser(userId: UserId): Option[UserQueryData] = {
    val target = userRepository.findById(userId)
    
    target.map(UserQueryData(_))
  }
  
  override def updateUser(userId: UserId, newName: UserName): User = {
    val target = userRepository.findById(userId)
    val updated =
      target.map(_.changeName(newName)).getOrElse(throw new IllegalArgumentException(s"このユーザは登録されていません id: $userId"))
      
    userRepository.save(updated)
    
    updated
  }
  
  override def deleteUser(userId: UserId): User = {
    val target = userRepository.findById(userId)
    
    target match {
      case Some(user) => {
        userRepository.delete(user)
        user
      }
      case None       => throw new IllegalArgumentException(s"このユーザは登録されていません id: $userId")
    }
  }
}

case class UserQueryData( id:String, name: String)
case object UserQueryData {
  def apply(user: User): UserQueryData =
    UserQueryData(user.id.value, user.name.value)
}