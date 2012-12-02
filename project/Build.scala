import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "thoth"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
      "net.databinder" %% "dispatch-http" % "0.8.8",
      "nu.validator.htmlparser" % "htmlparser" % "1.4",
      "org.scalaj" %% "scalaj-time" % "0.6"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here      
    )

}
