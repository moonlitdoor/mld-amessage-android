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

  implementation("com.android.tools.build:gradle:4.2.0-alpha08")
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.0")
}