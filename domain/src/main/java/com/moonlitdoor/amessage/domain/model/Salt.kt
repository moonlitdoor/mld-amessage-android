package com.moonlitdoor.amessage.domain.model

import java.util.*

data class Salt(val value: UUID) {
  companion object {
    fun create() = Salt(UUID.randomUUID())
  }
}