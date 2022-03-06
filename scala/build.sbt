val scala3Version = "3.1.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "knapsack",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % Test
  )
