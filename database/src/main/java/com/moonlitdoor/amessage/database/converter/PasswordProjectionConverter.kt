package com.moonlitdoor.amessage.database.converter

import androidx.room.TypeConverter
import com.moonlitdoor.amessage.database.projection.PasswordProjection
import java.util.*

class PasswordProjectionConverter {

  @TypeConverter
  fun to(item: PasswordProjection): UUID = item.value


  @TypeConverter
  fun to(item: UUID): PasswordProjection = PasswordProjection(item)

}
