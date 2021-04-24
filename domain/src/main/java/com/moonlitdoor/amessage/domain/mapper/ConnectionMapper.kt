package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.domain.model.AssociatedData
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.Handle
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.domain.model.Keys
import com.moonlitdoor.amessage.domain.model.Token
import com.moonlitdoor.amessage.dto.ConnectionInvitePayload

object ConnectionMapper {

  fun map(connection: Connection) = ConnectionEntity(
    connectionId = IdMapper.map(connection.connectionId),
    token = TokenMapper.map(connection.token),
    handle = HandleMapper.map(connection.handle),
    keys = KeysMapper.map(connection.keys),
    associatedData = AssociatedDataMapper.map(connection.associatedData),
    state = ConnectionStateMapper.map(connection.state),
    scanned = connection.scanned,
  )

  fun map(entity: ConnectionEntity) = Connection(
    connectionId = IdMapper.map(entity.connectionId),
    token = TokenMapper.map(entity.token),
    handle = HandleMapper.map(entity.handle),
    keys = KeysMapper.map(entity.keys),
    associatedData = AssociatedDataMapper.map(entity.associatedData),
    state = ConnectionStateMapper.map(entity.state),
    scanned = entity.scanned,
  )

  fun map(payload: ConnectionInvitePayload) = Connection(
    connectionId = Id(payload.connectionId),
    token = Token(payload.token),
    handle = Handle(payload.handle),
    keys = Keys(payload.keys.value),
    associatedData = AssociatedData(payload.associatedData.value),
    state = Connection.State.Pending,
    scanned = payload.scanned,
  )
}
