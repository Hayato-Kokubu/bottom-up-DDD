import application.UserApplicationService
import infrastructure.repository.JsonUserRepository

object Main extends App {
  
  
    val program = UserApplicationService(new JsonUserRepository)

  val tiger = program.createUser("Tiger")

  println(s"tiger = $tiger")
}
