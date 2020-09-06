package infrastructure.repository.application

import application.IUserApplicationService
import domain.model.{IUserRepository, User, UserId, UserName}

case class UserApplicationMockService(
  userRepository: IUserRepository
) extends IUserApplicationService {
  override def createUser ( userName: UserName ): User = User(UserId("aaa"), UserName("createdUser"))
  
  override def deleteUser ( userId: UserId ): User = User(UserId("aaa"), UserName("deletedUser"))
  
  override def updateUser ( userId: UserId, newName: UserName ): User = User(UserId("aaa"), UserName("updatedUser"))
  
  override def getUser ( userId: UserId ): Option[ UserQueryData ] =
    Some(
      UserQueryData(
        id = "aaa",
        name = "userName"
      )
    )
}
