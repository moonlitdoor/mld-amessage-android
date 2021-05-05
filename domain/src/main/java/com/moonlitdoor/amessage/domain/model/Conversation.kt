package com.moonlitdoor.amessage.domain.model

import java.time.Instant

data class Conversation(
  val conversationId: Id = Id(),
  var title: String? = null,
  var topic: String? = null,
  val created: Instant,
  var participants: List<Connection> = listOf(),
  var messages: List<Message> = mutableListOf(),
  val keys: Keys,
  val associatedData: AssociatedData
)
