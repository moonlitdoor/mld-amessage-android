package com.moonlitdoor.jacoco

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.applyTo
import org.gradle.kotlin.dsl.get
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension

class JacocoPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    project.applyTo<org.gradle.testing.jacoco.plugins.JacocoPlugin>()
    (project.extensions["jacoco"] as JacocoPluginExtension).apply {
      toolVersion = "0.8.5"
      reportsDir = project.file("${project.buildDir}/reports")
    }
    project.tasks.withType(Test::class.java) {
      (extensions["jacoco"] as JacocoTaskExtension).isIncludeNoLocationClasses = true
    }
  }
}