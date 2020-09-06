import application.UserApplicationService
import domain.model.{UserId, UserName}
import infrastructure.repository.JdbcUserRepository

// 現状はAPI層として見たい
object Main extends App {
  
  
  val program = UserApplicationService(new JdbcUserRepository)
  
  // 本当ならTestでかくべきものだが。。。いったんmainでやる
  
  // createUser のtest
  val a = program.createUser(UserName("TestUser"))
  val actual = program.getUser(userId = a.id).get.name
  
  println(s"actual = $actual")
  
  // deleteUser のtest
  val d = program.getUser(userId = a.id).get.name
  val deleteRes =  program.deleteUser(userId = a.id)
  val dres = program.getUser(userId = a.id)
  println(s"dres =  $dres")
  
  // getUser のtest
  val b = program.getUser(userId = UserId("983d859d-2f2a-4fa5-9e5d-4b570d88748a"))
  println(s"b = $b")
  
  // updateUser のtest
  val u =  program.createUser(UserName("TestUser"))
  val updated = program.updateUser(userId = u.id, newName = UserName("UpdatedUser"))
  val updateRes = program.getUser(updated.id)
  println(s"updatedRes = ${updateRes.get.name}")
  program.deleteUser(updated.id)
  
}
