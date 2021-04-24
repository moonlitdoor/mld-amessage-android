package com.moonlitdoor.amessage.connections

import com.moonlitdoor.amessage.domain.model.Connection

sealed class ConnectionsViewState {

  object Loading : ConnectionsViewState()
  object Empty : ConnectionsViewState()
  data class Result(val items: List<Connection>) : ConnectionsViewState()
}
