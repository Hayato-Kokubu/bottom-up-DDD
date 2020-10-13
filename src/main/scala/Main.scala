import domain.model.UserId
import infrastructure.repository.{JdbcUserRepository, JsonUserRepository}
import infrastructure.repository.application.{UserApplicationMockService, UserApplicationService}

// 現状はAPI層として見たい
object Main extends App {
  val module = new ApplicationModule()
  
  // 現状では、ここで Mock か 実際のService かを選んでいる。
  val program = module.userApplicationService //UserApplicationMockService(new JdbcUserRepository)
  
  val a = program.getUser(UserId("abc"))
  
  println(s"a = $a")

}

class ApplicationModule {
  import com.softwaremill.macwire._
  
  
  // ふくすういると怒られる lazy val userRepository2 = wire[JsonUserRepository]
  lazy val userApplicationService = wire[UserApplicationService] // UserApplicationService
  
  println(s"userApplicationService = $userApplicationService")
  lazy val userRepository1 = wire[JdbcUserRepository] //
  
  
}