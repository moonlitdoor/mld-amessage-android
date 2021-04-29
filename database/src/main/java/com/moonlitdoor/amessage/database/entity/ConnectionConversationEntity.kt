package com.moonlitdoor.amessage.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.moonlitdoor.amessage.database.projection.IdProjection

@Entity(
  tableName = "connection_conversation",
  primaryKeys = ["connection_id", "conversation_id"],
  indices = [Index(value = ["connection_id"]), Index(value = ["conversation_id"])],
  foreignKeys = [
    ForeignKey(entity = ConnectionEntity::class, parentColumns = ["connection_id"], childColumns = ["connection_id"], onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = ConversationEntity::class, parentColumns = ["conversation_id"], childColumns = ["conversation_id"], onDelete = ForeignKey.CASCADE),
  ]
)
data class ConnectionConversationEntity(
  @ColumnInfo(name = "connection_id")
  val connectionId: IdProjection,
  @ColumnInfo(name = "conversation_id")
  var conversationId: IdProjection,
)
