package com.moonlitdoor.amessage.push

import com.google.firebase.messaging.RemoteMessage
import com.moonlitdoor.amessage.domain.mapper.AssociatedDataMapper
import com.moonlitdoor.amessage.domain.mapper.ConnectionMapper
import com.moonlitdoor.amessage.domain.mapper.KeysMapper
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.Token
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import com.moonlitdoor.amessage.dto.AssociatedDataDto
import com.moonlitdoor.amessage.dto.ConnectionConfirmationPayload
import com.moonlitdoor.amessage.dto.ConnectionInvitePayload
import com.moonlitdoor.amessage.dto.KeysDto
import com.moonlitdoor.amessage.dto.Payload
import com.moonlitdoor.amessage.extensions.ignore
import kotlinx.coroutines.flow.first
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

class MessagingServiceAdapter @Inject constructor(
  private val connectionRepository: ConnectionRepository,
  private val profileRepository: ProfileRepository
) {

  suspend fun onNewToken(token: String) {
    Timber.i("New Firebase Token")
    profileRepository.setToken(Token(token))
  }

  suspend fun onMessageReceived(remoteMessage: RemoteMessage) {
    Timber.i("New Firebase Message")
    remoteMessage.data["type"]?.let { type ->
      remoteMessage.data["id"]?.let { id ->
        remoteMessage.data["payload"]?.let { payload ->
          type(type, UUID.fromString(id), payload)
        }
      }
    }
  }

  private suspend fun type(type: String, id: UUID, payload: String): Unit = when (type) {
    Payload.Type.ConnectionInvite.value -> {
      val string = profileRepository.getProfile().first().let {
        Payload.decrypt(
          encryptedPayload = payload,
          keys = KeysDto(it.keys.value),
          associatedData = AssociatedDataDto(it.associatedData.value)
        )
      }

      connectionRepository.insert(
        ConnectionMapper.map(
          ConnectionInvitePayload.inflate(
            string
          )
        )
      ).ignore()
    }
    Payload.Type.ConnectionConfirmation.value -> {
      val connection = connectionRepository.get(id)
      val string = Payload.decrypt(payload, KeysMapper.mapToDto(connection.keys), AssociatedDataMapper.mapToDto(connection.associatedData))
      val connectionConfirmationPayload = ConnectionConfirmationPayload.inflate(string)
      connectionRepository.update(connection.copy(confirmed = connectionConfirmationPayload.confirmed, state = Connection.State.Connected)).ignore()
    }
    Payload.Type.ConnectionRejection.value -> connectionRepository.delete(id)
    else -> throw IllegalStateException("type=$type")
  }.also {
    Timber.i("New Firebase Message of type=$type")
  }
}
