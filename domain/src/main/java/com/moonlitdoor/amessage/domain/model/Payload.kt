package com.moonlitdoor.amessage.domain.model

import java.util.UUID

data class Payload(
  val primary: Long = 0,
  val type: String,
  val id: UUID,
  val cipher: String
)
