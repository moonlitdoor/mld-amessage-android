package com.moonlitdoor.amessage.domain.model

import java.util.*

data class Password(val value: UUID) {
  companion object {
    fun create() = Password(UUID.randomUUID())
  }
}