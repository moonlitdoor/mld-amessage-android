package com.moonlitdoor.amessage.acknowledgements

import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.BaseVariant
import com.moonlitdoor.amessage.acknowledgements.tasks.DependencyTask
import com.moonlitdoor.amessage.acknowledgements.tasks.LicensesTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.FileCollection
import org.gradle.kotlin.dsl.extra
import java.io.File

class AcknowledgementsPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    val getDependencies = project.tasks.create("getDependencies", DependencyTask::class.java)
    val dependencyOutput = File(project.buildDir, "generated/third_party_licenses")
    val generatedJson = File(dependencyOutput, "dependencies.json")
    getDependencies.outputDir = dependencyOutput
    getDependencies.outputFile = generatedJson

    val resourceOutput = File(dependencyOutput, "/res")
    val outputDir = File(resourceOutput, "/raw")
    val licensesFile = File(outputDir, "third_party_licenses")
    val licensesMetadataFile = File(outputDir, "third_party_license_metadata")
    val licenseTask = project.tasks.create("generateLicenses", LicensesTask::class.java)

    licenseTask.dependenciesJson = generatedJson
    licenseTask.outputDir = outputDir
    licenseTask.licenses = licensesFile
    licenseTask.licensesMetadata = licensesMetadataFile

    licenseTask.inputs.file(generatedJson)
    licenseTask.outputs.dir(outputDir)
    licenseTask.outputs.files(licensesFile, licensesMetadataFile)

    licenseTask.dependsOn(getDependencies)

    project.afterEvaluate {
      val android = extensions.getByName("android")
      if (android is AppExtension) {
        android.applicationVariants.forEach { variant: BaseVariant ->
          variant.preBuildProvider.configure { dependsOn(licenseTask) }

          licenseTask.extra["generatedResFolders"] = project.files(resourceOutput).builtBy(licenseTask)
          variant.registerGeneratedResFolders(licenseTask.extra["generatedResFolders"] as FileCollection)
          variant.mergeResourcesProvider.configure { dependsOn(licenseTask) }
        }
      }
    }
  }
}
