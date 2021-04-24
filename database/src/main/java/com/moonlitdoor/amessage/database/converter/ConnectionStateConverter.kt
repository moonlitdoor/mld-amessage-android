package com.moonlitdoor.amessage.database.converter

import androidx.room.TypeConverter
import com.moonlitdoor.amessage.database.entity.ConnectionEntity

class ConnectionStateConverter {

  @TypeConverter
  fun to(item: ConnectionEntity.State): String = item.value

  @TypeConverter
  fun to(item: String): ConnectionEntity.State = when (item) {
    ConnectionEntity.State.Scanned.value -> ConnectionEntity.State.Scanned
    ConnectionEntity.State.Queued.value -> ConnectionEntity.State.Queued
    ConnectionEntity.State.Pending.value -> ConnectionEntity.State.Pending
    ConnectionEntity.State.Invited.value -> ConnectionEntity.State.Invited
    ConnectionEntity.State.Connected.value -> ConnectionEntity.State.Connected
    else -> throw IllegalStateException("state=$item")
  }
}
