package com.moonlitdoor.amessage.domain.service

import com.google.firebase.messaging.RemoteMessage
import com.moonlitdoor.amessage.domain.entity.ConnectionEntity
import com.moonlitdoor.amessage.domain.json.ConnectionInvitePayload
import com.moonlitdoor.amessage.domain.json.Payload
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import com.moonlitdoor.amessage.extensions.ignore
import org.koin.standalone.KoinComponent
import timber.log.Timber
import java.util.*

class FirebaseMessagingServiceAdapter(private val connectionRepository: ConnectionRepository, private val profileRepository: ProfileRepository) : KoinComponent {

  fun onNewToken(token: String?) {
    token?.let {
      Timber.i("New Firebase Token")
      profileRepository.setToken(it)
    }
  }

  fun onMessageReceived(remoteMessage: RemoteMessage?) {
    Timber.i("New Firebase Message")
    remoteMessage?.let {
      it.data["type"]?.let { type ->
        it.data["id"]?.let { id ->
          it.data["payload"]?.let { payload ->
            type(type.toInt(), UUID.fromString(id), payload)
          }
        }
      }
    }
  }

  private fun type(type: Int, id: UUID, payload: String) = when (type) {
    Payload.Type.ConnectionInvite.value -> connectionRepository.insert(
      ConnectionEntity.fromInvited(
        ConnectionInvitePayload.inflate(
          Payload.decrypt(
            payload,
            profileRepository.getProfile()?.password.toString(),
            profileRepository.getProfile()?.salt.toString()
          )
        )
      )
    ).ignore()
    Payload.Type.ConnectionConfirmation.value -> connectionRepository.update(id, Connection.State.Connected)
    Payload.Type.ConnectionRejection.value -> connectionRepository.delete(id)
    else -> throw IllegalStateException("type=$type")
  }.also {
    Timber.i("New Firebase Message of type=$type")
  }

}
