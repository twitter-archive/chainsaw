import sbt._
import com.twitter.sbt._

class ChainsawProject(info: ProjectInfo) extends StandardProject(info) {
  val slf4jVersion = "1.6.1"
  val slf4jApi = "org.slf4j" % "slf4j-api" % slf4jVersion withSources
  val specs = "org.scala-tools.testing" % "specs_2.8.1" % "1.6.6" % "test"
}
