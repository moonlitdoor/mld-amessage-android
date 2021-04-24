package com.moonlitdoor.amessage.domain.repository

// import androidx.lifecycle.LiveData
// import com.moonlitdoor.amessage.database.dao.ConversationDao
// import com.moonlitdoor.amessage.domain.mapper.ConversationConnectionMapper
// import com.moonlitdoor.amessage.domain.mapper.ConversationMapper
import com.moonlitdoor.amessage.database.dao.ConversationDao
import com.moonlitdoor.amessage.domain.model.Conversation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ConversationRepository @Inject constructor(private val conversationDao: ConversationDao) {

  val conversations: Flow<List<Conversation>> = conversationDao.get().map { list -> list.map { Conversation.from(it) } }

  fun create(conversation: Conversation) {

//    conversationDao.insert(ConversationMapper.toEntity(conversation), ConversationConnectionMapper.toEntityList(conversation))
  }
}
