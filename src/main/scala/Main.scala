import domain.model.User

object Main extends App {
  def createUser(userName: String): User = User(userName)
  
  
  val u1 = createUser("firstUser")
  
  println(u1)
}
