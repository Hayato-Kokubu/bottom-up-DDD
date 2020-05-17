import application.UserApplicationService
import infrastructure.repository.{JdbcUserRepository}

object Main extends App {
  
  
  val program = UserApplicationService(new JdbcUserRepository)

  val tiger = program.createUser("Tiger")
  
  val expected = program.getUser(tiger.id)

  println(s"tiger = $expected")
}
