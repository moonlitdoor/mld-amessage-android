package com.moonlitdoor.amessage.domain.model

import java.time.Instant

data class ConnectionWithConversations(
  val profileId: Id,
  val connectionId: Id,
  val handle: Handle,
  val token: Token,
  val associatedData: AssociatedData,
  val keys: Keys,
  val state: Connection.State,
  val scanned: Instant,
  val confirmed: Instant? = null,
  val conversations: List<Conversation> = emptyList()
)
