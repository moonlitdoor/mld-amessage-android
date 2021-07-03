package com.moonlitdoor.amessage.acknowledgements.tasks

import groovy.json.JsonException
import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.result.ResolvedArtifactResult
import org.gradle.api.internal.artifacts.DefaultModuleIdentifier
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.internal.component.external.model.DefaultModuleComponentIdentifier
import org.gradle.internal.impldep.com.google.gson.JsonArray
import org.gradle.internal.impldep.com.google.gson.JsonObject
import org.gradle.maven.MavenModule
import org.gradle.maven.MavenPomArtifact
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

open class AcknowledgementsTask : DefaultTask() {

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
    return collectDependenciesFromConfigurations(project.configurations, mutableSetOf(project)).map { toMavenId(it) }
  }

  @TaskAction
  fun action() {
    initOutput()

    val componentIds = collectDependenciesFromConfigurations(project.configurations, mutableSetOf(project)).map {
      DefaultModuleComponentIdentifier(DefaultModuleIdentifier.newId(it.group, it.name), it.version!!)
    }
    val artifactInfos = project.dependencies.createArtifactResolutionQuery()
      .forComponents(componentIds)
      .withArtifacts(MavenModule::class.java, MavenPomArtifact::class.java)
      .execute().resolvedComponents
      .flatMap { result ->
        result.getArtifacts(MavenPomArtifact::class.java)
          .filterIsInstance<ResolvedArtifactResult>()
          .map { it.file.absolutePath }
      }.sorted().mapNotNull {
        readPom(it)
      }

    writeAcknowledgementsStringArray(artifactInfos)
  }

  private fun readPom(filename: String): ArtifactInfo? {
    val document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filename).also { it.normalize() }
    val name = document.getElementsByTagName("name").asSequence().firstOrNull()?.childNodes?.asSequence()?.firstOrNull()?.textContent
    val description = document.getElementsByTagName("description").asSequence().firstOrNull()?.childNodes?.asSequence()?.firstOrNull()?.textContent
    val group = document.getElementsByTagName("groupId").asSequence().firstOrNull { it.parentNode.nodeName == "project" }?.childNodes?.asSequence()?.firstOrNull()?.textContent ?: return null
    val artifact = document.getElementsByTagName("artifactId").asSequence().firstOrNull { it.parentNode.nodeName == "project" }?.childNodes?.asSequence()?.firstOrNull()?.textContent ?: return null
    val version = document.getElementsByTagName("version").asSequence().firstOrNull { it.parentNode.nodeName == "project" }?.childNodes?.asSequence()?.firstOrNull()?.textContent ?: return null
    val licenses = document.getElementsByTagName("licenses").asSequence().firstOrNull() ?: return null
    if (licenses.nodeType == Node.ELEMENT_NODE) {
      val license = (licenses as Element).getElementsByTagName("license").asSequence().firstOrNull()
      if (license?.nodeType == Node.ELEMENT_NODE) {
        val licenseName = (license as Element).getElementsByTagName("name").asSequence().firstOrNull()?.childNodes?.asSequence()?.firstOrNull()?.textContent ?: return null
        val licenseUrl = (license).getElementsByTagName("url").asSequence().firstOrNull()?.childNodes?.asSequence()?.firstOrNull()?.textContent ?: return null
        return ArtifactInfo(
          name = name ?: "$group:$artifact",
          description = description,
          group = group,
          artifact = artifact,
          version = version,
          license = licenseName,
          url = licenseUrl
        )
      }
    }
    return null
  }

  private fun writeAcknowledgementsStringArray(artifactInfos: List<ArtifactInfo>) {

    val document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument()
    val rootElement = document.createElement("resources")
    val stringArray = document.createElement("string-array").also {
      it.setAttribute("name", "acknowledgements_array")
    }
    artifactInfos.forEach { artifactInfo ->
      stringArray.appendChild(document.createElement("item").also {
        it.textContent = artifactInfo.toString()
      })
    }
    rootElement.appendChild(stringArray)
    document.appendChild(rootElement)

    val transformer = TransformerFactory.newInstance().newTransformer().also {
      it.setOutputProperty(OutputKeys.INDENT, "yes")
    }
    val source = DOMSource(document)
    val file = StreamResult(outputFile);

    transformer.transform(source, file);
  }

  private fun collectDependenciesFromConfigurations(configurationContainer: ConfigurationContainer, visitedProjects: MutableSet<Project>): List<Dependency> {
    val directDependencies = mutableSetOf<Dependency>()
    val libraryProjects = mutableSetOf<Project>()
    for (configuration in configurationContainer) {
      if (shouldSkipConfiguration(configuration)) {
        continue
      }
      for (dependency in configuration.allDependencies) {
        if (dependency is ProjectDependency) {
          libraryProjects.add(dependency.dependencyProject)
        } else if (dependency is ExternalModuleDependency) {
          directDependencies.add(dependency)
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
    return directDependencies.sortedBy { toMavenId(it) }
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
    private val TEST_COMPILE = setOf("testCompile", "androidTestCompile")
    private val PACKAGED_DEPENDENCIES_PREFIXES = setOf("compile", "implementation", "api")
    private val artifactSet = mutableSetOf<String>()
  }

  private fun NodeList.asSequence() = NodeListSequence(this)

  private class NodeListSequence(private val nodes: NodeList) : Sequence<Node> {
    override fun iterator() = NodeListIterator(nodes)
  }

  private class NodeListIterator(private val nodes: NodeList) : Iterator<Node> {
    private var i = 0
    override fun hasNext() = nodes.length > i
    override fun next(): Node = nodes.item(i++)
  }

}
