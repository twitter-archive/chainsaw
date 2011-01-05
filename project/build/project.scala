import sbt._
import com.twitter.sbt._

class ChainsawProject(info: ProjectInfo) extends StandardProject(info) with SubversionPublisher {
  override def subversionRepository = Some("http://svn.local.twitter.com/maven/")

  val slf4jVersion = "1.5.8"
  val slf4jApi = "org.slf4j" % "slf4j-api" % slf4jVersion withSources

  val slf4j_simple = "org.slf4j" % "slf4j-simple" % slf4jVersion % "provided"

  val specs = "org.scala-tools.testing" % "specs_2.8.1" % "1.6.6" % "test"
  val mockito = "org.mockito" % "mockito-all" % "1.8.5" % "test"
}
