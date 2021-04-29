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
import com.moonlitdoor.amessage.database.dao.ConversationDao
import com.moonlitdoor.amessage.database.entity.ConversationEntity
import com.moonlitdoor.amessage.domain.mapper.AssociatedDataMapper
import com.moonlitdoor.amessage.domain.mapper.ConnectionMapper
import com.moonlitdoor.amessage.domain.mapper.KeysMapper
import com.moonlitdoor.amessage.dto.ConversationCreatePayload
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
class ConversationCreateWorker @AssistedInject constructor(
  @Assisted context: Context,
  @Assisted parameters: WorkerParameters,
  private val connectionDao: ConnectionDao,
  private val conversationDao: ConversationDao,
  private val client: NetworkClient,
) :
  CoroutineWorker(context, parameters) {

  override suspend fun doWork(): Result = coroutineScope {
    withContext(Dispatchers.IO) {
      inputData.getString(CONNECTION_UUID)?.let { connectionUuid ->
        inputData.getString(CONVERSATION_UUID)?.let { conversationUuid ->
          inputData.getStringArray(CONNECTION_UUIDS)?.let { connectionUuids ->
            val connectionEntity = connectionDao.get(UUID.fromString(connectionUuid))
            val conversationEntity = conversationDao.get(UUID.fromString(conversationUuid))
            return@withContext when (
              client.send(
                ConversationCreatePayload(
                  conversationId = UUID.fromString(conversationUuid),
                  title = conversationEntity.title?.value,
                  topic = conversationEntity.topic?.value,
                  created = conversationEntity.created,
                  keys = KeysMapper.mapToDto(conversationEntity.keys),
                  associatedData = AssociatedDataMapper.mapToDto(conversationEntity.associatedData),
                  connectionUuids = connectionUuids.map { UUID.fromString(it) }.toTypedArray()
                ),
                ConnectionMapper.mapToDto(connectionEntity)
              )
            ) {
              NetworkRequestStatus.SENT -> Result.success()
              NetworkRequestStatus.QUEUED,
              NetworkRequestStatus.FAILED -> Result.retry()
            }
          }
        }
      }
      Result.failure()
    }
  }

  companion object {

    private const val CONNECTION_UUID = "com.moonlitdoor.amessage.connection.uuid"
    private const val CONNECTION_UUIDS = "com.moonlitdoor.amessage.connection.uuids"
    private const val CONVERSATION_UUID = "com.moonlitdoor.amessage.conversation.uuid"

    private fun request() = OneTimeWorkRequest.Builder(ConversationCreateWorker::class.java)
      .setConstraints(Constraints.Builder().build())
      .setInitialDelay(0L, TimeUnit.SECONDS)
      .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)

    fun data(connectionId: UUID, conversationId: UUID, connectionIds: List<UUID>): Data = workDataOf(
      CONNECTION_UUID to connectionId.toString(),
      CONVERSATION_UUID to conversationId.toString(),
      CONNECTION_UUIDS to connectionIds.map { it.toString() }.toTypedArray()
    )

    fun enqueue(workManager: WorkManager, conversation: ConversationEntity, connectionIds: List<UUID>) =
      workManager.enqueue(
        connectionIds.map {
          request()
            .setInputData(data(connectionId = it, conversationId = conversation.conversationId.value, connectionIds))
            .build()
        }
      )
  }
}
