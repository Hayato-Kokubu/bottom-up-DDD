package infrastructure.repository

import domain.model.{IUserRepository, User, UserId, UserName}

class InMemoryUserRepository extends IUserRepository {
  
  // 内部で連想配列を持つ 状態を持つのでver にする 何かいい方法はない？
  var store : Map[UserId, User] = Map.empty
  
  override def add ( user: User ): Unit = {
    store = store + (user.id -> user)
    ()
  }
  
  override def findByName ( userName: UserName ): Option[ User ] =
    store.collectFirst{ case (_, user) if user.name == userName => user}
  
  override def findById ( userId: UserId ): Option[ User ] =
    store.collectFirst{ case (_, user) if user.id == userId => user}
}
