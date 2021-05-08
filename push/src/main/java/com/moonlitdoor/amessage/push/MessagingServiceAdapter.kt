package com.moonlitdoor.amessage.push

import com.google.firebase.messaging.RemoteMessage
import com.moonlitdoor.amessage.domain.model.Token
import com.moonlitdoor.amessage.domain.repository.PayloadRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

class MessagingServiceAdapter @Inject constructor(
  private val profileRepository: ProfileRepository,
  private val payloadRepository: PayloadRepository,
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
          payloadRepository.insert(
            com.moonlitdoor.amessage.domain.model.Payload(
              type = type,
              id = UUID.fromString(id),
              cipher = payload
            )
          )
        }
      }
    }
  }
}
