import java.util.Scanner

import domain.model.{UserId, UserName}
import infrastructure.repository.{JdbcUserRepository, JsonUserRepository}
import infrastructure.repository.application.{UserApplicationMockService, UserApplicationService}

// 現状はAPI層として見たい
object Main extends App {
  val module = new ApplicationModule()
  
  val program = module.userApplicationService
  
  printHeader()
  
  val sc = new Scanner(System.in)
  
  val userName = sc.next
  val newUser = program.createUser(UserName(userName))
  
  printResult(userName)
  
  printEnd()
  

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
  
  def printEnd(): Unit = println("end")

}

class ApplicationModule {
  import com.softwaremill.macwire._
  
  
  // ふくすういると怒られる lazy val userRepository2 = wire[JsonUserRepository]
  lazy val userApplicationService = wire[UserApplicationService] // UserApplicationService
  
  println(s"userApplicationService = $userApplicationService")
  lazy val userRepository1 = wire[JdbcUserRepository] //
  
  
}