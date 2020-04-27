package com.moonlitdoor.amessage.push

import com.google.firebase.messaging.RemoteMessage
import com.moonlitdoor.amessage.extensions.ignore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FirebaseMessagingService : com.google.firebase.messaging.FirebaseMessagingService(), CoroutineScope {

  override val coroutineContext: CoroutineContext = Job() + Dispatchers.Default

  @Inject
  lateinit var adapter: FirebaseMessagingServiceAdapter

  override fun onCreate() {
    super.onCreate()
    PushDI.get(this).inject(this)
  }

  override fun onNewToken(token: String) = launch { adapter.onNewToken(token) }.ignore()

  override fun onMessageReceived(remoteMessage: RemoteMessage) = launch {
    adapter.onMessageReceived(remoteMessage)
  }.ignore()

  override fun onDestroy() {
    super.onDestroy()
    this.cancel()
  }
}
