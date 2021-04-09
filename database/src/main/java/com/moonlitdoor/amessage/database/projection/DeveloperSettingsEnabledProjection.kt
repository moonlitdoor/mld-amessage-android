package com.moonlitdoor.amessage.database.projection

class DeveloperSettingsEnabledProjection(
  value: Boolean
) : KeyValueProjection<Boolean>(key = KEY, value = value) {
  companion object {
    const val KEY = "developer-settings-enabled"
  }
}
