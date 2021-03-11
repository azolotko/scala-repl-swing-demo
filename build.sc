import mill._, scalalib._

object demo extends ScalaModule {
  override def scalaVersion = "2.13.5"

  override def ivyDeps =
    Agg(
      ivy"org.scala-lang:scala-compiler:$scalaVersion"
    )
}
