name := "scaldi-play"
organization := "org.scaldi"
version := "0.5.16-SNAPSHOT"

description := "Scaldi-Play - Scaldi integration for Play framework"
homepage := Some(url("http://scaldi.org"))
licenses := Seq("Apache License, ASL Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

scalaVersion := "2.11.7"
scalacOptions ++= Seq("-deprecation", "-feature")
javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")
testOptions in Test += Tests.Argument("-oDF")

val playVersion = "2.6.0"

val guiceVersion = "4.1.0"


libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % playVersion % "provided",
  "org.scaldi" %% "scaldi-jsr330" % "0.5.8",
  "com.google.inject" % "guice" % guiceVersion,
  "com.google.inject.extensions" % "guice-assistedinject" % guiceVersion,
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "com.typesafe.play" %% "play-test" % playVersion % "test",
  "com.typesafe.play" %% "play-slick" % "3.0.0" % "test",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.0" % "test",
  "com.h2database" % "h2" % "1.4.187" % "test",
  "com.typesafe.play" %% "play-cache" % playVersion % "test" // cache plugin add extra bindings which have some specialties and will be tested automatically
)

git.remoteRepo := "git@github.com:scaldi/scaldi-play.git"
resolvers ++= Seq(
  "Typesafe Releases Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases")

// Site and docs

site.settings
site.includeScaladoc()
ghpages.settings

// Publishing

publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := (_ => false)
publishTo := Some(
  if (version.value.trim.endsWith("SNAPSHOT"))
    "snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  else
    "releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")

// nice prompt!

shellPrompt in ThisBuild := { state =>
  scala.Console.GREEN + Project.extract(state).currentRef.project + "> " + scala.Console.RESET
}

// Additional meta-info

startYear := Some(2011)
organizationHomepage := Some(url("https://github.com/scaldi"))
scmInfo := Some(ScmInfo(
  browseUrl = url("https://github.com/scaldi/scaldi-play"),
  connection = "scm:git:git@github.com:scaldi/scaldi-play.git"
))
pomExtra := <xml:group>
  <developers>
    <developer>
      <id>OlegIlyenko</id>
      <name>Oleg Ilyenko</name>
    </developer>
  </developers>
</xml:group>