package com.moonlitdoor.amessage.database.converter

import androidx.room.TypeConverter
import com.moonlitdoor.amessage.database.projection.KeysProjection

class KeysProjectionConverter {

  @TypeConverter
  fun to(item: KeysProjection): String = item.value

  @TypeConverter
  fun to(item: String?): KeysProjection = KeysProjection(item ?: "")
}
