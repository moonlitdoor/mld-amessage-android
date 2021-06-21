package com.moonlitdoor.amessage.acknowledgements.tasks

import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.component.ModuleComponentIdentifier
import org.gradle.api.artifacts.result.ResolvedArtifactResult
import org.gradle.api.internal.artifacts.DefaultModuleIdentifier
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.internal.component.external.model.DefaultModuleComponentIdentifier
import org.gradle.internal.impldep.com.google.gson.JsonArray
import org.gradle.internal.impldep.com.google.gson.JsonObject
import org.gradle.maven.MavenModule
import org.gradle.maven.MavenPomArtifact
import java.io.File
import java.util.zip.ZipFile

open class LicensesTask : DefaultTask() {

  @Input
  protected val start = 0

  @Input
  protected val googleServiceLicenses = mutableSetOf<String>()

  @Input
  protected val licensesMap = mutableMapOf<String, String>()

  @InputFile
  lateinit var dependenciesJson: File

  @OutputDirectory
  lateinit var outputDir: File

  @OutputFile
  lateinit var licenses: File

  @OutputFile
  lateinit var licensesMetadata: File

  @TaskAction
  fun action() {
    initOutputDir()
    initLicenseFile()
    initLicensesMetadata()

    val allDependencies = JsonSlurper().parse(dependenciesJson) as JsonArray
    for (e in allDependencies) {
      val entry = e as JsonObject
      val group = entry["group"].asString
      val name = entry["name"].asString
      val fileLocation = entry["fileLocation"].asString
      val version = entry["version"].asString
      val artifactLocation = File(fileLocation)

      if (isGoogleServices(group)) {
        // Add license info for google-play-services itself
        if (!name.endsWith(LICENSE_ARTIFACT_SURFIX)) {
          addLicensesFromPom(group, name, version)
        }
        // Add transitive licenses info for google-play-services. For
        // post-granular versions, this is located in the artifact
        // itself, whereas for pre-granular versions, this information
        // is located at the complementary license artifact as a runtime
        // dependency.
        if (isGranularVersion(version)) {
          addGooglePlayServiceLicenses(artifactLocation)
        } else if (name.endsWith(LICENSE_ARTIFACT_SURFIX)) {
          addGooglePlayServiceLicenses(artifactLocation)
        }
      } else {
        addLicensesFromPom(group, name, version)
      }
    }

//    writeMetadata()
  }

  private fun initOutputDir() {
    if (!outputDir.exists()) {
      outputDir.mkdirs()
    }
  }

  private fun initLicenseFile() {
//    if (licenses == null) {
//      logger.error("License file is undefined")
//    }
//    licenses.newWriter().withWriter {w ->
//      w << ''
//    }
  }

  private fun initLicensesMetadata() {
//    licensesMetadata.writer().w  withWriter {w ->
//      w << ''
//    }
  }

  private fun isGranularVersion(version: String): Boolean {
    val versions = version.split("\\.")
    return (versions.isNotEmpty() && Integer.valueOf(versions[0]) >= GRANULAR_BASE_VERSION)
  }

  private fun isGoogleServices(group: String): Boolean {
    return (GOOGLE_PLAY_SERVICES_GROUP.equals(group, true)
        || FIREBASE_GROUP.equals(group, true))
  }

  private fun addLicensesFromPom(group: String, name: String, version: String) {
    val pomFile = resolvePomFileArtifact(group, name, version)
    addLicensesFromPom(pomFile, group, name)
  }

  private fun addLicensesFromPom(pomFile: File?, group: String, name: String) {
//    if (pomFile == null || !pomFile.exists()) {
//      logger.error("POM file $pomFile for $group:$name does not exist.")
//      return
//    }
//
//    val rootNode =  XmlSlurper().parse(pomFile)
//    if (rootNode.  find("licenses").size() == 0) {
//      return
//    }
//
//    val licenseKey = "${group}:${name}"
//    if (rootNode.licenses.license.size() > 1) {
//      rootNode.licenses.license.each { node ->
//        String nodeName = node . name
//            String nodeUrl = node . url
//            appendLicense("${licenseKey} ${nodeName}", nodeUrl.getBytes(UTF_8))
//      }
//    } else {
//      String nodeUrl = rootNode . licenses . license . url
//          appendLicense(licenseKey, nodeUrl.getBytes(UTF_8))
//    }
  }

  private fun resolvePomFileArtifact(group: String, name: String, version: String): File? {
    val moduleComponentIdentifier = createModuleComponentIdentifier(group, name, version)
    logger.info("Resolving POM file for $moduleComponentIdentifier licenses.")
    val components = project.dependencies
      .createArtifactResolutionQuery()
      .forComponents(moduleComponentIdentifier)
      .withArtifacts(MavenModule::class.java, MavenPomArtifact::class.java).execute()
    if (components.resolvedComponents.isEmpty()) {
      logger.warn("$moduleComponentIdentifier has no POM file.")
      return null
    }

    val artifacts = components.resolvedComponents.first().getArtifacts(MavenPomArtifact::class.java)
    if (artifacts.isEmpty()) {
      logger.error("$moduleComponentIdentifier empty POM artifact list.")
      return null
    }
    if (artifacts.first() !is ResolvedArtifactResult) {
      logger.error("$moduleComponentIdentifier unexpected type ${artifacts.first()::class.java}")
      return null
    }
    return (artifacts.first() as ResolvedArtifactResult).file
  }

  private fun addGooglePlayServiceLicenses(artifactFile: File) {
    val licensesZip = ZipFile(artifactFile)
    val jsonSlurper = JsonSlurper()

    val jsonFile = licensesZip.getEntry("third_party_licenses.json") ?: return
    val txtFile = licensesZip.getEntry("third_party_licenses.txt") ?: return

    val licensesObj = jsonSlurper.parse(licensesZip.getInputStream(jsonFile)) as JsonArray ?: return

//    for (e in licensesObj) {
//      val entry = e as JsonObject
//      String key = entry . key
//          int startValue = entry . value . start
//          int lengthValue = entry . value . length
//
//          if (!googleServiceLicenses.contains(key)) {
//            googleServiceLicenses.add(key)
//            byte[] content = getBytesFromInputStream (
//                licensesZip.getInputStream(txtFile),
//            startValue,
//            lengthValue)
//            appendLicense(key, content)
//          }
//    }
  }


  private fun createModuleComponentIdentifier(group: String, name: String, version: String): ModuleComponentIdentifier {
    return DefaultModuleComponentIdentifier(DefaultModuleIdentifier.newId(group, name), version)
  }

  companion object {
    private const val UTF_8 = "UTF-8"
    private val LINE_SEPARATOR = System.getProperty("line.separator").toByteArray()
    private const val GRANULAR_BASE_VERSION = 14
    private const val GOOGLE_PLAY_SERVICES_GROUP = "com.google.android.gms"
    private const val LICENSE_ARTIFACT_SURFIX = "-license"
    private const val FIREBASE_GROUP = "com.google.firebase"
    private const val FAIL_READING_LICENSES_ERROR = "Failed to read license text."
  }


}
