package infrastructure.repository

import java.util.UUID

import domain.model.{IUserFactory, User, UserId, UserName}

class UUIDUserFactory() extends IUserFactory {
  
  override def create(userName: UserName): User =
    User( UserId(UUID.randomUUID.toString), userName)
}
