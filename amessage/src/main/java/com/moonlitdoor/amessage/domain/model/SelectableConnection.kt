package com.moonlitdoor.amessage.domain.model

import java.util.*

data class SelectableConnection(
  val id: Long = 0,
  val connectionId: UUID,
  val password: UUID,
  val salt: UUID,
  val token: String,
  val handle: String,
  var selected: Boolean = false
) {
  companion object {
    fun from(connection: Connection) = SelectableConnection(
      connection.id,
      connection.connectionId,
      connection.password,
      connection.salt,
      connection.token,
      connection.handle
    )
  }
}