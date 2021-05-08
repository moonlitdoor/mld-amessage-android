package com.moonlitdoor.amessage.conversation

import com.moonlitdoor.amessage.domain.model.Conversation

sealed class ConversationViewState {

  object Loading : ConversationViewState()
  object Empty : ConversationViewState()
  data class Result(val item: Conversation) : ConversationViewState()
}
