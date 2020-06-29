package com.moonlitdoor.amessage.database.projection

import androidx.room.ColumnInfo

data class HandleProjection(
  @ColumnInfo(name = "value")
  val value: String?
)
