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
interface ConversationDao {

  @Insert
  suspend fun insert(entity: ConversationEntity)

  @Insert
  suspend fun insert(entities: List<ConnectionConversationEntity>)

  @Transaction
  suspend fun insert(entity: ConversationEntity, entities: List<ConnectionConversationEntity>) {
    insert(entity)
    insert(entities)
  }

  @Query("SELECT * FROM conversation WHERE conversation_id = :conversationId")
  suspend fun get(conversationId: UUID): ConversationEntity?

  @Query("SELECT * FROM conversation WHERE conversation_id = :conversationId")
  fun getFlow(conversationId: UUID): Flow<ConversationEntity?>

  @Query("SELECT * FROM conversation")
  fun getFlow(): Flow<List<ConversationEntity>>
}
