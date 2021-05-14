package com.moonlitdoor.amessage.conversation

import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.domain.repository.ConversationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
  private val conversationRepository: ConversationRepository
) : ViewModel() {

  private val _conversationId: MutableStateFlow<Id?> = MutableStateFlow(null)

  @OptIn(FlowPreview::class)
  val viewState: Flow<ConversationViewState> = _conversationId.flatMapConcat { id: Id? ->
    id?.let { i ->
      conversationRepository.getConversationFlow(i).map { conversation ->
        conversation?.let { ConversationViewState.Result(it) } ?: ConversationViewState.Empty
      }
    } ?: flowOf(ConversationViewState.Loading)
  }

  fun setConversationId(conversationId: Id) {
    _conversationId.value = conversationId
  }

}
