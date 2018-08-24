name := """time-sheets"""
organization := "com.matt.groom"

version := "1.0-SNAPSHOT"

libraryDependencies += "org.reactivemongo" %% "play2-reactivemongo" % "0.11.14"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.matt.groom.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.matt.groom.binders._"
