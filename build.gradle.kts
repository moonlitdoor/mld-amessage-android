buildscript {

  val kotlin_version by extra("1.3.72")
  repositories {
    google()
    jcenter()
    maven(url = "https://plugins.gradle.org/m2/")
  }
  dependencies {
    classpath(D.Androidx.Navigation.navigationSafeArgsGradlePlugin)
    classpath(D.Com.Android.Tools.Build.gradle)
    classpath(D.Com.Github.BenManes.gradleVersionsPlugin)
    classpath(D.Com.Google.Firebase.firebaseAppdistributionGradle)
    classpath(D.Com.Github.Triplet.Gradle.playPublisher)
    classpath(D.Com.Moonlitdoor.GitVersion.gitVersion)
    classpath(D.Org.Jetbrains.Kotlin.kotlinGradlePlugin)
    classpath(D.Com.Google.Firebase.firebaseCrashlyticsGradle)
    classpath(D.Com.Google.Firebase.perfPlugin)
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
  }
}

allprojects {
  apply(plugin = "com.github.ben-manes.versions")
  repositories {
    google()
    jcenter()
    maven(url = "https://jitpack.io")
    maven(url = "https://ci.android.com/builds/submitted/6043188/androidx_snapshot/latest/repository/")
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

plugins {
  id("com.moonlitdoor.jacoco")
}

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
