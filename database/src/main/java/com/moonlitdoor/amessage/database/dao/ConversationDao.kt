package com.moonlitdoor.amessage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.moonlitdoor.amessage.database.entity.ConnectionConversationEntity
import com.moonlitdoor.amessage.database.entity.ConversationEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
abstract class ConversationDao {

  @Insert
  abstract suspend fun insert(entity: ConversationEntity)

  @Insert
  abstract suspend fun insert(entities: List<ConnectionConversationEntity>)

  @Transaction
  open suspend fun insert(entity: ConversationEntity, entities: List<ConnectionConversationEntity>) {
    insert(entity)
    insert(entities)
  }

  @Query("SELECT * FROM conversation WHERE conversation_id = :conversationId")
  abstract suspend fun get(conversationId: UUID): ConversationEntity

  @Query("SELECT * FROM conversation")
  abstract fun getFlow(): Flow<List<ConversationEntity>>
}
