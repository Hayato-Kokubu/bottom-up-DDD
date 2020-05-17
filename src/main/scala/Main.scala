import application.UserApplicationService
import domain.model.UserName
import infrastructure.repository.JdbcUserRepository

object Main extends App {
  
  
  val program = UserApplicationService(new JdbcUserRepository)
  
  val hana = program.createUser("Hana")
  
  val kana = program.updateUser(hana.id, UserName("Kana"))
  
  val res = program.getUser(kana.id)

  println(s"res = $res")
}
