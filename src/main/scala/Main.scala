import domain.model.FullName

object Main extends App {

  val fullName1 = FullName("first","last")
	val fullName2 = FullName("first1","last")
	val fullName3 = FullName("first","last2")
	val fullName4 = FullName("first","last")
	
  println(fullName1)
	println(fullName1 == fullName2)
	println(fullName1 == fullName3)
	println(fullName1 == fullName4)
}
