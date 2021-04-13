package com.moonlitdoor.amessage.database.converter

import androidx.room.TypeConverter
import java.util.*

class UuidConverter {

  @TypeConverter
  fun to(item: UUID?): String? = item?.toString()


  @TypeConverter
  fun to(item: String?): UUID? = item?.let { UUID.fromString(it) }

}
