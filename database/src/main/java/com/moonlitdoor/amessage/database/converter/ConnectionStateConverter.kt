package com.moonlitdoor.amessage.database.converter

import androidx.room.TypeConverter
import com.moonlitdoor.amessage.database.entity.ConnectionEntity

class ConnectionStateConverter {

  @TypeConverter
  fun to(state: ConnectionEntity.State): String = state.value

  @TypeConverter
  fun to(state: String): ConnectionEntity.State = when (state) {
    ConnectionEntity.State.Pending.value -> ConnectionEntity.State.Pending
    ConnectionEntity.State.Invited.value -> ConnectionEntity.State.Invited
    ConnectionEntity.State.Connected.value -> ConnectionEntity.State.Connected
    else -> throw IllegalStateException("state=$state")
  }

}
