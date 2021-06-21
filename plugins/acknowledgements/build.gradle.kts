import com.moonlitdoor.amessage.dependencies.Dependencies

repositories {
  google()
  mavenCentral()
}

buildscript {
  dependencies {
    classpath("com.moonlitdoor.amessage:dependencies")
  }
}

plugins {
  `java-gradle-plugin`
  `kotlin-dsl`
  groovy
}

group = "com.moonlitdoor.amessage"

gradlePlugin {
  plugins {
    create("acknowledgements") {
      id = "com.moonlitdoor.amessage.acknowledgements"
      implementationClass = "com.moonlitdoor.amessage.acknowledgements.AcknowledgementsPlugin"
    }
    create("acknowledgementsGrv") {
      id = "com.moonlitdoor.amessage.acknowledgements.grv"
      implementationClass = "com.moonlitdoor.amessage.acknowledgements.OssLicensesPlugin"
    }
  }
}

dependencies {
  implementation(localGroovy())
  implementation(Dependencies.AGP.gradle)
  implementation(Dependencies.Com.MoonlitDoor.AMessage.dependencies)
}
