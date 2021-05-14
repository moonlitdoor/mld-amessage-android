package com.moonlitdoor.amessage.connection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import com.moonlitdoor.amessage.domain.repository.ConversationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ConnectionViewModel @Inject constructor(
  private val connectionRepository: ConnectionRepository,
  private val conversationRepository: ConversationRepository
) : ViewModel() {

  private val _connectionId: MutableStateFlow<Id?> = MutableStateFlow(null)

  @OptIn(FlowPreview::class)
  val viewState: Flow<ConnectionViewState> = _connectionId.flatMapConcat { id: Id? ->
    id?.let { i ->
      connectionRepository.getConnectionWithConversationsFlow(i).map { connection ->
        connection?.let { ConnectionViewState.Result(it) } ?: ConnectionViewState.Empty
      }
    } ?: flowOf(ConnectionViewState.Loading)
  }

  fun setConnectionId(connectionId: UUID) {
    _connectionId.value = Id(connectionId)
  }

  fun createConversation(title: String?, topic: String?) = viewModelScope.launch(Dispatchers.IO) {
    conversationRepository.create(listOf(_connectionId.value?.value!!), title, topic)
  }
}
