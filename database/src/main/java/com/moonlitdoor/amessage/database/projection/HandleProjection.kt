package com.moonlitdoor.amessage.database.projection

class HandleProjection(
  value: String
) : KeyValueProjection<String>(key = KEY, value = value) {
  companion object {
    const val KEY = "handle"
  }
}
