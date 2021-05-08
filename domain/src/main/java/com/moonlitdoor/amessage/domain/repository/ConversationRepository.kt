package com.moonlitdoor.amessage.domain.repository

import androidx.work.WorkManager
import com.moonlitdoor.amessage.database.dao.ConnectionDao
import com.moonlitdoor.amessage.database.dao.ConversationDao
import com.moonlitdoor.amessage.database.entity.ConnectionConversationEntity
import com.moonlitdoor.amessage.database.entity.ConversationEntity
import com.moonlitdoor.amessage.database.projection.IdProjection
import com.moonlitdoor.amessage.database.projection.TitleProjection
import com.moonlitdoor.amessage.database.projection.TopicProjection
import com.moonlitdoor.amessage.domain.mapper.ConversationMapper
import com.moonlitdoor.amessage.domain.mapper.IdMapper
import com.moonlitdoor.amessage.domain.model.Conversation
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.domain.work.ConversationCreateWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class ConversationRepository @Inject constructor(
  private val conversationDao: ConversationDao,
  private val connectionDao: ConnectionDao,
  private val workManager: WorkManager,
) {

  fun getConversations(): Flow<List<Conversation>> = conversationDao.getConversationsFlow().map { list -> list.map { ConversationMapper.map(it) } }

  fun getConversation(conversationId: Id): Flow<Conversation> = conversationDao.getConversationFlow(conversationId.value).map { ConversationMapper.map(it) }

  suspend fun create(connectionIds: List<UUID>, title: String?, topic: String?) {
    val conversation = ConversationEntity(title = title?.let { TitleProjection(it) }, topic = topic?.let { TopicProjection(it) })
    conversationDao.insert(conversation, connectionIds.map { ConnectionConversationEntity(connectionId = IdProjection(it), conversationId = conversation.conversationId) })
    ConversationCreateWorker.enqueue(workManager, conversation, connectionIds)
  }

  suspend fun insert(conversation: Conversation, connectionIds: List<UUID>) {
    conversationDao.insert(ConversationMapper.mapToEntity(conversation))
    val (connected: List<UUID>, unconnected: List<UUID>) = connectionIds.partition { connectionDao.isConnectionExisting(it) == 1L }
    conversationDao.insert(
      connected.map {
        ConnectionConversationEntity(connectionId = IdProjection(it), conversationId = IdMapper.map(conversation.conversationId))
      }
    )
    // TODO handle unconnected connections
  }
}
