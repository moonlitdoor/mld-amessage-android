package com.moonlitdoor.amessage.network

import com.moonlitdoor.amessage.dto.ConnectionJson
import com.moonlitdoor.amessage.dto.FirebaseMessageJson
import com.moonlitdoor.amessage.dto.Payload
import com.moonlitdoor.amessage.network.client.FirebaseClient
import timber.log.Timber
import java.util.*

class NetworkClient(private val client: FirebaseClient) {

  suspend fun send(payload: Payload, connectionId: UUID, token: String, password: UUID, salt: UUID): NetworkRequestStatus {
    val message = FirebaseMessageJson(payload, connectionId, token, password, salt)
    Timber.i(message.toString())
    val response = client.send(message)
    return when (response.isSuccessful) {
      true -> NetworkRequestStatus.SENT
      false -> NetworkRequestStatus.FAILED
    }
  }

  suspend fun send(payload: Payload, connection: ConnectionJson) = send(payload, connection.connectionId, connection.token, connection.password, connection.salt)

}