name := """Diplomski"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies ++= Seq(
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "org.reactivemongo" %% "play2-reactivemongo" % "0.12.7-play26"
)
