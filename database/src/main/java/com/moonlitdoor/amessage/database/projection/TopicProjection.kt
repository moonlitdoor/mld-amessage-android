package com.moonlitdoor.amessage.database.projection

class TopicProjection(
  value: String
) : KeyValueProjection<String>(key = KEY, value = value) {
  companion object {
    const val KEY = "subject"
  }
}
