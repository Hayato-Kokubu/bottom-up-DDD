package domain.model

trait IUserRepository {
  
  // save (更新) と add(追加) を分けて考えたい
  // 今回はUnit だが、User を返す流派もある？
  def add(user: User): Unit
  
  // findByName
  def find(userName: UserName): Option[User]
}
