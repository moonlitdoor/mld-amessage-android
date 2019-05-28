package com.moonlitdoor.amessage.domain.service

import com.google.firebase.messaging.RemoteMessage
import org.koin.android.ext.android.get

class FirebaseMessagingService : com.google.firebase.messaging.FirebaseMessagingService() {

  private val service: FirebaseMessagingServiceAdapter = get()

  override fun onNewToken(token: String?) = service.onNewToken(token)

  override fun onMessageReceived(remoteMessage: RemoteMessage?) = service.onMessageReceived(remoteMessage)

}
