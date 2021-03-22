package com.moonlitdoor.amessage.android

import org.gradle.api.Project

class AndroidApplicationPlugin : AbstractAndroidPlugin() {
  override fun apply(project: Project) {
    project.plugins.apply("com.android.application")
    super.apply(project)
  }
}
