package com.moonlitdoor.amessage.database.projection

import java.util.*

class AssociatedDataProjection(
  value: UUID = UUID.randomUUID()
) : KeyValueProjection<UUID>(key = KEY, value = value) {
  companion object {
    const val KEY = "associated_data"
  }
}
