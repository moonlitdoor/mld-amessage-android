package com.moonlitdoor.amessage.domain.repository

//import androidx.lifecycle.LiveData
//import com.moonlitdoor.amessage.database.dao.ConversationDao
//import com.moonlitdoor.amessage.domain.mapper.ConversationConnectionMapper
//import com.moonlitdoor.amessage.domain.mapper.ConversationMapper
import com.moonlitdoor.amessage.domain.model.Conversation
//import com.moonlitdoor.amessage.extensions.map
import javax.inject.Inject

class ConversationRepository @Inject constructor(/*private val conversationDao: ConversationDao*/) {

  val conversations = Unit//: LiveData<List<Conversation>> = conversationDao.get().map { it.map { Conversation.from(it) } }

  fun create(conversation: Conversation) {

//    conversationDao.insert(ConversationMapper.toEntity(conversation), ConversationConnectionMapper.toEntityList(conversation))

  }
}