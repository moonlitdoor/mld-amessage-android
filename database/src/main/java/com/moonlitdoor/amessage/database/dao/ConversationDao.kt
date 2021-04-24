package com.moonlitdoor.amessage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.moonlitdoor.amessage.database.entity.ConversationConnectionEntity
import com.moonlitdoor.amessage.database.entity.ConversationEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ConversationDao {

  @Insert
  abstract fun insert(entity: ConversationEntity)

  @Insert
  abstract fun insert(entities: List<ConversationConnectionEntity>)

  @Transaction
  open fun insert(entity: ConversationEntity, entities: List<ConversationConnectionEntity>) {
    insert(entity)
    insert(entities)
  }

  @Query("SELECT * FROM conversation")
  abstract fun get(): Flow<List<ConversationEntity>>
}
