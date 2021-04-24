package com.moonlitdoor.amessage.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "conversation", indices = [(Index(value = ["conversation_id"], unique = true))])
data class ConversationEntity(
  @ColumnInfo(name = "conversation_id")
  val conversationId: UUID,
  val title: String?,
  val topic: String?,
  val password: UUID,
  val salt: UUID,
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0
)
