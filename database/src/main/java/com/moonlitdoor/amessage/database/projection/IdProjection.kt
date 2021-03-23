package com.moonlitdoor.amessage.database.projection

import androidx.room.ColumnInfo
import java.util.*

data class IdProjection(
  @ColumnInfo(name = "value")
  val value: UUID
) {
  val key: String = ID

  companion object {

    const val ID = "id"
  }

}
