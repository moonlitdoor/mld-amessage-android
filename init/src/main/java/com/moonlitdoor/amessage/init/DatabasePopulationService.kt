package com.moonlitdoor.amessage.init

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class DatabasePopulationService : IntentService(DatabasePopulationService::class.java.simpleName) {

  @Inject
  lateinit var repository: ProfileRepository

  override fun onHandleIntent(intent: Intent?) {
    val handler = CoroutineExceptionHandler { _, exception ->
      Timber.e(exception)
    }

    GlobalScope.launch(context = handler) {
      Timber.d(repository.getId().toString())
      Timber.d(repository.getPassword().toString())
      Timber.d(repository.getSalt().toString())
    }
  }

  companion object {
    fun start(context: Context) {
      context.startService(Intent(context, DatabasePopulationService::class.java))
    }
  }
}