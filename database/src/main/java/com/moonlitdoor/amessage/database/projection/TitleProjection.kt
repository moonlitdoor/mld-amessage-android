package com.moonlitdoor.amessage.database.projection

class TitleProjection(
  value: String
) : KeyValueProjection<String>(key = KEY, value = value) {
  companion object {
    const val KEY = "title"
  }
}
