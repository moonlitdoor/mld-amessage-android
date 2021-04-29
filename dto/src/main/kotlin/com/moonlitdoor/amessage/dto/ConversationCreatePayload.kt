package com.moonlitdoor.amessage.dto

import java.time.Instant
import java.util.UUID

class ConversationCreatePayload(
  val conversationId: UUID,
  val title: String?,
  val topic: String?,
  val created: Instant,
  val keys: KeysDto,
  val associatedData: AssociatedDataDto,
  val connectionUuids: Array<UUID>
) : Payload() {

  @Transient
  override val type: Type = Type.ConversationCreate

  companion object : PayLoadInflater<ConversationCreatePayload> {
    override fun inflate(json: String): ConversationCreatePayload = GSON.fromJson(json, ConversationCreatePayload::class.java)
  }

}
