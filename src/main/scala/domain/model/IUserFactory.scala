package domain.model

trait IUserFactory {
  def create(userName: UserName): User
}
