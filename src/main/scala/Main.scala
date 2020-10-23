import java.util.Scanner

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import scala.io.StdIn

//import Main.LoopStatus.{Continue, Exit}
// import Main.LoopStatus._ ではなぜかエラー
import domain.model.UserName
import infrastructure.repository.JdbcUserRepository
import infrastructure.repository.application.UserApplicationService

// 現状はAPI層として見たい
object Main {
  val module = new ApplicationModule()
  val program = module.userApplicationService
  
  def main(args: Array[String]): Unit = {
    // [参考]
    // https://doc.akka.io/docs/akka-http/current/introduction.html#routing-dsl-for-http-servers
    implicit val system = ActorSystem()

    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher
    
    val route: Route =
      path("hello") {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
        }
      }
    val bindingFuture = Http().bindAndHandle(route,"localhost", 8080)
    
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    
    bindingFuture.flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
//  val sc = new Scanner(System.in)
//
//  loop(Continue)
//
//  def loop(status: LoopStatus): Unit =
//  status match {
//    case Exit => ()
//    case Continue => {
//      printHeader()
//
//      val userName = sc.next
//      val newUser = program.createUser(UserName(userName))
//
//      printResult(userName)
//
//      println("continue? y/n")
//      print(">")
//      val flg = sc.next
//
//      flg match {
//        case "n" => loop(Exit)
//        case  _  => loop(Continue)
//      }
//    }
//  }
//
//  def printHeader(): Unit = {
//    println("Input yser name")
//    print(">")
//  }
//
//  def printResult(userName: String): Unit = {
//    println("-----------------")
//    println("user created:")
//    println("-----------------")
//    println(s"- $userName")
//    println("-----------------")
//  }
//
//  sealed trait LoopStatus
//  object LoopStatus {
//    case object Continue extends LoopStatus
//    case object Exit extends LoopStatus
//  }

}

class ApplicationModule {
  import com.softwaremill.macwire._
  
  
  // ふくすういると怒られる lazy val userRepository2 = wire[JsonUserRepository]
  lazy val userApplicationService = wire[UserApplicationService] // UserApplicationService
  
  println(s"userApplicationService = $userApplicationService")
  lazy val userRepository1 = wire[JdbcUserRepository] //
  
  
}