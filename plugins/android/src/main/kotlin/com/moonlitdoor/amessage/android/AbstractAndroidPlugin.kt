package com.moonlitdoor.amessage.android

import com.android.build.gradle.BaseExtension
import com.moonlitdoor.amessage.dependencies.Constants
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class AbstractAndroidPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    project.plugins.apply("kotlin-android")
    val android = project.extensions.getByName("android")
    if (android is BaseExtension) {
      android.apply {
        compileSdkVersion(28)

        defaultConfig {
          minSdkVersion(26)
          targetSdkVersion(28)
          testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
          testInstrumentationRunnerArguments.putAll(mapOf("clearPackageData" to "true"))
        }

        buildTypes {
          getByName(Constants.RELEASE) {
            isMinifyEnabled = false
//            isShrinkResources = SHRINK
            proguardFiles(Constants.PROGUARD_FILE)
          }
        }

        lintOptions {
          disable("RtlEnabled")
          isWarningsAsErrors = true
          isAbortOnError = true
          xmlReport = false
        }

        testOptions {
          unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
          }
          execution = "ANDROIDX_TEST_ORCHESTRATOR"
          animationsDisabled = true
        }

        compileOptions {
          sourceCompatibility = JavaVersion.VERSION_1_8
          targetCompatibility = JavaVersion.VERSION_1_8
        }

        sourceSets {
          getByName("test") {
            java.srcDir("src/sharedTest/java")
          }
          getByName("androidTest") {
            java.srcDir("src/sharedTest/java")
          }
        }
      }
    }

//    project.applyTo<org.gradle.testing.jacoco.plugins.JacocoPlugin>()
//    (project.extensions["jacoco"] as JacocoPluginExtension).apply {
//      toolVersion = "0.8.5"
//      reportsDir = project.file("${project.buildDir}/reports")
//    }
//    project.tasks.withType(Test::class.java) {
//      (extensions["jacoco"] as JacocoTaskExtension).apply {
//        excludes = listOf("jdk.internal.*")
//        isIncludeNoLocationClasses = true
//      }
//    }

  }
}
