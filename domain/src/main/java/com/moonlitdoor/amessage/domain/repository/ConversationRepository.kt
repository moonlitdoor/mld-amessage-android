package com.moonlitdoor.amessage.domain.repository

import com.moonlitdoor.amessage.database.dao.ConversationDao
import com.moonlitdoor.amessage.database.entity.ConnectionConversationEntity
import com.moonlitdoor.amessage.database.entity.ConversationEntity
import com.moonlitdoor.amessage.database.projection.IdProjection
import com.moonlitdoor.amessage.database.projection.TitleProjection
import com.moonlitdoor.amessage.database.projection.TopicProjection
import com.moonlitdoor.amessage.domain.model.Conversation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class ConversationRepository @Inject constructor(private val conversationDao: ConversationDao) {

  val conversations: Flow<List<Conversation>> = conversationDao.get().map { list -> list.map { Conversation.from(it) } }

  suspend fun create(connectionIds: List<UUID>, title: String?, topic: String?) {
    val conversation = ConversationEntity(title = title?.let { TitleProjection(it) }, topic = topic?.let { TopicProjection(it) })
    conversationDao.insert(conversation, connectionIds.map { ConnectionConversationEntity(conversation.conversationId, IdProjection(it)) })
  }
}
