package domain.model

import org.scalatest._
import org.scalatest.matchers.should.Matchers

class FullNameTest extends FlatSpec with Matchers {
  
  behavior of "AccountCode"
  
  it should "fullNameの出力ができる" in {
    val fullName = FullName("first", "last")
    
    fullName.toString shouldEqual "first last"
  }

  it should "fullName の比較ができる" in {
  
    val origin = FullName("first","last")
    val firstNameNotEqual = FullName("first1","last")
    val lastNameNotEqual = FullName("first","last2")
    val equalOne = FullName("first","last")
    
    origin == firstNameNotEqual shouldEqual false
    origin == lastNameNotEqual shouldEqual false
    origin == equalOne shouldEqual true
  }
}