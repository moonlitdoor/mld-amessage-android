package com.moonlitdoor.amessage.database.converter

import androidx.room.TypeConverter
import com.moonlitdoor.amessage.database.projection.HandleProjection

class HandleProjectionConverter {

  @TypeConverter
  fun to(item: HandleProjection): String = item.value

  @TypeConverter
  fun to(item: String?): HandleProjection = HandleProjection(item ?: "")
}
