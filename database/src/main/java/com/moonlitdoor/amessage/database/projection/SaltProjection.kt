package com.moonlitdoor.amessage.database.projection

import androidx.room.ColumnInfo
import java.util.*

data class SaltProjection(
  @ColumnInfo(name = "value")
  val value: UUID
) {
  val key: String = SALT

  companion object {

    const val SALT = "salt"
  }

}
