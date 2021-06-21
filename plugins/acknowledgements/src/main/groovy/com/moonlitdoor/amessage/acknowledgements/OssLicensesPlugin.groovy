package com.moonlitdoor.amessage.acknowledgements

import com.android.build.gradle.api.BaseVariant
import org.gradle.api.Plugin
import org.gradle.api.Project

class OssLicensesPlugin implements Plugin<Project> {
  void apply(Project project) {
    println "AMESBURY GROOVY "
    def getDependencies = project.tasks.create("getDependencies2",
            DependencyTask2)
    def dependencyOutput = new File(project.buildDir,
            "generated/third_party_licenses2")
    def generatedJson = new File(dependencyOutput, "dependencies.json")
    getDependencies.setProject(project)
    getDependencies.outputDir = dependencyOutput
    getDependencies.outputFile = generatedJson

    def resourceOutput = new File(dependencyOutput, "/res")
    def outputDir = new File(resourceOutput, "/raw")
    def licensesFile = new File(outputDir, "third_party_licenses")
    def licensesMetadataFile = new File(outputDir,
            "third_party_license_metadata")
    def licenseTask = project.tasks.create("generateLicenses2", LicensesTask2)

    licenseTask.dependenciesJson = generatedJson
    licenseTask.outputDir = outputDir
    licenseTask.licenses = licensesFile
    licenseTask.licensesMetadata = licensesMetadataFile

    licenseTask.inputs.file(generatedJson)
    licenseTask.outputs.dir(outputDir)
    licenseTask.outputs.files(licensesFile, licensesMetadataFile)

    licenseTask.dependsOn(getDependencies)

    project.android.applicationVariants.all { BaseVariant variant ->
      // This is necessary for backwards compatibility with versions of gradle that do not support
      // this new API.
      if (variant.hasProperty("preBuildProvider")) {
        variant.preBuildProvider.configure { dependsOn(licenseTask) }
      } else {
        //noinspection GrDeprecatedAPIUsage
        variant.preBuild.dependsOn(licenseTask)
      }

      // This is necessary for backwards compatibility with versions of gradle that do not support
      // this new API.
      if (variant.respondsTo("registerGeneratedResFolders")) {
        licenseTask.ext.generatedResFolders = project.files(resourceOutput).builtBy(licenseTask)
        variant.registerGeneratedResFolders(licenseTask.generatedResFolders)

        if (variant.hasProperty("mergeResourcesProvider")) {
          variant.mergeResourcesProvider.configure { dependsOn(licenseTask) }
        } else {
          //noinspection GrDeprecatedAPIUsage
          variant.mergeResources.dependsOn(licenseTask)
        }
      } else {
        //noinspection GrDeprecatedAPIUsage
        variant.registerResGeneratingTask(licenseTask, resourceOutput)
      }
    }
  }
}
