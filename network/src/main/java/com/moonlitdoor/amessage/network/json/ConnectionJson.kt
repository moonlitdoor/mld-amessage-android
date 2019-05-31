package com.moonlitdoor.amessage.network.json

import java.util.*

data class ConnectionJson(
  val id: Long = 0,
  val connectionId: UUID,
  val password: UUID,
  val salt: UUID,
  val token: String,
  val handle: String
)
