package com.moonlitdoor.amessage.domain.model

import com.moonlitdoor.amessage.database.entity.ConversationEntity
import java.util.*

data class Conversation(
  val id: Long = 0,
  val conversationId: UUID = UUID.randomUUID(),
  var title: String? = null,
  var topic: String? = null,
  val password: UUID = UUID.randomUUID(),
  val salt: UUID = UUID.randomUUID(),
  var participants: List<Connection> = listOf(),
  var messages: List<Message> = mutableListOf()
) {
  companion object {
    fun from(entity: ConversationEntity) = Conversation(
      entity.id,
      entity.conversationId,
      entity.title,
      entity.topic,
      entity.password,
      entity.salt
    )
  }
}

