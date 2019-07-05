package com.moonlitdoor.amessage.domain.service

import com.google.firebase.messaging.RemoteMessage
import com.moonlitdoor.amessage.extensions.ignore
import kotlinx.coroutines.*
import org.koin.android.ext.android.get
import kotlin.coroutines.CoroutineContext

class FirebaseMessagingService : com.google.firebase.messaging.FirebaseMessagingService(), CoroutineScope {

  override val coroutineContext: CoroutineContext = Job() + Dispatchers.Default

  private val adapter: FirebaseMessagingServiceAdapter = get()

  override fun onNewToken(token: String?) = adapter.onNewToken(token)

  override fun onMessageReceived(remoteMessage: RemoteMessage?) = launch { adapter.onMessageReceived(remoteMessage) }.ignore()

  override fun onDestroy() {
    super.onDestroy()
    this.cancel()
  }
}
