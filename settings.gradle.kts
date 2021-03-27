includeBuild("plugins/dependencies")
includeBuild("plugins/android")
includeBuild("plugins/jacoco")

plugins {
  id("com.gradle.enterprise").version("3.6")
}

gradleEnterprise {
  buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
//    publishAlways()
  }
}

//include(":about")
include(":amessage")
include(":analytics")
//include(":bindings")
//include(":components")
include(":connect")
include(":connections")
include(":conversations")
//include(":constants")
include(":database")
include(":domain")
//include(":encryption")
include(":experiments")
//include(":experiments_ui")
include(":extensions")
//include(":feedback")
include(":handle")
//include(":help")
include(":init")
//include(":ids")
//include(":network")
//include(":push")
//include(":resources")
include(":root")
include(":routes")
//include(":settings")
include(":theme")
//include(":windows")
