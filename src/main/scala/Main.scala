import java.util.Scanner

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._

import scala.concurrent.ExecutionContext
import scala.io.StdIn

//import Main.LoopStatus.{Continue, Exit}
// import Main.LoopStatus._ ではなぜかエラー
import domain.model.UserName
import infrastructure.repository.JdbcUserRepository
import infrastructure.repository.application.UserApplicationService

// 現状はAPI層として見たい
object Main {

  // 依存関係の解決
  val module = new ApplicationModule()
  
  def main(args: Array[String]): Unit = {
    // [参考] akkaHttp のintroduction
    // https://doc.akka.io/docs/akka-http/current/introduction.html#routing-dsl-for-http-servers

    implicit val system = ActorSystem()
    implicit val executionContext = system.dispatcher
    
    val route: Route =
      // これはhealthcheck としてつかおう
      path("hello") {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
        }
      } ~
      path("users") {
        val program = module.userApplicationService
        
        
        post {
  
          import io.circe.generic.auto._
          import UserPostOutput._
  
          entity(as[UserInput]) {input =>
            val newUser = program.createUser(UserName(input.name))
            complete((StatusCodes.Created, UserPostOutput(newUser.id.value)))
          }
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

case class UserInput(
  name: String
)

case class UserPostOutput(
  id: String
)