package com.moonlitdoor.amessage.domain.work

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.moonlitdoor.amessage.database.dao.ConnectionDao
import com.moonlitdoor.amessage.domain.mapper.ConnectionMapper
import com.moonlitdoor.amessage.network.NetworkClient
import com.moonlitdoor.amessage.network.NetworkRequestStatus
import com.moonlitdoor.amessage.network.json.ConnectionConfirmationPayload
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ConnectionConfirmationWorker @Inject constructor(
  private val connectionDao: ConnectionDao,
  private val client: NetworkClient,
  context: Context,
  parameters: WorkerParameters
) :
  CoroutineWorker(context, parameters) {

  override suspend fun doWork(): Result = coroutineScope {
    withContext(Dispatchers.IO) {
      inputData.getString(CONNECTION_UUID)?.let { connectionUuid ->
        val connectionEntity = connectionDao.get(UUID.fromString(connectionUuid))
        when (client.send(ConnectionConfirmationPayload(), ConnectionMapper.toJson(connectionEntity))) {
          NetworkRequestStatus.SENT -> Result.success()
          NetworkRequestStatus.QUEUED -> Result.retry()
          NetworkRequestStatus.FAILED -> Result.failure()
        }
      }
      Result.failure()
    }
  }

  companion object {

    private const val CONNECTION_UUID = "com.moonlitdoor.amessage.connection.uuid"

    fun request() = OneTimeWorkRequest.Builder(ConnectionConfirmationWorker::class.java)
      .setConstraints(Constraints.Builder().build())
      .setInitialDelay(0L, TimeUnit.SECONDS)
      .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)

    fun data(connectionId: UUID): Data = workDataOf(
      CONNECTION_UUID to connectionId.toString()
    )
  }
}
