package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.domain.model.AssociatedData
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.Handle
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.domain.model.Keys
import com.moonlitdoor.amessage.domain.model.Password
import com.moonlitdoor.amessage.domain.model.Salt
import com.moonlitdoor.amessage.domain.model.Token
import com.moonlitdoor.amessage.dto.ConnectionInvitePayload

object ConnectionMapper {

  fun map(connection: Connection) = ConnectionEntity(
    connectionId = IdMapper.map(connection.connectionId),
    password = PasswordMapper.map(connection.password),
    salt = SaltMapper.map(connection.salt),
    token = TokenMapper.map(connection.token),
    handle = HandleMapper.map(connection.handle),
    keys = KeysMapper.map(connection.keys),
    associatedData = AssociatedDataMapper.map(connection.associatedData),
    state = ConnectionStateMapper.map(connection.state),
    id = connection.id,
  )

  fun map(entity: ConnectionEntity) = Connection(
    connectionId = IdMapper.map(entity.connectionId),
    password = PasswordMapper.map(entity.password),
    salt = SaltMapper.map(entity.salt),
    token = TokenMapper.map(entity.token),
    handle = HandleMapper.map(entity.handle),
    keys = KeysMapper.map(entity.keys),
    associatedData = AssociatedDataMapper.map(entity.associatedData),
    state = ConnectionStateMapper.map(entity.state),
    id = entity.id,
  )

  fun map(payload: ConnectionInvitePayload) = Connection(
    connectionId = Id(payload.connectionId),
    password = Password(payload.password),
    salt = Salt(payload.salt),
    token = Token(payload.token),
    handle = Handle(payload.handle),
    state = Connection.State.Pending,
    associatedData = AssociatedData(payload.associatedData),
    keys = Keys(payload.keys)
  )

}