package com.moonlitdoor.amessage.jacoco

import com.moonlitdoor.amessage.jacoco.tasks.JacocoTestCoverageVerification
import com.moonlitdoor.amessage.jacoco.tasks.JacocoTestReport
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.testing.jacoco.plugins.JacocoPlugin

class JacocoPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    project.plugins.apply(JacocoPlugin::class.java)
    project.tasks.create(JacocoTestReport.NAME, JacocoTestReport::class.java)
    project.tasks.findByName(JacocoTestReport.NAME)?.mustRunAfter(project.childProjects.keys.map { "$it:testDebugUnitTest" })
    project.tasks.create(JacocoTestCoverageVerification.NAME, JacocoTestCoverageVerification::class.java)
    project.tasks.findByName(JacocoTestCoverageVerification.NAME)?.mustRunAfter(project.childProjects.keys.map { "$it:testDebugUnitTest" })
  }
}