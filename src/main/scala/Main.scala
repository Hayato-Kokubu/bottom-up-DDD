import domain.model.UserId
import infrastructure.repository.JdbcUserRepository
import infrastructure.repository.application.{UserApplicationMockService, UserApplicationService}

// 現状はAPI層として見たい
object Main extends App {
  
  // 現状では、ここで Mock か 実際のService かを選んでいる。
  val program = UserApplicationMockService(new JdbcUserRepository)
  
  val a = program.getUser(UserId("abc"))
  
  println(s"a = $a")
  

}
