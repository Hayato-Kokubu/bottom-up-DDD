name := "BottomUpDDD"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.1.1", // なんのためにあるんだっけ？
  "org.scalatest" %% "scalatest" % "3.1.1" % "test",
  
  // scalikeJdbc
  "org.scalikejdbc" %% "scalikejdbc"       % "3.4.1",
  "com.h2database"  %  "h2"                % "1.4.200",
  "ch.qos.logback"  %  "logback-classic"   % "1.2.3",
  
  // mySQL
  "mysql" % "mysql-connector-java" % "8.0.16",
  
  // circe
  "io.circe" %% "circe-core" % "0.12.3",
  "io.circe" %% "circe-generic" % "0.12.3",
  "io.circe" %% "circe-parser" % "0.12.3"
  
  
)



