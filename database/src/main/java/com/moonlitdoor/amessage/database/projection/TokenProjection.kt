package com.moonlitdoor.amessage.database.projection

import androidx.room.ColumnInfo

data class TokenProjection(
  @ColumnInfo(name = "value")
  val value: String?
) {
  val key: String = TOKEN

  companion object {

    const val TOKEN = "token"
  }

}
