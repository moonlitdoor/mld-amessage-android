package com.moonlitdoor.amessage.push

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.moonlitdoor.amessage.extensions.ignore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MessagingService : FirebaseMessagingService(), CoroutineScope {

  override val coroutineContext: CoroutineContext = Job() + Dispatchers.Default

  @Inject
  lateinit var adapter: MessagingServiceAdapter

  override fun onNewToken(token: String) = launch { adapter.onNewToken(token) }.ignore()

  override fun onMessageReceived(remoteMessage: RemoteMessage) = launch {
    adapter.onMessageReceived(remoteMessage)
  }.ignore()

  override fun onDestroy() {
    super.onDestroy()
    this.cancel()
  }
}
