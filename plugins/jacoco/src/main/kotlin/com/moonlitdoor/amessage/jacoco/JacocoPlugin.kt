package com.moonlitdoor.amessage.jacoco

import com.android.build.gradle.internal.crash.afterEvaluate
import com.moonlitdoor.amessage.jacoco.tasks.JacocoTestCoverageVerification
import com.moonlitdoor.amessage.jacoco.tasks.JacocoTestReport
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.get
import org.gradle.testing.jacoco.plugins.JacocoPlugin
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension

class JacocoPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    project.plugins.apply(JacocoPlugin::class.java)
    project.tasks.create(JacocoTestReport.NAME, JacocoTestReport::class.java)
    project.tasks.findByName(JacocoTestReport.NAME)?.mustRunAfter(project.childProjects.keys.map { "$it:testDebugUnitTest" })
    project.tasks.create(JacocoTestCoverageVerification.NAME, JacocoTestCoverageVerification::class.java)
    project.tasks.findByName(JacocoTestCoverageVerification.NAME)?.mustRunAfter(project.childProjects.keys.map { "$it:testDebugUnitTest" })
//    project.applyTo<org.gradle.testing.jacoco.plugins.JacocoPlugin>()
    (project.extensions["jacoco"] as JacocoPluginExtension).apply {
      toolVersion = "0.8.6"
      reportsDirectory.set(project.file("${project.buildDir}/reports"))
    }
    project.tasks.withType(Test::class.java) {
      (extensions["jacoco"] as JacocoTaskExtension).apply {
        isEnabled = true
        excludes = listOf("jdk.internal.*")
        isIncludeNoLocationClasses = true
      }
    }

    afterEvaluate {
      project.tasks.findByName("check")?.finalizedBy(project.tasks.findByName(JacocoTestReport.NAME))
      project.tasks.findByName(JacocoTestReport.NAME)?.dependsOn(project.tasks.findByName("check"))
      project.tasks.findByName(JacocoTestCoverageVerification.NAME)?.dependsOn(project.tasks.findByName("check"))
    }

  }
}