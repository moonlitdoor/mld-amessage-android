import com.moonlitdoor.amessage.dependencies.Constants
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
      id = Constants.PLUGIN_APPLICATION
      implementationClass = "com.moonlitdoor.amessage.android.AndroidApplicationPlugin"
    }
    create("library") {
      id = Constants.PLUGIN_LIBRARY
      implementationClass = "com.moonlitdoor.amessage.android.AndroidLibraryPlugin"
    }
  }
}

dependencies {
  implementation(Dependencies.AGP.gradle)
  implementation(Dependencies.Com.MoonlitDoor.AMessage.dependencies)
}