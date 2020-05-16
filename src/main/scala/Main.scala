import infrastructure.repository.JsonUserRepository

object Main extends App {
  
  
    val program = Program(new JsonUserRepository)

  val tiger = program.createUser("Tiger")

  println(s"tiger = $tiger")
}
