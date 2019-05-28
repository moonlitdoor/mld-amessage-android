package com.moonlitdoor.amessage.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.moonlitdoor.amessage.domain.model.Conversation

@Entity(
  tableName = "conversation_connection",
  primaryKeys = ["conversation_id", "connection_id"],
  indices = [Index(value = ["conversation_id"]), Index(value = ["connection_id"])],
  foreignKeys = [
    ForeignKey(entity = ConversationEntity::class, parentColumns = ["id"], childColumns = ["conversation_id"], onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = ConnectionEntity::class, parentColumns = ["id"], childColumns = ["connection_id"], onDelete = ForeignKey.CASCADE)
  ]
)
data class ConversationConnectionEntity(
  @ColumnInfo(name = "conversation_id")
  var conversationId: Long = 0,
  @ColumnInfo(name = "connection_id")
  val connectionId: Long
) {
  companion object {
    fun fromAsList(conversation: Conversation): List<ConversationConnectionEntity> = mutableListOf<ConversationConnectionEntity>().also {
      conversation.participants.forEach { connection ->
        it.add(ConversationConnectionEntity(connectionId = connection.id))
      }
    }
  }
}