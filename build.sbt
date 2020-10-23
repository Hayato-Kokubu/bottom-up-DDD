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
  "org.scalikejdbc" %% "scalikejdbc-syntax-support-macro" % "3.5.0",
  
  // mySQL
  "mysql" % "mysql-connector-java" % "8.0.16",
  
  // circe
  "io.circe" %% "circe-core" % "0.12.3",
  "io.circe" %% "circe-generic" % "0.12.3",
  "io.circe" %% "circe-parser" % "0.12.3",
  
  // MacWire
  "com.softwaremill.macwire" %% "macros" % "2.3.6" % "provided" , 
  "com.softwaremill.macwire" %% "macrosakka" % "2.3.6" % "provided",
  "com.softwaremill.macwire" %% "util" % "2.3.6",
  "com.softwaremill.macwire" %% "proxy" % "2.3.6",
  
  // akka http
  "com.typesafe.akka" %% "akka-stream" % "2.6.5", // 最新は 2.6.8
  "com.typesafe.akka" %% "akka-http" % "10.1.12", // 最新は 10.2.1

)



