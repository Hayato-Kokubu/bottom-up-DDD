package application

import domain.model.{User, UserId, UserName}
import infrastructure.repository.application.UserQueryData

trait IUserApplicationService {
  def createUser(userName: UserName): User
  def getUser(userId: UserId): Option[UserQueryData]
  def updateUser(userId: UserId, newName: UserName): User
  def deleteUser(userId: UserId): User
}
