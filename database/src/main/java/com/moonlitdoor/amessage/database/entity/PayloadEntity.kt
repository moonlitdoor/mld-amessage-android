package com.moonlitdoor.amessage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "payload")
data class PayloadEntity(
  @PrimaryKey(autoGenerate = true)
  val primary: Long = 0,
  val type: String,
  val id: UUID,
  val cipher: String
)
