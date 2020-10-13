import java.util.Scanner

import Main.LoopStatus.{Continue, Exit}
// import Main.LoopStatus._ ではなぜかエラー
import domain.model.UserName
import infrastructure.repository.JdbcUserRepository
import infrastructure.repository.application.UserApplicationService

// 現状はAPI層として見たい
object Main extends App {
  val module = new ApplicationModule()
  val program = module.userApplicationService
  
  val sc = new Scanner(System.in)
  
  loop(Continue)
  
  def loop(status: LoopStatus): Unit =
  status match {
    case Exit => ()
    case Continue => {
      printHeader()
  
      val userName = sc.next
      val newUser = program.createUser(UserName(userName))
  
      printResult(userName)
  
      println("continue? y/n")
      print(">")
      val flg = sc.next
  
      flg match {
        case "n" => loop(Exit)
        case  _  => loop(Continue)
      }
    }
  }

  def printHeader(): Unit = {
    println("Input yser name")
    print(">")
  }
  
  def printResult(userName: String): Unit = {
    println("-----------------")
    println("user created:")
    println("-----------------")
    println(s"- $userName")
    println("-----------------")
  }
  
  sealed trait LoopStatus
  object LoopStatus {
    case object Continue extends LoopStatus
    case object Exit extends LoopStatus
  }

}

class ApplicationModule {
  import com.softwaremill.macwire._
  
  
  // ふくすういると怒られる lazy val userRepository2 = wire[JsonUserRepository]
  lazy val userApplicationService = wire[UserApplicationService] // UserApplicationService
  
  println(s"userApplicationService = $userApplicationService")
  lazy val userRepository1 = wire[JdbcUserRepository] //
  
  
}