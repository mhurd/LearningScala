name := "LearningScala"

organization := "com.mhurd"

version := "1.0"

scalaVersion := "2.10.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.8" % "test" cross CrossVersion.full

libraryDependencies += "org.scala-lang" % "scala-swing" % "2.10.0"

libraryDependencies += "joda-time" % "joda-time" % "2.1"

libraryDependencies += "org.joda" % "joda-convert" % "1.3"

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))
