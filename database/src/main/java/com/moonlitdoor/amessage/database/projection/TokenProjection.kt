package com.moonlitdoor.amessage.database.projection

class TokenProjection(
  value: String
) : KeyValueProjection<String>(key = KEY, value = value) {
  companion object {
    const val KEY = "token"
  }
}
