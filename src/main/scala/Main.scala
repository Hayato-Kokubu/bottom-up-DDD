import application.UserApplicationService
import domain.model.UserName
import infrastructure.repository.JdbcUserRepository

// 現状はAPI層として見たい
object Main extends App {
  
  
  val program = UserApplicationService(new JdbcUserRepository)
  
  val hanaChan = program.createUser(UserName("HanaChan"))
  
  val kanaChan = program.updateUser(hanaChan.id, UserName("KanaChan"))
  
  val res = program.getUser(kanaChan.id)

  println(s"res = $res")
}
