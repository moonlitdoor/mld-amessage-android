package com.moonlitdoor.amessage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moonlitdoor.amessage.database.entity.KeyValueEntity

@Dao
interface KeyValueDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertKeyValue(keyValueEntity: KeyValueEntity)

  @Query("SELECT value FROM key_value WHERE `key` = :key")
  suspend fun getValue(key: String): String?





}