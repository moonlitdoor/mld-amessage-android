package com.moonlitdoor.amessage.conversations

import com.moonlitdoor.amessage.domain.model.Conversation

sealed class ConversationsScreenState {

  object Loading : ConversationsScreenState()
  object Empty : ConversationsScreenState()
  object HandleNotSet : ConversationsScreenState()
  data class Result(val items: List<Conversation>) : ConversationsScreenState()
}
