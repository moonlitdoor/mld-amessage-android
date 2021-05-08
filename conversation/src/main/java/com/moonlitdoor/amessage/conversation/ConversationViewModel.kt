package com.moonlitdoor.amessage.conversation

import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.domain.model.Conversation
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.domain.repository.ConversationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
  private val conversationRepository: ConversationRepository
) : ViewModel() {

  fun getConversation(conversationId: UUID): Flow<Conversation> = conversationRepository.getConversation(Id(conversationId))
}
