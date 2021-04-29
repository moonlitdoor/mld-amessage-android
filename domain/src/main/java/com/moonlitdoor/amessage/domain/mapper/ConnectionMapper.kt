package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.domain.model.AssociatedData
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.Handle
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.domain.model.Keys
import com.moonlitdoor.amessage.domain.model.Token
import com.moonlitdoor.amessage.dto.ConnectionDto
import com.moonlitdoor.amessage.dto.ConnectionInvitePayload

object ConnectionMapper {

  fun mapToEntity(connection: Connection) = ConnectionEntity(
    connectionId = IdMapper.map(connection.connectionId),
    profileId = IdMapper.map(connection.profileId),
    token = TokenMapper.map(connection.token),
    handle = HandleMapper.map(connection.handle),
    keys = KeysMapper.mapToProjection(connection.keys),
    associatedData = AssociatedDataMapper.mapToProjection(connection.associatedData),
    state = ConnectionStateMapper.map(connection.state),
    scanned = connection.scanned,
    confirmed = connection.confirmed,
  )

  fun map(entity: ConnectionEntity) = Connection(
    profileId = IdMapper.map(entity.profileId),
    connectionId = IdMapper.map(entity.connectionId),
    token = TokenMapper.map(entity.token),
    handle = HandleMapper.map(entity.handle),
    keys = KeysMapper.map(entity.keys),
    associatedData = AssociatedDataMapper.map(entity.associatedData),
    state = ConnectionStateMapper.map(entity.state),
    scanned = entity.scanned,
    confirmed = entity.confirmed,
  )

  fun map(payload: ConnectionInvitePayload) = Connection(
    connectionId = Id(payload.connectionId),
    profileId = Id(payload.profileId),
    token = Token(payload.token),
    handle = Handle(payload.handle),
    keys = Keys(payload.keys.value),
    associatedData = AssociatedData(payload.associatedData.value),
    state = Connection.State.Pending,
    scanned = payload.scanned,
    confirmed = payload.confirmed,
  )

  fun mapToDto(entity: ConnectionEntity) = ConnectionDto(
    connectionId = entity.connectionId.value,
    profileId = entity.profileId.value,
    token = entity.token.value,
    handle = entity.handle.value,
    associatedData = AssociatedDataMapper.mapToDto(entity.associatedData),
    keys = KeysMapper.mapToDto(entity.keys),
    scanned = entity.scanned,
    confirmed = entity.confirmed,
  )
}
