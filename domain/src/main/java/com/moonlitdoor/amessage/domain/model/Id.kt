package com.moonlitdoor.amessage.domain.model

import java.util.*

data class Id(val value: UUID) {
  companion object {
    fun create() = Id(UUID.randomUUID())
  }
}