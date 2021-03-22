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
    create("application") {
      id = "com.moonlitdoor.amessage.android.application"
      implementationClass = "com.moonlitdoor.amessage.android.AndroidApplicationPlugin"
    }
    create("library") {
      id = "com.moonlitdoor.amessage.android.library"
      implementationClass = "com.moonlitdoor.amessage.android.AndroidLibraryPlugin"
    }
  }
}

dependencies {
  implementation(Dependencies.AGP.gradle)
  implementation(Dependencies.Com.MoonlitDoor.AMessage.dependencies)
}