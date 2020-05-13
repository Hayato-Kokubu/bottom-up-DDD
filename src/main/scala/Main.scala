object Main extends App {
  val program = new Program
  
  val tom = program.createUser("Tom")
  
  println(s"tom = $tom")
}
