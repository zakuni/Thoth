import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "thoth"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
      "net.databinder" %% "dispatch-http" % "0.8.8",
      "nu.validator.htmlparser" % "htmlparser" % "1.4",
      "com.github.nscala-time" %% "nscala-time" % "0.2.0"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here      
    )

}
