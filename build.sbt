name := "application-creator"

version := "0.1"

scalaVersion := "2.13.5"
val springBootVersion       = "2.2.8.RELEASE"
val springBootConfigVersion = "2.2.7.RELEASE"
val springBootNetflixClient = "2.2.7.RELEASE"
libraryDependencies ++= Seq(
  "org.springframework.boot"     % "spring-boot-starter-parent"   % springBootVersion,
  "org.springframework.boot"     % "spring-boot-starter-web"      % springBootVersion,
  "org.springframework.boot"     % "spring-boot-starter-actuator" % springBootVersion,
  "com.fasterxml.jackson.module" % "jackson-module-scala"         % "2.0.2",
  "io.vavr"                      % "vavr"                         % "0.10.0",
  "org.slf4j"                    % "slf4j-api"                    % "1.7.5",
  "org.slf4j"                    % "slf4j-simple"                 % "1.7.5",
  "org.projectlombok"            % "lombok"                       % "1.16.16"
)
