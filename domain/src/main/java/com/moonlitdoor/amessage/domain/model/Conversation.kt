package com.moonlitdoor.amessage.domain.model

import com.moonlitdoor.amessage.database.entity.ConversationEntity
import com.moonlitdoor.amessage.database.projection.AssociatedDataProjection
import com.moonlitdoor.amessage.database.projection.KeysProjection
import java.time.Instant
import java.util.UUID

data class Conversation(
  val conversationId: UUID = UUID.randomUUID(),
  var title: String? = null,
  var topic: String? = null,
  val created: Instant,
  var participants: List<Connection> = listOf(),
  var messages: List<Message> = mutableListOf(),
  val keys: KeysProjection,
  val associatedData: AssociatedDataProjection
) {
  companion object {
    fun from(entity: ConversationEntity) = Conversation(
      conversationId = entity.conversationId.value,
      title = entity.title?.value,
      topic = entity.topic?.value,
      created = entity.created,
      keys = entity.keys,
      associatedData = entity.associatedData
    )
  }
}
