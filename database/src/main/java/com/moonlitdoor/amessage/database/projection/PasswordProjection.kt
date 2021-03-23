package com.moonlitdoor.amessage.database.projection

import androidx.room.ColumnInfo
import java.util.*

data class PasswordProjection(
  @ColumnInfo(name = "value")
  val value: UUID
) {
  val key: String = PASSWORD

  companion object {

    const val PASSWORD = "password"
  }

}
