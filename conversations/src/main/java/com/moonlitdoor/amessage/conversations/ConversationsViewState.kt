package com.moonlitdoor.amessage.conversations

import com.moonlitdoor.amessage.domain.model.Conversation

sealed class ConversationsViewState {

  object Loading : ConversationsViewState()
  object Empty : ConversationsViewState()
  data class Result(val items: List<Conversation>) : ConversationsViewState()
}
