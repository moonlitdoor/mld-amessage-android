repositories {
  mavenCentral()
}

plugins {
  `java-library`
  `kotlin-dsl`
}

group = "com.moonlitdoor.amessage"

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}
