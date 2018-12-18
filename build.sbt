import sbt._
import Keys._
import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform.{ScalariformKeys, autoImport}
import xerial.sbt.Sonatype._

val projectName = "lens"
version := "2.0.1"
name := projectName
scalaVersion := "2.12.8"
crossScalaVersions := Seq("2.11.11", scalaVersion.value)
description := "Convenient ad-hoc lens Syntax on top of Monocle"

useGpg := true

credentials += Credentials(Path.userHome / ".sbt" / "sonatype_credential")

val monocleVersion = "1.5.0"
libraryDependencies ++=   Seq(
  "com.github.julien-truffaut" %% "monocle-core" % monocleVersion,
  "com.github.julien-truffaut" %% "monocle-generic" % monocleVersion,
  "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion,
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)
resolvers ++= Seq(Resolver.sonatypeRepo("releases"),Resolver.sonatypeRepo("snapshots"))

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oSD")
organizationName := "x.ai"
organization := "ai.x"
scalacOptions in (Compile, doc) ++= Seq(
  "-doc-footer", projectName+" is developed by Miguel Iglesias.",
  "-implicits",
  "-groups"
)

scalariformPreferences := scalariformPreferences.value
      .setPreference(AlignParameters, true)
      .setPreference(AlignArguments, true)
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(MultilineScaladocCommentsStartOnFirstLine, true)
      .setPreference(SpaceInsideParentheses, true)
      .setPreference(SpacesWithinPatternBinders, true)
      .setPreference(SpacesAroundMultiImports, true)
      .setPreference(DanglingCloseParenthesis, Preserve)
      .setPreference(DoubleIndentConstructorArguments, true)
publishTo := sonatypePublishTo.value
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }
licenses += ("Apache 2.0", url("http://github.com/xdotai/"+projectName+"/blob/master/LICENSE.txt"))
homepage := Some(url("http://github.com/xdotai/"+projectName))
startYear := Some(2016)
scmInfo := Some(
  ScmInfo(
    url(s"https://github.com/xdotai/$projectName"),
    s"scm:git@github.com:xdotai/$projectName.git"
  )
)
developers := List(
  Developer(id="caente", name="Miguel Iglesias", email="miguel.iglesias@human.x.ai", url=url("https://github.com/caente"))
)
