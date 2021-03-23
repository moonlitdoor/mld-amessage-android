package com.moonlitdoor.amessage.domain.model

import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.domain.mapper.ConnectionMapper
import java.util.*

data class Connection(
  val id: Long = 0,
  val connectionId: UUID,
  val password: UUID,
  val salt: UUID,
  val token: String,
  val handle: String,
  val state: State
) {
  companion object {
    fun from(entity: ConnectionEntity) = Connection(
      entity.id,
      entity.connectionId,
      entity.password,
      entity.salt,
      entity.token,
      entity.handle,
      ConnectionMapper.state(entity.state)
    )

    fun from(selected: SelectableConnection) = Connection(
      selected.id,
      selected.connectionId,
      selected.password,
      selected.salt,
      selected.token,
      selected.handle,
      State.Connected
    )
  }

  sealed class State(val value: String) {
    object Scanned : State("scanned")
    object Queued : State("queued")
    object Pending : State("pending")
    object Invited : State("invited")
    object Connected : State("connected")
  }
}
