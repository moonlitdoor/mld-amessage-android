package com.moonlitdoor.amessage.database.projection

class EmployeeSettingsEnabledProjection(
  value: Boolean
) : KeyValueProjection<Boolean>(key = KEY, value = value) {
  companion object {
    const val KEY = "employee-settings-enabled"
  }
}
