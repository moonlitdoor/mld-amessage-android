includeBuild("plugins/dependencies")
includeBuild("plugins/android")
includeBuild("plugins/jacoco")
includeBuild("plugins/acknowledgements")

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

include(":about")
include(":amessage")
include(":analytics")
include(":components")
include(":connect")
include(":connection")
include(":connections")
include(":conversation")
include(":conversations")
include(":constants")
include(":database")
include(":domain")
include(":dto")
include(":encryption")
include(":experiments")
include(":experiments:ui")
include(":extensions")
include(":faq")
include(":feedback")
include(":handle")
include(":help")
include(":init")
include(":network")
include(":news")
include(":more")
include(":push")
include(":resources")
include(":root")
include(":routes")
include(":settings")
include(":theme")
include(":windows")
