package com.moonlitdoor.amessage.database.projection

import java.util.*

class PasswordProjection(
  value: UUID
) : KeyValueProjection<UUID>(key = KEY, value = value) {
  companion object {
    const val KEY = "password"
  }
}
