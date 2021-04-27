package com.moonlitdoor.amessage.domain.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.moonlitdoor.amessage.database.dao.ConnectionDao
import com.moonlitdoor.amessage.domain.mapper.ConnectionMapper
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.dto.ConnectionConfirmPayload
import com.moonlitdoor.amessage.network.NetworkClient
import com.moonlitdoor.amessage.network.NetworkRequestStatus
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.util.UUID
import java.util.concurrent.TimeUnit

@HiltWorker
class ConnectionConfirmWorker @AssistedInject constructor(
  @Assisted context: Context,
  @Assisted parameters: WorkerParameters,
  private val connectionDao: ConnectionDao,
  private val client: NetworkClient,
) :
  CoroutineWorker(context, parameters) {

  override suspend fun doWork(): Result = coroutineScope {
    withContext(Dispatchers.IO) {
      inputData.getString(CONNECTION_UUID)?.let { connectionUuid ->
        val connectionEntity = connectionDao.get(UUID.fromString(connectionUuid))
        return@withContext when (client.send(ConnectionConfirmPayload(connectionEntity.confirmed), ConnectionMapper.mapToDto(connectionEntity))) {
          NetworkRequestStatus.SENT -> Result.success()
          NetworkRequestStatus.QUEUED,
          NetworkRequestStatus.FAILED -> Result.retry()
        }
      }
      Result.failure()
    }
  }

  companion object {

    private const val CONNECTION_UUID = "com.moonlitdoor.amessage.connection.uuid"

    private fun request() = OneTimeWorkRequest.Builder(ConnectionConfirmWorker::class.java)
      .setConstraints(Constraints.Builder().build())
      .setInitialDelay(0L, TimeUnit.SECONDS)
      .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)

    fun data(connectionId: Id): Data = workDataOf(
      CONNECTION_UUID to connectionId.value.toString()
    )

    fun enqueue(workManager: WorkManager, connectionId: Id) = workManager.enqueue(request().setInputData(data(connectionId = connectionId)).build())
  }
}
