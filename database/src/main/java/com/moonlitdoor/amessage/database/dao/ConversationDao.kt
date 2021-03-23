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
  abstract fun insert(entity: ConversationEntity): Long

  @Insert
  abstract fun insert(entity: ConversationConnectionEntity)

  @Transaction
  open fun insert(entity: ConversationEntity, entities: List<ConversationConnectionEntity>) = insert(entity).also { id ->
    entities.forEach {
      insert(it.copy(conversationId = id))
    }
  }

  @Query("SELECT * FROM conversation")
  abstract fun get(): Flow<List<ConversationEntity>>

}