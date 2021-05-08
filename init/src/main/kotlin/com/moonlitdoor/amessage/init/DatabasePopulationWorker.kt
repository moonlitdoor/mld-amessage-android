package com.moonlitdoor.amessage.init

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import com.moonlitdoor.amessage.domain.work.RemoteMessageWorker
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.concurrent.TimeUnit

@HiltWorker
class DatabasePopulationWorker @AssistedInject constructor(
  @Assisted context: Context,
  @Assisted parameters: WorkerParameters,
  private var profileRepository: ProfileRepository,
) : CoroutineWorker(context, parameters) {

  override suspend fun doWork(): Result = coroutineScope {
    withContext(Dispatchers.IO) {
      Timber.d(profileRepository.getId().toString())
      Timber.d(profileRepository.getKeys().toString())
      Timber.d(profileRepository.getAssociatedData().toString())
      Result.success()
    }
  }

  companion object {

    fun enqueue(workManager: WorkManager) = workManager
      .beginWith(
        OneTimeWorkRequest.Builder(DatabasePopulationWorker::class.java)
          .setConstraints(Constraints.Builder().build())
          .setInitialDelay(0L, TimeUnit.SECONDS)
          .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS).build()
      ).then(RemoteMessageWorker.request())
      .enqueue()
  }
}
