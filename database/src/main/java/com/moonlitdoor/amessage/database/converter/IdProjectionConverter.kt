package com.moonlitdoor.amessage.database.converter

import androidx.room.TypeConverter
import com.moonlitdoor.amessage.database.projection.IdProjection
import java.util.UUID

class IdProjectionConverter {

  @TypeConverter
  fun to(item: IdProjection): UUID = item.value

  @TypeConverter
  fun to(item: UUID): IdProjection = IdProjection(item)
}
