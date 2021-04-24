package com.moonlitdoor.amessage.database.converter

import androidx.room.TypeConverter
import com.moonlitdoor.amessage.database.projection.AssociatedDataProjection
import java.util.UUID

class AssociatedDateProjectionConverter {

  @TypeConverter
  fun to(item: AssociatedDataProjection): UUID = item.value

  @TypeConverter
  fun to(item: UUID): AssociatedDataProjection = AssociatedDataProjection(item)
}
