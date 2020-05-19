plugins {
  `java-gradle-plugin`
  `kotlin-dsl`
}

repositories {
  jcenter()
  google()
}

dependencies {
  compileOnly(gradleApi())

  implementation("com.android.tools.build:gradle:4.1.0-alpha09")
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
}