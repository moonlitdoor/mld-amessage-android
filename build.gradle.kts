buildscript {

  repositories {
    google()
    jcenter()
    maven(url = "https://plugins.gradle.org/m2/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
  }
  dependencies {
    classpath(D.Androidx.Navigation.navigationSafeArgsGradlePlugin)
    classpath(T.Com.Android.Tools.Build.gradle)
    classpath(T.Com.Github.BenManes.gradleVersionsPlugin)
    classpath(D.Com.Google.Firebase.firebaseAppdistributionGradle)
    classpath(T.Com.Github.Triplet.Gradle.playPublisher)
    classpath(T.Com.Moonlitdoor.GitVersion.gitVersion)
    classpath(D.Org.Jetbrains.Kotlin.kotlinGradlePlugin)
    classpath(T.Com.Google.Firebase.firebaseCrashlyticsGradle)
    classpath(T.Com.Google.Firebase.perfPlugin)
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

gradle.projectsEvaluated {
  tasks.withType(JavaCompile::class.java) {
    options.compilerArgs + "-Xmaxerrs" + "500"
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
