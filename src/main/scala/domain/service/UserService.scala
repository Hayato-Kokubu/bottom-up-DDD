package domain.service

import domain.model.{IUserRepository, User}

case class UserService( userRepository: IUserRepository){
  def exists(user: User): Boolean = userRepository.find(user.name).nonEmpty
}
