package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.Profile
import com.moonlitdoor.amessage.network.json.ConnectionInvitePayload
import com.moonlitdoor.amessage.network.json.ConnectionJson
import java.util.*

object ConnectionMapper {

  fun from(connection: Connection) = ConnectionEntity(
    connectionId = connection.connectionId,
    password = connection.password,
    salt = connection.salt,
    token = connection.token,
    handle = connection.handle,
    state = state(connection.state),
    id = connection.id
  )

  fun state(state: Connection.State) = when (state) {
    Connection.State.Scanned -> ConnectionEntity.State.Scanned
    Connection.State.Queued -> ConnectionEntity.State.Queued
    Connection.State.Invited -> ConnectionEntity.State.Invited
    Connection.State.Pending -> ConnectionEntity.State.Pending
    Connection.State.Connected -> ConnectionEntity.State.Connected
  }

  fun state(state: ConnectionEntity.State) = when (state) {
    ConnectionEntity.State.Scanned -> Connection.State.Scanned
    ConnectionEntity.State.Queued -> Connection.State.Queued
    ConnectionEntity.State.Invited -> Connection.State.Invited
    ConnectionEntity.State.Pending -> Connection.State.Pending
    ConnectionEntity.State.Connected -> Connection.State.Connected
  }

  fun fromConnected(connection: Connection) = ConnectionEntity(
    connection.connectionId,
    connection.password,
    connection.salt,
    connection.token,
    connection.handle,
    ConnectionEntity.State.Connected,
    connection.id
  )

  fun fromScanned(profile: Profile) = ConnectionEntity(
    UUID.randomUUID(),
    UUID.randomUUID(),
    UUID.randomUUID(),
    profile.token,
    profile.handle,
    ConnectionEntity.State.Pending
  )

  fun fromPending(profile: Profile) = ConnectionEntity(
    UUID.randomUUID(),
    UUID.randomUUID(),
    UUID.randomUUID(),
    profile.token,
    profile.handle,
    ConnectionEntity.State.Pending
  )

  fun fromInvited(payload: ConnectionInvitePayload) = ConnectionEntity(
    payload.connectionId,
    payload.password,
    payload.salt,
    payload.token,
    payload.handle,
    ConnectionEntity.State.Invited
  )

  fun toJson(connection: Connection) = ConnectionJson(
    id = connection.id,
    connectionId = connection.connectionId,
    password = connection.password,
    salt = connection.salt,
    token = connection.token,
    handle = connection.handle
  )
}