package com.moonlitdoor.amessage.connection

import com.moonlitdoor.amessage.domain.model.ConnectionWithConversations

sealed class ConnectionViewState {

  object Loading : ConnectionViewState()
  object Empty : ConnectionViewState()
  data class Result(val item: ConnectionWithConversations) : ConnectionViewState()
}
