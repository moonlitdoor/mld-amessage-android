package com.moonlitdoor.amessage.init

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class DatabasePopulationService : IntentService(DatabasePopulationService::class.java.simpleName) {

  @Inject
  lateinit var profileRepository: ProfileRepository

  @Inject
  lateinit var connectionRepository: ConnectionRepository

  override fun onHandleIntent(intent: Intent?) {
    val handler = CoroutineExceptionHandler { _, exception ->
      Timber.e(exception)
    }

    GlobalScope.launch(context = handler) {
      Timber.d(profileRepository.getId().toString())
      Timber.d(profileRepository.getPassword().toString())
      Timber.d(profileRepository.getSalt().toString())
      /*Test Data*/
      if (connectionRepository.connectionCount() == 0L) {
        for (i in 1..25) {
          connectionRepository.insert(createConnection("token$i", "handle$i"))
        }
      }
    }

    GlobalScope.launch(context = handler) {
      delay(5000)

    }
  }

  private fun createConnection(token: String, handle: String) = Connection(
    connectionId = UUID.randomUUID(),
    password = UUID.randomUUID(),
    salt = UUID.randomUUID(),
    token = token,
    handle = handle,
    state = Connection.State.Connected
  )

  companion object {
    fun start(context: Context) {
      context.startService(Intent(context, DatabasePopulationService::class.java))
    }
  }
}