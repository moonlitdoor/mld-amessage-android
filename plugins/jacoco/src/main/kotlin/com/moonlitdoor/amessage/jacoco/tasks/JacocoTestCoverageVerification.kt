package com.moonlitdoor.amessage.jacoco.tasks

import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.Internal
import org.gradle.internal.jacoco.rules.JacocoViolationRulesContainerImpl
import org.gradle.internal.reflect.DirectInstantiator
import org.gradle.testing.jacoco.tasks.JacocoCoverageVerification
import org.gradle.testing.jacoco.tasks.rules.JacocoViolationRulesContainer

open class JacocoTestCoverageVerification : JacocoCoverageVerification() {

  private val minimumCoverage = 0.10

  private val subProjects: List<String> by lazy {
    project.subprojects
      .filter { it.plugins.hasPlugin("com.moonlitdoor.android") }
      .map { it.projectDir.absolutePath }
  }

  @Internal
  override fun getGroup(): String = "mld"

  @Internal
  override fun getDescription(): String = "Verify Coverage Minimum Requirement"

  override fun getAllClassDirs(): FileCollection = super.getAllClassDirs() + project.files(
    subProjects
      .map { "$it/build/tmp/kotlin-classes/debug" }
      .filter { project.file(it).exists() }
  )

  override fun getAllSourceDirs(): FileCollection = super.getAllSourceDirs() + project.files(
    subProjects
      .map { "$it/src/main/java" }
      .filter { project.file(it).exists() },
    subProjects
      .map { "$it/src/main/kotlin" }
      .filter { project.file(it).exists() }
  )

  override fun getExecutionData(): ConfigurableFileCollection = project.files(
    subProjects
      .map { "$it/build/jacoco/testDebugUnitTest.exec" }
      .filter { project.file(it).exists() }
  )

  override fun getViolationRules(): JacocoViolationRulesContainer = JacocoViolationRulesContainerImpl(DirectInstantiator.INSTANCE).apply {
    rule {
      limit {
        minimum = minimumCoverage.toBigDecimal()
      }
    }
  }

  companion object {
    const val NAME: String = "jacocoTestCoverageVerification"
  }

}
