package com.moonlitdoor.amessage.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.moonlitdoor.amessage.database.entity.PayloadEntity

@Dao
interface PayloadDao {

  @Insert
  suspend fun insert(entity: PayloadEntity)

  @Query("SELECT * FROM payload WHERE type IN (:types)")
  suspend fun get(types: List<String>): List<PayloadEntity>

  @Delete
  suspend fun delete(entity: PayloadEntity)
}
