package com.moonlitdoor.amessage.domain.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkerParameters
import com.moonlitdoor.amessage.database.dao.ConnectionDao
import com.moonlitdoor.amessage.database.dao.ProfileDao
import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.dto.AssociatedDataDto
import com.moonlitdoor.amessage.dto.ConnectionInvitePayload
import com.moonlitdoor.amessage.dto.KeysDto
import com.moonlitdoor.amessage.network.NetworkClient
import com.moonlitdoor.amessage.network.NetworkRequestStatus
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit

@HiltWorker
class ConnectionInviteWorker @AssistedInject constructor(
  @Assisted context: Context,
  @Assisted parameters: WorkerParameters,
  private val profileDao: ProfileDao,
  private val connectionDao: ConnectionDao,
  private val client: NetworkClient
) : CoroutineWorker(context, parameters) {

  override suspend fun doWork(): Result = coroutineScope {
    withContext(Dispatchers.IO) {
      val profile = profileDao.getProfile().first()
      inputData.getString(CONNECTION_UUID)?.let { newConnectionUuid ->
        val newConnectionEntity = connectionDao.get(UUID.fromString(newConnectionUuid)).first()
        inputData.getString(SCANNED_TOKEN)?.let { scannedToken ->
          inputData.getString(SCANNED_ASSOCIATED_DATA)?.let { scannedAssociatedData ->
            inputData.getString(SCANNED_KEYS)?.let { scannedKeys ->
              when (client.send(
                payload = ConnectionInvitePayload(
                  handle = profile.handle.value,
                  token = profile.token.value,
                  connectionId = newConnectionEntity.connectionId.value,
                  keys = KeysDto(scannedKeys),
                  associatedData = AssociatedDataDto(UUID.fromString(scannedAssociatedData)),
                  scanned = newConnectionEntity.scanned,
                ),
                connectionId = newConnectionEntity.connectionId.value,
                token = scannedToken,
                keys = KeysDto(scannedKeys),
                associatedData = AssociatedDataDto(UUID.fromString(scannedAssociatedData))
              )) {
                NetworkRequestStatus.SENT -> {
                  connectionDao.update(newConnectionEntity.copy(state = ConnectionEntity.State.Invited))
                  return@withContext Result.success()
                }
                NetworkRequestStatus.QUEUED, NetworkRequestStatus.FAILED -> return@withContext Result.retry()
              }
            }
          }
        }
      }
      Result.failure()
    }
  }

  companion object {

    private const val CONNECTION_UUID = "com.moonlitdoor.amessage.connection.uuid"
    private const val SCANNED_TOKEN = "com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker.CONNECTION_TOKEN"
    private const val SCANNED_ASSOCIATED_DATA = "com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker.CONNECTION_ASSOCIATED_DATA"
    private const val SCANNED_KEYS = "com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker.CONNECTION_KEYS"

    fun request() = OneTimeWorkRequest.Builder(ConnectionInviteWorker::class.java)
      .setConstraints(Constraints.Builder().build())
      .setInitialDelay(0L, TimeUnit.SECONDS)
      .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)

    fun data(newConnection: ConnectionEntity, scannedConnection: ConnectionEntity): Data = Data.Builder().putAll(
      mapOf(
        CONNECTION_UUID to newConnection.connectionId.value.toString(),
        SCANNED_TOKEN to scannedConnection.token.value,
        SCANNED_ASSOCIATED_DATA to scannedConnection.associatedData.value.toString(),
        SCANNED_KEYS to scannedConnection.keys.value
      )
    ).build()

  }

}
