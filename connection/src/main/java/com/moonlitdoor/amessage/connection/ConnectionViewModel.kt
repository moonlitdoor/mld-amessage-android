package com.moonlitdoor.amessage.connection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import com.moonlitdoor.amessage.domain.repository.ConversationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ConnectionViewModel @Inject constructor(
  private val connectionRepository: ConnectionRepository,
  private val conversationRepository: ConversationRepository
) : ViewModel() {

  fun getConnection(connectionId: UUID): Flow<Connection> = connectionRepository.getFlow(connectionId = connectionId)

  fun createConversation(connectionId: List<UUID>, title: String?, topic: String?) = viewModelScope.launch(Dispatchers.IO) {
    conversationRepository.create(connectionId, title, topic)
  }
}
