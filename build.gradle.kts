buildscript {

  repositories {
    google()
    jcenter()
    maven(url = "https://plugins.gradle.org/m2/")
  }
  dependencies {
    classpath(D.comGithubBenManesGradleVersionsPlugin)
    classpath(D.comMoonlitdoorGitVersionGitVersion)
    classpath(D.comAndroidToolsBuildGradle)
    classpath(kotlin("gradle-plugin", version = "1.3.41"))
    classpath(D.androidxNavigationNavigationSafeArgsGradlePlugin)
//    classpath ("com.google.firebase:perf-plugin:1.2.1")
//    classpath ("com.github.triplet.gradle:play-publisher:2.2.1")
//    classpath ("io.fabric.tools:gradle:1.29.0")
  }
}

allprojects {
  apply(plugin = "com.github.ben-manes.versions")
  repositories {
    google()
    jcenter()
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
  }
}

tasks.register("clean", Delete::class) {
  delete(
    rootProject.buildDir,
    "amessage/src/main/play/en-US"
  )
}
