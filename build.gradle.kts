plugins {
  id("com.gradle.build-scan") version "3.0"
}

buildScan {
  termsOfServiceUrl = "https://gradle.com/terms-of-service"
  termsOfServiceAgree = "yes"
  publishAlways()
}

buildscript {

  repositories {
    google()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
    maven(url = "https://plugins.gradle.org/m2/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
  }
  dependencies {
    classpath(D.Androidx.Navigation.navigationSafeArgsGradlePlugin)
    classpath(D.Com.Android.Tools.Build.gradle)
    classpath(D.Com.Github.BenManes.gradleVersionsPlugin)
    classpath(D.Com.Google.Firebase.firebaseAppdistributionGradle)
//    classpath(D.Com.Github.Triplet.Gradle.playPublisher)
    classpath(D.Com.Moonlitdoor.GitVersion.gitVersion)
    classpath(kotlin("gradle-plugin", version = "1.3.60"))
//    classpath ("com.google.firebase:perf-plugin:1.2.1")
//    classpath ("io.fabric.tools:gradle:1.29.0")
  }
}

allprojects {
  apply(plugin = "com.github.ben-manes.versions")
  repositories {
    google()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
//    maven(url = "https://dl.bintray.com/kotlin/kotlin-dev/")
    maven(url = "https://jitpack.io")
  }
  ext {
    set("smokeTests", if (project.hasProperty("smokeTests")) project.property("smokeTests") else false)
    set("largeTests", if (project.hasProperty("largeTests")) project.property("largeTests") else false)
    set("mediumTests", if (project.hasProperty("mediumTests")) project.property("mediumTests") else false)
    set("smallTests", if (project.hasProperty("smallTests")) project.property("smallTests") else false)
    set("flakyTests", if (project.hasProperty("flakyTests")) project.property("flakyTests") else false)
  }
  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
      jvmTarget = "1.8"
    }
  }

  tasks.withType<Test> {
    testLogging.events("failed", "passed", "skipped")
//    reports.html.isEnabled = false
  }
}

tasks.register("testReport", TestReport::class) {
  destinationDir = file("$buildDir/reports/allTests")
  reportOn(allprojects.map { it.tasks.withType(Test::class) })
}

tasks.register("clean", Delete::class) {
  delete(
    rootProject.buildDir,
    "amessage/src/main/play/en-US"
  )
}

tasks.register("cleanGradleBuildCache", Delete::class) {
  delete("${System.getProperty("user.home")}/.gradle/cache")
}
