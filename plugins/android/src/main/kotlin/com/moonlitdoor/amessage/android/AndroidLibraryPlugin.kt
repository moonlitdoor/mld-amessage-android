package com.moonlitdoor.amessage.android

import org.gradle.api.Project

class AndroidLibraryPlugin : AbstractAndroidPlugin() {
  override fun apply(project: Project) {
    project.plugins.apply("com.android.library")
    super.apply(project)
  }
}
