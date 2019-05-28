package com.moonlitdoor.amessage.domain.model

import com.moonlitdoor.amessage.domain.entity.ConnectionEntity
import java.util.*

data class Connection(
  val id: Long = 0,
  val connectionId: UUID,
  val password: UUID,
  val salt: UUID,
  val token: String,
  val handle: String,
  val state: Connection.State
) {
  companion object {
    fun from(entity: ConnectionEntity) = Connection(
      entity.id,
      entity.connectionId,
      entity.password,
      entity.salt,
      entity.token,
      entity.handle,
      entity.state
    )

    fun from(selected: SelectableConnection) = Connection(
      selected.id,
      selected.connectionId,
      selected.password,
      selected.salt,
      selected.token,
      selected.handle,
      Connection.State.Connected
    )
  }

  sealed class State(val value: String) {
    object Pending : State("pending")
    object Invited : State("invited")
    object Connected : State("connected")
  }
}
