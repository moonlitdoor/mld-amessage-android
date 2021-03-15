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
}

group = "com.moonlitdoor.amessage"

gradlePlugin {
  plugins {
    create("jacoco") {
      id = "com.moonlitdoor.amessage.jacoco"
      implementationClass = "com.moonlitdoor.amessage.jacoco.JacocoPlugin"
    }
  }
}

dependencies {
  implementation(Dependencies.AGP.gradle)
}