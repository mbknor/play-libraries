import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

  val appName = "api-s3"
  val appVersion = "1.5.0-with-headers_3"

  val appDependencies = Seq(
    "nl.rhinofly" %% "api-aws-utils" % "1.3")

  def rhinoflyRepo(version: String) = {
    val repo = if (version endsWith "SNAPSHOT") "snapshot" else "release"
    Some("Rhinofly Internal " + repo.capitalize + " Repository" at "http://maven-repository.rhinofly.net:8081/artifactory/libs-" + repo + "-local")
  }

  val mbknorRepository = Resolver.ssh("my local mbknor repo", "localhost", "~/projects/mbknor/mbknor.github.com/m2repo/releases/")(Resolver.mavenStylePatterns)

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
    organization := "nl.rhinofly",
    resolvers += rhinoflyRepo("RELEASE").get,
    publishTo := Some(mbknorRepository),
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"))

}
