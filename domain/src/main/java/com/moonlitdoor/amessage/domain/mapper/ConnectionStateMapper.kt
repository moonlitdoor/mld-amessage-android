package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.domain.model.Connection

object ConnectionStateMapper {
  fun map(state: Connection.State): ConnectionEntity.State = when (state) {
    Connection.State.Scanned -> ConnectionEntity.State.Scanned
    Connection.State.Queued -> ConnectionEntity.State.Queued
    Connection.State.Invited -> ConnectionEntity.State.Invited
    Connection.State.Pending -> ConnectionEntity.State.Pending
    Connection.State.Connected -> ConnectionEntity.State.Connected
  }

  fun map(state: ConnectionEntity.State): Connection.State = when (state) {
    ConnectionEntity.State.Scanned -> Connection.State.Scanned
    ConnectionEntity.State.Queued -> Connection.State.Queued
    ConnectionEntity.State.Invited -> Connection.State.Invited
    ConnectionEntity.State.Pending -> Connection.State.Pending
    ConnectionEntity.State.Connected -> Connection.State.Connected
  }
}
