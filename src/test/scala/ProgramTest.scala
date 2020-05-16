import domain.model.UserName
import infrastructure.repository.InMemoryUserRepository
import org.scalatest._
import org.scalatest.matchers.should.Matchers

class ProgramTest extends FlatSpec with Matchers{
  behavior of "createUser"
  val testUserRepo = new InMemoryUserRepository
  val target = Program(testUserRepo)
  
  
  it should "同じ名前のuserがいない場合にuserを登録できる" in {
  
    target.createUser("testUser")
    
    testUserRepo.store.values.map(_.name).toSet shouldEqual Set(UserName("testUser"))
  }
  
  it should "同じ名前のuserがある場合には例外を返す" in {
    
    target.createUser("existingUser")
  
    a [IllegalArgumentException] should be thrownBy{
      target.createUser("existingUser")
    }
    
    
    
  
  }
  
}
