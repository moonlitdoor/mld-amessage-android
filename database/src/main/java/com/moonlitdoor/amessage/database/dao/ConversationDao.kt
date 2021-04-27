package com.moonlitdoor.amessage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.moonlitdoor.amessage.database.entity.ConnectionConversationEntity
import com.moonlitdoor.amessage.database.entity.ConversationEntity
import kotlinx.coroutines.flow.Flow

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

  @Query("SELECT * FROM conversation")
  abstract fun get(): Flow<List<ConversationEntity>>
}
