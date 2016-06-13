import cbt._
import ai.x.build.{XdotaiFreeSoftwareBuild,team}
import scala.collection.immutable.Seq
// cbt:https://github.com/cvogt/cbt.git#25e4d66e6abe5ef285849e710851ef84dc3ac700
class Build(context: cbt.Context) extends cbt.PublishBuild(context) with XdotaiFreeSoftwareBuild{
  def name = "lens"
  def defaultVersion = "1.0.0"
  def description = "Convenient ad-hoc lens Syntax on top of Monocle"

  def inceptionYear = 2016
  def developers = Seq( team.cvogt )

  object monocle{
    val version = "1.2.0"
    val org = "com.github.julien-truffaut"
    val core = org %% "monocle-core" % version
    val generic = org %% "monocle-generic" % version
    val macro_ = org %% "monocle-macro" % version
    val all = Seq(core, generic, macro_)
  }

  override def dependencies = super.dependencies ++ Resolver( mavenCentral ).bind( monocle.all: _* )
}
