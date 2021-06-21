package com.moonlitdoor.amessage.acknowledgements.tasks

import groovy.json.JsonBuilder
import groovy.json.JsonException
import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.ResolveException
import org.gradle.api.artifacts.ResolvedArtifact
import org.gradle.api.artifacts.ResolvedDependency
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.internal.component.AmbiguousVariantSelectionException
import org.gradle.internal.impldep.com.google.gson.JsonArray
import org.gradle.internal.impldep.com.google.gson.JsonObject
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

open class DependencyTask : DefaultTask() {

  @OutputDirectory
  lateinit var outputDir: File

  @OutputFile
  lateinit var outputFile: File

  /**
   * Returns a serializable snapshot of direct dependencies from all relevant
   * configurations to allow task caching. To improve performance, this does
   * not resolve transitive dependencies. Direct dependencies should require a
   * version bump to publish a new POM file with updated transitive
   * dependencies.
   */
  @Input
  fun getDirectDependencies(): List<String> {
    return collectDependenciesFromConfigurations(project.configurations, mutableSetOf(project))
  }

  @TaskAction
  fun action() {
    initOutput()
    updateDependencyArtifacts()

    if (outputFile.exists() && checkArtifactSet(outputFile)) {
      return
    }

    BufferedWriter(FileWriter(outputFile)).write(JsonBuilder(artifactInfos).toPrettyString())

  }

  private fun collectDependenciesFromConfigurations(configurationContainer: ConfigurationContainer, visitedProjects: MutableSet<Project>): List<String> {
    val directDependencies = mutableSetOf<String>()
    val libraryProjects = mutableSetOf<Project>()
    for (configuration in configurationContainer) {
      if (shouldSkipConfiguration(configuration)) {
        continue
      }
      for (dependency in configuration.allDependencies) {
        if (dependency is ProjectDependency) {
          libraryProjects.add(dependency.dependencyProject)
        } else if (dependency is ExternalModuleDependency) {
          directDependencies.add(toMavenId(dependency))
        }
      }
    }
    for (libraryProject in libraryProjects) {
      if (visitedProjects.contains(libraryProject)) {
        continue
      }
      visitedProjects.add(libraryProject)
      directDependencies.addAll(
        collectDependenciesFromConfigurations(
          libraryProject.configurations, visitedProjects
        )
      )
    }
    return directDependencies.sorted()
  }

  private fun shouldSkipConfiguration(configuration: Configuration): Boolean {
    return !configuration.isCanBeResolved || (isTest(configuration)
        || !isPackagedDependency(configuration))
  }

  private fun isTest(configuration: Configuration): Boolean {
    var isTestConfiguration = (configuration.name.startsWith(TEST_PREFIX) || configuration.name.startsWith(ANDROID_TEST_PREFIX))
    configuration.hierarchy.forEach { isTestConfiguration = isTestConfiguration || TEST_COMPILE.contains(it.name) }
    return isTestConfiguration
  }

  private fun isPackagedDependency(configuration: Configuration): Boolean {
    var isPackagedDependency = PACKAGED_DEPENDENCIES_PREFIXES.any {
      configuration.name.startsWith(it)
    }
    configuration.hierarchy.forEach {
      val configurationHierarchyName = it.name
      isPackagedDependency = isPackagedDependency || PACKAGED_DEPENDENCIES_PREFIXES.any { configuration ->
        configurationHierarchyName.startsWith(configuration)
      }
    }
    return isPackagedDependency
  }

  private fun toMavenId(dependency: Dependency): String {
    return "${dependency.group}:${dependency.name}:${dependency.version}"
  }

  private fun initOutput() {
    if (!outputDir.exists()) {
      outputDir.mkdirs()
    }
  }

  private fun updateDependencyArtifacts() {
    for (configuration in project.configurations) {
      val artifacts: Set<ResolvedArtifact> = getResolvedArtifacts(configuration) ?: continue
      artifacts.forEach {
        println("${it.moduleVersion.id.group}:${it.name}:${it.moduleVersion.id.version}")
      }
      addArtifacts(artifacts)
    }
  }

  private fun getResolvedArtifacts(configuration: Configuration): Set<ResolvedArtifact>? {

    if (shouldSkipConfiguration(configuration)) {
      return null
    }

    return try {
      getResolvedArtifactsFromResolvedDependencies(
        configuration.resolvedConfiguration
          .lenientConfiguration
          .firstLevelModuleDependencies
      )
    } catch (exception: ResolveException) {
      logger.warn("Failed to resolve OSS licenses for $configuration.name.", exception)
      null
    }
  }

  private fun addArtifacts(artifacts: Set<ResolvedArtifact>) {
    for (artifact in artifacts) {
      val group = artifact.moduleVersion.id.group
      val artifactKey = artifact.file.absolutePath

      if (artifactSet.contains(artifactKey)) {
        continue
      }

      artifactSet.add(artifactKey)
      artifactInfos.add(ArtifactInfo(group, artifact.name, artifactKey, artifact.moduleVersion.id.version))
    }
  }

  private fun getResolvedArtifactsFromResolvedDependencies(resolvedDependencies: Set<ResolvedDependency>): Set<ResolvedArtifact> {

    val resolvedArtifacts = mutableSetOf<ResolvedArtifact>()
    for (resolvedDependency in resolvedDependencies) {
      try {
        if (resolvedDependency.moduleVersion == LOCAL_LIBRARY_VERSION) {
          /**
           * Attempting to getAllModuleArtifacts on a local library project will result
           * in AmbiguousVariantSelectionException as there are not enough criteria
           * to match a specific variant of the library project. Instead we skip the
           * the library project itself and enumerate its dependencies.
           */
          resolvedArtifacts.addAll(
            getResolvedArtifactsFromResolvedDependencies(
              resolvedDependency.children
            )
          )
        } else {
          resolvedArtifacts.addAll(resolvedDependency.allModuleArtifacts)
        }
      } catch (exception: AmbiguousVariantSelectionException) {
        logger.warn("Failed to process $resolvedDependency.name", exception)
      }
    }
    return resolvedArtifacts
  }

  private fun checkArtifactSet(file: File): Boolean {
    val artifacts = artifactSet.toMutableSet()
    try {
      val previousArtifacts = JsonSlurper().parse(file) as JsonArray
      for (entry in previousArtifacts) {
        val key = (entry as JsonObject)["fileLocation"].asString
        if (artifacts.contains(key)) {
          artifacts.remove(key)
        } else {
          return false
        }
      }
      return artifacts.isEmpty()
    } catch (exception: JsonException) {
      return false
    }
  }

  companion object {
    private const val TEST_PREFIX = "test"
    private const val ANDROID_TEST_PREFIX = "androidTest"
    private const val LOCAL_LIBRARY_VERSION = "unspecified"
    private val TEST_COMPILE = setOf("testCompile", "androidTestCompile")
    private val PACKAGED_DEPENDENCIES_PREFIXES = setOf("compile", "implementation", "api")
    private val artifactSet = mutableSetOf<String>()
    private val artifactInfos = mutableSetOf<ArtifactInfo>()
  }

}
