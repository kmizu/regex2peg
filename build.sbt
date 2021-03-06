organization := "com.github.kmizu"

name := "regex2peg"

version := "0.0.1"

scalaVersion := "2.11.12"

assemblyJarName in assembly := "regex2peg.jar"

mainClass in assembly := Some("com.github.kmizu.regex2peg.Main")

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.3",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
)

pomExtra := (
  <url>https://github.com/kmizu/regex2peg</url>
  <licenses>
    <license>
    <name>Apache</name>
    <url>http://www.opensource.org/licenses/Apache-2.0</url>
    <distribution>repo</distribution>
  </license>
  </licenses>
  <scm>
    <url>git@github.com:kmizu/regex2peg.git</url>
    <connection>scm:git:git@github.com:kmizu/regex2peg.git</connection>
  </scm>
  <developers>
    <developer>
      <id>kmizu</id>
      <name>Kota Mizushima</name>
      <url>https://github.com/kmizu</url>
    </developer>
  </developers>
)

publishTo := {
  val v = version.value
  val nexus = "https://oss.sonatype.org/"
  if (v.endsWith("-SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

credentials ++= {
  val sonatype = ("Sonatype Nexus Repository Manager", "oss.sonatype.org")
  def loadMavenCredentials(file: java.io.File) : Seq[Credentials] = {
    xml.XML.loadFile(file) \ "servers" \ "server" map (s => {
        val host = (s \ "id").text
        val realm = if (host == sonatype._2) sonatype._1 else "Unknown"
        Credentials(realm, host, (s \ "username").text, (s \ "password").text)
     })
  }
  val ivyCredentials   = Path.userHome / ".ivy2" / ".credentials"
  val mavenCredentials = Path.userHome / ".m2"   / "settings.xml"
  (ivyCredentials.asFile, mavenCredentials.asFile) match {
    case (ivy, _) if ivy.canRead => Credentials(ivy) :: Nil
    case (_, mvn) if mvn.canRead => loadMavenCredentials(mvn)
    case _ => Nil
  }
}

publishMavenStyle := true

scalacOptions ++= Seq(
  "-deprecation","-unchecked",
   "-encoding", "UTF-8"
)

initialCommands in console += {
  Iterator().map("import "+).mkString("\n")
}
