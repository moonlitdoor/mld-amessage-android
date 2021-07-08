package com.moonlitdoor.amessage.connect.invited

import com.moonlitdoor.amessage.domain.model.Connection

sealed class InvitedViewState {

  object Loading : InvitedViewState()
  object Empty : InvitedViewState()
  data class Loaded(val items: List<Connection>) : InvitedViewState()
}
