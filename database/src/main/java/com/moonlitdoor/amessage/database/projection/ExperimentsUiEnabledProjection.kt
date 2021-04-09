package com.moonlitdoor.amessage.database.projection

class ExperimentsUiEnabledProjection(
  value: Boolean
) : KeyValueProjection<Boolean>(key = KEY, value = value) {
  companion object {
    const val KEY = "experiments-ui-enabled"
  }
}
