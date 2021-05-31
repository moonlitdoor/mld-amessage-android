package com.moonlitdoor.amessage.connections

import com.moonlitdoor.amessage.domain.model.Connection

sealed class ConnectionsScreenState {

  object Loading : ConnectionsScreenState()
  object Empty : ConnectionsScreenState()
  data class Result(val items: List<Connection>) : ConnectionsScreenState()
}
