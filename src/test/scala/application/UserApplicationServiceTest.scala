package application

import domain.model.UserName
import infrastructure.repository.InMemoryUserRepository
import infrastructure.repository.application.UserApplicationService
import org.scalatest._
import org.scalatest.matchers.should.Matchers

class UserApplicationServiceTest extends FlatSpec with Matchers{
  behavior of "createUser"
  val testUserRepo = new InMemoryUserRepository
  val target = UserApplicationService(testUserRepo)
  
  
  it should "同じ名前のuserがいない場合にuserを登録できる" in {
  
    target.createUser(UserName("testUser"))
    
    testUserRepo.store.values.map(_.name).toSet shouldEqual Set(UserName("testUser"))
  }
  
  it should "同じ名前のuserがある場合には例外を返す" in {
    
    target.createUser(UserName("existingUser"))
  
    a [IllegalArgumentException] should be thrownBy{
      target.createUser(UserName("existingUser"))
    }
    
    
    
  
  }
  
}
