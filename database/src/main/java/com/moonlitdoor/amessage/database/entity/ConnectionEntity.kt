package com.moonlitdoor.amessage.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "connection", indices = [Index(value = ["connection_id"], unique = true), Index(value = ["token"], unique = true)])
data class ConnectionEntity(
  @ColumnInfo(name = "connection_id")
  val connectionId: UUID,
  val password: UUID,
  val salt: UUID,
  val token: String,
  val handle: String,
  val state: State,
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0
) {

  sealed class State(val value: String) {
    object Scanned : State("scanned")
    object Queued : State("queued")
    object Pending : State("pending")
    object Invited : State("invited")
    object Connected : State("connected")
  }

}
