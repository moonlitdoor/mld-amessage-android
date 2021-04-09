package com.moonlitdoor.amessage.database.projection

import java.util.*

class IdProjection(
  value: UUID
) : KeyValueProjection<UUID>(key = KEY, value = value) {
  companion object {
    const val KEY = "id"
  }
}
