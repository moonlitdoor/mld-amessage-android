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
    create("android") {
      id = "com.moonlitdoor.amessage.android"
      implementationClass = "com.moonlitdoor.amessage.android.AndroidPlugin"
    }
  }
}

dependencies {
  implementation(Dependencies.AGP.gradle)
}