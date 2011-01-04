import sbt._
import com.twitter.sbt._

class ChainsawProject(info: ProjectInfo) extends StandardProject(info) with SubversionPublisher {
  override def subversionRepository = Some("http://svn.local.twitter.com/maven/")

  val slf4jVersion = "1.6.1"
  val slf4jApi = "org.slf4j" % "slf4j-api" % slf4jVersion withSources
  val specs = "org.scala-tools.testing" % "specs_2.8.1" % "1.6.6" % "test"
}
