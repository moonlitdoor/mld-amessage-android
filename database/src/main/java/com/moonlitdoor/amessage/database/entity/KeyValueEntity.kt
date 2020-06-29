package com.moonlitdoor.amessage.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "key_value", indices = [Index(value = ["key"], unique = true)])
data class KeyValueEntity(
  @PrimaryKey
  val key: String,
  val value: String
)
