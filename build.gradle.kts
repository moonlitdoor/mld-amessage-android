buildscript {

  repositories {
    google()
    mavenCentral()
    maven(url = "https://plugins.gradle.org/m2/")

  }
  dependencies {
    classpath("com.moonlitdoor.amessage:dependencies")
//    classpath("com.android.tools.build:gradle:7.0.0-alpha09")
    classpath("com.moonlitdoor.amessage:android")
//    classpath("com.moonlitdoor.amessage:jacoco")
    classpath("com.google.dagger:hilt-android-gradle-plugin:2.33-beta")
    classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.3")
    classpath("com.github.ben-manes:gradle-versions-plugin:0.36.0")
    classpath("com.google.firebase:firebase-appdistribution-gradle:2.0.1")
    classpath("com.github.triplet.gradle:play-publisher:3.3.0")
    classpath("com.moonlitdoor.git-version:git-version:0.1.1")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
    classpath("com.google.firebase:firebase-crashlytics-gradle:2.5.0")
    classpath("com.google.firebase:perf-plugin:1.3.4")
  }
}

allprojects {
  apply(plugin = "com.github.ben-manes.versions")
  repositories {
    google()
    jcenter()
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven(url = "https://ci.android.com/builds/submitted/6043188/androidx_snapshot/latest/repository/")
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
    }
  }

  tasks.withType<Test> {
    testLogging.events("failed", "passed", "skipped")
//    reports.html.isEnabled = false
  }
}

//plugins {
//  id("com.moonlitdoor.amessage.jacoco")
//}

gradle.projectsEvaluated {
  tasks.withType(JavaCompile::class.java) {
    options.compilerArgs + "-Xmaxerrs" + "500"
  }
}


gradle.taskGraph.whenReady {
  val lintTaskRegex = """^((?!\:amessage:).)*lint$""".toRegex()
  allTasks.forEach { task -> task.enabled = !lintTaskRegex.matches(task.path) }
}

tasks.register("testReport", TestReport::class) {
  reportOn(allprojects.map { it.tasks.withType(Test::class) })
  destinationDir = file("$buildDir/reports/allTests")
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
