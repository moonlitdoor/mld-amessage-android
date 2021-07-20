package com.moonlitdoor.amessage.acknowledgements

import com.android.build.gradle.AppExtension
import com.moonlitdoor.amessage.acknowledgements.tasks.AcknowledgementsTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import java.util.Locale

class AcknowledgementsPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    project.afterEvaluate {
      val android = extensions.getByName("android")
      if (android is AppExtension) {
        android.applicationVariants.forEach { variant ->

          variant.registerGeneratedResFolders(project.files(File(project.buildDir, "generated/oss/${variant.name}")))

          val acknowledgementsTask = project.tasks.create("acknowledgements${variant.name.capitalize(Locale.ROOT)}", AcknowledgementsTask::class.java).also {
            it.outputDir = File(project.buildDir, "generated/oss/${variant.name}/values")
            it.outputFile = File(it.outputDir, "acknowledgements.xml")
          }

          variant.mergeResourcesProvider.configure {
            dependsOn(acknowledgementsTask)
          }

        }
      }
    }
  }
}
