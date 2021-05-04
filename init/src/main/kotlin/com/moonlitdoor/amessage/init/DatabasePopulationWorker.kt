package com.moonlitdoor.amessage.init

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.moonlitdoor.amessage.domain.model.AssociatedData
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.Handle
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.domain.model.Keys
import com.moonlitdoor.amessage.domain.model.Token
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.time.Instant
import java.util.concurrent.TimeUnit

@HiltWorker
class DatabasePopulationWorker @AssistedInject constructor(
  @Assisted context: Context,
  @Assisted parameters: WorkerParameters,
  private var profileRepository: ProfileRepository,
  private var connectionRepository: ConnectionRepository,
) : CoroutineWorker(context, parameters) {

  override suspend fun doWork(): Result = coroutineScope {
    withContext(Dispatchers.IO) {
      Timber.d(profileRepository.getId().toString())
      Timber.d(profileRepository.getKeys().toString())
      Timber.d(profileRepository.getAssociatedData().toString())
      /* Test Data */
      if (connectionRepository.connectionCount() == 0L && false) {
        for (i in 1..100) {
          val state = when {
            i % 5 == 1 -> Connection.State.Queued
            i % 5 == 2 -> Connection.State.Pending
            i % 5 == 3 -> Connection.State.Invited
            i % 5 == 4 -> Connection.State.Scanned
            else -> Connection.State.Connected
          }
          connectionRepository.insert(createConnection(i, state))
        }
      }
      Result.success()
    }
  }

  private fun createConnection(index: Int, state: Connection.State) = Connection(
    connectionId = Id(),
    profileId = Id(),
    token = Token("token$index"),
    handle = Handle("handle$index"),
    state = state,
    associatedData = AssociatedData(),
    keys = Keys("keys$index"),
    scanned = Instant.now(),
  )

  companion object {

    fun enqueue(workManager: WorkManager) = workManager
      .beginWith(
        OneTimeWorkRequest.Builder(DatabasePopulationWorker::class.java)
          .setConstraints(Constraints.Builder().build())
          .setInitialDelay(0L, TimeUnit.SECONDS)
          .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS).build()
      )
      .enqueue()
  }
}
