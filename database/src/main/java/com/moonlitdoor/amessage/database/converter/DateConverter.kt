package com.moonlitdoor.amessage.database.converter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

  @TypeConverter
  fun toDate(date: Long?): Date? = date?.let { Date(date) }


  @TypeConverter
  fun toTimestamp(date: Date?): Long? = date?.time

}
