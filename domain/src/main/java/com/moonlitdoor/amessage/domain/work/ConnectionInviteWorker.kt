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
import com.moonlitdoor.amessage.database.dao.ProfileDao
import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.domain.model.Profile
import com.moonlitdoor.amessage.network.NetworkClient
import com.moonlitdoor.amessage.network.NetworkRequestStatus
import com.moonlitdoor.amessage.network.json.ConnectionInvitePayload
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ConnectionInviteWorker @Inject constructor(
  private val profileDao: ProfileDao,
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
        profileDao.getProfileSync()?.let { profile ->
          inputData.getString(CONNECTION_ID)?.let { connectionId ->
            inputData.getString(CONNECTION_TOKEN)?.let { connectionToken ->
              inputData.getString(CONNECTION_PASSWORD)?.let { connectionPassword ->
                inputData.getString(CONNECTION_SALT)?.let { connectionSalt ->
                  when (client.send(
                    payload = ConnectionInvitePayload(
                      handle = profile.handle,
                      token = profile.token,
                      connectionId = connectionEntity.connectionId,
                      password = connectionEntity.password,
                      salt = connectionEntity.salt
                    ),
                    connectionId = UUID.fromString(connectionId),
                    token = connectionToken,
                    password = UUID.fromString(connectionPassword),
                    salt = UUID.fromString(connectionSalt)
                  )) {
                    NetworkRequestStatus.SENT -> {
                      connectionDao.update(connectionEntity.copy(state = ConnectionEntity.State.Invited))
                      return@withContext Result.success()
                    }
                    NetworkRequestStatus.QUEUED, NetworkRequestStatus.FAILED -> return@withContext Result.retry()
                  }
                }
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
    private const val CONNECTION_ID = "com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker.CONNECTION_ID"
    private const val CONNECTION_TOKEN = "com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker.CONNECTION_TOKEN"
    private const val CONNECTION_PASSWORD = "com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker.CONNECTION_PASSWORD"
    private const val CONNECTION_SALT = "com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker.CONNECTION_SALT"

    fun request() = OneTimeWorkRequest.Builder(ConnectionInviteWorker::class.java)
      .setConstraints(Constraints.Builder().build())
      .setInitialDelay(0L, TimeUnit.SECONDS)
      .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)

    fun data(connectionId: UUID, scannedProfile: Profile): Data = workDataOf(
      CONNECTION_UUID to connectionId.toString(),
      CONNECTION_ID to scannedProfile.id.toString(),
      CONNECTION_TOKEN to scannedProfile.token,
      CONNECTION_PASSWORD to scannedProfile.password.toString(),
      CONNECTION_SALT to scannedProfile.salt.toString()
    )

  }

}