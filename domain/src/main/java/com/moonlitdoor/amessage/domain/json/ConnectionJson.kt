package com.moonlitdoor.amessage.domain.json

data class ConnectionJson(
  val id: Long = 0,
  val connectionId: String,
  val password: String,
  val salt: String,
  val token: String,
  val handle: String
)
