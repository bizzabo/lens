import cbt._
import java.net._
import java.io.File
import scala.collection.immutable.Seq

class Build(context: cbt.Context) extends cbt.DefaultBuild(context) with mixins.Test{
  override def scalaVersion = "2.11.7"

  override def scalacOptions = super.scalacOptions ++ Seq( "-language:higherKinds" )
}
