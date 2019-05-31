package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.network.json.ConnectionJson

object ConnectionMapper {

  fun toJson(connection: Connection) = ConnectionJson(
    id = connection.id,
    connectionId = connection.connectionId,
    password = connection.password,
    salt = connection.salt,
    token = connection.token,
    handle = connection.handle
  )
}