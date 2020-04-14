package com.moonlitdoor.jacoco.tasks

import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.Internal
import org.gradle.testing.jacoco.tasks.JacocoReport

open class JacocoTestReport : JacocoReport() {

  private val subProjects: List<String> by lazy {
    project.subprojects
      .filter { it.plugins.hasPlugin("com.moonlitdoor.jacoco") }
      .map { it.projectDir.absolutePath }
  }

  @Internal
  override fun getGroup(): String = "mld"

  @Internal
  override fun getDescription(): String = "Generate Jacoco Coverage Report"

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

  companion object {
    const val NAME: String = "jacocoTestReport"
  }

}
