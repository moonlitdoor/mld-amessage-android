package com.moonlitdoor.amessage.database.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.moonlitdoor.amessage.database.entity.ConnectionConversationEntity
import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.database.entity.ConversationEntity

data class ConnectionConversationsRelation(
  @Embedded
  val connection: ConnectionEntity,
  @Relation(
    parentColumn = "connection_id",
    entityColumn = "conversation_id",
    associateBy = Junction(ConnectionConversationEntity::class)
  )
  val conversations: List<ConversationEntity>
)
