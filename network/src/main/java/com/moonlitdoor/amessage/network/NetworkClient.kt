package com.moonlitdoor.amessage.network

import com.moonlitdoor.amessage.dto.AssociatedDataDto
import com.moonlitdoor.amessage.dto.ConnectionDto
import com.moonlitdoor.amessage.dto.FirebaseMessageDto
import com.moonlitdoor.amessage.dto.KeysDto
import com.moonlitdoor.amessage.dto.PayloadDto
import com.moonlitdoor.amessage.network.client.FirebaseClient
import timber.log.Timber
import java.util.UUID

class NetworkClient(private val client: FirebaseClient) {

  suspend fun send(payload: PayloadDto, connectionId: UUID, token: String, keys: KeysDto, associatedData: AssociatedDataDto): NetworkRequestStatus {
    val message = FirebaseMessageDto(payload, connectionId, token, keys, associatedData)
    Timber.i(message.toString())
    val response = client.send(message)
    return when (response.isSuccessful) {
      true -> NetworkRequestStatus.SENT
      false -> NetworkRequestStatus.FAILED
    }
  }

  suspend fun send(payload: PayloadDto, connection: ConnectionDto): NetworkRequestStatus = send(payload, connection.connectionId, connection.token, connection.keys, connection.associatedData)
}
