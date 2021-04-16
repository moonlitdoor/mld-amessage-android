package com.moonlitdoor.amessage.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.moonlitdoor.amessage.database.projection.IdProjection

@Entity(
  tableName = "conversation_connection",
  primaryKeys = ["conversation_id", "connection_id"],
  indices = [Index(value = ["conversation_id"]), Index(value = ["connection_id"])],
  foreignKeys = [
    ForeignKey(entity = ConversationEntity::class, parentColumns = ["conversation_id"], childColumns = ["conversation_id"], onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = ConnectionEntity::class, parentColumns = ["connection_id"], childColumns = ["connection_id"], onDelete = ForeignKey.CASCADE)
  ]
)
data class ConversationConnectionEntity(
  @ColumnInfo(name = "conversation_id")
  var conversationId: IdProjection,
  @ColumnInfo(name = "connection_id")
  val connectionId: IdProjection
)