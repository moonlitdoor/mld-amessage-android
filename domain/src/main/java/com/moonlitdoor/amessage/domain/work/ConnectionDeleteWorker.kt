package com.moonlitdoor.amessage.domain.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.moonlitdoor.amessage.database.dao.ConnectionDao
import com.moonlitdoor.amessage.domain.model.Id
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.util.UUID
import java.util.concurrent.TimeUnit

@HiltWorker
class ConnectionDeleteWorker @AssistedInject constructor(
  @Assisted context: Context,
  @Assisted parameters: WorkerParameters,
  private val connectionDao: ConnectionDao,
) :
  CoroutineWorker(context, parameters) {

  override suspend fun doWork(): Result = coroutineScope {
    withContext(Dispatchers.IO) {
      inputData.getString(CONNECTION_UUID)?.let { connectionUuid ->

        if (connectionDao.delete(UUID.fromString(connectionUuid)) != 0) {
          return@withContext Result.success()
        }
      }
      Result.failure()
    }
  }

  companion object {

    private const val CONNECTION_UUID = "com.moonlitdoor.amessage.connection.uuid"

    fun request() = OneTimeWorkRequest.Builder(ConnectionDeleteWorker::class.java)
      .setConstraints(Constraints.Builder().build())
      .setInitialDelay(0L, TimeUnit.SECONDS)
      .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)

    fun data(connectionId: Id): Data = workDataOf(
      CONNECTION_UUID to connectionId.value.toString()
    )
  }
}
