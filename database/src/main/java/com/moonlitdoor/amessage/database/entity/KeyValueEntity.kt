package com.moonlitdoor.amessage.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.moonlitdoor.amessage.database.projection.HandleProjection
import com.moonlitdoor.amessage.database.projection.IdProjection
import com.moonlitdoor.amessage.database.projection.PasswordProjection
import com.moonlitdoor.amessage.database.projection.SaltProjection
import com.moonlitdoor.amessage.database.projection.TokenProjection

@Entity(tableName = "key_value", indices = [Index(value = ["key"], unique = true)])
data class KeyValueEntity(
  @PrimaryKey
  val key: String,
  val value: String
) {
  companion object {

    fun from(id: HandleProjection) = KeyValueEntity(key = id.key, value = id.value.toString())

    fun from(id: TokenProjection) = KeyValueEntity(key = id.key, value = id.value.toString())

    fun from(id: IdProjection) = KeyValueEntity(key = id.key, value = id.value.toString())

    fun from(id: PasswordProjection) = KeyValueEntity(key = id.key, value = id.value.toString())

    fun from(id: SaltProjection) = KeyValueEntity(key = id.key, value = id.value.toString())

  }
}
