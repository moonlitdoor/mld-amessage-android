package com.moonlitdoor.amessage.conversations

import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.domain.repository.ConversationRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class ConversationsViewModel @Inject constructor(repository: ConversationRepository, profileRepository: ProfileRepository) : ViewModel() {

  val viewState: Flow<ConversationsViewState> = profileRepository.handleIsSet()
    .combine(repository.getConversationsFlow()) { isHandleSet, conversations ->
      when {
        !isHandleSet -> ConversationsViewState.HandleNotSet
        conversations.isEmpty() -> ConversationsViewState.Empty
        else -> ConversationsViewState.Result(conversations)
      }
    }
}
