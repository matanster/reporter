name := "reporter"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "com.typesafe.play" %% "play-slick" % "0.8.1",
  "mysql" % "mysql-connector-java" % "latest.release"
)     

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies += "com.adrianhurt" %% "play-bootstrap3" % "0.4-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

