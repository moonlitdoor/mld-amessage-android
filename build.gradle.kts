import org.jlleitschuh.gradle.ktlint.KtlintExtension

buildscript {

  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
  dependencies {
    classpath("com.moonlitdoor.amessage:dependencies")
    classpath("com.moonlitdoor.amessage:android")
    classpath("com.moonlitdoor.amessage:jacoco")
    classpath("com.moonlitdoor.amessage:acknowledgements")
    classpath("com.google.dagger:hilt-android-gradle-plugin:2.38")
    classpath("com.github.ben-manes:gradle-versions-plugin:0.39.0")
    classpath("com.google.firebase:firebase-appdistribution-gradle:2.1.3")
    classpath("com.github.triplet.gradle:play-publisher:3.5.0")
    classpath("com.moonlitdoor.git-version:git-version:0.1.1")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
    classpath("com.google.firebase:firebase-crashlytics-gradle:2.7.1")
    classpath("com.google.firebase:perf-plugin:1.4.0")
    classpath("org.jlleitschuh.gradle:ktlint-gradle:10.1.0")
  }
}

allprojects {
  apply(plugin = "com.github.ben-manes.versions")
  apply(plugin = "org.jlleitschuh.gradle.ktlint")
  repositories {
    google()
    mavenCentral()
  }
//  ext {
//    set("smokeTests", if (project.hasProperty("smokeTests")) project.property("smokeTests") else false)
//    set("largeTests", if (project.hasProperty("largeTests")) project.property("largeTests") else false)
//    set("mediumTests", if (project.hasProperty("mediumTests")) project.property("mediumTests") else false)
//    set("smallTests", if (project.hasProperty("smallTests")) project.property("smallTests") else false)
//    set("flakyTests", if (project.hasProperty("flakyTests")) project.property("flakyTests") else false)
//  }
  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
      jvmTarget = "1.8"
      freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
    }
  }

  tasks.withType<Test> {
    testLogging.events("failed", "passed", "skipped")
//    reports.html.isEnabled = false
  }
}

plugins {
  id("com.moonlitdoor.amessage.jacoco")
}

apply(plugin = "org.jlleitschuh.gradle.ktlint")

gradle.taskGraph.whenReady {
  val lintTaskRegex = """^((?!\:amessage:).)*lint$""".toRegex()
  allTasks.forEach { task -> task.enabled = !lintTaskRegex.matches(task.path) }
}

tasks.register("testReport", TestReport::class) {
  reportOn(allprojects.map { it.tasks.withType(Test::class) })
  destinationDir = file("$buildDir/reports/allTests")
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir, "amessage/src/main/play/en-US")
}

tasks.register("cleanGradleBuildCache", Delete::class) {
  delete("${System.getProperty("user.home")}/.gradle/cache")
}

configure<KtlintExtension> {
  android.set(true)
  enableExperimentalRules.set(true)
}
