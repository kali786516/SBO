name := "SBO"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)     

play.Project.playScalaSettings

resolvers += "Maven Central Server" at "https://repo1.maven.org/maven2"