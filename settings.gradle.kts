plugins {
  id("com.gradle.enterprise") version "3.1"
}

gradleEnterprise {
  buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
    publishAlways()
  }
}

include(":about")
include(":amessage")
include(":analytics")
include(":bindings")
include(":components")
include(":connect")
include(":connection")
include(":conversation")
include(":constants")
include(":database")
include(":domain")
include(":encryption")
include(":experiments")
include(":experiments_ui")
include(":extensions")
include(":feedback")
include(":handle")
include(":help")
include(":ids")
include(":network")
include(":resources")
include(":settings")
include(":windows")
