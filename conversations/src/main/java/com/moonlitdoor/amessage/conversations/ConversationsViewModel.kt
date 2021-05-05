package com.moonlitdoor.amessage.conversations

import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.domain.repository.ConversationRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ConversationsViewModel @Inject constructor(val repository: ConversationRepository, profileRepository: ProfileRepository) : ViewModel() {

  val isHandleSet: Flow<Boolean> = profileRepository.handleIsSet()

  val viewState: Flow<ConversationsViewState> = repository.getConversations().map {
    when (it.isNotEmpty()) {
      true -> ConversationsViewState.Result(it)
      false -> ConversationsViewState.Empty
    }
  }
}
