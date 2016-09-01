name := """wrl"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  "org.springframework" % "spring-jdbc" % "4.2.5.RELEASE",
  "org.springframework" % "spring-orm" % "4.2.5.RELEASE",
  "org.springframework" % "spring-tx" % "4.2.5.RELEASE",
  "org.springframework" % "spring-expression" % "4.2.5.RELEASE",
  "org.springframework" % "spring-context" % "4.2.5.RELEASE",
  "org.springframework" % "spring-test" % "4.2.5.RELEASE",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.6.Final",
  "mysql" % "mysql-connector-java" % "5.1.35",
  "javax.inject" % "javax.inject" % "1",
  "org.slf4j" % "slf4j-api" % "1.7.21",
  "org.ocpsoft.prettytime" % "prettytime" % "3.2.7.Final"
)
