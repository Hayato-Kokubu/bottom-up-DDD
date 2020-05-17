package infrastructure.repository

import domain.model.{IUserRepository, User, UserId, UserName}
import java.io.{FileWriter, PrintWriter}

import scala.io.Source
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._



class JsonUserRepository extends IUserRepository {
  val targetFileName = """./temp/json/User.json"""

  override def add ( user: User ): Unit = {
    val pw = new PrintWriter(new FileWriter(targetFileName, true))
    
    val added = JsonUser(user)
    pw.write(added.asJson.noSpaces + System.getProperty("line.separator"))
    pw.close
  }
  
  override def findByName ( userName: UserName ): Option[ User ] = {
    Source.fromFile(targetFileName).getLines
      .map{line =>
        decode[JsonUser](line) match {
          case Right(ju) => ju
          case _ => throw new IllegalArgumentException(s"invalid json : $line")
        }
      }.collectFirst{
      case ju if ju.name == userName.value =>
        User(UserId(ju.id), UserName(ju.name))
    }
  }
  
  override def findById ( userId: UserId ): Option[ User ] = {
    Source.fromFile(targetFileName).getLines
      .map{line =>
        decode[JsonUser](line) match {
          case Right(ju) => ju
          case _ => throw new IllegalArgumentException(s"invalid json : $line")
        }
      }.collectFirst{
      case ju if ju.id == userId.value =>
        User(UserId(ju.id), UserName(ju.name))
    }
  }
  
  // とりあえず。。。
  override def save ( user: User ): Unit = ???
}

case class JsonUser( id: String, name: String)
case object JsonUser {
   def apply(user: User): JsonUser =
    JsonUser(user.id.value, user.name.value)
}
