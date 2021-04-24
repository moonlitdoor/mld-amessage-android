package com.moonlitdoor.amessage.database.converter

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

  @TypeConverter
  fun toDate(item: Long?): Date? = item?.let { Date(item) }

  @TypeConverter
  fun toTimestamp(item: Date?): Long? = item?.time
}
