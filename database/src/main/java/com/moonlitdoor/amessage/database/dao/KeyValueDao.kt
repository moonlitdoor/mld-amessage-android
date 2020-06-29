package com.moonlitdoor.amessage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moonlitdoor.amessage.database.entity.KeyValueEntity
import java.util.*

@Dao
abstract class KeyValueDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun insertKeyValue(keyValueEntity: KeyValueEntity)

  @Query("SELECT value FROM key_value WHERE `key` = :key")
  abstract suspend fun getValue(key: String): String?

  suspend fun insertId(value: UUID) = insertKeyValue(KeyValueEntity(ID, value.toString()))

  suspend fun getId() = getValue(ID).toUUID()

  suspend fun insertPassword(value: UUID) = insertKeyValue(KeyValueEntity(PASSWORD, value.toString()))

  suspend fun getPassword() = getValue(PASSWORD).toUUID()

  suspend fun insertSalt(value: UUID) = insertKeyValue(KeyValueEntity(SALT, value.toString()))

  suspend fun getSalt() = getValue(SALT).toUUID()

  private fun String?.toUUID() = this?.let { UUID.fromString(it) }

  companion object {
    private const val ID = "id"
    private const val PASSWORD = "password"
    private const val SALT = "salt"
  }

}