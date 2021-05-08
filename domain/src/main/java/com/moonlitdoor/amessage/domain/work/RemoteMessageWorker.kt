package com.moonlitdoor.amessage.domain.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.moonlitdoor.amessage.domain.mapper.AssociatedDataMapper
import com.moonlitdoor.amessage.domain.mapper.ConnectionMapper
import com.moonlitdoor.amessage.domain.mapper.ConversationMapper
import com.moonlitdoor.amessage.domain.mapper.KeysMapper
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import com.moonlitdoor.amessage.domain.repository.ConversationRepository
import com.moonlitdoor.amessage.domain.repository.PayloadRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import com.moonlitdoor.amessage.dto.AssociatedDataDto
import com.moonlitdoor.amessage.dto.ConnectionConfirmPayload
import com.moonlitdoor.amessage.dto.ConnectionInvitePayload
import com.moonlitdoor.amessage.dto.ConversationCreatePayload
import com.moonlitdoor.amessage.dto.KeysDto
import com.moonlitdoor.amessage.dto.PayloadDto
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

@HiltWorker
class RemoteMessageWorker @AssistedInject constructor(
  @Assisted context: Context,
  @Assisted parameters: WorkerParameters,
  private val payloadRepository: PayloadRepository,
  private val profileRepository: ProfileRepository,
  private val connectionRepository: ConnectionRepository,
  private val conversationRepository: ConversationRepository,
) : CoroutineWorker(context, parameters) {

  override suspend fun doWork(): Result = coroutineScope {
    withContext(Dispatchers.IO) {
      payloadRepository.get(
        listOf(
          PayloadDto.Type.ConnectionInvite,
          PayloadDto.Type.ConnectionConfirm,
          PayloadDto.Type.ConnectionReject,
          PayloadDto.Type.ConversationCreate,
        )
      ).forEach { payload ->
        when (payload.type) {
          PayloadDto.Type.ConnectionInvite.value -> {
            val string = profileRepository.getProfile().let {
              PayloadDto.decrypt(
                encryptedPayload = payload.cipher,
                keys = KeysDto(it.keys.value),
                associatedData = AssociatedDataDto(it.associatedData.value)
              )
            }
            connectionRepository.insert(ConnectionMapper.map(ConnectionInvitePayload.inflate(string)))
          }
          PayloadDto.Type.ConnectionConfirm.value -> {
            val connection = connectionRepository.get(payload.id)
            val string = PayloadDto.decrypt(payload.cipher, KeysMapper.mapToDto(connection.keys), AssociatedDataMapper.mapToDto(connection.associatedData))
            val connectionConfirmationPayload = ConnectionConfirmPayload.inflate(string)
            connectionRepository.update(connection.copy(confirmed = connectionConfirmationPayload.confirmed, state = Connection.State.Connected))
          }
          PayloadDto.Type.ConnectionReject.value -> connectionRepository.delete(payload.id)
          PayloadDto.Type.ConversationCreate.value -> {
            val connection = connectionRepository.get(payload.id)
            val string = PayloadDto.decrypt(payload.cipher, KeysMapper.mapToDto(connection.keys), AssociatedDataMapper.mapToDto(connection.associatedData))
            val conversationCreatePayload = ConversationCreatePayload.inflate(string)
            conversationRepository.insert(ConversationMapper.map(conversationCreatePayload), conversationCreatePayload.connectionUuids.toList())
          }
        }
        payloadRepository.delete(payload)
      }
      Result.success()
    }
  }

  companion object {

    fun request() = OneTimeWorkRequest.Builder(RemoteMessageWorker::class.java)
      .setConstraints(Constraints.Builder().build())
      .setInitialDelay(0L, TimeUnit.SECONDS)
      .setBackoffCriteria(
        BackoffPolicy.EXPONENTIAL,
        OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
        TimeUnit.MILLISECONDS
      ).build()

    fun enqueue(workManager: WorkManager) = workManager.enqueue(
      request()
    )
  }
}
