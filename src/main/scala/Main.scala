import infrastructure.repository.JdbcUserRepository

object Main extends App {
  
  val program = Program(new JdbcUserRepository)
  
  val ken = program.createUser("Ken")
  
  println(s"tom = $ken")
}
