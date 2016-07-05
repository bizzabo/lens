import cbt._
import ai.x.build.{XdotaiFreeSoftwareBuild,team}
// cbt:https://github.com/cvogt/cbt.git#bc2231720d3620b5e0459fa12c467bf675fcfdf5
class Build(val context: cbt.Context) extends XdotaiFreeSoftwareBuild{
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
