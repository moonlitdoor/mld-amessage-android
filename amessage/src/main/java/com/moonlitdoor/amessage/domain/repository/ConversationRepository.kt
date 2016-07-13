package com.moonlitdoor.amessage.domain.repository

import androidx.lifecycle.LiveData
import com.moonlitdoor.amessage.domain.dao.ConversationDao
import com.moonlitdoor.amessage.domain.entity.ConversationConnectionEntity
import com.moonlitdoor.amessage.domain.entity.ConversationEntity
import com.moonlitdoor.amessage.domain.model.Conversation
import com.moonlitdoor.amessage.extensions.map

class ConversationRepository(private val conversationDao: ConversationDao) {

  val conversations: LiveData<List<Conversation>> = conversationDao.get().map { it.map { Conversation.from(it) } }

  fun create(conversation: Conversation) {

    conversationDao.insert(ConversationEntity.from(conversation), ConversationConnectionEntity.fromAsList(conversation))

  }
}