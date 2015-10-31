import Dependencies._

lazy val root = (project in file(".")).
		settings(
			name := "e-commerce-rest-api-scala",
			version := "1.0.0",
			organization := "org.juanitodread",
			scalaVersion := "2.11.6",
			scalaHome := Some(file("/opt/scala-sdk/scala-2.11.6/")),
			routesGenerator := InjectedRoutesGenerator
		).settings(
		    libraryDependencies ++= dependencies
		).enablePlugins(PlayScala)
