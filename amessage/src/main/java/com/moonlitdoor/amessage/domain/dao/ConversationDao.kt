package com.moonlitdoor.amessage.domain.dao

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.moonlitdoor.amessage.domain.entity.ConversationConnectionEntity
import com.moonlitdoor.amessage.domain.entity.ConversationEntity

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

  @MainThread
  @Query("SELECT * FROM conversation")
  abstract fun get(): LiveData<List<ConversationEntity>>

}