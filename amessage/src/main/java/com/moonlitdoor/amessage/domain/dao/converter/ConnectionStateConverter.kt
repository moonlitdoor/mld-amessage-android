package com.moonlitdoor.amessage.domain.dao.converter

import androidx.room.TypeConverter
import com.moonlitdoor.amessage.domain.model.Connection

class ConnectionStateConverter {

  @TypeConverter
  fun to(state: Connection.State): String = state.value


  @TypeConverter
  fun to(state: String): Connection.State = when (state) {
    Connection.State.Pending.value -> Connection.State.Pending
    Connection.State.Invited.value -> Connection.State.Invited
    Connection.State.Connected.value -> Connection.State.Connected
    else -> throw IllegalStateException("state=$state")
  }

}
