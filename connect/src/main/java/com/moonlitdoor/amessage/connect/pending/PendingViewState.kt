package com.moonlitdoor.amessage.connect.pending

import com.moonlitdoor.amessage.domain.model.Connection

sealed class PendingViewState {

  object Loading : PendingViewState()
  object Empty : PendingViewState()
  data class Loaded(val items: List<Connection>) : PendingViewState()
}
